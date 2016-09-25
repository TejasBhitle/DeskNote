package myPanels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;

import javaClasses.NoteData;
import myDialogs.ConfirmDeleteDialog;
import myInterfaces.DeleteDialogListener;

public class ViewNotePanel extends JPanel{
	
	private JTextArea titleTextArea,contentTextArea,dateTextArea;
	private JPanel viewToolBar,editToolBar,mainPanel;
	private JScrollPane scrollPane;
	private NoteData noteData;
	private ConfirmDeleteDialog deleteDialog;
	
	public ViewNotePanel(){ 
		Dimension dim = new Dimension();
		dim.width=500;
		setPreferredSize(dim);
		setBackground(Color.WHITE);
		Border innerborder = BorderFactory.createTitledBorder("View Note Panel");
		Border outerborder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		setBorder(BorderFactory.createCompoundBorder(outerborder,innerborder));
		
		
		/*instantiation*/
		titleTextArea = new JTextArea();
		contentTextArea = new JTextArea();
		dateTextArea = new JTextArea();
		deleteDialog = new ConfirmDeleteDialog((JFrame)this.getParent());
		deleteDialog.setDeleteDialogListener(new DeleteDialogListener(){

			@Override
			public void onYesPressed() {
				// TODO Auto-generated method stub
				
			}

			
		});
		
		/*set Note data*/
		setNoteData(noteData);
		
		setEditMode(false);
		dateTextArea.setEditable(false);//always
		
		
		/*Main ContentPane*/
		createMainContentPaneLayout();
		
		/*View ToolBar*/
		createViewToolBar();
		
		/*Edit TooLBar*/
		createEditToolBar();
		
		/*Set Layout*/
		setViewlayout();
		
		
	}
	
	public void setNoteData(NoteData note){
		this.noteData = note;
		if(noteData == null){
			this.setVisible(false);
		}
		else{
			this.setVisible(true);
			titleTextArea.setText(noteData.getTitle());
			contentTextArea.setText(noteData.getContent());
			dateTextArea.setText(noteData.getDate());
		}
	}
	
	private void createMainContentPaneLayout(){
		mainPanel = new JPanel();
		int height = titleTextArea.getHeight()+ contentTextArea.getHeight() + dateTextArea.getHeight() +20;
		Dimension dim1 = new Dimension();
		dim1.height = height;
		mainPanel.setSize(dim1);
		mainPanel.setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		gc.gridx=0;
		gc.gridy=0;
		gc.weighty=1;
		mainPanel.add(titleTextArea,gc);
		gc.gridx=0;
		gc.gridy=1;
		gc.weighty=1;
		mainPanel.add(dateTextArea,gc);
		gc.gridx=0;
		gc.gridy=2;
		gc.weighty=3;
		mainPanel.add(contentTextArea,gc);
		scrollPane= new JScrollPane();
		scrollPane.setViewportView(mainPanel);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	}
	
	private void createViewToolBar(){ 
		viewToolBar = new JPanel();
		Border innerborder1 = BorderFactory.createTitledBorder("View Mode");
		Border outerborder1 = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		viewToolBar.setBorder(BorderFactory.createCompoundBorder(outerborder1,innerborder1));
		viewToolBar.setBackground(Color.GRAY);
		JButton editButton = new JButton("Edit");
		JButton deleteButton = new JButton("Delete");
		JButton closeButton = new JButton("Close");
		
		editButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				editPressed();
			}
			
		});
		
		deleteButton.addActionListener(new ActionListener(){ 
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				deletePressed();
			}
			
		});
		
		closeButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				closePressed();
			}
			
		});
		
		viewToolBar.add(editButton);
		viewToolBar.add(deleteButton);
		viewToolBar.add(closeButton);
	}
	
	private void createEditToolBar(){
		editToolBar = new JPanel();
		Border innerborder2 = BorderFactory.createTitledBorder("Edit Mode");
		Border outerborder2 = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		editToolBar.setBorder(BorderFactory.createCompoundBorder(outerborder2,innerborder2));
		editToolBar.setBackground(Color.GRAY);
		JButton saveButton = new JButton("Save");
		JButton cancelButton = new JButton("Cancel");
		saveButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				savePressed();
			}
			
		});
		cancelButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				cancelPressed();
			}
			
		});
		editToolBar.add(saveButton);
		editToolBar.add(cancelButton);
	}
	
	private void setViewlayout(){
		this.removeAll();
		this.repaint();
		this.setLayout(new BorderLayout());
		this.add(viewToolBar,BorderLayout.NORTH);
		this.add(scrollPane,BorderLayout.CENTER);
		this.revalidate();
	}
	
	private void setEditLayout(){
		this.removeAll();
		this.repaint();
		this.setLayout(new BorderLayout());
		this.add(editToolBar,BorderLayout.NORTH);
		this.add(scrollPane,BorderLayout.CENTER);
		this.revalidate();
	}
	
	private void editPressed(){
		/*Change to editToolBar*/
		setEditLayout();
		setEditMode(true);
		
	}
	
	private void deletePressed(){
		/*create confirm dialog to delete*/
		deleteDialog.showDialog(true);
	}
	
	private void closePressed(){ 
		this.setVisible(false);
	}
	
	private void savePressed(){
		/*Save changes
		 * cancelPressed*/
		setViewlayout();
		setEditMode(false);
	}
	
	private void cancelPressed(){
		/*Change to viewToolBar*/
		setViewlayout();
		setEditMode(false);
	}
	
	public void setEditMode(boolean val){
		titleTextArea.setEditable(val);
		contentTextArea.setEditable(val);
	}
	
	

}
