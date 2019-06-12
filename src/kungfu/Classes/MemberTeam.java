package kungfu.Classes;

import java.time.LocalDate;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

@Entity(name="MemberTeam")
public class MemberTeam {

	private long id;
	private Team team;
	private Member member;
	private LocalDate joinDate;
	private LocalDate leaveDate = null;
	public MemberTeam() {}
	public MemberTeam(Member p, Team g) {
		this.member = p;
		this.team = g;
		this.joinDate = LocalDate.now();
		this.member.addMemberTeam(this);
		this.team.addMemberTeam(this);
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
	@ManyToOne
	public Team getTeam() {
		return this.team;
	}
	public void setTeam(Team Team) {
		this.team = Team;
		
		
	}
	@ManyToOne
	public Member getMember() {
		return this.member;
	}
	public void setMember(Member p) {
		this.member = p;
		
	}
	
	@Basic 
	public LocalDate getJoinDate() {
		return this.joinDate;
	}
	public void setJoinDate(LocalDate date) {
		this.joinDate = date;
	}
	@Override
	public String toString() {
		return getMember().getPerson().getFullName()+" do grupy "+getTeam().getTeamNr()+" dołączyła: "+getJoinDate();
	}
	@Basic
	public LocalDate getLeaveDate() {
		return leaveDate;
	}
	public void setLeaveDate(LocalDate leaveDate) {
		this.leaveDate = leaveDate;
	}
	
}
