package graph;

/* See restrictions in Graph.java. */

import java.util.ArrayList;
import java.util.Collections;

/** A partial implementation of Graph containing elements common to
 *  directed and undirected graphs.
 *
 *  @author A.R. LOEFFLER
 */
abstract class GraphObj extends Graph {

    /** A new, empty Graph. */
    GraphObj() {
        _vertices = new ArrayList<>();
        _vertices.add(null);
        _numVertices = 0;
        _numEdges = 0;
    }

    @Override
    public int vertexSize() {
        return _numVertices;
    }

    @Override
    public int maxVertex() {
        int largest = 0;
        int ind = 0;
        for (var v : _vertices) {
            if (v != null) {
                largest = ind;
            }
            ind += 1;
        }

        return largest;
    }

    @Override
    public int edgeSize() {

        return _numEdges;
    }

    @Override
    public abstract boolean isDirected();

    @Override
    public int outDegree(int v) {
        int ind = 0;
        if (v > maxVertex()) {
            return 0;
        }
        for (var n : _vertices) {
            if (v == ind) {
                if (n == null) {
                    return 0;
                }
                return n.size();
            }
            ind += 1;
        }

        return -1;
    }

    @Override
    public abstract int inDegree(int v);

    @Override
    public boolean contains(int u) {
        int ind = 0;
        for (var v : _vertices) {
            if (u == ind) {
                if (v == null) {
                    return false;
                }
                return true;
            }
            ind += 1;
        }
        return false;
    }

    @Override
    public boolean contains(int u, int v) {
        boolean ucheck = false;
        int ind = 0;
        for (var n : _vertices) {
            if (ucheck) {
                return false;
            }
            if ((u == ind) && (_vertices.get(ind) != null)) {
                ucheck = true;
                for (var m : n) {
                    if (m == v) {
                        return true;
                    }
                }
            }
            ind += 1;
        }

        return false;
    }

    @Override
    public int add() {

        int ind = 0;
        boolean added = false;
        for (var v : _vertices) {
            if (ind != 0) {
                if (v == null) {
                    _vertices.set(ind,
                            new ArrayList<>(Collections.emptyList()));
                    added = true;
                    break;
                }
            }
            ind += 1;
        }
        if (!added) {
            _vertices.add(new ArrayList<>(Collections.emptyList()));
        }
        _numVertices += 1;
        return ind;
    }

    @Override
    public int add(int u, int v) {
        checkMyVertex(u);
        checkMyVertex(v);
        if (!_vertices.get(u).contains((v))) {
            _vertices.get(u).add(v);
            _numEdges += 1;
        }

        return edgeId(u, v);

    }

    @Override
    public void remove(int v) {
        checkMyVertex(v);
        int toRemove = this.inDegree(v) + this.outDegree(v);
        for (var m : _vertices.get(v)) {
            if (m == v) {
                toRemove -= 1;
            }
        }
        _vertices.set(v, null);
        int ind = 0;
        for (var n : _vertices) {
            if (n != null) {
                int mInd = 0;
                for (var m : new ArrayList<>(_vertices.get(ind))) {
                    if (m == v) {
                        _vertices.get(ind).remove(mInd);
                    }
                    mInd += 1;
                }
            }
            ind += 1;
        }

        _numEdges -= toRemove;
        _numVertices -= 1;
    }

    @Override
    public void remove(int u, int v) {
        checkMyVertex(u);
        checkMyVertex(v);
        int ind = 0;
        boolean found = false;
        boolean removed = false;
        for (var n : _vertices) {
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
                }

            }
            ind += 1;
        }
        if (removed) {
            _numEdges -= 1;
        }
    }

    @Override
    public Iteration<Integer> vertices() {

        return Iteration.iteration(getVerticesList());

    }

    @Override
    public Iteration<Integer> successors(int v) {
        if (!contains(v)) {
            return Iteration.iteration(Collections.emptyList());
        }

        return Iteration.iteration(_vertices.get(v));

    }

    @Override
    public abstract Iteration<Integer> predecessors(int v);

    @Override
    public Iteration<int[]> edges() {

        int ind = 0;
        ArrayList<int[]> pairs = new ArrayList<>();
        for (var n : _vertices) {
            if (n != null && ind != 0) {
                for (var m : n) {
                    int[] pair = new int[]{ind, m};
                    pairs.add(pair);
                }
            }
            ind += 1;
        }

        return Iteration.iteration(pairs);
    }

    @Override
    protected void checkMyVertex(int v) {
        if (!contains(v)) {
            throw new IllegalArgumentException("vertex not from Graph");
        }
    }

    @Override
    protected int edgeId(int u, int v) {

        return (((u + v) * (u + v + 1)) / 2) + v;

    }

    /** Returns int array of reversed edgeID pair from Z. */
    public int[] reverseEdgeId(int z) {
        double w = java.lang.StrictMath.floor(
                (java.lang.StrictMath.sqrt(8 * z + 1) - 1) / 2);
        double t = (Math.pow(w, 2) + w) / 2;
        double y = z - t;
        double x = w - y;
        return new int[]{(int) x, (int) y};

    }

    /** Returns array list of vertices. */
    public ArrayList<Integer> getVerticesList() {
        ArrayList<Integer> verts = new ArrayList<>();
        int ind = 0;
        for (var n : _vertices) {
            if (n != null) {
                verts.add(ind);
            }
            ind += 1;
        }

        return verts;
    }

    /** Returns my Array List of vertices. */
    protected ArrayList<ArrayList<Integer>> getVertices() {
        return _vertices;
    }

    /** Reduces my number of vertices by N. */
    protected void reduceVertices(int n) {
        _numVertices -= n;
    }

    /** Reduces my number of edges by N. */
    protected void reduceEdges(int n) {
        _numEdges -= n;
    }

    /** ArrayList of my vertices. */
    private ArrayList<ArrayList<Integer>> _vertices;
    /** My number of vertices. */
    private int _numVertices;
    /** My number of edges. */
    private int _numEdges;

}
