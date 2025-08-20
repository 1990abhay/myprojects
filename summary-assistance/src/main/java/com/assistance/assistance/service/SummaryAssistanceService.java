package com.assistance.assistance.service;

import java.security.PrivateKey;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitterReturnValueHandler;

import com.assistance.assistance.model.GeminiResponse;
import com.assistance.assistance.model.SummaryAssistanceModel;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class SummaryAssistanceService {
	

	@Value("${gemini.api.url}")
	public String geminiApiUrl;
	@Value("${gemini.api.key}")
	private String geminiApikey;
	
	private final WebClient webClient;
	private final ObjectMapper objectMapper;
	
	public SummaryAssistanceService(WebClient.Builder webClient,ObjectMapper objectMapper) {
		this.webClient = webClient.build();
		this.objectMapper = objectMapper;
	}

	public String processResult(SummaryAssistanceModel summaryAssistanceModel) {
		
		// build prompt
		String prompt=buildPrompt(summaryAssistanceModel);
		//query the AI model API
		Map<String,Object> requestBpdy=Map.of("contents",new Object[] {Map.of("parts",new Object[] {Map.of("text",prompt)})});
		
		String response=webClient.post().uri(geminiApiUrl+geminiApikey).bodyValue(requestBpdy).retrieve().bodyToMono(String.class).block();
		//parse and return the response
		return extractTextFromResponse(response);
	}
	
	private String extractTextFromResponse(String response) {
		try {
			GeminiResponse geminiResponse=objectMapper.readValue(response, GeminiResponse.class);
			if(geminiResponse.getCandidates()!=null && !geminiResponse.getCandidates().isEmpty()) {
				GeminiResponse.Candidate fisrtCandidate=geminiResponse.getCandidates().get(0);	
				if(fisrtCandidate.getContent() !=null && fisrtCandidate.getContent().getParts() !=null && !fisrtCandidate.getContent().getParts().isEmpty()) {
					return fisrtCandidate.getContent().getParts().get(0).getText();
							
				}
			}
			
		} catch (Exception e) {
			return "Error parsing : "+e.getMessage();
		}
		return "No content";
	}

	private String buildPrompt(SummaryAssistanceModel summaryAssistanceModel) {
		StringBuilder prompt=new StringBuilder();
		
		switch (summaryAssistanceModel.getOperation()) {
		case "summarize": 		
			prompt.append("Provide a clear and concise summary of the following text \n\n");	
			break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + summaryAssistanceModel);
			
		}
		prompt.append(summaryAssistanceModel.getContent());
		return prompt.toString();
	}

}
