package package1;
import java.sql.*;
public class Adminlogin extends Facultylogin{                     /* create admin class by extending the student class*/
	
	Adminlogin()throws SQLException, ClassNotFoundException			 /* use constructor to invoke the connection*/
	{
		super();
	}
	
	public void updatedetails(int id) throws SQLException
	{
		
		int res;
		String checkid="select *from userinfo where id=?";
		
		ps=con.prepareStatement(checkid);
		ps.setInt(1, id);
		rs=ps.executeQuery();
		System.out.println("----------------------------------------");
		if(rs.next())
		{
			System.out.println("Enter your choice to update");
			System.out.println("1 Personal details\n2 Library info\n3 Extracurricular activity\n4 Update marks\n5 exit"); //give choice to update details
			System.out.println("-------------------------------------------------------------------------------------------");
			int adminres=sc.nextInt();
			switch(adminres)
			{
			case 1: //personal derails
				System.out.println("Enter your choice\n1 Location\n2 MobileNo\n3 Both\n4 Exit");
				System.out.println("--------------------------------------------------------------");
				switch(sc.nextInt())
				{
				case 1: //location
					String location="update studentpersonaldetails set location=? where sid=?";
					System.out.println("Enter your location");
					String loc=sc.next();
					ps=con.prepareStatement(location);
					ps.setString(1, loc);
					ps.setInt(2, id);
					res=ps.executeUpdate();
					if(res!=0)
					{
						System.out.println("updated successfully");
						System.out.println("------------------------------------");
						ps=con.prepareStatement("select *from studentpersonaldetails where sid=?"); // view the details after updating
						ps.setInt(1, id);
						rs=ps.executeQuery();
						System.out.println("sid\tName\tLocation\tMobno");
						while(rs.next())
						{
							System.out.println(rs.getInt("sid")+"\t"+rs.getString("name")+"\t"+rs.getString("location")+"\t\t"+rs.getLong("mobno"));
						}
						System.out.println("---------------------------------------------------------------------------------------------------------");
						
					}
					else
					{
						System.out.println("Update failed");
					}
					break;
				
				case 2: //mobno
					String mobno="update studentpersonaldetails set mobno=? where sid=?";
					System.out.println("Enter your mobno");
					long mob=sc.nextLong();
					ps=con.prepareStatement(mobno);
					ps.setLong(1, mob);
					ps.setInt(2, id);
					res=ps.executeUpdate();
					if(res!=0)
					{
						System.out.println("updated successfully");
						System.out.println("------------------------------------");
						ps=con.prepareStatement("select *from studentpersonaldetails where sid=?"); // view the details after updating
						ps.setInt(1, id);
						rs=ps.executeQuery();
						System.out.println("sid\tName\tLocation\tMobno");
						while(rs.next())
						{
							System.out.println(rs.getInt("sid")+"\t"+rs.getString("name")+"\t"+rs.getString("location")+"\t\t"+rs.getLong("mobno"));
						}
						System.out.println("---------------------------------------------------------------------------------------------------------");
						
					}
					else
					{
						System.out.println("Update failed");
					}
					break;	
					
				case 3: // loc and mob no
					String both="update studentpersonaldetails set mobno=?,location=? where sid=?";
					System.out.println("Enter mobno");
					long mob2=sc.nextLong();
					System.out.println("Enter location");
					String loc2=sc.next();
					ps=con.prepareStatement(both);
					ps.setLong(1, mob2);
					ps.setString(2, loc2);
					ps.setInt(3, id);
					res=ps.executeUpdate();
					if(res!=0)
					{
						System.out.println("updated successfully");
						System.out.println("------------------------------------");
						ps=con.prepareStatement("select *from studentpersonaldetails where sid=?"); // view the details after updating
						ps.setInt(1, id);
						rs=ps.executeQuery();
						System.out.println("sid\tName\tLocation\tMobno");
						while(rs.next())
						{
							System.out.println(rs.getInt("sid")+"\t"+rs.getString("name")+"\t"+rs.getString("location")+"\t\t"+rs.getLong("mobno"));
						}
						System.out.println("---------------------------------------------------------------------------------------------------------");
						
					}
					else
					{
						System.out.println("Update failed");
					}
					break;	
					
				case 4:
					System.out.println("Thanks");
					break;
				default:
					System.out.println("Invalid Input");
				}
				updatedetails(id);
				
				break;
			
			case 2: //library info
				System.out.println("Enter your choice\n1 BooksReferred\n2 DueAmount\n3 exit");
				System.out.println("--------------------------------------------------------------");
				
				switch(sc.nextInt())
				{
				case 1://books referred
					String booksreferred="update libraryinfo set booksreferred=? where libid=?";
					System.out.println("Enter the number of books referred");
					int books=sc.nextInt();
					ps=con.prepareStatement(booksreferred);
					ps.setInt(1, books);
					ps.setInt(2, id);
					res=ps.executeUpdate();
					if(res!=0)
					{
						ps=con.prepareStatement("select *from libraryinfo where libid=?");
						ps.setInt(1, id);
						rs=ps.executeQuery();
						System.out.println("Id\tBooksreferred\tDueAmount");
						while(rs.next())
						{
							System.out.println(rs.getInt("libid")+"\t"+rs.getInt("booksreferred")+"\t\t"+rs.getInt("dueamount"));
						}
					}
					break;
					
				case 2: //due amt
					String dueamt="update libraryinfo set dueamount=? where libid=?";
					System.out.println("Enter the due amount");
					int amt=sc.nextInt();
					ps=con.prepareStatement(dueamt);
					ps.setInt(1,amt);
					ps.setInt(2, id);
					res=ps.executeUpdate();
					if(res!=0)
					{
						ps=con.prepareStatement("select *from libraryinfo where libid=?");
						ps.setInt(1, id);
						rs=ps.executeQuery();
						System.out.println("Id\tBooksreferred\tDueAmount");
						while(rs.next())
						{
							System.out.println(rs.getInt("libid")+"\t"+rs.getInt("booksreferred")+"\t\t"+rs.getInt("dueamount"));
						}
					}
					break;
				
				case 3:
					System.out.println("Thank you ");
					break;
				
				default:
					System.out.println("Invalid input");
					
					
				}
				updatedetails(id);
				break;
			
			case 3:// Extra curricular activities
				System.out.println("Enter your choice\n1 sports\n2 clubs\n3 exit");
				System.out.println("--------------------------------------------------------------");
				switch(sc.nextInt())
				{
				case 1://sports
					String sports="update extracurricularactivity set sports=? where eid=?";
					System.out.println("Enter the sports");
					String sprt=sc.next();
					ps=con.prepareStatement(sports);
					ps.setString(1, sprt);
					ps.setInt(2, id);
					res=ps.executeUpdate();
					if(res!=0)
					{
						ps=con.prepareStatement("Select name,sports from studentpersonaldetails inner join extracurricularactivity on sid=eid where sid=?");
						ps.setInt(1, id);
						rs=ps.executeQuery();
						System.out.println("Name\tSports");
						
						while(rs.next())
						{
							System.out.println(rs.getString("name")+"\t"+rs.getString("sports"));
						}
					}
					
					break;
				
				case 2://clubs
					String clubs="update extracurricularactivity set clubs=? where eid=?";
					System.out.println("Enter the clubs");
					String clbs=sc.next();
					ps=con.prepareStatement(clubs);
					ps.setString(1, clbs);
					ps.setInt(2, id);
					res=ps.executeUpdate();
					if(res!=0)
					{
						ps=con.prepareStatement("Select name,clubs from studentpersonaldetails inner join extracurricularactivity on sid=eid where sid=?");
						System.out.println("Name\tSports");
						ps.setInt(1, id);
						rs=ps.executeQuery();
						
						while(rs.next())
						{
							System.out.println(rs.getString("name")+"\t"+rs.getString("clubs"));
						}
					}
					
					break;
				
				case 3:
					System.out.println("Thank you");
					break;
				default:
					break;
				}
				updatedetails(id);
				break;
			case 4:// update marks
				updatemarks(id);  //method from faculty class
				break;
			case 5:
				System.out.println("Thank you");
				break;
			default:
				System.out.println("invalid Input");
			}
				
		}
		else
		{
			System.out.println("not found");
			System.out.println("Do you want to try again? Y or N"); // if user enterd wrong id by mistake give option to try again.
			char res1=sc.next().charAt(0);
			if(res1=='y' || res1=='Y')
			{
				updatedetails(id);
			}
			else
			{
				System.out.println("Thank you");
			}
		}
		
	}
	public void insertdetails() throws SQLException
	{
		int res;

		System.out.println("Enter the student id");
		int sid=sc.nextInt();
		
		String checkquery="select *from userinfo where id=?";
		ps=con.prepareStatement(checkquery);
		ps.setInt(1, sid);
		rs=ps.executeQuery();
		if(rs.next()==false)
		{
			System.out.println("Enter the student name");
			String name=sc.next();
			System.out.println("Enter the student email");
			String email=sc.next();
			System.out.println("Enter the student mobile no");
			long mobno=sc.nextLong();
			System.out.println("Enter the student location");
			String location=sc.next();
			System.out.println("Enter the department");
			String dep=sc.next();

			//insert into userinfo table
			String userinfo="insert into userinfo values(?,?,?,?)";
			ps=con.prepareStatement(userinfo);
			ps.setInt(1, sid);
			ps.setString(2, name);
			ps.setString(3, name);
			ps.setString(4, email);
			res=ps.executeUpdate();
			if(res!=0)
			{
				System.out.println("userinfo updated");
			}
			else
			{
				System.out.println("update failed");
			}
			
			//insert into studentpersonaldetails table
			
			String personaldetail="insert into studentpersonaldetails values(?,?,?,?)";
			ps=con.prepareStatement(personaldetail);
			ps.setInt(1, sid);
			ps.setString(2, name);
			ps.setString(3, location);
			ps.setLong(4, mobno);
			res=ps.executeUpdate();
			
			if(res!=0)
			{
				System.out.println("personal details updated");
			}
			else
			{
				System.out.println("update failed");
			}
			
			//library info
			
			String libraryino="insert into libraryinfo values(?,?,?)";
			ps=con.prepareStatement(libraryino);
			ps.setInt(1, sid);
			ps.setInt(2, 0);
			ps.setInt(3, 0);
			res=ps.executeUpdate();
			
			if(res!=0)
			{
				System.out.println("library details updated");
			}
			else
			{
				System.out.println("update failed");
			}
			
			//studentmarks
			
			String marks="insert into studentmarks values(?,?,?,?,?,?,?,?)";
			ps=con.prepareStatement(marks);
			ps.setInt(1, sid);
			ps.setString(2, dep);
			ps.setInt(3, 0);
			ps.setInt(4, 0);
			ps.setInt(5, 0);
			ps.setInt(6, 0);
			ps.setInt(7, 0);
			ps.setFloat(8, 0.0f);
			res=ps.executeUpdate();
			
			if(res!=0)
			{
				System.out.println("department details updated");
			}
			else
			{
				System.out.println("update failed");
			}
			
			System.out.println("is the student interested in sports Y Or N");
			char ecopt=sc.next().charAt(0);
			if(ecopt=='Y' || ecopt=='y')
			{
				System.out.println("Enter your choice\n1 football\n2 crkcket\n3 volleyball\n4 batminton");
				switch(sc.nextInt())
				{
				case 1:
					ps=con.prepareStatement("insert into extracurricularactivity(eid,sports) values(?,?)");
					ps.setInt(1, sid);
					ps.setString(2, "football");
					ps.executeUpdate();
					break;
					
				case 2:
					ps=con.prepareStatement("insert into extracurricularactivity(eid,sports) values(?,?)");
					ps.setInt(1, sid);
					ps.setString(2, "cricket");
					ps.executeUpdate();
					break;
					
				case 3:
					ps=con.prepareStatement("insert into extracurricularactivity(eid,sports) values(?,?)");
					ps.setInt(1, sid);
					ps.setString(2, "volleyball");
					ps.executeUpdate();
					break;
					
				case 4:
					ps=con.prepareStatement("insert into extracurricularactivity(eid,sports) values(?,?)");
					ps.setInt(1, sid);
					ps.setString(2, "batminton");
					ps.executeUpdate();
					break;
					
				default:
					System.out.println("invalid option");
					
				}
			}
			System.out.println("is the student interested in Clubs like NCC NSS Y Or N");
			char ecopt2=sc.next().charAt(0);
			if(ecopt2=='Y' || ecopt2=='y')
			{
				System.out.println("Enter your choice\n1 NCC\n2 NSS");
				switch(sc.nextInt())
				{
				case 1:
					ps=con.prepareStatement("insert into extracurricularactivity(eid,clubs) values(?,?)");
					ps.setInt(1, sid);
					ps.setString(2, "NCC");
					ps.executeUpdate();
					break;
					
				case 2:
					ps=con.prepareStatement("insert into extracurricularactivity(eid,clubs) values(?,?)");
					ps.setInt(1, sid);
					ps.setString(2, "NSS");
					ps.executeUpdate();
					break;
				
				}
			}
		}
		else
		{
			System.out.println("id already present in database enter a different id");
			insertdetails();
		}
			
	
		
		
	}

}
