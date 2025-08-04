package com.AI.MyAIProject.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/openai")
@CrossOrigin("*")
public class OpenAIController {
	
	//private OpenAiChatModel chatModel;
	private ChatClient chatModel;
	
	
	
	public OpenAIController(OpenAiChatModel chatModel) {
		this.chatModel = ChatClient.create(chatModel);
	}



	@GetMapping("/{message}")
	public ResponseEntity<String> getAnswer(@PathVariable String message) {
		
		String response=chatModel.prompt(message).call().content();
		
		return ResponseEntity.ok(response);
	}
}
