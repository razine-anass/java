package org.sid.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class ElecException {
	
	public static class InvalidJwtException extends BaseException
    {
		private static final long serialVersionUID = -7922458048355109570L;

		public InvalidJwtException(String msg) {
            super(msg);
        }
        
        public InvalidJwtException(String msg, Throwable cause) {
            super(msg,cause);
        }
    }
	
    public static class FileStorageException extends BaseException
    {
		private static final long serialVersionUID = -7922458048355109570L;

		public FileStorageException(String msg) {
            super(msg);
        }
        
        public FileStorageException(String msg, Throwable cause) {
            super(msg,cause);
        }
    }
 
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public static class FileNotFoundException extends BaseException
    {
		private static final long serialVersionUID = -1664764571290098309L;

		public FileNotFoundException(String msg) {
            super(msg);
        }
        
        public FileNotFoundException(String msg, Throwable cause) {
        	 super(msg,cause);
        }
    }

}
