package vn.vti.jsondemo.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    private Integer id;
    @NotBlank(message = "Ban phai nhap ten ")
    @Size(min=3,max=20,message = "Ten cua khon hop le tu 3 - 20 ki tu")
    private String name;
    @NotBlank(message = "Ban phai nhap email")
    private String email;
    @NotBlank(message = "Ban phai nhap email")
    private String job;
    @NotBlank(message = "Ban phai nhap email")
    private String gerder;
    @NotBlank(message = "Ban phai nhap email")
    private String city;
    private Integer salary;
    private String birthdate;
    private String photo;
}
