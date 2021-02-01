import java.util.*;

/**
 * Undirected graph class with nodes labeled with Strings.
 */
public class Graph {

    private Map<String, Node> nodes;

    public Graph() {
        nodes = new HashMap<>();
    }

    public List<String> BFS(String key) {
        Node start = nodes.get(key);
        Map<String, Boolean> visited = new HashMap<>();
        List<String> result = new ArrayList<>();
        LinkedList<Node> queue = new LinkedList<>();
        for(String s : nodes.keySet()) {
            visited.put(s, false);
        }
        queue.add(start);
        visited.replace(start.getLabel(), false, true);
        Node current;
        while(queue.size() != 0) {
            current = queue.poll();
            for(Node n : current.getNeighbors()) {
                if(!visited.get(n.getLabel())) {
                    queue.add(n);
                    visited.replace(n.getLabel(), false, true);
                }
            }
        }
        for (Map.Entry<String, Boolean> entry : visited.entrySet()) {
            if(entry.getValue()) {
                result.add(entry.getKey());
            }
        }

        return result;
    }

    public void addNode(String label) {
        if (!nodes.containsKey(label)) {
            nodes.put(label, new Node(label));
        }
    }

    /**
     * Creates an edge from predecessor to successor. If nodes do not exist,
     * will add the nodes to the graph.
     */
    public void connect(String predecessor, String successor) {
        if (!nodes.containsKey(predecessor)) {
            addNode(predecessor);
        }
        if (!nodes.containsKey(successor)) {
            addNode(successor);
        }
        Node pred = nodes.get(predecessor);
        Node succ = nodes.get(successor);
        pred.addNeighbor(succ);
        succ.addNeighbor(pred);
    }

    /**
     * Returns a set of all nodes' labels in the graph.
     */
    public Set<String> labels() {
        return nodes.keySet();
    }

    /**
     * Return a set of the labels of neighbors of the Node labeled node.
     * Only returns outgoing neighbors (i.e. if there is an outgoing edge
     * from node to the neighbor.
     */
    public Set<String> neighbors(String node) {
        if (!nodes.containsKey(node)) {
            return null;
        }
        Node n = nodes.get(node);
        Set<String> toReturn = new HashSet<>();
        for (Node neighbor : n.getNeighbors()) {
            toReturn.add(neighbor.getLabel());
        }
        return toReturn;
    }

    class Node {
        private String label;

        private Set<Node> neighbors;

        Node(String label) {
            this.label = label;
            neighbors = new HashSet<>();
        }

        void addNeighbor(Node neighbor) {
            neighbors.add(neighbor);
            if (!neighbor.neighbors.contains(this)) {
                neighbor.addNeighbor(this);
            }
        }

        Set<Node> getNeighbors() {
            return neighbors;
        }

        String getLabel() {
            return label;
        }
    }
}