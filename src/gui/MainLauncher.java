package gui;

public class MainLauncher {

//	public static void main(String[] args) {
//		MainFrame aMainFrame = new MainFrame();
//		aMainFrame.setVisible(true);
//	}
	
	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new LoginGUI().setVisible(true);

			}
		});
	}
}
