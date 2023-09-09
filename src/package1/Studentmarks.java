package package1;

public class Studentmarks {
	
	String name;
	String department;
	int sub1mark;
	int sub2mark;
	int sub3mark;
	int sub4mark;
	int sub5mark;
	float avg;
	public Studentmarks(String name, String department, int sub1mark, int sub2mark, int sub3mark, int sub4mark,
			int sub5mark, float avg) {
		super();
		this.name = name;
		this.department = department;
		this.sub1mark = sub1mark;
		this.sub2mark = sub2mark;
		this.sub3mark = sub3mark;
		this.sub4mark = sub4mark;
		this.sub5mark = sub5mark;
		this.avg = avg;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public int getSub1mark() {
		return sub1mark;
	}
	public void setSub1mark(int sub1mark) {
		this.sub1mark = sub1mark;
	}
	public int getSub2mark() {
		return sub2mark;
	}
	public void setSub2mark(int sub2mark) {
		this.sub2mark = sub2mark;
	}
	public int getSub3mark() {
		return sub3mark;
	}
	public void setSub3mark(int sub3mark) {
		this.sub3mark = sub3mark;
	}
	public int getSub4mark() {
		return sub4mark;
	}
	public void setSub4mark(int sub4mark) {
		this.sub4mark = sub4mark;
	}
	public int getSub5mark() {
		return sub5mark;
	}
	public void setSub5mark(int sub5mark) {
		this.sub5mark = sub5mark;
	}
	public float getAvg() {
		return avg;
	}
	public void setAvg(float avg) {
		this.avg = avg;
	}
	
	

}
