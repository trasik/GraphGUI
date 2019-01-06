import java.awt.Color;
import java.awt.Font;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

// ButtonListener class to give the buttons properties
	class ButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			Object s = e.getSource();
			if (s == GraphGUI.addVertex) {
				clear();
				GraphGUI.addVertex.setSelected(true);
				GraphGUI.option = e.getActionCommand().toString();
			} else if (s == GraphGUI.addEdge) {
				clear();
				GraphGUI.addEdge.setSelected(true);
				GraphGUI.option = e.getActionCommand().toString();
			} else if (s == GraphGUI.rVertex) {
				clear();
				GraphGUI.rVertex.setSelected(true);
				GraphGUI.option = e.getActionCommand().toString();
			} else if (s == GraphGUI.rEdge) {
				clear();
				GraphGUI.rEdge.setSelected(true);
				GraphGUI.option = e.getActionCommand().toString();
			} else if (s == GraphGUI.mVertex) {
				clear();
				GraphGUI.mVertex.setSelected(true);
				GraphGUI.option = e.getActionCommand().toString();
			} else if (s == GraphGUI.aaEdges) {
				clear();
				GraphGUI.option = e.getActionCommand().toString();
				Graph.addAllEdges();
			} else if (s == GraphGUI.cc) {
				clear();
				Graph.showCC = true;
				Graph.CC(Graph.helper);
				Graph.showCC = false;
			} else if (s == GraphGUI.scv) {
				clear();
				Graph.showCutV();
			} else if (s == GraphGUI.help) {
				clear();
				helpFrame();
			}
		}

		// Creates a new GUI when the help button is pressed
		public void helpFrame() {
			JFrame h = new JFrame();
			h.setTitle("Help Screen");
			h.setSize(1000, 500);
			h.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			h.setVisible(true);
			h.setResizable(false);

			TextArea hp = new TextArea();
			Font font = new Font("Arial", Font.BOLD, 14);
			hp.setFont(font);
			hp.setBackground(Color.BLACK);
			hp.setForeground(Color.WHITE);
			hp.append("Welcome to the Help Screen, below are definitions to understand what each button does.\n\n");
			hp.append("Switching between Radio Buttions selects what you want to do towards the drawing area.\n\n");
			hp.append(
					"Add Vertex - When selected allows you to click on the space provided and adds a Vertex with an x and y on a Graph.\n\n");
			hp.append("AddEdge - When selected allows you to add a Edge between two Vertices in your choosing.\n\n");
			hp.append("Remove Vertex - When selected removes the vertex of your choosing.\n\n");
			hp.append("Remove Edge - When selected removes the edge of your choosing.\n\n");
			hp.append(
					"Move Vertex - When selected moves a Vertex you choose to a designated coordinates that you also choose.\n\n");
			hp.append(
					"\n\nClicking on a big button below the Radio Buttons displays the features associated to the Graph.\n\n");
			hp.append(
					"Add All Edges - adds every edge avaliable from the number of vertices you have in the drawing area.\n\n");
			hp.append(
					"Connected Components - highlights the different components on the graph depending on how the vertices and edges are connected.\n\n");
			hp.append(
					"Show Cut Vertices - highlights the vertices that when removed, disconnects the graph into multiple components.\n\n");
			hp.append("\n Hope this helps you - T.R");
			h.add(hp);
		}

		// Reset the Radio Buttons
		public void clear() {
			GraphGUI.addVertex.setSelected(false);
			GraphGUI.addEdge.setSelected(false);
			GraphGUI.rVertex.setSelected(false);
			GraphGUI.rEdge.setSelected(false);
			GraphGUI.mVertex.setSelected(false);
			for (int i = 0; i < Graph.vertices.size(); i++) {
				try {
					Graph.vertices.get(i).setColor(Color.RED);
				} catch (NullPointerException e) {
					continue;
				}
			}
			for (int i = 0; i < Graph.edges.size(); i++) {
				Graph.edges.get(i).setColor(Color.BLUE);
			}
		}
	}
