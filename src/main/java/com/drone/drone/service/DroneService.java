package com.drone.drone.service;

import com.drone.drone.domain.Drone;
import com.drone.drone.domain.Engine;
import com.drone.drone.domain.OrientationSensor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Predicate;

/**
 * @author binayrai
 */
public class DroneService implements DroneServiceAPI {
    private static final Logger LOG = LoggerFactory.getLogger(MotionService.class);
    private final static double hoverVelocity = 1.0;
    private MotionAPI motion;

    public DroneService(MotionService motion) {
        this.motion = motion;

    }

    Predicate<Engine> engineOn = (engine) -> engine.getStatus().name()
            .equalsIgnoreCase(Engine.STATUS.ON.name());


    @Override
    public void takeOff(Drone drone) {
        if (null != drone && !drone.getEngine().isEmpty()) {
            drone.getEngine().stream().filter(engine -> engineOn.test(engine));
            motion.up(drone);
        }

    }

    @Override
    public void stabilize(Drone drone) {
        takeOff(drone);
        Drone orientationSensor = status(drone);
        if (null != orientationSensor) {
            drone.getGyroscope().setAngularVelInX(hoverVelocity);
            drone.getGyroscope().setAngularVelInY(hoverVelocity);
            drone.getGyroscope().setAngularVelInZ(hoverVelocity);

        }
        boolean hasHover = MotionService.calculateThrust(drone.getGyroscope().getAngularVelInX())
                .equals(MotionService.DEFAULT_DRONE_WEIGHT)
                && MotionService.calculateThrust(drone.getGyroscope().getAngularVelInY())
                .equals(MotionService.DEFAULT_DRONE_WEIGHT)
                && MotionService.calculateThrust(drone.getGyroscope().getAngularVelInZ())
                .equals(MotionService.DEFAULT_DRONE_WEIGHT);
        if (hasHover) {
            LOG.info("Drone in hover mode.");
            drone.setStatus(Drone.STATUS.HOVERING);
        }


    }


    @Override
    public void land(Drone drone) {
        if (null != drone && !drone.getEngine().isEmpty()) {
            drone.getEngine().stream().filter(engine -> engineOn.test(engine));
            motion.down(drone);

        }
    }

    @Override
    public Drone status(Drone drone) {
        drone.setSensor(new OrientationSensor());
        return  drone;
    }

    @Override
    public void move(Drone drone) {
        forwardBackMove(drone);
        leftRightMove(drone);
    }

    public void forwardBackMove(Drone drone) {
        if (MotionService.hasMovement(drone.getSensor().getPitch())) {
            motion.forward(drone);
        } else {
            motion.back(drone);
        }
    }

    public void leftRightMove(Drone drone) {
        if (MotionService.hasMovement(drone.getSensor().getRoll())) {
            motion.right(drone);
        } else {
            motion.left(drone);
        }
    }
}
