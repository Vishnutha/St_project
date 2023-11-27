package com.example.st_project.st;

import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
class StApplicationTests {


	@Test
	void ArticulationTest1() {
		Articulation articulation = new Articulation();
		int[][] edges = {{0, 1}};
		ArrayList<Integer> articulationPoints = articulation.articulationPoints(2, edges);
		ArrayList<Integer> expectedArticulations = new ArrayList<>();
		expectedArticulations.add(-1);
		assertArrayEquals(expectedArticulations.toArray(new Integer[0]), articulationPoints.toArray(new Integer[0]));

	}

	@Test
	void ArticulationTest2() {
		Articulation articulation = new Articulation();
		int[][] edges = {{0, 1}, {1, 2}};
		ArrayList<Integer> articulationPoints = articulation.articulationPoints(3, edges);
		ArrayList<Integer> expectedArticulations = new ArrayList<>();
		expectedArticulations.add(1);
		assertArrayEquals(expectedArticulations.toArray(new Integer[0]), articulationPoints.toArray(new Integer[0]));

	}

	@Test
	void ArticulationTest3() {
		Articulation articulation = new Articulation();
		int[][] edges = {{0, 1}, {1, 2}, {2, 0}};
		ArrayList<Integer> articulationPoints = articulation.articulationPoints(3, edges);
		ArrayList<Integer> expectedArticulations = new ArrayList<>();
		expectedArticulations.add(-1);
		assertArrayEquals(expectedArticulations.toArray(new Integer[0]), articulationPoints.toArray(new Integer[0]));

	}

	@Test
	void ArticulationTest4() {
		Articulation articulation = new Articulation();
		int[][] edges = {{0, 1}, {2, 0}};
		ArrayList<Integer> articulationPoints = articulation.articulationPoints(3, edges);
		ArrayList<Integer> expectedArticulations = new ArrayList<>();
		expectedArticulations.add(0);
		assertArrayEquals(expectedArticulations.toArray(new Integer[0]), articulationPoints.toArray(new Integer[0]));

	}

	@Test
	void BridgeTest1() {
//		int[][] edges = {
//                {0, 1}, {1, 2},
//                {2, 0}, {1, 3}
//        };
		int[][] edges = {{0, 1}, {1, 2}, {2, 0}};

		List<List<Integer>> connections = new ArrayList<>();
		int n = 3;
		for (int i = 0; i < edges.length; i++) {
			connections.add(new ArrayList<Integer>());
		}
		for (int i = 0; i < edges.length; i++) {
			connections.get(i).add(edges[i][0]);
			connections.get(i).add(edges[i][1]);
		}

		Bridge obj = new Bridge();
		List<List<Integer>> bridges = obj.criticalConnections(n, connections);
//		int [][] expectedBridges = {{}};
		List<List<Integer>> expectedBridgesList = new ArrayList<>();

		assertArrayEquals(expectedBridgesList.toArray(), bridges.toArray());


	}

	@Test
	void BridgeTest2() {
		int[][] edges = {{0, 1}, {1, 2}, {2, 3}};
		List<List<Integer>> connections = new ArrayList<>();
		int n = 4;
		for (int i = 0; i < edges.length; i++) {
			connections.add(new ArrayList<Integer>());
		}
		for (int i = 0; i < edges.length; i++) {
			connections.get(i).add(edges[i][0]);
			connections.get(i).add(edges[i][1]);
		}

		Bridge obj = new Bridge();
		List<List<Integer>> bridges = obj.criticalConnections(n, connections);
		int[][] expectedBridges = {{3, 2}};
		int expectedEdges = 1;
		List<List<Integer>> expectedBridgesList = new ArrayList<>();

		expectedBridgesList.add(Arrays.asList(3, 2));
		expectedBridgesList.add(Arrays.asList(2, 1));
		expectedBridgesList.add(Arrays.asList(1, 0));
		for (List<Integer> edge : bridges) {
			int u = (int) edge.get(0);
			int v = (int) edge.get(1);
			System.out.println(u + " " + v);
		}

		assertEquals(expectedBridgesList.size(), bridges.size());


	}

	@Test
	void DijkstraTest1() {
		int V = 4, S = 2;
		List<int[]>[] adj = new List[V];
		for (int i = 0; i < V; i++) {
			adj[i] = new ArrayList<>();
		}
		int[][] edges = {{0, 1, 1}, {0, 2, 6}, {1, 2, 3}, {2, 0, 1}, {0, 3, 3}, {2, 3, 6}};
		for (int[] edge : edges) {
			adj[edge[0]].add(new int[]{edge[1], edge[2]});
		}
		Dijkstra obj = new Dijkstra();
		int[] res = obj.dijkstra(V, adj, S);
		int[] exp = {1, 2, 0, 4};
		assertArrayEquals(exp, res);

	}

	@Test
	void DijkstraTest3() {
		int V = 4, S = 0;
		List<int[]>[] adj = new List[V];
		for (int i = 0; i < V; i++) {
			adj[i] = new ArrayList<>();
		}
		int[][] edges = {{0, 1, 10}};
		for (int[] edge : edges) {
			adj[edge[0]].add(new int[]{edge[1], edge[2]});
		}
		Dijkstra obj = new Dijkstra();
		int[] res = obj.dijkstra(V, adj, S);
		int[] exp = {0, 10, Integer.MAX_VALUE, Integer.MAX_VALUE};
		assertArrayEquals(exp, res);

	}

	@Test
	void DijkstraTest2() {
		int V = 4, S = 2;
		List<int[]>[] adj = new List[V];
		for (int i = 0; i < V; i++) {
			adj[i] = new ArrayList<>();
		}
		int[][] edges = {{0, 1, 10}};
		for (int[] edge : edges) {
			adj[edge[0]].add(new int[]{edge[1], edge[2]});
		}
		Dijkstra obj = new Dijkstra();
		int[] res = obj.dijkstra(V, adj, S);
		int[] exp = {Integer.MAX_VALUE, Integer.MAX_VALUE, 0, Integer.MAX_VALUE};
		assertArrayEquals(exp, res);

	}

	@Test
	void BellmanFordTest1() {
		int V = 6;
		int S = 0;
		ArrayList<ArrayList<Integer>> edges = new ArrayList<>() {
			{
				add(new ArrayList<Integer>(Arrays.asList(3, 2, 6)));
				add(new ArrayList<Integer>(Arrays.asList(5, 3, 1)));
				add(new ArrayList<Integer>(Arrays.asList(0, 1, 5)));
				add(new ArrayList<Integer>(Arrays.asList(1, 5, -3)));
				add(new ArrayList<Integer>(Arrays.asList(1, 2, -2)));
				add(new ArrayList<Integer>(Arrays.asList(3, 4, -2)));
				add(new ArrayList<Integer>(Arrays.asList(2, 4, 3)));
			}
		};
		int[] res = BellmanFord.bellman_ford(V, edges, S);
		int[] exp = {0, 5, 3, 3, 1, 2};
		assertArrayEquals(exp, res);
	}


	@Test
	void BellmanFordTest2() {
		int V = 3;
		int S = 0;
		// negative cycle check
		ArrayList<ArrayList<Integer>> edges = new ArrayList<>() {
			{
				add(new ArrayList<Integer>(Arrays.asList(0, 1, 5)));
				add(new ArrayList<Integer>(Arrays.asList(1, 2, -100)));
				add(new ArrayList<Integer>(Arrays.asList(2, 0, 10)));

			}
		};
		int[] res = BellmanFord.bellman_ford(V, edges, S);
		int[] exp = {-1};
		assertArrayEquals(exp, res);
	}

	@Test
	void DsuSizeTest1() {
		int V = 5;
		ArrayList<ArrayList<ArrayList<Integer>>> adj = new ArrayList<ArrayList<ArrayList<Integer>>>();
		int[][] edges = {{0, 1, 2}, {0, 2, 1}, {1, 2, 1}, {2, 3, 2}, {3, 4, 1}, {4, 2, 2}};

		for (int i = 0; i < V; i++) {
			adj.add(new ArrayList<ArrayList<Integer>>());
		}

		for (int i = 0; i < 6; i++) {
			int u = edges[i][0];
			int v = edges[i][1];
			int w = edges[i][2];

			ArrayList<Integer> tmp1 = new ArrayList<Integer>();
			ArrayList<Integer> tmp2 = new ArrayList<Integer>();
			tmp1.add(v);
			tmp1.add(w);

			tmp2.add(u);
			tmp2.add(w);

			adj.get(u).add(tmp1);
			adj.get(v).add(tmp2);
		}

		MinSpanningTree obj = new MinSpanningTree();
		int resMstWt = obj.spanningTree(V, adj);
		int expMstWt = 5;
		assertEquals(expMstWt, resMstWt);
	}

	@Test
	void DsuRankTest2() {
		int V = 5;
		ArrayList<ArrayList<ArrayList<Integer>>> adj = new ArrayList<ArrayList<ArrayList<Integer>>>();
		int[][] edges = {{0, 1, 2}, {0, 2, 1}, {1, 2, 1}, {2, 3, 2}, {3, 4, 1}, {4, 2, 2}};

		for (int i = 0; i < V; i++) {
			adj.add(new ArrayList<ArrayList<Integer>>());
		}

		for (int i = 0; i < 6; i++) {
			int u = edges[i][0];
			int v = edges[i][1];
			int w = edges[i][2];

			ArrayList<Integer> tmp1 = new ArrayList<Integer>();
			ArrayList<Integer> tmp2 = new ArrayList<Integer>();
			tmp1.add(v);
			tmp1.add(w);

			tmp2.add(u);
			tmp2.add(w);

			adj.get(u).add(tmp1);
			adj.get(v).add(tmp2);
		}
		MinSpanningTree obj = new MinSpanningTree();
		int resMstWt = obj.spanningTree2(V, adj);
		int expMstWt = 5;
		assertEquals(expMstWt, resMstWt);
	}
	@Test
	void DsuRankTest() {
		int V = 5;
		ArrayList<ArrayList<ArrayList<Integer>>> adj = new ArrayList<ArrayList<ArrayList<Integer>>>();
		int[][] edges = {{0, 1, 2}, {0, 3, 1}, {0, 2, 1}, {3, 4, 1}};

		for (int i = 0; i < V; i++) {
			adj.add(new ArrayList<ArrayList<Integer>>());
		}
		int E = edges.length;

		for (int i = 0; i < E; i++) {
			int u = edges[i][0];
			int v = edges[i][1];
			int w = edges[i][2];

			ArrayList<Integer> tmp1 = new ArrayList<Integer>();
			ArrayList<Integer> tmp2 = new ArrayList<Integer>();
			tmp1.add(v);
			tmp1.add(w);

			tmp2.add(u);
			tmp2.add(w);

			adj.get(u).add(tmp1);
			adj.get(v).add(tmp2);
		}
		MinSpanningTree obj = new MinSpanningTree();
		int resMstWt = obj.spanningTree2(V, adj);
		int expMstWt = 5;
		assertEquals(expMstWt, resMstWt);
	}

	@Test
	void FloydWarshallTest()
	{
		int V = 4;
		int[][] matrix = new int[V][V];
		for (int i = 0; i < V; i++) {
			for (int j = 0; j < V; j++) {
				matrix[i][j] = -1;
			}
		}
		matrix[0][1] = 2;
		matrix[1][0] = 1;
		matrix[1][2] = 3;
		matrix[3][0] = 3;
		matrix[3][1] = 5;
		matrix[3][2] = 4;
		Floyd obj = new Floyd();
		obj.shortest_distance(matrix);
		int res[][] = {{0,2,5,-1},{1,0,3,-1},{-1,-1,0,-1},{3,5,4,0}};
		assertArrayEquals(res,matrix);
	}
	@Test
	void KosarajuTest()
	{
		int n = 5;
		int[][] edges = {
				{1, 0}, {0, 2},
				{2, 1}, {0, 3},
				{3, 4}
		};
		ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			adj.add(new ArrayList<Integer>());
		}
		for (int i = 0; i < n; i++) {
			adj.get(edges[i][0]).add(edges[i][1]);
		}
		Kosaraju obj = new Kosaraju();
		int ans = obj.kosaraju(n, adj);
		int exp = 3;
		assertEquals(3,ans);

	}
	@Test
	void SpanningTreeTest()
	{
		int V = 5;
		ArrayList<ArrayList<ArrayList<Integer>>> adj = new ArrayList<ArrayList<ArrayList<Integer>>>();
		int[][] edges =  {{0, 1, 2}, {0, 2, 1}, {1, 2, 1}, {2, 3, 2}, {3, 4, 1}, {4, 2, 2}};

		for (int i = 0; i < V; i++) {
			adj.add(new ArrayList<ArrayList<Integer>>());
		}

		for (int i = 0; i < 6; i++) {
			int u = edges[i][0];
			int v = edges[i][1];
			int w = edges[i][2];

			ArrayList<Integer> tmp1 = new ArrayList<Integer>();
			ArrayList<Integer> tmp2 = new ArrayList<Integer>();
			tmp1.add(v);
			tmp1.add(w);

			tmp2.add(u);
			tmp2.add(w);

			adj.get(u).add(tmp1);
			adj.get(v).add(tmp2);
		}

		SpanningTree obj = new SpanningTree();
		int sum = obj.spanningTree(V, adj);
		assertEquals(5,sum);
	}

//	4 5 0 2 3 1
	@Test
	void TopoSortBfsTest()
	{
		int V = 6;
		ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
		for (int i = 0; i < V; i++) {
			adj.add(new ArrayList<>());
		}
		adj.get(2).add(3);
		adj.get(3).add(1);
		adj.get(4).add(0);
		adj.get(4).add(1);
		adj.get(5).add(0);
		adj.get(5).add(2);

		int[] res = ToposortBfs.topoSort(V, adj);
		int [] exp = {4,5,0,2,3,1};
		assertArrayEquals(exp,res);
	}

	@Test
	void TopoSortDfsTest()
	{
		int V = 6;
		ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
		for (int i = 0; i < V; i++) {
			adj.add(new ArrayList<>());
		}
		adj.get(2).add(3);
		adj.get(3).add(1);
		adj.get(4).add(0);
		adj.get(4).add(1);
		adj.get(5).add(0);
		adj.get(5).add(2);

		int[] res = ToposortDfs.topoSort(V, adj);
		int [] exp = {5,4,2,3,1,0};
		assertArrayEquals(exp,res);
	}
	@Test
	void TrieTest()
	{
		int type[] = {1, 1, 2, 3, 2};
		String value[] = {"hello", "help", "help", "hel", "hel"};
		Trie trie = new Trie();
		ArrayList<Boolean> res = trie.getQueries(type,value);
		ArrayList<Boolean> exp = new ArrayList<>()
		{{
			add(true);
			add(true);
			add(false);
		}};
		assertArrayEquals(exp.toArray(),res.toArray());

	}
	@Test
	void TrieTest2()
	{
		int type[] = {1, 1, 2, 3, 2,3,2};
        String value[] = {"hello", "help", "help", "hel", "hel","helo","a"};
		Trie trie = new Trie();
		ArrayList<Boolean> res = trie.getQueries(type,value);
		ArrayList<Boolean> exp = new ArrayList<>()
		{{
			add(true);
			add(true);
			add(false);
			add(false);
			add(false);

		}};
		assertArrayEquals(exp.toArray(),res.toArray());

	}
	@Test
	void segmentTreeTest()
	{


	}



}
