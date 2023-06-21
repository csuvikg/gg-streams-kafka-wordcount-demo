package com.infinitelambda.wordcountv2;

import org.springframework.kafka.core.KafkaTemplate;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@AllArgsConstructor
@Service
public class WordCountService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void submitMessage(String message) {
        kafkaTemplate.send("input-topic", message)
            .whenComplete((result, ex) -> {
                if (ex == null) {
                    log.info("Message sent to topic: {}", message);
                } else {
                    log.error("Failed to send message", ex);
                }
            });
    }
}
