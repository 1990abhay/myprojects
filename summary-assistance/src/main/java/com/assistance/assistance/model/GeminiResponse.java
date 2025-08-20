package com.assistance.assistance.model;

import java.net.ContentHandler;
import java.security.PrivateKey;
import java.util.List;

import org.springframework.context.index.CandidateComponentsIndex;
import org.springframework.context.support.StaticApplicationContext;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GeminiResponse {
	
	public List<Candidate> candidates;
	
	public List<Candidate> getCandidates() {
		return candidates;
	}

	public void setCandidates(List<Candidate> candidates) {
		this.candidates = candidates;
	}
	
	@Data
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class Candidate{
		public Content getContent() {
			return content;
		}

		public void setContent(Content content) {
			this.content = content;
		}

		public Content content;
	}
	@Data
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class Content{
		public List<Part> getParts() {
			return parts;
		}

		public void setParts(List<Part> parts) {
			this.parts = parts;
		}

		public List<Part> parts;
	}
	@Data
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class Part{
		public String getText() {
			return text;
		}

		public void setText(String text) {
			this.text = text;
		}

		public String text;
	}
}
