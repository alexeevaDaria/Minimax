package sample;


import javafx.geometry.Point2D;

import java.util.ArrayList;
import java.util.List;

public class Cluster {
    private Point2D centroid;
    private List<Point2D> points = new ArrayList<>();
    private Point2D newPossibleCentroid;

    public void setPoints(List<Point2D> points) {
        this.points = points;
    }

    public Point2D getNewPossibleCentroid() {
        return newPossibleCentroid;
    }

    public void setNewPossibleCentroid(Point2D newPossibleCentroid) {
        this.newPossibleCentroid = newPossibleCentroid;
    }

    public void setCentroid(Point2D centroid) {
        this.centroid = centroid;
    }

    public List<Point2D> getPoints() {
        return points;
    }

    public Cluster(Point2D centroid) {
        this.centroid = centroid;
    }

    public Point2D getCentroid() {
        return centroid;
    }

    public void addPointToCluster(Point2D point) {
        points.add(point);
    }
}
