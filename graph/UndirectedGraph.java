package graph;

/* See restrictions in Graph.java. */

import java.util.ArrayList;
import java.util.Collections;

/** Represents an undirected graph.  Out edges and in edges are not
 *  distinguished.  Likewise for successors and predecessors.
 *
 *  @author A.R. LOEFFLER
 */
public class UndirectedGraph extends GraphObj {

    @Override
    public boolean isDirected() {
        return false;
    }

    @Override
    public int inDegree(int v) {

        return totalDegree(v);

    }

    @Override
    public int outDegree(int v) {

        return totalDegree(v);

    }

    @Override
    public boolean contains(int u, int v) {
        int ind = 0;
        for (var n : getVertices()) {
            if ((u == ind) && (getVertices().get(ind) != null)) {
                for (var m : n) {
                    if (m == v) {
                        return true;
                    }
                }
            }
            if ((v == ind) && (getVertices().get(ind) != null)) {
                for (var m : n) {
                    if (m == u) {
                        return true;
                    }
                }
            }
            ind += 1;
        }

        return false;
    }

    @Override
    public void remove(int v) {
        checkMyVertex(v);
        int toRemove = this.inDegree(v);
        getVertices().set(v, null);
        int ind = 0;
        for (var n : getVertices()) {
            if (n != null) {
                int mInd = 0;
                for (var m : new ArrayList<>(getVertices().get(ind))) {
                    if (m == v) {
                        getVertices().get(ind).remove(mInd);
                    }

                    mInd += 1;
                }
            }

            ind += 1;
        }

        reduceEdges(toRemove);
        reduceVertices(1);
    }

    @Override
    public void remove(int u, int v) {
        checkMyVertex(u);
        checkMyVertex(v);
        int ind = 0;
        boolean found = false;
        boolean removed = false;
        for (var n : getVertices()) {
            if (u == ind) {
                int pos = 0;
                for (var m : n) {
                    if (m == v) {
                        found = true;
                        break;
                    }
                    pos += 1;
                }
                if (found) {
                    n.remove(pos);
                    removed = true;
                    break;
                }

            } else if (v == ind) {
                int pos = 0;
                for (var m : n) {
                    if (m == u) {
                        found = true;
                        break;
                    }
                    pos += 1;
                }
                if (found) {
                    n.remove(pos);
                    removed = true;
                    break;
                }

            }
            ind += 1;
        }

        if (removed) {
            reduceEdges(1);
        }
    }

    @Override
    protected int edgeId(int u, int v) {

        int lower = Math.min(u, v);
        int higher = Math.max(u, v);
        return (((lower + higher) * (lower + higher + 1)) / 2) + higher;

    }

    /** Returns degree in/out for undirected graph from node V. */
    private int totalDegree(int v) {
        if (!contains(v)) {
            return 0;
        }
        int total = 0;
        for (var m : getVertices().get(v)) {
            total += 1;
        }
        int ind = 0;
        for (var n : getVertices()) {
            if (ind != v && n != null) {
                for (var m : n) {
                    if (m == v) {
                        total += 1;
                    }
                }
            }
            ind += 1;
        }

        return total;
    }

    @Override
    public Iteration<Integer> successors(int v) {

        return allNeighbors(v);
    }

    @Override
    public Iteration<Integer> predecessors(int v) {

        return allNeighbors(v);

    }

    /** Returns all neighbors of V. */
    private Iteration<Integer> allNeighbors(int v) {
        if (!contains(v)) {
            return Iteration.iteration(Collections.emptyList());
        }

        ArrayList<Integer> allSuccessors = new ArrayList<>();
        allSuccessors.addAll(getVertices().get(v));
        int ind = 0;
        for (var n : getVertices()) {
            if (n != null) {
                for (var m : n) {
                    if (m == v && m != ind) {
                        allSuccessors.add(ind);
                    }
                }
            }
            ind += 1;
        }
        return Iteration.iteration(allSuccessors);
    }

}
