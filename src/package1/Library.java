package package1;

public class Library {
	
	String name;
	int bookreferred;
	int due;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getBookreferred() {
		return bookreferred;
	}
	public void setBookreferred(int bookreferred) {
		this.bookreferred = bookreferred;
	}
	public int getDue() {
		return due;
	}
	public void setDue(int due) {
		this.due = due;
	}
	public Library(String name, int bookreferred, int due) {
		super();
		this.name = name;
		this.bookreferred = bookreferred;
		this.due = due;
	}


}
