package co.com.valid.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Resource conflic Exception class
 * 
 * @author diego ibanez
 * @Since 1.0.0
 */
@ResponseStatus(value = HttpStatus.CONFLICT)
public class ResourceConflicException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public ResourceConflicException(String message) {
		super(message);
	}
}
