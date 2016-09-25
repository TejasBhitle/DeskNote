package javaClasses;
/*
 * This Class is created By Tejas
 * on Date 4/9/16
 * App Version 1.0
 */
import javax.swing.SwingUtilities;

import myFrames.MainFrame;

public class MainClass {

	public static void main(String[] args){
		
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				new MainFrame();
				
			}
		});
	}
}
