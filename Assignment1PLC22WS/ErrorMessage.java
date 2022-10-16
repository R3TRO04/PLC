public enum ErrorMessage {
    parameterError("Error: Invalid parameter."),
    yearInvalid("Error: Year built invalid."),
    priceInvalid("Error: Base price invalid."),
    inspectionInvalid("Error: Inspection year invalid."),
    vehicleNotFound("Error: Vehicle not found. "),
    vehicleAlreadyExists("Error: Vehicle already exists. "),
    serializationError("Error during serialization: "),
    deserializationError("Error during deserialization: ");

    private final String message;
    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
