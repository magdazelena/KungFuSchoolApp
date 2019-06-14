package kungfu.Classes;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;

import kungfu.Classes.Member.Status;
/**
 * Student class
 * @see java.lang.Object
 *
 */
@Entity(name="Student")
public class Student {
	private long id;
	private Integer grade = 0;
	private final int maxGrade = 12;
	private Member member;
	private Caretaker caretaker = null;
	public Student() {}
	
	/**
	 * Constructor
	 * @param Member
	 * @throws Exception
	 */
	public Student(Member member) throws Exception {
		if(member == null) throw new Exception("Member must exist to become Student");
		if(this.member != null) throw new Exception("Student is a member already");
		if(member.getMaster()!= null) throw new Exception("This member is a master ");
		if(member.checkIfMinor()) throw new Exception("Minor student must have a Caretaker");
		this.setMember(member);
		member.setStudent(this);
	}
	/**
	 * Constructor
	 * @param Member
	 * @param Caretaker
	 * @throws Exception
	 */
	public Student(Member member, Caretaker caretaker) throws Exception {
		if(member == null) throw new Exception("Member must exist to become Student");
		if(this.member != null) throw new Exception("Student is a member already");
		setMember(member);
		setCaretaker(caretaker);
		member.setStudent(this);
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
	 * Gets grade
	 * @return integer
	 */
	@Basic
	public Integer getGrade() {
		return grade;
	}
/**
 * Sets grade
 * @param integer
 * @throws Exception
 */
	public void setGrade(Integer grade) throws Exception {
		if(grade <= maxGrade) {
			if(grade > this.grade+1 && this.grade != 0) throw new Exception("Grade may be elevated by one only!");
				this.grade = grade;
		}else { 
			throw new Exception("Student must have grade lower or equal 12. ");
		};
	}
	/**
	 * Upgrade (+1 grade)
	 * @throws Exception
	 */
	public void upGrade() throws Exception {
		if(this.grade <= maxGrade) {
				this.grade++;
		}else { 
			throw new Exception("Student must have grade lower or equal 12. ");
		};
	}
	/**
	 * Association
	 * Gets Member
	 * @return Member
	 */
	 @OneToOne
	 @JoinColumn(name = "fk_member")
	public Member getMember() {
		return member;
	}
/**
 * Sets Member
 * @param Member
 * @throws Exception
 */
	public void setMember(Member member) throws Exception {
		if(member == null) throw new Exception("Can't set member to null");
		  if(getMember() == null)
			this.member = member;
			
		
	}
	/**
	 * Association 
	 * Gets Caretaker
	 * @return Caretaker
	 */
	@ManyToOne
	public Caretaker getCaretaker() {
		return caretaker;
	}
/**
 * Sets Caretaker
 * @param Caretaker
 */
	public void setCaretaker(Caretaker caretaker) {
		this.caretaker = caretaker;
	}
/**
 * Pay fee
 */
	public void payFee() {
		if(getMember().getStatus() ==Status.Suspended) {
			getMember().setStatus(Status.Active);
		}
		System.out.println("Opłacono składkę w wysokości "+ getMember().getMonthFee());
	}

	
}
