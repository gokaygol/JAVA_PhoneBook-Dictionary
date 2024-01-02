package MyPhoneDict.Screens;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import MyPhoneDict.DataStructures.ListNode;

import static MyPhoneDict.Main.list;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
public class HomeScreen {

	private JFrame frame;
	private JTable table;
	private JButton AddNewPeopleButton;
	private DefaultTableModel model;
	private int selectedRow = 0;
	
	/**
	 * Launch the application.
	 */
	public static void main() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomeScreen window = new HomeScreen();
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
	public HomeScreen() {
		initialize();
	}

	/**
	 * I
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setAlwaysOnTop(true);
		frame.setBounds(100, 100, 883, 433);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
		});
		
		AddNewPeopleButton = new JButton("Add New Person");
		AddNewPeopleButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				System.out.println();
				AddNewUserScreen.main(model);
			}
		});
		
		JButton DeletePersonButton = new JButton("Delete Person");
		DeletePersonButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DefaultTableModel tblModel = (DefaultTableModel) table.getModel();
				if(table.getSelectedRowCount() == 1) {
					int selected = table.getSelectedRow();
					tblModel.removeRow(table.getSelectedRow());
					list.deleteNode(selected);
					list.writeListToFile();
				} else {
					if(table.getRowCount()==0) {
						JOptionPane.showMessageDialog(DeletePersonButton,this,"Table is Empty", selectedRow);
					}else {
						int[] selectedRows = table.getSelectedRows();
						for(int i = selectedRows.length-1;i>=0;i--) {
							tblModel.removeRow(i);
							list.deleteNode(i);
							list.writeListToFile();

						}
					}
					
				}
			}
		});

		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(26, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addComponent(AddNewPeopleButton)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(DeletePersonButton, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE))
						.addComponent(scrollPane, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 816, GroupLayout.PREFERRED_SIZE))
					.addGap(25))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(26)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 315, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(AddNewPeopleButton)
						.addComponent(DeletePersonButton))
					.addContainerGap(28, Short.MAX_VALUE))
		);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Name", "Surname", "PhoneNumber","PhoneType", "Email", "Gender", "Adress", "Birthday"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, Integer.class, Object.class, Object.class, Object.class, Object.class, Object.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		model = (DefaultTableModel) table.getModel();
		
		table.getSelectionModel().addListSelectionListener(
	            new ListSelectionListener() {
				@Override
				public void valueChanged(ListSelectionEvent e) {
					if (!e.getValueIsAdjusting()) {
						selectedRow = table.getSelectedRow();
	                }
				}
	        });
		ListNode node = list.currentNode;
		while(node!=null) {
			String[] isim = {node.getName(), node.getSurname(), node.getPhoneNumber().toString(),node.getPhoneType().toString(), node.getEmail(),node.getSex().toString(), node.getAddress(),node.getBirthday()};
			model.insertRow(0, isim);
			node=node.next;
		}
		scrollPane.setViewportView(table);
		frame.getContentPane().setLayout(groupLayout);
	}
}
