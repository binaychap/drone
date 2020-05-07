package com.drone.drone.unittest.service;


import com.drone.drone.domain.Drone;
import com.drone.drone.domain.Engine;
import com.drone.drone.domain.Gyroscope;
import com.drone.drone.domain.OrientationSensor;
import com.drone.drone.service.DroneService;
import com.drone.drone.service.MotionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.Arrays;
import java.util.List;

/**
 * @author binayrai
 */

@TestMethodOrder(MethodOrderer.Alphanumeric.class)
public class DroneServiceTest {

    public static final int POSITIVE_SENSOR_INDICATOR = 1;
    public static final int NEGATIVE_SENSOR_INDICATOR = -1;
    private DroneService droneService = new DroneService(new MotionService());

    @Test
    public void takeOffTest() {
        Drone drone = getDrone();
        drone.getGyroscope().setAngularVelInZ(10.0);
        droneService.takeOff(drone);
        Assertions.assertEquals(Drone.STATUS.MOVING, drone.getStatus());
    }

    @Test
    public void stabilizeTest() {
        Drone drone = getDrone();
        drone.getGyroscope().setAngularVelInZ(10.0);
        droneService.stabilize(drone);
        Assertions.assertEquals(Drone.STATUS.HOVERING, drone.getStatus());
    }

    @Test
    public void landTest() {
        Drone drone = getDrone();
        drone.getGyroscope().setAngularVelInZ(-1.0);
        droneService.land(drone);
    }

    @Test
    public void statusTest() {
        Drone drone = getDrone();
        droneService.status(drone);
        Assertions.assertNotNull(drone.getSensor());
    }


    @Test
    public void forwardMoveTest() {
        Drone drone = getDrone();
        drone.setSensor(new OrientationSensor());
        drone.getSensor().setPitch(POSITIVE_SENSOR_INDICATOR);
        droneService.forwardBackMove(drone);
        Assertions.assertNotNull(drone.getGyroscope());
    }

    @Test
    public void backMoveTest() {
        Drone drone = getDrone();
        drone.setSensor(new OrientationSensor());
        drone.getSensor().setPitch(NEGATIVE_SENSOR_INDICATOR);
        droneService.forwardBackMove(drone);
        Assertions.assertNotNull(drone.getGyroscope());
    }

    @Test
    public void rightTest() {
        Drone drone = getDrone();
        drone.setSensor(new OrientationSensor());
        drone.getSensor().setRoll(POSITIVE_SENSOR_INDICATOR);
        droneService.leftRightMove(drone);
        Assertions.assertNotNull(drone.getGyroscope());
    }

    @Test
    public void leftTest() {
        Drone drone = getDrone();
        drone.setSensor(new OrientationSensor());
        drone.getSensor().setRoll(NEGATIVE_SENSOR_INDICATOR);
        droneService.leftRightMove(drone);
        Assertions.assertNotNull(drone.getGyroscope());
    }

    private Drone getDrone() {
        Drone drone = new Drone();
        drone.setEngine(getAllEngines());
        drone.setStatus(Drone.STATUS.OFF);
        drone.setGyroscope(new Gyroscope());
        return drone;
    }

    private List<Engine> getAllEngines() {
        return Arrays.asList(
                new Engine(1, Engine.STATUS.ON),
                new Engine(2, Engine.STATUS.ON),
                new Engine(3, Engine.STATUS.ON),
                new Engine(4, Engine.STATUS.ON)
        );
    }
}
