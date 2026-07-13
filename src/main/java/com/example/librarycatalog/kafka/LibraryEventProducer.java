package com.example.librarycatalog.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * Publishes LibraryEvent messages to Kafka topics.
 *
 * Topics:
 *   library.books   – book-related events
 *   library.authors – author-related events
 */
@Service
public class LibraryEventProducer {

    private static final Logger log = LoggerFactory.getLogger(LibraryEventProducer.class);

    public static final String TOPIC_BOOKS   = "library.books";
    public static final String TOPIC_AUTHORS = "library.authors";

    private final KafkaTemplate<String, LibraryEvent> kafkaTemplate;

    public LibraryEventProducer(KafkaTemplate<String, LibraryEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    /**
     * Publish a book-related event.
     */
    public void publishBookEvent(String eventType, Long bookId, String description) {
        LibraryEvent event = new LibraryEvent(eventType, "BOOK", bookId, description);
        log.info("Publishing book event: {}", event);
        kafkaTemplate.send(TOPIC_BOOKS, String.valueOf(bookId), event);
    }

    /**
     * Publish an author-related event.
     */
    public void publishAuthorEvent(String eventType, Long authorId, String description) {
        LibraryEvent event = new LibraryEvent(eventType, "AUTHOR", authorId, description);
        log.info("Publishing author event: {}", event);
        kafkaTemplate.send(TOPIC_AUTHORS, String.valueOf(authorId), event);
    }
}
