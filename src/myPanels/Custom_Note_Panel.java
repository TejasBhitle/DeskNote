package myPanels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.Border;

import javaClasses.NoteData;
import myInterfaces.NoteClickListener;

public class Custom_Note_Panel extends JPanel{
	
	private JLabel titleLabel,dateLabel;
	private JTextArea contentTextArea;
	private NoteClickListener listener;
	
	public Custom_Note_Panel(NoteData noteData,String colour){
		titleLabel = new JLabel(noteData.getTitle());
		contentTextArea= new JTextArea(noteData.getContent(),5,5);
		contentTextArea.setEditable(false);
		dateLabel = new JLabel(noteData.getDate());
		contentTextArea.setBackground(Color.decode(colour));
		setBackground(Color.decode(colour));
		
		Dimension dim = new Dimension();
		dim.width=300;
		dim.height=150;
		setPreferredSize(dim);
		
		/*Set Layout to Panel*/
		setLayout(new BorderLayout());
		Border innerborder = BorderFactory.createTitledBorder(noteData.getTitle());
		Border outerborder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		setBorder(BorderFactory.createCompoundBorder(outerborder,innerborder));
		
		/*Set Panel Content*/
		add(contentTextArea,BorderLayout.NORTH);
		add(dateLabel,BorderLayout.SOUTH);
		
		/*Mouse Listeners*/
		addMouseListener(new MouseAdapter(){
			@Override
			public void mousePressed(MouseEvent e){
				setBackground(Color.red);
				//System.out.println("Mouse Pressed");
				
			}
			@Override
            public void mouseReleased(MouseEvent e) {
				setBackground(Color.decode(colour));
				//System.out.println("Mouse Releaesd");
				listener.onNoteClicked(noteData);
            }
		});
		
		contentTextArea.addMouseListener(new MouseAdapter(){
			@Override
			public void mousePressed(MouseEvent e){
				setBackground(Color.red);
				//System.out.println("Mouse Pressed");
				
			}
			@Override
            public void mouseReleased(MouseEvent e) {
				setBackground(Color.decode(colour));
				//System.out.println("Mouse Releaesd");
				listener.onNoteClicked(noteData);
            }
		});
	
	}
	
	public void setNoteListener(NoteClickListener noteclickListener){
		listener = noteclickListener;
	}
	

}
