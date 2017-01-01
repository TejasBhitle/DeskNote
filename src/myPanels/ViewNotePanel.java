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

import javaClasses.DBhelper;
import javaClasses.NoteData;
import myDialogs.ConfirmDeleteDialog;
import myInterfaces.DataChangeListener;
import myInterfaces.DeleteDialogListener;

public class ViewNotePanel extends JPanel{
	
	private JTextArea titleTextArea,contentTextArea,dateTextArea;
	private JPanel viewToolBar,editToolBar,mainPanel;
	private JScrollPane scrollPane;
	private NoteData noteData;
	private ConfirmDeleteDialog deleteDialog;
	private DataChangeListener dataChangeListener;

	
	public ViewNotePanel(){ 
		Dimension dim = new Dimension();
		dim.width=500;
		dim.height=500;
		setPreferredSize(dim);
		setBackground(Color.decode("#311b92"));
		Border innerborder = BorderFactory.createEmptyBorder(5,5,5,5);
		Border outerborder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		setBorder(BorderFactory.createCompoundBorder(outerborder,innerborder));
		
		
		/*instantiation*/
		titleTextArea = new JTextArea(1,3);
		contentTextArea = new JTextArea(25,20);
		dateTextArea = new JTextArea(1,3);
		deleteDialog = new ConfirmDeleteDialog((JFrame)this.getParent());
		setNoteData(noteData);
		deleteDialog.setDeleteDialogListener(new DeleteDialogListener(){
		
			@Override
			public void onYesPressed() {
				//deleteFromSql(noteData) or something similar
			
				DBhelper.deleteNote(noteData);
				/*refreshing NotesPanel*/
			
		
				dataChangeListener.onDataChanged();
				
				//close dialog
				deleteDialog.showDialog(false);
				
				closePressed();
			}
		});
		
		/*set Note data*/
		
		
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
	public void setDataChangeListener( DataChangeListener listener)
	{
		this.dataChangeListener = listener;
	}
	private void createMainContentPaneLayout(){
		mainPanel = new JPanel();
		mainPanel.setBackground(Color.decode("#311b92"));
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
		gc.gridx=2;
		gc.gridy=0;
		gc.weighty=1;
		mainPanel.add(dateTextArea,gc);
		gc.gridx=1;
		gc.gridy=2;
		gc.weighty=3;
		mainPanel.add(contentTextArea,gc);
		titleTextArea.setBackground(Color.decode("#e8eaf6"));
		dateTextArea.setBackground(Color.decode("#e8eaf6"));
		contentTextArea.setBackground(Color.decode("#e8eaf6"));
		scrollPane= new JScrollPane();
		//scrollPane.setBackground(Color.PINK);
		//scrollPane.setOpaque(true);
		
		scrollPane.setViewportView(mainPanel);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	}
	
	private void createViewToolBar(){ 
		viewToolBar = new JPanel();
		//Border innerborder1 = BorderFactory.createTitledBorder("View Mode");
		//Border outerborder1 = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		//viewToolBar.setBorder(BorderFactory.createCompoundBorder(outerborder1,innerborder1));
		viewToolBar.setBackground(Color.decode("#311b92"));
		JButton editButton = new JButton("Edit");
		editButton.setBackground(Color.decode("#18ffff"));
		JButton deleteButton = new JButton("Delete");
		deleteButton.setBackground(Color.decode("#18ffff"));
		JButton closeButton = new JButton("Close");
		closeButton.setBackground(Color.decode("#18ffff"));
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
		
		//Border innerborder2 = BorderFactory.createTitledBorder("Edit Mode");
		//Border outerborder2 = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		//editToolBar.setBorder(BorderFactory.createCompoundBorder(outerborder2,innerborder2));
		editToolBar.setBackground(Color.decode("#311b92"));
		JButton saveButton = new JButton("Save");
		saveButton.setBackground(Color.decode("#18ffff"));
		JButton cancelButton = new JButton("Cancel");
		cancelButton.setBackground(Color.decode("#18ffff"));
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

		NoteData edited_notedata = new NoteData(
		titleTextArea.getText().toString(),
		contentTextArea.getText().toString(),
		dateTextArea.getText().toString());
		
		/*update editNoteData to database here*/
	
		DBhelper.editNote(edited_notedata);
		
		/*refreshing NotesPanel*/
		dataChangeListener.onDataChanged();
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
