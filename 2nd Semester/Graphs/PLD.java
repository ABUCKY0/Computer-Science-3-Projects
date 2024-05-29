package Graphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.*;

public class PLD { // 🍞th 1️⃣st 🔍
  private Graph map;
  public Vertex start;
  private Queue<Vertex> queue;
  public static Logger LOGGER = Logger.getLogger(Vertex.class.getName());
  private double[] distance;
  private int u;
  private int count;
  private double adjMatrix[][];
  private ArrayList<Vertex> vertices;
  private boolean[] visitedVertex;

  /**
   * PLD Constructor
   *
   * @param graph Graph to perform the PLD on
   * @param start the starting node
   */
  public PLD(Graph graph, Vertex start) {
    this.start = start;
    this.map = graph;
    this.vertices = this.map.vertices;
    this.adjMatrix = this.map.createAdjacencyMatrix();
    this.queue = new LinkedList<Vertex>();
    queue.add(this.start);
    this.count = this.vertices.size();
    this.distance = new double[this.count];
    this.visitedVertex = new boolean[this.count];
    start.isVisited = true;
  }

  /**
   * Checks if there is a path from this.start to the end node
   *
   * @param end The end Vertex
   * @return boolean true if there is a path, false otherwise
   */
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

  /**
   * Update the distance array with distances
   */
  public void updateNeighbors() {

    for (int v = 0; v < count; v++) {
      if (!this.visitedVertex[v]
          && this.adjMatrix[this.u][v] != 0
          && (this.distance[this.u] + this.adjMatrix[this.u][v] < this.distance[v]))
        distance[v] = distance[this.u] + this.adjMatrix[this.u][v];
    }
  }

  /**
   * Gets the neighbors of a vertex
   * @param start Vertex to get the neighbors for
   * @return the neighbors
   */
  public ArrayList<Vertex> getNeighbors(Vertex start) {
    ArrayList<Vertex> neighbors = new ArrayList<Vertex>();
    int index = this.map.indexOf(start);
    double[][] adj = this.map.createAdjacencyMatrix();
    for (int i = 0; i < start.edges.size(); i++) {
      if (adj[index][i] != 0) neighbors.add(this.map.vertices.get(i));
    }
    return neighbors;
  }

  /**
   * Finds the index of this.start
   * @return int index of this.start in the map
   */
  public int findSource() {
    return this.map.indexOf(this.start);
  }

  
  public int findMinDistance() {
    double minDistance = Double.MAX_VALUE;
    int minDistanceVertex = -1;
    for (int i = 0; i < this.distance.length; i++) {
      if (!visitedVertex[i] && distance[i] < minDistance) {
        minDistance = distance[i];
        minDistanceVertex = i;
      }
    }
    return minDistanceVertex;
  }

  public void findPLD() { // the method that does all the work of finding the PLD
    int src = findSource();
    for (int i = 0; i < this.count; i++) {
      visitedVertex[i] = false;
      distance[i] = Double.MAX_VALUE;
    }
    // Distance of self loop is zero
    this.distance[src] = 0;
    for (int i = 0; i < this.count; i++) {

      // Update the distance between neighbouring vertex and source vertex
      this.u = findMinDistance();
      visitedVertex[u] = true;

      // Update all the neighbouring vertex distances
      updateNeighbors();
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
