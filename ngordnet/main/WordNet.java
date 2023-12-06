package ngordnet.main;

import edu.princeton.cs.algs4.In;

import java.util.*;

public class WordNet {
    private Graph graph;
    private Map<Integer, HashSet<String>> id;

    public WordNet(String synsetFile, String hyponymFile) {
        graph = new Graph();
        this.id = createId(synsetFile);
        buildGraph(hyponymFile);
    }

    public Map<Integer, HashSet<String>> createId(String synsetFile) {
        Map<Integer, HashSet<String>> retId = new TreeMap<>();
        In mappings = new In(synsetFile);
        HashSet<String> words = new HashSet<>();
        while (mappings.hasNextLine()) {
            String line = mappings.readLine();
            String[] splits = line.split(",");
            int num = Integer.parseInt(splits[0]);
            String[] spaceSplits = splits[1].split(" ");
            words.addAll(Arrays.asList(spaceSplits));
            if (!retId.containsKey(num)) {
                retId.put(num, words);
            }
            words = new HashSet<>();
        }
        return retId;
    }

    public void buildGraph(String hyponymFile) {
        In edges = new In(hyponymFile);
        while (edges.hasNextLine()) {
            String[] edgeMappings = edges.readLine().split(",");
            int node = Integer.parseInt(edgeMappings[0]);
            graph.createNode(node);
            int edgeValues;
            for (int i = 1; i < edgeMappings.length; i++) {
                edgeValues = Integer.parseInt(edgeMappings[i]);
                graph.addEdge(node, edgeValues);
            }
        }
    }

    public Set<String> getHyponym(String hyper) {
        Set<Integer> keys = new HashSet<>();
        Set<String> hyponyms = new TreeSet<>();
        for (int k : id.keySet()) {
            if (id.get(k).contains(hyper)) {
                keys.add(k);
            }
        }
        //if (keys.isEmpty()) {
        //    throw new IllegalArgumentException();
        //}

        for (int key : keys) {
            List<Integer> retHyponyms = graph.getHyponyms(key);
            for (int hyp : retHyponyms) {
                hyponyms.addAll(id.get(hyp));
            }
        }
        return hyponyms;
    }

    public Set<String> getHyponym(List<String> hypers) {
        Set<String> combinedHyponyms = getHyponym(hypers.get(0));
        for (String hyper : hypers) {
            combinedHyponyms.retainAll(this.getHyponym(hyper));
        }
        //for (String hyper : hypers) {
        //    combinedHyponyms.retainAll(this.getHyponym(hyper));
        //}
        return combinedHyponyms;
    }
}