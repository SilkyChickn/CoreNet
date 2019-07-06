package network;

public class CoreNetException extends Exception {

    /**Creating new core net exception and setting its error message
     *
     * @param error Core net exception error message
     */
    public CoreNetException(String error){
        super(error);
    }

    /**Creating new core net exception and setting its error message and cause
     *
     * @param error Core net exception error message
     * @param cause Cause for this exception
     */
    public CoreNetException(String error, Throwable cause){
        super(error, cause);
    }
}
