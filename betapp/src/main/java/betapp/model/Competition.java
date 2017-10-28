package betapp.model;

public class Competition {

	private Integer id;
	private TypeCompetition type;
	private String name;
	private String country;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public TypeCompetition getType() {
		return type;
	}

	public void setType(TypeCompetition type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return "Competition [id=" + id + ", type=" + type + ", name=" + name + ", country=" + country + "]";
	}

}
