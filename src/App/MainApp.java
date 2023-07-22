package App;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import Structures.Dictionary;

import javax.swing.SwingConstants;
import java.awt.Font;

public class MainApp {

	private JFrame frmDictionary;
	public static  Dictionary dictionary;
	private JTextField wordField;
	private JTextPane infoBox;
	private JButton editDict;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainApp window = new MainApp();
					window.frmDictionary.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainApp() {
		initialize();
		dictionary = new Dictionary();
		dictionary.initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmDictionary = new JFrame();
		frmDictionary.setTitle("Dictionary");
		frmDictionary.setBounds(100, 100, 450, 408);
		frmDictionary.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		wordField = new JTextField();
		wordField.setColumns(10);
		
		JLabel commandLabel = new JLabel("Enter  a Word");
		commandLabel.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 15));
		commandLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		JButton findButton = new JButton("Find");
		findButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String word = wordField.getText();
				String meaning = dictionary.search(word);
				infoBox.setText(meaning);
			}
		});
		
		infoBox = new JTextPane();
		
		editDict = new JButton("Edit Dictionary");
		editDict.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Edit.start();
			}
		});
		GroupLayout groupLayout = new GroupLayout(frmDictionary.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(wordField, GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(157)
							.addComponent(commandLabel, GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
							.addGap(148))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(infoBox, GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE)))
					.addContainerGap())
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(194)
					.addComponent(findButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(187))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(345, Short.MAX_VALUE)
					.addComponent(editDict))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(commandLabel, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
						.addComponent(editDict))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(wordField, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(findButton)
					.addGap(11)
					.addComponent(infoBox, GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE)
					.addContainerGap())
		);
		frmDictionary.getContentPane().setLayout(groupLayout);
	}
}
