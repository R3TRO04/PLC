import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SerializedVehicleDAO implements VehicleDAO, Serializable {
    private static List<Vehicle> vehicleList = new ArrayList<>();
    private static File file;

    public SerializedVehicleDAO(String fileName){
        file = new File(fileName);
        vehicleList = readVehicleFromFile(file);

    }

    static List<Vehicle> readVehicleFromFile (File file) {
        try {
            if(file.exists()) {
                FileInputStream fileInputStream = new FileInputStream(file);
                ObjectInputStream objectOutputStream = new ObjectInputStream(fileInputStream);
                return (List<Vehicle>) objectOutputStream.readObject();
            }
        } catch (Exception e) {
            System.err.println("Error during deserialization: " + e.getMessage());
            System.exit(1);
        }
        return new ArrayList<>();
    }

    static void writeVehicleToFile() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(vehicleList);
        } catch (Exception e) {
            System.err.println("Error during serialization: " + e.getMessage());
            System.exit(1);
        }
    }

    @Override
    public List<Vehicle> getVehicleList() {
        return vehicleList;
    }

    @Override
    public Vehicle getVehicle(int id) {
        return vehicleList.stream()
                .filter(vehicle -> vehicle.getUniqueVehicleIdentificationNumber() == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Error: Vehicle not found. " + "(" + id + ")"));
    }

    @Override
    public void saveVehicle(Vehicle vehicle) {
        if(vehicleList.stream()
                .anyMatch(v -> v.getUniqueVehicleIdentificationNumber() == vehicle.getUniqueVehicleIdentificationNumber())) {
            throw new IllegalArgumentException(
                    "Error: Vehicle already exists. " + "(" + vehicle.getUniqueVehicleIdentificationNumber() + ")"
            );
        }else {
            vehicleList.add(vehicle);
            writeVehicleToFile();
        }
    }

    @Override
    public void deleteVehicle(int id) {
        if(vehicleList.stream()
                .noneMatch(v -> v.getUniqueVehicleIdentificationNumber() == id)) {
            throw new IllegalArgumentException(
                    "Error: Vehicle not found. " + "(" + id + ")"
            );
        }else {
            vehicleList.removeIf(v -> v.getUniqueVehicleIdentificationNumber() == id);
            writeVehicleToFile();
        }
    }
}
