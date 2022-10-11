import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SerializedVehicleDAO implements VehicleDAO, Serializable {
    private Collection<Vehicle> vehicleList;
    File file;

    public SerializedVehicleDAO(String fileName) {
        this.file = new File(fileName);
        this.vehicleList = new ArrayList<>();

    }

    @Override
    public List<Vehicle> getVehicleList() {
        return new ArrayList<>(vehicleList);
    }

    @Override
    public Vehicle getVehicle(int id) {
        return vehicleList.stream()
                .filter(vehicle -> vehicle.getUniqueVehicleIdentificationNumber() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public void saveVehicle(Vehicle vehicle) {
        if(vehicleList.stream()
                .anyMatch(v -> v.getUniqueVehicleIdentificationNumber() == vehicle.getUniqueVehicleIdentificationNumber())) {
            throw new IllegalArgumentException("Vehicle with this ID already exists");
        }else {
            vehicleList.add(vehicle);
        }
    }

    @Override
    public void deleteVehicle(int id) {
        vehicleList.remove(vehicleList.stream()
                    .filter(vehicle -> vehicle.getUniqueVehicleIdentificationNumber() == id)
                    .findAny()
                    .orElseThrow(() -> new IllegalArgumentException("Vehicle with this ID does not exist")));
    }
}
