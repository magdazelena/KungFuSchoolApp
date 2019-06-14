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
 * @param Integer
 * @param String
 * @param Integer
 */
	public EquipmentSportive(Integer serial, String name, Integer mingrade) {
		super(serial, name);
		setMinGrade(mingrade);
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
	 * Gets min grade
	 * @return integer
	 */
	@Basic
	public Integer getMinGrade() {
		return minGrade;
	}
	/**
	 * Sets min grade
	 * @param integer
	 */
	public void setMinGrade(Integer minGrade) {
		this.minGrade = minGrade;
	}
	/**
	 * Association
	 * Gets Rental list
	 * @return Rental list
	 */
	@OneToMany(mappedBy = "eqSportive", cascade = CascadeType.ALL, orphanRemoval = true)
	public List<Rental> getRented() {
		return rented;
	}
/**
 * Sets Rented
 * @param Rental list
 */
	public void setRented(List<Rental> rented) {
		this.rented = rented;
	}
	/**
	 * Add Rented
	 * @param Rental
	 */
	public void addRented(Rental rent) {
		if(!this.rented.contains(rent)) {
			this.rented.add(rent);
			rent.setEqSportive(this);
		}
	}
	/**
	 * Remove rented
	 * @param Rental
	 */
	public void removeRented(Rental rent) {
		if(this.rented.contains(rent)) {
			this.rented.remove(rent);
			rent.setEqSportive(null);
		}
			
	}
}
