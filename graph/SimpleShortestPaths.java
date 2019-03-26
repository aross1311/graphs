package graph;

/* See restrictions in Graph.java. */

/** A partial implementation of ShortestPaths that contains the weights of
 *  the vertices and the predecessor edges.   The client needs to
 *  supply only the two-argument getWeight method.
 *  @author A.R. LOEFFLER
 */
public abstract class SimpleShortestPaths extends ShortestPaths {

    /** The shortest paths in G from SOURCE. */
    public SimpleShortestPaths(Graph G, int source) {
        this(G, source, 0);
    }

    /** A shortest path in G from SOURCE to DEST. */
    public SimpleShortestPaths(Graph G, int source, int dest) {
        super(G, source, dest);
    }

    /** Returns the current weight of edge (U, V) in the graph.  If (U, V) is
     *  not in the graph, returns positive infinity. */
    @Override
    protected abstract double getWeight(int u, int v);

    @Override
    public double getWeight(int v) {
        return getVertWeights()[v];
    }

    @Override
    protected void setWeight(int v, double w) {
        getVertWeights()[v] = w;
    }

    @Override
    public int getPredecessor(int v) {
        return getEdgeTo()[v];
    }

    @Override
    protected void setPredecessor(int v, int u) {
        getEdgeTo()[v] = u;
    }

    /** Returns my graph. */
    public Graph getG() {
        return _G;
    }

}
