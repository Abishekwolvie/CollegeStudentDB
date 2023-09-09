package package1;
import java.sql.*;
import java.util.ArrayList;

public class Facultylogin extends StudentLogin{                        /* create faculty class by extending the student class*/
	
	Facultylogin()throws SQLException, ClassNotFoundException			/* use constructor to invoke the connection*/
	{
		super();
	}
	
	
	public ArrayList<Studentpersonaldetail> viewpersonalinfo() throws SQLException
	{
		System.out.println("Enter the Student id");
		int sid=sc.nextInt();
		ArrayList<Studentpersonaldetail> sp=new ArrayList<Studentpersonaldetail>();
		
		ps=con.prepareStatement("select *from userinfo where id=?");
		ps.setInt(1, sid);
		rs=ps.executeQuery();
		if(rs.next())
		{
			
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
			
			
		}
		else
		{
			System.out.println("Invalid id");
		}
		return sp;

		
	}
	
	
	public ArrayList<ECA> vieweca() throws SQLException
	{
		
		System.out.println("Enter the Student id");
		int sid=sc.nextInt();
		ArrayList<ECA> ec=new ArrayList<ECA>();
		ps=con.prepareStatement("select *from userinfo where id=?");
		ps.setInt(1, sid);
		rs=ps.executeQuery();
		if(rs.next())
		{
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
			
		}
		else
		{
			System.out.println("Invalid id");
		}
		return ec;

		

		
		

	}
	
	public ArrayList<Library> viewlibrary() throws SQLException
	{
		System.out.println("Enter the Student id");
		int sid=sc.nextInt();
		ArrayList<Library> li=new ArrayList<Library>();
		ps=con.prepareStatement("select *from userinfo where id=?");
		ps.setInt(1, sid);
		rs=ps.executeQuery();
		if(rs.next())
		{
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
		}
		else
		{
			System.out.println("Invalid id");
		}
		

		return li;	
		
	}
	
	public ArrayList<Studentmarks> viewmarks() throws SQLException
	{
		System.out.println("Enter the Student id");
		int sid=sc.nextInt();
		ArrayList<Studentmarks> sm=new ArrayList<Studentmarks>();
		ps=con.prepareStatement("select *from userinfo where id=?");
		ps.setInt(1, sid);
		rs=ps.executeQuery();
		if(rs.next())
		{
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
			
		}
		else
		{
			System.out.println("Invalid id");
		}
		

		return sm;
		
		
	}
	

	public boolean viewinfo(int id)throws SQLException             //method to check the student details based on the id enterd by faculty returns boolean
	{
		boolean v=false;

		String checkid="select *from studentpersonaldetails where sid=?";
		ps=con.prepareStatement(checkid);
		ps.setInt(1, id);
		rs=ps.executeQuery();
		
		if(rs.next()==true)
		{
			System.out.println("Enter your choice to view your details");
			System.out.println("-------------------------");
			System.out.println("1 Extra curricular activity\n2 library info\n3 personal details\n4 Exam Results\n"
					+ "5 Exit");// Give the options to view the details of the student
			System.out.println("======================");
			int facultyres=sc.nextInt();
			
			switch(facultyres)                                        // Using switch case to view different table from the database personal, library, eca,marks
			{
			
			case 1:                                   // use inner join to combine student personal details and extracurricularactivity table
				String fecaq="Select name,sports,clubs from studentpersonaldetails inner join extracurricularactivity on sid=eid where sid=?";
				ps=con.prepareStatement(fecaq);
				ps.setInt(1, id);
				rs=ps.executeQuery();
				System.out.println("======================");
				System.out.println("Extracurricular activity");
				System.out.println("-------------------------");
				System.out.println();
				System.out.println("Name\tSports\tClubs");
				
				while(rs.next())
				{
					System.out.println(rs.getString("name")+"\t"+rs.getString("sports")+"\t"+rs.getString("clubs"));
				}
				System.out.println();
				System.out.println("======================");
				viewinfo(id);         // call the method again in case the user needs to view any other info
				break;
				
				
			case 2:                           // use inner join to combine student personal details and libraryinfo table
				String flibinfo="select name,booksreferred,dueamount from studentpersonaldetails inner join libraryinfo on sid=libid where sid=? ";
				ps=con.prepareStatement(flibinfo);
				ps.setInt(1, id);
				rs=ps.executeQuery();
				System.out.println("======================");
				System.out.println("Library info");
				System.out.println("-------------------------");
				System.out.println();
				System.out.println("Name\tBooksreferred\tDueAmount");
				while(rs.next())
				{
					System.out.println(rs.getString("name")+"\t"+rs.getInt("booksreferred")+"\t\t"+rs.getInt("dueamount"));
				}
				System.out.println();
				System.out.println("======================");
				viewinfo(id);               // call the method again in case the user needs to view any other info
				break;
				
			case 3:     //personaldetails
				String personaldetail="select *from studentpersonaldetails where sid=?";
				ps=con.prepareStatement(personaldetail);
				ps.setInt(1, id);
				rs=ps.executeQuery();
				System.out.println("======================");
				System.out.println("Personal details");
				System.out.println("-------------------------");
				System.out.println();
				System.out.println("sid\tName\tLocation\tMobno");
				while(rs.next())
				{
					System.out.println(rs.getInt("sid")+"\t"+rs.getString("name")+"\t"+rs.getString("location")+"\t\t"+rs.getLong("mobno"));
				}
				System.out.println();
				System.out.println("======================");
				viewinfo(id);// call the method again in case the user needs to view any other info
				break;
			
			case 4: // marks table
				String results="select name,department,sub1mark,sub2mark,sub3mark,sub4mark,sub5mark,avg from studentpersonaldetails "
						+ "inner join studentmarks on sid=regno where sid=?";
				ps=con.prepareStatement(results);
				ps.setInt(1, id);
				rs=ps.executeQuery();
				System.out.println("======================");
				System.out.println("Exam Results");
				System.out.println("-------------------------");
				System.out.println();
				System.out.println("name\tdepartment\tsub1mark\tsub2mark\tsub3mark\tsub4mark\tsub5mark\tavg");
				while(rs.next())
				{
					System.out.println(rs.getString("name")+"\t"+rs.getString("department")+"\t\t"+rs.getInt("sub1mark")+"\t\t"+rs.getInt("sub2mark")
					+"\t\t"+rs.getInt("sub3mark")+"\t\t"+rs.getInt("sub4mark")+"\t\t"+rs.getInt("sub5mark")+"\t\t"+rs.getFloat("avg"));
					
				}
				System.out.println();
				System.out.println("======================");
				viewinfo(id); // call the method again in case the user needs to view any other info
				break;
			
			case 5:
				System.out.println("Thank you");
				break;
				
				
				
			default:
				System.out.println("Invalid Input");
				break;
			
			}
			
			v=true;
		}
		else
		{
			System.out.println("Record not found invalid student id");
			v=false;
			
		
		}
		return v;

		
		
	}
	
	public boolean updatemarks(int id) throws SQLException
	{
		int res;
		boolean u=false;
		String checkid="select *from studentpersonaldetails where sid=?";
		ps=con.prepareStatement(checkid);
		ps.setInt(1, id);
		rs=ps.executeQuery();
		if(rs.next()) // if id is present in database proceed
		{
			
			u=true;
			System.out.println("Enter your choice");
			System.out.println("-------------------------");
			System.out.println("1 update marks in one subject\n2 update marks in all subject\n3 Exit"); // give choice to the user to update only one sub mark or total mark
			int markres=sc.nextInt();
			switch(markres)
			{
			case 1:
				System.out.println("Enter the subject");
				String sub=sc.next();
				System.out.println("Enter the marks");
				int submark=sc.nextInt();
				String markquery="update studentmarks set "+sub+"=? where regno=?";
				ps=con.prepareStatement(markquery);
				ps.setInt(1, submark);
				ps.setInt(2, id);
				res=ps.executeUpdate();
				if(res!=0)
				{
					System.out.println(" marks succesfully Updated");
					ps=con.prepareStatement("select sub1mark,sub2mark,sub3mark,sub4mark,sub5mark from studentmarks where regno=?");  //view the updated marks
					ps.setInt(1, id);
					rs=ps.executeQuery();
					System.out.println("------------------------------------------------------------------------------------------------------------");
					
					float avg1=0;
					while(rs.next()) // calculate average with total marks from the table
					{
						avg1=(rs.getInt("sub1mark")+rs.getInt("sub2mark")+rs.getInt("sub3mark")+rs.getInt("sub4mark")+rs.getInt("sub5mark"))/5;
						
					}
					String avgquery="update studentmarks set avg=? where regno=?"; // Query to update the calculated average in the table
					ps=con.prepareStatement(avgquery);
					ps.setFloat(1, avg1);
					ps.setInt(2, id);
					res=ps.executeUpdate();
					
			
					if(res!=0) //view the updated details
					{
						ps=con.prepareStatement("select name,department,sub1mark,sub2mark,sub3mark,sub4mark,sub5mark,avg from studentpersonaldetails "
								+ "inner join studentmarks on sid=regno where sid=?");  //view the updated marks
						ps.setInt(1, id);
						rs=ps.executeQuery();
						System.out.println("------------------------------------------------------------------------------------------------------------");
						System.out.println("name\tdepartment\tsub1mark\tsub2mark\tsub3mark\tsub4mark\tsub5mark\tavg");
						while(rs.next())
						{
							System.out.println(rs.getString("name")+"\t"+rs.getString("department")+"\t"+rs.getInt("sub1mark")+"\t\t"+rs.getInt("sub2mark")
							+"\t\t"+rs.getInt("sub3mark")+"\t\t"+rs.getInt("sub4mark")+"\t\t"+rs.getInt("sub5mark")+"\t"+rs.getFloat("avg"));
						}
					}
					
					
					System.out.println("------------------------------------------------------------------------------------------------------------");
					
				}
				else
				{
					System.out.println("update failed invaid input");
				}
				
				break;
				
			case 2:
				String updateallsub="update studentmarks set sub1mark=?, sub2mark=?, sub3mark=?, sub4mark=?, sub5mark=? where regno=?";
				System.out.println("Enter subject 1 mark");
				int sub1=sc.nextInt();
				System.out.println("Enter subject 2 mark");
				int sub2=sc.nextInt();
				System.out.println("Enter subject 3 mark");
				int sub3=sc.nextInt();
				System.out.println("Enter subject 4 mark");
				int sub4=sc.nextInt();
				System.out.println("Enter subject 5 mark");
				int sub5=sc.nextInt();
				ps=con.prepareStatement(updateallsub);
				ps.setInt(1, sub1);
				ps.setInt(2, sub2);
				ps.setInt(3, sub3);
				ps.setInt(4, sub4);
				ps.setInt(5, sub5);
				ps.setInt(6, id);
				res=ps.executeUpdate();
				if(res!=0)
				{
					System.out.println(" marks succesfully Updated");
					ps=con.prepareStatement("select sub1mark,sub2mark,sub3mark,sub4mark,sub5mark from studentmarks where regno=?"); //get the marks to calculate and update avg in db
					ps.setInt(1, id);
					rs=ps.executeQuery();
					System.out.println("------------------------------------------------------------------------------------------------------------");
					
					float avg1=0;
					while(rs.next()) // calculate average with total marks from the table
					{
						avg1=(rs.getInt("sub1mark")+rs.getInt("sub2mark")+rs.getInt("sub3mark")+rs.getInt("sub4mark")+rs.getInt("sub5mark"))/5;
						
					}
					String avgquery="update studentmarks set avg=? where regno=?"; // Query to update the calculated average in the table
					ps=con.prepareStatement(avgquery);
					ps.setFloat(1, avg1);
					ps.setInt(2, id);
					res=ps.executeUpdate();
					
			
					if(res!=0) //view the updated details
					{
						ps=con.prepareStatement("select name,department,sub1mark,sub2mark,sub3mark,sub4mark,sub5mark,avg from studentpersonaldetails "
								+ "inner join studentmarks on sid=regno where sid=?");  //view the updated marks
						ps.setInt(1, id);
						rs=ps.executeQuery();
						System.out.println("------------------------------------------------------------------------------------------------------------");
						System.out.println("name\tdepartment\tsub1mark\tsub2mark\tsub3mark\tsub4mark\tsub5mark\tavg");
						while(rs.next())
						{
							System.out.println(rs.getString("name")+"\t\t"+rs.getString("department")+"\t"+rs.getInt("sub1mark")+"\t\t"+rs.getInt("sub2mark")
							+"\t\t"+rs.getInt("sub3mark")+"\t\t"+rs.getInt("sub4mark")+"\t\t"+rs.getInt("sub5mark")+"\t\t"+rs.getFloat("avg"));
						}
					}
					
					
					System.out.println("------------------------------------------------------------------------------------------------------------");
				}
				else
				{
					System.out.println("Invalid details");
					
				}
				break;
			
			case 3:
				System.out.println("Thank you");
				break;
				
			default:
				System.out.println("Invalid option");
				
			
			}
			
		}
		else
		{
			System.out.println("invalid id");
			u=false;
		}
		return u;
		
	}
	
	public void filterby() throws SQLException
	{
		System.out.println("-------------------------------");
		System.out.println("Enter your choice");
		System.out.println("-------------------------------");
		System.out.println("1 Filter by sports team\n2 Filter by department\n3 Filter by clubs\n4 Passed\n5 Failed\n6 Exit");
		System.out.println("-------------------------------");
		int filterres=sc.nextInt();
		switch(filterres)
		{
		case 1:
			System.out.println("Sports"); //filter by sports
			System.out.println("-------------------------------");
			System.out.println("Enter your choice");
			System.out.println("1 Cricket\n2 Football\n3 Volleyball\n4 Batminton\n5 Exit ");
			String sportq="Select name,eid,sports from studentpersonaldetails inner join extracurricularactivity on sid=eid where sports=?";
			
			switch(sc.nextInt())
			{
			case 1:
				
				ps=con.prepareStatement(sportq);
				ps.setString(1, "cricket");
				rs=ps.executeQuery();
				System.out.println("Name\tEid\tSports");
				System.out.println("------------------------------");
				while(rs.next())
				{
					System.out.println(rs.getString("name")+"\t"+rs.getInt("eid")+"\t"+rs.getString("sports"));
				}

				break;
			case 2:
				ps=con.prepareStatement(sportq);
				ps.setString(1, "football");
				rs=ps.executeQuery();
				System.out.println("Name\tEid\tSports");
				System.out.println("------------------------------");
				while(rs.next())
				{
					System.out.println(rs.getString("name")+"\t"+rs.getInt("eid")+"\t"+rs.getString("sports"));
				}
				
				break;
				
			case 3:
				ps=con.prepareStatement(sportq);
				ps.setString(1, "volleyball");
				rs=ps.executeQuery();
				System.out.println("Name\tEid\tSports");
				System.out.println("------------------------------");
				while(rs.next())
				{
					System.out.println(rs.getString("name")+"\t"+rs.getInt("eid")+"\t"+rs.getString("sports"));
				}
				
				break;
				
			case 4:
				ps=con.prepareStatement(sportq);
				ps.setString(1, "Batminton");
				rs=ps.executeQuery();
				System.out.println("Name\tEid\tSports");
				System.out.println("------------------------------");
				while(rs.next())
				{
					System.out.println(rs.getString("name")+"\t"+rs.getInt("eid")+"\t"+rs.getString("sports"));
				}
				
				break;
				
			case 5:
				System.out.println("Thank you");
				break;	
			default:
				System.out.println("Invaild option");
			}
			filterby();
			
			break;
			
		case 2:// filter by department
			System.out.println("Department");
			System.out.println("-------------------------------");
			System.out.println("Enter your choice");
			System.out.println("1 IT\n2 Mechanical\n3 Civil\n4 CSE\n5 Exit ");
			String dep="Select name,regno,department from studentpersonaldetails inner join studentmarks on sid=regno where department=?";
			switch(sc.nextInt())
			{
			case 1:
				
				ps=con.prepareStatement(dep);
				ps.setString(1, "it");
				rs=ps.executeQuery();
				System.out.println("Name\tRegno\tDepartment");
				System.out.println("------------------------------");
				
				while(rs.next())
				{
					System.out.println(rs.getString("name")+"\t"+rs.getInt("regno")+"\t"+rs.getString("department"));
				}
				
				break;
				
			case 2:
				ps=con.prepareStatement(dep);
				ps.setString(1, "mechanical");
				rs=ps.executeQuery();
				System.out.println("Name\tRegno\tDepartment");
				System.out.println("------------------------------");
				
				while(rs.next())
				{
					System.out.println(rs.getString("name")+"\t"+rs.getInt("regno")+"\t"+rs.getString("department"));
				}
				
				break;
				
			case 3:
				ps=con.prepareStatement(dep);
				ps.setString(1, "civil");
				rs=ps.executeQuery();
				System.out.println("Name\tRegno\tDepartment");
				System.out.println("------------------------------");
				
				while(rs.next())
				{
					System.out.println(rs.getString("name")+"\t"+rs.getInt("regno")+"\t"+rs.getString("department"));
				}
				
				break;
				
			case 4:
				ps=con.prepareStatement(dep);
				ps.setString(1, "cse");
				rs=ps.executeQuery();
				System.out.println("Name\tRegno\tDepartment");
				System.out.println("------------------------------");
				
				while(rs.next())
				{
					System.out.println(rs.getString("name")+"\t"+rs.getInt("regno")+"\t"+rs.getString("department"));
				}
		
				break;
			
			case 5:
				System.out.println("Exit");
				break;
			default:
				System.out.println("invalid input");
				
			}
			filterby();

			break;
			
		case 3:
			System.out.println("Clubs");
			System.out.println("-------------------------------");
			System.out.println("Enter your choice");
			System.out.println("1 NCC\n2 NSS\n3 Exit");
			String clquery="Select name,clubs from studentpersonaldetails inner join extracurricularactivity on sid=eid where clubs=?";
			switch(sc.nextInt())
			{
			case 1:
				ps=con.prepareStatement(clquery);
				ps.setString(1, "NCC");
				rs=ps.executeQuery();
				System.out.println("Name\tclubs");
				while(rs.next())
				{
					System.out.println(rs.getString("name")+"\t"+rs.getString("clubs"));
				}
				break;
			
			case 2:
				ps=con.prepareStatement(clquery);
				ps.setString(1, "NSS");
				rs=ps.executeQuery();
				System.out.println("Name\tclubs");
				while(rs.next())
				{
					System.out.println(rs.getString("name")+"\t"+rs.getString("clubs"));
				}
				break;
			
			case 3:
				System.out.println("Thanks");
				break;
			
			default:
				System.out.println("Invalid input");
					
			}
			filterby();
			break;
			
		
		case 4://passed students
	
			String pass="select name,department,sub1mark,sub2mark,sub3mark,sub4mark,sub5mark,avg from studentpersonaldetails "
					+ "inner join studentmarks on sid=regno where avg>=50";
			s=con.createStatement();
			rs=s.executeQuery(pass);
			System.out.println("name\tdepartment\tsub1mark\tsub2mark\tsub3mark\tsub4mark\tsub5mark\tavg");
			while(rs.next())
			{
				System.out.println(rs.getString("name")+"\t\t"+rs.getString("department")+"\t\s"+rs.getInt("sub1mark")+"\t\t"+rs.getInt("sub2mark")
				+"\t\t"+rs.getInt("sub3mark")+"\t\t"+rs.getInt("sub4mark")+"\t\t"+rs.getInt("sub5mark")+"\t\t"+rs.getFloat("avg"));
				
			}
			filterby();
			break;
		
		case 5://Failed students
			
			String fail="select name,department,sub1mark,sub2mark,sub3mark,sub4mark,sub5mark,avg from studentpersonaldetails "
					+ "inner join studentmarks on sid=regno where avg>=50";
			s=con.createStatement();
			rs=s.executeQuery(fail);
			System.out.println("name\tdepartment\tsub1mark\tsub2mark\tsub3mark\tsub4mark\tsub5mark\tavg");
			while(rs.next())
			{
				System.out.println(rs.getString("name")+"\t\t"+rs.getString("department")+"\t\s"+rs.getInt("sub1mark")+"\t\t"+rs.getInt("sub2mark")
				+"\t\t"+rs.getInt("sub3mark")+"\t\t"+rs.getInt("sub4mark")+"\t\t"+rs.getInt("sub5mark")+"\t\t"+rs.getFloat("avg"));
				
			}
			filterby();
			break;
			
		case 6:
			System.out.println("Thank you");
			break;
			
		default:
			System.out.println("invalid option");
		}
		
		
	}

}
