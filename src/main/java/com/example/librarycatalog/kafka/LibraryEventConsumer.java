package com.example.librarycatalog.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * Listens to Kafka topics and logs received library events.
 */
@Service
public class LibraryEventConsumer {

    private static final Logger log = LoggerFactory.getLogger(LibraryEventConsumer.class);

    @KafkaListener(
        topics   = LibraryEventProducer.TOPIC_BOOKS,
        groupId  = "library-group"
    )
    public void consumeBookEvent(LibraryEvent event) {
        log.info("[KAFKA] Book event received → {}", event);
    }

    @KafkaListener(
        topics   = LibraryEventProducer.TOPIC_AUTHORS,
        groupId  = "library-group"
    )
    public void consumeAuthorEvent(LibraryEvent event) {
        log.info("[KAFKA] Author event received → {}", event);
    }
}
