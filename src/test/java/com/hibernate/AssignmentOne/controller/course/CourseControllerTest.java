package com.hibernate.AssignmentOne.controller.course;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.hibernate.AssignmentOne.entities.course.Course;
import com.hibernate.AssignmentOne.service.course.CourseService;
import org.hibernate.annotations.Imported;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CourseController.class)
class CourseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CourseService courseService;

    Course course1;
    Course course2;
    List<Course> courseList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        course1 = new Course(1001L,"JavaScript");
        course2 = new Course(1002L,"Java");
        courseList.add(course1);
        courseList.add(course2);
    }

    @Nested
    class GetAllCourse {
        @Test
        @DisplayName("Positive Scenarios : testGetAllCourses_positiveScenarios()")
        void testGetAllCourses_positiveScenarios() throws Exception {
            when(courseService.getAllCourses())
                    .thenReturn(courseList);
            mockMvc.perform(get("/api/v1/courses"))
                    .andDo(print())
                    .andExpect(status().isOk());
        }

        @Test
        @DisplayName("Negative Scenarios : testGetAllCourses_negativeScenarios()")
        void testGetAllCourses_negativeScenarios() throws Exception {
            when(courseService.getAllCourses())
                    .thenReturn(null);
            mockMvc.perform(get("/api/v1/courses"))
                    .andDo(print())
                    .andExpect(status().isNoContent());
        }
    }

   @Nested
   class GetCourseById {
       @Test
       @DisplayName("Positive Scenarios : testGetCoursesById_positiveScenarios()")
       void testGetCourseById_positiveScenarios() throws Exception {
           when(courseService.getCourseById(1001L))
                   .thenReturn(course1);
           mockMvc.perform(get("/api/v1/courses/1001"))
                   .andDo(print())
                   .andExpect(status().isOk());
       }

       @Test
       @DisplayName("Negative Scenarios : testGetCoursesById_negativeScenarios")
       void testGetCourseById_negativeScenarios() throws Exception {
           when(courseService.getCourseById(1001L))
                   .thenReturn(null);
           mockMvc.perform(get("/api/v1/courses/1001"))
                   .andDo(print())
                   .andExpect(status().isNoContent());
       }
   }


    @Nested
    class AddCourse {
        @Test
        @DisplayName("Positive Scenarios : testAddCourse_positiveScenarios")
        void testAddCourse_positiveScenarios() throws Exception {
            String requestJson = CourseControllerTest.getJsonObjectString(course1);
            when(courseService.addCourse(course1)).thenReturn(course1);
            mockMvc.perform(post("/api/v1/courses")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(requestJson))
                            .andDo(print())
                            .andExpect(status().isCreated());
        }

        @Test
        @DisplayName("Negative Scenarios : testAddCourse_negativeScenarios")
        void testAddCourse_negativeScenarios() throws Exception {
            String requestJson = CourseControllerTest.getJsonObjectString(null);
            when(courseService.addCourse(course1)).thenReturn(null);
            mockMvc.perform(post("/api/v1/courses")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(requestJson))
                            .andDo(print())
                            .andExpect(status().isBadRequest());
        }
    }

    @Nested
    class DeleteCourse {
        @Test
        @DisplayName("Positive Scenarios : testDeleteCourseById_positiveScenarios")
        void testDeleteCourseById_positiveScenarios() throws Exception {
            when(courseService.deleteCourseById(1001L))
                    .thenReturn("------Item deleted Successfully-------");
            mockMvc.perform(delete("/api/v1/courses/1001")).andDo(print()).andExpect(status().isOk());
        }

        @Test
        @DisplayName("Negative Scenarios : testDeleteCourseById_negativeScenarios")
        void testDeleteCourseById_negativeScenarios() throws Exception {
            when(courseService.deleteCourseById(1003L)).thenReturn("------Wrong Id------");
            mockMvc.perform(delete("/api/v1/courses/1003")).andDo(print()).andExpect(status().isOk());
        }
    }

    private static String getJsonObjectString(Object course) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE,false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(course);
        return requestJson;
    }

}