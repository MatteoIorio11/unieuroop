package unieuroop.model.sale;

public class NullSaleException extends NullPointerException {

    private static final long serialVersionUID = 3913737910147825420L;
    private static final String RULE = "The sale must not be null";
    private String errorMessage;

    /**
     * @return the Place where the exception where throw and the Rule message
     */
    public String getMessage() {
        return this.errorMessage;
    }

    /**
     * 
     * @param placeException : where the exception happened
     */
    public void setMessage(final String placeException) {
        this.errorMessage = placeException.concat(NullSaleException.RULE);
    }
}
