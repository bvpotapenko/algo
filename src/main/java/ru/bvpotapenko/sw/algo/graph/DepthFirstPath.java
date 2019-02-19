package ru.bvpotapenko.sw.algo.graph;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class DepthFirstPath {
    private Map<Integer, Boolean> marked;
    private Map<Integer, Integer> edgeTo;
    private int source; //Initial Vertex

    public DepthFirstPath(Graph g, int source) {
        if (source < 0) {
            throw new IllegalArgumentException("Negative Vertex index");
        }
        this.source = source;
        edgeTo = new HashMap<>(g.vertexCount());
        marked = new HashMap<>(g.vertexCount());
        dfs(g, source);
    }

    private void dfs(Graph g, int v) {
        marked.put(v, true);
        System.out.println(g.adjList(v));
        for (Graph.Edge w:
                g.adjList(v)) {
            if (!marked.containsKey(w.to) || !marked.get(w.to)) {
                edgeTo.put(w.to, v);
                dfs(g, w.to);
            }
        }
    }

    public boolean hasPathTo(int dist) {return marked.containsKey(dist);}

    public LinkedList<Integer> pathTo(int dist) {
        if (!hasPathTo(dist)) {
            return null;
        }
        LinkedList<Integer> stack = new LinkedList<>();
        int vertex = dist;
        while (vertex != source) {
            stack.push(vertex);
            vertex = edgeTo.get(vertex);
        }
        return stack;
    }
}
