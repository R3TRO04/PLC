import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.locks.ReentrantLock;

public class TrafficControllerFair implements TrafficController {
	private TrafficRegistrar registrar;
	private final Collection<Vehicle> bridge = new ArrayList<>();
	private final ReentrantLock locker = new ReentrantLock(true);

	public TrafficControllerFair(TrafficRegistrar r) {
		this.registrar = r;
	}
	
	public void enterRight(Vehicle v) {
		locker.lock();
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
	
	public void enterLeft(Vehicle v) {
		locker.lock();
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
	
	public void leaveLeft(Vehicle v) {
		if(bridge.isEmpty()){
			throw new IllegalArgumentException("Bridge is empty");
		} else if (v.getId() != bridge.stream().findFirst().get().getId()) {
			throw new IllegalArgumentException("Car is not currently on the Bridge");
		}else {
			bridge.remove(v);
		}
		registrar.deregisterLeft(v);
		locker.unlock();
	}
	
	public void leaveRight(Vehicle v) {
		if(bridge.isEmpty()){
			throw new IllegalArgumentException("Bridge is empty");
		} else if (v.getId() != bridge.stream().findFirst().get().getId()) {
			throw new IllegalArgumentException("Car is not currently on the Bridge");
		}else {
			bridge.remove(v);
		}
		registrar.deregisterRight(v);
		locker.unlock();
	}
}
