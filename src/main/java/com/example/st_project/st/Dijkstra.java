package com.example.st_project.st;

import java.util.*;

public class Dijkstra {
    // Function to find the shortest distance of all the vertices
    // from the source vertex S.
    public int[] dijkstra(int V, List<int[]>[] adj, int S) {
        // Create a priority queue for storing the nodes as a pair {dist,node}
        // where dist is the distance from source to the node.
        // Priority queue stores the nodes in ascending order of the distances
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));

        // Initializing dist array with a large number to
        // indicate the nodes are unvisited initially.
        // This array contains distance from source to the nodes.
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);

        pq.offer(new int[]{0, S});

        // Source initialized with dist=0
        dist[S] = 0;

        // Now, poll the minimum distance node first from the queue
        // and traverse for all its adjacent nodes.
        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int node = current[1];
            int dis = current[0];

            // Check for all adjacent nodes of the polled
            // element whether the previous distance is larger than the current or not.
            for (int[] neighbor : adj[node]) {
                int adjNode = neighbor[0];
                int edgeWeight = neighbor[1];

                if (dis + edgeWeight < dist[adjNode]) {
                    // remove if it was visited previously at
                    // a greater cost.
                    if (dist[adjNode] != Integer.MAX_VALUE)
                        pq.remove(new int[]{dist[adjNode], adjNode});

                    // If current distance is smaller,
                    // push it into the priority queue
                    dist[adjNode] = dis + edgeWeight;
                    pq.offer(new int[]{dist[adjNode], adjNode});
                }
            }
        }
//         Return the array containing shortest distances
        // from source to all the nodes.
        return dist;
    }
}

// class Main {
//    public static void main(String[] args) {
//        // Driver code.
//        int V = 4, E = 3, S = 2;
//        List<int[]>[] adj = new List[V];
//        for (int i = 0; i < V; i++) {
//            adj[i] = new ArrayList<>();
//        }
//
//        int[][] edges = {{0,1, 1}, {0,2, 6}, {1,2, 3}, {2,0, 1}, {0,3, 3}, {2,3, 6}};
//
//        for (int[] edge : edges) {
//            adj[edge[0]].add(new int[]{edge[1], edge[2]});
//        }
//
//        Dijkstra obj = new Dijkstra();
//        int[] res = obj.dijkstra(V, adj, S);
//
//        for (int i = 0; i < V; i++) {
//            System.out.print(res[i] + " ");
//        }
//        System.out.println();
//    }
//}