package myDialogs;

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
import javax.swing.JTextField;

import javaClasses.NoteData;
import myInterfaces.AddNoteDialogListener;
import myPanels.NotesPanel;

public class AddNoteDialog extends JDialog{
	
	private JButton okButton,cancelButton;
	private JLabel titlelabel,contentlabel;
	private JTextField titleTextField, contentTextField;
	private String enteredTitle,enteredContent,enteredDate;
	private NoteData noteData;
	
	private ArrayList<NoteData> myArrayList;
	
	private AddNoteDialogListener listener;
	
	public AddNoteDialog(JFrame parent){
		
		super(parent,"Add note",false);
		
		setSize(400,300);
		setLocationRelativeTo(parent);
		
		//instantiation
		okButton = new JButton("Okay");
		cancelButton = new JButton("Cancel");
		
		titlelabel = new JLabel("Title");
		contentlabel = new JLabel("Content");
		
		titleTextField = new JTextField(10);
		contentTextField = new JTextField(20);
		
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
		NotesPanel notesPanel = new NotesPanel();
		
		enteredDate = dateFormat.format(date);
		enteredTitle = titleTextField.getText().toString();
		enteredContent = contentTextField.getText().toString();
		
		if(enteredContent.matches("")){
			//show dialog that content is empty
			System.out.println("Content is empty");
		}
		else{
			System.out.println(enteredTitle +"\n"+enteredContent +"\n");
			NoteData notedata = new NoteData(enteredTitle,enteredContent,enteredDate);
			
			/*Add to SQL database instead of arrayList*/
			
			myArrayList.add(notedata);
			listener.getArrayList(myArrayList);
			
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
		gc.gridy=0;
		add(titleTextField,gc);
		
		gc.gridx=0;
		gc.gridy=1;
		add(contentlabel,gc);
		
		gc.gridx=1;
		gc.gridy=1;
		add(contentTextField,gc);
		
		gc.gridx=0;
		gc.gridy=2;
		add(okButton,gc);
		
		gc.gridx=1;
		gc.gridy=2;
		add(cancelButton,gc);
		
		
	}
	
	public void showDialog(){
		titleTextField.setText("");
		contentTextField.setText("");
		this.setVisible(true);
	}
	
	
	

}












