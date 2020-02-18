package com.vote.vote.exception;

public class StorageFileNotFoundException extends StorageException{

	private static final long serialVersiionUID =1L;
	
	public StorageFileNotFoundException(String message) {   
		super(message);  
	}
	public StorageFileNotFoundException(String message, Throwable cause) {
		super(message,cause);
	}
	
}
