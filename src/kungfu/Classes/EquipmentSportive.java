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

@Entity(name="EquipmentSportive")
@DiscriminatorValue("sportive")  
public class EquipmentSportive extends Equipment{
	private long id;
	private Integer minGrade;
	private List<Rental> rented = new ArrayList<>();
	public EquipmentSportive() {}

	public EquipmentSportive(Integer serial, String name, Integer mingrade) {
		super(serial, name);
		setMinGrade(mingrade);
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
	public Integer getMinGrade() {
		return minGrade;
	}
	
	public void setMinGrade(Integer minGrade) {
		this.minGrade = minGrade;
	}
	@OneToMany(mappedBy = "eqSportive", cascade = CascadeType.ALL, orphanRemoval = true)
	public List<Rental> getRented() {
		return rented;
	}

	public void setRented(List<Rental> rented) {
		this.rented = rented;
	}
	public void addRented(Rental rent) {
		if(!this.rented.contains(rent)) {
			this.rented.add(rent);
			rent.setEqSportive(this);
		}
	}
	public void removeRented(Rental rent) {
		if(this.rented.contains(rent)) {
			this.rented.remove(rent);
			rent.setEqSportive(null);
		}
			
	}
}
