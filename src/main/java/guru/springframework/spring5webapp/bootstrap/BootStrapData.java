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

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository,
            PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Started in Bootstrap");

        Publisher sp = new Publisher();
        sp.setName("SFG Publishing");
        sp.setCity("St Petersburg");
        sp.setState("FL");

        publisherRepository.save(sp);

        System.out.println("Number of publishers " + publisherRepository.count());

        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Drive Design", "123123");
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);
        sp.getBooks().add(ddd);
        ddd.setPublisher(sp);

        authorRepository.save(eric);
        bookRepository.save(ddd);
        publisherRepository.save(sp);

        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EE Development without EJB", "3939459459");
        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);
        sp.getBooks().add(noEJB);
        noEJB.setPublisher(sp);

        authorRepository.save(rod);
        bookRepository.save(noEJB);
        publisherRepository.save(sp);

        System.out.println("Number of books: " + bookRepository.count());
        System.out.println("Publisher number of books: " + sp.getBooks().size());
    }
}
