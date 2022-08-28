package com.ugisoftware.blog.exceptions;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundExceptions extends RuntimeException {

	 public ResourceNotFoundExceptions() {
	        super();
	    }

	    public ResourceNotFoundExceptions(final String message, final Throwable cause) {
	        super(message, cause);
	    }

	    public ResourceNotFoundExceptions(final String message) {
	        super(message);
	    }

	    public ResourceNotFoundExceptions(final Throwable cause) {
	        super(cause);
	    }


	
}