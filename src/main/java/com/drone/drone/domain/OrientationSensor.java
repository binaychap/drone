package com.drone.drone.domain;

import java.util.Objects;

/**
 * @author binayrai
 */
public class OrientationSensor {
    private Integer pitch;
    private Integer roll;

    /**
     *  Left to right 1
     *  right to left -1
     * @return
     */
    public Integer getPitch() {
        return pitch;
    }

    public void setPitch(Integer pitch) {
        this.pitch = pitch;
    }

    /**
     * Back to Front  1
     * Front to Back -1
     * @return
     */
    public Integer getRoll() {
        return roll;
    }

    public void setRoll(Integer roll) {
        this.roll = roll;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrientationSensor that = (OrientationSensor) o;
        return Objects.equals(pitch, that.pitch) &&
                Objects.equals(roll, that.roll);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pitch, roll);
    }

    @Override
    public String toString() {
        return "OrientationSensor{" +
                "pitch=" + pitch +
                ", roll=" + roll +
                '}';
    }
}
