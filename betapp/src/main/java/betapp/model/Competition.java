package betapp.model;

public class Competition {

	private Integer id;
	private TypeCompetition type;
	private String name;
	
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
		
}
