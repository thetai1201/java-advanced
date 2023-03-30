package vn.vti.jsondemo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.vti.jsondemo.model.Person;
import vn.vti.jsondemo.repositories.PersonRespository;

import java.util.List;

@RestController
@RequestMapping("/api/persons")

public class PersonControllerAPI {
    private final PersonRespository personRepo;

    public PersonControllerAPI(PersonRespository personRepo) {
        this.personRepo = personRepo;
    }
    @GetMapping
    public ResponseEntity<List<Person>> getAll(){return ResponseEntity.ok(personRepo.list());}
    @GetMapping
    public ResponseEntity<?> savePerson(@RequestBody Person person){
        personRepo.savePerson(person);
        return ResponseEntity.ok(person);
    }

    @GetMapping("/{salary}")
    public ResponseEntity<?> getBySalary(@PathVariable("salary") Integer salary){
        return ResponseEntity.ok(personRepo.findBySalary(salary));
    }

    @GetMapping("/job")
    public ResponseEntity<?> getByJob(@RequestParam("value") String value){
        return new ResponseEntity<>(personRepo.findByJob(value), HttpStatus.OK);
    }

    @GetMapping("/sorting")
    public ResponseEntity<?> sortBySalary(@RequestParam("sorted") String sorted){
        return new ResponseEntity<>(personRepo.sortBy(sorted),HttpStatus.OK);
    }
    @GetMapping("/id")
    public ResponseEntity<?> deleteById(@PathVariable Integer id){
        personRepo.deletePerson(id);
        return ResponseEntity.ok("Delete success");
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updatePerson(@PathVariable Integer id ,@RequestBody Person person){
        return ResponseEntity.ok(personRepo.updatePerson(id , person));
    }

}
