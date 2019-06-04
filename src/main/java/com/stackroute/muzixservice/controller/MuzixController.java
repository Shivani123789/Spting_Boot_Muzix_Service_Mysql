package com.stackroute.muzixservice.controller;

import com.stackroute.muzixservice.domain.TrackDetails;
import com.stackroute.muzixservice.exception.TrackAlreadyExistsException;
import com.stackroute.muzixservice.exception.TrackNotFoundException;
import com.stackroute.muzixservice.service.MuzixService;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1")
public class MuzixController {
 private MuzixService muzixService;
    public MuzixController(MuzixService muzixService) {
        this.muzixService = muzixService;
    }
    @PostMapping("/tracks")
    public ResponseEntity<?> saveTrack(@RequestBody TrackDetails trackDetails) throws TrackAlreadyExistsException {
        RequestEntity requestEntity;
        //    try {
        muzixService.saveTrack(trackDetails);
        return new ResponseEntity<String>("succefully created",HttpStatus.OK);

//         }catch (TrackAlreadyExistsException ex)
//         {
//             return new ResponseEntity<String>(ex.getMessage(),HttpStatus.CONFLICT);
//
//         }

    }
    @GetMapping("/tracks")
    public ResponseEntity<?> displayTracks()
    {
        List<TrackDetails> musicList=muzixService.displayTrack();
        return new ResponseEntity<List<TrackDetails>>(musicList,HttpStatus.OK);
    }
    @GetMapping("/tracks/{trackId}")
    public ResponseEntity<?> displayTracksById(@PathVariable int trackId)
    {
        TrackDetails musicList=muzixService.displayTrackById(trackId);
        return new ResponseEntity<TrackDetails>(musicList,HttpStatus.OK);
    }
//    @GetMapping("/tracks/{trackName}")
//    public ResponseEntity<?> displayTracksByName(@PathVariable String trackName)
//    {
//        TrackDetails musicList=muzixService.displayTrackByName(trackName);
//        return new ResponseEntity<TrackDetails>(musicList,HttpStatus.OK);
//    }
    @DeleteMapping("/tracks/{trackId}")
    public ResponseEntity<?> deleteTrack(@PathVariable int trackId)
    {
        muzixService.removeTrack(trackId);
        return new ResponseEntity<List<TrackDetails>>(muzixService.displayTrack(),HttpStatus.OK);
    }
    @PutMapping("/tracks/{trackId}")
    public ResponseEntity<?> updateTracks(@RequestBody TrackDetails trackDetails, @PathVariable int trackId) throws TrackNotFoundException {
        //  try{
        TrackDetails musicList=muzixService.updateTrackComments(trackDetails,trackId);
        return new ResponseEntity<TrackDetails>(musicList, HttpStatus.OK);}
//        catch (TrackNotFoundException ex)
//        {
//            return new ResponseEntity<String>(ex.getMessage(),HttpStatus.CONFLICT);
//        }
}
