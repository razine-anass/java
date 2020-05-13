package org.sid.exception;

//Java n'oblige à déclarer les exceptions dans l'en-tête de la méthode que pour les exceptions dites contrôlées (checked). 
//Les exceptions non contrôlées (unchecked) peuvent être capturées mais n'ont pas à être déclarées. Les exceptions et erreurs 
//qui héritent de RunTimeException et de Error sont non contrôlées. Toutes les autres exceptions sont contrôlées
public abstract class BaseException extends RuntimeException{
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BaseException(String msg)
	 {
		 super(msg);
	 }
	 
	 public BaseException(String msg, Throwable cause) {
         super(msg,cause);
     }
}