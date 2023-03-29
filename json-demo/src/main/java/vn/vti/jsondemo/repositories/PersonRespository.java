package vn.vti.jsondemo.repositories;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import vn.vti.jsondemo.model.Person;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;


@Component

public class PersonRespository {
    private List<Person> persons = new ArrayList<>();
    public PersonRespository(){
        try {
            File file = ResourceUtils.getFile("classpath:static/personsmall.json");
            ObjectMapper mapper = new ObjectMapper();
            persons.addAll(mapper.readValue(file, new TypeReference<List<Person>>(){}));
            persons.forEach(System.out::println);
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
    public List<Person> list(){return  persons;}
    public Person savePerson(Person person){
        int id;
        if(persons.isEmpty()){
            id = 1;
        }else {
            Person lastPerson = persons.get(persons.size()-1);
            id = lastPerson.getId() + 1;
        }
        person.setId(id);
        persons.add(person);
        return person;
    }
    public Person findBySalary(int salary){
        return persons.stream().filter(person -> person.getSalary()==salary).findFirst().orElse(null);

    }
    public List<Person> findByJob(String job){
        return persons.stream().filter(person -> person.getJob().equals(job)).collect(Collectors.toList());
    }
    public Person findById(Integer id){
        return persons.stream().filter(person -> person.getId()==id).findFirst().orElse(null);
    }
    public List<Person> sortBy(String sorting){
        if(sorting.equals("asc"))
            Collections.sort(persons, Comparator.comparing(Person::getSalary));
        else
            Collections.sort(persons,Comparator.comparing(Person::getSalary).reversed());
        return persons;
    }
    public void deletePerson(Integer id){
        persons = persons.stream().filter(person -> person.getId()!=id).collect(Collectors.toList());
    }
    public Person updatePerson(Integer id,Person newperson){
        Person person = persons.stream().filter(person1 -> person1.getId()==id).findFirst().orElse(null);
        if(person!=null){
            person.setName(newperson.getName());
            person.setEmail(newperson.getEmail());
            person.setJob(newperson.getJob());
            person.setGerder(newperson.getGerder());
            person.setCity(newperson.getCity());
            person.setSalary(newperson.getSalary());
            person.setBirthdate(newperson.getBirthdate());
            persons.set(persons.indexOf(person),person);
        }
        return person;
    }


}
