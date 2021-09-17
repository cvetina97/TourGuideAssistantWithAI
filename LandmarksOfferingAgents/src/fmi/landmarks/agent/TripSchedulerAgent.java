package fmi.landmarks.agent;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;

import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

public class TripSchedulerAgent extends Agent{
	
	private AID[] checkerAgents;
	
	ArrayList<TripOffer> tripOffers = new ArrayList<TripOffer>();
	ArrayList<TripOfferToManipulate> tripOffersToManipulate = new ArrayList<TripOfferToManipulate>();
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	TripOffer tripOffer;
	LocalDate startDate;
	LocalDate endDate;
	double startingPointLongitude;
	double startingPointLatitude;
	int tripLength;
	int categoryId;
	int typeId;
	int regionId;
	int userId;
	
	public static ArrayList<Landmark> filteredLandmarks;
	
	Random rand = new Random();
	
	@Override
	protected void setup() {
		Object[] arguments = getArguments(); // трябва да дойдат от екрана с настройки за пътуване от потребителя
		// начална дата на почивка, крайна дата на почивка, начална точка на пътуването, 
		//преференции за тип и категория забалежителност, регион

		System.out.println("TripSchedulerAgent started. Preparing offers");
		
		try {
			userId = Integer.parseInt(arguments[0].toString());
			categoryId = Integer.parseInt(arguments[1].toString());
			typeId = Integer.parseInt(arguments[2].toString());
			regionId = Integer.parseInt(arguments[3].toString());
			startingPointLatitude = Double.parseDouble(arguments[4].toString());
			startingPointLongitude = Double.parseDouble(arguments[5].toString());
			startDate = LocalDate.parse(arguments[6].toString(), dtf);
			endDate = LocalDate.parse(arguments[7].toString(), dtf);
			
			System.out.println(startDate);
			System.out.println(endDate);
			
			tripLength = (int) ChronoUnit.DAYS.between(startDate, endDate);
			filteredLandmarks = RepoLandmarks.filterLandmarks(regionId, categoryId, typeId, false);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		addBehaviour(new TickerBehaviour(this, 10000) {//ticker behaviour който се изпълнява на всеки 10 секунди 
			//търси за свободни агенти да проверят времето за пътуване на почивката
			
			@Override
			protected void onTick() {
				System.out.println(myAgent.getName() + "Looking for an agent to check time on trip");
				
				DFAgentDescription dfAgentDescription = new DFAgentDescription();
				ServiceDescription serviceDescription = new ServiceDescription();//описания на услугата която търсим
				serviceDescription.setType("offer-trip");

				dfAgentDescription.addServices(serviceDescription);
				
				try {
					DFAgentDescription[] searchResult = DFService.search(myAgent, dfAgentDescription);//получаваме списък с описанията на агентите отговарящи на търсеното описание
					
					checkerAgents = new AID[searchResult.length];//инициализираме масива на id-та на агентите
					
					for(int i = 0; i < searchResult.length; i++) {//попълваме масива
						checkerAgents[i] = searchResult[i].getName();
					}
					
				} catch (FIPAException e) {			
					e.printStackTrace();
				}
				
				if(checkerAgents.length > 0) {//ако има агенти за проверка на времето за пътуване
					myAgent.addBehaviour(new OfferTripBehaviour());//стартираме офертите
				}else
				{//ако не - значи в момента няма агенти за тази услуга
					System.out.println("There is no agents for our purpose!");
				}
			}
		});
	}
	
	private class OfferTripBehaviour extends Behaviour {

		int step = 0;
		MessageTemplate mt;
		
		int acceptedOffers = 0;
		
		ACLMessage aclMessage;//съобщение което ще изпращаме
		ACLMessage aclReply;//съобщение което сме получили
		
		@Override
		public void action() {
			
			switch(step) {
			
			case 0:	
				System.out.println(myAgent.getName() + ": Sending offers for checking");
				aclMessage = new ACLMessage(ACLMessage.CFP);
				
				for(int i = 0; i < checkerAgents.length; i++) {
					aclMessage.addReceiver(checkerAgents[i]);					
				}

				if(filteredLandmarks.size() >= tripLength * 2){
					//Trip offer by distance algorithm
					TripOffer tripOfferWithNearestLandmarks = new TripOffer(userId, startingPointLatitude, startingPointLongitude, startDate, endDate);
					tripOffers.add(RepoLandmarks.setLandmarksInOfferByDistance(filteredLandmarks, tripLength, tripOfferWithNearestLandmarks, startingPointLatitude, startingPointLongitude));

					// Trip offer by random principe algorithm
					TripOffer tripOfferWithRandomLandmarks = new TripOffer(userId, startingPointLatitude, startingPointLongitude, startDate, endDate);
					tripOffers.add(RepoLandmarks.setRandomLandmarksInOffer(filteredLandmarks, tripLength, tripOfferWithRandomLandmarks));

					//Trip offer by category and region
					TripOffer tripOfferWithAllTypesInRegion = new TripOffer(userId, startingPointLatitude, startingPointLongitude, startDate, endDate);
					tripOffers.add(RepoLandmarks.setLandmarksFilteredByCategoryAndRegion(tripLength, categoryId, regionId, tripOfferWithAllTypesInRegion));
				}

				try {
					aclMessage.setContentObject(tripOffers);//като съдържание предложенията за почивка(по 3 предложения)
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				};
				
				aclMessage.setConversationId("start-checking-offer");//слагаме id на разговора за да го иползваме за определене на отговор по това съобщение
				aclMessage.setReplyWith("start-" + System.currentTimeMillis());//слагаме допълнителен уникален "таг" по който да се ориентираме за конкретния отговор
				
				myAgent.send(aclMessage);//изпращаме съобщението
				
				mt = MessageTemplate.and(MessageTemplate.MatchConversationId("start-checking-offer"),//подогтваме темплейта който ще иползваме за получените отговори
						MessageTemplate.MatchInReplyTo(aclMessage.getReplyWith()));
				
				step++;//преминаваме на следваща стъпка
				
				break;
				
			case 1:
				
				aclReply = myAgent.receive(mt);//получаваме отговор отговарящ на темплейта от горе
				
				if(aclReply != null) {//ако има отговор
					System.out.println("Case 1");
					if(aclReply.getPerformative() == ACLMessage.ACCEPT_PROPOSAL) {//проверява отговора от другия агент
						System.out.println(myAgent.getName() + "Offers accepted");
						step++;
					} else if (aclReply.getPerformative() == ACLMessage.REJECT_PROPOSAL){
						try {
							tripOffersToManipulate = (ArrayList<TripOfferToManipulate>) aclReply.getContentObject();
						} catch (UnreadableException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						ArrayList<TripOffer> tripOffersToCheck = (ArrayList<TripOffer>) tripOffers.clone();
						for (TripOfferToManipulate tripOfferToManipulate : tripOffersToManipulate)
						{
							if(tripOfferToManipulate.getTripOfferManipulation() == TripOfferManipulation.AddLandmark){
								int numberOfAddedLandmarks = addToLandmarksInOffer(tripOfferToManipulate);

								if(numberOfAddedLandmarks == 0){
									TripOffer tripOfferToRemove = tripOffers.stream().filter(searchedTripOffer -> searchedTripOffer.getId() == tripOfferToManipulate.getId()).findFirst().get();
									tripOffersToCheck.remove(tripOfferToRemove);
								}
							}

							if(tripOfferToManipulate.getTripOfferManipulation() == TripOfferManipulation.RemoveLandmark){
								reduceLandmarksInOffer(tripOfferToManipulate);
							}
						}
						
						try {
							if(tripOffersToCheck.size() != 0){
								aclMessage.setContentObject(tripOffersToCheck);//като съдържание предложенията за почивка
								myAgent.send(aclMessage);
							}else {
								step++;
							}
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						};
					}
				}
						
				break;
				
			case 2://агента приключва работа, защото е изпратил нужния брой одобрени предложения за почивки
					System.out.println(myAgent.getName() + " done giving offers");
						myAgent.doDelete();					
					
					step++;//преминваме в стъпка 3 (тоест край на държанието)		
				
				break;
			}
			}

		@Override
		public boolean done() {
			if ( step == 3)
				return true;

			return false;
		}
	}
	
	private void reduceLandmarksInOffer(TripOfferToManipulate tripOfferToManipulate)
	{
		TripOffer searchedTripOffer = tripOffers.stream()
				.filter(tripOffer -> tripOffer.getId() == tripOfferToManipulate.getId())
				.findFirst().get();

		for (int i =0; i < tripOfferToManipulate.getNumberOfLandmarksToManipulate(); i++){
			searchedTripOffer.landmarks.remove(rand.nextInt(searchedTripOffer.landmarks.size()));
		}
	}
	
	private int addToLandmarksInOffer(TripOfferToManipulate tripOfferToManipulate)
	{
		int averageSpeed = 80;
		double timeOfAllLandmarks = 0;
		double maxTimeOfAllLandmarks = tripOfferToManipulate.getNumberOfLandmarksToManipulate() * 1;
		int newlyAddedLandmarks = 0;

		TripOffer searchedTripOffer = tripOffers.stream()
				.filter(tripOffer -> tripOffer.getId() == tripOfferToManipulate.getId())
				.findFirst().get();

		ArrayList<Integer> filteredLandmarksIds = (ArrayList<Integer>) filteredLandmarks.stream()
				.map(Landmark::getId)
				.collect(Collectors.toList());

		ArrayList<Landmark> landmarks = (ArrayList<Landmark>) searchedTripOffer.landmarks
				.stream()
				.filter(landmark -> !filteredLandmarksIds.contains(landmark.getId()))
				.collect(Collectors.toList());

		for(int i =0; i < tripOfferToManipulate.getNumberOfLandmarksToManipulate(); i++){
			Landmark currentNearestLandmark = RepoLandmarks.getNextNearestLandmark(searchedTripOffer.landmarks.get(searchedTripOffer.landmarks.size() - 1), landmarks);

			if(currentNearestLandmark == null || timeOfAllLandmarks == maxTimeOfAllLandmarks){
				break;
			}

			Landmark lastLandmarkInOffer = searchedTripOffer.landmarks.get(searchedTripOffer.landmarks.size() - 1);
			double distance = RepoLandmarks.getDistanceBetweenTwoPoints(lastLandmarkInOffer.getLatitude(), lastLandmarkInOffer.getLongitude(), currentNearestLandmark.getLatitude(), currentNearestLandmark.getLongitude());

			if(distance / averageSpeed > 1){
				continue;
			}
			else{
				newlyAddedLandmarks++;
				timeOfAllLandmarks += distance/averageSpeed;
				searchedTripOffer.landmarks.add(currentNearestLandmark);
			}
		}
		
		return newlyAddedLandmarks;
	}
	
	
	
	
	
	
	
}
