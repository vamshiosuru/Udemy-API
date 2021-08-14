package pojo;

public class GetCourse {
	
	// to generate getters and setters  use alt+shift+ s by selecting all private variables

	private String instructor;
	private String services;
	private String url;
	private String expertise;
	private Courses courses;
	private String linkedIn;
	
	public String getInstructor() {
		return instructor;
	}
	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}
	public String getServices() {
		return services;
	}
	public void setServices(String services) {
		this.services = services;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getExpertise() {
		return expertise;
	}
	public void setExpertise(String expertise) {
		this.expertise = expertise;
	}
	public pojo.Courses getCourses() {
		return courses;
	}
	public void setCourses(pojo.Courses courses) {
		this.courses = courses;
	}
	public String getLinkedIn() {
		return linkedIn;
	}
	public void setLinkedIn(String linkedIn) {
		this.linkedIn = linkedIn;
	}
	
	
	
}
