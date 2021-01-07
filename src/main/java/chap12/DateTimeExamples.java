package chap12;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.Period;
import java.time.chrono.JapaneseDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateTimeExamples {

	private static final ThreadLocal<DateFormat> formatters = new ThreadLocal<DateFormat>() {

		@Override
		protected DateFormat initialValue() {
			return new SimpleDateFormat("dd-MMM-yyyy");
		}

	};

	public static void main(String... args) {

		useOldDate();
		useLocalDate();
		useTemporalAdjuster();
		useDateFormatter();

	}

	private static void useOldDate() {
		Date date = new Date(114, 2, 18);
		System.out.println(date);

		System.out.println(formatters.get().format(date));

		Calendar calendar = Calendar.getInstance();
		calendar.set(2014, Calendar.FEBRUARY, 18);
		System.out.println(calendar);
	}

	private static void useLocalDate() {
		LocalDate date = LocalDate.of(2014, 3, 18);
		int year = date.getYear();
		Month month = date.getMonth();
		int day = date.getDayOfMonth();
		DayOfWeek dow = date.getDayOfWeek();
		int len = date.lengthOfMonth();
		boolean leap = date.isLeapYear();
		System.out.println(date);

		LocalTime time = LocalTime.of(13, 45, 20);
		int hour = time.getHour();
		int minute = time.getMinute();
		int second = time.getSecond();
		System.out.println(time);

		LocalDateTime dt1 = LocalDateTime.of(2014, Month.MARCH, 18, 13 ,45, 20);
		LocalDateTime dt2 = LocalDateTime.of(date, time);
		LocalDateTime dt3 = date.atTime(13, 45, 20);
		LocalDateTime dt4 = date.atTime(time);
		LocalDateTime dt5 = time.atDate(date);
		System.out.println(dt1);

		LocalDate date1 = dt1.toLocalDate();
		System.out.println(date1);
		LocalTime time1 = dt1.toLocalTime();
		System.out.println(time1);

		Instant instant = Instant.ofEpochSecond(44 * 365 * 86400);
		Instant now = Instant.now();

		Duration d1 = Duration.between(LocalTime.of(13, 45, 10), time);
		Duration d2 = Duration.between(instant, now);
		System.out.println(d1.getSeconds());
		System.out.println(d2.getSeconds());

		Duration threeMinutes = Duration.of(3, ChronoUnit.MINUTES);
		System.out.println(threeMinutes);

		Period tenDays = Period.ofDays(10);
		System.out.println(tenDays);
		Period threeWeeks = Period.ofWeeks(3);
		System.out.println(threeWeeks);
		Period twoYearsSixMonthsOneDay = Period.of(2, 6, 1);
		System.out.println(twoYearsSixMonthsOneDay);

		JapaneseDate japaneseDate = JapaneseDate.from(LocalDate.of(2020, 9, 25));
		System.out.println(japaneseDate);
	}

	private static void useTemporalAdjuster() {
		LocalDate date = LocalDate.of(2014, 3, 18);
		date = date.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
		System.out.println(date);
		date = date.with(TemporalAdjusters.lastDayOfMonth());
		System.out.println(date);

		date = date.with(new NextWorkingDay());
		System.out.println(date);
		date = date.with(TemporalAdjusters.nextOrSame(DayOfWeek.FRIDAY));
		System.out.println(date);
		date = date.with(new NextWorkingDay());
		System.out.println(date);

		date = date.with(TemporalAdjusters.nextOrSame(DayOfWeek.FRIDAY));
		System.out.println(date);
		date = date.with(temporal -> {
			DayOfWeek dow = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
			int dayToAdd = 1;
			if (dow == DayOfWeek.FRIDAY) {
				dayToAdd = 3;
			}
			if (dow == DayOfWeek.SATURDAY) {
				dayToAdd = 2;
			}
			return temporal.plus(dayToAdd, ChronoUnit.DAYS);
		});
		System.out.println(date);
	}

	private static class NextWorkingDay implements TemporalAdjuster {

		@Override
		public Temporal adjustInto(Temporal temporal) {

			DayOfWeek dow = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
			int dayToAdd = 1;
			if (dow == DayOfWeek.FRIDAY) {
				dayToAdd = 3;
			}
			if (dow == DayOfWeek.SATURDAY) {
				dayToAdd = 2;
			}

			return temporal.plus(dayToAdd, ChronoUnit.DAYS);
		}

	}

	private static void useDateFormatter() {
		LocalDate date = LocalDate.of(2014, 3, 18);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		DateTimeFormatter italianFormatter = DateTimeFormatter.ofPattern("d. MMMM yyyy", Locale.ITALIAN);

		System.out.println(date.format(DateTimeFormatter.ISO_LOCAL_DATE));
		System.out.println(date.format(formatter));
		System.out.println(date.format(italianFormatter));

		DateTimeFormatter complexFormatter = new DateTimeFormatterBuilder()
				.appendText(ChronoField.DAY_OF_MONTH)
				.appendLiteral(". ")
				.appendText(ChronoField.MONTH_OF_YEAR)
				.appendLiteral(" ")
				.appendText(ChronoField.YEAR)
				.parseCaseInsensitive()
				.toFormatter(Locale.ITALIAN);

		System.out.println(date.format(complexFormatter));
	}

}
