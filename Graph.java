import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javax.swing.JPanel;

@SuppressWarnings("serial")
class Graph extends JPanel {
		static ArrayList<Vertex> vertices = new ArrayList<>();
		static ArrayList<Edge> edges = new ArrayList<>();
		// ArrayList to help show the connections between two or more vertices within a
		// graph.
		static ArrayList<ArrayList<Integer>> helper = new ArrayList<ArrayList<Integer>>();
		// boolean to show the connected component colors
		static boolean showCC = false;
		static int numV = 0;
		// keep count of the connected components
		static int ccCount = 0;

		public static void addVertex(int x, int y) {
			if (vertexExists(x, y))
				return;
			Vertex v = new Vertex(x, y, numV++);
			// Whenever this method is called adds the coords clicked into a new Vertex
			// object
			// and also creates a index in the helper arraylist to represent the vertex
			vertices.add(v);
			ArrayList<Integer> vAdd = new ArrayList<Integer>();
			helper.add(vAdd);
		}

		// Auxillary method to check if the vertex is already on the graph
		public static boolean vertexExists(int x, int y) {
			Vertex temp = null;
			for (int i = 0; i < vertices.size(); i++) {
				try {
					if (x >= vertices.get(i).getX() - 20 && x <= vertices.get(i).getX() + 20
							&& y >= vertices.get(i).getY() - 20 && y <= vertices.get(i).getY() + 20) {
						temp = vertices.get(i);
					}
				} catch (NullPointerException e) {
					continue;
				}
			}
			if (temp != null)
				return true;

			return false;
		}

		public static void addEdge(Vertex s, Vertex d) {
			if (edgeExists(s, d))
				return;
			// Adds two cases of an edge as the graph is undirected.
			edges.add(new Edge(s, d));
			edges.add(new Edge(d, s));
			// Adds the connections between two vertices into helper
			helper.get(s.getN()).add(d.getN());
			helper.get(d.getN()).add(s.getN());
		}

		// Auxiliary method to check if the edge already exists on the graph.
		public static boolean edgeExists(Vertex s, Vertex d) {
			Edge temp = null;
			for (int i = 0; i < edges.size(); i++)
				if (edges.get(i).getSource().equals(s) && edges.get(i).getDest().equals(d))
					temp = edges.get(i);

			if (temp != null)
				return true;

			return false;
		}

		public static void removeVertex(Vertex v) {
			// Go through the edge list three times to ensure all edges were deleted from
			// the single vertex
			// that was deleted
			for (int j = 0; j <= 3; j++) {
				for (int i = 0; i < edges.size(); i++) {
					if (edges.get(i).getSource().equals(v) || edges.get(i).getDest().equals(v)) {
						removeEdge(edges.get(i));
					}
				}
			}
			// Set the vertex deleted to empty or null
			vertices.set(v.getN(), null);
			// Set the helper index of the vertex to empty or null
			helper.set(v.getN(), null);
		}

		public static void removeEdge(Edge e) {
			edges.remove(e);
			Vertex s = e.getSource();
			Vertex d = e.getDest();
			// Go through the source vertex in helper and delete the occurrence of the
			// vertex it
			// was connected to
			Iterator<Integer> sIT = helper.get(s.getN()).iterator();
			while (sIT.hasNext())
				if (sIT.next().equals(d.getN()))
					sIT.remove();
			// Do the same as before but this time delete the occurrence of the source
			// vertex from destination vertex
			Iterator<Integer> dIT = helper.get(d.getN()).iterator();
			while (dIT.hasNext())
				if (dIT.next().equals(s.getN()))
					dIT.remove();
		}

		public static void addAllEdges() {
			for (int i = 0; i < vertices.size(); i++) {
				for (int j = 0; j < vertices.size(); j++) {
					try {
						if (vertices.get(i).equals(vertices.get(j)))
							continue;
						addEdge(vertices.get(i), vertices.get(j));
					} catch (NullPointerException e) {
						continue;
					}
				}
			}
		}

		/*
		 * Method to calculate the number of connected components given a single helper
		 * arraylist This method uses the algorithm of depth first search in order to
		 * mark the visited vertices given a single vertex to show the total connections
		 * as one.
		 */
		public static void CC(ArrayList<ArrayList<Integer>> x) {
			ccCount = 0;
			boolean[] seen = new boolean[x.size()];
			Random random = new Random();
			Color ccColor = new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255));

			// Initialize all values to false as they haven't been seen
			for (int i = 0; i < x.size(); i++) {
				seen[i] = false;
			}

			// Go through each vertex in the seen array and if they haven't been seen call
			// dfs
			// to find them
			for (int j = 0; j < x.size(); j++) {
				if (seen[j] == false) {
					try {
						dfs(j, seen, ccColor, x);
						ccColor = new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255));
						ccCount++;
					} catch (NullPointerException e) {
						continue;
					}
				}
			}
		}

		/*
		 * Utility method that uses the algorithm of depth first searching and recursion
		 * in order to find the connected vertices.
		 */
		public static void dfs(int v, boolean seen[], Color c, ArrayList<ArrayList<Integer>> x) {
			// vertex has now been seen
			seen[v] = true;
			// Draw the colors to the Jpanel of the connected component
			if (showCC) {
				for (int i = 0; i < vertices.size(); i++) {
					try {
						if (vertices.get(i).getN() == v) {
							vertices.get(i).setColor(c);
						}
					} catch (NullPointerException e) {
						continue;
					}
					for (int j = 0; j < edges.size(); j++) {
						if (edges.get(j).getSource().equals(vertices.get(v))
								|| edges.get(j).getDest().equals(vertices.get(v))) {
							edges.get(j).setColor(c);
						}
					}
				}
			}
			// go through all vertices connected to v and if they haven't been seen
			// call dfs again till all vertices have been seen
			for (int i : x.get(v)) {
				if (!seen[i]) {
					dfs(i, seen, c, x);
				}
			}
		}

		/*
		 * Based off the idea that if a vertex that connects the graph is removed and
		 * increases the number of connected components then it is a cut Vertex
		 */
		@SuppressWarnings("unchecked")
		public static void showCutV() {
			// Call CC() on helper to get initial value of connected components
			CC(helper);
			final int count = ccCount;
			for (int i = 0; i < helper.size(); i++) {
				// Go through the helper arraylist and every time recreate a new
				// arraylist to hold values so helper doesn't change
				ArrayList<ArrayList<Integer>> temp = new ArrayList<ArrayList<Integer>>();
				int c = 0;
				for (ArrayList<Integer> copy : helper) {
					try {
					temp.add((ArrayList<Integer>) copy.clone());
					++c;
					//Special case where a vertex was removed add that null index into temp
					//in order to check
					} catch (NullPointerException e) {
						temp.add(c, null);
					}
				}
				// Go through each vertices and delete the occurance of the vertex being deleted
				// from each
				for (int j = 0; j < temp.size(); j++) {
					try {
					Iterator<Integer> tempIT = temp.get(j).iterator();
					while (tempIT.hasNext())
						if (tempIT.next().equals(i))
							tempIT.remove();
					} catch (NullPointerException e) {
						continue;
					}
				}
				// set the vertex to empty or null
				temp.set(i, null);
				// Calculate the new number of connected components
				CC(temp);
				if (count < ccCount) {
					// if the new number is greater than the old then that vertex is a cut vertex
					try {
					vertices.get(i).setColor(Color.GREEN);
					} catch(NullPointerException e) {
						continue;
					}
				}
			}
		}

		public void paint(Graphics g) {
			super.paint(g);
			Graphics2D p = (Graphics2D) g;

			for (Vertex k : vertices) {
				try {
					p.setColor(k.getColor());
					Shape v = k.getVertex();
					p.fill(v);
				} catch (NullPointerException e) {
					continue;
				}
			}

			for (Edge e : edges) {
				p.setColor(e.getColor());
				p.setStroke(new BasicStroke(4));
				Shape edge = e.getEdge();
				p.draw(edge);
			}

			repaint();
		}
	}
