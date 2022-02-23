package gui;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import bus.TaiKhoanBUS;

public class LoginGUI extends javax.swing.JFrame {

	public LoginGUI() {
		initComponents();
		this.setLocationRelativeTo(null);
	}

	// <editor-fold defaultstate="collapsed" desc="Generated
	// Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		jPanel1 = new javax.swing.JPanel();
		pnl_Title = new javax.swing.JPanel();
		lbl_Mini = new javax.swing.JLabel();
		lbl_Title = new javax.swing.JLabel();
		lbl_Exit = new javax.swing.JLabel();
		pnl_Login = new javax.swing.JPanel();
		txtUser = new javax.swing.JTextField();
		lblUser_emtpy = new javax.swing.JLabel();
		txtPass = new javax.swing.JPasswordField();
		lblPass_empty = new javax.swing.JLabel();
		btnLogin = new javax.swing.JButton();
		lbl_User = new javax.swing.JLabel();
		btnReset = new javax.swing.JButton();
		lbl_Pass = new javax.swing.JLabel();
		lbl_icon = new javax.swing.JLabel();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setUndecorated(true);

		pnl_Title.setBackground(new java.awt.Color(0, 153, 153));
		pnl_Title.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
		pnl_Title.setForeground(new java.awt.Color(51, 0, 255));

		lbl_Mini.setFont(new java.awt.Font("Tahoma", 1, 28)); // NOI18N
		lbl_Mini.setForeground(new java.awt.Color(255, 255, 255));
		lbl_Mini.setText("-");
		lbl_Mini.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				lbl_MiniMouseClicked(evt);
			}
		});

		lbl_Title.setFont(new java.awt.Font("Tahoma", 1, 27)); // NOI18N
		lbl_Title.setForeground(new java.awt.Color(255, 255, 255));
		lbl_Title.setText("LOGIN HR MANAGER ");

		lbl_Exit.setFont(new java.awt.Font("Tahoma", 1, 28)); // NOI18N
		lbl_Exit.setForeground(new java.awt.Color(255, 255, 255));
		lbl_Exit.setText("X");
		lbl_Exit.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				lbl_ExitMouseClicked(evt);
			}
		});

		javax.swing.GroupLayout pnl_TitleLayout = new javax.swing.GroupLayout(pnl_Title);
		pnl_Title.setLayout(pnl_TitleLayout);
		pnl_TitleLayout
				.setHorizontalGroup(pnl_TitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
								pnl_TitleLayout.createSequentialGroup()
										.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(lbl_Title, javax.swing.GroupLayout.PREFERRED_SIZE, 304,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(55, 55, 55)
										.addComponent(lbl_Mini, javax.swing.GroupLayout.PREFERRED_SIZE, 27,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(18, 18, 18)
										.addComponent(lbl_Exit, javax.swing.GroupLayout.PREFERRED_SIZE, 27,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(45, 45, 45)));
		pnl_TitleLayout.setVerticalGroup(pnl_TitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(pnl_TitleLayout.createSequentialGroup().addContainerGap(36, Short.MAX_VALUE)
						.addGroup(pnl_TitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(lbl_Title, javax.swing.GroupLayout.PREFERRED_SIZE, 66,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(lbl_Mini, javax.swing.GroupLayout.PREFERRED_SIZE, 34,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(lbl_Exit, javax.swing.GroupLayout.PREFERRED_SIZE, 34,
										javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGap(35, 35, 35)));

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(pnl_Title, javax.swing.GroupLayout.Alignment.TRAILING,
						javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
		jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
						jPanel1Layout.createSequentialGroup().addGap(0, 0, Short.MAX_VALUE).addComponent(pnl_Title,
								javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.PREFERRED_SIZE)));

		pnl_Login.setBackground(new java.awt.Color(163, 217, 217));

		txtUser.setFont(new java.awt.Font("Times New Roman", 0, 22)); // NOI18N

		txtPass.setFont(new java.awt.Font("Times New Roman", 0, 22)); // NOI18N
		txtPass.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				txtPassActionPerformed(evt);
			}
		});

		btnLogin.setBackground(new java.awt.Color(204, 255, 255));
		btnLogin.setFont(new java.awt.Font("Sitka Heading", 1, 25)); // NOI18N
		btnLogin.setText("LOGIN");
		btnLogin.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnLoginActionPerformed(evt);
			}
		});

		lbl_User.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N
		lbl_User.setIcon(new javax.swing.ImageIcon("src\\img\\user.png")); // NOI18N
		lbl_User.setText("Username");

		btnReset.setBackground(new java.awt.Color(204, 255, 255));
		btnReset.setFont(new java.awt.Font("Sitka Heading", 1, 25)); // NOI18N
		btnReset.setText("RESET");
		btnReset.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnResetActionPerformed(evt);
			}
		});

		lbl_Pass.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N
		lbl_Pass.setIcon(new javax.swing.ImageIcon("src\\img\\passwd.png")); // NOI18N
		lbl_Pass.setText("Password");

		lbl_icon.setIcon(new javax.swing.ImageIcon("src\\img\\io.jpg")); // NOI18N

		javax.swing.GroupLayout pnl_LoginLayout = new javax.swing.GroupLayout(pnl_Login);
		pnl_Login.setLayout(pnl_LoginLayout);
		pnl_LoginLayout.setHorizontalGroup(pnl_LoginLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_LoginLayout.createSequentialGroup()
						.addContainerGap(33, Short.MAX_VALUE).addComponent(lbl_icon).addGap(18, 18, 18)
						.addGroup(pnl_LoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(pnl_LoginLayout.createSequentialGroup()
										.addComponent(lbl_User, javax.swing.GroupLayout.PREFERRED_SIZE, 127,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(18, 18, 18).addComponent(txtUser,
												javax.swing.GroupLayout.PREFERRED_SIZE, 260,
												javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGroup(pnl_LoginLayout.createSequentialGroup()
										.addComponent(lbl_Pass, javax.swing.GroupLayout.PREFERRED_SIZE, 127,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(18, 18, 18)
										.addGroup(pnl_LoginLayout
												.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
												.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_LoginLayout
														.createSequentialGroup().addComponent(btnLogin)
														.addPreferredGap(
																javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														.addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE,
																110, javax.swing.GroupLayout.PREFERRED_SIZE))
												.addComponent(txtPass, javax.swing.GroupLayout.PREFERRED_SIZE, 260,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(lblUser_emtpy, javax.swing.GroupLayout.PREFERRED_SIZE,
														187, javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(lblPass_empty, javax.swing.GroupLayout.PREFERRED_SIZE,
														183, javax.swing.GroupLayout.PREFERRED_SIZE))))
						.addGap(32, 32, 32)));
		pnl_LoginLayout.setVerticalGroup(pnl_LoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(pnl_LoginLayout.createSequentialGroup().addGap(65, 65, 65)
						.addGroup(pnl_LoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(lbl_icon, javax.swing.GroupLayout.PREFERRED_SIZE, 187,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGroup(pnl_LoginLayout.createSequentialGroup()
										.addGroup(pnl_LoginLayout
												.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
												.addComponent(txtUser, javax.swing.GroupLayout.PREFERRED_SIZE, 42,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(lbl_User, javax.swing.GroupLayout.PREFERRED_SIZE, 42,
														javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(lblUser_emtpy, javax.swing.GroupLayout.PREFERRED_SIZE, 25,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(28, 28, 28)
										.addGroup(pnl_LoginLayout
												.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(txtPass, javax.swing.GroupLayout.PREFERRED_SIZE, 41,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(lbl_Pass, javax.swing.GroupLayout.PREFERRED_SIZE, 41,
														javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(lblPass_empty, javax.swing.GroupLayout.PREFERRED_SIZE, 25,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(45, 45, 45)
										.addGroup(pnl_LoginLayout
												.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 56,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 56,
														javax.swing.GroupLayout.PREFERRED_SIZE))))
						.addContainerGap(62, Short.MAX_VALUE)));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(pnl_Login, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
						Short.MAX_VALUE)
				.addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
						Short.MAX_VALUE));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(pnl_Login,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)));

		pack();
	}// </editor-fold>//GEN-END:initComponents

	private void txtPassActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_txtPassActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_txtPassActionPerformed

	@SuppressWarnings("deprecation")
	private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnLoginActionPerformed

		if (txtUser.getText().trim().isEmpty() && txtPass.getText().trim().isEmpty()) {
			lblUser_emtpy.setText("Username is Empty!");
			lblUser_emtpy.setForeground(Color.RED);
			
			lblPass_empty.setText("Password is Empty!");
			lblPass_empty.setForeground(Color.RED);
		} else if (txtUser.getText().trim().isEmpty()) {
			lblUser_emtpy.setText("Username is Empty!");
			lblUser_emtpy.setForeground(Color.RED);
		} else if (txtPass.getText().trim().isEmpty()) {
			lblPass_empty.setText("Password is Empty!");
			lblPass_empty.setForeground(Color.RED);
		} else {
			try {
//				MySqlDataAccessHelper conn = new MySqlDataAccessHelper();
//				String sql = "select * from taikhoan where tendangnhap = ? and matkhau = ?";
//				conn.prepare(sql);
//
//				conn.bind(1, txtUser.getText());
//				conn.bind(2, txtPass.getText().trim());
//
//				ResultSet rs = conn.executeQueryPre();
				
				TaiKhoanBUS tkBUS = new TaiKhoanBUS();
				boolean hopLe = tkBUS.Login(txtUser.getText().trim(), txtPass.getText().trim());
				
				if (hopLe) {
					JOptionPane.showMessageDialog(null, "Đăng nhập thành công");
					MainFrame n = new MainFrame();
					n.setVisible(true);
					setVisible(false);
				} else {
					JOptionPane.showMessageDialog(null, "Sai tên đăng nhập hoặc mật khẩu");
					txtUser.setText("");
					txtPass.setText("");
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e);
			}
		}
	}// GEN-LAST:event_btnLoginActionPerformed

	private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnResetActionPerformed

		txtUser.setText("");
		txtPass.setText("");
		lblUser_emtpy.setText("");
		lblPass_empty.setText("");
	}// GEN-LAST:event_btnResetActionPerformed

	private void lbl_ExitMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lbl_ExitMouseClicked
		int dialogResult = JOptionPane.showConfirmDialog(null, // component
				"Do you want to exit?", // message
				"Confirm Exit", // title
				JOptionPane.YES_NO_OPTION, // option type
				JOptionPane.WARNING_MESSAGE // message types
		);

		if (dialogResult == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}// GEN-LAST:event_lbl_ExitMouseClicked

	private void lbl_MiniMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lbl_MiniMouseClicked

		this.setState(JFrame.ICONIFIED);
	}// GEN-LAST:event_lbl_MiniMouseClicked

//	public static void main(String args[]) {
//
//		java.awt.EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				new LoginGUI().setVisible(true);
//
//			}
//		});
//	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton btnLogin;
	private javax.swing.JButton btnReset;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JLabel lblPass_empty;
	private javax.swing.JLabel lblUser_emtpy;
	private javax.swing.JLabel lbl_Exit;
	private javax.swing.JLabel lbl_Mini;
	private javax.swing.JLabel lbl_Pass;
	private javax.swing.JLabel lbl_Title;
	private javax.swing.JLabel lbl_User;
	private javax.swing.JLabel lbl_icon;
	private javax.swing.JPanel pnl_Login;
	private javax.swing.JPanel pnl_Title;
	private javax.swing.JPasswordField txtPass;
	private javax.swing.JTextField txtUser;
	// End of variables declaration//GEN-END:variables
}
