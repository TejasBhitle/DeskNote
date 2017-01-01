package myDialogs;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import javaClasses.DBhelper;
import javaClasses.NoteData;
import myInterfaces.AddNoteDialogListener;

public class AddNoteDialog extends JDialog{
	
	private JButton okButton,cancelButton;
	private JLabel titlelabel,contentlabel;
	private JTextField titleTextField;
	private JTextArea contentTextArea;
	private String enteredTitle,enteredContent,enteredDate;
	private NoteData noteData;
	private JPanel TextPanel;
	
	private ArrayList<NoteData> myArrayList;
	
	private AddNoteDialogListener listener;
	
	public AddNoteDialog(JFrame parent){
		
		super(parent,"Add note",false);
		
		setSize(600,400);
		setLocationRelativeTo(parent);
		getContentPane().setBackground(Color.decode("#dcedc8"));
		//instantiation
		okButton = new JButton("Okay");
		okButton.setBackground(Color.decode("#ffebee"));
		cancelButton = new JButton("Cancel");
		cancelButton.setBackground(Color.decode("#ffebee"));
		

		
		titlelabel = new JLabel("Title");
		contentlabel = new JLabel("Content");
		
		titleTextField = new JTextField(15);
		titleTextField.setBackground(Color.decode("#ffebee"));
		contentTextArea = new JTextArea("",12,30);
		contentTextArea.setBackground(Color.decode("#ffebee"));
		TextPanel =new JPanel();
		TextPanel.setBackground(Color.decode("#ffebee"));
		myArrayList = new ArrayList<>();
		
		setLayout();
		
		
		okButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent actionevent) {
				okayButtonPressed();
			}
		});
		
		cancelButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent actionevent) {
				setVisible(false);
			}
		});
	
		
	}
	
	public void okayButtonPressed(){
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();
	
		
		enteredDate = dateFormat.format(date);
		enteredTitle = titleTextField.getText().toString();
		enteredContent = contentTextArea.getText().toString();
		
		if(enteredContent.matches("")){
			//show dialog that content is empty
			System.out.println("Content is empty");
		}
		else{
			System.out.println(enteredTitle +"\n"+enteredContent +"\n");
			NoteData notedata = new NoteData(enteredTitle,enteredContent,enteredDate);
			
			/*Add to SQL database instead of arrayList*/
			DBhelper.addNote(notedata);
			
			/*myArrayList.add(notedata);*/
			listener.refreshPanel();
			
			/*Also refresh notesPanel */
		}
		
		setVisible(false);//to make dialog disappear after ok clicked.
	}
	
	public void setAddNoteDialogListener(AddNoteDialogListener listener){
		this.listener = listener;
	}
	
	public void setLayout(){
		
		setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		
		gc.gridx=0;
		gc.gridy=0;
		add(titlelabel,gc);
		
		gc.gridx=1;
		gc.gridy=1;
		add(titleTextField,gc);
		
		gc.gridx=0;
		gc.gridy=2;
		add(contentlabel,gc);
		
		gc.gridx=1;
		gc.gridy=3;
		gc.weightx=1.0;
		gc.weighty=2.0;
		TextPanel.add(contentTextArea);
		add(new JScrollPane(TextPanel),gc);
		
		
		gc.gridx=0;
		gc.gridy=8;
		add(okButton,gc);
		
		gc.gridx=2;
		gc.gridy=8;
		add(cancelButton,gc);
		
		
	}
	
	public void showDialog(){
		titleTextField.setText("");
		contentTextArea.setText("");
		this.setVisible(true);
	}
	
	
	

}












