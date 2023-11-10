/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurants;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;


class Restaurant
{
    int id;
    List<Restaurant> children = new ArrayList<>();
    boolean isPho = false;
}

class Graph {
  private boolean adjMatrix[][];
  private int numVertices;

  // Initialize the matrix
  public Graph(int numVertices) {
    this.numVertices = numVertices;
    adjMatrix = new boolean[numVertices][numVertices];
  }

  // Add edges
  public void addEdge(int i, int j) {
    adjMatrix[i][j] = true;
    adjMatrix[j][i] = true;
  }

  // Remove edges
  public void removeEdge(int i, int j) {
    adjMatrix[i][j] = false;
    adjMatrix[j][i] = false;
  }
  
 
  public boolean isConnected(int i, int j)
  {
      return adjMatrix[i][j] || adjMatrix[j][i];
  }
  
  public void getConnected(int i, List<Integer> connected)
  {
      for (int j = 0; j < numVertices - 1; j++)
      {
          if (isConnected(i, j))
          {
              connected.add(j);
          }
      }
  }

  // Print the matrix
  public String toString() {
    StringBuilder s = new StringBuilder();
    s.append("\n");
    for (int i = 0; i < numVertices; i++) {
      s.append(i + ": ");
      for (boolean j : adjMatrix[i]) {
        s.append((j ? 1 : 0) + " ");
      }
      s.append("\n");
    }
    return s.toString();
  }
}

/**
 *
 * @author Matt
 */
public class Restaurants {

   
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Scanner scan = new Scanner(System.in);
        Queue queue = new LinkedList<>();
        int restaurantCount = scan.nextInt();
        int phoCount = scan.nextInt();
        Set<Integer> phoSet = new TreeSet<>();
        
        Graph graph;
        List<Restaurant> restaurants = new ArrayList<>();
        
        for (int i = 0; i < phoCount; i++)
        {
            phoSet.add(scan.nextInt());
        }
        graph = new Graph(restaurantCount);
        for (int i = 0; i < restaurantCount; i++)
        {
            Restaurant r = new Restaurant();
            r.id = i;
            if (phoSet.contains(i))
            {
                r.isPho = true;
            }
            restaurants.add(r);
        }
        
        for (int i = 0; i < restaurantCount - 1; i++)
        {
            int r0 = scan.nextInt();
            int r1 = scan.nextInt();
            graph.addEdge(r0, r1);
            
        }
        
        int startIndex =-1;
        for (int i = 0; i < restaurantCount - 1; i++)
        {
            int count = 0;
            for (int j = 0; j < restaurantCount - 1; j++)
            {
                if (graph.isConnected(i, j))
                {
                    count++;
                    if(count>1)
                    {
                        continue;
                    }
                }
            }
            if(count<2)
            {
                startIndex = i;
                break;
            }
        }
        queue.add(startIndex);
        while(!queue.isEmpty())
        {
            walkConnected(graph, queue, startIndex);
        }
        
    }
    
    static boolean walkConnected(Graph g, Queue q, int i)
    {
        List<Integer> connected = new ArrayList<>();    
        g.getConnected(i, connected);
        for(int j =0 ; j < connected.size(); j++)
        {
            return walkConnected(g, q, j);
        }
        return true;
    }
}
