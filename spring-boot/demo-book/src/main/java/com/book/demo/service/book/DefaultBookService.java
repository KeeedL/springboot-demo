package com.book.demo.service.book;

import com.book.demo.dto.AuthorDto;
import com.book.demo.dto.BookDto;
import com.book.demo.entity.BookEntity;
import com.book.demo.mapper.BookMapper;
import com.book.demo.repository.BookRepository;
import com.book.demo.service.author.AuthorService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DefaultBookService implements BookService {

    private final BookRepository repository;
    private final BookMapper mapper;
    private final AuthorService authorService;

    public DefaultBookService(BookRepository repository, BookMapper mapper, AuthorService authorService) {
        this.repository = repository;
        this.mapper = mapper;
        this.authorService = authorService;
    }

    @Override
    @Transactional
    public BookEntity postBook(final BookDto dto) {
        final var entity = new BookEntity();
        mapper.toEntity(dto, entity);

        dto.authors().forEach(authorDto -> {
            addNewAuthorToBook(authorDto, entity);
        });

        return repository.save(entity);
    }

    @Override
    @Transactional
    public Optional<BookEntity> updateBook(final long id, final BookDto dto) {
        final var entity = repository.findById(id);

        if(entity.isPresent()) {
            mapper.toEntity(dto, entity.get());

            // Remove authors
            final var inputAuthorIds = dto.authors().stream()
                    .map(AuthorDto::id)
                    .collect(Collectors.toSet());
            entity.get().getAuthors().removeIf(currentAuthor -> !inputAuthorIds.contains(currentAuthor.getId()));

            // Create new or add existing authors
            dto.authors().forEach(author -> {
                addNewAuthorToBook(author, entity.get());
            });

            return Optional.of(repository.save(entity.get()));
        }

        return entity;
    }

    @Override
    public void deleteBook(final long id) {
        repository.deleteById(id);
    }

    @Override
    public List<BookEntity> getBooks() {
        return repository.findAll();
    }

    @Override
    public Optional<BookEntity> getBookById(final long id) {
        return repository.findById(id);
    }

    private void addNewAuthorToBook(final AuthorDto author, final BookEntity book) {
        final var isNewAuthorToBook = book.getAuthors() == null || book.getAuthors().stream()
                .noneMatch(currentAuthor ->
                        currentAuthor.getFirstName().equals(author.firstName())
                        && currentAuthor.getLastName().equals(author.lastName())
                );

        if(isNewAuthorToBook) {
            final var existingAuthor = authorService.getAuthor(author.firstName(), author.lastName());
            existingAuthor.ifPresentOrElse(
                    book::addAuthor,
                    () -> book.addAuthor(authorService.postAuthor(author))
            );
        }

    }

}
