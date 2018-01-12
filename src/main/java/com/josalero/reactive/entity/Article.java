package com.josalero.reactive.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonCreator;

import lombok.Data;
import lombok.ToString;

@Document
@Data
@ToString
public class Article {
    @Id 
    private String id;
    private final String name;
    private final String text;
    
    public Article (final String name, final String text) {
    		this.name = name;
    		this.text = text;
    }
    
    @JsonCreator
    public Article (final String id, final String name, final String text) {
		this.name = name;
		this.text = text;
		this.id = id;
    }
    
    public String getId() {
		return id;
	}
    
    public String getText() {
		return text;
	}
    
    public String getName() {
		return name;
	}
}

