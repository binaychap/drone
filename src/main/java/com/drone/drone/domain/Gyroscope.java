package com.drone.drone.domain;

import java.util.Objects;

/**
 * @author binayrai
 */
public class Gyroscope {
    private Double angularVelInX;
    private Double angularVelInY;
    private Double angularVelInZ;

    public Double getAngularVelInX() {
        return angularVelInX;
    }

    public void setAngularVelInX(Double angularVelInX) {
        this.angularVelInX = angularVelInX;
    }

    public Double getAngularVelInY() {
        return angularVelInY;
    }

    public void setAngularVelInY(Double angularVelInY) {
        this.angularVelInY = angularVelInY;
    }

    public Double getAngularVelInZ() {
        return angularVelInZ;
    }

    public void setAngularVelInZ(Double angularVelInZ) {
        this.angularVelInZ = angularVelInZ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Gyroscope gyroscope = (Gyroscope) o;
        return Objects.equals(angularVelInX, gyroscope.angularVelInX) &&
                Objects.equals(angularVelInY, gyroscope.angularVelInY) &&
                Objects.equals(angularVelInZ, gyroscope.angularVelInZ);
    }

    @Override
    public int hashCode() {
        return Objects.hash(angularVelInX, angularVelInY, angularVelInZ);
    }

    @Override
    public String toString() {
        return "Gyroscope{" +
                "angularVelInX=" + angularVelInX +
                ", angularVelInY=" + angularVelInY +
                ", angularVelInZ=" + angularVelInZ +
                '}';
    }
}
