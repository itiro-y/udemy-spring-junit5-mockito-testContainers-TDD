package br.com.ayrton;

import java.util.concurrent.atomic.AtomicLong;

public class PersonService implements IPersonService {
    @Override
    public Person createPerson(Person person) {
        long id = new AtomicLong().incrementAndGet();
        person.setId(id);

        if(person.getEmail() == null || person.getEmail().isEmpty()) {
            throw new IllegalArgumentException("Email is null or empty");
        }
        return person;
    }
}
