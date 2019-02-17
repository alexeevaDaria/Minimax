package sample;

import javafx.geometry.Point2D;

public class PointAndDistance {
    private Point2D point;
    private double distance;

    public Point2D getPoint() {
        return point;
    }

    public double getDistance() {
        return distance;
    }

    public void setPoint(Point2D point) {
        this.point = point;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public PointAndDistance(Point2D point, double distance) {
        this.point = point;
        this.distance = distance;
    }
}
