package fmi.landmarks.agent;

import java.util.UUID;

public class TripOfferToManipulate {
    private UUID id;
    private TripOfferManipulation tripOfferManipulation;
    private int numberOfLandmarksToManipulate;

    public TripOfferToManipulate(UUID id, TripOfferManipulation tripOfferManipulation, int numberOfLandmarksToManipulate) {
        this.id = id;
        this.tripOfferManipulation = tripOfferManipulation;
        this.numberOfLandmarksToManipulate = numberOfLandmarksToManipulate;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public TripOfferManipulation getTripOfferManipulation() {
        return tripOfferManipulation;
    }

    public void setTripOfferManipulation(TripOfferManipulation tripOfferManipulation) {
        this.tripOfferManipulation = tripOfferManipulation;
    }

    public int getNumberOfLandmarksToManipulate() {
        return numberOfLandmarksToManipulate;
    }

    public void setNumberOfLandmarksToManipulate(int numberOfLandmarksToManipulate) {
        this.numberOfLandmarksToManipulate = numberOfLandmarksToManipulate;
    }
}

enum TripOfferManipulation{
    AddLandmark,
    RemoveLandmark
}
