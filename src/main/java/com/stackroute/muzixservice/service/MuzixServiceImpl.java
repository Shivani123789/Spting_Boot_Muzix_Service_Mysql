package com.stackroute.muzixservice.service;

import com.stackroute.muzixservice.domain.TrackDetails;
import com.stackroute.muzixservice.exception.TrackAlreadyExistsException;
import com.stackroute.muzixservice.exception.TrackNotFoundException;
import com.stackroute.muzixservice.repository.MuzixRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import java.util.List;
@CacheConfig(cacheNames = "muzix")
@Service
public class MuzixServiceImpl implements MuzixService {
    private MuzixRepository muzixRepository;
    @Autowired
    public MuzixServiceImpl(MuzixRepository muzixRepository)

    {
        this.muzixRepository=muzixRepository;
    }
    @Override
    public TrackDetails saveTrack(TrackDetails trackDetails)throws TrackAlreadyExistsException {
        if(muzixRepository.existsById(trackDetails.getTrackId()))
        {
            throw new TrackAlreadyExistsException("tack is not found");
        }
        TrackDetails savedTrack=muzixRepository.save(trackDetails);
        return savedTrack;
    }
    @Override
    public TrackDetails displayTrackById(int trackId)
    {
        return muzixRepository.findById(trackId).get();
    }

    @PostConstruct
    public void loadData()
    {
        muzixRepository.save(TrackDetails.builder().trackId(3).trackName("kal ho na ho").trackComment("very good").build());
        muzixRepository.save(TrackDetails.builder().trackId(4).trackName("kal ho ").trackComment("very very good").build());
    }
//    @Override
//    public TrackDetails displayTrackByName(String trackName)
//    {
//        return muzixRepository.displayTrackByName(trackName);
    //}

    @Override
    @Cacheable
    public List<TrackDetails> displayTrack() {
    return muzixRepository.findAll();
    }

    @Override
    public void removeTrack(int trackId) {
         muzixRepository.deleteById(trackId);
    }

    @Override
    @CachePut
    public TrackDetails updateTrackComments(TrackDetails trackDetails,int trackId) throws TrackNotFoundException {
        if(!muzixRepository.existsById(trackId))
        {
            throw new TrackNotFoundException("tack is not found");
        }
        TrackDetails trackDetails1=muzixRepository.findById(trackId).get();
        trackDetails1.setTrackComment(trackDetails.getTrackComment());
        return muzixRepository.save(trackDetails1);
    }

    public void simulatedelay(){
        try {
            Thread.sleep(3000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
