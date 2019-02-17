package sample;

import javafx.geometry.Point2D;

import java.util.ArrayList;
import java.util.List;

public class Minimax {
    private int pointsCount;
    private List<Point2D> points;
    private List<Cluster> clusters = new ArrayList<>();

    public Minimax(int pointsCount) {
        this.pointsCount = pointsCount;
        this.points = new ArrayList<>(pointsCount);
    }

    public List<Cluster> calculate() {
        generatePoints();
        generateCluster();
        assignPointsToCluster();
        PointAndDistance newPossibleCluster = calculateNewPossibleCluster();
        do {
            addNewClusterAndClearPoints(newPossibleCluster.getPoint());
            assignPointsToCluster();
            newPossibleCluster = calculateNewPossibleCluster();
        } while (newPossibleCluster.getDistance() > calculateThresholdDistance());
        return clusters;
    }

    private double calculateThresholdDistance() {  //todo ???? среднее арифметическое
        double sum = 0;
        for (int i = 0; i < clusters.size() - 1; ++i) {
            for (int j = i + 1; j < clusters.size(); ++j) { //todo ?
                sum += euclideanDistance(clusters.get(i).getCentroid(), clusters.get(j).getCentroid());
            }
        }
        int distancesNumber = 0;
        for (int i = clusters.size() - 1; i > 0; --i) {
            distancesNumber += i;
        }
        return (sum / distancesNumber) / 2;
    }

    private void generatePoints() {
        int a = 0, x2 = 900, y2 = 500;
        for (int i = 0; i < pointsCount; ++i) {
            points.add(new Point2D(a + Math.random() * x2, a + Math.random() * y2));
        }
    }

    private void generateCluster() {
        clusters.add(new Cluster(points.get((int) (Math.random() * pointsCount))));
    }

    private void addNewClusterAndClearPoints(Point2D newPossibleCluster){
        clusters.add(new Cluster(newPossibleCluster));
        for( Cluster cluster: clusters){
            cluster.setPoints(new ArrayList<>());
        }
    }

    private PointAndDistance calculateNewPossibleCluster() {
        PointAndDistance newPossibleCluster = new PointAndDistance(null, 0);
        for (Cluster cluster : clusters) {
            double distance = calculateMaxDistanceInCluster(cluster);
            if (distance > newPossibleCluster.getDistance()) {
                newPossibleCluster.setDistance(distance);
                newPossibleCluster.setPoint(cluster.getNewPossibleCentroid());
            }
        }
        return newPossibleCluster;
    }

    private double calculateMaxDistanceInCluster(Cluster cluster) {
        double maxDistanceInCluster = Double.MIN_VALUE;
        for (Point2D point : cluster.getPoints()) {
            double distance = euclideanDistance(cluster.getCentroid(), point);
            if (distance > maxDistanceInCluster) {
                maxDistanceInCluster = distance;
                cluster.setNewPossibleCentroid(point);
            }
        }
        return maxDistanceInCluster;
    }


    private void assignPointsToCluster() {
        for (Point2D point : points) {
            double minDistance = Double.MAX_VALUE;
            Cluster minCluster = clusters.get(0);
            for (Cluster cluster : clusters) {
                double distance = euclideanDistance(cluster.getCentroid(), point);
                if (distance < minDistance) {
                    minDistance = distance;
                    minCluster = cluster;
                }
            }
            minCluster.addPointToCluster(point);
        }
    }

    private double euclideanDistance(Point2D point1, Point2D point2) {
        double x = point1.getX() - point2.getX();
        double y = point1.getY() - point2.getY();
        return Math.hypot(x, y);
    }
}