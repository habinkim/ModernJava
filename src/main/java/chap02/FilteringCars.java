package chap02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FilteringCars {

	public static void main(String... args) {
		List<Car> garage = Arrays.asList(
				new Car(200, Color.BLUE),
				new Car(300, Color.GREEN),
				new Car(450, Color.BLUE),
				new Car(400, Color.RED),
				new Car(500, Color.RED)
				);

		List<Car> redCars = filterCarsByColor(garage, Color.RED);
		System.out.println(redCars);

		List<Car> blueCars = filterCarsByColor(garage, Color.BLUE);
		System.out.println(blueCars);

		List<Car> redCars2 = filter(garage, new CarColorPredicate());
		System.out.println(redCars2);

		List<Car> muscleCars = filter(garage, new CarHorsePowerPredicate());
		System.out.println(muscleCars);

		List<Car> blueMuscleCars = filter(garage, new CarBlueAndMusclePredicate());
		System.out.println(blueMuscleCars);

	}

	public static List<Car> filterCarsByColor(List<Car> garage, Color color) {

		List<Car> result = new ArrayList<>();
		for (Car car : garage) {
			if (car.getColor() == color) {
				result.add(car);
			}
		}

		return result;

	}

	public static List<Car> filterCarsByHorsePower(List<Car> garage, int horsepower) {

		List<Car> result = new ArrayList<>();
		for (Car car : garage) {
			if (car.getHorsepower() > horsepower) {
				result.add(car);
			}
		}

		return result;

	}

	public static List<Car> filter(List<Car> garage, CarPredicate p) {
		List<Car> result = new ArrayList<>();
		for (Car car : garage) {
			if (p.test(car)) {
				result.add(car);
			}
		}

		return result;
	}

	enum Color {
		RED,
		GREEN,
		BLUE
	}

	public static class Car {
		private int horsepower = 0;
		private Color color;

		public Car(int horsepower, Color color) {
			this.horsepower = horsepower;
			this.color = color;
		}

		public int getHorsepower() {
			return horsepower;
		}

		public void setHorsepower(int horsepower) {
			this.horsepower = horsepower;
		}

		public Color getColor() {
			return color;
		}

		public void setColor(Color color) {
			this.color = color;
		}

		@Override
		public String toString() {
			return String.format("Car { horsepower = %d, color = %s }", horsepower, color);
		}

	}

	interface CarPredicate {

		boolean test(Car c);

	}

	static class CarColorPredicate implements CarPredicate {

		@Override
		public boolean test(Car car) {
			return car.getColor() == Color.RED;
		}

	}

	static class CarHorsePowerPredicate implements CarPredicate {

		@Override
		public boolean test(Car car) {
			return car.getHorsepower() >= 400;
		}

	}

	static class CarBlueAndMusclePredicate implements CarPredicate {

		@Override
		public boolean test(Car car) {
			return car.getColor() == Color.BLUE && car.getHorsepower() >= 400;
		}

	}

}
