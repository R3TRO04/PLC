/**
 * @author Nico Beranek
 * @id 11807137
 */

public class TrafficControllerSimple implements TrafficController {
    private TrafficRegistrar registrar;

    public TrafficControllerSimple(TrafficRegistrar r) {
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