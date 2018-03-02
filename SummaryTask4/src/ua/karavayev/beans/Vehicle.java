package ua.karavayev.beans;

public class Vehicle {
	private int id;
	private String model;
	private int tonnage;
	private double capacity;
	private VehicleType type;
	private Boolean serviceable;
	private Boolean available;
	
	public Vehicle() {
		super();
	}

	public Vehicle(int id, String model, int tonnage, double capacity, VehicleType type, Boolean serviceable,
			Boolean available) {
		super();
		this.id = id;
		this.model = model;
		this.tonnage = tonnage;
		this.capacity = capacity;
		this.type = type;
		this.serviceable = serviceable;
		this.available = available;
	}
	
	public Vehicle(String model, int tonnage, double capacity, VehicleType type, Boolean serviceable,
			Boolean available) {
		super();
		this.model = model;
		this.tonnage = tonnage;
		this.capacity = capacity;
		this.type = type;
		this.serviceable = serviceable;
		this.available = available;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getTonnage() {
		return tonnage;
	}

	public void setTonnage(int tonnage) {
		this.tonnage = tonnage;
	}

	public double getCapacity() {
		return capacity;
	}

	public void setCapacity(double capacity) {
		this.capacity = capacity;
	}

	public VehicleType getType() {
		return type;
	}

	public void setType(VehicleType type) {
		this.type = type;
	}

	public Boolean getServiceable() {
		return serviceable;
	}

	public void setServiceable(Boolean serviceable) {
		this.serviceable = serviceable;
	}

	public Boolean getAvailable() {
		return available;
	}

	public void setAvailable(Boolean available) {
		this.available = available;
	}
	
	
	
	
}
