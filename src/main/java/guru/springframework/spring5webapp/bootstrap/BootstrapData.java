package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Author aut1 = new Author("Auteur", "1");
        Author aut2 = new Author("Auteur", "2");

        Book book1 = new Book("Book 1", "1213");
        Book book2 = new Book("Book 2", "1214");

        aut1.getBooks().add(book1);
        book1.getAuthors().add(aut1);

        aut2.getBooks().add(book2);
        book2.getAuthors().add(aut2);

        authorRepository.save(aut1);
        authorRepository.save(aut2);

        bookRepository.save(book1);
        bookRepository.save(book2);

        System.out.printf("Bootstrap");
        System.out.println("Nombres de livres : " + bookRepository.count());
    }
}
