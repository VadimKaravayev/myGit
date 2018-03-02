package ua.karavayev.beans;

public enum Role {
	ADMIN("Admin"),
	DISPATCHER("Dispatcher"),
	DRIVER("Driver");
	
	private String roleName;
	
	Role(String roleName) {
		this.roleName = roleName;
	}
	
	public String getRoleName() {
		return roleName;
	}
}
