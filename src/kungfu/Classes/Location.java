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
/**
 * Location class
 * @see java.lang.Object
 */
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
/**
 * 
 * @param school
 * @param street
 * @param number
 * @param zip
 * @param city
 * @param isHq
 */
	private Location(School school, String street, Integer number, Integer zip, String city, Boolean isHq) {
		this.street =street;
		this.number = number;
		this.zipcode = zip;
		this.city=city;
		this.school = school;
		this.isHQ =isHq;
	}
	/**
	 * Static creator
	 * @param school
	 * @param street
	 * @param number
	 * @param zipcode
	 * @param city
	 * @param isHq
	 * @return Location
	 * @throws Exception
	 */
	public static Location createLocation(School school, String street, Integer number, Integer zipcode, String city, Boolean isHq) throws Exception {
		if(school == null) throw new Exception("School must exist");
		Location location = new Location(school, street, number, number, city, isHq);
		school.addLocation(location);
		return location;
	}
	/**
	 * Gets id
	 * @return id
	 */
	@Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    public long getId() {
        return id;
    }
	/**
	 * Sets id
	 * @param id
	 */
	private void setId(long id) {
        this.id = id;
    }
	/**
	 * Gets street
	 * @return street
	 */
	@Basic
	public String getStreet() {
		return street;
	}
	/**
	 * Sets street
	 * @param street
	 */
	public void setStreet(String street) {
		this.street = street;
	}
	/**
	 * Gets number
	 * @return number
	 */
	@Basic
	public Integer getNumber() {
		return number;
	}
	/**
	 * Sets number
	 * @param number
	 */
	public void setNumber(Integer number) {
		this.number = number;
	}
	/**
	 * Gets zipcode
	 * @return zipcode
	 */
	@Basic
	public Integer getZipcode() {
		return zipcode;
	}
	/**
	 * Sets zipcode
	 * @param zipcode
	 */
	public void setZipcode(Integer zipcode) {
		this.zipcode = zipcode;
	}
	/**
	 * Gets city
	 * @return city
	 */
	@Basic
	public String getCity() {
		return city;
	}
	/**
	 * Sets city
	 * @param city
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * Association
	 * Gets school
	 * @return school
	 */
	@ManyToOne
	@JoinColumn(name="fk_school")
	public School getSchool() {
		return school;
	}
	/**
	 * Sets School
	 * @param school
	 */
	public void setSchool(School school) {
		this.school = school;
	}
	/**
	 * Gets headquarters
	 * @return isHQ
	 */
	@Basic
	public Boolean getIsHQ() {
		return isHQ;
	}
	/**
	 * Sets headquarters
	 * @param isHQ
	 */
	public void setIsHQ(Boolean isHQ) {
		this.isHQ = isHQ;
	}
	/**
	 * Association
	 * Gets teams
	 * @return teams
	 */
	@OneToMany(mappedBy="location")
	public List<Team> getTeams() {
		return teams;
	}
	/**
	 * Sets teams list
	 * @param teams
	 */
	public void setTeams(List<Team> teams) {
		this.teams = teams;
	}
	/**
	 * Add team
	 * @param team
	 */
	public void addTeam(Team team) {
		if(!this.teams.contains(team)) {
			this.teams.add(team);
		}
	}
	/**
	 * Remove team
	 * @param team
	 */
	public void removeTeam(Team team) {
		if(this.teams.contains(team)) {
			this.teams.remove(team);
		}
	}
}
