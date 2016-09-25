package javaClasses;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


public class DBhelper {
	public static int srno=1;
	
	public void addNote(NoteData noteData){
	
		String SrNo=Integer.toString(srno);
		String title = noteData.getTitle();
		String content = noteData.getContent();
		String date = noteData.getDate();
		try{
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost/notes","root","root");
			//System.out.println("Connection Success");
			String query="insert into notesinfovalues('"+SrNo+"','"+title+"','"+content+"','"+date+"')";
			Statement stmt=conn.createStatement();
			stmt.execute(query);
			srno++;
		}
		catch(Exception e)
		{	
			System.err.println(e);
		}
	} 

	public ArrayList<NoteData>getNoteData(){
		ArrayList<NoteData> arraylist=new ArrayList<NoteData>();
		try{
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost/notes","root","root");
			//System.out.println("Connection Success");
			String query="SELECT * FROM notesinfo";
			Statement stmt=conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){
				NoteData notedata = new NoteData("","","");
				notedata.setTitle(rs.getString("Title"));
				notedata.setContent(rs.getString("Content"));
				notedata.setDate(rs.getString("Date"));
				arraylist.add(notedata);
			}
		}
		catch(Exception e)
		{	
			System.err.println(e);
		}
		return arraylist;
	}

}

