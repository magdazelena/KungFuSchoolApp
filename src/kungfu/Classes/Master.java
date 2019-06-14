package kungfu.Classes;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import kungfu.Classes.Member.Status;
/**
 * Master class
 * @see java.lang.Object
 */
@Entity(name="Master")
public class Master {
	private long id;
	private Member member;
	private Integer grade = 13;
	private Master master;
	private List<Master> followers = new ArrayList<>();
	private List<Team> ledTeams = new ArrayList<>();
	private School ledSchool = null;
	private List<EquipmentDecorative> decors = new ArrayList<>();
	public Master() {}
	/**
	 * Constructor
	 * @param Member
	 * @param Master
	 * @throws Exception
	 */
	public Master(Member member, Master master) throws Exception {
		if(member == null) throw new Exception ("Member must exist to become master");
		if(member.checkIfMinor()) throw new Exception("Cannot create master under age 18");
		if(member.getStudent()!= null) throw new Exception("This member is a master ");
		if(master == null) throw new Exception("All masters must have a master");
		setMember(member);
		setMaster(master);
		member.setMaster(this);
		master.addFollower(this);
	}
	/**
	 * Construtor (no master)
	 * @param Member
	 * @throws Exception
	 */
	public Master(Member member) throws Exception {
		if(member == null) throw new Exception ("Member must exist to become master");
		if(member.checkIfMinor()) throw new Exception("Cannot create master under age 18");
		setMember(member);
		member.setMaster(this);
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
	 * Assocation 
	 * Gets Member
	 * @return Member
	 */
	 @OneToOne
	 @JoinColumn(name = "fk_member")
	public Member getMember() {
		return this.member;
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
	 * Gets grade
	 * @return Integer
	 */
	@Basic
	public Integer getGrade() {
		return grade;
	}
/**
 * Sets grade
 * @param Integer
 */
	public void setGrade(Integer grade) {
		this.grade = grade;
	}
	/**
	 * Association 
	 * Gets Master
	 * @return Master
	 */
	@ManyToOne
	public Master getMaster() {
		return master;
	}
/**
 * Sets Master
 * 
 * @param Master
 */
	public void setMaster(Master master) {
		if(this.master == null) {
			if(master != null) {
				this.master = master;
				master.addFollower(this);
			}
		}
	}
	/**
	 * Association 
	 * Gets followers
	 * @return Master list
	 */
	@OneToMany(mappedBy = "master", cascade = CascadeType.ALL, orphanRemoval = true)
	public List<Master> getFollowers() {
		return followers;
	}
	/**
	 * Sets followers
	 * @param Master list
	 */
	public void setFollowers(List<Master> followers) {
		this.followers = followers;
	}
	
	/**
	 * Add follower
	 * @param Master
	 */
	public void addFollower(Master master) {
		if(!this.followers.contains(master)) {
			this.followers.add(master);
			master.setMaster(this);
		}	
	}
	/**
	 * Remove follower
	 * @param Master
	 */
	public void removeFollower(Master master) {
		if(this.followers.contains(master)) {
			this.followers.remove(master);
			master.setMaster(null);
			master = null;
		}	
	}
	/**
	 * Association 
	 * Gets Led teams
	 * @return Team list
	 */
	@OneToMany(mappedBy = "master", cascade = CascadeType.ALL, orphanRemoval = true)
	public List<Team> getLedTeams() {
		return ledTeams;
	}
/**
 * Sets led teams
 * @param Team list
 */
	public void setLedTeams(List<Team> ledTeams) {
		this.ledTeams = ledTeams;
	}
	/**
	 * Add team
	 * @param Team
	 */
	public void addTeam(Team team) {
		if(!this.getLedTeams().contains(team)) {
			this.ledTeams.add(team);
			team.setMaster(this);
		}
	}
/**
 * Remove team
 * @param Team
 */
	public void removeTeam(Team team) {
		if(this.getLedTeams().contains(team)) {
			this.ledTeams.remove(team);
			team.setMaster(null);
		}
	}
	/**
	 * Association 
	 * Gets led school
	 * @return School
	 */
	@OneToOne(mappedBy="leader")
	public School getLedSchool() {
		return ledSchool;
	}
/**
 * Sets led school
 * @param School
 */
	public void setLedSchool(School ledSchool) {
		if(this.ledSchool == null) {
			if(ledSchool != null) {
				this.ledSchool = ledSchool;
				ledSchool.setLeader(this);
			}
		}
	}
	/**
	 * Association
	 * Gets owned decors
	 * @return EquipmentDecorative list
	 */
	@OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
	public List<EquipmentDecorative> getDecors() {
		return decors;
	}
/**
 * Sets decors
 * @param EquipmentDecorative list
 */
	public void setDecors(List<EquipmentDecorative> decors) {
		this.decors = decors;
	}
	/**
	 * Add decor
	 * @param EquipmentDecorative
	 */
	public void addDecor(EquipmentDecorative decor) {
		if(!getDecors().contains(decor)) {
			this.decors.add(decor);
			decor.setOwner(this);
		}
	}
	/**
	 * Check if master is administrator
	 * @return boolean
	 */
	@Transient
	public Boolean isAdministator() {
		return this.getLedSchool() != null ? true: false;
	}
	/**
	 * Overrides to string
	 * @return string
	 */
	@Override
	public String toString() {
		return "Mistrz: "+ getMember().getPerson().getName();
	}
	/**
	 * payFee method
	 *  
	 */
	public void payFee() {
		if(getMember().getStatus() ==Status.Suspended) {
			getMember().setStatus(Status.Active);
		}
		System.out.println("Opłacono składkę w wysokości "+ getMember().getMonthFee());
	}
}
