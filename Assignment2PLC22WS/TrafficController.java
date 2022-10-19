/**
 * @author Nico Beranek
 * @id 11807137
 * @comment READ-ONLY FILE
 */

public interface TrafficController {
    void enterRight(Vehicle v); // only returns if bridge is empty; blocks otherwise
    void enterLeft(Vehicle v);  // only returns if bridge is empty; blocks otherwise
    void leaveLeft(Vehicle v);  // must be called after enterRight
    void leaveRight(Vehicle v); // must be called after enterLeft

}
