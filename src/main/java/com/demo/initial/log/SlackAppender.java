package com.demo.initial.log;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class SlackAppender extends AppenderBase<ILoggingEvent> {

    private static final String WEBHOOK_URL = System.getenv("SLACK_WEBHOOK_URL");
    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    protected void append(ILoggingEvent eventObject) {
        try {
            Map<String, Object> body = createSlackErrorBody(eventObject);
            restTemplate.postForEntity(WEBHOOK_URL, body, String.class);
        } catch (Exception e) {
            // Slack 전송 실패 시 로그 기록
            addError("Slack에 메시지를 전송하는 데 실패했습니다.", e);
        }
    }

    private Map<String, Object> createSlackErrorBody(ILoggingEvent eventObject) {
        String message = createMessage(eventObject);
        return Map.of(
                "attachments", List.of(
                        Map.of(
                                "fallback", "요청을 실패했어요 :cry:",
                                "color", "#2eb886",
                                "pretext", "에러가 발생했어요 확인해주세요 :cry:",
                                "author_name", "car",
                                "text", message,
                                "fields", List.of(
                                        Map.of("title", "로그 레벨", "value", eventObject.getLevel().levelStr, "short", false)
                                )
                        )
                ),
                "ts", String.valueOf(eventObject.getTimeStamp() / 1000) // 초 단위 Unix timestamp
        );
    }

    private String createMessage(ILoggingEvent eventObject) {
        String baseMessage = "에러가 발생했습니다.\n";
        String pattern = baseMessage + "```%s %s %s [%s] - %s```";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        return String.format(pattern,
                simpleDateFormat.format(new Date(eventObject.getTimeStamp())),
                eventObject.getLevel(),
                eventObject.getThreadName(),
                eventObject.getLoggerName(),
                eventObject.getFormattedMessage());
    }
}
