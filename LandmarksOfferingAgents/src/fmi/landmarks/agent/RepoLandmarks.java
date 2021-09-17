package fmi.landmarks.agent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.Collectors;

public final class RepoLandmarks {

	private RepoLandmarks() {};

	public static ArrayList<Landmark> landmarks;
	public static ArrayList<Integer> typeIds;

	public static ArrayList<Integer> getAllTypeIds(){
		setAllTypeIds();
		return typeIds;
	}

	private static void setAllTypeIds(){
		typeIds = new ArrayList<Integer>(
				Arrays.asList(2, 3, 4, 5, 6, 7, 8));
	}
	
	public static ArrayList<Landmark> getAllLandmarks(){
		setAllLandmarks();
		return landmarks;
	}
	
	private static void setAllLandmarks() {
		//public Landmark(int Id, String name, int categoryId, int typeId, int regionId) {
		
		//CategoryId -> 1 - природни,  2 - културно-исторически, 3 - антропогенни(създадени от човека)
		//TypeId -> 2 - пещери, 3 - музеи, 4 - манастири, 5 - природен резерват, 6- зоологическа градина, 7 - крепост
		// 8 - водопад, 9 - местности
		//RegionId ->  1 - Смолян; 2 - София; 3 - Благоевград; 4 - Бургас; 5 - Варна, 6 - Пловдив, 7 - Добрич;
		// 8 - Търново, 9 - С.Загора, 10 - Ловеч
		
		landmarks = new ArrayList<Landmark>();
		
		//пещери
		landmarks.add(new Landmark(1, "The devil's throat", 1, 2, 1, 41.614884, 24.379132));
		landmarks.add(new Landmark(2, "Jagodina cave", 1, 2, 1, 42.607404, 23.379820));
		landmarks.add(new Landmark(3, "Uhlovitsa cave", 1, 2, 1, 41.514176, 24.659933));
		landmarks.add(new Landmark(4, "Marvelous Bridges", 1, 2, 1, 43.264459, 28.017125));
		landmarks.add(new Landmark(5, "Sharenka", 1, 2, 1, 41.489870746365106, 24.91780901757138));
		landmarks.add(new Landmark(6, "Haramiiska hole", 1, 2, 1, 41.61480420218986, 24.381620781153774));
		landmarks.add(new Landmark(7, "The dark hole", 1, 2, 2, 43.089376125829645, 23.384881320787105));
		landmarks.add(new Landmark(8, "Ryazhishka cave", 1, 2, 2, 43.090417819749305, 23.38620148883336));
		
		//музеи
		landmarks.add(new Landmark(15, "Regional History Museum (Blagoevgrad)", 2, 3, 3, 42.020600780555114, 23.10292540621697));
		landmarks.add(new Landmark(16, "Archaeological Museum (Sandanski)", 2, 3, 3, 41.56646374823699, 23.28071957589907));
		landmarks.add(new Landmark(19, "Historical Museum (Melnik)", 2, 3, 3, 41.52343491676215, 23.396550745105447));
		landmarks.add(new Landmark(20, "Historical Museum (Petrich)", 2, 3, 3, 41.39194754076543, 23.208573488687747));
		landmarks.add(new Landmark(24, "Samuel's Fortress", 2, 3, 3, 41.39486267747428, 23.028923990020804));
		landmarks.add(new Landmark(27, "Ethnographic Museum (Burgas)", 2, 3, 4, 42.491383259078134, 27.479333836002553));
		landmarks.add(new Landmark(30, "Archaeological Museum (Nessebar)", 2, 3, 4, 42.65850571936364, 27.730774922167083));
		landmarks.add(new Landmark(31, "Salt Museum (Pomorie)", 2, 3, 4, 42.56559109039492, 27.631750117940204));
		landmarks.add(new Landmark(33, "Historical Museum - Malko Tarnovo", 2, 3, 4, 41.97958085568266, 27.524272041830297));
		landmarks.add(new Landmark(34, "Archaeological Museum of Varna", 2, 3, 5, 43.20748983113745, 27.91539945389535));
		landmarks.add(new Landmark(35, "The Varna Aquarium", 2, 3, 5, 43.20159374729172, 27.922480829028085));
		landmarks.add(new Landmark(36, "Naval Museum", 2, 3, 5, 43.20048291700377, 27.92140317011506));
		landmarks.add(new Landmark(46, "Museum of the Revival", 2, 3, 5, 43.20138063759172, 27.912351765476373));
		landmarks.add(new Landmark(47, "Vladislav Varnenchik Park-Museum", 2, 3, 5, 43.2312118101798, 27.8684032374279));
		landmarks.add(new Landmark(48, "Ethnographic Museum (Dobrich)", 2, 3, 7, 43.5676219363991, 27.83190920805946));
		landmarks.add(new Landmark(49, "Miniature Park Tarnovgrad - the spirit of millennial Bulgaria", 2, 3, 8, 43.08109244668494, 25.654597152275556));

		//манастири
		landmarks.add(new Landmark(50, "Rozhen Monastery Nativity of the Virgin", 2, 4, 3, 41.53111898047428, 23.426866463261014));
		landmarks.add(new Landmark(51, "St. Mary Spileotisa", 2, 4, 3, 41.52158809783253, 23.402256420081095));
		landmarks.add(new Landmark(52, "Pomorie Monastery St. George", 2,4,4, 42.56780517149446, 27.617930997713746));

		//природен резерват
		landmarks.add(new Landmark(53, "Rozhen pyramids", 1, 5, 3, 41.52754815704016, 23.41984371088616));

		// зоологическа градина
		landmarks.add(new Landmark(54, "Dobrich Zoo", 3, 6, 7, 43.560145021193506, 27.84336553408683 ));
		landmarks.add(new Landmark(55, "Stara Zagora Zoo", 3, 6, 9, 42.452638655542636, 25.610977763215597));
		landmarks.add(new Landmark(56, "Sofiq Zoo", 3, 6, 2, 42.65998172686573, 23.334020854064658));
		landmarks.add(new Landmark(57, "Burgas Zoo", 3, 6, 4, 42.602188705800685, 27.49492234737983));

		// крепост
		landmarks.add(new Landmark(58, "Tsarevets Architectural and Museum Reserve", 2, 7, 8, 43.084989134028966, 25.65281904512917));

		// водопад
		landmarks.add(new Landmark(59, "Kartala Waterfall", 1, 8, 8, 43.09737729239754, 25.623973022374898));
		landmarks.add(new Landmark(60, "Paradise Sprinkler Waterfall",1, 8, 6 , 42.70129891251591, 24.92543750434382 ));
		landmarks.add(new Landmark(61, "Krushuna waterfalls", 1, 8 , 10,43.24351848893645, 25.03318017163176 ));

	}

	public static double rad(double x) {
		return x * Math.PI / 180;
	}

	public static Landmark getNextNearestLandmark(Landmark currentLandmark, ArrayList<Landmark> filteredLandmarks) {
		double minNearestDistance = Double.MAX_VALUE;
		Landmark nearestLandmark = null;

		for (Landmark landmark : filteredLandmarks)
		{
			int R = 6378137; // Earth’s mean radius in meter
			double distanceLat = rad(landmark.getLatitude() - currentLandmark.getLatitude());
			double distanceLong = rad(landmark.getLongitude() - currentLandmark.getLongitude());
			double a = Math.sin(distanceLat / 2) * Math.sin(distanceLat / 2) + Math.cos(rad(currentLandmark.getLatitude())) * Math.cos(rad(landmark.getLatitude())) * Math.sin(distanceLong / 2) * Math.sin(distanceLong / 2);
			double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
			double distance = R * c;
			double currentDistanceInKm = distance/1000;

			if(minNearestDistance > currentDistanceInKm){
				minNearestDistance = currentDistanceInKm;
				nearestLandmark = landmark;
			}
		}

		return nearestLandmark;
	}

	public static double getDistanceBetweenTwoPoints(double firstPointLatitude, double firstPointLongitude, double secondPointLatitude, double secondPointLongitude){
		int R = 6378137; // Earth’s mean radius in meter
		double distanceLat = rad(secondPointLatitude - firstPointLatitude);
		double distanceLong = rad(secondPointLongitude - firstPointLongitude);
		double a = Math.sin(distanceLat / 2) * Math.sin(distanceLat / 2) + Math.cos(rad(firstPointLatitude)) * Math.cos(rad(secondPointLatitude)) * Math.sin(distanceLong / 2) * Math.sin(distanceLong / 2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		double distance = R * c;
		double distanceInKm = distance/1000;

		return distanceInKm;
	}

	public static Landmark getNextNearestLandmark(double startingPointLatitude, double startingPointLongitude, ArrayList<Landmark> filteredLandmarks) {
		double minNearestDistance = Double.MAX_VALUE;
		Landmark nearestLandmark = null;

		for (Landmark landmark : filteredLandmarks)
		{
			int R = 6378137; // Earth’s mean radius in meter
			double distanceLat = rad(landmark.getLatitude() - startingPointLatitude);
			double distanceLong = rad(landmark.getLongitude() - startingPointLongitude);
			double a = Math.sin(distanceLat / 2) * Math.sin(distanceLat / 2) + Math.cos(rad(startingPointLatitude)) * Math.cos(rad(landmark.getLatitude())) * Math.sin(distanceLong / 2) * Math.sin(distanceLong / 2);
			double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
			double distance = R * c;
			double currentDistanceInKm = distance/1000;

			if(minNearestDistance > currentDistanceInKm){
				minNearestDistance = currentDistanceInKm;
				nearestLandmark = landmark;
			}
		}

		return nearestLandmark;
	}

	public static Landmark findLandmarkById(int landmarkId){
		return landmarks.get(landmarkId);
	}

	public static ArrayList<Landmark> filterLandmarks(int regionId, int categoryId, int typeId, boolean includeNextNearestRegion){
		ArrayList<Landmark> landmarks = getAllLandmarks(); // трябва да взима всички забалежителности от базата

		if(includeNextNearestRegion){
			Region region = RepoRegions.findRegionById(regionId);
			Region nextNearestRegion = RepoRegions.getNextNearestRegion(region);
		}

		//филтрира забележителности по 3те критерия
		ArrayList<Landmark> filteredLandmarks = (ArrayList<Landmark>) landmarks.stream()
				.filter(landmark -> landmark.getRegionId() == regionId
						&& landmark.getCategoryId() == categoryId
						&& landmark.getTypeId() == typeId)
				.collect(Collectors.toList());

		return filteredLandmarks;
	}

	public static TripOffer setLandmarksInOfferByDistance(ArrayList<Landmark> filteredLandmarks, int tripLength, TripOffer tripOffer, double startingPointLatitude, double startingPointLongitude)
	{
		ArrayList<Landmark> temporaryLandmarks = (ArrayList<Landmark>) filteredLandmarks.clone();
		Landmark currentNearestLandmark = null;
		for (int i = 0; i < tripLength * 2; i++)
		{
			if(i == 0 && tripOffer.landmarks.size() == 0){
				// according to starting point
				Landmark nextNearestLandmark = RepoLandmarks.getNextNearestLandmark(startingPointLatitude, startingPointLongitude, temporaryLandmarks);
				tripOffer.landmarks.add(nextNearestLandmark);
				currentNearestLandmark = nextNearestLandmark;
			}else{
				temporaryLandmarks.remove(currentNearestLandmark);
				Landmark nextNearestLandmark = RepoLandmarks.getNextNearestLandmark(currentNearestLandmark, temporaryLandmarks);
				tripOffer.landmarks.add(nextNearestLandmark);
				currentNearestLandmark = nextNearestLandmark;
			}
		}

		return tripOffer;
	}

	public static TripOffer setRandomLandmarksInOffer(ArrayList<Landmark> filteredLandmarks, int tripLength, TripOffer tripOffer)
	{
		ArrayList<Landmark> temporaryLandmarks = (ArrayList<Landmark>) filteredLandmarks.clone();
		Random rand = new Random();

		for (int i = 0; i < tripLength * 2; i++)
		{
			Landmark randomLandmark = temporaryLandmarks.get(rand.nextInt(temporaryLandmarks.size()));
			temporaryLandmarks.remove(randomLandmark);
			tripOffer.landmarks.add(randomLandmark);
		}

		return tripOffer;
	}

	public static TripOffer setLandmarksFilteredByCategoryAndRegion(int tripLength, int categoryId, int regionId, TripOffer tripOffer)
	{
		Random rand = new Random();
		//ArrayList<Integer> filteredTypes = (ArrayList<Integer>) getAllTypeIds().stream().filter(type -> type != typeId).collect(Collectors.toList());

		//int randomTypeId = filteredTypes.get(rand.nextInt(filteredTypes.size()));

		ArrayList<Landmark> landmarks = getAllLandmarks(); // трябва да взима всички забалежителности от базата

		ArrayList<Landmark> filteredLandmarks = (ArrayList<Landmark>) landmarks.stream()
				.filter(landmark -> landmark.getRegionId() == regionId
						&& landmark.getCategoryId() == categoryId)
				.collect(Collectors.toList());

		if(filteredLandmarks.size() >= tripLength * 2){
			for (int i = 0; i < tripLength * 2; i++)
			{
				Landmark randomLandmark = filteredLandmarks.get(rand.nextInt(filteredLandmarks.size()));
				filteredLandmarks.remove(randomLandmark);
				tripOffer.landmarks.add(randomLandmark);
			}
		}
		else{
			if(filteredLandmarks.size() >= tripLength){
				for (int i = 0; i < tripLength; i++)
				{
					Landmark randomLandmark = filteredLandmarks.get(rand.nextInt(filteredLandmarks.size()));
					filteredLandmarks.remove(randomLandmark);
					tripOffer.landmarks.add(randomLandmark);
				}
			}
		}

		return tripOffer;
	}
}
