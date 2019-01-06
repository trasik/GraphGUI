import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/*
 * GraphGUI class that draws the user interface to interact and show the graph
 */
@SuppressWarnings("serial")
class GraphGUI extends JFrame {
	private JPanel b;
	protected static JRadioButton addVertex;
	protected static JRadioButton addEdge;
	protected static JRadioButton rVertex;
	protected static JRadioButton rEdge;
	protected static JRadioButton mVertex;
	protected static JButton aaEdges, cc, scv, help;
	private Graph graph;
	private ButtonListener bl;
    static String option = "";

	// Constructor
	public GraphGUI() {
		setTitle("Graph GUI");
		setSize(1500, 1000);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setResizable(false);
		setup();
	}

	// Auxillary method to set up the GUI panels and buttons
	private void setup() {
		Container c = getContentPane();
		c.setLayout(new BorderLayout());

		b = new JPanel(new GridLayout(10, 1));
		bl = new ButtonListener();

		addVertex = new JRadioButton("Add Vertex");
		addVertex.setFont(new Font("Arial", Font.BOLD, 20));
		addVertex.addActionListener(bl);
		addEdge = new JRadioButton("Add Edge");
		addEdge.setFont(new Font("Arial", Font.BOLD, 20));
		addEdge.addActionListener(bl);
		rVertex = new JRadioButton("Remove Vertex");
		rVertex.setFont(new Font("Arial", Font.BOLD, 20));
		rVertex.addActionListener(bl);
		rEdge = new JRadioButton("Remove Edge");
		rEdge.setFont(new Font("Arial", Font.BOLD, 20));
		rEdge.addActionListener(bl);
		mVertex = new JRadioButton("Move Vertex");
		mVertex.setFont(new Font("Arial", Font.BOLD, 20));
		mVertex.addActionListener(bl);

		aaEdges = new JButton("Add All Edges");
		aaEdges.setFont(new Font("Arial", Font.BOLD, 20));
		aaEdges.addActionListener(bl);
		cc = new JButton("Connected Components");
		cc.setFont(new Font("Arial", Font.BOLD, 20));
		cc.addActionListener(bl);
		scv = new JButton("Show Cut Vertices");
		scv.setFont(new Font("Arial", Font.BOLD, 20));
		scv.addActionListener(bl);
		help = new JButton("Help");
		help.setFont(new Font("Arial", Font.BOLD, 20));
		help.addActionListener(bl);

		b.add(addVertex);
		b.add(addEdge);
		b.add(rVertex);
		b.add(rEdge);
		b.add(mVertex);
		b.add(aaEdges);
		b.add(cc);
		b.add(scv);
		b.add(help);

		graph = new Graph();
		graph.addMouseListener(new ClickListener(graph));

		c.add(b, BorderLayout.WEST);
		c.add(graph, BorderLayout.CENTER);
	}
}