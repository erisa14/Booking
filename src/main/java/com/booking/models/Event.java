package com.booking.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Event name is required!")
    @Size(min = 3, max = 50, message = "Event name must be between 3 and 50 characters!")
    private String eventName;

    @NotNull(message = "Event date is required!")
    @Future(message = "Event date must be in the future!")
    private LocalDateTime eventDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotEmpty(message = "Event name is required!") @Size(min = 3, max = 50, message = "Event name must be between 3 and 50 characters") String getEventName() {
        return eventName;
    }

    public void setEventName(@NotEmpty(message = "Event name is required!") @Size(min = 3, max = 50, message = "Event name must be between 3 and 50 characters") String eventName) {
        this.eventName = eventName;
    }

    public @Future(message = "Event date must be in the future") LocalDateTime getEventDate() {
        return eventDate;
    }

    public void setEventDate(@Future(message = "Event date must be in the future") LocalDateTime eventDate) {
        this.eventDate = eventDate;
    }

    public Event(Long id, String eventName, LocalDateTime eventDate) {
        this.id = id;
        this.eventName = eventName;
        this.eventDate = eventDate;
    }

    public Event() {
    }
}