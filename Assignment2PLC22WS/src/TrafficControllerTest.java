import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.locks.ReentrantLock;


public class TrafficControllerTest implements TrafficController {
	private TrafficRegistrar registrar;
	private final ReentrantLock lockerLeft = new ReentrantLock();
	private final ReentrantLock lockerRight = new ReentrantLock();
	private final ReentrantLock locker = new ReentrantLock(false);

	public TrafficControllerTest(TrafficRegistrar r) {
		this.registrar = r;
	}

	public void enterRight(Vehicle v) {
		locker.;
		registrar.registerRight(v);
	}

	public void enterLeft(Vehicle v) {
		lockerLeft.lock();
		registrar.registerLeft(v);
	}

	public void leaveLeft(Vehicle v) {
		registrar.deregisterLeft(v);

	}

	public void leaveRight(Vehicle v) {
		registrar.deregisterRight(v);

	}

}
