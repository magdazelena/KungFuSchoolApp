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
	   * @param p
	   * @param birthDate
	   * @param monthFee
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
	     * Association
	     * Gets Person
	     * @return person
	     */
	    //asocjacja
	   @OneToOne
	   @JoinColumn(name = "fk_person")
	   public Person getPerson() {
		   return this.person;
	   }
	   /**
	    * Sets Person
	    * @param p
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
	     * @return memberTeams
	     */
	   @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
	   public List<MemberTeam> getMemberTeams(){
		   return this.memberTeams;
	   }
	   /**
	    * Adds member team
	    * @param pg
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
	    * @param pg
	    */
	   public void removeMemberTeam(MemberTeam pg) {
		   if(this.getMemberTeams().contains(pg)) {
			   getMemberTeams().remove(pg);
			   pg.setMember(null);
		   }
	   }
	   /**
	    * Sets MemberTeam
	    * @param pg
	    */
	   public void setMemberTeams(List<MemberTeam> pg) {
		   this.memberTeams = pg;
	   } 
	   
	   
	 /**
	  * Gets Status
	  * @return status 
	  */
	@Enumerated 
	public Status getStatus() {
		return this.status;
	}
	/**
	 * Sets status
	 * @param status
	 */
	public void setStatus(Status status) {
		this.status = status;
	}
	/**
	 * Gets Join date
	 * @return joinDate
	 */
	@Basic
	public LocalDate getJoinDate() {
		return this.joinDate;
	}
	/**
	 * SetsJoinDate
	 * @param date
	 */
	public void setJoinDate(LocalDate date) {
		this.joinDate = date;
	}
	/**
	 * Gets birth date
	 * @return birthDate
	 */
	@Basic
	public LocalDate getBirthDate() {
		return this.birthDate;
	}
	/**
	 * Sets birthdate
	 * @param date
	 */
	public void setBirthDate(LocalDate date) {
		this.birthDate = date;
	}
	/**
	 * Gets year fee
	 * @return yearFee
	 */
	@Basic
	public Integer getYearFee() {
		return this.yearFee;
	}
	/**
	 * Sets year Fee
	 * @param yearFee
	 */
	public void setYearFee(Integer yearFee) {
		this.yearFee = yearFee;
	}
	/**
	 * Gets Month Fee
	 * @return monthFee
	 */
	@Basic 
	public Integer getMonthFee() {
		return this.monthFee;
	}
	/**
	 * Sets month fee
	 * @param monthFee
	 */
	public void setMonthFee(Integer money) {
		this.monthFee = money;
	}
	/**
	 * Gets Former Club
	 * @return formerClub
	 */
    @Basic
    public String getFormerClub() {
    	return this.formerClub;
    }
    /**
     * Sets Former Club
     * @param fc
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
	 * @return student
	 */
	@OneToOne(mappedBy = "member", orphanRemoval = true)
	public Student getStudent() {
		return student;
	}
	/**Sets student
	 * 
	 * @param student
	 */
	public void setStudent(Student student) {
		if(this.master == null && this.student==null) {
			this.student = student;
		}
		
	}
	/**
	 * Gets master
	 * @return master
	 */
	@OneToOne(mappedBy = "member", orphanRemoval = true)
	public Master getMaster() {
		return master;
	}
	/**
	 * Sets Master
	 * @param master
	 */
	public void setMaster(Master master) {
		if(this.master == null && this.student==null) {
			this.master = master;
		}
		
	}
	/**
	 * Upgrade to Master
	 * @param master
	 * @return m
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
