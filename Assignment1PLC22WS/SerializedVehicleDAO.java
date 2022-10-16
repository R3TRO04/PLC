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
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectOutputStream = new ObjectInputStream(fileInputStream);
            return (List<Vehicle>) objectOutputStream.readObject();
        } catch (Exception e) {
            System.err.println(ErrorMessage.deserializationError.getMessage() + e.getMessage());
            System.exit(1);
        }
        return null;
    }

    static void writeVehicleToFile() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(vehicleList);
        } catch (Exception e) {
            System.err.println(ErrorMessage.serializationError.getMessage() + e.getMessage());
            System.exit(1);
        }
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
            throw new IllegalArgumentException(
                    ErrorMessage.vehicleAlreadyExists.getMessage() + "(" + vehicle.getUniqueVehicleIdentificationNumber() + ")"
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
                    ErrorMessage.vehicleNotFound.getMessage() + "(" + id + ")"
            );
        }else {
            vehicleList.removeIf(v -> v.getUniqueVehicleIdentificationNumber() == id);
            writeVehicleToFile();
        }
    }
}
