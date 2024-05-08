package Graphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.*;

public class PLD { // 🍞th 1️⃣st 🔍
  private Graph map;
  private Vertex start;
  private Queue<Vertex> queue;
  public static Logger LOGGER = Logger.getLogger(Vertex.class.getName());
  private double[] distance;
  private int row;
  private int col;

  private double adjMatrix[][];

  /** */
  public PLD(Graph graph, Vertex start) {
    this.map = graph;
    this.queue = new LinkedList<Vertex>();
    queue.add(start);
    start.isVisited = true;
    this.start = start;
    this.distance = new double[graph.vertices.size()];
    this.row = graph.createAdjacencyMatrix().length;
    this.col = graph.createAdjacencyMatrix()[0].length;
    this.adjMatrix = graph.createAdjacencyMatrix();
  }

  public boolean hasPath(Vertex end) {
    while (this.queue.size() > 0) {
      Vertex n = this.queue.remove();
      // System.out.print(n + "->");
      for (Vertex vertex : n.getList()) {
        if (vertex.getData().equals(end.getData())) {
          // System.out.println(end);
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

  public void updateNeighbors() {
    for (Vertex vert : this.map.vertices) {
      int v = map.indexOf(vert);
      System.out.println("v" + v);
      System.out.println("this.row"+this.row);
      if (this.row > this.map.createAdjacencyMatrix().length - 1) {
        continue;
      }
      if (!vert.isVisited
          && this.adjMatrix[this.row][v] != 0
          && (this.distance[this.row] + this.adjMatrix[this.row][v] < this.distance[v]))
        distance[v] = distance[this.row] + this.adjMatrix[this.row][v];
    }
  }

  public ArrayList<Vertex> getNeighbors(Vertex start) {
    ArrayList<Vertex> neighbors = new ArrayList<Vertex>();
    int index = this.map.indexOf(start);
    double[][] adj = this.map.createAdjacencyMatrix();
    for (int i = 0; i < start.edges.size(); i++) {
      if (adj[index][i] != 0) neighbors.add(this.map.vertices.get(i));
    }
    return neighbors;
  }

  public int findSource() {
    return this.map.indexOf(this.start);
  }

  public int findMinDistance() {
    double minDistance = Double.MAX_VALUE;
    int minDistanceVertex = 0;
    int i = 0;
    for (Vertex v : this.map.vertices) {
      if (!v.isVisited && distance[i] < minDistance) {
        minDistance = distance[i];
        minDistanceVertex = i;
      }
      i += 1;
    }
    return minDistanceVertex;
  }

  public void findPLD() {
    int src = findSource();
    int i = 0;
    for (Vertex v : this.map.vertices) {
      v.isVisited = false;
      distance[i] = Double.MAX_VALUE;
      i++;
    }

    // Distance of Self Loop is 0
    this.distance[src] = 0;
    for (Vertex v : this.map.vertices) {
      // Update Distance between neighboring vertex and source
      this.row = findMinDistance();
      v.isVisited = true;

      // Update all neighboring vertex distances
      this.updateNeighbors();
    }
  }

  /**
   * Returns the Path of Least distance from the start to each node
   *
   * @return String representation of the Path of Least Distance
   */
  public String toString() {
    String s = "";
    this.findPLD();
    for (int i = 0; i < this.distance.length; i++) {
      s += this.map.vertices.get(i) + " | " + this.distance[i] + "\n";
    }
    return s;
  }
}
