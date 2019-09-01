package kungfu.Classes;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
 * Master class (part of Member)
 * @version 1.0
 * @author magda
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
	private Master() {}
/**
 * Private constructor 
 * @param member Member to become the Master
 * @param master Master of this Master
 * @throws Exception if Member is null
 */
	private Master(Member member, Master master) throws Exception {
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
	 * Constructor (no master)
	 * @param member Member to become Master
	 * @throws Exception if the member is null
	 */
	private Master(Member member) throws Exception {
		if(member == null) throw new Exception ("Member must exist to become master");
		if(member.checkIfMinor()) throw new Exception("Cannot create master under age 18");
		if(member.getStudent()!= null) throw new Exception("This member is a master ");
		setMember(member);
		member.setMaster(this);
	}
	/**
	 * Static method to create Master
	 * @param member Member to become master
	 * @return Master
	 * @throws Exception is Member is null
	 */
	public static Master createMaster(Member member) throws Exception {
		if(member == null) throw new Exception("Mistrz musi być członkiem");
		Master m = new Master(member);
		member.setMaster(m);
		return m;
	}
	/**
	 * Static method to create Master
	 * @param member Member to become master
	 * @param master Master of this Master
	 * @return Master
	 * @throws Exception is Member is null
	 */
	public static Master createMaster(Member member, Master master) throws Exception {
		if(member == null) throw new Exception("Mistrz musi być członkiem");
		Master m = new Master(member, master);
		member.setMaster(m);
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
	 * Assocation 
	 * Gets Member
	 * @return member
	 */
	 @OneToOne
	 @JoinColumn(name = "fk_member")
	public Member getMember() {
		return this.member;
	}
	 /**
	  * Sets Member
	  * @param member Member to become Master
	  * @throws Exception is member is null
	  */
	public void setMember(Member member) throws Exception {
		if(member == null) throw new Exception("Can't set member to null");
		  if(getMember() == null)
			this.member = member;
	}
	/**
	 * Gets grade
	 * @return grade
	 */
	@Basic
	public Integer getGrade() {
		return grade;
	}
/**
 * Sets grade
 * @param grade Integer of member grade
 */
	public void setGrade(Integer grade) {
		this.grade = grade;
	}
	/**
	 * Association 
	 * Gets Master
	 * @return master
	 */
	@ManyToOne
	public Master getMaster() {
		return master;
	}
/**
 * Sets Master
 * 
 * @param master Master of this Master
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
	 * @return followers
	 */
	@OneToMany(mappedBy = "master", cascade = CascadeType.ALL, orphanRemoval = true)
	public List<Master> getFollowers() {
		return followers;
	}
	/**
	 * Sets followers
	 * @param followers List of Masters following this master
	 */
	public void setFollowers(List<Master> followers) {
		this.followers = followers;
	}
	
	/**
	 * Add follower
	 * @param master add follower to this Master
	 */
	public void addFollower(Master master) {
		if(!this.followers.contains(master)) {
			this.followers.add(master);
			master.setMaster(this);
		}	
	}
	/**
	 * Remove follower
	 * @param master remove follower of this Master
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
	 * @return ledTeams
	 */
	@OneToMany(mappedBy = "master", cascade = CascadeType.ALL, orphanRemoval = true)
	public List<Team> getLedTeams() {
		return ledTeams;
	}
/**
 * Sets led teams
 * @param ledTeams List of teams this master leads
 */
	public void setLedTeams(List<Team> ledTeams) {
		this.ledTeams = ledTeams;
	}
	/**
	 * Add team
	 * @param team Team to be added to the list of led teams
	 */
	public void addTeam(Team team) {
		if(!this.getLedTeams().contains(team)) {
			this.ledTeams.add(team);
			team.setMaster(this);
		}
	}
/**
 * Remove team
 * @param team Team to be removed from the list of led teams
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
	 * @return ledSchool
	 */
	@OneToOne(mappedBy="leader")
	public School getLedSchool() {
		return ledSchool;
	}
/**
 * Sets led school
 * @param ledSchool School that is Led by this master
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
	 * @return decors
	 */
	@OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
	public List<EquipmentDecorative> getDecors() {
		return decors;
	}
/**
 * Sets decors
 * @param decors List of decors protected by this master
 */
	public void setDecors(List<EquipmentDecorative> decors) {
		this.decors = decors;
	}
	/**
	 * Add decor
	 * @param decor Adds to the list of decors protected by this master
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
