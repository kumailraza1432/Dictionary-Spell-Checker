package App;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import DataHandling.DataHandle;
import Structures.dictComp;
import javax.swing.JTabbedPane;

public class Edit {

	private static JFrame frmEditDict;
	private static JTextField wordField;
	private static JTextPane meaningField;
	/**
	 * Accessing Application from another JFrame
	 */
	public static void start()
	{
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					if(frmEditDict != null)
						frmEditDict.dispose();
					Edit window = new Edit();
					window.frmEditDict.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Create the application.
	 */
	public Edit() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmEditDict = new JFrame();
		frmEditDict.setResizable(false);
		frmEditDict.setTitle("Edit the Dictionary");
		frmEditDict.setBounds(100, 100, 450, 300);
		frmEditDict.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmEditDict.getContentPane().setLayout(null);
		
		JLabel enterWord = new JLabel("Enter the Word: ");
		enterWord.setBounds(23, 11, 344, 20);
		frmEditDict.getContentPane().add(enterWord);
		
		wordField = new JTextField();
		wordField.setBounds(23, 34, 401, 28);
		frmEditDict.getContentPane().add(wordField);
		wordField.setColumns(10);
		//meaningField.setColumns(10);
		
		JLabel enterMeaning = new JLabel("Enter Meaning: (Meaning Field is not used in delete)");
		enterMeaning.setBounds(23, 73, 365, 14);
		frmEditDict.getContentPane().add(enterMeaning);
		
		meaningField = new JTextPane();
		meaningField.setBounds(23, 98, 401, 105);
		frmEditDict.getContentPane().add(meaningField);
		
		JButton insertButton = new JButton("Insert");
		insertButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String word = wordField.getText();
				String meaning = meaningField.getText();
				
				String wordSplit[] = word.split("[\n ]+");
				word = "";
				for(int i = 0; i < wordSplit.length; i++)
					word = word + " " + wordSplit[i];
				word = word.substring(1);
				
				String meaningSplit[] = meaning.split("[\n ]+");
				meaning = "";
				for(int i = 0; i < meaningSplit.length; i++)
					meaning = meaning + " " + meaningSplit[i];
				meaning = meaning.substring(1);
				MainApp.dictionary.insert(word, meaning);
				String lineToAdd = word + "  " + meaning;
				DataHandle.addLine(lineToAdd, "src/dictionary.dat");
			}
		});
		insertButton.setBounds(115, 215, 89, 23);
		frmEditDict.getContentPane().add(insertButton);
		
		JButton btnNewButton = new JButton("Delete");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String word = wordField.getText();
				MainApp.dictionary.delete(word);
				DataHandle.overWrite(MainApp.dictionary, "src/dictionary.dat");
			}
		});
		btnNewButton.setBounds(229, 214, 89, 23);
		frmEditDict.getContentPane().add(btnNewButton);
	}
}
