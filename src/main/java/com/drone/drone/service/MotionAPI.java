package com.drone.drone.service;

import com.drone.drone.domain.Drone;
/**
 * @author binayrai
 */
public interface MotionAPI {

    void forward(Drone drone);
    void back(Drone drone);
    void left(Drone drone);
    void right(Drone drone);
    void up(Drone drone);
    void down(Drone drone);
}
