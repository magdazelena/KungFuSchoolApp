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
	//ubermaster:
	public Master(Member member) throws Exception {
		if(member == null) throw new Exception ("Member must exist to become master");
		if(member.checkIfMinor()) throw new Exception("Cannot create master under age 18");
		setMember(member);
		member.setMaster(this);
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
	 @OneToOne
	 @JoinColumn(name = "fk_member")
	public Member getMember() {
		return this.member;
	}

	public void setMember(Member member) {
		this.member = member;
	}
	@Basic
	public Integer getGrade() {
		return grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}
	@ManyToOne
	public Master getMaster() {
		return master;
	}

	public void setMaster(Master master) {
		if(this.master == null) {
			if(master != null) {
				this.master = master;
				master.addFollower(this);
			}
		}
	}
	@OneToMany(mappedBy = "master", cascade = CascadeType.ALL, orphanRemoval = true)
	public List<Master> getFollowers() {
		return followers;
	}
	
	public void setFollowers(List<Master> followers) {
		this.followers = followers;
	}
	
	public void addFollower(Master master) {
		if(!this.followers.contains(master)) {
			this.followers.add(master);
			master.setMaster(this);
		}	
	}
	public void removeFollower(Master master) {
		if(this.followers.contains(master)) {
			this.followers.remove(master);
			master.setMaster(null);
			master = null;
		}	
	}
	
	@OneToMany(mappedBy = "master", cascade = CascadeType.ALL, orphanRemoval = true)
	public List<Team> getLedTeams() {
		return ledTeams;
	}

	public void setLedTeams(List<Team> ledTeams) {
		this.ledTeams = ledTeams;
	}
	public void addTeam(Team team) {
		if(!this.getLedTeams().contains(team)) {
			this.ledTeams.add(team);
			team.setMaster(this);
		}
	}

	public void removeTeam(Team team) {
		if(this.getLedTeams().contains(team)) {
			this.ledTeams.remove(team);
			team.setMaster(null);
		}
	}
	@OneToOne(mappedBy="leader")
	public School getLedSchool() {
		return ledSchool;
	}

	public void setLedSchool(School ledSchool) {
		if(this.ledSchool == null) {
			if(ledSchool != null) {
				this.ledSchool = ledSchool;
				ledSchool.setLeader(this);
			}
		}
	}
	@OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
	public List<EquipmentDecorative> getDecors() {
		return decors;
	}

	public void setDecors(List<EquipmentDecorative> decors) {
		this.decors = decors;
	}
	public void addDecor(EquipmentDecorative decor) {
		if(!getDecors().contains(decor)) {
			this.decors.add(decor);
			decor.setOwner(this);
		}
	}
	@Transient
	public Boolean isAdministator() {
		return this.getLedSchool() != null ? true: false;
	}

}
