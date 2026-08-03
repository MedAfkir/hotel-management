package com.afkir.hotel.outbox;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MessageDeserializer {

    private final ObjectMapper objectMapper;

    public <T> T deserialize(byte[] payload, Class<T> type) {
        try {
            return objectMapper.readValue(payload, type);
        }
        catch (IOException ex) {
            throw new IllegalStateException("cannot deserialize message payload", ex);
        }
    }

}
