package com.brayden;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import org.junit.Test;

public class CloneGraph extends LeetcodeGraph {

    private static class Pair {
        UndirectedGraphNode oldNode, newNode;

        Pair(UndirectedGraphNode oldNode, UndirectedGraphNode newNode) {
            this.oldNode = oldNode;
            this.newNode = newNode;
        }

        @Override
        public boolean equals(Object p) {
            if (!(p instanceof Pair))
                return false;
            return oldNode.equals(((Pair) p).oldNode);
        }

        @Override
        public int hashCode() {
            return oldNode.hashCode();
        }
    }

    public UndirectedGraphNode cloneGraph1(UndirectedGraphNode node) {
        if (node == null)
            return null;

        Set<Pair> graghSet = new HashSet<>();
        Map<Integer, UndirectedGraphNode> map = new HashMap<>();
        UndirectedGraphNode resultNode = newNode(node.label);
        map.put(resultNode.label, resultNode);
        graghSet.add(new Pair(node, resultNode));

        while (!graghSet.isEmpty()) {
            Pair p = graghSet.iterator().next();
            graghSet.remove(p);
            for (UndirectedGraphNode n : p.oldNode.neighbors) {
                UndirectedGraphNode tmpNode = map.get(n.label);
                if (tmpNode == null)
                    tmpNode = newNode(n.label);
                Pair tmpPair = new Pair(n, tmpNode);
                if (!map.containsKey(tmpNode.label))
                    graghSet.add(tmpPair);
                p.newNode.neighbors.add(tmpNode);
                map.put(tmpNode.label, tmpNode);
            }
        }
        return resultNode;
    }

    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if (node == null)
            return null;

        Queue<UndirectedGraphNode> bsfQ = new LinkedList<>();
        Map<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<>();
        bsfQ.offer(node);
        map.put(node, newNode(node.label));

        while (!bsfQ.isEmpty()) {
            UndirectedGraphNode curNode = bsfQ.poll();
            for (UndirectedGraphNode n : curNode.neighbors) {
                if (!map.containsKey(n)) {
                    map.put(n, newNode(n.label));
                    bsfQ.offer(n);
                }
                map.get(curNode).neighbors.add(map.get(n));
            }
        }
        return map.get(node);
    }

    @Test
    public void test() {
        UndirectedGraphNode n = newNode(0);
        n.neighbors.add(n);
        n.neighbors.add(n);
        assertEquals(0, cloneGraph(n).label);

        /**
         * 
         Input: {0,1,5#1,2,5#2,3#3,4,4#4,5,5#5} Output:
         * {0,1,5#1,2,5#2,3#3,4,4#4,5,5#4,5,5#5#5#5#5} Expected:
         * {0,1,5#1,2,5#2,3#3,4,4#4,5,5#5}
         */
    }
    
    @Test
    public void test1() {
        UndirectedGraphNode n0 = newNode(0);
        UndirectedGraphNode n1 = newNode(1);
        UndirectedGraphNode n2 = newNode(2);
        n0.neighbors.add(n1);
        n1.neighbors.add(n2);
        n2.neighbors.add(n2);
        assertEquals(0, cloneGraph(n0).label);
    }
}
