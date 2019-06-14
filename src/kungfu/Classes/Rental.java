package kungfu.Classes;

import java.time.LocalDate;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;
/**
 * Rental class
 * @see java.lang.Object
 */
@Entity(name="Rental")
public class Rental {
	private long id;
	private LocalDate rentDay;
	private LocalDate returnDay = null;
	private EquipmentSportive eqSportive= null;
	private Member member = null;
	public Rental() {}
	/**
	 * Constructor
	 * @param Member member
	 * @param EquipmentSportive eq
	 * @throws Exception
	 */
	public Rental(Member member, EquipmentSportive eq) throws Exception {
		if(member.getStudent() != null)
			if(member.getStudent().getGrade() < eq.getMinGrade())
				throw new Exception("Member grade is too low to rent this equipment");
		setMember(member);
		setEqSportive(eq);
		this.rentDay = LocalDate.now();
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
	 * Gets rent dat
	 * @return LocalDate
	 */
	@Basic
	public LocalDate getRentDay() {
		return rentDay;
	}
	/**
	 * Sets rent day
	 * @param LocalDate
	 */
	public void setRentDay(LocalDate rentDay) {
		this.rentDay = rentDay;
	}
	/**
	 * Gets return day
	 * @return LocalDate
	 */
	@Basic
	public LocalDate getReturnDay() {
		return returnDay;
	}
	/**
	 * Sets return day
	 * @param LocalDate
	 */
	public void setReturnDay(LocalDate returnDay) {
		this.returnDay = returnDay;
	}
	/**
	 * Gets Equipment Sportive
	 * @return EquipmentSportive
	 */
	@ManyToOne
	public EquipmentSportive getEqSportive() {
		return eqSportive;
	}
	/**
	 * Sets Equipment sportive
	 * @param EquipmentSportive
	 */
	public void setEqSportive(EquipmentSportive eqSportive) {
		this.eqSportive = eqSportive;
	}
	/**
	 * Gets Member
	 * @return Member
	 */
	@ManyToOne
	public Member getMember() {
		return member;
	}
	/**
	 * Sets Member
	 * @param Member
	 */
	public void setMember(Member member) {
		this.member = member;
	}
}
