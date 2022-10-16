import java.io.InputStream;
import java.util.function.Consumer;
import java.util.function.Function;

public class VehicleCLI {
	private VehicleManagement vehicleManagement;

	public VehicleCLI(String filename) {
		vehicleManagement = new VehicleManagement(new SerializedVehicleDAO(filename));
	}

	public VehicleManagement getVehicleManagement() {
		return vehicleManagement;
	}

	public static void main(String[] args) {
		VehicleCLI vehicleCLI = new VehicleCLI(args[0]);
		switch (args[1]) {
			case "show": {
				if(args[2].isEmpty()){
					vehicleCLI.printShow();
				}
				vehicleCLI.printVehicle(args[2]);
			}
			case "add": {
				
			}
			case "del": {

			}
			case "count": {

			}
			case "meanprice": {

			}
			case "oldest": {

			}

			}
	}

	public void printShow(){
		vehicleManagement.getVehicles().forEach(vehicle -> printVehicle(String.valueOf(vehicle.getUniqueVehicleIdentificationNumber())));
	}

	public void printVehicle(String id) {
		Vehicle vehicleToPrint = vehicleManagement.getVehicle(Integer.parseInt(id));
		System.out.println("Type:       " + vehicleToPrint.getClass().getName());
		System.out.println("Id:         " + vehicleToPrint.getUniqueVehicleIdentificationNumber());
		System.out.println("Brand:      " + vehicleToPrint.getBrand());
		System.out.println("Model:      " + vehicleToPrint.getModel());
		System.out.println("Year:       " + vehicleToPrint.getBuildYear());
		if(vehicleToPrint instanceof Car){
			System.out.println("Inspection: " + ((Car) vehicleToPrint).getLastInspectionYear());
		}
		System.out.println("Base price: " + vehicleToPrint.getPrice());
		System.out.println("Price:      " + (vehicleToPrint.getPrice()- vehicleToPrint.getDiscount()));
		System.out.println();
	}


}



