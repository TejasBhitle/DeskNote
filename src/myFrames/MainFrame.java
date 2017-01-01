package myFrames;

import java.awt.BorderLayout;
import java.awt.Color;
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
import myDialogs.AboutDialog;
import myDialogs.AddNoteDialog;
import myInterfaces.AddNoteDialogListener;
import myInterfaces.DataChangeListener;
import myInterfaces.NoteClickListener;
import myPanels.NotesPanel;
import myPanels.ViewNotePanel;

public class MainFrame extends JFrame{
	
	private NotesPanel notesPanel;
	private JScrollPane notesScrollPane;
	private AddNoteDialog addnoteDialog;
	private ViewNotePanel viewNotePanel;
	private ArrayList<NoteData> arrayList;
	private AboutDialog about;
	public MainFrame(){
		super("My Notes");//window title
		
		setVisible(true);
		setSize(1200,500);
		setMinimumSize(new Dimension(1325,700));
		
		//for exiting on Close pressed
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setBackground(Color.decode("#e3f2fd"));
		setJMenuBar(createMenu());
		
		
		//instantiation
		notesScrollPane = new JScrollPane();
		notesPanel = new NotesPanel();
		notesPanel.setPanelLayout();
		viewNotePanel = new ViewNotePanel();
		
		/*setting up the interface*/
		viewNotePanel.setDataChangeListener(new DataChangeListener(){
			@Override
			public void onDataChanged(){
				notesPanel.setPanelContent();
			}
		});
		about=new AboutDialog(this);
		addnoteDialog = new AddNoteDialog(this);
		addnoteDialog.setAddNoteDialogListener(new AddNoteDialogListener(){
			@Override
			public void refreshPanel() {
				notesPanel.setPanelContent();
			}
		});
		
		notesPanel.setNoteClickListener(new NoteClickListener(){
			@Override
			public void onNoteClicked(NoteData noteData) {
				viewNotePanel.setNoteData(noteData);
			}	
		});
		
		
		
		/*Setting up the content for the first time*/
		notesPanel.setPanelContent();
		
		//layout (BorderLayout)
		setLayout(new BorderLayout());
		getContentPane().add(viewNotePanel,BorderLayout.WEST);
		

		/*setting the layout*/
		Dimension dim = new Dimension();
		dim.width =800;
		notesScrollPane.setPreferredSize(dim);
		notesScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		notesScrollPane.setViewportView(notesPanel);
		getContentPane().add(notesScrollPane,BorderLayout.EAST);
		
			
	}
	
	
		private JMenuBar createMenu(){
		JMenuBar menuBar = new JMenuBar();
		JMenu newMenu = new JMenu("Options");
		JMenuItem newnote = new JMenuItem("New Note");
		JMenuItem aboutMenu=new JMenuItem("About");
		newMenu.add(newnote);
		newMenu.add(aboutMenu);
		
		aboutMenu.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event){
				about.showaboutDialog();
			}
		});
	
		
		newnote.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event){
				addnoteDialog.showDialog();
			}
		});
		newnote.setOpaque(true);
		newnote.setBackground(Color.decode("#bbdefb"));
		aboutMenu.setBackground(Color.decode("#bbdefb"));
		menuBar.setBackground(Color.decode("#bbdefb"));
		menuBar.add(newMenu);
		
		return menuBar;
	}
	
	
	
}


