package com.drone.drone.domain;

import java.util.Objects;

/**
 * @author binayrai
 */
public class Engine {
    private Integer powerIndicator;
    private STATUS status;

    public Engine() {
    }

    public Engine(Integer powerIndicator, STATUS status) {
        this.powerIndicator = powerIndicator;
        this.status = status;
    }

    public Integer getPowerIndicator() {
        return powerIndicator;
    }

    public void setPowerIndicator(Integer powerIndicator) {
        this.powerIndicator = powerIndicator;
    }

    public STATUS getStatus() {
        return status;
    }

    public void setStatus(STATUS status) {
        this.status = status;
    }

   public enum STATUS{
        OFF, ON
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Engine engine = (Engine) o;
        return Objects.equals(powerIndicator, engine.powerIndicator) &&
                status == engine.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(powerIndicator, status);
    }

    @Override
    public String toString() {
        return "Engine{" +
                "powerIndicator=" + powerIndicator +
                ", status=" + status +
                '}';
    }
}
