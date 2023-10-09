package com.gyjian.restemplatenative.service;

import com.gyjian.restemplatenative.entity.GptResultDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aot.hint.annotation.RegisterReflectionForBinding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
@RegisterReflectionForBinding(GptResultDto.class)
public class TransApiService {
    private static final String TRANS_API_HOST = "https://973427ac-bc31-4e73-b10a-d71b16fb2a53.mock.pstmn.io/mock";
    @Autowired
    RestTemplate restTemplate;

    public GptResultDto getTransResult(String query, String from, String to) {
        ResponseEntity<GptResultDto> response = restTemplate.exchange(
                TRANS_API_HOST,
                HttpMethod.POST,
                null,
                GptResultDto.class
        );

        return response.getBody();
    }

}
