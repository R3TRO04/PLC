import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.Calendar;

public abstract class Vehicle implements Serializable {

    private final String brand;
    private final String model;
    private final int buildYear;
    private final double price;
    private final int uniqueVehicleIdentificationNumber;
    private final DecimalFormat df = new DecimalFormat("0.00");

    public Vehicle(String brand, String model, int buildYear, double price, int uniqueVehicleIdentificationNumber) {
        this.brand = brand;
        this.model = model;
        this.buildYear = buildYear;
        this.price = price;
        this.uniqueVehicleIdentificationNumber = uniqueVehicleIdentificationNumber;

        if(this.brand == null || this.brand.isEmpty()) {
            throw new IllegalArgumentException("Invalid parameter.");
        }

        if(this.model == null || this.model.isEmpty()) {
            throw new IllegalArgumentException("Invalid parameter.");
        }

        if(this.buildYear > Calendar.getInstance().get(Calendar.YEAR)) {
            throw new IllegalArgumentException("Year built invalid.");
        }

        if(this.price < 0) {
            throw new IllegalArgumentException("Base price invalid.");
        }

        if(this.uniqueVehicleIdentificationNumber < 0) {
            throw new IllegalArgumentException("Invalid parameter.");
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
        return this.getBuildYear() - vehicle.getBuildYear();
    }

    public DecimalFormat getDecimalFormat() {
        return df;
    }
}