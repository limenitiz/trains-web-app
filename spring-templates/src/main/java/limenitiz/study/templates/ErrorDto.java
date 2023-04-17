package limenitiz.study.templates;

import lombok.Value;

import java.time.LocalDateTime;

@Value
public class ErrorDto {
    Integer code;
    String message;
    String details;
    LocalDateTime datetime = LocalDateTime.now();
}
