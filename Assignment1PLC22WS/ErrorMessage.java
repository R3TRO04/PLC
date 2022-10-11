public enum ErrorMessage {
    parameterError("Error: Invalid parameter."),
    yearInvalid("Error: Year built invalid."),
    priceInvalid("Error: Base price invalid."),
    inspectionInvalid("Error: Inspection year invalid."),
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
