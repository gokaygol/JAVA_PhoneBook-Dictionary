package MyPhoneDict.Screens;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class PasswordScreen {

	private JFrame frame;
	private JPasswordField textField;

	/**
	 * Launch the application.
	 */
	public static void main() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PasswordScreen window = new PasswordScreen();
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
	public PasswordScreen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 456, 103);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel passwordLabel = new JLabel("Password:");
		passwordLabel.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(passwordLabel, BorderLayout.NORTH);
		
		textField = new JPasswordField();
		frame.getContentPane().add(textField, BorderLayout.CENTER);
		textField.setColumns(10);
		
		JButton loginButton = new JButton("Login");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				File fileTxt = new File(".\\pass.txt");
				
				try {
					Scanner s = new Scanner(fileTxt);
					
					String scannedHashedPass = s.nextLine();
					
					s.close();
					
					MessageDigest md = MessageDigest.getInstance("SHA-256");
					md.update(String.valueOf(textField.getPassword()).getBytes());
					
					byte [] digest = md.digest();
					StringBuilder sb = new StringBuilder();
					for (byte b : digest) {
						sb.append(String.format("%02x",b & 0xff));
					}
					
					if(sb.toString().equals(scannedHashedPass)) {
						System.out.println("Logged in");

						frame.setVisible(false);

						HomeScreen.main();
					} else {
						System.out.println("Wrong Password");
					}
				} catch (FileNotFoundException | NoSuchAlgorithmException e) {
					e.printStackTrace();
				}
			}
		});
		
		frame.getContentPane().add(loginButton, BorderLayout.SOUTH);
	}

}
