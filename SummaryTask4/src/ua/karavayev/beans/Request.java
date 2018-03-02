package ua.karavayev.beans;

public class Request {
	private int id;
	private Place fromPlace;
	private Place toPlace;
	private int vehicleTonnage;
	private double vehicleCapacity;
	private Person driver;
	RequestStatus requestStatus;
	RouteNote routeNote;
	
	public Request() {}

	public Request(int id, Place fromPlace, Place toPlace, int vehicleTonnage, double vehicleCapacity, Person driver,
			RequestStatus requestStatus, RouteNote routeNote) {
		super();
		this.id = id;
		this.fromPlace = fromPlace;
		this.toPlace = toPlace;
		this.vehicleTonnage = vehicleTonnage;
		this.vehicleCapacity = vehicleCapacity;
		this.driver = driver;
		this.requestStatus = requestStatus;
		this.routeNote = routeNote;
	}
	public Request(Place fromPlace, Place toPlace, int vehicleTonnage, double vehicleCapacity, Person driver,
			RequestStatus requestStatus, RouteNote routeNote) {
		super();
		this.fromPlace = fromPlace;
		this.toPlace = toPlace;
		this.vehicleTonnage = vehicleTonnage;
		this.vehicleCapacity = vehicleCapacity;
		this.driver = driver;
		this.requestStatus = requestStatus;
		this.routeNote = routeNote;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Place getFromPlace() {
		return fromPlace;
	}

	public void setFromPlace(Place fromPlace) {
		this.fromPlace = fromPlace;
	}

	public Place getToPlace() {
		return toPlace;
	}

	public void setToPlace(Place toPlace) {
		this.toPlace = toPlace;
	}

	public int getVehicleTonnage() {
		return vehicleTonnage;
	}

	public void setVehicleTonnage(int vehicleTonnage) {
		this.vehicleTonnage = vehicleTonnage;
	}

	public double getVehicleCapacity() {
		return vehicleCapacity;
	}

	public void setVehicleCapacity(double vehicleCapacity) {
		this.vehicleCapacity = vehicleCapacity;
	}

	public Person getDriver() {
		return driver;
	}

	public void setDriver(Person driver) {
		this.driver = driver;
	}

	public RequestStatus getRequestStatus() {
		return requestStatus;
	}

	public void setRequestStatus(RequestStatus requestStatus) {
		this.requestStatus = requestStatus;
	}

	public RouteNote getRouteNote() {
		return routeNote;
	}

	public void setRouteNote(RouteNote routeNote) {
		this.routeNote = routeNote;
	}
	
	
	
	
}
