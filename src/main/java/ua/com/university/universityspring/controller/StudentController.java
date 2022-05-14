package ua.com.university.universityspring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.com.university.universityspring.entity.Student;
import ua.com.university.universityspring.service.StudentService;

import java.util.Optional;

@Controller
@RequestMapping("/students")
public record StudentController(StudentService studentService) {

    @GetMapping
    public String listStudents(Model model) {
        Iterable<Student> theStudents = studentService.findAll();
        model.addAttribute("students", theStudents);
        return "/students/list_students";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model) {
        Student student = new Student();
        model.addAttribute("student", student);
        return "/students/student_form";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@ModelAttribute("studentId") Long theId, Model model) {

        Optional<Student> student = studentService.findById(theId);
        if (student.isPresent()) {
            model.addAttribute("student", student);
            return "redirect:/student";
        }
        //TODO replace error
        return "Bed Student_Id";
    }

    @PostMapping("/save")
    public String saveStudent(@ModelAttribute("student") Optional<Student> student) {
        studentService.save(student);
        return "redirect:/students/list_students";
    }

    @GetMapping("/search")
    public String editStudentForm(@ModelAttribute("student") String theName, Model model) {
        Iterable<Student> students = studentService.searchBy(theName);
        model.addAttribute("students", students);
        return "redirect:/students/list_students";
    }

    @GetMapping("/delete")
    public String updateStudent(@ModelAttribute("student") Long theId) {
        studentService.delete(theId);
        return "redirect:/students/list_students";
    }
}
