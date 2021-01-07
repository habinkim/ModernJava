import java.util.Scanner;

public class Main {

	public static void main(String... main) {

		final Long LIMIT = (long) Math.pow(10, 1000);

		Scanner sc = new Scanner(System.in);

		Long amount; Long index;

		amount = sc.nextLong();
		index = sc.nextLong();

		if(amount >= 1 && (amount <= LIMIT && index <= LIMIT) && amount >= index) {
			System.out.printf("%d\n", amount / index);
			System.out.printf("%d", amount % index);
		}

		sc.close();

	}

}
