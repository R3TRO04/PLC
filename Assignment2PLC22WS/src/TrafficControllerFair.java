public class TrafficControllerFair implements TrafficController {
	private TrafficRegistrar registrar;

	public TrafficControllerFair(TrafficRegistrar r) {
		this.registrar = r;
	}
	
	public void enterRight(Vehicle v) { 
		registrar.registerRight(v);       
	}
	
	public void enterLeft(Vehicle v) {
		registrar.registerLeft(v);   
	}
	
	public void leaveLeft(Vehicle v) {  
		registrar.deregisterLeft(v);      
	}
	
	public void leaveRight(Vehicle v) { 
		registrar.deregisterRight(v); 
	}
}
