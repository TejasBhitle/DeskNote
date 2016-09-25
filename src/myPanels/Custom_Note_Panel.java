package myPanels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import javaClasses.NoteData;
import myInterfaces.NoteClickListener;

public class Custom_Note_Panel extends JPanel{
	
	private JLabel titleLabel,contentLabel,dateLabel;
	private NoteClickListener listener;
	
	public Custom_Note_Panel(NoteData noteData){
		titleLabel = new JLabel(noteData.getTitle());
		contentLabel = new JLabel(noteData.getContent());
		dateLabel = new JLabel(noteData.getDate());
		setBackground(Color.WHITE);
		
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
		add(contentLabel,BorderLayout.NORTH);
		add(dateLabel,BorderLayout.SOUTH);
		
		/*Mouse Listeners*/
		addMouseListener(new MouseAdapter(){
			@Override
			public void mousePressed(MouseEvent e){
				setBackground(Color.GRAY);
				System.out.println("Mouse Pressed");
				
			}
			@Override
            public void mouseReleased(MouseEvent e) {
				setBackground(Color.WHITE);
				System.out.println("Mouse Releaesd");
				listener.onNoteClicked(noteData);
            }
		});
	
	}
	
	public void setNoteListener(NoteClickListener noteclickListener){
		listener = noteclickListener;
	}
	

}
