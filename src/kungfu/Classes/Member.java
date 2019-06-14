package kungfu.Classes;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
/**
 * Member class
 * @see java.lang.Object
 */
@Entity(name="Member")
public class Member {
	
		private long id;
	
		public enum Status {New, Active, Suspended, Excluded}
		private LocalDate joinDate;
		private LocalDate birthDate;
	    private Status status;
	    static Integer yearFee = 200;
	    private Integer monthFee;
	    private Person person = null;
	    private Student student = null;
	    private Master master = null;
	    private String formerClub = null;
	    
	    private List<MemberTeam> memberTeams = new ArrayList<>();
	    
	    public Member() {}
	    /**
	     * Constructor
	     * @param Person 
	     * @param LocalDate birthday
	     * @param Integer fee
	     * @throws Exception
	     */
	    public Member(Person p, LocalDate birthDate, Integer monthFee) throws Exception {
	    	if(p == null) throw new Exception("Person must exist to become a member");
	    	if(p.getMember() != null) throw new Exception("The person is already a member");
	    	setPerson(p);
	    	this.birthDate = birthDate;
	    	this.monthFee = monthFee;
	    	this.status = Status.New;
	    	this.joinDate = LocalDate.now();
	    	p.setMember(this);
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
	     * Association
	     * Gets Person
	     * @return Person
	     */
	    //asocjacja
	   @OneToOne
	   @JoinColumn(name = "fk_person")
	   public Person getPerson() {
		   return this.person;
	   }
	   /**
	    * Sets Person
	    * @param Person
	    * @throws Exception
	    */
	   public void setPerson(Person p) throws Exception {
		   if(p == null) throw new Exception("Can't set person to null");
		   if(getPerson() == null) 
		   this.person = p;
	   }
	    /**
	     * Association
	     * Gets MemberTeams
	     * @return MemberTeam list
	     */
	   @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
	   public List<MemberTeam> getMemberTeams(){
		   return this.memberTeams;
	   }
	   /**
	    * Adds member team
	    * @param MemberTeam
	    */
	   public void addMemberTeam(MemberTeam pg) {
		   if(this.status == Status.New)
			  this.setStatus(Status.Active);
		   if(!this.getMemberTeams().contains(pg)) {
			   getMemberTeams().add(pg);
			   pg.setMember(this);
		   }
	   }
	   /**
	    * Removes MemberTeam
	    * @param MemberTeam
	    */
	   public void removeMemberTeam(MemberTeam pg) {
		   if(this.getMemberTeams().contains(pg)) {
			   getMemberTeams().remove(pg);
			   pg.setMember(null);
		   }
	   }
	   /**
	    * Sets MemberTeam
	    * @param MemberTeam list
	    */
	   public void setMemberTeams(List<MemberTeam> pg) {
		   this.memberTeams = pg;
	   } 
	   
	   
	 /**
	  * Gets Status
	  * @return Enum 
	  */
	@Enumerated 
	public Status getStatus() {
		return this.status;
	}
	/**
	 * Sets status
	 * @param Enum Status
	 */
	public void setStatus(Status status) {
		this.status = status;
	}
	/**
	 * Gets Join date
	 * @return LocalDate
	 */
	@Basic
	public LocalDate getJoinDate() {
		return this.joinDate;
	}
	/**
	 * SetsJoinDate
	 * @param LocalDate
	 */
	public void setJoinDate(LocalDate date) {
		this.joinDate = date;
	}
	/**
	 * Gets birth date
	 * @return LocalDate
	 */
	@Basic
	public LocalDate getBirthDate() {
		return this.birthDate;
	}
	/**
	 * Sets birthdate
	 * @param LocalDate
	 */
	public void setBirthDate(LocalDate date) {
		this.birthDate = date;
	}
	/**
	 * Gets year fee
	 * @return Integer
	 */
	@Basic
	public Integer getYearFee() {
		return this.yearFee;
	}
	/**
	 * Sets year Fee
	 * @param Integer
	 */
	public void setYearFee(Integer yearFee) {
		this.yearFee = yearFee;
	}
	/**
	 * Gets Month Fee
	 * @return Integer
	 */
	@Basic 
	public Integer getMonthFee() {
		return this.monthFee;
	}
	/**
	 * Sets month fee
	 * @param Integer
	 */
	public void setMonthFee(Integer money) {
		this.monthFee = money;
	}
	/**
	 * Gets Former Club
	 * @return String
	 */
    @Basic
    public String getFormerClub() {
    	return this.formerClub;
    }
    /**
     * Sets Former Club
     * @param String
     */
    public void setFormerClub(String fc) {
    	this.formerClub = fc;
    }
    /**
     * Check if member is minor
     * @return boolean
     */
    @Transient
    public Boolean checkIfMinor() {
    	return Period.between(getBirthDate(), LocalDate.now()).getYears() < 18;
    }
	/**
	 * Check how long is a member registered
	 * @return integer
	 */
	@Transient
	public Integer getYears() {
		return Period.between(getJoinDate(),LocalDate.now()).getYears();
	}
	/**
	 * Override to String
	 */
	@Override
	public String toString() {
		return String.format("Person: %s, Member for %s years, joined: %s, status: %s, month fee: %s. Id: %s (@%s)", getPerson().getFullName(), getYears(), getJoinDate(), getStatus(), getMonthFee(), getId(), super.hashCode());}
	
	/**
	 * Association
	 *Gets student
	 * @return Student
	 */
	@OneToOne(mappedBy = "member", orphanRemoval = true)
	public Student getStudent() {
		return student;
	}
	/**Sets student
	 * 
	 * @param Student
	 */
	public void setStudent(Student student) {
		if(this.master == null && this.student==null) {
			this.student = student;
		}
		
	}
	/**
	 * Gets master
	 * @return Master
	 */
	@OneToOne(mappedBy = "member", orphanRemoval = true)
	public Master getMaster() {
		return master;
	}
	/**
	 * Sets Master
	 * @param Master
	 */
	public void setMaster(Master master) {
		if(this.master == null && this.student==null) {
			this.master = master;
		}
		
	}
	/**
	 * Upgrade to Master
	 * @param Master
	 * @return Master
	 * @throws Exception 
	 */
	public Master upgradeToMaster(Master master) throws Exception {
		if(this.student != null && !checkIfMinor()) {
			if(this.student.getGrade() ==12) {
				if(this.student.getCaretaker() != null) {
					this.student.setCaretaker(null);
				}
				setStudent(null);
				Master m = new Master(this, master);
				return m;
			}
		}
		return null;
	}
}
