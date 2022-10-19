/**
 * @author Nico Beranek
 * @id 11807137
 * @comment READ-ONLY FILE
 */

public interface TrafficRegistrar {
    void registerLeft(Vehicle v);
    void registerRight(Vehicle v);
    void deregisterLeft(Vehicle v);
    void deregisterRight(Vehicle v);
}
