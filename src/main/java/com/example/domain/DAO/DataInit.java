package com.example.domain.DAO;

import com.example.domain.entity.Person;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("dev")
@Component
public class DataInit implements ApplicationRunner {

    private PersonRepository personRepository;

    public DataInit(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        long count = personRepository.count();

        if (count == 0) {
            Person p1 = new Person("Walker,", 32,"male");
            Person p2 = new Person("Anderson",25,"female");
            personRepository.save(p1);
            personRepository.save(p2);
        }
    }
}