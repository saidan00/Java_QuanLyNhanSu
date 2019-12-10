package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class HeaderPanel extends JPanel {
	JButton btnExit;
	JButton btnMinimize;
	JLabel lblTitle;
	String TITLE = MyProps.CURRENT_USER.isEmpty() ? "HR Manager" : "HR Manager: " + MyProps.CURRENT_USER;
	MyProps myProps = new MyProps();

	// default size
	public static final int WIDTH = MyProps.DEFAULT_WIDTH;
	public static final int HEIGHT = 50;

	public HeaderPanel() {
		this.setLayout(null);
		this.setBackground(Color.decode(myProps.Color_Teal_Dark));
		this.setBounds(0, 0, WIDTH, HEIGHT);

		this.setSize(WIDTH, HEIGHT);

		// init components
		initBtnExit();
		initBtnMinimize();
		initLblTitle();
	}

	private void initBtnExit() {
		btnExit = new JButton("X");

		// set properties
		btnExit.setBounds(WIDTH - HEIGHT, 0, HEIGHT, HEIGHT);
		btnExit.setBackground(Color.decode(myProps.Color_Teal_Dark));
		btnExit.setForeground(Color.WHITE);
		btnExit.setFont(myProps.DEFAULT_FONT);

		// flat style
		btnExit.setBorderPainted(false);
		btnExit.setFocusPainted(false);

		// hover effect
		myProps.BtnHover(btnExit, Color.decode("#d50000"));

		// confirm exit
		btnExit.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				// confirm dialog exit or not
				int dialogResult = JOptionPane.showConfirmDialog(null, // component
						"Do you want to exit?", // message
						"Confirm Exit", // title
						JOptionPane.YES_NO_OPTION, // option type
						JOptionPane.WARNING_MESSAGE // message types
				);

				if (dialogResult == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
		});

		this.add(btnExit);
	}

	private void initBtnMinimize() {
		btnMinimize = new JButton("-");

		// set properties
		btnMinimize.setBounds(WIDTH - 2 * HEIGHT, 0, HEIGHT, HEIGHT);
		btnMinimize.setBackground(Color.decode(myProps.Color_Teal_Dark));
		btnMinimize.setForeground(Color.WHITE);
		btnMinimize.setFont(myProps.DEFAULT_FONT);

		// flat style
		btnMinimize.setBorderPainted(false);
		btnMinimize.setFocusPainted(false);

		// hover effect
		myProps.BtnHover(btnMinimize, Color.decode(myProps.Color_Teal));

		this.add(btnMinimize);
	}

	public void minimizeAction(JFrame aFrame) {
		btnMinimize.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				aFrame.setState(JFrame.ICONIFIED);
			}
		});
	}

	private void initLblTitle() {
		lblTitle = new JLabel(TITLE);

		lblTitle.setFont(new Font("Arial Rounded MT Bold", Font.ITALIC, 20));
		lblTitle.setBounds(10, 15, 500, 20);
		lblTitle.setForeground(Color.WHITE);

		this.add(lblTitle);
	}
}
