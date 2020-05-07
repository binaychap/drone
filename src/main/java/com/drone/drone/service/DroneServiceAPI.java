package com.drone.drone.service;

import com.drone.drone.domain.Drone;
/**
 * @author binayrai
 */
public interface DroneServiceAPI {
    void takeOff(Drone drone);
    void stabilize(Drone drone);
    void land(Drone drone);
    Drone status(Drone drone);
    void move(Drone drone);

}
