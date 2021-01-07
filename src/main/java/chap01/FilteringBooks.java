package chap01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class FilteringBooks {

	public static void main(String... args) {

		List<Book> inventory = Arrays.asList(
				new Book(50, "Trip To Europe Catalog"),
				new Book(50, "Trip To Egypt Catalog"),
				new Book(90, "Pulp Fiction"),
				new Book(692, "Modern Java In Action"),
				new Book(340, "UX Design CookBook")
				);

		List<Book> ThickBooks = filterBooks(inventory, FilteringBooks::isThick);
		System.out.println(ThickBooks);

		List<Book> Catalogs = filterBooks(inventory, FilteringBooks::isCatalog);
		System.out.println(Catalogs);

		List<Book> ThickBooks2 = filterBooks(inventory, (Book b) -> b.getPages() > 300);
		System.out.println(ThickBooks2);

		List<Book> Catalogs2 = filterBooks(inventory, (Book b) -> b.getName().contains("Catalog"));
		System.out.println(Catalogs2);

	}

	public static List<Book> filterBooks(List<Book> inventory, Predicate<Book> p) {
		List<Book> result = new ArrayList<>();

		for (Book book : inventory) {
			if (p.test(book)) {
				result.add(book);
			}
		}

		return result;

	}

	public static boolean isThick (Book book) {
		return book.getPages() > 300;
	}

	public static boolean isCatalog (Book book) {
		return book.getName().contains("Catalog");
	}

	public static class Book {
		private int pages = 0;
		private String name = "";

		public Book(int pages, String name) {
			this.pages = pages;
			this.name = name;
		}

		public int getPages() {
			return pages;
		}

		public void setPages(int pages) {
			this.pages = pages;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		@Override
		public String toString() {
			return String.format("Book { pages = %d, name = '%s' }", pages, name);
		}
	}

}
