import java.util.Calendar;

public abstract class Vehicle {

    private final String brand;
    private final String model;
    private final int buildYear;
    private final double price;
    private final int uniqueVehicleIdentificationNumber;

    public Vehicle(String brand, String model, int buildYear, double price, int uniqueVehicleIdentificationNumber) {
        this.brand = brand;
        this.model = model;
        this.buildYear = buildYear;
        this.price = price;
        this.uniqueVehicleIdentificationNumber = uniqueVehicleIdentificationNumber;

        if(this.brand == null || this.brand.isEmpty()) {
            throw new IllegalArgumentException(ErrorMessage.parameterError.getMessage());
        }

        if(this.model == null || this.model.isEmpty()) {
            throw new IllegalArgumentException(ErrorMessage.parameterError.getMessage());
        }

        if(this.buildYear > Calendar.getInstance().get(Calendar.YEAR)) {
            throw new IllegalArgumentException(ErrorMessage.yearInvalid.getMessage());
        }

        if(this.price < 0) {
            throw new IllegalArgumentException(ErrorMessage.priceInvalid.getMessage());
        }

        if(this.uniqueVehicleIdentificationNumber < 0) {
            throw new IllegalArgumentException(ErrorMessage.parameterError.getMessage());
        }

    }

    public int getUniqueVehicleIdentificationNumber(){
        return uniqueVehicleIdentificationNumber;
    }

    public int getAge(){
        return Calendar.getInstance().get(Calendar.YEAR) - this.buildYear;
    }

    public double getPrice(){
        return this.price;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public int getBuildYear() {
        return buildYear;
    }

    public abstract double getDiscount();

    public int compareToAge(Vehicle vehicle) {
        return this.getAge() - vehicle.getAge();
    }
}