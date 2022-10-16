import java.util.Calendar;

public class Car extends Vehicle {

    public static final double MAXIMUM_DISCOUNT_VALUE = 15.00;
    private final int lastInspectionYear;

    public Car(String brand, String model, int buildYear, double price, int uniqueVehicleIdentificationNumber, int lastInspectionYear) {
        super(brand, model, buildYear, price, uniqueVehicleIdentificationNumber);
        this.lastInspectionYear = lastInspectionYear;

        if(lastInspectionYear > Calendar.getInstance().get(Calendar.YEAR)){
            throw new IllegalArgumentException("Error: Inspection year invalid.");
        }
    }

    @Override
    public double getDiscount() {
        double calculatedDiscount = this.getAge() * 0.05 + this.getAge()-this.lastInspectionYear * 0.02;
        return Math.min(calculatedDiscount, MAXIMUM_DISCOUNT_VALUE);
    }

    public int getLastInspectionYear() {
        return lastInspectionYear;
    }
}