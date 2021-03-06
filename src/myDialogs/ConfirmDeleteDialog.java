package myDialogs;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

import myInterfaces.DeleteDialogListener;

public class ConfirmDeleteDialog extends JDialog{
	
	private JLabel Msg;
	private JButton yes;
	private DeleteDialogListener listener;

	private final String MSG = "Are you sure you want to Delete ?";
			
	
	public ConfirmDeleteDialog(JFrame parent ){
		super(parent ,"Confirmation ",false);
		
		setSize(400,200);
		setLocationRelativeTo(parent);
		getContentPane().setBackground(Color.decode("#bbdefb"));
		Msg = new JLabel(MSG);
		yes = new JButton("Yes");
		yes.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// delete it
				listener.onYesPressed();
			}
		});
		
		
		setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		gc.gridx=0;
		gc.gridy=0;
		add(Msg,gc);
		gc.gridx=0;
		gc.gridy=1;
		add(yes,gc);
	}
	
	
	public void setDeleteDialogListener(DeleteDialogListener deleteDialogListener){
		this.listener = deleteDialogListener;
	}
	
	public void showDialog(boolean val){
		this.setVisible(val);
	}

}










