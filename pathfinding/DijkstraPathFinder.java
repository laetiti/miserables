package pathfinding;

import graph.Edge;
import graph.Graph;
import graph.Node;

import java.util.*;

public class DijkstraPathFinder extends PathFinder {
    private Graph graph;

    private List<Edge> path = new ArrayList<>();
    private int pathLength;
    private List<Node> traversedNodes = new ArrayList<>();

    public DijkstraPathFinder(Graph graph) {
        this.graph = graph;
    }

    // TODO throw if called before calling computeShortestPath
    @Override
    public List<Edge> getPath() {
        return path;
    }

    // TODO throw if called before calling computeShortestPath
    @Override
    public int getPathLength() {
        return pathLength;
    }

    @Override
    Graph getGraph() {
        return graph;
    }


    @Override
    public void traverse(Node sourceNode) {
        Node currentNode = sourceNode;
        currentNode.setDistanceFromSource(0);

        while (currentNode != null) {
            for (Edge edge: currentNode.getEdges()) {
                Node neighbor = edge.getNodeTo();

                int neighborDistance = currentNode.getDistanceFromSource() + currentNode.getWeightForNeigbhor(neighbor);

                // neighbordistance > 0 : prevent int overflow going to negative
                // TODO something bad happens here :'(
                if (neighborDistance < neighbor.getDistanceFromSource()) {
                    neighbor.setDistanceFromSource(neighborDistance);
                    neighbor.setPredecessor(currentNode);
                }
            }

            currentNode.setMarked(true);
            traversedNodes.add(currentNode);

            // Find the new current node
            currentNode = findUnmarkedNodeAtMinimalDistance();
        }
    }

    @Override
    public void performChecks() {
        // TODO check every weight is positive
    }

    @Override
    public void setPath(List<Edge> path) {
        this.path = path;
    }

    @Override
    public void setPathLength(int pathLength) {
        this.pathLength = pathLength;
    }

    @Override
    public List<Node> getTraversedNodes() {
        return traversedNodes;
    }

    @Override
    public void clearTraversedNodes() {
        traversedNodes = new ArrayList<>();
    }

    // TODO a better way to do this could be to keep up-to-date a Map that would map distances of unmarked Nodes to these nodes (then the number of Nodes to iterate on would decrease as the algorithm runs)
    private Node findUnmarkedNodeAtMinimalDistance() {
        Map<Integer, Node> nodes = graph.getNodes();
        Node selectedNode = null;

        for (Map.Entry<Integer, Node> currentEntry : nodes.entrySet()) {
            Node currentNode = currentEntry.getValue();

            // Keep only unmarked nodes :
            if (currentNode.isMarked()) continue;

            // Keep only nodes that have been set a non-infinite distance (aka neighbors of already visited nodes)
            if (currentNode.getDistanceFromSource() == Integer.MAX_VALUE) continue;

            if (selectedNode == null || currentNode.getDistanceFromSource() < selectedNode.getDistanceFromSource()) {
                selectedNode = currentNode;
            }
        }

        return selectedNode;
    }
}
