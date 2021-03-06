package WebService;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.google.gson.Gson;

import ie.gmit.sw.DatabaseOption;

// Path
@Path("car")
public class CarResource {

	// RMI settings
	private String service = "/databaseOption";
	private String address = "localhost:1099";

	// This function displays all cars in the database
	@GET
	@Path("/get")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCars()
			throws RemoteException, MalformedURLException, NotBoundException, SQLException, ClassNotFoundException {

		// Connect to RMI and setup crud interface
		DatabaseOption db = (DatabaseOption) Naming.lookup("rmi://" + address + service);

		// Connect to database
		db.Connect();

		// Get all data in cars and add to list object
		List<Object> rs = db.ReadCar("SELECT * FROM CARS");

		// Disconnect to database
		db.Close();

		// GSON import used to serialize and deserialize Java objects to JSON
		Gson gson = new Gson();

		// Set to string
		String jsonResp = gson.toJson(rs);

		// Return webpage with json data from RMI
		return Response.ok(jsonResp, MediaType.APPLICATION_JSON).build();
	}

	// This function is used to add cars to the database through the /post
	// webpage
	@POST
	@Path("/post")
	@Consumes("application/json")
	public void postCar(String input)
			throws MalformedURLException, RemoteException, NotBoundException, ClassNotFoundException, SQLException {

		// Separate incoming data into individual strings
		String[] splited = input.split("\\s+");

		// Connect to RMI and setup crud interface
		DatabaseOption db = (DatabaseOption) Naming.lookup("rmi://" + address + service);

		// Connect to database
		db.Connect();

		// Create a new row in the cars table
		db.Create("INSERT INTO CARS (REG, YEAR, MAKE, COST) VALUES ('" + splited[0] + "', '" + splited[1] + "', '"
				+ splited[2] + "', '" + splited[3] + "')");

		// Disconnect to database
		db.Close();
	}

	// This function is used to get one object in the database
	@GET
	@Path("/edit/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCar(@PathParam(value = "id") int id)
			throws RemoteException, MalformedURLException, NotBoundException, ClassNotFoundException, SQLException {

		// Connect to RMI and setup crud interface
		DatabaseOption db = (DatabaseOption) Naming.lookup("rmi://" + address + service);

		// Connect to database
		db.Connect();

		// Get specific car by its unique id
		List<Object> rs = db.ReadCar("SELECT * FROM CARS WHERE id=" + id);

		// Disconnect to database
		db.Close();

		// GSON import used to serialize and deserialize Java objects to JSON
		Gson gson = new Gson();

		// Set to string
		String jsonResp = gson.toJson(rs);

		// Return webpage with json data from RMI
		return Response.ok(jsonResp, MediaType.APPLICATION_JSON).build();
	}

	// This function is used to update a row in the cars table
	@PUT
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON)
	public void putCar(String test)
			throws MalformedURLException, RemoteException, NotBoundException, ClassNotFoundException, SQLException {

		// Separate incoming data into individual strings
		String[] splited = test.split("\\s+");

		// Connect to RMI and setup crud interface
		DatabaseOption db = (DatabaseOption) Naming.lookup("rmi://" + address + service);

		// Connect to database
		db.Connect();

		// Update the cars table
		db.Update("UPDATE CARS SET REG='" + splited[1] + "', YEAR='" + splited[2] + "', MAKE='" + splited[3]
				+ "', COST='" + splited[4] + "' WHERE id='" + splited[0] + "'");

		// Update the bookings table
		db.Update("UPDATE BOOKINGS SET REG='" + splited[1] + "', YEAR='" + splited[2] + "', MAKE='" + splited[3]
				+ "', COST='" + splited[4] + "' WHERE CARID='" + splited[0] + "'");

		// Disconnect to database
		db.Close();
	}

	// This function is used to delete data from the cars table
	@DELETE
	@Path("/delete")
	@Consumes(MediaType.APPLICATION_JSON)
	public void delCar(String id)
			throws MalformedURLException, RemoteException, NotBoundException, ClassNotFoundException, SQLException {

		// Connect to RMI and setup crud interface
		DatabaseOption db = (DatabaseOption) Naming.lookup("rmi://" + address + service);

		// Connect to database
		db.Connect();

		// Delete the cars table
		db.Delete("DELETE FROM CARS WHERE id='" + id + "'");

		// Delete the bookings table
		db.Delete("DELETE FROM BOOKINGS WHERE CARID='" + id + "'");

		// Disconnect to database
		db.Close();
	}

}
