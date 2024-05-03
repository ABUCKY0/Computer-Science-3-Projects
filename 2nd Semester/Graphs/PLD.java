package Graphs;

import java.util.Queue;
import java.util.LinkedList;
import java.util.logging.*;
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
}
