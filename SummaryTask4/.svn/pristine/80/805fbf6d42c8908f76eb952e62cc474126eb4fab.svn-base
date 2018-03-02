package ua.karavayev.databasemanager;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import ua.karavayev.beans.Person;
import ua.karavayev.beans.Place;
import ua.karavayev.beans.Request;
import ua.karavayev.beans.RequestStatus;
import ua.karavayev.beans.Role;
import ua.karavayev.beans.Route;
import ua.karavayev.beans.RouteNote;
import ua.karavayev.beans.RouteStatus;
import ua.karavayev.beans.Vehicle;
import ua.karavayev.beans.VehicleType;
import ua.karavayev.myExceptions.NotInDataBaseException;

public class DBmanager {

	private DataSource dataSource;

	private static final String SQL_GET_USER_BY_EMAIL_AND_PASSWORD = "SELECT * FROM people WHERE email=? AND password=?";

	private static final String SQL_GET_ROLE_BY_ID = "SELECT * FROM role WHERE id=?";

	private static final String SQL_GET_ALL_REQUESTS = "SELECT * FROM request";

	private static final String SQL_GET_PLACE_BY_ID = "SELECT * FROM place WHERE id=?";

	private static final String SQL_GET_PERSON_BY_ID = "SELECT * FROM people WHERE id=?";

	private static final String SQL_GET_ALL_PLACES = "SELECT * FROM place";

	private static final String SQL_GET_REQUEST_BY_ID = "SELECT * FROM request WHERE id=?";

	private static final String SQL_ADD_REQUEST = "INSERT INTO request VALUES(null, ?, ?, ?, ?, ?, ?, ?)";

	private static final String SQL_UPDATE_REQUEST = "UPDATE request SET from_place_id=?, "
			+ "to_place_id=?, vehicle_tonnage=?, vehicle_capacity=? WHERE id=?";

	private static final String SQL_GET_ALL_ROUTES = "SELECT * FROM route";

	private static final String SQL_GET_VEHICLE_BY_ID = "SELECT * FROM vehicle WHERE id=?";

	private static final String SQL_GET_VEHICLETYPE_BY_ID = "SELECT * FROM vehicle_type WHERE id=?";

	private static final String SQL_GET_ROUTE_BY_ID = "SELECT * FROM route WHERE id=?";

	private static final String SQL_GET_ALL_VEHICLES = "SELECT * FROM vehicle";

	private static final String SQL_GET_AVAILABLE_VEHICLES = "SELECT * FROM vehicle WHERE tonnage >=? "
			+ "AND capacity >=? AND is_serviceable = true AND is_available = true";

	private static final String SQL_ADD_ROUTE = "INSERT INTO route VALUES(null, ?, ?, ?, ?, ?, ?, ?)";

	private static final String SQL_UPDATE_ROUTE = "UPDATE route SET assigned_vehicle_id=?, status=? WHERE id=?";

	private static final String SQL_UPDATE_REQUEST_STATUS = "UPDATE request SET request_status=? WHERE id=?";

	private static final String SQL_UPDATE_VEHICLE_AVAILABLE = "UPDATE vehicle SET is_available=? WHERE id=?";

	private static final String SQL_GET_ROUTE_BY_REQUEST_ID = "SELECT * FROM route WHERE request_id = ?";

	private static final String SQL_UPDATE_VEHICLE_AVAILABLE_SERVICEABLE = "UPDATE vehicle SET is_available=?, is_serviceable=? WHERE id=?";

	private static final String SQL_UPDATE_REQUEST_ROUTENOTE = "UPDATE request SET route_note=? WHERE id=?";

	private static final String SQL_UPDATE_ROUTE_ROUTESTATUS = "UPDATE route SET status=? WHERE id=?";

	private static final String SQL_GET_ALL_PEOPLE = "SELECT * FROM people";

	private static final String SQL_ADD_PERSON = "INSERT INTO people VALUES(null, ?, ?, ?, ?, ?)";
	
	private static final String SQL_GET_ROLE_BY_NAME = "SELECT * FROM role WHERE role_name=?";
	
	private static final String SQL_UPDATE_PERSON = "UPDATE people SET first_name=?, last_name=?, email=?, password=?, role_id=? WHERE id=?";
	
	private static final String SQL_DELETE_PERSON_BY_ID = "DELETE FROM people WHERE id=?";
	
	private static final String SQL_ADD_PLACE = "INSERT INTO place VALUES(null, ?, ?)";	
	
	private static final String SQL_UPDATE_PLACE = "UPDATE place SET country=?, city=? WHERE id=?";

	private static final String SQL_DELETE_PLACE_BY_ID = "DELETE FROM place WHERE id=?";

	private static final String SQL_ADD_VEHICLE = "INSERT INTO vehicle VALUES(null, ?, ?, ?, ?, ?, ?)";
	
	private static final String SQL_GET_VEHICLE_TYPE_ID_BY_NAME = "SELECT id FROM vehicle_type WHERE type_name=?";
	
	private static final String SQL_UPDATE_VEHICLE = 
			"update vehicle set model=?, tonnage=?, capacity=?, "
			+ "vehicle_type_id=?, is_serviceable=?, is_available=? where id=?";
	
	private static final String SQL_DELETE_VEHICLE_BY_ID = "DELETE FROM vehicle WHERE id=?";
	

	public DBmanager(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public List<Place> getAllPlaces() throws SQLException, NotInDataBaseException {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		List<Place> placeList = null;

		try {
			connection = dataSource.getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(SQL_GET_ALL_PLACES);
			placeList = new ArrayList<>();

			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				Place place = getPlaceById(id);
				placeList.add(place);
			}
			return placeList;

		} finally {
			close(resultSet, statement, connection);
		}
	}

	public Place getPlaceById(int id) throws SQLException, NotInDataBaseException {
		Connection theConnection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Place place = null;

		try {
			theConnection = dataSource.getConnection();
			preparedStatement = theConnection.prepareStatement(SQL_GET_PLACE_BY_ID);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				int theId = resultSet.getInt("id");
				String theCountry = resultSet.getString("country");
				String theCity = resultSet.getString("city");
				place = new Place(theId, theCountry, theCity);
			} else {
				throw new NotInDataBaseException("Could not find place id: " + id);
			}
			return place;
		} finally {
			close(resultSet, preparedStatement, theConnection);
		}
	}

	public List<Request> getAllRequests() throws SQLException, NotInDataBaseException {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		List<Request> requestList = null;

		try {
			connection = dataSource.getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(SQL_GET_ALL_REQUESTS);
			requestList = new ArrayList<>();

			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				Request request = getRequestById(id);
				requestList.add(request);
			}
			return requestList;
		} finally {
			close(resultSet, statement, connection);
		}
	}

	public Request getRequestById(int requestID) throws NotInDataBaseException, SQLException {
		Connection theConnection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Request routeRequest = null;

		try {
			theConnection = dataSource.getConnection();
			preparedStatement = theConnection.prepareStatement(SQL_GET_REQUEST_BY_ID);
			preparedStatement.setInt(1, requestID);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				int id = resultSet.getInt("id");
				Place fromPlace = getPlaceById(resultSet.getInt("from_place_id"));
				Place toPlace = getPlaceById(resultSet.getInt("to_place_id"));
				int vehicleTonnage = resultSet.getInt("vehicle_tonnage");
				double vehicleCapacity = resultSet.getDouble("vehicle_capacity");
				Person driver = getPersonById(resultSet.getInt("driver_people_id"));
				RequestStatus requestStatus = RequestStatus
						.valueOf(resultSet.getString("request_status").toUpperCase());
				RouteNote routeNote = RouteNote.valueOf(resultSet.getString("route_note").toUpperCase());
				routeRequest = new Request(id, fromPlace, toPlace, vehicleTonnage, vehicleCapacity, driver,
						requestStatus, routeNote);
			} else {
				throw new NotInDataBaseException("Could not find request id: " + requestID);
			}
			return routeRequest;
		} finally {
			close(resultSet, preparedStatement, theConnection);
		}
	}

	public void addRequest(int fromPlaceId, int toPlaceId, int vehicleTonnage, double vehicleCapacity, int driverId,
			RequestStatus requestStatus, RouteNote routeNote) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(SQL_ADD_REQUEST);
			preparedStatement.setInt(1, fromPlaceId);
			preparedStatement.setInt(2, toPlaceId);
			preparedStatement.setInt(3, vehicleTonnage);
			preparedStatement.setDouble(4, vehicleCapacity);
			preparedStatement.setInt(5, driverId);
			preparedStatement.setString(6, requestStatus.toString());
			preparedStatement.setString(7, routeNote.toString());

			preparedStatement.execute();

		} finally {
			close(null, preparedStatement, connection);
		}

	}

	public void updateRequest(int requestId, int fromPlaceId, int toPlaceId, int vehicleTonnage, double vehicleCapacity)
			throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(SQL_UPDATE_REQUEST);
			preparedStatement.setInt(1, fromPlaceId);
			preparedStatement.setInt(2, toPlaceId);
			preparedStatement.setInt(3, vehicleTonnage);
			preparedStatement.setDouble(4, vehicleCapacity);
			preparedStatement.setInt(5, requestId);

			preparedStatement.execute();

		} finally {
			close(null, preparedStatement, connection);
		}

	}

	public List<Route> getAllRoutes() throws SQLException, NotInDataBaseException {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		List<Route> routesList = null;

		try {
			// initialize variables
			connection = dataSource.getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(SQL_GET_ALL_ROUTES);
			routesList = new ArrayList<>();

			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				Route route = getRouteById(id);
				routesList.add(route);
			}
			return routesList;
		} finally {
			close(resultSet, statement, connection);
		}
	}

	public Route getRouteById(int routeId) throws SQLException, NotInDataBaseException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Route route = null;

		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(SQL_GET_ROUTE_BY_ID);
			preparedStatement.setInt(1, routeId);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {

				int id = resultSet.getInt("id");
				Date creationDate = resultSet.getDate("creation_date");
				Place fromPlace = getPlaceById(resultSet.getInt("from_place_id"));
				Place toPlace = getPlaceById(resultSet.getInt("to_place_id"));
				RouteStatus routeStatus = RouteStatus.valueOf(resultSet.getString("status").toUpperCase());
				Vehicle vehicle = null;
				Person driver = null;
				Request request = null;

				try {
					vehicle = getVehicleById(resultSet.getInt("assigned_vehicle_id"));
				} catch (SQLException | NotInDataBaseException e) {
					vehicle = null;
					// driver = null;
					// request = null;
				}
				driver = getPersonById(resultSet.getInt("people_id"));
				request = getRequestById(resultSet.getInt("request_id"));
				route = new Route(id, creationDate, fromPlace, toPlace, routeStatus, vehicle, driver, request);
			} else {
				throw new NotInDataBaseException("Could not find route id " + routeId);
			}
			return route;
		} finally {
			close(resultSet, preparedStatement, connection);
		}
	}

	public List<Vehicle> getAllVehicles() throws SQLException, NotInDataBaseException {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		List<Vehicle> vehicleList = null;

		try {
			connection = dataSource.getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(SQL_GET_ALL_VEHICLES);
			vehicleList = new ArrayList<>();

			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				Vehicle theVehicle = getVehicleById(id);
				vehicleList.add(theVehicle);
			}
			return vehicleList;
		} finally {
			close(resultSet, statement, connection);
		}

	}

	public List<Vehicle> getAvailableVehicles(int requiredTonnage, double requiredCapacity)
			throws SQLException, NotInDataBaseException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Vehicle> sortedVehicles = null;

		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(SQL_GET_AVAILABLE_VEHICLES);
			preparedStatement.setInt(1, requiredTonnage);
			preparedStatement.setDouble(2, requiredCapacity);
			resultSet = preparedStatement.executeQuery();
			sortedVehicles = new ArrayList<>();

			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				Vehicle vehicle = getVehicleById(id);
				sortedVehicles.add(vehicle);
			}
			return sortedVehicles;
		} finally {
			close(resultSet, preparedStatement, connection);
		}
	}

	public Vehicle getVehicleById(int id) throws SQLException, NotInDataBaseException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Vehicle vehicle = null;

		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(SQL_GET_VEHICLE_BY_ID);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {

				int theId = resultSet.getInt("id");
				String model = resultSet.getString("model");
				int tonnage = resultSet.getInt("tonnage");
				double capacity = resultSet.getDouble("capacity");
				VehicleType type = getVehicleTypeById(resultSet.getInt("vehicle_type_id"));
				Boolean isServiceable = resultSet.getBoolean("is_serviceable");
				Boolean isAvailable = resultSet.getBoolean("is_available");
				vehicle = new Vehicle(theId, model, tonnage, capacity, type, isServiceable, isAvailable);
			} else {
				throw new NotInDataBaseException("Could not find vehicle id: " + id);
			}

			return vehicle;
		} finally {
			close(resultSet, preparedStatement, connection);
		}
	}

	public VehicleType getVehicleTypeById(int id) throws SQLException, NotInDataBaseException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		VehicleType type = null;

		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(SQL_GET_VEHICLETYPE_BY_ID);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				type = VehicleType.valueOf(resultSet.getString("type_name").toUpperCase());
			} else {
				throw new NotInDataBaseException("Could not find vehicle type id " + id);
			}
			return type;
		} finally {
			close(resultSet, preparedStatement, connection);
		}
	}

	public Role getRoleById(int id) throws NotInDataBaseException, SQLException {
		Connection theConnection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Role role = null;

		try {
			theConnection = dataSource.getConnection();
			preparedStatement = theConnection.prepareStatement(SQL_GET_ROLE_BY_ID);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				role = Role.valueOf(resultSet.getString("role_name").toUpperCase());
			} else {
				throw new NotInDataBaseException("Could not find role id: " + id);
			}
			return role;
		} finally {
			close(resultSet, preparedStatement, theConnection);
		}

	}

	public int getRoleIdByName(String name) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int id = -1;
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(SQL_GET_ROLE_BY_NAME);
			preparedStatement.setString(1, name);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				id = resultSet.getInt("id");
			}
			return id;
		} finally {
			close(resultSet, preparedStatement, connection);
		}

		
		
	}

	public List<Person> getAllPeople() throws SQLException, NotInDataBaseException {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		List<Person> list = null;

		try {
			connection = dataSource.getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(SQL_GET_ALL_PEOPLE);
			list = new ArrayList<>();
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				Person person = getPersonById(id);
				list.add(person);
			}
			return list;
		} finally {
			close(resultSet, statement, connection);
		}

	}

	public Person getPersonByLogin(String email, String password) throws SQLException, NotInDataBaseException {
	
		Connection theConnection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Person thePerson = null;
	
		try {
			theConnection = dataSource.getConnection();
			preparedStatement = theConnection.prepareStatement(SQL_GET_USER_BY_EMAIL_AND_PASSWORD);
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, password);
			resultSet = preparedStatement.executeQuery();
	
			if (resultSet.next()) {
				int id = resultSet.getInt("id");
				String firstName = resultSet.getString("first_name");
				String lastName = resultSet.getString("last_name");
				;
				String theEmail = resultSet.getString("email");
				;
				String thePassword = resultSet.getString("password");
				Role role = getRoleById(resultSet.getInt("role_id"));
				thePerson = new Person(id, firstName, lastName, theEmail, thePassword, role);
			} else {
				throw new NotInDataBaseException("Could not find person in database");
			}
			return thePerson;
		} finally {
			close(resultSet, preparedStatement, theConnection);
		}
	}

	public Person getPersonById(int id) throws SQLException, NotInDataBaseException {
		Connection theConnection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Person person = null;

		try {
			theConnection = dataSource.getConnection();
			preparedStatement = theConnection.prepareStatement(SQL_GET_PERSON_BY_ID);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				int theId = resultSet.getInt("id");
				String firstName = resultSet.getString("first_name");
				String lastName = resultSet.getString("last_name");
				;
				String email = resultSet.getString("email");
				;
				String password = resultSet.getString("password");
				;
				Role role = getRoleById(resultSet.getInt("role_id"));
				person = new Person(theId, firstName, lastName, email, password, role);
			} else {
				throw new NotInDataBaseException("Could not find person id: " + id);
			}
			return person;
		} finally {
			close(resultSet, preparedStatement, theConnection);
		}
	}

	public void addPerson(Person person) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(SQL_ADD_PERSON);
			preparedStatement.setString(1, person.getFirstName());
			preparedStatement.setString(2, person.getLastName());
			preparedStatement.setString(3, person.getEmail());
			preparedStatement.setString(4, person.getPassword());
			preparedStatement.setInt(5, getRoleIdByName(person.getRole().getRoleName().toLowerCase()));
			preparedStatement.execute();
		} finally {
			close(null, preparedStatement, connection);
		}

	}

	public void updatePerson(Person person) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(SQL_UPDATE_PERSON);
			preparedStatement.setString(1, person.getFirstName());
			preparedStatement.setString(2, person.getLastName());
			preparedStatement.setString(3, person.getEmail());
			preparedStatement.setString(4, person.getPassword());
			preparedStatement.setInt(5, getRoleIdByName(person.getRole().getRoleName().toLowerCase()));
			preparedStatement.setInt(6, person.getId());
			preparedStatement.execute();
		} finally {
			close(resultSet, preparedStatement, connection);
		}
		
	}
	public void deletePersonById(int personId) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(SQL_DELETE_PERSON_BY_ID);
			preparedStatement.setInt(1, personId);
			preparedStatement.execute();
		} finally {
			close(null, preparedStatement, connection);
		}
		
	}

	public void addRoute(Route route) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(SQL_ADD_ROUTE);
			preparedStatement.setDate(1, java.sql.Date.valueOf(java.time.LocalDate.now()));
			preparedStatement.setInt(2, route.getFromPlace().getId());
			preparedStatement.setInt(3, route.getToPlace().getId());
			preparedStatement.setString(4, route.getRouteStatus().toString());

			try {
				preparedStatement.setInt(5, route.getVehicle().getId());
			} catch (NullPointerException e) {
				preparedStatement.setNull(5, java.sql.Types.INTEGER);
			}

			preparedStatement.setInt(6, route.getDriver().getId());
			preparedStatement.setInt(7, route.getRequest().getId());
			preparedStatement.execute();

		} finally {
			close(resultSet, preparedStatement, connection);
		}

	}

	public void updateRoute(int routeId, int newVehicleId, RouteStatus routeStatus) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(SQL_UPDATE_ROUTE);
			preparedStatement.setInt(1, newVehicleId);
			preparedStatement.setString(2, routeStatus.toString());
			preparedStatement.setInt(3, routeId);
			preparedStatement.execute();
		} finally {
			close(null, preparedStatement, connection);
		}

	}

	public void updateRoute(int id, RouteStatus routeStatus) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(SQL_UPDATE_ROUTE_ROUTESTATUS);
			preparedStatement.setString(1, routeStatus.toString());
			preparedStatement.setInt(2, id);
			preparedStatement.execute();
		} finally {
			close(null, preparedStatement, connection);
		}

	}

	public void updateRequest(int id, RequestStatus status, RouteNote note) throws SQLException {
		updateRequest(id, status);
		updateRequest(id, note);
	}

	public void updateRequest(int requestId, RequestStatus requestStatus) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(SQL_UPDATE_REQUEST_STATUS);
			preparedStatement.setString(1, requestStatus.toString());
			preparedStatement.setInt(2, requestId);
			preparedStatement.execute();
		} finally {
			close(null, preparedStatement, connection);
		}

	}

	public void updateRequest(int requestId, RouteNote routeNote) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(SQL_UPDATE_REQUEST_ROUTENOTE);
			preparedStatement.setString(1, routeNote.toString());
			preparedStatement.setInt(2, requestId);
			preparedStatement.execute();
		} finally {
			close(null, preparedStatement, connection);
		}

	}

	public void updateVehicle(int vehicleId, boolean isAvailable) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(SQL_UPDATE_VEHICLE_AVAILABLE);
			preparedStatement.setBoolean(1, isAvailable);
			preparedStatement.setInt(2, vehicleId);
			preparedStatement.execute();
		} finally {
			close(null, preparedStatement, connection);
		}

	}

	public void updateVehicle(int vehicleId, boolean isAvailable, boolean isServiceabile) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(SQL_UPDATE_VEHICLE_AVAILABLE_SERVICEABLE);
			preparedStatement.setBoolean(1, isAvailable);
			preparedStatement.setBoolean(2, isServiceabile);
			preparedStatement.setInt(3, vehicleId);
			preparedStatement.execute();
		} finally {
			close(null, preparedStatement, connection);
		}

	}

	public Route getRouteByRequestId(int requestId) throws SQLException, NotInDataBaseException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Route route = null;

		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(SQL_GET_ROUTE_BY_REQUEST_ID);
			preparedStatement.setInt(1, requestId);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				int id = resultSet.getInt("id");
				route = getRouteById(id);
			}
			return route;

		} finally {
			close(resultSet, preparedStatement, connection);
		}
	}

	public void addPlace(Place place) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(SQL_ADD_PLACE);
			preparedStatement.setString(1, place.getCountry());
			preparedStatement.setString(2, place.getCity());
			preparedStatement.execute();
		} finally {
			close(null, preparedStatement, connection);
		}
		
		
	}

	public void updatePlace(int placeId, String country, String city) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(SQL_UPDATE_PLACE);
			preparedStatement.setString(1, country);
			preparedStatement.setString(2, city);
			preparedStatement.setInt(3, placeId);
			preparedStatement.execute();
		} finally {
			close(null, preparedStatement, connection);
		}
		
		
	}

	public void deletePlaceById(int placeId) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(SQL_DELETE_PLACE_BY_ID);
			preparedStatement.setInt(1, placeId);
			preparedStatement.execute();
		} finally {
			close(null, preparedStatement, connection);
		}
		
	}

	public void addVehicle(Vehicle vehicle) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(SQL_ADD_VEHICLE);
			preparedStatement.setString(1, vehicle.getModel());
			preparedStatement.setInt(2, vehicle.getTonnage());
			preparedStatement.setDouble(3, vehicle.getCapacity());
			preparedStatement
				.setInt(4, getVehicleTypeIdByName(vehicle.getType().getName().toLowerCase()));
			preparedStatement.setBoolean(5, vehicle.getServiceable());
			preparedStatement.setBoolean(6, vehicle.getAvailable());
			preparedStatement.execute();
		} finally {
			close(null, preparedStatement, connection);
		}
		
	}

	public int getVehicleTypeIdByName(String name) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int id = -1;
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(SQL_GET_VEHICLE_TYPE_ID_BY_NAME);
			preparedStatement.setString(1, name);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				id = resultSet.getInt("id");
			}
			return id;
		} finally {
			close(resultSet, preparedStatement, connection);
		}
		
		
	}

	public void updateVehicle(Vehicle vehicle) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(SQL_UPDATE_VEHICLE);
			preparedStatement.setString(1, vehicle.getModel());
			preparedStatement.setInt(2, vehicle.getTonnage());
			preparedStatement.setDouble(3, vehicle.getCapacity());
			preparedStatement.setInt(
					4, getVehicleTypeIdByName(vehicle.getType().getName().toLowerCase()));
			preparedStatement.setBoolean(5, vehicle.getServiceable());
			preparedStatement.setBoolean(6, vehicle.getAvailable());
			preparedStatement.setInt(7, vehicle.getId());
			preparedStatement.execute();
			
		} finally {
			close(resultSet, preparedStatement, connection);
		}
		
	}

	public void deleteVehicleById(int vehicleId) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(SQL_DELETE_VEHICLE_BY_ID);
			preparedStatement.setInt(1, vehicleId);
			preparedStatement.execute();
		} finally {
			close(null, preparedStatement, connection);
		}
		
	}

	private void close(ResultSet resultSet, Statement statement, Connection connection) {
		try {
			if (resultSet != null) {
				resultSet.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (connection != null) {
				connection.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
