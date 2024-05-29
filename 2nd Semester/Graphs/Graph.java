package Graphs;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import java.util.logging.*;

public class Graph {
  public ArrayList<Vertex> vertices;
  public static Logger LOGGER = Logger.getLogger(Vertex.class.getName());

  /** Graph Constructor */
  public Graph() {
    this.vertices = new ArrayList<Vertex>();
  }

  /**
   * Graph Constructor with Vertices
   *
   * @param vertices
   */
  public Graph(ArrayList<Vertex> vertices) {
    this.vertices = vertices;
  }

  /**
   * Adds a Vertex
   *
   * @param city (string name) to add
   */
  public void addVertex(String city) {
    this.vertices.add(new Vertex(city));
  }

  /**
   * Adds a Vertex
   *
   * @param city vertex to add
   */
  public void addVertex(Vertex city) {
    this.vertices.add(city);
  }

  /**
   * Adds an Edge/Connection
   *
   * @param city1 home City to Connect
   * @param city2 remote city to connect
   * @param weight weight of the connection
   */
  public void addConnection(Vertex city1, Vertex city2, double weight) {
    city1.addConnection(city2, weight);
  }

  /**
   * Adds a connection from a connection
   *
   * @param connection the connection to pick apart, and add to both sides
   */
  public void addConnection(Edge connection) {
    Vertex city1 = connection.getStart();
    Vertex city2 = connection.getEnd();
    city1.edges.add(connection);
    city2.edges.add(new Edge(city2, city1, connection.getWeight()));
  }

  /**
   * Removes a Connection from start and end Vertices
   *
   * @param connection: connection to remove from both vertices
   */
  public void removeConnection(Edge connection) {
    this.removeConnection(connection.getStart(), connection.getEnd());
  }

  /**
   * Removes any connections between 2 Vertices
   *
   * @param city1 Vertex1 to disconnect
   * @param city2 Vertex2 to disconnect
   */
  public void removeConnection(Vertex city1, Vertex city2) {
    int index1 = city2.getEdgeIndex(city2);
    int index2 = city1.getEdgeIndex(city1);

    city1.edges.remove(index1);
    city2.edges.remove(index2);
  }

  public double[][] createAdjacencyMatrix() {
    double[][] adjacencyMatrix = new double[this.vertices.size()][this.vertices.size()];
    for (int i = 0; i < this.vertices.size(); i++) {
      for (int y = 0; y < this.vertices.size(); y++) {
        boolean connect = this.vertices.get(i).hasConnection(this.vertices.get(y));
        if (!connect) {
          adjacencyMatrix[i][y] = 0;
        } else {
          adjacencyMatrix[i][y] = this.vertices.get(i).getConnectionWeight(this.vertices.get(y));
        }
      }
    }
    return adjacencyMatrix;
  }

  /**
   * Creates a string adjcency list
   *
   * @return
   */
  public String createAdjList() {
    // ArrayList<ArrayList<Vertex>> adjList = new ArrayList<ArrayList<Vertex>>();
    String s = "";

    for (Vertex v : this.vertices) {
      s += v.toString() + " | " + v.edges.toString();
      s += "\n";
    }
    return s.toString();
  }

  /**
   * Returns the index of a vertex in the graph
   *
   * @param city vertex to find
   */
  public int indexOf(Vertex city) {
    int index = -1;
    for (Vertex v : this.vertices) {
      index += 1;
      if (v.getData().equals(city.getData())) {
        return index;
      }
    }

    return index;
  }

  public void pathOfLeastDistance(Vertex start, Vertex end) {}

  public String toString() {
    double[][] adjMatrix = this.createAdjacencyMatrix();
    // ArrayList<Vertex> v = tji;
    String s = "";
    int i = 0;

    for (double[] row : adjMatrix) {
      s += this.vertices.get(i) + " | ";
      for (double val : row) {
        s += val + " ";
      }
      s += "\n";
      i++;
    }
    return s;
  }

  /**
   * Static Main Method
   *
   * @param args program run arguments
   */
  public static void main(String[] args) {
    try {
      // CodeHS
      FileInputStream codehs = new FileInputStream("logging.properties");
      LogManager.getLogManager().readConfiguration(codehs);
      LOGGER.log(Level.INFO, "Loaded on CodeHS Successfully");
    } catch (IOException e) {
      // GH CodeSpaces
      LOGGER.log(
          Level.SEVERE, "Could not read 'log.properties' file from default CodeHS location.", e);
      try {
        FileInputStream codespaces =
            new FileInputStream(
                "/workspaces/Computer-Science-3-Projects/other utilities/logging.properties");
        LogManager.getLogManager().readConfiguration(codespaces);
        LOGGER.log(Level.INFO, "Loaded on Codespace Successfully");
      } catch (IOException x) {
        // None worked
        LOGGER.log(Level.SEVERE, "Could not read 'log.properties' file.", x);
      }
    }
    // ? -------------------- TESTING VERTEX, DISABLED --------------------
    // Vertex city1 = new Vertex("City1");
    // Vertex city2 = new Vertex("City2");
    // Vertex city3 = new Vertex("City3");
    // // city1.addConnection(city2, 50);
    // // city2.addConnection(city1, 50);

    // Edge e1 = new Edge(city1, city2, 50);
    // Graph g1 = new Graph();

    // g1.addVertex(city1);
    // g1.addVertex(city2);
    // g1.addConnection(e1);

    // ! END VERTEX TESTING

    // ! Map of Virginia
    // * Cities
    Vertex Alexandria = new Vertex("Alexandria     ");
    Vertex Blacksburg = new Vertex("Blacksburg     ");
    Vertex Charlottesville = new Vertex("Charlottesville");
    Vertex Danville = new Vertex("Danville       ");
    Vertex Fredricksburg = new Vertex("Fredricksburg  ");
    Vertex Harrisonburg = new Vertex("Harrisonburg   ");
    Vertex Lynchburg = new Vertex("Lynchburg      ");
    Vertex NewportNews = new Vertex("Newport News   ");
    Vertex Richmond = new Vertex("Richmond       ");
    Vertex Roanoke = new Vertex("Roanoke        ");
    Vertex VirginiaBeach = new Vertex("VirginiaBeach  ");

    // * Making an ArrayList to pass into the Virginia Graph
    ArrayList<Vertex> vv = new ArrayList<Vertex>();
    vv.add(Alexandria);
    vv.add(Blacksburg);
    vv.add(Charlottesville);
    vv.add(Danville);
    vv.add(Fredricksburg);
    vv.add(Harrisonburg);
    vv.add(Lynchburg);
    vv.add(NewportNews);
    vv.add(Richmond);
    vv.add(Roanoke);
    vv.add(VirginiaBeach);

    Graph Virginia = new Graph(vv);

    // * Edges
    Virginia.addConnection(Harrisonburg, Blacksburg, 140.0);
    Virginia.addConnection(Harrisonburg, Alexandria, 135.0);
    Virginia.addConnection(Harrisonburg, Charlottesville, 50.0);
    Virginia.addConnection(Blacksburg, Roanoke, 40.0);
    Virginia.addConnection(Roanoke, Lynchburg, 65.0);
    Virginia.addConnection(Lynchburg, Charlottesville, 70.0);
    Virginia.addConnection(Charlottesville, Richmond, 70.0);
    Virginia.addConnection(Richmond, Fredricksburg, 60.0);
    Virginia.addConnection(Fredricksburg, Alexandria, 50.0);
    Virginia.addConnection(Richmond, Lynchburg, 110.0);
    Virginia.addConnection(Lynchburg, Danville, 70.0);
    Virginia.addConnection(Danville, Richmond, 145.0);
    Virginia.addConnection(Richmond, NewportNews, 70.0);
    Virginia.addConnection(NewportNews, VirginiaBeach, 35.0);
    Virginia.addConnection(VirginiaBeach, Danville, 210.0);

    // LOGGER.info(Virginia.createAdjList());
    // LOGGER.info("\n"+Arrays.deepToString(Virginia.createAdjacencyMatrix()).replace("],",
    // "],\n"));
    // System.out.println(
    //     "\n" + Arrays.deepToString(Virginia.createAdjacencyMatrix()).replace("],", "],\n"));
    // System.out.println("\n\n");
    // System.out.println(Virginia);

    // ? Logging

    System.out.println("\n\n\n");

    PLD path = new PLD(Virginia, Harrisonburg);
    LOGGER.info("path.hasPath(): " + path.hasPath(VirginiaBeach));
    for (Vertex v : Virginia.vertices) {
      System.out.print(v.getData() + " to " + path.start.getData());
      path.hasPath(v);
      System.out.println();
    }
    System.out.println("\n\n\n");;
    LOGGER.info("path.toString():\n" + path.toString());
  }
}
