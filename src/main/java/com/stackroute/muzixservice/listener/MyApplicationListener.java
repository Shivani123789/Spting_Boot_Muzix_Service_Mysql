package com.stackroute.muzixservice.listener;

import com.stackroute.muzixservice.domain.TrackDetails;
import com.stackroute.muzixservice.repository.MuzixRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:configure.properties")
public class MyApplicationListener implements CommandLineRunner {
    @Value("${track1.id}")
    private int id;
    @Value("${track1.name}")
    private String name;
    @Value("${track1.comment}")
    private String comment;

    TrackDetails music=new TrackDetails();
    @Autowired
    MuzixRepository muzixRepository;
    @Override
    public void run(String... args) throws Exception {
        music.setTrackId(id);
        music.setTrackName(name);
        music.setTrackComment(comment);
        muzixRepository.save(music);
    }
}