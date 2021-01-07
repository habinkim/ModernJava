package chap05;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Practice {

	public static void main(String... args) {

		Trader raoul = new Trader("Rauol", "Cambridge");
		Trader mario = new Trader("Mario", "Milan");
		Trader alan = new Trader("Alan", "Cambridge");
		Trader brian = new Trader("Brian", "Cambridge");

		List<Transaction> transactions = Arrays.asList(
			new Transaction(brian, 2011, 300),
			new Transaction(raoul, 2012, 1000),
			new Transaction(raoul, 2011, 400),
			new Transaction(mario, 2012, 710),
			new Transaction(mario, 2012, 700),
			new Transaction(alan, 2012, 950)
		);

		/* 1. 2011년에 일어난 모든 트랜잭션을 찾아 값을 오름차순으로 정리하시오. */
		List<Transaction> result1 = transactions.stream()
		.filter(transaction -> transaction.getYear() == 2011)
		.sorted(Comparator.comparing(Transaction::getValue))
		.collect(Collectors.toList());

		System.out.println("result1 : " + result1);

		/* 2. 거래자가 근무하는 모든 도시를 중복 없이 나열하시오. */
		List<String> result2 = transactions.stream()
		.map(transaction -> transaction.getTrader().getCity())
		.distinct()
		.collect(Collectors.toList());

		System.out.println("result2 : " + result2);

		/* 3. 케임브리지에서 근무하는 모든 거래자를 찾아서 이름순으로 정렬하시오. */
		List<Trader> result3 = transactions.stream()
		.map(Transaction::getTrader)
		.filter(trader -> trader.getCity().equals("Cambridge"))
		.distinct()
		.sorted(Comparator.comparing(Trader::getName))
		.collect(Collectors.toList());

		System.out.println("result3 : " + result3);

		/* 4. 모든 거래자의 이름을 알파벳순으로 정렬해서 반환하시오. */
		String result4 = transactions.stream()
		.map(transaction -> transaction.getTrader().getName())
		.distinct()
		.sorted()
		.reduce("", (n1, n2) -> n1 + n2);

		System.out.println("result4 : " + result4);

		/* 5. 밀라노에 거래자가 있는가? */
		boolean result5 = transactions.stream()
		.anyMatch(transaction -> transaction.getTrader().getCity().equals("Milan"));

		System.out.println("result5 : " + result5);

		/* 6. 케임브리지에 거주하는 거래자의 모든 트랜잭션값을 출력하시오 */
		List<Transaction> result6 = transactions.stream()
		.filter(transaction -> transaction.getTrader().getCity().equals("Cambridge"))
		.collect(Collectors.toList());

		System.out.println("result6 : " + result6);

		/* 7. 전체 트랜잭션 중 최댓값은 얼마인가? */
		Optional<Integer> reduce = transactions.stream()
		.map(transaction -> transaction.getValue())
		.reduce(Integer::max);

		Integer result7 = reduce.get();

		System.out.println("result7 : " + result7);

		/* 8. 전체 트랜잭션 중 최솟값은 얼마인가? */
		Optional<Integer> reduce2 = transactions.stream()
		.map(transaction -> transaction.getValue())
		.reduce(Integer::min);

		Integer result8 = reduce2.get();

		System.out.println("result8 : " + result8);

	}

}
