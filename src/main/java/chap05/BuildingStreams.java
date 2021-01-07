package chap05;

import java.util.Arrays;
import java.util.stream.Stream;

public class BuildingStreams {

	public static void main(String... args) throws Exception {

		Stream<String> stream = Stream.of("Java 8", "Lambdas", "In", "Action");
		stream.map(String::toUpperCase).forEach(System.out::println);

		Stream<String> emptyStream = Stream.empty();

		int[] numbers = { 2, 3, 5, 6, 11, 13 };
		System.out.println(Arrays.stream(numbers).sum());

		Stream.iterate(0, n -> n + 2)
			.limit(10)
			.forEach(System.out::println);

		Stream.iterate(new int[] { 0, 1 }, t -> new int[] { t[1], t[0] + t[1] })
			.limit(10)
			.forEach(t -> System.out.printf("(%d, %d)", t[0], t[1]));
		System.out.println();

		Stream.iterate(new int[] { 0, 1 }, t-> new int[] { t[1], t[0] + t[1] })
			.limit(10)
			.map(t -> t[0])
			.forEach(System.out::println);

	}

}
