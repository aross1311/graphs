package graph;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/** Unit tests for the Graph class.
 *  @author
 */
public class GraphTest {

    public DirectedGraph buildComp1() {
        DirectedGraph comp1 = new DirectedGraph();
        comp1.add();
        comp1.add();
        comp1.add();
        comp1.add();
        comp1.add();
        comp1.add();
        comp1.add();
        comp1.add(1, 2);
        comp1.add(2, 1);
        comp1.add(2, 3);
        comp1.add(2, 4);
        comp1.add(3, 6);
        comp1.add(4, 4);
        comp1.add(4, 5);
        comp1.add(5, 6);
        comp1.add(5, 7);
        comp1.add(6, 1);

        return comp1;

    }

    public UndirectedGraph buildComp2() {
        UndirectedGraph comp2 = new UndirectedGraph();
        comp2.add();
        comp2.add();
        comp2.add();
        comp2.add();
        comp2.add();
        comp2.add();
        comp2.add();
        comp2.add(1, 2);
        comp2.add(2, 3);
        comp2.add(2, 4);
        comp2.add(3, 6);
        comp2.add(4, 5);
        comp2.add(5, 6);
        comp2.add(5, 7);
        comp2.add(6, 1);
        comp2.add(4, 4);

        return comp2;

    }

    public boolean compare2dArrays(ArrayList<int[]> first,
                                   ArrayList<int[]> second) {

        assert (first.size() == second.size());
        int outerInd = 0;
        int innerInd = 0;
        for (var a : first) {
            if (innerInd == 0) {
                int ref1 = first.get(outerInd)[innerInd];
                int ref2 = second.get(outerInd)[innerInd];
                if (ref1 != ref2) {
                    return false;
                }
                innerInd += 1;
            } else if (innerInd == 1) {
                int ref1 = first.get(outerInd)[innerInd];
                int ref2 = second.get(outerInd)[innerInd];
                if (ref1 != ref2) {
                    return false;
                }
            }
            if (innerInd == 1) {
                outerInd += 1;
                innerInd = 0;
            }
        }

        return true;
    }

    @Test
    public void emptyGraph() {
        DirectedGraph g = new DirectedGraph();
        assertEquals("Initial graph has vertices", 0, g.vertexSize());
        assertEquals("Initial graph has edges", 0, g.edgeSize());
    }

    @Test
    public void undirectedGraphGettersTest() {
        UndirectedGraph g = new UndirectedGraph();
        assertEquals("Initial graph has vertices", 0, g.vertexSize());
        assertEquals("Initial graph has edges", 0, g.edgeSize());
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        assertEquals(5, g.vertexSize());
        assertEquals(5, g.maxVertex());
        assertEquals(0, g.edgeSize());
        assertFalse(g.isDirected());

    }

    @Test
    public void directedGraphGettersTest() {
        DirectedGraph g = new DirectedGraph();
        assertEquals("Initial graph has vertices", 0, g.vertexSize());
        assertEquals("Initial graph has edges", 0, g.edgeSize());
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        assertEquals(5, g.vertexSize());
        assertEquals(5, g.maxVertex());
        assertEquals(0, g.edgeSize());
        assertTrue(g.isDirected());

    }

    @Test
    public void directedGraphDegreeTest() {
        DirectedGraph g = new DirectedGraph();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add(1, 2);
        g.add(2, 3);
        g.add(2, 4);
        g.add(3, 6);
        g.add(4, 5);
        g.add(5, 6);
        g.add(5, 7);
        g.add(6, 1);
        assertEquals(8, g.edgeSize());
        assertEquals(7, g.vertexSize());
        assertEquals(2, g.outDegree(2));
        assertEquals(1, g.inDegree(2));
        assertEquals(1, g.outDegree(1));
        assertEquals(1, g.inDegree(1));
        assertEquals(0, g.outDegree(32));
        assertEquals(0, g.inDegree(32));



    }

    @Test
    public void undirectedGraphDegreeTest() {
        UndirectedGraph g = new UndirectedGraph();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add(1, 2);
        g.add(2, 3);
        g.add(2, 4);
        g.add(3, 6);
        g.add(4, 5);
        g.add(5, 6);
        g.add(5, 7);
        g.add(6, 1);
        assertEquals(8, g.edgeSize());
        assertEquals(7, g.vertexSize());
        assertEquals(3, g.outDegree(2));
        assertEquals(3, g.inDegree(2));
        assertEquals(3, g.outDegree(2));
        assertEquals(3, g.inDegree(6));
        assertEquals(2, g.outDegree(1));
        assertEquals(2, g.inDegree(1));

    }

    @Test
    public void directedGraphRemoveTest() {
        DirectedGraph g = new DirectedGraph();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add(1, 2);
        g.add(2, 3);
        g.add(2, 4);
        g.add(3, 6);
        g.add(4, 5);
        g.add(5, 6);
        g.add(5, 7);
        g.add(6, 1);
        g.remove(2);
        assertEquals(5, g.edgeSize());
        g.add();
        g.add(1, 2);
        g.add(2, 3);
        g.add(2, 4);

        g.remove(2, 4);
        assertEquals(7, g.edgeSize());
        assertEquals(7, g.vertexSize());
        g.add(2, 4);
        assertEquals(8, g.edgeSize());
        assertEquals(7, g.vertexSize());
    }

    @Test
    public void undirectedGraphRemoveTest() {
        UndirectedGraph g = new UndirectedGraph();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add(1, 2);
        g.add(2, 3);
        g.add(2, 4);
        g.add(3, 6);
        g.add(4, 5);
        g.add(5, 6);
        g.add(5, 7);
        g.add(6, 1);
        g.remove(2);
        assertEquals(5, g.edgeSize());
        g.add();
        g.add(1, 2);
        g.add(2, 3);
        g.add(2, 4);

        g.remove(2, 4);
        assertEquals(7, g.edgeSize());
        assertEquals(7, g.vertexSize());
        g.add(2, 4);
        assertEquals(8, g.edgeSize());
        assertEquals(7, g.vertexSize());
    }

    @Test
    public void directedGraphContainsTest() {
        DirectedGraph g = new DirectedGraph();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add(1, 2);
        g.add(2, 3);
        g.add(2, 4);
        g.add(3, 6);
        g.add(4, 5);
        g.add(5, 6);
        g.add(5, 7);
        g.add(6, 1);
        assertTrue(g.contains(3));
        assertTrue(g.contains(5));
        assertTrue(g.contains(7));
        assertTrue(g.contains(1));
        assertTrue(g.contains(1, 2));
        assertFalse(g.contains(2, 1));
        assertTrue(g.contains(2, 4));
        assertTrue(g.contains(5, 7));
        assertTrue(g.contains(6, 1));
        g.remove(2, 4);
        assertFalse(g.contains(2, 4));
        assertTrue(g.contains(2));
        assertTrue(g.contains(4));
        g.add(2, 4);
        assertTrue(g.contains(2, 4));

    }

    @Test
    public void undirectedGraphContainsTest() {
        UndirectedGraph g = new UndirectedGraph();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add(2, 1);
        g.add(3, 2);
        g.add(4, 2);
        g.add(3, 6);
        g.add(4, 5);
        g.add(5, 6);
        g.add(5, 7);
        g.add(6, 1);
        assertTrue(g.contains(3));
        assertTrue(g.contains(5));
        assertTrue(g.contains(7));
        assertTrue(g.contains(1));
        assertTrue(g.contains(1, 2));
        assertTrue(g.contains(2, 1));
        assertTrue(g.contains(2, 4));
        assertTrue(g.contains(4, 2));
        assertTrue(g.contains(5, 7));
        assertTrue(g.contains(7, 5));
        assertTrue(g.contains(6, 1));
        assertTrue(g.contains(1, 6));
        g.remove(2, 4);
        assertFalse(g.contains(2, 4));
        assertFalse(g.contains(4, 2));
        assertTrue(g.contains(2));
        assertTrue(g.contains(4));
        g.add(2, 4);
        assertTrue(g.contains(2, 4));
        assertTrue(g.contains(4, 2));

    }

    @Test
    public void iterationVerticesTest() {
        DirectedGraph g = new DirectedGraph();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add(1, 2);
        g.add(2, 3);
        g.add(2, 4);
        g.add(3, 6);
        g.add(4, 5);
        g.add(5, 6);
        g.add(5, 7);
        g.add(6, 1);
        Iteration<Integer> iter = g.vertices();
        ArrayList<Integer> result = new ArrayList<>();
        ArrayList<Integer> lst = new ArrayList<>();
        while (iter.hasNext()) {
            lst.add(iter.next());
        }
        for (int i = 1; i < 8; i += 1) {
            result.add(i);
        }
        assertEquals(result, lst);
        g.remove(2);
        iter = g.vertices();
        result = new ArrayList<>();
        lst = new ArrayList<>();
        while (iter.hasNext()) {
            lst.add(iter.next());
        }
        for (int i = 1; i < 8; i += 1) {
            result.add(i);
        }
        result.remove(1);
        assertEquals(result, lst);

    }

    @Test
    public void iterationVerticesTest2() {
        UndirectedGraph h = new UndirectedGraph();
        h.add();
        h.add();
        h.add();
        h.add();
        h.add();
        h.add();
        h.add();
        h.add(1, 2);
        h.add(2, 3);
        h.add(2, 4);
        h.add(3, 6);
        h.add(4, 5);
        h.add(5, 6);
        h.add(5, 7);
        h.add(6, 1);
        Iteration<Integer> iter = h.vertices();
        ArrayList<Integer> result = new ArrayList<>();
        ArrayList<Integer> lst = new ArrayList<>();
        while (iter.hasNext()) {
            lst.add(iter.next());
        }
        for (int i = 1; i < 8; i += 1) {
            result.add(i);
        }
        assertEquals(result, lst);

        h.remove(2);
        iter = h.vertices();
        result = new ArrayList<>();
        lst = new ArrayList<>();
        while (iter.hasNext()) {
            lst.add(iter.next());
        }
        for (int i = 1; i < 8; i += 1) {
            result.add(i);
        }
        result.remove(1);
        assertEquals(result, lst);
    }

    @Test
    public void iterationSuccessorsTest() {
        DirectedGraph g = new DirectedGraph();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add(1, 2);
        g.add(2, 3);
        g.add(2, 4);
        g.add(3, 6);
        g.add(4, 5);
        g.add(5, 6);
        g.add(5, 7);
        g.add(6, 1);
        Iteration<Integer> iter = g.successors(2);
        ArrayList<Integer> result = new ArrayList<>();
        ArrayList<Integer> lst = new ArrayList<>();
        while (iter.hasNext()) {
            lst.add(iter.next());
        }
        result.add(3);
        result.add(4);
        assertEquals(result, lst);

        iter = g.successors(5);
        result = new ArrayList<>();
        lst = new ArrayList<>();
        while (iter.hasNext()) {
            lst.add(iter.next());
        }
        result.add(6);
        result.add(7);
        assertEquals(result, lst);

        iter = g.successors(7);
        result = new ArrayList<>();
        lst = new ArrayList<>();
        while (iter.hasNext()) {
            lst.add(iter.next());
        }
        assertEquals(result, lst);

    }

    @Test
    public void iterationSuccessorsTest2() {
        UndirectedGraph h = new UndirectedGraph();
        h.add();
        h.add();
        h.add();
        h.add();
        h.add();
        h.add();
        h.add();
        h.add(1, 2);
        h.add(2, 3);
        h.add(2, 4);
        h.add(3, 6);
        h.add(4, 5);
        h.add(5, 6);
        h.add(5, 7);
        h.add(6, 1);
        Iteration<Integer> iter = h.successors(2);
        ArrayList<Integer> result = new ArrayList<>();
        ArrayList<Integer> lst = new ArrayList<>();
        while (iter.hasNext()) {
            lst.add(iter.next());
        }
        result.add(3);
        result.add(4);
        result.add(1);
        assertEquals(result, lst);

        iter = h.successors(1);
        result = new ArrayList<>();
        lst = new ArrayList<>();
        while (iter.hasNext()) {
            lst.add(iter.next());
        }
        result.add(2);
        result.add(6);
        assertEquals(result, lst);

        iter = h.successors(7);
        result = new ArrayList<>();
        lst = new ArrayList<>();
        while (iter.hasNext()) {
            lst.add(iter.next());
        }
        result.add(5);
        assertEquals(result, lst);

    }

    @Test
    public void iterationPredecessorsTest() {
        DirectedGraph g = new DirectedGraph();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add(1, 2);
        g.add(2, 3);
        g.add(2, 4);
        g.add(3, 6);
        g.add(4, 5);
        g.add(5, 6);
        g.add(5, 7);
        g.add(6, 1);
        Iteration<Integer> iter = g.predecessors(2);
        ArrayList<Integer> result = new ArrayList<>();
        ArrayList<Integer> lst = new ArrayList<>();
        while (iter.hasNext()) {
            lst.add(iter.next());
        }
        result.add(1);
        assertEquals(result, lst);

        iter = g.predecessors(6);
        result = new ArrayList<>();
        lst = new ArrayList<>();
        while (iter.hasNext()) {
            lst.add(iter.next());
        }
        result.add(3);
        result.add(5);
        assertEquals(result, lst);

    }

    @Test
    public void iterationPredecessorsTest2() {
        UndirectedGraph h = new UndirectedGraph();
        h.add();
        h.add();
        h.add();
        h.add();
        h.add();
        h.add();
        h.add();
        h.add(1, 2);
        h.add(2, 3);
        h.add(2, 4);
        h.add(3, 6);
        h.add(4, 5);
        h.add(5, 6);
        h.add(5, 7);
        h.add(6, 1);
        Iteration<Integer> iter = h.predecessors(2);
        ArrayList<Integer> result = new ArrayList<>();
        ArrayList<Integer> lst = new ArrayList<>();
        while (iter.hasNext()) {
            lst.add(iter.next());
        }
        result.add(3);
        result.add(4);
        result.add(1);
        assertEquals(result, lst);

        iter = h.predecessors(1);
        result = new ArrayList<>();
        lst = new ArrayList<>();
        while (iter.hasNext()) {
            lst.add(iter.next());
        }
        result.add(2);
        result.add(6);
        assertEquals(result, lst);

    }

    @Test
    public void iterationEdgesDirectedTest() {
        DirectedGraph g = new DirectedGraph();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add(1, 2);
        g.add(2, 3);
        g.add(2, 4);
        g.add(3, 6);
        g.add(4, 5);
        g.add(5, 6);
        g.add(5, 7);
        g.add(6, 1);
        Iteration<int[]> iter = g.edges();
        ArrayList<int[]> result = new ArrayList<>();
        ArrayList<int[]> lst = new ArrayList<>();
        while (iter.hasNext()) {
            lst.add(iter.next());
        }
        result.add(new int[]{1, 2});
        result.add(new int[]{2, 3});
        result.add(new int[]{2, 4});
        result.add(new int[]{3, 6});
        result.add(new int[]{4, 5});
        result.add(new int[]{5, 6});
        result.add(new int[]{5, 7});
        result.add(new int[]{6, 1});
        ArrayList<Integer> resultList = new ArrayList<>();
        ArrayList<Integer> lstList = new ArrayList<>();
        for (var n : lst) {
            for (var m : n) {
                lstList.add(m);
            }
        }
        for (var n : result) {
            for (var m : n) {
                resultList.add(m);
            }
        }
        assertEquals(resultList, lstList);

    }

    @Test
    public void iterationEdgesUndirectedTest() {
        UndirectedGraph h = new UndirectedGraph();
        h.add();
        h.add();
        h.add();
        h.add();
        h.add();
        h.add();
        h.add();
        h.add(1, 2);
        h.add(2, 3);
        h.add(2, 4);
        h.add(3, 6);
        h.add(4, 5);
        h.add(5, 6);
        h.add(5, 7);
        h.add(6, 1);
        Iteration<int[]> iter = h.edges();
        ArrayList<int[]> result = new ArrayList<>();
        ArrayList<int[]> lst = new ArrayList<>();
        while (iter.hasNext()) {
            lst.add(iter.next());
        }
        result.add(new int[]{1, 2});
        result.add(new int[]{2, 3});
        result.add(new int[]{2, 4});
        result.add(new int[]{3, 6});
        result.add(new int[]{4, 5});
        result.add(new int[]{5, 6});
        result.add(new int[]{5, 7});
        result.add(new int[]{6, 1});
        ArrayList<Integer> resultList = new ArrayList<>();
        ArrayList<Integer> lstList = new ArrayList<>();
        for (var n : lst) {
            for (var m : n) {
                lstList.add(m);
            }
        }
        for (var n : result) {
            for (var m : n) {
                resultList.add(m);
            }
        }
        assertEquals(resultList, lstList);
    }

    @Test
    public void directedComp1GettersTest() {

        DirectedGraph g = buildComp1();
        assertEquals(10, g.edgeSize());
        assertEquals(7, g.vertexSize());
        assertEquals(7, g.maxVertex());
        assertTrue(g.isDirected());

    }

    @Test
    public void undirectedComp2GettersTest() {

        UndirectedGraph g = buildComp2();
        assertEquals(9, g.edgeSize());
        assertEquals(7, g.vertexSize());
        assertEquals(7, g.maxVertex());
        assertFalse(g.isDirected());

    }

    @Test
    public void directedComp1DegreeTest() {

        DirectedGraph g = buildComp1();
        assertEquals(1, g.outDegree(1));
        assertEquals(2, g.inDegree(1));
        assertEquals(3, g.outDegree(2));
        assertEquals(1, g.inDegree(2));
        assertEquals(2, g.outDegree(4));
        assertEquals(2, g.inDegree(4));
        assertEquals(0, g.outDegree(7));
        assertEquals(1, g.inDegree(7));
        assertEquals(1, g.outDegree(6));
        assertEquals(2, g.inDegree(6));

    }

    @Test
    public void undirectedComp2DegreeTest() {

        UndirectedGraph g = buildComp2();
        assertEquals(2, g.outDegree(1));
        assertEquals(2, g.inDegree(1));
        assertEquals(3, g.outDegree(2));
        assertEquals(3, g.inDegree(2));
        assertEquals(3, g.outDegree(4));
        assertEquals(3, g.inDegree(4));
        assertEquals(1, g.outDegree(7));
        assertEquals(1, g.inDegree(7));
        assertEquals(3, g.outDegree(6));
        assertEquals(3, g.inDegree(6));

    }

    @Test
    public void directedComp1ContainsTest() {

        DirectedGraph g = buildComp1();
        assertTrue(g.contains(1));
        assertTrue(g.contains(2));
        assertTrue(g.contains(4));
        assertTrue(g.contains(7));
        assertTrue(g.contains(6));

        assertTrue(g.contains(1, 2));
        assertTrue(g.contains(2, 1));
        assertTrue(g.contains(6, 1));
        assertFalse(g.contains(1, 6));
        assertTrue(g.contains(2, 3));
        assertTrue(g.contains(2, 4));
        assertFalse(g.contains(4, 2));
        assertTrue(g.contains(4, 4));
        assertTrue(g.contains(4, 5));
        assertFalse(g.contains(5, 4));

    }

    @Test
    public void undirectedComp2ContainsTest() {

        UndirectedGraph g = buildComp2();
        assertTrue(g.contains(1));
        assertTrue(g.contains(2));
        assertTrue(g.contains(4));
        assertTrue(g.contains(7));
        assertTrue(g.contains(6));

        assertTrue(g.contains(1, 2));
        assertTrue(g.contains(2, 1));
        assertTrue(g.contains(6, 1));
        assertTrue(g.contains(1, 6));
        assertTrue(g.contains(2, 3));
        assertTrue(g.contains(2, 4));
        assertTrue(g.contains(4, 2));
        assertTrue(g.contains(4, 4));
        assertTrue(g.contains(4, 5));
        assertTrue(g.contains(5, 4));

    }

    @Test
    public void directedComp1AddRemoveTest() {

        DirectedGraph g = buildComp1();
        g.remove(2);
        assertEquals(6, g.vertexSize());
        assertEquals(6, g.edgeSize());
        assertFalse(g.contains(1, 2));
        assertFalse(g.contains(2, 1));
        assertFalse(g.contains(2, 3));
        assertFalse(g.contains(2, 4));
        assertFalse(g.contains(4, 2));

        g = buildComp1();
        g.remove(1, 2);
        assertTrue(g.contains(2, 1));
        assertFalse(g.contains(1, 2));
        g.add(1, 2);
        assertTrue(g.contains(1, 2));

    }

    @Test
    public void undirectedComp2AddRemoveTest() {

        UndirectedGraph g = buildComp2();
        g.remove(2);
        assertEquals(6, g.vertexSize());
        assertEquals(6, g.edgeSize());
        assertFalse(g.contains(1, 2));
        assertFalse(g.contains(2, 1));
        assertFalse(g.contains(2, 3));
        assertFalse(g.contains(2, 4));
        assertFalse(g.contains(4, 2));

        g = buildComp2();
        g.remove(1, 2);
        assertEquals(8, g.edgeSize());
        assertEquals(7, g.vertexSize());
        assertFalse(g.contains(2, 1));
        assertFalse(g.contains(1, 2));
        g.add(1, 2);
        assertTrue(g.contains(1, 2));
        assertTrue(g.contains(2, 1));

    }

    @Test
    public void directedComp1VerticesIterTest() {

        DirectedGraph g = buildComp1();
        Iteration<Integer> iter = g.vertices();
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 1; i < 8; i += 1) {
            result.add(i);
        }
        ArrayList<Integer> lst = new ArrayList<>();
        while (iter.hasNext()) {
            lst.add(iter.next());
        }
        assertEquals(result, lst);
    }

    @Test
    public void undirectedComp2VerticesIterTest() {

        UndirectedGraph g = buildComp2();
        Iteration<Integer> iter = g.vertices();
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 1; i < 8; i += 1) {
            result.add(i);
        }
        ArrayList<Integer> lst = new ArrayList<>();
        while (iter.hasNext()) {
            lst.add(iter.next());
        }
        assertEquals(result, lst);
    }

    @Test
    public void directedComp1SuccessorsIterTest() {

        DirectedGraph g = buildComp1();
        Iteration<Integer> iter = g.successors(1);
        ArrayList<Integer> result = new ArrayList<>();
        result.add(2);
        ArrayList<Integer> lst = new ArrayList<>();
        while (iter.hasNext()) {
            lst.add(iter.next());
        }
        assertEquals(result, lst);

        iter = g.successors(2);
        result = new ArrayList<>();
        result.add(1);
        result.add(3);
        result.add(4);
        lst = new ArrayList<>();
        while (iter.hasNext()) {
            lst.add(iter.next());
        }
        assertEquals(result, lst);

        iter = g.successors(4);
        result = new ArrayList<>();
        result.add(4);
        result.add(5);
        lst = new ArrayList<>();
        while (iter.hasNext()) {
            lst.add(iter.next());
        }
        assertEquals(result, lst);
    }

    @Test
    public void undirectedComp2SuccessorsIterTest() {

        UndirectedGraph g = buildComp2();
        Iteration<Integer> iter = g.successors(1);
        ArrayList<Integer> result = new ArrayList<>();
        result.add(2);
        result.add(6);
        ArrayList<Integer> lst = new ArrayList<>();
        while (iter.hasNext()) {
            lst.add(iter.next());
        }
        assertEquals(result, lst);

        iter = g.successors(2);
        result = new ArrayList<>();
        result.add(3);
        result.add(4);
        result.add(1);
        lst = new ArrayList<>();
        while (iter.hasNext()) {
            lst.add(iter.next());
        }
        assertEquals(result, lst);

        iter = g.successors(4);
        result = new ArrayList<>();
        result.add(5);
        result.add(4);
        result.add(2);
        lst = new ArrayList<>();
        while (iter.hasNext()) {
            lst.add(iter.next());
        }
        assertEquals(result, lst);

    }

    @Test
    public void directedComp1PredecessorsIterTest() {

        DirectedGraph g = buildComp1();
        Iteration<Integer> iter = g.predecessors(1);
        ArrayList<Integer> result = new ArrayList<>();
        result.add(2);
        result.add(6);
        ArrayList<Integer> lst = new ArrayList<>();
        while (iter.hasNext()) {
            lst.add(iter.next());
        }
        assertEquals(result, lst);

        iter = g.predecessors(2);
        result = new ArrayList<>();
        result.add(1);
        lst = new ArrayList<>();
        while (iter.hasNext()) {
            lst.add(iter.next());
        }
        assertEquals(result, lst);

        iter = g.predecessors(4);
        result = new ArrayList<>();
        result.add(2);
        result.add(4);
        lst = new ArrayList<>();
        while (iter.hasNext()) {
            lst.add(iter.next());
        }
        assertEquals(result, lst);

    }

    @Test
    public void undirectedComp2PredecessorsIterTest() {

        UndirectedGraph g = buildComp2();
        Iteration<Integer> iter = g.predecessors(1);
        ArrayList<Integer> result = new ArrayList<>();
        result.add(2);
        result.add(6);
        ArrayList<Integer> lst = new ArrayList<>();
        while (iter.hasNext()) {
            lst.add(iter.next());
        }
        assertEquals(result, lst);

        iter = g.predecessors(2);
        result = new ArrayList<>();
        result.add(3);
        result.add(4);
        result.add(1);
        lst = new ArrayList<>();
        while (iter.hasNext()) {
            lst.add(iter.next());
        }
        assertEquals(result, lst);

        iter = g.predecessors(4);
        result = new ArrayList<>();
        result.add(5);
        result.add(4);
        result.add(2);
        lst = new ArrayList<>();
        while (iter.hasNext()) {
            lst.add(iter.next());
        }
        assertEquals(result, lst);


    }

    @Test
    public void directedComp1EdgesIterTest() {

        DirectedGraph g = buildComp1();
        Iteration<int[]> iter = g.edges();
        ArrayList<int[]> result = new ArrayList<>();
        ArrayList<int[]> lst = new ArrayList<>();
        while (iter.hasNext()) {
            lst.add(iter.next());
        }
        result.add(new int[]{1, 2});
        result.add(new int[]{2, 1});
        result.add(new int[]{2, 3});
        result.add(new int[]{2, 4});
        result.add(new int[]{3, 6});
        result.add(new int[]{4, 4});
        result.add(new int[]{4, 5});
        result.add(new int[]{5, 6});
        result.add(new int[]{5, 7});
        result.add(new int[]{6, 1});
        assertTrue(compare2dArrays(result, lst));
        g.remove(4);
        assertEquals(6, g.vertexSize());
        assertEquals(7, g.edgeSize());
    }

    @Test
    public void undirectedComp2EdgesIterTest() {

        UndirectedGraph g = buildComp2();
        Iteration<int[]> iter = g.edges();
        ArrayList<int[]> result = new ArrayList<>();
        ArrayList<int[]> lst = new ArrayList<>();
        while (iter.hasNext()) {
            lst.add(iter.next());
        }
        result.add(new int[]{1, 2});
        result.add(new int[]{2, 3});
        result.add(new int[]{2, 4});
        result.add(new int[]{3, 6});
        result.add(new int[]{4, 4});
        result.add(new int[]{4, 5});
        result.add(new int[]{5, 6});
        result.add(new int[]{5, 7});
        result.add(new int[]{6, 1});
        assertTrue(compare2dArrays(result, lst));

    }

    @Test
    public void cantorTest() {

        DirectedGraph g = buildComp1();
        assertEquals(450, g.edgeId(14, 15));
        int[] result = new int[]{14, 15};
        assertArrayEquals(result, g.reverseEdgeId(450));
        assertEquals(449, g.edgeId(15, 14));
        result = new int[]{15, 14};
        assertArrayEquals(result, g.reverseEdgeId(449));

    }

    @Test
    public void directedEdgeIDTest() {

        DirectedGraph g = buildComp1();
        int u = 2;
        int v = 3;
        int id1 = g.edgeId(u, v);
        int id2 = g.edgeId(v, u);
        assertNotEquals(id1, id2);

        u = 5;
        v = 7;
        id1 = g.edgeId(u, v);
        id2 = g.edgeId(v, u);
        assertNotEquals(id1, id2);

    }

    @Test
    public void undirectedEdgeIDTest() {

        UndirectedGraph g = buildComp2();
        int u = 2;
        int v = 3;
        int id1 = g.edgeId(u, v);
        int id2 = g.edgeId(v, u);
        assertEquals(id1, id2);

        u = 5;
        v = 7;
        id1 = g.edgeId(u, v);
        id2 = g.edgeId(v, u);
        assertEquals(id1, id2);

    }

}
