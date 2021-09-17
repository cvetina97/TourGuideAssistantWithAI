package fmi.landmarks.agent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import com.sun.deploy.net.HttpRequest;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;

public class TripTimeCheckerAgent extends Agent{

	private static HttpURLConnection connection;
	ArrayList<TripOffer> tripOffers; // оферти за проверка
	ArrayList<TripOfferToManipulate> tripOffersToManipulate = new ArrayList<TripOfferToManipulate>();
	BufferedReader bufferedReader;
	String line;
	StringBuffer responseContent = new StringBuffer();
	
	@Override
	protected void setup() {
		System.out.println("Trip time checking agent started");
		
		DFAgentDescription dad = new DFAgentDescription();//Създаваме описание на агент което ще използваме за да регистрираме услугите които предлага агента в жълтите страници
		dad.setName(getAID());//слагаме името на агент (неговото aгент id)
		
		ServiceDescription sd = new ServiceDescription();//създаваме описание на предлаганата услуга
		sd.setType("offer-trip");//слагаме тип на услугата
		sd.setName("let-me-check-time-for-trip");//слагаме име на услугата
		
		dad.addServices(sd);//добавяме създадената услуга към описанието на агента
		
		try {
			DFService.register(this, dad);//регистрираме себе се(агента) във жълтите страници със неготово описание което създадохме			
		} catch (FIPAException e) {			
			e.printStackTrace();
		}	
		
		addBehaviour(new CyclicBehaviour() {//използва се да чака съобщения за запитване за конкретно предложение
			
			@Override
			public void action() {
				MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.CFP);//използваме темплейт да филтрираме само събощения които са с перформатив CFP
				
				ACLMessage aclMessage = myAgent.receive(mt);//получваме съобщение от чакащите което отговаря на темплей-та
				
				if(aclMessage != null) {//проверяваме има ли такова съобщение
					System.out.println("Receiving message from scheduler");
					try {
						tripOffers = (ArrayList<TripOffer>) aclMessage.getContentObject(); //взима предложенията за почивка
					} catch (UnreadableException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					ACLMessage aclReply = aclMessage.createReply();//започваме подоготовка за отговор
					
					//тук слагам метода за проверка на времето
					for (TripOffer tripOffer: tripOffers) {
						TripOfferToManipulate tripOfferToManipulate = CheckTimeForTrip(tripOffer);
						if(tripOfferToManipulate != null){
							tripOffersToManipulate.add(tripOfferToManipulate);
						}
					}

					if(tripOffersToManipulate.size() == 0){
						aclReply.setPerformative(ACLMessage.ACCEPT_PROPOSAL);//праща подвърждение, че офертата е подходяща
					}
					else{
						aclReply.setPerformative(ACLMessage.REJECT_PROPOSAL);//праща отказ и офертите, които трябва да се преправят
						try {
							aclMessage.setContentObject(tripOffersToManipulate);//изпращаме предложението за преработка
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					myAgent.send(aclMessage);//изпраща съобщението
				}
								
			}
		});	
	}

	private double rad(double x) {
		return x * Math.PI / 180;
	}
	
	private TripOfferToManipulate CheckTimeForTrip(TripOffer tripOffer){
		// по 2 часа за забележителност, първоначално по 2 забележителност на ден, 2 часа - обяд
		// тръгване в осем сутрин, пътуване до 6 вечер, 3 * 2 = 6, остават 4 часа за път
		
		LocalDate startDate = tripOffer.getStartDate();
		LocalDate endDate = tripOffer.getEndDate();

		int tripLength = (int) ChronoUnit.DAYS.between(startDate, endDate);
		int maxTimeForTravel = tripLength * 4; //трябва да е в часове, време за пътуване по логиката по-горе
		
		double sumDistance = 0;
		int averageSpeedInKm = 80;
		int timeInHours = 0;
		int numberOfLandmarksToManipulate = 0;

		Landmark currentLandmark = new Landmark();
		currentLandmark.setLatitude(tripOffer.getStartingPointLatitude());
		currentLandmark.setLongitude(tripOffer.getStartingPointLongitude());
		for (Landmark landmark: tripOffer.landmarks)
		{
			int R = 6378137; // Earth’s mean radius in meter
			double distanceLat = rad(landmark.getLatitude() - currentLandmark.getLatitude());
			double distanceLong = rad(landmark.getLongitude() - currentLandmark.getLongitude());
			double a = Math.sin(distanceLat / 2) * Math.sin(distanceLat / 2) + Math.cos(rad(currentLandmark.getLatitude())) * Math.cos(rad(landmark.getLatitude())) * Math.sin(distanceLong / 2) * Math.sin(distanceLong / 2);
			double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
			double distance = R * c;
			double currentDistanceInKm = distance/1000;
			currentLandmark = landmark;

			sumDistance+= currentDistanceInKm;
		}

		timeInHours = (int) (sumDistance/ averageSpeedInKm);

		if(timeInHours > maxTimeForTravel){
			double difference = timeInHours - maxTimeForTravel;
				numberOfLandmarksToManipulate = (int) difference / 2;
			return new TripOfferToManipulate(
					tripOffer.getId(),
					TripOfferManipulation.RemoveLandmark,
					numberOfLandmarksToManipulate);
		}
		else if( timeInHours < maxTimeForTravel){
			double difference = maxTimeForTravel - timeInHours;
				numberOfLandmarksToManipulate = (int) (difference / 3);
				if(numberOfLandmarksToManipulate == 0){
					return null;
				}
			return new TripOfferToManipulate(
					tripOffer.getId(),
					TripOfferManipulation.AddLandmark,
					numberOfLandmarksToManipulate);
		}
		else{
			return null;
		}

		/*try {
			URL url = new URL("https://maps.googleapis.com/maps/api/directions/json?origin=${tripOffer.startingPointLat,tripOffer.startingPointLong}&destination=Universal+Studios+Hollywood&key=AIzaSyBvQFvmp4-59XuY17CPkJ_Ui1DUT_ODAkM");
			connection = (HttpURLConnection) url.openConnection();

			connection.setRequestMethod("GET");
			connection.setConnectTimeout(5000);
			connection.setReadTimeout(5000);

			int status = connection.getResponseCode();

			if(status > 299){
				bufferedReader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
				while((line = bufferedReader.readLine()) != null){
					responseContent.append(line);
				}
				bufferedReader.close();
			}else{
				bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				while((line = bufferedReader.readLine()) != null){
					responseContent.append(line);
				}
				bufferedReader.close();
			}
			System.out.println(responseContent.toString());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			connection.disconnect();
		}*/
	}
}
