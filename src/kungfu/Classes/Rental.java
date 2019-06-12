package kungfu.Classes;

import java.time.LocalDate;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

@Entity(name="Rental")
public class Rental {
	private long id;
	private LocalDate rentDay;
	private LocalDate returnDay = null;
	private EquipmentSportive eqSportive= null;
	private Member member = null;
	public Rental() {}
	
	public Rental(Member member, EquipmentSportive eq) throws Exception {
		if(member.getStudent() != null)
			if(member.getStudent().getGrade() < eq.getMinGrade())
				throw new Exception("Member grade is too low to rent this equipment");
		setMember(member);
		setEqSportive(eq);
		this.rentDay = LocalDate.now();
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
	public LocalDate getRentDay() {
		return rentDay;
	}

	public void setRentDay(LocalDate rentDay) {
		this.rentDay = rentDay;
	}
	@Basic
	public LocalDate getReturnDay() {
		return returnDay;
	}

	public void setReturnDay(LocalDate returnDay) {
		this.returnDay = returnDay;
	}
	@ManyToOne
	public EquipmentSportive getEqSportive() {
		return eqSportive;
	}

	public void setEqSportive(EquipmentSportive eqSportive) {
		this.eqSportive = eqSportive;
	}
	@ManyToOne
	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}
}
