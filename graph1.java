
import java.util.*;
public class Graph {

    private static class Edge {
        int o;
        int n;
        int wt;

        Edge (int v1, int v2, int wt){
            this.o = v1;
            this.n = v2;
            this.wt = wt;
        }
    }

    private static void addEdge(ArrayList<ArrayList<Edge>> graph, int v1, int v2, int wt){
        graph.get(v1).add(new Edge(v1, v2, wt));
        graph.get(v2).add(new Edge(v2, v1, wt));
    }

    private static void display(ArrayList<ArrayList<Edge>> graph){
        for(int v = 0; v < graph.size(); v++){
            System.out.print("|" + v + "| -> ");
            for(int e = 0; e < graph.get(v).size(); e++){
                Edge edge = graph.get(v).get(e);
                System.out.print("[" + edge.o + ", " + edge.n + ", " + edge.wt + "]" + ", ");
            }
            System.out.println();
        }
    }

    private static boolean hasPath(int src, int dest, ArrayList<ArrayList<Edge>> graph,
                                     boolean[] visited){
        if(src == dest){
            return true;
        }
        visited[src] = true;
        for(int e = 0; e < graph.get(src).size(); e++){
            // take neighbour
           int nbr = graph.get(src).get(e).n;
           if(visited[nbr] == false){
                // path from neighbour
                boolean pfn = hasPath(nbr, dest, graph, visited);
                if(pfn == true){
                    return true;
                }
            }
        }
        return false;
    }

    private static void printAllPath(int src, int dest, ArrayList<ArrayList<Edge>> graph,
                                    boolean[] visited, String asf){
        if(src == dest){
            System.out.println(asf + src);
            return;
        }
        visited[src] = true;
        for(int e = 0; e < graph.get(src).size(); e++){
            int nbr = graph.get(src).get(e).n;
            if(visited[nbr] == false){
                printAllPath(nbr, dest, graph, visited, asf + src + "->");
            }
        }
        visited[src] = false;
    }

    public static void main(String[] args) {
        int n = 7;
        ArrayList<ArrayList<Edge>> graph = new ArrayList<>();
        //push_back new ArrayList in graph so that u can push edges in graph
        for(int i=0;i<7;i++) {
            graph.add(new ArrayList<>());
        }
        addEdge(graph, 0, 3, 40);
        addEdge(graph, 0, 1, 10);
        addEdge(graph, 1, 2, 10);
        addEdge(graph, 2, 3, 10);
        addEdge(graph, 3, 4, 2);
        addEdge(graph, 4, 5, 3);
        addEdge(graph, 4, 6, 8);
        addEdge(graph, 5, 6, 3);

        display(graph);
        boolean[] visited = new boolean[n];
        System.out.println(hasPath(0, 7, graph, visited));
        System.out.println("----------");
        Arrays.fill(visited, false);
        printAllPath(0, 6, graph, visited, "");
        

	    }

    }
