package chap05;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import chap04.Dish;

public class Mapping {

	public static void main(String... args) {
		// map
		List<String> dishNames = Dish.menu.stream()
				.map(Dish::getName)
				.collect(Collectors.toList());
		System.out.println(dishNames);

		// map
		List<String> words = Arrays.asList("Hello", "World");
		List<Integer> wordLengths = words.stream()
				.map(String::length)
				.collect(Collectors.toList());
		System.out.println(wordLengths);

		// flatMap
		words.stream()
			.flatMap((String line) -> Arrays.stream(line.split("")))
			.distinct()
			.forEach(System.out::println);

		// flatMap
		List<Integer> numbers1 = Arrays.asList(1, 2, 3, 4, 5);
		List<Integer> numbers2 = Arrays.asList(1, 2, 3);
		List<Integer> numbers3 = Arrays.asList(3, 4);

		List<Integer> squares = numbers1.stream()
										.map(n -> n * n)
										.collect(Collectors.toList());
		System.out.println(squares);

		List<int[]> pairs1 = numbers2.stream()
										.flatMap(i -> numbers3.stream()
																.map(j -> new int[]{i, j})
										)
										.collect(Collectors.toList());
		System.out.println(pairs1);

		List<int[]> pairs2 = numbers2.stream()
										.flatMap(i -> numbers3.stream()
																.filter(j -> (i + j) % 3 == 0)
																.map(j -> new int[]{i, j})
										)
										.collect(Collectors.toList());
		System.out.println(pairs2);

	}

}
