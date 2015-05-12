package com.brayden;

import java.util.ArrayList;
import java.util.List;

public class LeetcodeGraph extends LeetcodeCommon {

	class UndirectedGraphNode {
		int label;
		List<UndirectedGraphNode> neighbors;

		UndirectedGraphNode(int x) {
			label = x;
			neighbors = new ArrayList<UndirectedGraphNode>();
		}

		@Override
		public boolean equals(Object p) {
			if (!(p instanceof UndirectedGraphNode))
				return false;
			
			return label == ((UndirectedGraphNode)p).label;
		}

		@Override
		public int hashCode() {
			return label;
		}

        @Override
        public String toString() {
            return "UndirectedGraphNode [label=" + label + ", neighbors="
                    + neighbors + "]";
        }
	}

	protected UndirectedGraphNode newNode(int label) {
		return new UndirectedGraphNode(label);
	}

}
