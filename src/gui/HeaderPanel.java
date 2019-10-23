package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class HeaderPanel extends JPanel {
	JButton btnExit;
	JButton btnMinimize;
	JLabel lblTitle;
	
	public final int WIDTH = MyProperties.DEFAULT_WIDTH;
	public final int HEIGHT = 50;
	
	public HeaderPanel() {
		this.setLayout(null);
		this.setBackground(Color.blue);
		
		this.setSize(WIDTH, HEIGHT);
		
		initBtnExit();
		
		initBtnMinimize();
	}
	
	private void initBtnExit() {
		btnExit = new JButton("X");
		
		btnExit.setBounds(WIDTH - HEIGHT, 0, HEIGHT, HEIGHT);
		btnExit.setBackground(Color.BLACK);
		btnExit.setForeground(Color.WHITE);
		
		btnExit.setBorderPainted(false);
		btnExit.setFocusPainted(false);
//		btnExit.setContentAreaFilled(false);
		
		btnExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed (ActionEvent e) {
				System.exit(0);
			}
		});
		
		btnExit.addMouseListener(new MouseAdapter() {
		    public void mouseEntered(MouseEvent evt) {
		    	btnExit.setBackground(Color.GRAY);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	btnExit.setBackground(Color.BLACK);
		    }
		});
		
		this.add(btnExit);
	}
	
	private void initBtnMinimize() {
		btnMinimize = new JButton("-");
		
		btnMinimize.setBounds(WIDTH - 2*HEIGHT, 0, HEIGHT, HEIGHT);
		btnMinimize.setBackground(Color.BLACK);
		btnMinimize.setForeground(Color.WHITE);
		
		btnMinimize.setBorderPainted(false);
		btnMinimize.setFocusPainted(false);
		
		btnMinimize.addMouseListener(new MouseAdapter() {
		    public void mouseEntered(MouseEvent evt) {
		    	btnMinimize.setBackground(Color.GRAY);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	btnMinimize.setBackground(Color.BLACK);
		    }
		});
		
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
}
