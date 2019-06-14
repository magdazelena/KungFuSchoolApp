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

@Entity(name="Student")
public class Student {
	private long id;
	private Integer grade = 0;
	private final int maxGrade = 12;
	private Member member;
	private Caretaker caretaker = null;
	public Student() {}
	
	public Student(Member member) throws Exception {
		if(member == null) throw new Exception("Member must exist to become Student");
		if(this.member != null) throw new Exception("Student is a member already");
		if(member.getMaster()!= null) throw new Exception("This member is a master ");
		if(member.checkIfMinor()) throw new Exception("Minor student must have a Caretaker");
		this.setMember(member);
		member.setStudent(this);
	}
	public Student(Member member, Caretaker caretaker) throws Exception {
		if(member == null) throw new Exception("Member must exist to become Student");
		if(this.member != null) throw new Exception("Student is a member already");
		setMember(member);
		setCaretaker(caretaker);
		member.setStudent(this);
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
	public Integer getGrade() {
		return grade;
	}

	public void setGrade(Integer grade) throws Exception {
		if(grade <= maxGrade) {
			if(grade > this.grade+1 && this.grade != 0) throw new Exception("Grade may be elevated by one only!");
				this.grade = grade;
		}else { 
			throw new Exception("Student must have grade lower or equal 12. ");
		};
	}
	public void upGrade() throws Exception {
		if(this.grade <= maxGrade) {
				this.grade++;
		}else { 
			throw new Exception("Student must have grade lower or equal 12. ");
		};
	}
	 @OneToOne
	 @JoinColumn(name = "fk_member")
	public Member getMember() {
		return member;
	}

	public void setMember(Member member) throws Exception {
		if(member == null) throw new Exception("Can't set member to null");
		  if(getMember() == null)
			this.member = member;
			
		
	}
	@ManyToOne
	public Caretaker getCaretaker() {
		return caretaker;
	}

	public void setCaretaker(Caretaker caretaker) {
		this.caretaker = caretaker;
	}

	public void payFee() {
		if(getMember().getStatus() ==Status.Suspended) {
			getMember().setStatus(Status.Active);
		}
		System.out.println("Opłacono składkę w wysokości "+ getMember().getMonthFee());
	}

	
}
