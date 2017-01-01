package javaClasses;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
public class DBhelper {
	
	static int i,j; 
	static public void addNote(NoteData noteData){
	
		String title = noteData.getTitle();
		String content = noteData.getContent();
		String date = noteData.getDate();
		try{
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost/notes1","root","root");
			//System.out.println("Connection Success");
			String query="insert into notesinfo values('"+title+"','"+content+"','"+date+"')";
			Statement stmt=conn.createStatement();
			stmt.execute(query);
		}
		catch(Exception e)
		{	
			System.err.println(e);
		}
	} 

	static public ArrayList<NoteData> getNoteData(){
		ArrayList<NoteData> arraylist=new ArrayList<NoteData>();
		try{
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost/notes1","root","root");
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
	static public void deleteNote(NoteData notedata)
	{	
		try{
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost/notes1","root","root");
			String queryDelete="DELETE FROM notesinfo WHERE Date =?";
			PreparedStatement pstmt=conn.prepareStatement(queryDelete);
			pstmt.setString(1,notedata.getDate());
			pstmt.execute();
		}
		catch(Exception e)
		{	
			System.err.println(e);
		}
	}
	static public void editNote(NoteData notedata)
	{
		try{
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost/notes1","root","root");
			String date=notedata.getDate();
			String queryEdit="UPDATE notesinfo SET Title='"+notedata.getTitle()+"',Content='"+notedata.getContent()+"' WHERE Date='"+date+"'";
			Statement stmt=conn.createStatement();
			stmt.executeUpdate(queryEdit);
		}
		catch(Exception e)
		{
			System.err.println(e);
		}
	}
}
