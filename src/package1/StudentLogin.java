package package1;

import java.sql.*;
import java.util.ArrayList;

import java.util.Scanner;

public class StudentLogin {
	
	public static Connection con;
	PreparedStatement ps;
	ResultSet rs;
	Statement s;
	Scanner sc=new Scanner(System.in);

	public StudentLogin()throws ClassNotFoundException,SQLException                    /*use the student class constructor to connect with the database*/
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		String user="root";
		String pass="";
		String url="jdbc:mysql://localhost:3306/student";              
		con=DriverManager.getConnection(url,user,pass);
		System.out.println("Connected with database");
		
	}
	
	public boolean validateuser(String username,String password) throws SQLException  /* method to validate the username and password returns boolean*/
	{
		boolean b=false;
		String validatequery="select *from userinfo where username=?";
		
		ps=con.prepareStatement(validatequery);
		ps.setString(1, username);
		rs=ps.executeQuery();
		while(rs.next())
		{
			if(password.equals(rs.getString("password")))
			{
				b=true;
				
				
			}

		}
		return b;
		
	}
	
	public void changepassword(String user, String email)throws SQLException      /* method to change the password */
	{
		
		String checkemail="select *from userinfo where username=?";
		String updatepass="update userinfo set password=? where username=?";
		ps=con.prepareStatement(checkemail);
		ps.setString(1, user);
		rs=ps.executeQuery();
		
		while(rs.next())
		{
			if(email.equals(rs.getString("email")))
			{
				ps=con.prepareStatement(updatepass);
				System.out.println("Enter a new password");
				String newpass=sc.next();
				ps.setString(1, newpass);
				ps.setString(2, user);
				int res=ps.executeUpdate();
				if(res!=0)
				{
					System.out.println("Password updated");
				}
				else
				{
					System.out.println("Password update failed");
				}
				
			}
			else
			{
				System.out.println("Invalid email");
			}
			
		}
			
	}
	
	
	
	
	public ArrayList<Studentpersonaldetail> viewpersonalinfo(String username) throws SQLException
	{
		String stdidq="select id from userinfo where username=?"; // use this query to get the id of the student using username
		
		ps=con.prepareStatement(stdidq);
		ps.setString(1, username);
		rs=ps.executeQuery();
		int sid=0;
		while(rs.next())
		{
			sid=rs.getInt("id");                       // Store the student id in a variable 
		}
		ArrayList<Studentpersonaldetail> sp=new ArrayList<Studentpersonaldetail>();
		String personaldetail="select *from studentpersonaldetails where sid=?";
		ps=con.prepareStatement(personaldetail);
		ps.setInt(1, sid);
		rs=ps.executeQuery();
		System.out.println("======================");
		System.out.println("Personal details");
		System.out.println("-------------------------");
		System.out.println();
		
		while(rs.next())
		{
			Studentpersonaldetail spdetail=new Studentpersonaldetail(rs.getInt("sid"),rs.getString("name"),rs.getString("location"),rs.getLong("mobno"));
			sp.add(spdetail);

		}
		return sp;
		
	}
	
	public ArrayList<ECA> vieweca(String username) throws SQLException
	{
		String stdidq="select id from userinfo where username=?"; // use this query to get the id of the student using username
		
		ps=con.prepareStatement(stdidq);
		ps.setString(1, username);
		rs=ps.executeQuery();
		int sid=0;
		while(rs.next())
		{
			sid=rs.getInt("id");                       // Store the student id in a variable 
		}
		ArrayList<ECA> ec=new ArrayList<ECA>();
		String ecaq="Select name,sports,clubs from studentpersonaldetails inner join extracurricularactivity on sid=eid where sid=?";
		ps=con.prepareStatement(ecaq);
		ps.setInt(1, sid);
		rs=ps.executeQuery();
		System.out.println("======================");
		System.out.println("Extracurricular activity");
		System.out.println("-------------------------");
		System.out.println();
		
		
		while(rs.next())
		{
			ECA e=new ECA(rs.getString("name"),rs.getString("sports"),rs.getString("clubs"));
			ec.add(e);
			
		}
		return ec;
		

	}
	
	
	public ArrayList<Library> viewlibrary(String username) throws SQLException
	{
		String stdidq="select id from userinfo where username=?"; // use this query to get the id of the student using username
		
		ps=con.prepareStatement(stdidq);
		ps.setString(1, username);
		rs=ps.executeQuery();
		int sid=0;
		while(rs.next())
		{
			sid=rs.getInt("id");                       // Store the student id in a variable 
		}
		
		ArrayList<Library> li=new ArrayList<Library>();
		
		String libinfo="select name,booksreferred,dueamount from studentpersonaldetails inner join libraryinfo on sid=libid where sid=? ";
		ps=con.prepareStatement(libinfo);
		ps.setInt(1, sid);
		rs=ps.executeQuery();
		System.out.println("======================");
		System.out.println("Library info");
		System.out.println("-------------------------");
		System.out.println();
		
		while(rs.next())
		{
			Library lib=new Library(rs.getString("name"),rs.getInt("booksreferred"),rs.getInt("dueamount"));
			li.add(lib);
			
		}
		return li;	
		
	}
	
	public ArrayList<Studentmarks> viewmarks(String username) throws SQLException
	{
		String stdidq="select id from userinfo where username=?"; // use this query to get the id of the student using username
		
		ps=con.prepareStatement(stdidq);
		ps.setString(1, username);
		rs=ps.executeQuery();
		int sid=0;
		while(rs.next())
		{
			sid=rs.getInt("id");                       // Store the student id in a variable 
		}
		
		ArrayList<Studentmarks> sm=new ArrayList<Studentmarks>();
		
		String results="select name,department,sub1mark,sub2mark,sub3mark,sub4mark,sub5mark,avg from studentpersonaldetails "
				+ "inner join studentmarks on sid=regno where sid=?";
		ps=con.prepareStatement(results);
		ps.setInt(1, sid);
		rs=ps.executeQuery();
		System.out.println("======================");
		System.out.println("Exam Results");
		System.out.println("-------------------------");
		System.out.println();
		
		while(rs.next())
		{
			Studentmarks smark=new Studentmarks(rs.getString("name"),rs.getString("department"),rs.getInt("sub1mark"),rs.getInt("sub2mark"),
					rs.getInt("sub3mark"),rs.getInt("sub4mark"),rs.getInt("sub5mark"),rs.getFloat("avg"));
			
			sm.add(smark);	
		}
		return sm;
		
		
	}
	
	

}
