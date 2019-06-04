package com.stackroute.muzixservice.repository;


import com.stackroute.muzixservice.domain.TrackDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MuzixRepository extends JpaRepository<TrackDetails,Integer> {
//@Query("select u from TrackDetails u where u.trackName=:trackName")
//    public TrackDetails displayTrackByName(@Param("trackName") String trackName);
}
