package ru.bvpotapenko.sw.algo.graph;

import java.util.*;

public class Graph {
    private int vertexCount = 0;
    private int edgeCount = 0;
    private boolean isOriented;
    protected Map<Integer, LinkedList<Edge>> adjLists = new TreeMap<>();

    public Graph(boolean isOriented) {
        this.isOriented = isOriented;
    }

    public int getVertexCount() {
        return vertexCount;
    }

    public int getEdgeCount() {
        return edgeCount;
    }

    public void addEdge(int v1, int v2, int weight) {
        if (v1 < 0 || v2 < 0) {
            throw new IllegalArgumentException("Negative indexes are forbidden");
        }
        if (!adjLists.containsKey(v1))
            addVertex(v1);
        adjLists.get(v1).add(new Edge(v1, v2, weight));

        if (!isOriented) {
            if (!adjLists.containsKey(v2))
                addVertex(v2);
            adjLists.get(v2).add(new Edge(v2, v1, weight));
        }
        edgeCount++;
    }

    public void addVertex(int vertex) {
        adjLists.put(vertex, new LinkedList<>());
        vertexCount++;
    }

    class Edge {
        int from;
        int to;
        int weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Edge)) return false;

            Edge edge = (Edge) o;

            if (from == edge.from && to == edge.to && weight == edge.weight)
                return true;
            if (!isOriented && from == edge.to && to == edge.from && weight == edge.weight)
                return true;
            return false;
        }

        @Override
        public int hashCode() {
            int result = from;
            if (isOriented) {
                result = 31 * result + to;
                result = 31 * result + weight;
            } else {
                result = (result + to);
                result = 31 * result + Math.abs(from - to);
                result = 31 * result + weight;
            }
            return result;
        }
    }

    public LinkedList<Edge> adjList(int vertex) {
        return adjLists.get(vertex);
    }

    /**
     * (1):
     * ==[1]==> (2)
     * ==[2]==> (4)
     * (2):
     * ==[1]==> (3)
     * <p>
     * For non-oriented graphs we only show edges once
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Set<Edge> usedEdges = new HashSet<>();
        String arrow = isOriented ? ">" : "";
        for (Map.Entry<Integer, LinkedList<Edge>> entry : adjLists.entrySet()) {
            sb.append("(" + entry.getKey() + "):");
            for (Edge e : entry.getValue()) {
                if (isOriented || usedEdges.add(e)) {
                    sb.append("\n\t==[" + e.weight + "]==" + arrow + " (" + e.to + ")");
                }
            }
            sb.append("\n");
        }

        return sb.toString();
    }
}
