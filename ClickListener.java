import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/*
	 * ClickListener that allows for actions when the user clicks on the graph panel
	 */
	class ClickListener extends MouseAdapter {
		@SuppressWarnings("unused")
		private Graph graph;

		public ClickListener(Graph g) {
			graph = g;
		}

		// Handles the clicks of he user on the JPanel according to a selected
		// JRadioButton
		public void mouseClicked(MouseEvent e) {
			if (GraphGUI.option.equals("Add Vertex")) {
				Graph.addVertex(e.getX(), e.getY());
			}
			if (GraphGUI.option.equals("Add Edge")) {
				Vertex s = null;
				for (int i = 0; i < Graph.vertices.size(); i++) {
					try {
						if (Graph.vertices.get(i).getVertex().contains(e.getX(), e.getY())) {
							if (Graph.vertices.get(i).getColor() != Color.GREEN) {
								Graph.vertices.get(i).setColor(Color.GREEN);
								s = Graph.vertices.get(i);
							} else {
								Graph.vertices.get(i).setColor(Color.GREEN);
							}
						}
					} catch (NullPointerException x) {
						continue;
					}
				}
				for (int j = 0; j < Graph.vertices.size(); j++) {
					try {
						if (!(Graph.vertices.get(j).getVertex().contains(e.getX(), e.getY()))) {
							if (Graph.vertices.get(j).getColor() == Color.GREEN) {
								if (s == null) {
									Graph.vertices.get(j).setColor(Color.RED);
									break;
								}
								Graph.addEdge(Graph.vertices.get(j), s);
								s.setColor(Color.RED);
							}
							Graph.vertices.get(j).setColor(Color.RED);
						}
					} catch (NullPointerException x) {
						continue;
					}
				}
			}
			if (GraphGUI.option.equals("Remove Vertex")) {
				for (int i = 0; i < Graph.vertices.size(); i++) {
					try {
						if (Graph.vertices.get(i).getVertex().contains(e.getX(), e.getY())) {
							Graph.removeVertex(Graph.vertices.get(i));
						}
					} catch (NullPointerException x) {
						continue;
					}
				}
			}
			if (GraphGUI.option.equals("Remove Edge")) {
				for (int i = 0; i < Graph.edges.size(); i++) {
					if (Graph.edges.get(i).getEdge().intersects(e.getX(), e.getY(), 4, 4)) {
						Graph.removeEdge(Graph.edges.get(i));
						Graph.removeEdge(Graph.edges.get(i));
					}
				}
			}
			if (GraphGUI.option.equals("Move Vertex")) {
				Vertex c = null;
				for (int i = 0; i < Graph.vertices.size(); i++) {
					try {
						if (Graph.vertices.get(i).getVertex().contains(e.getX(), e.getY())) {
							Graph.vertices.get(i).setColor(Color.GREEN);
						}
					} catch (NullPointerException x) {
						continue;
					}
				}
				for (int i = 0; i < Graph.vertices.size(); i++) {
					try {
						if (!(Graph.vertices.get(i).getVertex().contains(e.getX(), e.getY()))) {
							if (Graph.vertices.get(i).getColor() == Color.GREEN) {
								Graph.vertices.get(i).relocate(e.getX(), e.getY());
								c = Graph.vertices.get(i);
							}
						}
					} catch (NullPointerException x) {
						continue;
					}
				}
				for (int i = 0; i < Graph.edges.size(); i++) {
					if (Graph.edges.get(i).getSource().equals(c)) {
						Graph.edges.get(i).relocateS(c.getX(), c.getY());
					} else if (Graph.edges.get(i).getDest().equals(c)) {
						Graph.edges.get(i).relocateD(c.getX(), c.getY());
					}
				}
			}
		}
	}
