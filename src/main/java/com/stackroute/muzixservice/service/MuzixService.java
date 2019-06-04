package com.stackroute.muzixservice.service;

import com.stackroute.muzixservice.domain.TrackDetails;
import com.stackroute.muzixservice.exception.TrackAlreadyExistsException;
import com.stackroute.muzixservice.exception.TrackNotFoundException;

import java.util.List;

public interface MuzixService {
    public TrackDetails saveTrack (TrackDetails trackDetails) throws TrackAlreadyExistsException;
    public List<TrackDetails> displayTrack();
    public void removeTrack(int trackId);
    public TrackDetails displayTrackById(int trackId);
   // public TrackDetails displayTrackByName(String trackName);
    public TrackDetails updateTrackComments(TrackDetails trackDetails,int trackId) throws TrackNotFoundException;

}
