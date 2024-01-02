package MyPhoneDict.Screens;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;

import MyPhoneDict.DataStructures.ListNode;
import MyPhoneDict.Enums.Gender;
import MyPhoneDict.Enums.PhoneType;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.stream.IntStream;
import java.awt.event.ActionEvent;

import static MyPhoneDict.Main.list;
import javax.swing.DefaultComboBoxModel;

public class AddNewUserScreen {

	private JFrame frame;
	private JTextField NameTextField;
	private JTextField SurnameTextField;
	private JLabel SurnameLabel;
	private JLabel EmailLabel;
	private JTextField EmailTextField;
	private JLabel GenderSelection;
	private JLabel lblAdress;
	private JTextField AdressField;
	private JLabel lblBirthday;
	private JTextField PhoneNumberField;

	/**
	 * Launch the application.
	 */
	public static void main(DefaultTableModel model) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddNewUserScreen window = new AddNewUserScreen(model);
					window.frame.setVisible(true);
					window.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @param model 
	 */
	public AddNewUserScreen(DefaultTableModel model) {
		initialize(model);
	}

	/**
	 * I
	 * @param model 
	 */
	private void initialize(DefaultTableModel model) {
		frame = new JFrame();
		frame.setAlwaysOnTop(true);
		frame.setBounds(100, 100, 567, 582);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel nameLabel = new JLabel("Name :");
		
		
		NameTextField = new JTextField();
		NameTextField.setColumns(10);
		
		SurnameTextField = new JTextField();
		SurnameTextField.setColumns(10);
		
		SurnameLabel = new JLabel("Surname :");
		
		EmailLabel = new JLabel("Email : ");
		
		EmailTextField = new JTextField();
		EmailTextField.setColumns(10);
		
		JComboBox ComboBoxPhoneType = new JComboBox();
		ComboBoxPhoneType.setModel(new DefaultComboBoxModel(PhoneType.values()));
		
		GenderSelection = new JLabel("Gender :");
		
		JRadioButton MaleButton = new JRadioButton("Male");
		
		
		JRadioButton FemaleButton = new JRadioButton("Female");
		
		JRadioButton OtherGendersButton = new JRadioButton("Other");
		
		MaleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FemaleButton.setSelected(false);
				OtherGendersButton.setSelected(false);
			}
		});
		
		FemaleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MaleButton.setSelected(false);
				OtherGendersButton.setSelected(false);
			}
		});
		
		OtherGendersButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MaleButton.setSelected(false);
				FemaleButton.setSelected(false);
			}
		});
		
		lblAdress = new JLabel("Adress :");
		
		AdressField = new JTextField();
		AdressField.setColumns(10);
		
		lblBirthday = new JLabel("Birthday :");
		
		JComboBox dayComboBox = new JComboBox();
		dayComboBox.setMaximumRowCount(31);
		
		JComboBox monthComboBox = new JComboBox();
		monthComboBox.setMaximumRowCount(31);
		
		JComboBox yearComboBox = new JComboBox();
		
		for(int i=1950;i<=2022;i++){
			yearComboBox.addItem(i);
		}
		
		for(int i=1;i<=31;i++){
			dayComboBox.addItem(i);
		}
		
		for(int i=1;i<=12;i++){
			monthComboBox.addItem(i);
		}
		
		JLabel PhoneNumberLabel = new JLabel("PhoneNumber : ");
		
		PhoneNumberField = new JTextField();
		PhoneNumberField.setColumns(10);
		
		JButton SaveButton = new JButton("Save");
		SaveButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				String name = NameTextField.getText();

				String surname = SurnameTextField.getText();

				String phoneNumber = PhoneNumberField.getText();

				PhoneType phoneType = (PhoneType) ComboBoxPhoneType.getSelectedItem();

			Gender gender = Gender.Other;
			if(MaleButton.isSelected()) {
				gender = Gender.Male;
			} else if(FemaleButton.isSelected()) {
				gender = Gender.Famele;
			}

				String email = EmailTextField.getText();

				String adress = AdressField.getText();
				
				String day = dayComboBox.getSelectedItem().toString();
				String month = monthComboBox.getSelectedItem().toString();
				String year = yearComboBox.getSelectedItem().toString();
				
				String birthDay = day + "/" + month + "/"+ year;

			    list.addToFront(name, surname, phoneNumber, phoneType, gender, email, birthDay, adress);
				list.writeListToFile();
				ListNode newNode = list.currentNode;
					String[] isim = {newNode.getName(), newNode.getSurname(), newNode.getPhoneNumber().toString(),newNode.getPhoneType().toString(), newNode.getEmail(),newNode.getSex().toString(), newNode.getAddress(),newNode.getBirthday()};

					model.addRow(isim);
		}
	});
		
		JLabel lblPhoneType = new JLabel("Phone Type");
		
		
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(120)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblBirthday, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(dayComboBox, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(monthComboBox, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(yearComboBox, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
							.addComponent(SaveButton)
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(lblAdress, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(AdressField, GroupLayout.PREFERRED_SIZE, 323, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
											.addComponent(nameLabel)
											.addComponent(SurnameLabel, GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE)
											.addComponent(PhoneNumberLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
										.addGroup(groupLayout.createSequentialGroup()
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(GenderSelection)
											.addGap(16)
											.addComponent(MaleButton))
										.addComponent(EmailLabel, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE))
									.addGap(18)
									.addComponent(FemaleButton))
								.addComponent(lblPhoneType, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE))
							.addGap(14)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(ComboBoxPhoneType, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(OtherGendersButton)
								.addComponent(EmailTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(PhoneNumberField, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
								.addComponent(NameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(SurnameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addGap(210))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(66)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(nameLabel)
						.addComponent(NameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(12)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(SurnameLabel)
						.addComponent(SurnameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(PhoneNumberLabel)
						.addComponent(PhoneNumberField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(12)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(EmailTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(EmailLabel))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPhoneType)
						.addComponent(ComboBoxPhoneType, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(15)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(GenderSelection)
						.addComponent(MaleButton)
						.addComponent(FemaleButton)
						.addComponent(OtherGendersButton))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAdress)
						.addComponent(AdressField, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE))
					.addGap(36)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblBirthday)
						.addComponent(monthComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(yearComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(dayComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(70)
					.addComponent(SaveButton)
					.addContainerGap(43, Short.MAX_VALUE))
		);
		frame.getContentPane().setLayout(groupLayout);
	}
}
