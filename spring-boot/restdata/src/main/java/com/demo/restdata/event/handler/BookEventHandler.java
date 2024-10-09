package com.demo.restdata.event.handler;

import com.demo.restdata.entity.BookEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeDelete;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;


@Component
@RepositoryEventHandler
public class BookEventHandler {

    private static final Logger log = LoggerFactory.getLogger(BookEventHandler.class);

    @HandleBeforeCreate
    public void createBookEvent(BookEntity book) {
        log.info("Before creating book: " + book.getTitle());
    }

    @HandleBeforeSave
    public void updateBookEvent(BookEntity book) {
        log.info("Before updating book: " + book.getTitle());
    }

    @HandleBeforeDelete
    public void deleteBookEvent(BookEntity book) {
        log.info("Before deleting the book: " + book.getTitle());
    }
}
