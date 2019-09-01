package kungfu.Classes;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;
/**
 * Location class
 * @version 1.0
 * @author magda
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
 * Private constructor for the Location object
 * @param school School object to be assigned
 * @param street Street name
 * @param number House number Integer
 * @param zip Zipcode Integer
 * @param city City name String
 * @param isHq Boolean if the Location is the head quaters of school
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
	 * @param school School object to be assigned
	 * @param street Street name
	 * @param number House number Integer
	 * @param zipcode Zipcode IntegeR
	 * @param city City name String
	 * @param isHq Boolean if the Location is the head quaters of school
	 * @return Location
	 * @throws Exception if School is null
	 */
	public static Location createLocation(School school, String street, Integer number, Integer zipcode, String city, Boolean isHq) throws Exception {
		if(school == null) throw new Exception("School must exist");
		Location location = new Location(school, street, number, zipcode, city, isHq);
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
	@SuppressWarnings("unused")
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
	 * @param street Street name String
	 */
	public void setStreet(String street) {
		this.street = street;
	}
	/**
	 * Gets house number
	 * @return number
	 */
	@Basic
	public Integer getNumber() {
		return number;
	}
	/**
	 * Sets house number
	 * @param number House number as Integer
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
	 * @param zipcode Zipcode in Integer
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
	 * @param city City name String
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
	 * @param school School object to be associated
	 */
	public void setSchool(School school) {
		this.school = school;
	}
	/**
	 * Gets if Location is headquarters
	 * @return isHQ
	 */
	@Basic
	public Boolean getIsHQ() {
		return isHQ;
	}
	/**
	 * Sets headquarters
	 * @param isHQ Boolean if Location is headquarters
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
	 * @param teams List of Team objects for the Location
	 */
	public void setTeams(List<Team> teams) {
		this.teams = teams;
	}
	/**
	 * Add team
	 * @param team Team to be added to Location
	 */
	public void addTeam(Team team) {
		if(!this.teams.contains(team)) {
			this.teams.add(team);
		}
	}
	/**
	 * Remove team
	 * @param team Team to be removed from list in Location
	 */
	public void removeTeam(Team team) {
		if(this.teams.contains(team)) {
			this.teams.remove(team);
		}
	}
	/**
	 * Overrides toString method
	 */
	public String toString(){
		return "ul. "+this.getStreet()+" "+this.getNumber()+", "+this.getZipcode()+" "+this.getCity();
	}
}
