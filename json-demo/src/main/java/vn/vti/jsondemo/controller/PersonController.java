package vn.vti.jsondemo.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import vn.vti.jsondemo.model.Person;
import vn.vti.jsondemo.repositories.PersonRespository;

import javax.naming.Binding;
import java.util.List;

@Controller
@RequestMapping("/")

public class PersonController {
    private final PersonRespository repo;

    public PersonController(PersonRespository repo) {
        this.repo = repo;
    }
    @GetMapping
    public String home(){return "index";}
    @GetMapping("listAll")
    public String getAll(Model model, @RequestParam(value ="direction",required = false) String direction){
        if(direction==null){
            model.addAttribute("persons",repo.list());
        }else{
            List<Person> persons = repo.sortBy(direction);
            model.addAttribute("persons", persons );
        }
        return "list";
    }
    @GetMapping("/search")
    public String search(HttpServletRequest request,Model model){
        String job = request.getParameter("job");
        if(job==""){
            model.addAttribute("persons" , repo.list());
            return "redirect:/listAll";
        }else{
            model.addAttribute("persons" , repo.findByJob(job));
            return "list";
        }
    }
    @GetMapping("/create")
    public String createCustomer(Model model){
        model.addAttribute("person" , new Person());
        return "PersonForm";
    }
    @PostMapping("/post")
    public String postInfo( @ModelAttribute("person") Person person, BindingResult result, Model model){
        if (!result.hasErrors()) {
            if(person.getId()!=null){
                repo.updatePerson(person.getId(), person);
            }else{
                repo.savePerson(person);
            }
            model.addAttribute("Persons", repo.list());
            return "redirect:/listAll";
        }
        return "PersonForm";
    }
    @GetMapping("/edit/{id}")
    public String editPerson(@PathVariable("id") Integer id , Model model){
        Person person = repo.findById(id);
        model.addAttribute("person", person);
        return "PersonForm";
    }
    @GetMapping("/deletete/{id}")
    public String deletePerson(@PathVariable("id") Integer id , Model model){
        repo.deletePerson(id);
        model.addAttribute("customers",repo.list());
        return "redirect:/listAll";
    }
}
