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
	 * Constructor
	 * @param School school
	 * @param String street
	 * @param int number
	 * @param int zip
	 * @param String city
	 * @param Boolean isHq
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
		Location location = new Location(school, city, number, number, city, isHq);
		school.addLocation(location);
		return location;
	}
	/**
	 * Gets id
	 * @return long
	 */
	@Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    public long getId() {
        return id;
    }
	/**
	 * Sets id
	 * @param long
	 */
	private void setId(long id) {
        this.id = id;
    }
	/**
	 * Gets street
	 * @return String
	 */
	@Basic
	public String getStreet() {
		return street;
	}
	/**
	 * Sets street
	 * @param String
	 */
	public void setStreet(String street) {
		this.street = street;
	}
	/**
	 * Gets number
	 * @return integer
	 */
	@Basic
	public Integer getNumber() {
		return number;
	}
	/**
	 * Sets number
	 * @param integer
	 */
	public void setNumber(Integer number) {
		this.number = number;
	}
	/**
	 * Gets zipcode
	 * @return integer
	 */
	@Basic
	public Integer getZipcode() {
		return zipcode;
	}
	/**
	 * Sets zipcode
	 * @param integer
	 */
	public void setZipcode(Integer zipcode) {
		this.zipcode = zipcode;
	}
	/**
	 * Gets city
	 * @return String
	 */
	@Basic
	public String getCity() {
		return city;
	}
	/**
	 * Sets city
	 * @param string
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * Association
	 * Gets school
	 * @return School
	 */
	@ManyToOne
	@JoinColumn(name="fk_school")
	public School getSchool() {
		return school;
	}
	/**
	 * Sets School
	 * @param School
	 */
	public void setSchool(School school) {
		this.school = school;
	}
	/**
	 * Gets headquarters
	 * @return boolean
	 */
	@Basic
	public Boolean getIsHQ() {
		return isHQ;
	}
	/**
	 * Sets headquarters
	 * @param boolean
	 */
	public void setIsHQ(Boolean isHQ) {
		this.isHQ = isHQ;
	}
	/**
	 * Association
	 * Gets teams
	 * @return Teams list
	 */
	@OneToMany(mappedBy="location")
	public List<Team> getTeams() {
		return teams;
	}
	/**
	 * Sets teams list
	 * @param Team list
	 */
	public void setTeams(List<Team> teams) {
		this.teams = teams;
	}
	/**
	 * Add team
	 * @param Team
	 */
	public void addTeam(Team team) {
		if(!this.teams.contains(team)) {
			this.teams.add(team);
		}
	}
	/**
	 * Remove team
	 * @param Team
	 */
	public void removeTeam(Team team) {
		if(this.teams.contains(team)) {
			this.teams.remove(team);
		}
	}
}
