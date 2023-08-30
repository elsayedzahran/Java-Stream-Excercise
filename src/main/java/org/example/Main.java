package org.example;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;
import java.util.stream.Stream;

public class Main {

    public static void main(final String[] args) {

        List<Author> authors = Library.getAuthors();

        banner("Authors information");
        // TODO With functional interfaces declared
        Consumer<Author> printerAuthor = System.out::println;
        authors
                .stream()
                .forEach(printerAuthor);

        // TODO With functional interfaces used directly
        authors
                .stream()
                .forEach(System.out::println);

        banner("Active authors");
        // TODO With functional interfaces declared
        Predicate<Author> activeAuthor = author -> author.active == true;
        authors
                .stream()
                .filter(activeAuthor)
                .forEach(printerAuthor);
        // TODO With functional interfaces used directly
        authors
                .stream()
                .filter(author -> author.active == true)
                .forEach(System.out::println);
        banner("Active books for all authors");
        Consumer<Book> printerBooks = System.out::println;
        // TODO With functional interfaces declared
        Function<Author, Stream<Book>> streamBook = author -> author.books.stream();
        Predicate<Book> isPublished = book -> book.published == true;
        authors
                .stream()
                .flatMap( streamBook)
                .filter( isPublished)
                .forEach(printerBooks);
        // TODO With functional interfaces used directly
        authors
                .stream()
                .flatMap( author -> author.books.stream())
                .filter( book -> book.published == true)
                .forEach(System.out::println);
        banner("Average price for all books in the library");
        // TODO With functional interfaces declared
        ToIntFunction<Book> getPrice=(book)-> book.price;
        authors
                .stream()
                .flatMap( streamBook)
                .mapToInt(getPrice )
                .average()
                .orElse(0);
        // TODO With functional interfaces used directly
        authors
                .stream()
                .flatMap( author -> author.books.stream())
                .mapToInt(book -> book.price )
                .average()
                .orElse(0);
        banner("Active authors that have at least one published book");
        // TODO With functional interfaces declared
        Predicate<Author> streamBookAndIsPublished = author -> author.books.stream().anyMatch(isPublished);
        authors
                .stream()
                .filter(activeAuthor)
                .filter(streamBookAndIsPublished)
                .forEach(printerAuthor);
        // TODO With functional interfaces used directly
        authors
                .stream()
                .filter(author -> author.active == true)
                .filter(author -> author.books.stream().anyMatch(book -> book.published == true))
                .forEach(System.out::println);

    }

    private static void banner(final String m) {
        System.out.println("#### " + m + " ####");
    }
}


