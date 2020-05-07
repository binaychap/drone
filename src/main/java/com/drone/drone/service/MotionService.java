package com.drone.drone.service;

import com.drone.drone.domain.Drone;
import com.drone.drone.domain.Engine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author binayrai
 */
public class MotionService implements MotionAPI {
    private static final Logger LOG = LoggerFactory.getLogger(MotionService.class);
    public static final Double DEFAULT_DRONE_WEIGHT = 12.5; // weight in kg/s

    /**
     * rare engine with power 10
     * - front engine with power 1
     *
     * @param
     */
    @Override
    public void forward(Drone drone) {
        validate(drone.getEngine());
        if (hasMovement(drone.getSensor().getPitch())
                && checkAscEnginePattern(drone.getEngine())) {
            LOG.info("Drone in Forward Direction");
           setGyroscopeWithPosVelocity(drone);
        }
    }

    @Override
    public void back(Drone drone) {
        validate(drone.getEngine());
        if (hasMovement(drone.getSensor().getPitch())
                && !checkRevEnginePattern(drone.getEngine())) {
            LOG.info("Drone in Backward Direction");
            setGyroscopeWithNegVelocity(drone);

        }
    }

    @Override
    public void left(Drone drone) {
        if (hasMovement(drone.getSensor().getRoll())
                && !checkRevEnginePattern(drone.getEngine())) {
            LOG.info("Drone in Left Direction");
            setGyroscopeWithNegVelocity(drone);
        }
    }

    @Override
    public void right(Drone drone) {
        if (hasMovement(drone.getSensor().getRoll())
                && checkAscEnginePattern(drone.getEngine())) {
            LOG.info("Drone in Right Direction");
            setGyroscopeWithPosVelocity(drone);
        }
    }

    @Override
    public void up(Drone drone) {
        if (calculateThrust(drone.getGyroscope().getAngularVelInZ()) > DEFAULT_DRONE_WEIGHT) {
           drone.setStatus(Drone.STATUS.MOVING);
           setGyroscopeWithPosVelocity(drone);
            LOG.info("Drone are in up direction");
        }
    }

    @Override
    public void down(Drone drone) {
        if (calculateThrust(drone.getGyroscope().getAngularVelInZ()) < DEFAULT_DRONE_WEIGHT) {
            LOG.info("Drone are in down direction");
        }
    }

    static Double calculateThrust(Double angularVelocity) {
        return DEFAULT_DRONE_WEIGHT * angularVelocity;
    }

    boolean checkAscEnginePattern(List<Engine> engines){
        engines.sort(Comparator.comparing(Engine::getPowerIndicator));
        return compareEnginePower(engines);
    }
    boolean checkRevEnginePattern(List<Engine> engines){
        engines.sort(Comparator.comparing(Engine::getPowerIndicator).reversed());
        return compareEnginePower(engines);

    }

    private boolean compareEnginePower(List<Engine> engines) {
        AtomicReference<Boolean> test = new AtomicReference<>(false);
        AtomicReference<Integer> enginePower = new AtomicReference<>(engines.get(0).getPowerIndicator());
        engines.stream().skip(1).forEach(engine -> {
            if (engine.getPowerIndicator().compareTo(enginePower.get()) == 1) {
                test.set(true);
                enginePower.set(engine.getPowerIndicator());
            }
        });
        return test.get();
    }

    public static boolean hasMovement(Integer value){
        return (value == 1 || value.equals(1));
    }

    void validate(List<Engine> engines) {
        boolean test = engines.size() == 4 ? true : false;
        Assert.isTrue(test);
    }

    private void setGyroscopeWithPosVelocity(Drone drone){
        drone.getGyroscope().setAngularVelInX(getRandomNumberDoubles(1D,100D));
        drone.getGyroscope().setAngularVelInY(getRandomNumberDoubles(1D,100D));
        drone.getGyroscope().setAngularVelInZ(getRandomNumberDoubles(1D,100D));
    }
    private void setGyroscopeWithNegVelocity(Drone drone){
        drone.getGyroscope().setAngularVelInX(getRandomNumberDoubles(-1D,-100D));
        drone.getGyroscope().setAngularVelInY(getRandomNumberDoubles(-1D,-100D));
        drone.getGyroscope().setAngularVelInZ(getRandomNumberDoubles(-1D,-100D));
    }

    public static double getRandomNumberDoubles(double min, double max){
        Random random = new Random();
        return random.doubles(min,(max+1)).findFirst().getAsDouble();
    }

}
