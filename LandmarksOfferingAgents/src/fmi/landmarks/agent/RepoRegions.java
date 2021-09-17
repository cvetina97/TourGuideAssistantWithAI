package fmi.landmarks.agent;

import java.util.ArrayList;

public final class RepoRegions {
    private RepoRegions() {};

    public static ArrayList<Region> regions;

    public static ArrayList<Region> getAllRegions(){
        setAllRegions();
        return regions;
    }

    private static void setAllRegions() {
        regions = new ArrayList<Region>();

        regions.add(new Region(1, "Blagoevgrad", 42.0326763804487, 23.093572152287404));
        regions.add(new Region(2, "Burgas", 42.571114080133995, 27.464751899539618));
        regions.add(new Region(3, "Varna", 43.316528845634245, 27.903594692121704));
        regions.add(new Region(4, "Veliko Tarnovo", 43.12823255790894, 25.620703459109116));
        regions.add(new Region(5, "Vidin", 44.01344404651409, 22.86669444514404));
        regions.add(new Region(6, "Vratsa", 43.253383554234716, 23.555056093499438));
        regions.add(new Region(7, "Gabrovo", 42.918564055125735, 25.31634715729123));
        regions.add(new Region(8, "Dobrich", 43.5968059367433, 27.83160327184336));
        regions.add(new Region(9, "Kardjali", 41.671462549705836, 25.376615971665693));
        regions.add(new Region(10, "Kiustendil", 42.298326181447955, 22.692494163555));
        regions.add(new Region(11, "Lovech", 43.1541385366181, 24.710935255830027));
        regions.add(new Region(12, "Montana", 43.42777872297589, 23.223109342783967));
        regions.add(new Region(13, "Pazardzhik", 42.22344126434294, 24.33620813738957));
        regions.add(new Region(14, "Pernik", 42.66034337522531, 23.030790000066307));
        regions.add(new Region(15, "Pleven", 43.435730951207795, 24.60571445864302));
        regions.add(new Region(16, "Plovdiv", 42.1846735059031, 24.75485762421519));
        regions.add(new Region(17, "Razgrad", 43.55755290016016, 26.546251911316332));
        regions.add(new Region(18, "Ruse", 43.87249546167672, 25.973534450336565));
        regions.add(new Region(19, "Silistra", 44.124732351649115, 27.264001691677883));
        regions.add(new Region(20, "Sliven", 42.7160139128065, 26.329051853746076));
        regions.add(new Region(21, "Smolqn", 41.599297547068716, 24.70670760833514));
        regions.add(new Region(22, "Sofiq", 43.01974098430005, 23.875415127719847));
        regions.add(new Region(23, "Stara Zagora", 42.44675317492967, 25.637448039903553));
        regions.add(new Region(24, "Targovishte", 43.26877642454048, 26.575079512173488));
        regions.add(new Region(25, "Haskovo", 41.958403590093, 25.557994068032606));
        regions.add(new Region(26, "Shumen", 43.32331090404331, 26.947678199418174));
        regions.add(new Region(27, "Qmbol", 42.505113039740195, 26.503182080617613));
    }

    public static double rad(double x) {
        return x * Math.PI / 180;
    }

    public static Region getNextNearestRegion(Region currentRegion) {
        double minNearestDistance = Double.MIN_VALUE;
        Region nearestRegion = null;

        for (Region region : regions)
        {
            int R = 6378137; // Earth’s mean radius in meter
            double distanceLat = rad(region.getLatitude() - currentRegion.getLatitude());
            double distanceLong = rad(region.getLongitude() - currentRegion.getLongitude());
            double a = Math.sin(distanceLat / 2) * Math.sin(distanceLat / 2) + Math.cos(rad(currentRegion.getLatitude())) * Math.cos(rad(region.getLatitude())) * Math.sin(distanceLong / 2) * Math.sin(distanceLong / 2);
            double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
            double distance = R * c;
            double currentDistanceInKm = distance/1000;

            if(minNearestDistance > currentDistanceInKm){
                minNearestDistance = currentDistanceInKm;
                nearestRegion = region;
            }
        }

        return nearestRegion;
    }

    public static Region findRegionById(int regionId){
        return regions.get(regionId);
    }
}
