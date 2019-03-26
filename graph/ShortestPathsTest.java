package graph;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.*;

public class ShortestPathsTest {

    public class ShortestExtender extends SimpleShortestPaths {

        HashMap<Integer, Integer> _edgeMap;

        ShortestExtender(Graph G, int source, int dest) {
            super(G, source, dest);
            _edgeMap = new HashMap<>();
            int[] edges = {

                    edgeId(1, 2),
                    edgeId(2, 1),
                    edgeId(2, 3),
                    edgeId(2, 4),
                    edgeId(3, 6),
                    edgeId(4, 4),
                    edgeId(4, 5),
                    edgeId(5, 6),
                    edgeId(5, 7),
                    edgeId(6, 1),
            };
            _edgeMap.put(edges[0], 5);
            _edgeMap.put(edges[1], 12);
            _edgeMap.put(edges[2], 1);
            _edgeMap.put(edges[3], 3);
            _edgeMap.put(edges[4], 9);
            _edgeMap.put(edges[5], 1);
            _edgeMap.put(edges[6], 6);
            _edgeMap.put(edges[7], 4);
            _edgeMap.put(edges[8], 8);
            _edgeMap.put(edges[9], 2);
        }


        public double getWeight(int u, int v) {

            return _edgeMap.get(edgeId(u, v));
        }
    }


    protected int edgeId(int u, int v) {

        return (((u + v) * (u + v + 1)) / 2) + v;

    }


    public static DirectedGraph buildComp3Isolated() {
        DirectedGraph comp3 = new DirectedGraph();
        comp3.add();
        comp3.add();
        comp3.add();
        comp3.add();
        comp3.add();
        comp3.add();
        comp3.add();
        comp3.add();
        comp3.add(1, 2);
        comp3.add(2, 1);
        comp3.add(2, 3);
        comp3.add(2, 4);
        comp3.add(3, 6);
        comp3.add(4, 4);
        comp3.add(4, 5);
        comp3.add(5, 6);
        comp3.add(5, 7);
        comp3.add(6, 1);

        return comp3;

    }

    public static UndirectedGraph buildComp4Isolated() {
        UndirectedGraph comp4 = new UndirectedGraph();
        comp4.add();
        comp4.add();
        comp4.add();
        comp4.add();
        comp4.add();
        comp4.add();
        comp4.add();
        comp4.add();
        comp4.add(1, 2);
        comp4.add(2, 1);
        comp4.add(2, 3);
        comp4.add(2, 4);
        comp4.add(3, 6);
        comp4.add(4, 4);
        comp4.add(4, 5);
        comp4.add(5, 6);
        comp4.add(5, 7);
        comp4.add(6, 1);

        return comp4;

    }

    @Test
    public void shortestPathsDirectedTest() {

        DirectedGraph comp3 = buildComp3Isolated();
        ShortestExtender s = new ShortestExtender(comp3, 1, 7);
        s.setPaths();
        assertEquals(5.0, s.getWeight(1, 2), .001);
        assertEquals(12.0, s.getWeight(2, 1), .001);
        assertEquals(1.0, s.getWeight(2, 3), .001);
        assertEquals(3.0, s.getWeight(2, 4), .001);
        assertEquals(9.0, s.getWeight(3, 6), .001);
        assertEquals(1.0, s.getWeight(4, 4), .001);
        assertEquals(6.0, s.getWeight(4, 5), .001);
        assertEquals(4.0, s.getWeight(5, 6), .001);
        assertEquals(8.0, s.getWeight(5, 7), .001);
        assertEquals(2.0, s.getWeight(6, 1), .001);

        ArrayList<Integer> result = new ArrayList<>();
        result.add(1); result.add(2); result.add(3); result.add(6);
        assertEquals(result, s.pathTo(6));
        result.clear();
        result.add(1); result.add(2); result.add(4);
        result.add(5); result.add(7);
        assertEquals(result, s.pathTo(7));

    }

    @Test
    public void shortestPathsUndirectedTest() {
        DirectedGraph comp3 = buildComp3Isolated();
        ShortestExtender s = new ShortestExtender(comp3, 1, 7);
        s.setPaths();
        assertEquals(12.0, s.getWeight(2, 1), .001);
        assertEquals(5.0, s.getWeight(1, 2), .001);
        assertEquals(1.0, s.getWeight(2, 3), .001);
        assertEquals(4.0, s.getWeight(5, 6), .001);
        assertEquals(8.0, s.getWeight(5, 7), .001);
        assertEquals(2.0, s.getWeight(6, 1), .001);
        assertEquals(3.0, s.getWeight(2, 4), .001);
        assertEquals(9.0, s.getWeight(3, 6), .001);
        assertEquals(1.0, s.getWeight(4, 4), .001);
        assertEquals(6.0, s.getWeight(4, 5), .001);


        ArrayList<Integer> result = new ArrayList<>();
        result.add(1); result.add(2); result.add(3); result.add(6);
        assertEquals(result, s.pathTo(6));
        result.clear();
        result.add(1); result.add(2); result.add(4);
        result.add(5); result.add(7);
        assertEquals(result, s.pathTo(7));
    }
}
