import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

	/*
	 * Vertex class to give the properties of a given vertex object
	 */
	public class Vertex {
		int xPos;
	    int yPos;
		private int num;
		private Shape v;
		private Color c;

		public Vertex(int x, int y, int n) {
			xPos = x;
			yPos = y;
			v = new Ellipse2D.Double(x, y, 20, 20);
			c = Color.RED;
			num = n;
		}

		public int getX() {
			return xPos;
		}

		public int getY() {
			return yPos;
		}

		public int getN() {
			return num;
		}

		public void setN(int n) {
			num = n;
		}

		public void setX(int x) {
			xPos = x;
		}

		public void setY(int y) {
			yPos = y;
		}

		public void relocate(int x, int y) {
			setX(x);
			setY(y);
			v = new Ellipse2D.Double(x, y, 20, 20);
			c = Color.RED;
		}

		public Shape getVertex() {
			return v;
		}

		public Color getColor() {
			return c;
		}

		public void setColor(Color g) {
			c = g;
		}

		public String toString() {
			return num + ": " + xPos + ", " + yPos;
		}
	}
