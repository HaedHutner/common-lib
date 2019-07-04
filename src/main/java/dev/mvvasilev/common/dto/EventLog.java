package dev.mvvasilev.common.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import dev.mvvasilev.common.enums.EventType;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class EventLog<T> implements Serializable {

    @NotNull
    private EventType eventType;

    @NotEmpty
    private String source;

    @PastOrPresent
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime submittedAt;

    private int version;

    private T data;

    public EventLog() {
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public LocalDateTime getSubmittedAt() {
        return submittedAt;
    }

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    public void setSubmittedAt(LocalDateTime submittedAt) {
        this.submittedAt = submittedAt;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EventLog<?> eventLog = (EventLog<?>) o;
        return version == eventLog.version &&
                eventType == eventLog.eventType &&
                Objects.equals(source, eventLog.source) &&
                Objects.equals(submittedAt, eventLog.submittedAt) &&
                Objects.equals(data, eventLog.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventType, source, submittedAt, version, data);
    }

    @Override
    public String toString() {
        return "EventLog{" +
                "eventType=" + eventType +
                ", source='" + source + '\'' +
                ", submittedAt=" + submittedAt +
                ", version=" + version +
                ", data=" + data +
                '}';
    }
}
