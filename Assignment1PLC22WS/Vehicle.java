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
            throw new IllegalArgumentException("Brand cannot be null or empty");
        }

        if(this.model == null || this.model.isEmpty()) {
            throw new IllegalArgumentException("Model cannot be null or empty");
        }

        if(this.buildYear > Calendar.getInstance().get(Calendar.YEAR)) {
            throw new IllegalArgumentException("Build year cannot be in the future");
        }

        if(this.price < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }

        if(this.uniqueVehicleIdentificationNumber < 0) {
            throw new IllegalArgumentException("Unique vehicle identification number cannot be negative");
        }

    }

    public int getUniqueVehicleIdentificationNumber(){
        return uniqueVehicleIdentificationNumber;
    }

    public int getAge(){
        return Calendar.getInstance().get(Calendar.YEAR) - this.buildYear;
    }

    public double getPrice(){
        return this.price-getDiscount();
    }

    public abstract double getDiscount();


}