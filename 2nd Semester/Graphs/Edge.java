package Graphs;

import java.util.logging.*;

public class Edge {

  private Vertex start;
  private Vertex end;
  private double weight;

  // LOGGER
  public static Logger LOGGER = Logger.getLogger(Vertex.class.getName());

  /**
   * Edge Constructor
   * @param start the starting vertex
   * @param end the end vertex
   * @param weight the weight of a connection, aka distance.
   */
  public Edge(Vertex start, Vertex end, double weight) {
    this.start = start;
    this.end = end;
    this.weight = weight;
  }

  /**
   * Gets the starting Vertex
   * @return The Starting Vertex
   */
  public Vertex getStart() {
    return this.start;
  }

  /**
   * Gets the End Vertex
   * @return this.end
   */
  public Vertex getEnd() {
    return this.end;
  }

  /**
   * Gets the weight of this connection
   * @return this.weight
   */
  public double getWeight() {
    return this.weight;
  }

  /**
   * Adjusts the weight of a connection
   * @param newWeight the weight to replace the old weight
   */
  public void setWeight(double newWeight) {
    this.weight = newWeight;
  }

  /**
   * toString
   */
  public String toString() {
    return this.getEnd().toString();
  }
}
