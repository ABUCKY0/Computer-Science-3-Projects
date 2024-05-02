package Graphs;

import java.util.ArrayList;
import java.util.logging.*;

public class Vertex {
  private String data;
  public boolean isVisited;
  public ArrayList<Edge> edges;

  // logger
  public static Logger LOGGER = Logger.getLogger(Vertex.class.getName());

  /**
   * Creates a new Vertex
   *
   * @param data String data for the vertex
   */
  public Vertex(String data) {
    this.data = data;
    this.edges = new ArrayList<>();
    this.isVisited = false;
  }

  /**
   * Gets the data of this Vertex
   *
   * @return this.data
   */
  public String getData() {
    return this.data;
  }

  /**
   * Data Setter
   * 
   * @param data the data to replace the current data with
   */
  public void setData(String data) {
    this.data = data;
  }

  /**
   * Returns true or false if the graph has visited this before. 
   * @return isVisited
   */
  public boolean isVisited() {
    return this.isVisited;
  }

  /**
   * Adds a Connection
   * @param city: Vertex city ta connect to
   * @param weight: weight of the connetion
   */
  public void addConnection(Vertex city, double weight) {
    this.edges.add(new Edge(this, city, weight));
    city.edges.add(new Edge(city, this, weight));
  }

  /**
   * Gets the Amount of Connections
   * @return edges array size
   */
  public int getAmountOfConnections() {
    return this.edges.size();
  }

  /**
   * Sets the boolean visited
   * @param isVisited boolean
   */
  public void setVisited(boolean isVisited) {
    this.isVisited = isVisited;
  }

  /**
   * Get Edge Index
   * @param e Edge to get index of
   * @return index of edge, or -1 if not found
   */
  public int getEdgeIndex(Edge e) {
    for (int i = 0; i < edges.size(); i++) {
      if (edges.get(i).getEnd().getData().equals(e.getEnd().getData())) {
        return i;
      }
    }
    return -1;
  }

  /**
   * Gets the Edge index of a Vertex
   * @param end Vertex to find in the edges
   * @return index of the first edge containing this index
   */
  public int getEdgeIndex(Vertex end) {
    for (int i = 0; i < edges.size(); i++) {
      if (edges.get(i).getEnd().getData().equals(end.getData())) {
        return i;
      }
    }
    return -1;
  }

  /**
   * Gets a Adjacency List of the Edges
   * @return
   */
  public ArrayList<Vertex> getList() {
    ArrayList<Vertex> list = new ArrayList<Vertex>();
    for (Edge e: this.edges) {
      list.add(e.getEnd());
    }
    return list;
  }

  /**
   * Checks if this Vertex has a Connection to a specified node
   */
  public boolean hasConnection(Vertex end) {
    ArrayList<Vertex> connections = this.getList();
    return connections.contains(end);
  }

  /**
   * Gets the weight of a connection
   * @param end
   * @return The weight of a connection
   */
  public double getConnectionWeight(Vertex end) {
    for (Edge victim: this.edges) {
      if (victim.getEnd().getData().equals(end.getData())) {
        return victim.getWeight();
      }
    }
    return 0;
  }

  /**
   * toString
   * 
   * @return string representation of Vertex
   */
  public String toString() {
    return this.data;
  }
}
