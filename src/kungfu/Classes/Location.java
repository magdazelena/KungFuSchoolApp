package kungfu.Classes;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;

@Entity(name="Location")
public class Location {
	private String street;
	private Integer number;
	private Integer zipcode;
	private String city;
	private long id;
	private School school;
	private Boolean isHQ;
	private List<Team> teams = new ArrayList<>();
	private Location() {}
	private Location(School school, String street, Integer number, Integer zip, String city, Boolean isHq) {
		this.street =street;
		this.number = number;
		this.zipcode = zip;
		this.city=city;
		this.school = school;
		this.isHQ =isHq;
	}
	public static Location createLocation(School school, String street, Integer number, Integer zipcode, String city, Boolean isHq) throws Exception {
		if(school == null) throw new Exception("School must exist");
		Location location = new Location(school, city, number, number, city, isHq);
		school.addLocation(location);
		return location;
	}
	@Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    public long getId() {
        return id;
    }
	private void setId(long id) {
        this.id = id;
    }
	@Basic
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	@Basic
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	@Basic
	public Integer getZipcode() {
		return zipcode;
	}
	public void setZipcode(Integer zipcode) {
		this.zipcode = zipcode;
	}
	@Basic
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	@ManyToOne
	@JoinColumn(name="fk_school")
	public School getSchool() {
		return school;
	}
	public void setSchool(School school) {
		this.school = school;
	}
	@Basic
	public Boolean getIsHQ() {
		return isHQ;
	}
	public void setIsHQ(Boolean isHQ) {
		this.isHQ = isHQ;
	}
	@OneToMany(mappedBy="location")
	public List<Team> getTeams() {
		return teams;
	}
	public void setTeams(List<Team> teams) {
		this.teams = teams;
	}
	public void addTeam(Team team) {
		if(!this.teams.contains(team)) {
			this.teams.add(team);
		}
	}
	public void removeTeam(Team team) {
		if(this.teams.contains(team)) {
			this.teams.remove(team);
		}
	}
}
