package gtt.model;

public class TableCondition {
	
	// ATTRIBUTES :
	
	protected Integer id;
	protected String query;
	
	// CONSTRUCTS :
	
	public TableCondition() {}
	
	public TableCondition(Integer id) throws ModelException {
		
		this.setId(id);
		
	}
	
	public TableCondition(String query) {
		
		this.setQuery(query);
		
	}
	
	//  GETTERS AND SETTERS :
	
	public Integer getId() { return id; }

	public void setId(Integer id) throws ModelException {
		if(id > 0)
			this.id = id;
		else
			throw new ModelException("Invalid value on ID : " + id + " !");
	}

	public String getQuery() { return query; }

	public void setQuery(String query) { this.query = query; }

}
