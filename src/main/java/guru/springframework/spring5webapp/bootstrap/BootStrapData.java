package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Publisher publisher = new Publisher();
        publisher.setName("SFG Publishing");
        publisher.setCity("St Petersburg");
        publisher.setState("FL");

        publisherRepository.save(publisher);

        Author aut1 = new Author("Auteur", "1");
        Author aut2 = new Author("Auteur", "2");

        Book book1 = new Book("Book 1", "1213");
        Book book2 = new Book("Book 2", "1214");
        Book book3 = new Book("Book 3", "fddgfgd");

        aut1.getBooks().add(book1);
        book1.getAuthors().add(aut1);

        aut2.getBooks().add(book2);
        book2.getAuthors().add(aut2);

        aut2.getBooks().add(book3);
        book3.getAuthors().add(aut2);

        authorRepository.save(aut1);
        authorRepository.save(aut2);

        bookRepository.save(book1);
        bookRepository.save(book2);
        bookRepository.save(book3);

        book1.setPublisher(publisher);
        publisher.getBooks().add(book1);
        book2.setPublisher(publisher);
        publisher.getBooks().add(book2);

        publisherRepository.save(publisher);

        System.out.printf("Bootstrap");
        System.out.println("Nombres de livres : " + bookRepository.count());
        System.out.println("Publisher books : " + publisher.getBooks().size());
    }
}
