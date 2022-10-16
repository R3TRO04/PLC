public class Truck extends Vehicle {

    public static final double MAXIMUM_DISCOUNT_VALUE = 0.20;

    public Truck(String brand, String model, int buildYear, double price, int uniqueVehicleIdentificationNumber) {
        super(brand, model, buildYear, price, uniqueVehicleIdentificationNumber);
    }

    @Override
    public double getDiscount() {
        double calculatedDiscount = this.getAge() * 0.05;
        return Math.min(calculatedDiscount, MAXIMUM_DISCOUNT_VALUE);
    }

}