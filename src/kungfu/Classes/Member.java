package kungfu.Classes;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
/**
 * Member class
 * @version 1.0
 * @author magda
 * @see java.lang.Object
 */
@Entity(name="Member")
public class Member {
	
		private long id;
	
		public enum Status {New, Active, Suspended, Excluded}
		private Date joinDate;
		private Date birthDate;
	    private Status status;
	    static Integer yearFee = 200;
	    private Integer monthFee;
	    private Person person = null;
	    private Student student = null;
	    private static Set<Student> students = new HashSet<>();
	    private Master master = null;
	    private static Set<Master> masters = new HashSet<>();
	    private String formerClub = null;
	    
	    private List<MemberTeam> memberTeams = new ArrayList<>();
	    private List<Rental> memberRentals = new ArrayList<>();
	    private Member() {}
	  /**
	   * Member private constructor
	   * @param p Person to become Member
	   * @param birthDate Birthdate of Member LocalDate
	   * @param monthFee Monthly fee Integer
	   * @throws Exception if Person is null
	   */
	   private Member(Person p, LocalDate birthDate, Integer monthFee) throws Exception {
	    	if(p == null) throw new Exception("Person must exist to become a member");
	    	if(p.getMember() != null) throw new Exception("The person is already a member");
	    	setPerson(p);
	    	this.birthDate = Date.valueOf(birthDate);
	    	this.monthFee = monthFee;
	    	this.status = Status.New;
	    	this.joinDate = Date.valueOf(LocalDate.now());
	    	p.setMember(this);
	    }
	   /**
	    * Static method to create Member
	    * @param p Person to become member
	    * @param birthDate birth date of Member LocalDate
	    * @param monthFee monthly fee Integer
	    * @return Member
	    * @throws Exception if Person is null
	    */
	   public static Member createMember(Person p, LocalDate birthDate, Integer monthFee) throws Exception {
		   if(p == null) throw new Exception("Osoba nie istnieje");
		   Member m = new Member(p, birthDate, monthFee);
		   p.setMember(m);	
		   return m;
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
	    * @param p Person to become Member
	    * @throws Exception if Person is null
	    */
	   public void setPerson(Person p) throws Exception {
		   if(p == null) throw new Exception("Can't set person to null");
		   if(getPerson() == null) 
		   this.person = p;
	   }
	    /**
	     * Association
	     * Gets MemberTeams List
	     * @return memberTeams
	     */
	   @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
	   public List<MemberTeam> getMemberTeams(){
		   return this.memberTeams;
	   }
	   /**
	    * Adds member team
	    * @param pg MemberTeam for connecting Member with Team
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
	    * @param pg MemberTeam for connecting Member with Team
	    */
	   public void removeMemberTeam(MemberTeam pg) {
		   if(this.getMemberTeams().contains(pg)) {
			   getMemberTeams().remove(pg);
			   pg.setMember(null);
		   }
	   }
	   /**
	    * Sets MemberTeam
	    * @param pg MemberTeam for connecting Member with Team
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
	 * @param status Status in Enum (New, Active, Suspended, Excluded)
	 */
	public void setStatus(Status status) {
		this.status = status;
	}
	/**
	 * Gets Join date
	 * @return joinDate
	 */
	@Basic
	public Date getJoinDate() {
		return this.joinDate;
	}
	/**
	 * SetsJoinDate
	 * @param date Joining Date in sql.Date format
	 */
	public void setJoinDate(Date date) {
		this.joinDate = date;
	}
	/**
	 * Gets birth date
	 * @return birthDate
	 */
	@Basic
	public Date getBirthDate() {
		return this.birthDate;
	}
	/**
	 * Sets birthdate
	 * @param date in sql.Date format
	 */
	public void setBirthDate(Date date) {
		this.birthDate = date;
	}
	/**
	 * Gets year fee
	 * @return yearFee
	 */
	@Basic
	public Integer getYearFee() {
		return yearFee;
	}
	/**
	 * Sets year Fee
	 * @param yearFee Class value of yearFee
	 */
	public void setYearFee(Integer yearFee) {
		Member.yearFee = yearFee;
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
	 * @param money Integer of fee to be payed each month
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
     * @param fc Former Club of Member(optional)
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
    	return Period.between(getBirthDate().toLocalDate(), LocalDate.now()).getYears() < 18;
    }
	/**
	 * Check how long is a member registered
	 * @return integer
	 */
	@Transient
	public Integer getYears() {
		return Period.between(getJoinDate().toLocalDate(),LocalDate.now()).getYears();
	}
	/**
	 * Override to String
	 */
	@Override
	public String toString() {
		return String.format("Person: %s, Member for %s years, joined: %s, status: %s, month fee: %s. Id: %s (@%s)", getPerson().getFullName(), getYears(), getJoinDate(), getStatus(), getMonthFee(), getId(), super.hashCode());}
	
	/**
	 * Association
	 * Gets student
	 * @return student
	 */
	@OneToOne(mappedBy = "member", orphanRemoval = true)
	public Student getStudent() {
		return student;
	}
	/**Sets student
	 * 
	 * @param student Student to be set
	 * @throws Exception if student is already a member
	 */
	public void setStudent(Student student) throws Exception {
		if(this.master == null && this.student==null) {
			if(students.contains(student) && student != null) throw new Exception("Taki student już istnieje");
			   this.student = student;
			   students.add(student);
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
	 * @param master Master to be set
	 * @throws Exception if Master is already a Member
	 */
	public void setMaster(Master master) throws Exception {
		if(this.master == null && this.student==null ) {
			if(masters.contains(master) && master != null) throw new Exception("Taki mistrz już istnieje");
			   this.master = master;
			   masters.add(master);
		}
		
	}
	/**
	 * Upgrade to Master
	 * @param master Master doing the upgrade
	 * @return Master of this Member
	 * @throws Exception if this Member is not a student
	 */
	public Master upgradeToMaster(Master master) throws Exception {
		if(this.student != null && !checkIfMinor()) {
			if(this.student.getGrade() ==12) {
				if(this.student.getCaretaker() != null) {
					this.student.setCaretaker(null);
				}
				students.remove(this.student);
				setStudent(null);
				Master m = Master.createMaster(this, master);
				return m;
			}
		}
		return null;
	}
	/**
	 * @return the memberRentals
	 */
	@OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
	public List<Rental> getMemberRentals() {
		return memberRentals;
	}
	/**
	 * @param memberRentals the memberRentals to set
	 */
	public void setMemberRentals(List<Rental> memberRentals) {
		this.memberRentals = memberRentals;
	}
		/**
	    * Adding Rental to Member
	    * @param pg Rental to be added
	    */
	   public void addRental(Rental pg) {
		   if(!this.getMemberRentals().contains(pg)) {
			   getMemberRentals().add(pg);
			   pg.setMember(this);
		   }
	   }
	   /**
	    * Removes Rental from Member
	    * @param pg Rental to be removed
	    */
	   public void removeRental(Rental pg) {
		   if(this.getMemberRentals().contains(pg)) {
			   getMemberRentals().remove(pg);
			   pg.setMember(null);
		   }
	   }
	   /**
	    * Get number of students
	    * @return Integer student list size
	    */
	   public static Integer getStudentListSize() {
		   return students.size();
	   }
}
