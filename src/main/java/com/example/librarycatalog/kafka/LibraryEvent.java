package com.example.librarycatalog.kafka;

import java.time.LocalDateTime;

/**
 * Generic event published to Kafka for any library entity action.
 * eventType: CREATED | UPDATED | DELETED
 * entityType: BOOK | AUTHOR
 */
public class LibraryEvent {

    private String eventType;   // CREATED, UPDATED, DELETED
    private String entityType;  // BOOK, AUTHOR
    private Long   entityId;
    private String description;
    private LocalDateTime occurredAt;

    public LibraryEvent() {}

    public LibraryEvent(String eventType, String entityType, Long entityId, String description) {
        this.eventType   = eventType;
        this.entityType  = entityType;
        this.entityId    = entityId;
        this.description = description;
        this.occurredAt  = LocalDateTime.now();
    }

    // ── Getters & Setters ──────────────────────────────────────────────────────

    public String getEventType()   { return eventType; }
    public void   setEventType(String eventType) { this.eventType = eventType; }

    public String getEntityType()  { return entityType; }
    public void   setEntityType(String entityType) { this.entityType = entityType; }

    public Long   getEntityId()    { return entityId; }
    public void   setEntityId(Long entityId) { this.entityId = entityId; }

    public String getDescription() { return description; }
    public void   setDescription(String description) { this.description = description; }

    public LocalDateTime getOccurredAt() { return occurredAt; }
    public void          setOccurredAt(LocalDateTime occurredAt) { this.occurredAt = occurredAt; }

    @Override
    public String toString() {
        return "LibraryEvent{" +
               "eventType='"  + eventType  + '\'' +
               ", entityType='" + entityType + '\'' +
               ", entityId="  + entityId   +
               ", description='" + description + '\'' +
               ", occurredAt=" + occurredAt +
               '}';
    }
}
