package com.gyjian.restemplatenative.service;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gyjian.restemplatenative.entity.GptResultDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aot.hint.annotation.RegisterReflectionForBinding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Service
@Slf4j
@RegisterReflectionForBinding(GptResultDto.class)
public class TransApiService {
    @Data
    @AllArgsConstructor
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
    class Message {
        String role;
        String content;
    }

    // JSON 结构类
    @Data
    @AllArgsConstructor
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
    class JsonStructure {
        private String model;
        private Message[] messages;
        private Integer max_tokens;
        private Float temperature;
        private String request_id;
    }

    private static final String TRANS_API_HOST = "https://973427ac-bc31-4e73-b10a-d71b16fb2a53.mock.pstmn.io/mock";
    @Autowired
    RestTemplate restTemplate;

    public GptResultDto getTransResult(String query, String from, String to) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        // 构建JSON结构
        Integer max_tokens = 4095;
        Float temperature = 0.7f;

        // 创建消息对象
        Message message1 = new Message("system", "你是个超级厉害的翻译官");
        Message message2 = new Message("user", "翻译以下内容：abcde");

        // 创建请求JSON对象
        JsonStructure jsonStructure = new JsonStructure("glm-4-plus", new Message[]{message1, message2}, max_tokens, temperature, UUID.randomUUID().toString());
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json"); // Set the content type as JSON

        String requestBody = mapper.writeValueAsString(jsonStructure);
        log.info("requestBody: " + requestBody);

        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<GptResultDto> response = restTemplate.exchange(
                TRANS_API_HOST,
                HttpMethod.POST,
                entity,
                GptResultDto.class
        );

        return response.getBody();
    }

}
