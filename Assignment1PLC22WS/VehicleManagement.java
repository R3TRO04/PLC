import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class VehicleManagement{
    private final VehicleDAO vehicleDAO;
    private final DecimalFormat df = new DecimalFormat("0.00");

    public VehicleManagement(VehicleDAO vehicleDAO){
        this.vehicleDAO = vehicleDAO;
    }

    public List<Vehicle> getVehicles(){
        return vehicleDAO.getVehicleList();
    }

    public Vehicle getVehicle(int id){
        return vehicleDAO.getVehicle(id);
    }

    public boolean isVehicleInList(int id){
        return vehicleDAO.getVehicleList().stream().anyMatch(vehicle -> vehicle.getUniqueVehicleIdentificationNumber() == id);
    }

    public void saveVehicle(Vehicle vehicle){
        vehicleDAO.saveVehicle(vehicle);
    }

    public void deleteVehicle(int id){
        vehicleDAO.deleteVehicle(id);
    }

    public int getVehicleCount(){
        return vehicleDAO.getVehicleList().size();
    }

    public int getCarCount(){
        return (int) vehicleDAO.getVehicleList().stream()
                .filter(vehicle -> vehicle instanceof Car)
                .count();
    }

    public int getTruckCount(){
        return (int) vehicleDAO.getVehicleList().stream()
                .filter(vehicle -> vehicle instanceof Truck)
                .count();
    }

    public double getMeanPriceOfAllVehicles(){
        return Double.parseDouble(
                df.format(vehicleDAO.getVehicleList().stream()
                .mapToDouble(Vehicle::getReducedPrice)
                .sum() / getVehicleCount()));

    }

    public List<Integer> getOldestVehiclesIds(){
        int oldestYear =  vehicleDAO.getVehicleList().stream()
                .min(Vehicle::compareToAge)
                .get()
                .getBuildYear();

        List<Integer> idsOfOldestVehicles = new ArrayList<>();
        vehicleDAO.getVehicleList().stream()
                .filter(vehicle -> vehicle.getBuildYear() == oldestYear)
                .forEach(vehicle -> idsOfOldestVehicles.add(vehicle.getUniqueVehicleIdentificationNumber()));

        return idsOfOldestVehicles;
    }

}