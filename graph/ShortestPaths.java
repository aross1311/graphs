package graph;

/* See restrictions in Graph.java. */
import java.util.TreeSet;
import java.util.Comparator;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

/** The shortest paths through an edge-weighted graph.
 *  By overrriding methods getWeight, setWeight, getPredecessor, and
 *  setPredecessor, the client can determine how to represent the weighting
 *  and the search results.  By overriding estimatedDistance, clients
 *  can search for paths to specific destinations using A* search.
 *  @author A.R. LOEFFLER
 */
public abstract class ShortestPaths {

    /** The shortest paths in G from SOURCE. */
    public ShortestPaths(Graph G, int source) {
        this(G, source, 0);
    }

    /** A shortest path in G from SOURCE to DEST. */
    public ShortestPaths(Graph G, int source, int dest) {
        _G = G;
        _source = source;
        _dest = dest;
        _vertWeights = new double[_G.maxVertex() + 1];
        _edgeTo = new int[_G.maxVertex() + 1];
        for (int n = 0; n < _vertWeights.length; n += 1) {
            _vertWeights[n] = Double.POSITIVE_INFINITY;
            _edgeTo[n] = 0;
        }
        _myComparator = new MyCompClass();
        _fringe = new TreeSet<>(_myComparator);

    }

    /** Initialize the shortest paths.  Must be called before using
     *  getWeight, getPredecessor, and pathTo. */
    public void setPaths() {
        _fringe.clear();
        Iteration<Integer> myVerts = _G.vertices();
        while (myVerts.hasNext()) {
            _vertWeights[myVerts.next()] = Double.POSITIVE_INFINITY;
        }
        _vertWeights[_source] = 0;
        _fringe.add(_source);
        while (!_fringe.isEmpty()) {
            int v = _fringe.pollFirst();
            if (v == _dest) {
                return;
            }
            Iteration<Integer> vSuccessors = _G.successors(v);
            while (vSuccessors.hasNext()) {
                int currVert = vSuccessors.next();
                if ((getWeight(v) + getWeight(v, currVert)
                        < getWeight(currVert))) {
                    setWeight(currVert, getWeight(v) + getWeight(v, currVert));
                    _fringe.add(currVert);
                    setPredecessor(currVert, v);
                }
            }
        }
    }

    /** Returns the starting vertex. */
    public int getSource() {
        return _source;
    }

    /** Returns the target vertex, or 0 if there is none. */
    public int getDest() {
        return _dest;
    }

    /** Returns the current weight of vertex V in the graph.  If V is
     *  not in the graph, returns positive infinity. */
    public abstract double getWeight(int v);

    /** Set getWeight(V) to W. Assumes V is in the graph. */
    protected abstract void setWeight(int v, double w);

    /** Returns the current predecessor vertex of vertex V in the graph, or 0 if
     *  V is not in the graph or has no predecessor. */
    public abstract int getPredecessor(int v);

    /** Set getPredecessor(V) to U. */
    protected abstract void setPredecessor(int v, int u);

    /** Returns an estimated heuristic weight of the shortest path from vertex
     *  V to the destination vertex (if any).  This is assumed to be less
     *  than the actual weight, and is 0 by default. */
    protected double estimatedDistance(int v) {
        return 0.0;
    }

    /** Returns the current weight of edge (U, V) in the graph.  If (U, V) is
     *  not in the graph, returns positive infinity. */
    protected abstract double getWeight(int u, int v);

    /** Returns a list of vertices starting at _source and ending
     *  at V that represents a shortest path to V.  Invalid if there is a
     *  destination vertex other than V. */
    public List<Integer> pathTo(int v) {

        List<Integer> result = new ArrayList<>();
        result.add(v);
        int lastEdge = getPredecessor(v);
        while (lastEdge != 0) {
            result.add(lastEdge);
            lastEdge = getPredecessor(lastEdge);
        }
        Collections.reverse(result);
        return result;
    }

    /** Returns a list of vertices starting at the source and ending at the
     *  destination vertex. Invalid if the destination is not specified. */
    public List<Integer> pathTo() {
        return pathTo(getDest());
    }

    /** Class for the comparator. */
    private class MyCompClass implements Comparator<Integer> {

        @Override
        public int compare(Integer o1, Integer o2) {
            double a = estimatedDistance(o1) + getWeight(o1);
            double b = estimatedDistance(o2) + getWeight(o2);
            if (a > b) {
                return 1;
            }
            return -1;
        }
    }

    /** Returns my graph. */
    private Graph getG() {
        return _G;
    }

    /** Returns my vert weight array. */
    protected double[] getVertWeights() {
        return _vertWeights;
    }

    /** Returns my edge to array. */
    protected int[] getEdgeTo() {
        return _edgeTo;
    }

    /** Returns my fringe. */
    public TreeSet<Integer> getFringe() {
        return _fringe;
    }


    /** The graph being searched. */
    protected final Graph _G;
    /** The starting vertex. */
    private final int _source;
    /** The target vertex. */
    private final int _dest;
    /** My 2d vert weight array. */
    private double[] _vertWeights;
    /** My edge to array. */
    private int[] _edgeTo;
    /** My fringe. */
    private TreeSet<Integer> _fringe;
    /** My comparator. */
    private Comparator<Integer> _myComparator;

}
