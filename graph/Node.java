package graph;

import java.util.ArrayList;
import java.util.List;

public class Node {
    private String name;
    private List<Edge> edges = new ArrayList<>();
    private List<String> lines = new ArrayList<>();
    private double lng;
    private double lat;

    private Node predecessor;
    private int distanceFromSource = Integer.MAX_VALUE;
    private int eccentricity = -1;
    private boolean marked = false;

    public Node(String name, List<String> lines, double lng, double lat) {
        this.name = name;
        this.lines = lines;
        this.lat = lat;
        this.lng = lng;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public void setEdges(List<Edge> edges) {
        this.edges = edges;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getLines() {
        return lines;
    }

    public void setLines(List<String> lines) {
        this.lines = lines;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public void addEdge(Edge e) {
        edges.add(e);
    }

    public int getDistanceFromSource() {
        return distanceFromSource;
    }

    public void setDistanceFromSource(int distanceFromSource) {
        this.distanceFromSource = distanceFromSource;
    }

    public boolean isMarked() {
        return marked;
    }

    public void setMarked(boolean marked) {
        this.marked = marked;
    }

    public int getWeightForNeigbhor(Node nodeTo) {
        int weight = 0;
        for (int i = 0; i < edges.size(); i++) {
            if (edges.get(i).getNodeTo().equals(nodeTo)) {
                weight = edges.get(i).getWeight();
            }
        }
        return (weight);
    }

    /**
     * Get the edge that points towards the node passed as argument
     *
     * TODO what if several edges point to the same neighbor ?
     * @param neighbor the node where we want to go
     * @return Edge pointing to the Node passed
     */
    public Edge getEdgeToNeighbor(Node neighbor) {
        for (Edge edge: edges) {
            if (edge.getNodeTo().equals(neighbor)) {
                return edge;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "graph.Node{" +
                "name='" + name + '\'' +
                ", lng=" + lng +
                ", lat=" + lat +
                ", lines=" + lines +
                ", marked=" + marked +
                '}';
    }

    public int getEccentricity() {
        return eccentricity;
    }

    public void setEccentricity(int eccentricity) {
        this.eccentricity = eccentricity;
    }

    public Node getPredecessor() {
        return predecessor;
    }

    public void setPredecessor(Node predecessor) {
        this.predecessor = predecessor;
    }
}
