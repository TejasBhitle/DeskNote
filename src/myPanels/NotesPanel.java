package myPanels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;

import javaClasses.NoteData;
import myInterfaces.NoteClickListener;

public class NotesPanel extends JPanel{
	
	private GridBagConstraints gc;
	private ArrayList<NoteData> arraylist;
	private NoteData notedata;
	private Custom_Note_Panel custom_note_panel;
	private JScrollPane jscrollpane;
	private NoteClickListener noteClickListener;
	
	
	
	
	public NotesPanel(){
		
		//initialise from DBhelper at app launch
		
	}
	
	public void setPanelLayout(){
		Dimension dim = new Dimension();
		dim.width=600;
		dim.height=4000;
		setPreferredSize(dim);
		
		//layout
		//setLayout(new GridBagLayout());
		Border innerborder = BorderFactory.createTitledBorder("Notes Panel");
		Border outerborder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		setBorder(BorderFactory.createCompoundBorder(outerborder,innerborder));
		
	}
	
	public void setNoteClickListener(NoteClickListener listener ){ 
		noteClickListener = listener;
		
	}
	
	public void setPanelContent(ArrayList<NoteData> list){
		this.removeAll();
		
		arraylist = list;
		for(int i=0;i<arraylist.size();i++){
			notedata = arraylist.get(i);
			custom_note_panel = new Custom_Note_Panel(notedata);
			custom_note_panel.setNoteListener(noteClickListener);
			add(custom_note_panel);
		}
		
		this.revalidate();
		
	};
	
	

	
	
	
	
	
	
}

