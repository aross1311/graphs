package graph;

/* See restrictions in Graph.java. */

import java.util.ArrayList;
import java.util.Collections;

/** Represents a general unlabeled directed graph whose vertices are denoted by
 *  positive integers. Graphs may have self edges.
 *
 *  @author A.R. LOEFFLER
 */
public class DirectedGraph extends GraphObj {

    @Override
    public boolean isDirected() {
        return true;
    }

    @Override
    public int inDegree(int v) {
        int total = 0;
        if (!contains(v)) {
            return 0;
        }
        for (var n : getVertices()) {
            if (n != null) {
                for (var m : n) {
                    if (m == v) {
                        total += 1;
                    }
                }
            }
        }

        return total;
    }

    @Override
    public Iteration<Integer> predecessors(int v) {
        if (!contains(v)) {
            return Iteration.iteration(Collections.emptyList());
        }

        ArrayList<Integer> preds = new ArrayList<>();
        int ind = 0;
        for (var n : getVertices()) {
            if (n != null) {
                for (var m : n) {
                    if (m == v) {
                        preds.add(ind);
                    }
                }
            }
            ind += 1;
        }

        return Iteration.iteration(preds);

    }

}
