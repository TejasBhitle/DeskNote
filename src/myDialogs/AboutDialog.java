package myDialogs;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class AboutDialog extends JDialog {
	private JTextArea msg;
	private final String MSG="This Project is made by Students of Sardar Patel Institute of Tecnology.\n"+
			"This ia a primitive version of Notes Appliaction For Windows Machine.\n"+
			"It is making use of MYSQL Database for storing data\n"+
			"The UI is constructed using Java Swing"
			+"\nCopy Rights Reserved With Developers"+"\nDevelopment Team:\n"+"Raj Gite\n"+"Tejas Bhitle\nSushmen Chaudhary";
	private JLabel Image1,Image2,Image3,Name1,Name2,Name3;
	public AboutDialog(JFrame Parent){
		super(Parent ,"ABOUT",false);
		setLocationRelativeTo(Parent);
		getContentPane().setBackground(Color.decode("#b39ddb"));
		msg = new JTextArea(MSG,5,2);
		msg.setEditable(false);
		msg.setBackground(Color.decode("#b39ddb"));
		Name1= new JLabel("Raj Gite");
		Name2= new JLabel("Tejas Bhitle");
		Name3= new JLabel("Sushmen Chaudhari");
		setSize(800,400);
		setMaximumSize(new Dimension(600,500));
		ImageIcon icon1 = new ImageIcon("rsz_20160610_162514.jpg");
		Image1= new JLabel(icon1);
		Image1.setSize(10,10);
		ImageIcon icon2 = new ImageIcon("IMG-20161011-WA0001.jpg");
		Image2= new JLabel(icon2);
		Image2.setSize(10,10);
		ImageIcon icon3 = new ImageIcon("sush.jpg");
		Image3= new JLabel(icon3);
		Image3.setSize(10,10);
		setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		gc.gridx=0;
		gc.gridy=2;
		gc.weightx=5;
		gc.weighty=5;
		add(Image1,gc);
		gc.gridx=0;
		gc.gridy=3;
		add(Name1,gc);
		
		gc.gridx=2;
		gc.gridy=2;
		gc.weightx=5;
		gc.weighty=5;
		add(Image2,gc);
		gc.gridx=2;
		gc.gridy=3;
		add(Name2,gc);
		
		gc.gridx=4;
		gc.gridy=2;
		gc.weightx=5;
		gc.weighty=5;
		add(Image3,gc);
		gc.gridx=4;
		gc.gridy=3;
		add(Name3,gc);
		
		gc.gridx=2;
		gc.gridy=0;
		gc.weightx=5;
		gc.weighty=8;
		add(msg,gc);
	}
	public void showaboutDialog()
	{
		this.setVisible(true);
	}
}
