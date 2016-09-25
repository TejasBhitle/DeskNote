package myFrames;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;

import javaClasses.NoteData;
import myDialogs.AddNoteDialog;
import myInterfaces.AddNoteDialogListener;
import myInterfaces.NoteClickListener;
import myPanels.NotesPanel;
import myPanels.ViewNotePanel;

public class MainFrame extends JFrame{
	
	private NotesPanel notesPanel;
	private JScrollPane notesScrollPane;
	private AddNoteDialog addnoteDialog;
	private ViewNotePanel viewNotePanel;

	public MainFrame(){
		super("My Notes App");//window title
		
		setVisible(true);
		setSize(1200,500);
		setMinimumSize(new Dimension(1325,700));
		
		//for exiting on Close pressed
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		setJMenuBar(createMenu());
		
		
		//instantiation
		notesScrollPane = new JScrollPane();
		
		notesPanel = new NotesPanel();
		notesPanel.setPanelLayout();
		
		viewNotePanel = new ViewNotePanel();
		
		
		addnoteDialog = new AddNoteDialog(this);
		addnoteDialog.setAddNoteDialogListener(new AddNoteDialogListener(){
			@Override
			public void getArrayList(ArrayList<NoteData> arraylist) {
				notesPanel.setPanelContent(arraylist);
			}
		});
		
		notesPanel.setNoteClickListener(new NoteClickListener(){
			@Override
			public void onNoteClicked(NoteData noteData) {
				viewNotePanel.setNoteData(noteData);
			}	
		});
		
		
		//layout (BorderLayout)
		setLayout(new BorderLayout());
		getContentPane().add(viewNotePanel,BorderLayout.WEST);
		

		Dimension dim = new Dimension();
		dim.width =800;
		notesScrollPane.setPreferredSize(dim);
		notesScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		notesScrollPane.setViewportView(notesPanel);
		getContentPane().add(notesScrollPane,BorderLayout.EAST);
		
			
	}
	
	
	private JMenuBar createMenu(){
		JMenuBar menuBar = new JMenuBar();
		
		JMenu newMenu = new JMenu("New");
		JMenu settingsMenu = new JMenu("Settings");
		JMenu aboutMenu = new JMenu("About");
		JMenu exitMenu = new JMenu("Exit");
		
		JMenuItem newnote = new JMenuItem("New Note");
		newMenu.add(newnote);
		
		newnote.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event){
				addnoteDialog.showDialog();
			}
		});
		
		menuBar.add(newMenu);
		menuBar.add(settingsMenu);
		menuBar.add(aboutMenu);
		menuBar.add(exitMenu);
		
		return menuBar;
	}
	
	
	
}


