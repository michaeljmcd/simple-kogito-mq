package org.acme;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class NotificationMessage {
    private String eventType;
    private LocalDate occurredAt;
}
