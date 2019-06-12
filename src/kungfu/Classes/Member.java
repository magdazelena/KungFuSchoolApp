package kungfu.Classes;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity(name="Member")
public class Member {
	
		private long id;
	
		public enum Status {New, Active, Suspended, Excluded}
		private LocalDate joinDate;
		private LocalDate birthDate;
	    private Status status;
	    private static Integer yearFee = 200;
	    private Integer monthFee;
	    private Person person = null;
	    private Student student = null;
	    private Master master = null;
	    private String formerClub = null;
	    
	    private List<MemberTeam> memberTeams = new ArrayList<>();
	    
	    public Member() {}
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
	    @Id
	    @GeneratedValue(generator="increment")
	    @GenericGenerator(name="increment", strategy = "increment")
	    public long getId() {
	        return id;
	    }

	    private void setId(long id) {
	        this.id = id;
	    }
	    //asocjacja
	   @OneToOne
	   @JoinColumn(name = "fk_person")
	   public Person getPerson() {
		   return this.person;
	   }
	   public void setPerson(Person p) throws Exception {
		   if(p == null) throw new Exception("Can't set person to null");
		   if(getPerson() == null) 
		   this.person = p;
	   }
	    
	   @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
	   private List<MemberTeam> getMemberTeams(){
		   return this.memberTeams;
	   }
	   public void addMemberTeam(MemberTeam pg) {
		   if(this.getMemberTeams().isEmpty())
			   setStatus(Status.Active);
		   if(!this.getMemberTeams().contains(pg)) {
			   getMemberTeams().add(pg);
			   pg.setMember(this);
		   }
	   }
	   public void removeMemberTeam(MemberTeam pg) {
		   if(this.getMemberTeams().contains(pg)) {
			   getMemberTeams().remove(pg);
			   pg.setMember(null);
		   }
	   }
	   public void setMemberTeams(List<MemberTeam> pg) {
		   this.memberTeams = pg;
	   } 
	   
	   
	@Enumerated 
	public Status getStatus() {
		return this.status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	
	@Basic
	public LocalDate getJoinDate() {
		return this.joinDate;
	}
	public void setJoinDate(LocalDate date) {
		this.joinDate = date;
	}
	@Basic
	public LocalDate getBirthDate() {
		return this.birthDate;
	}
	public void setBirthDate(LocalDate date) {
		this.birthDate = date;
	}
	@Basic
	public Integer getYearFee() {
		return this.yearFee;
	}
	public void setYearFee(Integer yearFee) {
		this.yearFee = yearFee;
	}
	@Basic 
	public Integer getMonthFee() {
		return this.monthFee;
	}
	public void setMonthFee(Integer money) {
		this.monthFee = money;
	}
    @Basic
    public String getFormerClub() {
    	return this.formerClub;
    }
    public void setFormerClub(String fc) {
    	this.formerClub = fc;
    }
    @Transient
    public Boolean checkIfMinor() {
    	return Period.between(getBirthDate(), LocalDate.now()).getYears() < 18;
    }
	
	@Transient
	public Integer getYears() {
		return Period.between(getJoinDate(),LocalDate.now()).getYears();
	}
	@Override
	public String toString() {
		return String.format("Person: %s, Member for %s years, joined: %s, status: %s, month fee: %s. Id: %s (@%s)", getPerson().getFullName(), getYears(), getJoinDate(), getStatus(), getMonthFee(), getId(), super.hashCode());}
	
	@OneToOne(mappedBy = "member", orphanRemoval = true)
	public Student getStudent() {
		return student;
	}
	
	public void setStudent(Student student) {
		if(this.master == null && this.student==null) {
			this.student = student;
		}
		
	}
	@OneToOne(mappedBy = "member", orphanRemoval = true)
	public Master getMaster() {
		return master;
	}
	public void setMaster(Master master) {
		if(this.master == null && this.student==null) {
			this.master = master;
		}
		
	}
}
