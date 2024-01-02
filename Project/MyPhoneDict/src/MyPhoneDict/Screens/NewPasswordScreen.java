package MyPhoneDict.Screens;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class NewPasswordScreen {

	private JFrame frame;
	private JPasswordField NewPasswordTextField;
	private JPasswordField ConfirmPasswordTextField;
	private JLabel ConfirmPassword;

	/**
	 * Launch the application.
	 */
	public static void main() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewPasswordScreen window = new NewPasswordScreen();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public NewPasswordScreen() {
		initialize();
	}

	/**
	 * I
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setAlwaysOnTop(true);
		frame.setBounds(100, 100, 552, 269);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel NewPassword = new JLabel("Enter New Password :");
		
		NewPasswordTextField = new JPasswordField();
		NewPasswordTextField.setColumns(10);
		
		ConfirmPasswordTextField = new JPasswordField();
		ConfirmPasswordTextField.setColumns(10);
		
		ConfirmPassword = new JLabel("Confirm Password :");
		
		JLabel PassWarnLabel = new JLabel("Please make sure your passwords match!");
		PassWarnLabel.setVisible(false);
		
		JButton SaveButton = new JButton("Save");
		SaveButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				
				if (String.valueOf(NewPasswordTextField.getPassword()).equals(String.valueOf(ConfirmPasswordTextField.getPassword()))) {
					String pass = String.valueOf(NewPasswordTextField.getPassword());
					
					try {
						MessageDigest md = MessageDigest.getInstance("SHA-256");
						md.update(pass.getBytes());
						
						byte [] digest = md.digest();
						StringBuilder sb = new StringBuilder();
						for (byte b : digest) {
							sb.append(String.format("%02x",b & 0xff));
						}
						System.out.println("SHA256 Hash:"+ sb.toString());
						
						pass = sb.toString();
						
						BufferedWriter writer = new BufferedWriter(new FileWriter(".\\pass.txt"));
						
						writer.write(pass);
						
						writer.close();
					} catch(IOException | NoSuchAlgorithmException e) {
						e.printStackTrace();
					}

					System.out.println("Password set");

					frame.setVisible(false);

					PasswordScreen.main();

				} else {
					PassWarnLabel.setVisible(true);
				}
		
		}
	});
		
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(57, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(NewPassword)
								.addComponent(ConfirmPassword, GroupLayout.PREFERRED_SIZE, 77, Short.MAX_VALUE))
							.addGap(84)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(ConfirmPasswordTextField)
								.addComponent(NewPasswordTextField, GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE))
							.addGap(131))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(PassWarnLabel)
							.addGap(56)
							.addComponent(SaveButton)
							.addGap(141))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(66)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(NewPassword)
						.addComponent(NewPasswordTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(12)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(ConfirmPassword)
						.addComponent(ConfirmPasswordTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(53)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(SaveButton)
						.addComponent(PassWarnLabel))
					.addContainerGap(36, Short.MAX_VALUE))
		);
		frame.getContentPane().setLayout(groupLayout);
	}
}
