package ngordnet.main;

import java.util.*;

public class Graph {
    private Map<Integer, List<Integer>> adjList;
    public Graph() {
        adjList = new TreeMap<>();
    }

    public void createNode(int node) {
        if (!adjList.containsKey(node)) {
            adjList.put(node, new ArrayList<>());
        }
    }

    public Set<Integer> getNodes() {
        return adjList.keySet();
    }

    public void addEdge(int start, int end) {
        createNode(start);
        createNode(end);
        adjList.get(start).add(end);
    }

    public List<Integer> neighbors(int node) {
        return adjList.get(node);
    }

    public int numberOfNodes() {
        return adjList.size();
    }

    public List<Integer> getHyponyms(int val){ // need to use graph traversal instead
        boolean[] marked = new boolean[this.numberOfNodes()];
        List<Integer> ret = new ArrayList<>();
        Queue<Integer> fringe = new PriorityQueue<>();
        ret.add(val);
        fringe.add(val);
        while (!fringe.isEmpty()) {
            int current = fringe.poll();
            for (int neighbor : this.neighbors(current)) {
                if (!marked[neighbor]) {
                    marked[neighbor] = true;
                    fringe.add(neighbor);
                    ret.add(neighbor);
                }
            }
        }
        return ret;
    }

    public void print(){
        System.out.println(adjList.toString());
    }




}
