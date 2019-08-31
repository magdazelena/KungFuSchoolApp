package kungfu.Classes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;
/**
 * School class
 * @see java.lang.Object
 */
@Entity(name="School")
public class School {
	
	
	private long id;
	private Master leader;
	private Integer studentCount = 0;
	private Location hQ = null;
	private List<Location> locations = new ArrayList<>();
	private Accountant accountant;
	private List<Equipment> equipment = new ArrayList<>();
	//private Map<Integer, Equipment> equipment = new HashMap<>();
	public School() {}
	/**
	 * School constructor
	 * @param leader
	 * @param accountant
	 */
	public School(Master leader, Accountant accountant) {
		setLeader(leader);
		setAccountant(accountant);
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
	 * Gets leader
	 * @return leader
	 */
	@OneToOne
	@JoinColumn(name = "fk_leader")
	public Master getLeader() {
		return leader;
	}
	/**
	 * Sets leader
	 * @param leader
	 */
	public void setLeader(Master leader) {
		this.leader = leader;
		leader.setLedSchool(this);
	}
	/**
	 * Gets studentCount
	 * @return studentCount
	 */
	@Basic
	public Integer getStudentCount() {
		return studentCount;
	}

	public void setStudentCount(Integer studentCount) {
		this.studentCount = studentCount;
	}
	public void incStudentCount() throws Exception {
		if(getStudentCount()<100)
			setStudentCount(getStudentCount()+1);
		else throw new Exception("Student limit exceeded");
	}
	public void decStudentCount() {
		if(getStudentCount() >0)
			setStudentCount(getStudentCount()-1);
	}
	@Transient
	public Location gethQ() {
		Location res = null;
		for(Location l :locations) {
			if(l.getIsHQ()) res = l;
		};
		return res;
	}

	public void sethQ(Location hQ) {
		if(this.hQ != null) {
			this.hQ.setIsHQ(false);
			this.hQ = hQ;
		}
		hQ.setIsHQ(true);
	}
	@OneToMany(mappedBy="school")
	public List<Location> getLocations() {
		return locations;
	}

	public void setLocations(List<Location> locations) {
		this.locations = locations;
	}
	public void addNewLocation(String street, Integer number, Integer zipcode, String city, Boolean hq) throws Exception {
		if(hq) {
			if(this.gethQ() !=null) throw new Exception("School already has headquaters");
		} 
		Location.createLocation(this, street, number, zipcode, city, hq);
	}
	public void addLocation(Location location) {
		if(!this.getLocations().contains(location))
			this.locations.add(location);
		if(location.getIsHQ())
			this.sethQ(location);
	}
	@ManyToOne
	public Accountant getAccountant() {
		return accountant;
	}

	public void setAccountant(Accountant accountant) {
		if(this.accountant == null) {
			this.accountant = accountant;
			accountant.addSchool(this);
		}
		
	}
	@OneToMany(mappedBy="school")
	public List<Equipment> getEquipment() {
		return equipment;
	}
	public void setEquipment(List<Equipment> equipment) {
		this.equipment = equipment;
	}
	public void addEquipment(Equipment eq) {
		if(!this.equipment.contains(eq)) {
			this.equipment.add(eq);
			eq.setSchool(this);
		}
	}
	public void removeEquipment(Equipment eq) {
		if(this.equipment.contains(eq)) {
			this.equipment.remove(eq);
			eq.setSchool(null);
		}
	}
	public String toString() {
		return "Szkoła na "+this.gethQ().getStreet()+", "+this.gethQ().getCity();
	}
}
