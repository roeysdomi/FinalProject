package crypto;



public class CryptoException extends Exception {
 
	
	/**
	 * class that handle exception
	 */
    public CryptoException() {
    }
 
    public CryptoException(String message, Throwable throwable) {
        super(message, throwable);
    }
}