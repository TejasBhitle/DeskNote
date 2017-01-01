package myPanels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import javaClasses.DBhelper;
import javaClasses.NoteData;
import myInterfaces.NoteClickListener;

public class NotesPanel extends JPanel{
	
	private GridBagConstraints gc;
	private ArrayList<NoteData> arraylist;
	private NoteData notedata;
	private Custom_Note_Panel custom_note_panel;
	private JScrollPane jscrollpane;
	private NoteClickListener noteClickListener;
	private DBhelper dbhelper;
	private boolean isReverse;
	String [] custompanelcolors=new String[8];
	
	public NotesPanel(){
		
		//initialise from DBhelper at app launch
		dbhelper = new DBhelper();
		setBackground(Color.decode("#e3f2fd"));
		
		custompanelcolors[0]="#f44336";
		custompanelcolors[1]="#673ab7";
		custompanelcolors[2]="#2962ff";
		custompanelcolors[3]="#009688";
		custompanelcolors[4]="#ffc107";
		custompanelcolors[5]="#4caf50";
		custompanelcolors[6]="#ff6d00";
		custompanelcolors[7]="#795548";
	}
	
	public void setPanelLayout(){
		Dimension dim = new Dimension();
		dim.width=600;
		dim.height=4000;
		setPreferredSize(dim);
	
		//layout
	//	Border innerborder = BorderFactory.createTitledBorder("Notes Panel");
		//Border outerborder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		//setBorder(BorderFactory.createCompoundBorder(outerborder,innerborder));
		
	}
	
	public void setNoteClickListener(NoteClickListener listener ){ 
		noteClickListener = listener;
		
	}
	
	
	public void setPanelContent(){
		this.removeAll();
		
		arraylist =dbhelper.getNoteData() ;
		
		for(int i=arraylist.size()-1,j=0;i>=0;i--,j=(j%8)){
			notedata = arraylist.get(i);
			custom_note_panel = new Custom_Note_Panel(notedata,custompanelcolors[j]);
			j++;
			custom_note_panel.setNoteListener(noteClickListener);
			add(custom_note_panel);
		}
		
		this.revalidate();
		
	};
	
	

	
	
	
	
	
	
}

