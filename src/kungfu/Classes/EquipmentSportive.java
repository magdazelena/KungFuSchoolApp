package kungfu.Classes;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;
/**
 * Equipment sportive class
 * @version 1.0
 * @author magda
 * @see java.lang.Object
 */
@Entity(name="EquipmentSportive")
@DiscriminatorValue("sportive")  
public class EquipmentSportive extends Equipment{
	private long id;
	private Integer minGrade;
	private List<Rental> rented = new ArrayList<>();
	public EquipmentSportive() {}
/**
 * Constructor
 * @param serial Serial number of the equipment as Integer
 * @param name Name of the equipment
 * @param mingrade Minimum grade of member to be able to rent equipment
 */
	public EquipmentSportive(Integer serial, String name, Integer mingrade) {
		super(serial, name);
		setMinGrade(mingrade);
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
	 * Gets minimum grade of member to be able to rent equipment
	 * @return minGrade
	 */
	@Basic
	public Integer getMinGrade() {
		return minGrade;
	}
	/**
	 * Sets minimum grade of member to be able to rent equipment
	 * @param minGrade Minimum grade of member to be able to rent equipment
	 */
	public void setMinGrade(Integer minGrade) {
		this.minGrade = minGrade;
	}
	/**
	 * Association
	 * Gets Rental list
	 * @return rented
	 */
	@OneToMany(mappedBy = "eqSportive", cascade = CascadeType.ALL, orphanRemoval = true)
	public List<Rental> getRented() {
		return rented;
	}
/**
 * Sets Rented
 * @param rented List of rentals (who and dates) 
 */
	public void setRented(List<Rental> rented) {
		this.rented = rented;
	}
	/**
	 * Add Rental
	 * @param rent Rental of the equipment
	 */
	public void addRented(Rental rent) {
		if(!this.rented.contains(rent)) {
			this.rented.add(rent);
			rent.setEqSportive(this);
		}
	}
	/**
	 * Remove rental
	 * @param rent Rental of the equipment
	 */
	public void removeRented(Rental rent) {
		if(this.rented.contains(rent)) {
			this.rented.remove(rent);
			rent.setEqSportive(null);
		}
			
	}
}
