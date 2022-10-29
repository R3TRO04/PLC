import java.util.ArrayList;
import java.util.Collection;

public class TrafficControllerSimple implements TrafficController {
	private final TrafficRegistrar registrar;
	private final Collection<Vehicle> bridge = new ArrayList<>();

	
	public TrafficControllerSimple(TrafficRegistrar r) {
		this.registrar = r;
	}
	
	public synchronized void enterRight(Vehicle v) {
		while(!bridge.isEmpty()){
			try {
				wait();
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
		bridge.add(v);
		registrar.registerRight(v);
	}
	
	public synchronized void enterLeft(Vehicle v) {
		while(!bridge.isEmpty()){
			try {
				wait();
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
		bridge.add(v);
		registrar.registerLeft(v);   
	}
	
	public synchronized void leaveLeft(Vehicle v) {
		if(bridge.isEmpty()){
			throw new IllegalArgumentException("Bridge is empty");
		} else if (v.getId() != bridge.stream().findFirst().get().getId()) {
			throw new IllegalArgumentException("Car is not currently on the Bridge");
		}else {
			bridge.remove(v);
		}
		registrar.deregisterLeft(v);
		notifyAll();
	}
	
	public synchronized void leaveRight(Vehicle v) {
		if(bridge.isEmpty()){
			throw new IllegalArgumentException("Bridge is empty");
		} else if (v.getId() != bridge.stream().findFirst().get().getId()) {
			throw new IllegalArgumentException("Car is not currently on the Bridge");
		}else {
			bridge.remove(v);
		}
		registrar.deregisterRight(v);
		notifyAll();
	}
}
