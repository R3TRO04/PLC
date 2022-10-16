import java.util.List;


public class VehicleCLI {
	private VehicleManagement vehicleManagement;

	public VehicleCLI(String filename) {
		vehicleManagement = new VehicleManagement(new SerializedVehicleDAO(filename));
	}

	public static void main(String[] args) {
		VehicleCLI vehicleCLI = new VehicleCLI(args[0]);
		int argsCounter = args.length;
		switch (args[1]) {
			case "show": {
				if(argsCounter <= 2){
					vehicleCLI.printShow();
				}else {
					vehicleCLI.printVehicle(args[2]);
				}
				return;
			}
			case "add": {

				return;
			}
			case "del": {
				if(argsCounter <= 2){
					throw new IllegalArgumentException("Error: Invalid parameter.");
				}
				vehicleCLI.vehicleManagement.deleteVehicle(Integer.parseInt(args[2]));
				return;
			}
			case "count": {
				if(args[2].isEmpty()){
					vehicleCLI.printCount();
				}else if(args[2].equals("car")){
					vehicleCLI.printCountCar();
				} else if (args[2].equals("truck")) {
					vehicleCLI.printCountTruck();
				}else throw new IllegalArgumentException("Error: Invalid parameter.");
				return;
			}
			case "meanprice": {
				System.out.println(vehicleCLI.vehicleManagement.getMeanPriceOfAllVehicles());
				return;
			}
			case "oldest": {
				vehicleCLI.printOldest();
			}

		}
	}

	public void printCount(){
		System.out.println(vehicleManagement.getVehicleCount());
	}

	public void printCountCar(){
		System.out.println(vehicleManagement.getCarCount());
	}

	public void printCountTruck(){
		System.out.println(vehicleManagement.getTruckCount());
	}

	public void printOldest(){
		List<Integer> ids = vehicleManagement.getOldestVehiclesIds();
		for (int id : ids){
			System.out.println("Id: " + id);
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
		System.out.println("Base price: " + vehicleToPrint.getDecimalFormat().format(vehicleToPrint.getPrice()));
		System.out.println("Price:      " + vehicleToPrint.getDecimalFormat().format((vehicleToPrint.getPrice() - vehicleToPrint.getDiscount())));
		System.out.println();
	}


}



