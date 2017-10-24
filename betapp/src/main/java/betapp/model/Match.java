package betapp.model;

import java.util.Date;

public class Match {

	private Integer id;
	private Date date;
	private Competition competition;
	private Equipe equipe1;
	private Equipe equipe2;
	private Integer score1;
	private Integer score2;
	private Float cote1;
	private Float cote2;
	private Float coteN;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Competition getCompetition() {
		return competition;
	}
	public void setCompetition(Competition competition) {
		this.competition = competition;
	}
	public Equipe getEquipe1() {
		return equipe1;
	}
	public void setEquipe1(Equipe equipe1) {
		this.equipe1 = equipe1;
	}
	public Equipe getEquipe2() {
		return equipe2;
	}
	public void setEquipe2(Equipe equipe2) {
		this.equipe2 = equipe2;
	}
	public Integer getScore1() {
		return score1;
	}
	public void setScore1(Integer score1) {
		this.score1 = score1;
	}
	public Integer getScore2() {
		return score2;
	}
	public void setScore2(Integer score2) {
		this.score2 = score2;
	}
	public Float getCote1() {
		return cote1;
	}
	public void setCote1(Float cote1) {
		this.cote1 = cote1;
	}
	public Float getCote2() {
		return cote2;
	}
	public void setCote2(Float cote2) {
		this.cote2 = cote2;
	}
	public Float getCoteN() {
		return coteN;
	}
	public void setCoteN(Float coteN) {
		this.coteN = coteN;
	}
}