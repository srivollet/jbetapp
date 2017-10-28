package betapp.model;

public class Equipe {

	private Integer id;
	private String nom;
	private String nationalite;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getNationalite() {
		return nationalite;
	}

	public void setNationalite(String nationalite) {
		this.nationalite = nationalite;
	}

	@Override
	public String toString() {
		return "Equipe [id=" + id + ", nom=" + nom + ", nationalite=" + nationalite + "]";
	}

}
