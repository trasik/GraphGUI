import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Line2D;

/*
	 * Edge class that gives the properties of a given Edge object
	 */
	public class Edge {
		private Vertex source, dest;
		private Shape e;
		private Color c;

		public Edge(Vertex s, Vertex d) {
			source = s;
			dest = d;
			c = Color.BLUE;
			e = new Line2D.Double(source.getX() + 12, source.getY() + 12, dest.getX() + 12, dest.getY() + 12);
		}

		public Vertex getSource() {
			return source;
		}

		public Vertex getDest() {
			return dest;
		}

		public void setSource(Vertex s) {
			source = s;
		}

		public void setDest(Vertex d) {
			dest = d;
		}

		public void relocateS(int x, int y) {
			source.setX(x);
			source.setY(y);
			c = Color.BLUE;
			e = new Line2D.Double(source.getX() + 12, source.getY() + 12, dest.getX() + 12, dest.getY() + 12);
		}

		public void relocateD(int x, int y) {
			dest.setX(x);
			dest.setY(y);
			c = Color.BLUE;
			e = new Line2D.Double(source.getX() + 12, source.getY() + 12, dest.getX() + 12, dest.getY() + 12);
		}

		public Shape getEdge() {
			return e;
		}

		public Color getColor() {
			return c;
		}

		public void setColor(Color c) {
			this.c = c;
		}

		public String toString() {
			return "Pos: " + source.xPos + ", " + source.yPos + " Pos: " + dest.xPos + ", " + dest.yPos;
		}
	}