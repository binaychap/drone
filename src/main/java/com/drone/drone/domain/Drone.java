package com.drone.drone.domain;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * @author binayrai
 */
public class Drone implements Serializable {
    private List<Engine> engine = null;
    private Gyroscope gyroscope;
    private OrientationSensor sensor;
    private STATUS status;

    public List<Engine> getEngine() {
        return engine;
    }

    public void setEngine(List<Engine> engine) {
        this.engine = engine;
    }

    public Gyroscope getGyroscope() {
        return gyroscope;
    }

    public void setGyroscope(Gyroscope gyroscope) {
        this.gyroscope = gyroscope;
    }

    public OrientationSensor getSensor() {
        return sensor;
    }

    public void setSensor(OrientationSensor sensor) {
        this.sensor = sensor;
    }

    public STATUS getStatus() {
        return status;
    }

    public void setStatus(STATUS status) {
        this.status = status;
    }

    public enum  STATUS{
        OFF,HOVERING,MOVING
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Drone drone = (Drone) o;
        return Objects.equals(engine, drone.engine) &&
                Objects.equals(gyroscope, drone.gyroscope) &&
                Objects.equals(sensor, drone.sensor) &&
                status == drone.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(engine, gyroscope, sensor, status);
    }

    @Override
    public String toString() {
        return "Drone{" +
                "engine=" + engine +
                ", gyroscope=" + gyroscope +
                ", sensor=" + sensor +
                ", status=" + status +
                '}';
    }
}
