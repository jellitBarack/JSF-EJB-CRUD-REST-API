/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany;



import EJB.StudentEJB;
import entity.Student;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;

import javax.ws.rs.core.MediaType;


/**
 * REST Web Service
 *
 * @author Sujan Maka
 */
@Path("student")
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GenericResource {

    @EJB
    private StudentEJB studentEJB;
    
    
    

    
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of GenericResource
     */
    public GenericResource() {
    }

  
    @GET
    public List<Student> getAllStudent() {
        return studentEJB.allStudents();
    }

    @POST
    public Student addStudent(Student student){
        return studentEJB.saveStudent(student);
        
    }
   
    @PUT
    @Path("/{sId}")
    public Student updateStudent(@PathParam("sId") long id, Student student) throws Exception{
        student.setId(id);
        return studentEJB.updateStudent(student);
    }

    @DELETE
    @Path("/{sId}")
    public void deleteMessage(@PathParam("sId") long id) throws Exception {
         studentEJB.deleteStudent(id);
    }
   
    @GET
    @Path("/{sId}")
    public Student getStudent(@PathParam("sId") long id) {
        return studentEJB.singleStudent(id);
    }
}
