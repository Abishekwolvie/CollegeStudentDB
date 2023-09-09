package package1;

public class Studentpersonaldetail {
	
	int sid;
	String sname;
	String location;
	long mobno;
	public Studentpersonaldetail(int sid, String sname, String location, long mobno) {
		super();
		this.sid = sid;
		this.sname = sname;
		this.location = location;
		this.mobno = mobno;
	}
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public long getMobno() {
		return mobno;
	}
	public void setMobno(long mobno) {
		this.mobno = mobno;
	}


}
