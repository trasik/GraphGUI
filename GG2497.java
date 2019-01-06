import javax.swing.UIManager;

public class GG2497 {
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
			System.err.println("Look and feel not set");
		}
		new GraphGUI();
	}
}