package vn.vti.jsondemo.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.vti.jsondemo.model.Person;
import vn.vti.jsondemo.repositories.PersonRespository;

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
    public String getALl(Model model, @RequestParam(value ="direction",required = false) String direction){
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
