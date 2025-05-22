package com.repaso_prueba;

import com.repaso_prueba.entities.Course;
import com.repaso_prueba.entities.Enrollment;
import com.repaso_prueba.entities.Student;
import com.repaso_prueba.services.CourseService;
import com.repaso_prueba.services.EnrollmentSernvice;
import com.repaso_prueba.services.StudentService;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Repaso_Prueba {

    public static void main(String[] args) throws Exception {
        
        StudentService service = new StudentService();  
        
        // Obtener todos
        //System.out.println("Todos: " + service.getAll());
        
        // Obtener uno
        //System.out.println("Listar todos: " + service.getById(2L).toString());
        
        // Crear 
        //Student crear = new Student("David", "dd@gmail.com");
        //System.out.println("Crear: " + service.create(crear).toString());
        
        // Actualizar
        //Student actualizar = new Student("Manja", "dd@gmail.com");
        //System.out.println("Actualizar: " + service.update(2L, actualizar).toString());
        
        // Eliminar
        // System.out.println("Eliminar: " + service.delete(2L));
        
        CourseService courseService = new CourseService();
        
        // Obtener todos
        //System.out.println("Todos: " + courseService.getAll());
        
        // Obtener uno
        //System.out.println("Uno: " + courseService.getById(1L));
        
        // Crear
        //Course courseNuevo = new Course("Fisica", "Fisica avanzada");
        //System.out.println("Creado: " + courseService.create(courseNuevo));
        
        // Actualiar
        //Course courseActualizar = new Course("Fisica avanzada", "Fisica avanzada");
        //System.out.println("Actualizar: " + courseService.update(2L, courseActualizar));
        
        // Eliminar
        //System.out.println("Eliminar: " + courseService.delete(2L));
        
        // Obtener con relaciones
        //System.out.println("Estudiante con relaciones: " + service.withEnrollments(1L).toString());
        //System.out.println("Curso con relaciones: " + courseService.withEnrollments(1L).toString());
        
        // Servicio de inscripciones
        EnrollmentSernvice enrollmentSernvice = new EnrollmentSernvice();
        
        // Obtener todos
        //System.out.println("Todos enrollments: " + enrollmentSernvice.getAll().toString());
        
        // Obtener uno
        //System.out.println("Uno: " + enrollmentSernvice.getById(1L).toString());
        
        // Crear
        //Enrollment crear = new Enrollment(1L, 1L, 60);
        //System.out.println("Crear: " + enrollmentSernvice.create(crear));
        
        // Actualizar
        //Enrollment actualizarEn = new Enrollment(1L, 1L, 90);
        //System.out.println("Actualizar: " + enrollmentSernvice.update(4L, actualizarEn));
    
        // Delete
        System.out.println("Eliminar: " + enrollmentSernvice.delete(4L));
    }
}
