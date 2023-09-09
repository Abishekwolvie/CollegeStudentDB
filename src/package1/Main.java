package package1;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner sc=new Scanner(System.in);
		boolean a=false;
		
		
		try {
			
			System.out.println("Enter your response");
			System.out.println("-------------------------");
			System.out.println("1 Admin\n2 Student\n3 Faculty\n4 Exit");
			int res=sc.nextInt();
			
			
			
			switch(res)
			{
			case 1://admin login
				Adminlogin admin=new Adminlogin();
				System.out.println("Enter the admin username"); //case 1 for admin login option
				String aname=sc.next();
				System.out.println("Enter the admin password");
				String apass=sc.next();
				a=admin.validateuser(aname, apass);             //validate the credentials entered
				if(a==true)
				{
					System.out.println("Login success");
					System.out.println("======================");
					System.out.println("Enter your choice");    // give option to update the student details, view
					System.out.println("1 update student details\n2 View Student info\n3 Filterby\n4 Insert new student detail\n5 Exit");
					int adminres=sc.nextInt();
					int adid=0;
					switch(adminres)
					{
					case 1: // update student detail
						System.out.println("Enter the student id");
						int id=sc.nextInt();
						admin.updatedetails(id);
						break;
						
					case 2: // view Student Info
						System.out.println("-------------------------");                                   
						System.out.println("Student info");
						System.out.println("-------------------------");
						System.out.println("Enter your choice \n1 personaldetail\n2 Extracurricularactivity\n3 Libraryinfo\n4 marks\n5 Exit ");
						switch(sc.nextInt())
						{
						
						
						case 1://personaldetails
							ArrayList<Studentpersonaldetail> st=admin.viewpersonalinfo();
							Iterator<Studentpersonaldetail> it=st.iterator();
							System.out.println("sid\tName\tLocation\tMobno");
							while(it.hasNext())
							{
								Studentpersonaldetail sp=it.next();
								System.out.println(sp.getSid()+"\t"+sp.getSname()+"\t"+sp.getLocation()+"\t\t"+sp.getMobno());
							}
							break;
						
							
						case 2:// ECA
							ArrayList<ECA> ec=admin.vieweca();
							Iterator<ECA> ite=ec.iterator();
							System.out.println("Name\tClubs\tSports");
							while(ite.hasNext())
							{
								ECA eca=ite.next();
								System.out.println(eca.getName()+"\t"+eca.getClubs()+"\t"+eca.getSports());
							}
							break;
							
						case 3://Library
							ArrayList<Library> li=admin.viewlibrary();
							Iterator<Library> itl=li.iterator();
							System.out.println("Name\tBooksreferred\tDueAmount");
							while(itl.hasNext())
							{
								Library lib=itl.next();
								System.out.println(lib.getName()+"\t"+lib.getBookreferred()+"\t\t"+lib.getDue());
							}
							break;
							
						case 4:
							ArrayList<Studentmarks> sma=admin.viewmarks();
							Iterator<Studentmarks> itm=sma.iterator();
							System.out.println("name\tdepartment\tsub1mark\tsub2mark\tsub3mark\tsub4mark\tsub5mark\tavg");
							while(itm.hasNext())
							{
								Studentmarks sm=itm.next();
								System.out.println(sm.getName()+"\t"+sm.getDepartment()+"\t"+sm.getSub1mark()+"\t\t"+sm.getSub2mark()+"\t\t"+sm.getSub3mark()
								+"\t\t"+sm.getSub4mark()+"\t\t"+sm.getSub5mark()+"\t\t"+sm.getAvg());
							}
							break;
							
							
						case 5:
							System.out.println("Thankyou");
							break;
							
						default:
							System.out.println("Invalid Input");
							break;
				
						
						}
						
						break;
						
					case 3:// filter students
						admin.filterby();
						break;
					case 4://insert student details
						admin.insertdetails();
						break;
					case 5:
						System.out.println("Thank you");
						break;
					default:
						System.out.println("Invalid Response");
						
					}
					
				}
				else
				{
					System.out.println("Login Failed");
					System.out.println("Invalid Credentials");
					System.out.println("Do you want to update password y or N?");     //if password is invalid option to change password
					char opt=sc.next().charAt(0);
					
					if(opt=='Y' || opt=='y')
					{
						System.out.println("Enter your email");          
						String aemail=sc.next();
						admin.changepassword(aname, aemail);            //method to change password
						main(args);                                     //call the main function again after updating the password
					}
					else
					{
						System.out.println("Thank you");
					}
					
				}
				break;
				
				
				
				
				
			case 2:  //student login                                                        //case 2 for Student login option
				StudentLogin s=new StudentLogin();
				System.out.println("Enter the student username");
				String sname=sc.next();
				System.out.println("Enter the student password");
				String spass=sc.next();
				a=s.validateuser(sname, spass);
				if(a==true)
				{
					System.out.println("Login success");
					System.out.println("======================");
					System.out.println("Enter your choice \n1 personaldetail\n2 Extracurricularactivity\n3 Libraryinfo\n4 marks\n5 Exit ");
					
					switch(sc.nextInt())
					{
					
					case 1://personaldetails
						ArrayList<Studentpersonaldetail> st=s.viewpersonalinfo(sname);
						Iterator<Studentpersonaldetail> it=st.iterator();
						System.out.println("sid\tName\tLocation\tMobno");
						while(it.hasNext())
						{
							Studentpersonaldetail sp=it.next();
							System.out.println(sp.getSid()+"\t"+sp.getSname()+"\t"+sp.getLocation()+"\t\t"+sp.getMobno());
						}
						break;
					
						
					case 2:// ECA
						ArrayList<ECA> ec=s.vieweca(sname);
						Iterator<ECA> ite=ec.iterator();
						System.out.println("Name\tClubs\tSports");
						while(ite.hasNext())
						{
							ECA eca=ite.next();
							System.out.println(eca.getName()+"\t"+eca.getClubs()+"\t"+eca.getSports());
						}
						break;
						
					case 3://Library
						ArrayList<Library> li=s.viewlibrary(sname);
						Iterator<Library> itl=li.iterator();
						System.out.println("Name\tBooksreferred\tDueAmount");
						while(itl.hasNext())
						{
							Library lib=itl.next();
							System.out.println(lib.getName()+"\t"+lib.getBookreferred()+"\t\t"+lib.getDue());
						}
						break;
						
					case 4:
						ArrayList<Studentmarks> sma=s.viewmarks(sname);
						Iterator<Studentmarks> itm=sma.iterator();
						System.out.println("name\tdepartment\tsub1mark\tsub2mark\tsub3mark\tsub4mark\tsub5mark\tavg");
						while(itm.hasNext())
						{
							Studentmarks sm=itm.next();
							System.out.println(sm.getName()+"\t"+sm.getDepartment()+"\t"+sm.getSub1mark()+"\t\t"+sm.getSub2mark()+"\t\t"+sm.getSub3mark()
							+"\t\t"+sm.getSub4mark()+"\t\t"+sm.getSub5mark()+"\t\t"+sm.getAvg());
						}
						break;
						
						
					case 5:
						System.out.println("Thankyou");
						break;
						
					default:
						System.out.println("Invalid Input");
						break;
						
					}

					
					
				}
				else
				{
					System.out.println("Login Failed");
					System.out.println("Invalid Credentials");
					System.out.println("Do you want to update password y or N?");  //if password is invalid option to change password
					char opt=sc.next().charAt(0);
					
					if(opt=='Y' || opt=='y')
					{
						System.out.println("Enter your email");
						String semail=sc.next();
						s.changepassword(sname, semail);                 //method to change password
						main(args);                                      //call the main function again after updating the password
					}
					else
					{
						System.out.println("Thank you");
					}
					
					
				}
				break;
			
				
			case 3: //faculty login
				Facultylogin f=new Facultylogin();
				System.out.println("Enter the faculty username");
				String fname=sc.next();
				System.out.println("Enter the faculty3 password");
				String fpass=sc.next();
				a=f.validateuser(fname, fpass);
				if(a==true)
				{
					int id;
					System.out.println("Login success");
					System.out.println("-------------------------");

					System.out.println("Enter your choice");
					System.out.println("-------------------------");
					System.out.println("1 view student info\n2 update markss\n3 filter by\n4 Exit"); // give option to view details and update marks.
					int fresponse=sc.nextInt();
					switch(fresponse)
					{
					case 1://case 1 method to view student info viewinfo()                                
						System.out.println("Student info");
						System.out.println("-------------------------");
						System.out.println("Enter your choice \n1 personaldetail\n2 Extracurricularactivity\n3 Libraryinfo\n4 marks\n5 Exit ");
						switch(sc.nextInt())
						{
						
						
						case 1://personaldetails
							ArrayList<Studentpersonaldetail> st=f.viewpersonalinfo();
							Iterator<Studentpersonaldetail> it=st.iterator();
							System.out.println("sid\tName\tLocation\tMobno");
							while(it.hasNext())
							{
								Studentpersonaldetail sp=it.next();
								System.out.println(sp.getSid()+"\t"+sp.getSname()+"\t"+sp.getLocation()+"\t\t"+sp.getMobno());
							}
							break;
						
							
						case 2:// ECA
							ArrayList<ECA> ec=f.vieweca();
							Iterator<ECA> ite=ec.iterator();
							System.out.println("Name\tClubs\tSports");
							while(ite.hasNext())
							{
								ECA eca=ite.next();
								System.out.println(eca.getName()+"\t"+eca.getClubs()+"\t"+eca.getSports());
							}
							break;
							
						case 3://Library
							ArrayList<Library> li=f.viewlibrary();
							Iterator<Library> itl=li.iterator();
							System.out.println("Name\tBooksreferred\tDueAmount");
							while(itl.hasNext())
							{
								Library lib=itl.next();
								System.out.println(lib.getName()+"\t"+lib.getBookreferred()+"\t\t"+lib.getDue());
							}
							break;
							
						case 4:
							ArrayList<Studentmarks> sma=f.viewmarks();
							Iterator<Studentmarks> itm=sma.iterator();
							System.out.println("name\tdepartment\tsub1mark\tsub2mark\tsub3mark\tsub4mark\tsub5mark\tavg");
							while(itm.hasNext())
							{
								Studentmarks sm=itm.next();
								System.out.println(sm.getName()+"\t"+sm.getDepartment()+"\t"+sm.getSub1mark()+"\t\t"+sm.getSub2mark()+"\t\t"+sm.getSub3mark()
								+"\t\t"+sm.getSub4mark()+"\t\t"+sm.getSub5mark()+"\t\t"+sm.getAvg());
							}
							break;
							
							
						case 5:
							System.out.println("Thankyou");
							break;
							
						default:
							System.out.println("Invalid Input");
							break;
				
						
						}
						
						break;
					case 2: //Method to update marks updatemarks()         
						System.out.println("Enter the student id");
						id=sc.nextInt();
						System.out.println("-------------------------");
						System.out.println("Update Student marks");
						System.out.println("-------------------------");
						boolean markres=f.updatemarks(id);    //Method to update marks updatemarks() returns boolean               
						if(markres==false) // check the return of the viewinfo method in case the faculty enters the wrong id by mistake give option to try again
						{
							System.out.println("Do you want to try again Y or N?");
							char fopt=sc.next().charAt(0);
							if(fopt=='Y' || fopt=='y')
							{
								System.out.println("-------------------------");
								System.out.println("Enter the student id");
								System.out.println("-------------------------");
								id=sc.nextInt();
								f.updatemarks(id);
							}
							else
							{
								System.out.println("Thank you");
							}
						}
						break;
						
					case 3:
						f.filterby();
						break;
					case 4:
						System.out.println("Thank you");
						break;
					
					default: 
						System.out.println("Exit");
						
					}
						
				}
				else
				{
					System.out.println("Login Failed");
					System.out.println("Invalid Credentials");
					System.out.println("Do you want to update password y or N?");    //if password is invalid option to change password
					char opt=sc.next().charAt(0);
					
					if(opt=='Y' || opt=='y')
					{
						System.out.println("Enter your email");
						String femail=sc.next();
						f.changepassword(fname, femail);                                 //method to change password                           
						main(args);                                                      //call the main function again after updating the password
					}
					else
					{
						System.out.println("Thank you");
					}
					
					
				}
				break;
			case 4:
				System.out.println("Thank you");
				break;
				
				
				
			default:
				System.out.println("Invalid input");
				
				
			}
			

		}
		catch(SQLException | ClassNotFoundException e)
		{
			System.out.println(e);
		}
		catch (InputMismatchException e)
		{
			System.out.println("Enter the valid Input");
		
		
		}
		

	}

}
