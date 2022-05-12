package com.abc.person.resource;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Response Resource
 * 
 ********************************************************************************************************
 *  ###   Date         Author    IT No.        Description
 *-------------------------------------------------------------------------------------------------------
 *    1   25-03-2022   MiyuruW   IT19020990     Created
 *    
 ********************************************************************************************************
 */

@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class SuccessAndErrorDetailsResource {
	@JsonProperty("messages")
	private String messages;
	
	@JsonProperty("details")
	private String details;
	
	@JsonProperty("code")
	private String code;
	
	@JsonProperty("value")
	private String value;
	
	@JsonProperty("response")
	private Object response;

	public String getMessages() {
		return messages;
	}

	public void setMessages(String messages) {
		this.messages = messages;
	}
	
	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	public Object getResponse() {
		return response;
	}

	public void setResponse(Object response) {
		this.response = response;
	}

	public SuccessAndErrorDetailsResource() {
		super();
	}
	
	public SuccessAndErrorDetailsResource(String messages) {
		super();
		this.messages = messages;
	}

	public SuccessAndErrorDetailsResource(String messages, String value) {
		super();
		this.messages = messages;
		this.value = value;
	}

	public SuccessAndErrorDetailsResource(String messages, Object response) {
		super();
		this.messages = messages;
		this.response = response;
	}

}
