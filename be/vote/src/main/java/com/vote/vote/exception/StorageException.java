package com.vote.vote.exception;

public class StorageException extends RuntimeException{
	private static final long serialVersiionUID =1L;
	
	public StorageException(String message) {   
		super(message);  
	}
	public StorageException(String message, Throwable cause) {
		super(message,cause);
	}
}
