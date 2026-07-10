package com.focusquest.service;

import com.focusquest.dto.ExternalQuoteDTO;
import com.focusquest.dto.response.MotivationQuoteResponseDTO;
import com.focusquest.exception.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class MotivationService {

    private final RestTemplate restTemplate;

    public MotivationQuoteResponseDTO getDailyMotivationQuote() {


        ExternalQuoteDTO[] externalQuote = restTemplate.getForObject("https://zenquotes.io/api/random", ExternalQuoteDTO[].class);

        if (externalQuote.length == 0) {
            throw new ResourceNotFoundException("No motivation quote available right now");
        }

        ExternalQuoteDTO firstQuote = externalQuote[0];

        MotivationQuoteResponseDTO response = new MotivationQuoteResponseDTO(firstQuote.getQ(), firstQuote.getA());

        return response;
    }

}
