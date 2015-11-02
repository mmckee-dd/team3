package application.spring.model;

import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import application.spring.boot.CourseList;

@RestController
@RequestMapping("/courses")
public class CoursesController {

    private CourseList courses;

    @Resource(name = "courses")
    public void setCourseList(CourseList courses) {
        this.courses = courses;
    }

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public Collection<Course> viewAll() {
        return courses.list();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = "application/json")
    public Course addOrUpdate(@RequestBody Course course) {
        courses.replace(course);
        return course;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public Course getById(@PathVariable("id") String id) {
        return courses.get(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteById(@PathVariable("id") String id) {
        courses.delete(id);
    }
}
