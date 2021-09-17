package fmi.landmarks.agent;

import java.io.IOException;
import java.net.URI;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import jade.Boot;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONString;
import org.json.JSONStringer;
import sun.net.www.http.HttpClient;

public class TripOfferring {
	public static void main(String[] args) throws IOException, InterruptedException, JSONException {

		/*String startDate = "04/09/2021";
		String endDate = "06/09/2021";
		String startingPointLatitude = "42.63231021535507";
		String startingPointLongitude = "24.80449523989785";
		String categoryId = "2";
		String typeId = "3";
		String regionId = "3";
		String userId = "2";


		System.out.println(startDate);
		System.out.println(endDate);

		TripSchedulerAgent tripSchedulerAgent = new TripSchedulerAgent();
		Object[] arguments = new Object[8];
		arguments[0] = userId;
		arguments[1] = categoryId;
		arguments[2] = typeId;
		arguments[3] = regionId;
		arguments[4] = startingPointLatitude;
		arguments[5] = startingPointLongitude;
		arguments[6] = startDate;
		arguments[7] = endDate;

		tripSchedulerAgent.setArguments(arguments);
		tripSchedulerAgent.run();*/
		//HttpClient client = HttpClient.newHttpClient();
		   //HttpRequest request = HttpRequest.newBuilder()
		         //.uri(URI.create("https://maps.googleapis.com/maps/api/directions/json?origin=Disneyland&destination=Universal+Studios+Hollywood&key=AIzaSyBvQFvmp4-59XuY17CPkJ_Ui1DUT_ODAkM"))
		         //.build();

	        //HttpResponse<String> response = client.send(request,
	                //HttpResponse.BodyHandlers.ofString());
	        
	        //JSONObject jsonObject = new JSONObject(response.body().toString());

	        //System.out.println(jsonObject.get("routes"));

	}

}
