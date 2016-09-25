/*This Class
 * is deprecated
 * by Tejas*/

package myPanels;


import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import javaClasses.NoteData;
import myInterfaces.NavPanelListener;

public class NavPanel extends JPanel{
	
	private JLabel title_label,content_label;
	private JTextField title_textfield,content_textfield;
	private JButton addButton;
	private String entered_title,entered_content,entered_date;
	public ArrayList<NoteData> arrayList;
	
	private NavPanelListener navPanelListener;

	public NavPanel(){
		
		Dimension dim = new Dimension();
		dim.width=300;
		setPreferredSize(dim);
		
		//instantiation
		title_label = new JLabel("Title");
		content_label = new JLabel("Content");
		title_textfield = new JTextField(10);
		content_textfield = new JTextField(10);
		addButton = new JButton("Add");
		arrayList = new ArrayList();
		
	
		addButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent actionevent) {
				addButtonPresssed();
			}
			
		});
	}
	
	public void setNavPanelListener(NavPanelListener listener){
		this.navPanelListener = listener;
	}
	
	public void setPanelLayout(){
		setLayout(new GridBagLayout());
		
		Border innerborder = BorderFactory.createTitledBorder("NavPanel");
		Border outerborder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		setBorder(BorderFactory.createCompoundBorder(outerborder,innerborder));
		
	}
	
	public void setPanelContent(){
		GridBagConstraints gc = new GridBagConstraints();
		gc.gridx=0;
		gc.gridy=0;
		add(title_label,gc);
		
		gc.gridx=1;
		gc.gridy=0;
		add(title_textfield,gc);
		
		gc.gridx=0;
		gc.gridy=1;
		add(content_label,gc);
		
		gc.gridx=1;
		gc.gridy=1;
		add(content_textfield,gc);
		
		gc.gridx=0;
		gc.gridy=2;
		add(addButton,gc);
	}
	
	public void addButtonPresssed(){
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();
		NotesPanel notesPanel = new NotesPanel();
		
		entered_date = dateFormat.format(date);
		entered_title = title_textfield.getText().toString();
		entered_content = content_textfield.getText().toString();
		
		if(entered_content.matches("")){
			//show dialog that content is empty
			System.out.println("Content is empty");
		}
		else{
			System.out.println(entered_title +"\n"+entered_content +"\n");
			NoteData notedata = new NoteData(entered_title,entered_content,entered_date);
			/*Add to SQL database instead of arrayList*/
			arrayList.add(notedata);
			navPanelListener.onAddButtonClick(arrayList);
		}
		
	}
	
	

}
