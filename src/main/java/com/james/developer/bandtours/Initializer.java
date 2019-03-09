package com.james.developer.bandtours;


import com.james.developer.bandtours.model.Group;
import com.james.developer.bandtours.model.GroupRepository;
import com.james.developer.bandtours.model.Event;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Collections;
import java.util.stream.Stream;


@Component
class Initializer implements CommandLineRunner {

    private final GroupRepository repository;

    public Initializer(GroupRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... strings) {
        Stream.of("Atlantic City Tour", "Boston Tour", "Seattle Tour", "San Diego").forEach(name -> repository.save(new Group(name)));

        Group aTour = repository.findByName("Atlantic City Tour");
        Event e = Event.builder().title("Full Stack Reactive").description("Reactive with Spring Boot + React").date(Instant.parse("2018-12-12T18:00:00.000Z")).build();
        aTour.setEvents(Collections.singleton(e));

        repository.save(aTour);

        repository.findAll().forEach(System.out::println);
    }
}