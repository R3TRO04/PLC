public enum ErrorMessage {
    parameterError("Error: Invalid parameter."),
    buildInvalid("Error: Year built invalid."),
    priceInvalid("Error: Base price invalid."),
    yearInvalid("Error: Inspection year invalid."),
    vehicleNotFound("Error: Vehicle not found. "),
    vehicleAlreadyExists("Error: Vehicle already exists. ");

    private String message;
    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
