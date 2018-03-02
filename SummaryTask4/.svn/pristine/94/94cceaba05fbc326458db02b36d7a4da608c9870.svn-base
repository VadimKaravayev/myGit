package ua.karavayev.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.EnumSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
import ua.karavayev.databasemanager.DBmanager;
import ua.karavayev.myExceptions.NotInDataBaseException;
import ua.karavayev.utils.Command;
/**
 * Servlet implementation class Controller
 */
@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DBmanager dbManager;

	@Resource(name = "jdbc/truckingdepot")
	private DataSource dataSource;

	@Override
	public void init() throws ServletException {
		dbManager = new DBmanager(dataSource);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Command theCommand = Command.valueOf(request.getParameter("command").toUpperCase());
		switch (theCommand) {
		case NEW_REQUEST_CLICKED:
			request.getRequestDispatcher("/app/new-request-form.jsp").forward(request, response);
			break;
			
		case LOGOUT:
			doLogout(request, response);
			break;
			
		case COMPLETE_ROUTE:
			sendToCompleteRoutePage(request, response);
			break;
			
		case LOAD_REQUEST:
			loadRequest(request, response);
			break;
		case VIEW_ROUTE:
			viewRoute(request, response);
			break;
			
		case VIEW_ALL_REQUESTS:
			listRequestsForDispatcher(request, response);
			break;
			
		case CREATE_ROUTE_CLICKED:
			sendToNewRoutePage(request, response);
			break;
		
		case LIST_VEHICLES:
			listVehicles(request, response);
			break;
			
		case SORT_OUT_VEHICLES:
			sortOutVehicles(request, response);
			break;
		
		case ASSIGN_VEHICLE:
			assignVehicle(request, response);
			break;
		
		case VIEW_ALL_ROUTES:
			listRoutes(request, response);
			break;
			
		case LOAD_DRIVER_REQUESTS:
			listRequests(request, response);
			break;
			
		case LOAD_ROUTES:
			listRoutes(request, response);
			break;
			
		case LOAD_PEOPLE:
			listPeople(request, response);
			break;
			
		case LOAD_PERSON: 
			loadPerson(request, response);
			break;
		
		case DELETE_PERSON:
			deletePerson(request, response);
			break;
			
		case LOAD_PLACES:
			loadPlaces(request, response);
			break;
		
		case LOAD_THE_PLACE:
			loadThePlace(request, response);
			break;
		
		case DELETE_THE_PLACE:
			deleteThePlace(request, response);
			break;
			
		case LOAD_VEHICLES:
			loadVehicles(request, response);
			break;
			
		case LOAD_THE_VEHICLE:
			loadTheVehicle(request, response);
			break;
			
		case DELETE_VEHICLE:
			deleteVehicle(request, response);
			break;
		
		case SORT_ROUTES:
			sortRoutes(request, response);
			break;
			
		case DO_THE_TASK:
			doTheTask(request, response);
			break;
			
		default:
			response.getWriter().println("Get method here. I don't know this command " + theCommand);
			break;
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Command theCommand = Command.valueOf(request.getParameter("command").toUpperCase());

		switch (theCommand) {
			case DO_LOGIN:
				doLogin(request, response);
				break;
				
			case NEW_REQUEST_SAVE:
				createNewRequest(request, response);
				break;
				
			case UPDATE_REQUEST:
				updateRequest(request, response);
				break;
				
			case ROUTE_UPDATE_CLICKED:
				doRouteUpdate(request, response);
				break;
				
			case CREATE_ROUTE:
				createRoute(request, response);
				break;
			
			case UPDATE_ROUTE:
				updateRoute(request, response);
				break;
				
			case COMPLETE_ROUTE_CONFIRMED:
				completRoute(request, response);
				break;
				
			case ADD_NEW_PERSON:
				addPerson(request, response);
				break;
				
			case UPDATE_PERSON:
				updatePerson(request, response);
				break;
				
			case ADD_PLACE:
				addPlace(request, response);
				break;
			
			case UPDATE_PLACE:
				updatePlace(request, response);
				break;
			
			case ADD_VEHILCE:
				addVehicle(request, response);
				break;
				
			case UPDATE_VEHILCE:
				updateVehicle(request, response);
				break;
			
			default:
				response.getWriter().println("Post method here. I don't know this command " + theCommand);
				break;
		}
	}

	private void doTheTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			List<Route> listRoute = dbManager.getAllRoutes();
			List<Route> sortedLlist = new ArrayList<>();
			listRoute.forEach(r->{
				if (r.getRouteStatus() == RouteStatus.CLOSED && r.getRequest().getVehicleCapacity() >= 75) {
					sortedLlist.add(r);
				}
			});
			int count = sortedLlist.size();
			request.setAttribute("routeList", sortedLlist);
			request.setAttribute("count", count);
			request.getRequestDispatcher("/app/task-epam.jsp").forward(request, response);
		} catch (SQLException | NotInDataBaseException e) {
			
			e.printStackTrace();
		}
		
	}

	private void sortRoutes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//criterion=open
		
		try {
			String criterion = request.getParameter("criterion");
			List<Route> routeList = dbManager.getAllRoutes();
			List<Route> result = new LinkedList<>();
			
			if (criterion.equals("open")) {
				routeList.forEach(r ->{
					if (r.getRouteStatus() == RouteStatus.OPEN) {
						result.add(r);
					}
				});
				request.setAttribute("routesList", result);
			} else if (criterion.equals("underway")) {
				routeList.forEach(r ->{
					if (r.getRouteStatus() == RouteStatus.UNDERWAY) {
						result.add(r);
					}
				});
				request.setAttribute("routesList", result);
			} else if (criterion.equals("closed")) {
				routeList.forEach(r ->{
					if (r.getRouteStatus() == RouteStatus.CLOSED) {
						result.add(r);
					}
				});
				request.setAttribute("routesList", result);
			} else if (criterion.equals("canceled")) {
				routeList.forEach(r ->{
					if (r.getRouteStatus() == RouteStatus.CANCELED) {
						result.add(r);
					}
				});
				request.setAttribute("routesList", result);
			} else if (criterion.equals("date")) {
				
				Collections.sort(routeList, new Comparator<Route>(){

					@Override
					public int compare(Route o1, Route o2) {
						
						return o2.getCreationDate().compareTo(o1.getCreationDate());
					}
					
				});
				request.setAttribute("routesList", routeList);
				
			} else if(criterion.equals("id")) {
				routeList.sort((r1, r2)-> r1.getId() - r2.getId());
				request.setAttribute("routesList", routeList);
			}
			
			
			request.getRequestDispatcher("/app/dispatcherPage.jsp").forward(request, response);
			
		} catch (SQLException | NotInDataBaseException e) {
			e.printStackTrace();
		}
		
		
		
	}

	private void deleteVehicle(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		try {
			int vehicleId = Integer.parseInt(request.getParameter("vehicleId"));
			dbManager.deleteVehicleById(vehicleId);
			String app = request.getContextPath();
			String command = "command=" + Command.LOAD_VEHICLES;
			String message = "message=The vehicle was successfully deleted";
			response.sendRedirect(app + "/Controller?" + command + "&" + message);
		} catch (SQLException e) {
			String app = request.getContextPath();
			String command = "command=" + Command.LOAD_VEHICLES;
			String message = "message=Deletion failed";
			response.sendRedirect(app + "/Controller?" + command + "&" + message);
			e.printStackTrace();
		}
		
		
	}

	private void updateVehicle(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		try {
			int vehicleId = Integer.parseInt(request.getParameter("vehicleId"));
			String model = request.getParameter("model");
			int tonnage = Integer.parseInt(request.getParameter("tonnage"));
			double capacity = Double.parseDouble(request.getParameter("capacity"));
			VehicleType vehicleType = VehicleType.valueOf(request.getParameter("vehicleType")
					.toUpperCase());
			Boolean serviceable = request.getParameter("condition")
					.equals("In service") ? true : false;
			Boolean available = request.getParameter("available")
					.equals("yes") ? true : false;
			Vehicle vehicle = new Vehicle
					(vehicleId, model, tonnage, capacity, vehicleType, serviceable, available);
			dbManager.updateVehicle(vehicle);
			String app = request.getContextPath();
			String command = "command=" + Command.LOAD_VEHICLES;
			String message = "message=The vehicle was successfully updated";
			response.sendRedirect(app + "/Controller?" + command + "&" + message);
			
		} catch (SQLException e) {
			String app = request.getContextPath();
			String command = "command=" + Command.LOAD_VEHICLES;
			String message = "message=Error!";
			response.sendRedirect(app + "/Controller?" + command + "&" + message);
			e.printStackTrace();
		}
		
		
		
	}

	private void loadTheVehicle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int vehicleId = Integer.parseInt(request.getParameter("vehicleId"));
		try {
			Vehicle vehicle = dbManager.getVehicleById(vehicleId);
			request.setAttribute("vehicle", vehicle);
			request.getRequestDispatcher("app/update-vehicle-form.jsp").forward(request, response);
		} catch (SQLException | NotInDataBaseException e) {
			String message = "message=Database error";
			String app = request.getContextPath();
			String command = "command="+Command.LOAD_VEHICLES.toString();
			response.sendRedirect(app + "/Controller?" + command + "&" + message);
			e.printStackTrace();
		}
		
	}

	private void addVehicle(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String model = request.getParameter("model");
		int tonnage = Integer.parseInt(request.getParameter("tonnage"));
		double capacity = Double.parseDouble(request.getParameter("capacity"));
		VehicleType vehicleType = VehicleType.
				valueOf(request.getParameter("vehicleType").toUpperCase());
		Boolean condition = request.getParameter("condition").equals("In service") ? true : false;
		Boolean available = request.getParameter("available").equals("yes") ? true : false;
		Vehicle vehicle = new Vehicle(model, tonnage, capacity, vehicleType, condition, available);
		String message = "Vehicle was successfully added";
		try {
			dbManager.addVehicle(vehicle);
		} catch (SQLException e) {
			message = "Error! Adding failed";
			e.printStackTrace();
		}
		String command = Command.LOAD_VEHICLES.toString();
		String app = request.getContextPath();
		
		response.sendRedirect(app+ "/Controller?command="+command+"&message="+message);
		
		
		
	}

	private void loadVehicles(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			List<Vehicle> vehicleList = dbManager.getAllVehicles();
			Set<VehicleType> vehicleTypes = EnumSet.allOf(VehicleType.class);
			HttpSession session = request.getSession();
			String message = request.getParameter("message");
			request.setAttribute("message", message);
			session.setAttribute("vehicleTypes", vehicleTypes);
			request.setAttribute("vehicleList", vehicleList);
			request.getRequestDispatcher("app/admin-vehicle-list.jsp").
				forward(request, response);
		} catch (SQLException | NotInDataBaseException e) {
			e.printStackTrace();
		}
		
	}

	private void deleteThePlace(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int placeId = Integer.parseInt(request.getParameter("placeId"));
		String message = "The place was successfully deleted";
		try {
			dbManager.deletePlaceById(placeId);
		} catch (SQLException e) {
			message = "Deletion failed";
			e.printStackTrace();
		}
		String app = request.getContextPath();
		String command = Command.LOAD_PLACES.toString();
		
		response.sendRedirect(app + "/Controller?command="+command+"&message="+message);
		
	}

	private void updatePlace(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int placeId = Integer.parseInt(request.getParameter("placeId"));
		String country = request.getParameter("country");
		String city = request.getParameter("city");
		String message = "The place was successfully updated";
		try {
			dbManager.updatePlace(placeId, country, city);
		} catch (SQLException e) {
			message = "Update failed";
			e.printStackTrace();
		}
		String app = request.getContextPath();
		
		String command = Command.LOAD_PLACES.toString();
		response.sendRedirect(app+"/Controller?command="+command+"&message="+message);
	}

	private void loadThePlace(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int placeId = Integer.parseInt(request.getParameter("placeId"));
		try {
			Place place = dbManager.getPlaceById(placeId);
			request.setAttribute("place", place);
			request.getRequestDispatcher("/app/update-place-form.jsp").forward(request, response);
		} catch (SQLException | NotInDataBaseException e) {
			e.printStackTrace();
		}
		
	}

	private void addPlace(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String country = request.getParameter("coutry");
		String city = request.getParameter("city");
		Place place = new Place(country, city);
		try {
			dbManager.addPlace(place);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String app = request.getContextPath();
		String command = Command.LOAD_PLACES.toString();
		String message = "The place was successfully added";
		response.sendRedirect(app + "/Controller?command="+command+"&message="+message);
		
	}

	private void loadPlaces(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			List<Place> placeList = dbManager.getAllPlaces();
			String message = request.getParameter("message");
			request.setAttribute("placeList", placeList);
			request.setAttribute("message", message);
			request.getRequestDispatcher("/app/places-list.jsp").forward(request, response);
		} catch (SQLException | NotInDataBaseException e) {
			e.printStackTrace();
		}
	}

	private void deletePerson(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int personId = Integer.parseInt(request.getParameter("personId"));
		try {
			dbManager.deletePersonById(personId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String app = request.getContextPath();
		String command = Command.LOAD_PEOPLE.toString();
		String message = "Person was successfully deleted";
		response.sendRedirect(app+"/Controller?command="+command+"&message="+message);
		
	}

	private void updatePerson(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int personId = Integer.parseInt(request.getParameter("personId"));
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		Role role = Role.valueOf(request.getParameter("role").toUpperCase());
		Person person = new Person(personId, firstName, lastName, email, password, role);
		try {
			dbManager.updatePerson(person);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String message = "Person was successfully updated";
		String app = request.getContextPath();
		String command = Command.LOAD_PEOPLE.toString();
		response.sendRedirect(app + "/Controller?command="+command+"&message="+message);
		
		
		
	}

	private void loadPerson(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int personId = Integer.parseInt(request.getParameter("personId"));
		try {
			Person person = dbManager.getPersonById(personId);
			request.setAttribute("person", person);
			request.getRequestDispatcher("/app/update-person-form.jsp").forward(request, response);
		} catch (SQLException | NotInDataBaseException e) {
			e.printStackTrace();
		}
		
	}

	private void addPerson(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		Role role = Role.valueOf(request.getParameter("role").toUpperCase());
		Person person = new Person(firstName, lastName, email, password, role);
		String message = "Person was successfully added";
		try {
			dbManager.addPerson(person);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		//PRG must be used here
		response.sendRedirect("/SummaryTask4/Controller?command=LOAD_PEOPLE&message="+message);
		
	}

	private void doRouteUpdate(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String param = request.getParameter("button");
		int routeId = Integer.parseInt(request.getParameter("routeId"));
		Route route = null;
		try {
			route = dbManager.getRouteById(routeId);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NotInDataBaseException e) {
			e.printStackTrace();
		}
		request.setAttribute("route", route);
		if (param.equals("Update route")) {
			if (route.getRouteStatus() == RouteStatus.OPEN) {
				request.getRequestDispatcher("/app/update-route-page.jsp").forward(request, response);
			} else {
				String message = "Can't be updated";
				request.setAttribute("error_msg", message);
				request.getRequestDispatcher("/app/routePage.jsp").forward(request, response);
			}
			
		} else if (param.equals("Cancel route")) {
			try {
				dbManager.updateRoute(routeId, RouteStatus.CANCELED);
				int requestId = route.getRequest().getId();
				dbManager.updateRequest(requestId, RequestStatus.DECLINED, RouteNote.CLOSED);
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				response.sendRedirect("Controller?command=LOAD_ROUTES");
			}
			
		}
		
	}

	private void completRoute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//make the vehicle available
		//set serviceability
		//RouteNote CLOSED in Request
		//RouteStatus CLOSED in Route
		try {
			int requestId = Integer.parseInt(request.getParameter("requestId"));
			RouteNote routeNote = RouteNote.valueOf(request.getParameter("routeNote").toUpperCase());
			boolean isServiceabile = request.getParameter("serviceability").equals("Positive") ? true : false;
			boolean isAvailable = true;
			Route route = dbManager.getRouteByRequestId(requestId);
			route.setRouteStatus(RouteStatus.CLOSED);
			int vehicleId = route.getVehicle().getId();
			dbManager.updateVehicle(vehicleId, isAvailable, isServiceabile);
			dbManager.updateRequest(requestId, routeNote);
			dbManager.updateRoute(route.getId(), route.getRouteStatus());
			listRequests(request, response);
			
		} catch (SQLException | NotInDataBaseException e) {
			e.printStackTrace();
		} catch(NumberFormatException e) {
			e.printStackTrace();
		}
		
		
	}

	private void updateRoute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int routeId = Integer.parseInt(request.getParameter("routeId"));
		int newVehicleId = Integer.parseInt(request.getParameter("newVehicle"));
		RouteStatus newRouteStatus = RouteStatus.valueOf(request.getParameter("newRouteStatus").toUpperCase());
		try {
			dbManager.updateRoute(routeId, newVehicleId, newRouteStatus);
			Route route = dbManager.getRouteById(routeId);
			if (newRouteStatus == RouteStatus.UNDERWAY) {
				int requestId = route.getRequest().getId();
				boolean isAvailable = false;
				dbManager.updateRequest(requestId, RequestStatus.CONFIRMED);
				dbManager.updateVehicle(newVehicleId, isAvailable);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NotInDataBaseException e) {
			e.printStackTrace();
		}
		listRoutes(request, response);
		
	}

	private void createRoute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int requestId = Integer.parseInt(request.getParameter("requestId"));
		int vehicleId;
		try {
			vehicleId = Integer.parseInt(request.getParameter("vehicleId"));
		} catch (NumberFormatException e) {
			vehicleId = -1;
		}
		Request routeRequest = null;
		Date creationDate = null;
		try {
			
			routeRequest = dbManager.getRequestById(requestId);
			creationDate = new Date();
			Place fromPlace = routeRequest.getFromPlace();
			Place toPlace = routeRequest.getToPlace();
			RouteStatus routeStatus = RouteStatus.valueOf(request.getParameter("routeStatus")
					.toUpperCase());
			Vehicle vehicle = (vehicleId == -1) ? null : dbManager.getVehicleById(vehicleId);
			Person driver = routeRequest.getDriver();
			
			Route route = new Route(creationDate, fromPlace, toPlace, routeStatus, vehicle, driver, routeRequest);
			if (vehicle != null) {
				dbManager.updateRequest(requestId, RequestStatus.CONFIRMED);
				boolean isAvailable = false;
				dbManager.updateVehicle(vehicle.getId(), isAvailable);
			}
			dbManager.addRoute(route);
			
			//dangerous
			String app = request.getContextPath();
			String command = "command=" + Command.VIEW_ALL_ROUTES;
			String message = "message=The route was successfully created";
			response.sendRedirect(app + "/Controller?" + command + "&" + message);
			
		} catch (NotInDataBaseException | SQLException e1) {
			e1.printStackTrace();
		} 
		
	}

	/*
	 * Accepts the user's email and password, defines to which role the user
	 * belongs and if successful, sends him/her to the role-related page (i.e.
	 * Admin page, Driver page, Dispatcher page. If not successful, it sends the
	 * user to Login page and shows the error message.
	 */
	private void doLogin(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		HttpSession session = request.getSession();
		List<Place> placeList = null;
		try {
			placeList = dbManager.getAllPlaces();
		} catch (SQLException | NotInDataBaseException e1) {
			e1.printStackTrace();
		}
		request.getServletContext().setAttribute("placeList", placeList);

		try {
			Person thePerson = dbManager.getPersonByLogin(email, password);
			Role role = thePerson.getRole();
			int personId = thePerson.getId();
			String userName = thePerson.getFirstName() + " " + thePerson.getLastName();
			session.setAttribute("role", role);
			session.setAttribute("personId", personId);
			session.setAttribute("userName", userName);
			switch (role) {
			case ADMIN:
				listPeople(request, response);
				break;
			case DISPATCHER:
				listRoutes(request, response);
				break;
			case DRIVER:
				listRequests(request, response);
				break;
			}
		} catch (NotInDataBaseException e) {
			String message = "Incorrect email/password combination";
			response.sendRedirect("/SummaryTask4/index.jsp?error_message=" + message);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void doLogout(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/index.jsp").forward(request, response);
		request.getSession().invalidate();
	}

	private void listPeople(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Person> peopleList = null;
		String message = request.getParameter("message");
		try {
			peopleList = dbManager.getAllPeople();
		} catch (SQLException | NotInDataBaseException e) {
			e.printStackTrace();
		}
		request.setAttribute("message", message);
		request.setAttribute("peopleList", peopleList);
		request.getRequestDispatcher("/app/adminPage.jsp").forward(request, response);
		
	}

	private void listRoutes(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Route> routesList = null;
		try {
			routesList = dbManager.getAllRoutes();
		} catch (SQLException | NotInDataBaseException e) {
			e.printStackTrace();
		}
		request.setAttribute("routesList", routesList);
		request.getRequestDispatcher("/app/dispatcherPage.jsp").forward(request, response);
	}

	private void viewRoute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int routeID = Integer.parseInt(request.getParameter("routeId"));
		Route route = null;
		try {
			route = dbManager.getRouteById(routeID);
			request.setAttribute("route", route);
			request.getRequestDispatcher("/app/routePage.jsp").forward(request, response);
		} catch (NotInDataBaseException | SQLException e) {
			e.printStackTrace();
		}
		
	}

	private void listRequests(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Request> requestsList = null;
		try {
			requestsList = dbManager.getAllRequests();
		} catch (NotInDataBaseException | SQLException e) {
			e.printStackTrace();
		}
		request.setAttribute("requestsList", requestsList);
		request.getRequestDispatcher("/app/driverPage.jsp").forward(request, response);
	}
	
	private void listRequestsForDispatcher(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Request> requestsList;
		try {
			requestsList = dbManager.getAllRequests();
			request.setAttribute("requestsList", requestsList);
			request.getRequestDispatcher("/app/requests-for-dispatcher.jsp").forward(request, response);
		} catch (NotInDataBaseException | SQLException e) {
			e.printStackTrace();
		}
		
	}

	private void createNewRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		int fromPlaceId = Integer.parseInt(request.getParameter("fromPlace"));
		int toPlaceId = Integer.parseInt(request.getParameter("toPlace"));
		int vehicleTonnage = Integer.parseInt(request.getParameter("tonnage"));
		double vehicleCapacity = Double.parseDouble(request.getParameter("capacity"));
		int driverId = (int) session.getAttribute("personId");
		RequestStatus requestStatus = RequestStatus.PENDING;
		RouteNote routeNote = RouteNote.OPEN;

		try {
			dbManager.addRequest(fromPlaceId, toPlaceId, vehicleTonnage, vehicleCapacity, driverId, requestStatus,
					routeNote);
			//listRequests(request, response);
			response.sendRedirect("Controller?command=LOAD_DRIVER_REQUESTS");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void loadRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int requestId = Integer.parseInt(request.getParameter("requestId"));
		Request routeRequest;
		try {
			routeRequest = dbManager.getRequestById(requestId);
			request.setAttribute("routeRequest", routeRequest);
			request.getRequestDispatcher("/app/update-request-form.jsp").forward(request, response);
		} catch (NotInDataBaseException | SQLException e) {
			e.printStackTrace();
		}
		
	}

	private void updateRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int requestId = Integer.parseInt(request.getParameter("requestId"));

		Request routeRequest;
		try {
			routeRequest = dbManager.getRequestById(requestId);
			if (routeRequest.getRequestStatus() == RequestStatus.PENDING) {
				int fromPlaceId = Integer.parseInt(request.getParameter("fromPlace"));
				int toPlaceId = Integer.parseInt(request.getParameter("toPlace"));
				int vehicleTonnage = Integer.parseInt(request.getParameter("tonnage"));
				double vehicleCapacity = Double.parseDouble(request.getParameter("capacity"));

				dbManager.updateRequest(requestId, fromPlaceId, toPlaceId, vehicleTonnage, vehicleCapacity);
				listRequests(request, response);

			} else {
				listRequests(request, response);
			}
		} catch (NotInDataBaseException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void sendToNewRoutePage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int requestID = Integer.parseInt(request.getParameter("requestId"));
		Request routeRequest = null;
		try {
			routeRequest = dbManager.getRequestById(requestID);
			request.setAttribute("routeRequest", routeRequest);
			if (routeRequest.getRequestStatus() == RequestStatus.PENDING) {
				request.getRequestDispatcher("/app/new-route-page.jsp").forward(request, response);
			} else {
				String message = "Can't be created";
				request.setAttribute("error_msg", message);
				listRequestsForDispatcher(request, response);
				//request.getRequestDispatcher("/requests-for-dispatcher.jsp").forward(request, response);
			}
		} catch (NotInDataBaseException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void sendToCompleteRoutePage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int requestID = Integer.parseInt(request.getParameter("requestId"));
		Request routeRequest = null;
		try {
			routeRequest = dbManager.getRequestById(requestID);
			if(routeRequest.getRouteNote() == RouteNote.OPEN) {
				request.setAttribute("routeRequest", routeRequest);
				request.getRequestDispatcher("/app/completeRoutePage.jsp")
					.forward(request, response);
			} else {
				listRequests(request, response);
			}
			
		} catch (NotInDataBaseException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	private void listVehicles(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int requestId = Integer.parseInt(request.getParameter("requestId"));
		String routeId = request.getParameter("routeId");
		request.setAttribute("routeId", routeId);
		try {
			Request routeRequest = dbManager.getRequestById(requestId);
			request.setAttribute("routeRequest", routeRequest);
			List<Vehicle> vehicleList = dbManager.getAllVehicles();
			request.setAttribute("vehicleList", vehicleList);
			request.getRequestDispatcher("/app/vehicle-list.jsp").forward(request, response);
		} catch (NotInDataBaseException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	private void sortOutVehicles(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int requestId = Integer.parseInt(request.getParameter("requestId"));
		int routeId = -1;
		try {
			routeId = Integer.parseInt(request.getParameter("routeId"));
		} catch (NumberFormatException e1) {
			routeId = -1;
		}
		try {
			Request routeRequest = dbManager.getRequestById(requestId);
			Route route = routeId > 0 ? dbManager.getRouteById(routeId) : null;
			int requiredTonnage = routeRequest.getVehicleTonnage();
			double requiredCapacity = routeRequest.getVehicleCapacity();
			List<Vehicle> sortedVehicle = dbManager.getAvailableVehicles(requiredTonnage, requiredCapacity);
			request.setAttribute("vehicleList", sortedVehicle);
			request.setAttribute("routeRequest", routeRequest);
			if (route != null) {
				request.setAttribute("routeId", route.getId());
			}
			
			request.getRequestDispatcher("/app/vehicle-list.jsp").forward(request, response);
			
		} catch (NotInDataBaseException | SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		
	}

	private void assignVehicle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//vehicleId=9&requestId=2
		try {
			int vehicleId = Integer.parseInt(request.getParameter("vehicleId"));
			int requestId = Integer.parseInt(request.getParameter("requestId"));
			String routeId = request.getParameter("routeId");
			Request routeRequest = null;
			Vehicle vehicle = null;
			routeRequest = dbManager.getRequestById(requestId);
			vehicle = dbManager.getVehicleById(vehicleId);
			request.setAttribute("routeRequest", routeRequest);
			request.setAttribute("vehicle", vehicle);
			if (routeId != null && routeId != "") {
				Route route = dbManager.getRouteById(Integer.parseInt(routeId));
				route.setVehicle(vehicle);
				route.setRouteStatus(RouteStatus.UNDERWAY);
				request.setAttribute("route", route);
				
				request.getRequestDispatcher("/app/update-route-page.jsp").forward(request, response);
			} 
			request.getRequestDispatcher("/app/new-route-page.jsp").forward(request, response);

		} catch (NumberFormatException | SQLException | NotInDataBaseException e) {
			e.printStackTrace();
		}
		
	}

}
