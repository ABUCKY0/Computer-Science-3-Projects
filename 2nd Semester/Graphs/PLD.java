package Graphs;

import java.util.Queue;
import java.util.LinkedList;
import java.util.logging.*;
import java.util.ArrayList;
public class PLD { // 🍞th 1️⃣st 🔍
  private Graph map;
  private Vertex v;
  private Queue<Vertex> queue;
  public static Logger LOGGER = Logger.getLogger(Vertex.class.getName());

  public PLD(Graph graph, Vertex start) {
    this.map = graph;
    this.queue = new LinkedList<Vertex>();
    queue.add(start);
    start.isVisited = true;
  }

  public boolean hasPath(Vertex end) {
    while (this.queue.size() > 0) {
      Vertex n = this.queue.remove();
      //System.out.print(n + "->");
      for (Vertex vertex: n.getList()) {
        if (vertex.getData().equals(end.getData())) {
          //System.out.println(end);
          vertex.isVisited = true;
          return true;
        }
        if (vertex.isVisited == false) {
          vertex.isVisited = true;
          this.queue.add(vertex);
        }
      }
    }
    return false;
  }

  public ArrayList<Vertex> getNeighbors(Vertex start){
    ArrayList<Vertex> neighbors = new ArrayList<Vertex>();
    int index = this.map.indexOf(start);
    double[][] adj = this.map.createAdjacencyMatrix();
    for(int i=0; i<start.edges.size(); i++){
        if(adj[index][i] !=0)
            neighbors.add(this.map.vertices.get(i));
    }
    return neighbors;
}
}
