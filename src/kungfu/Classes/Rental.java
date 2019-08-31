package kungfu.Classes;

import java.sql.Date;
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
	private Date rentDay;
	private Date returnDay = null;
	private EquipmentSportive eqSportive= null;
	private Member member = null;
	public Rental() {}
	/**
	 * Constructor
	 * @param member
	 * @param eq
	 * @throws Exception
	 */
	public Rental(Member member, EquipmentSportive eq) throws Exception {
		if(member.getStudent() != null)
			if(member.getStudent().getGrade() < eq.getMinGrade())
				throw new Exception("Member grade is too low to rent this equipment");
		setMember(member);
		setEqSportive(eq);
		member.addRental(this);
		eq.addRented(this);
		this.rentDay = Date.valueOf(LocalDate.now());
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
	 * Gets rent dat
	 * @return rentDay
	 */
	@Basic
	public Date getRentDay() {
		return rentDay;
	}
	/**
	 * Sets rent day
	 * @param rentDay
	 */
	public void setRentDay(Date rentDay) {
		this.rentDay = rentDay;
	}
	/**
	 * Gets return day
	 * @return returnDay
	 */
	@Basic
	public Date getReturnDay() {
		return returnDay;
	}
	/**
	 * Sets return day
	 * @param returnDay
	 */
	public void setReturnDay(Date returnDay) {
		this.returnDay = returnDay;
	}
	/**
	 * Gets Equipment Sportive
	 * @return eqSportive
	 */
	@ManyToOne
	public EquipmentSportive getEqSportive() {
		return eqSportive;
	}
	/**
	 * Sets Equipment sportive
	 * @param eqSportive
	 */
	public void setEqSportive(EquipmentSportive eqSportive) {
		this.eqSportive = eqSportive;
	}
	/**
	 * Gets Member
	 * @return member
	 */
	@ManyToOne
	public Member getMember() {
		return member;
	}
	/**
	 * Sets Member
	 * @param member
	 */
	public void setMember(Member member) {
		this.member = member;
	}
}
