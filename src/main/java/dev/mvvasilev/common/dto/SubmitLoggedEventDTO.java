package dev.mvvasilev.common.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import dev.mvvasilev.common.enums.EventType;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;

public class SubmitLoggedEventDTO<T> implements Serializable {

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

    public SubmitLoggedEventDTO() {
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
        SubmitLoggedEventDTO that = (SubmitLoggedEventDTO) o;
        return version == that.version &&
                eventType == that.eventType &&
                Objects.equals(source, that.source) &&
                Objects.equals(submittedAt, that.submittedAt) &&
                Objects.equals(data, that.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventType, source, submittedAt, version, data);
    }

    @Override
    public String toString() {
        return "SubmitLoggedEventDTO{" +
                "eventType=" + eventType +
                ", source='" + source + '\'' +
                ", submittedAt=" + submittedAt +
                ", version=" + version +
                ", data=" + data +
                '}';
    }
}
