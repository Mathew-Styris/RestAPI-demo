package com.webapp.tyrrest;


import java.util.List;

import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

@Path("employees")
public class EmployeeResource {
	
	EmployeeRepo repo = new EmployeeRepo();
	
	
	//@GET
	//@Produces(MediaType.APPLICATION_JSON)
	//public List<Employee> getEmployees() {
		
	//	return repo.getEmployees();
		
	//}
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Employee getEmployee(@PathParam("id") int id) {
		return repo.getEmployee(id);
	}
	
	
	
	
	@POST
    public Employee createEmployee(Employee e1) {
		System.out.println(e1);
		repo.create(e1);
		return e1;
	}
	
 
	
	@PUT
    public Employee updateEmployee(Employee e1) {
		System.out.println(e1);
		if(repo.getEmployee(e1.getId()).getId()==null) {
			repo.create(e1);
		}
		repo.update(e1);
		
		return e1;
	}
	@PUT
	@Path("/{id}")
    public Employee updateEmployees(Employee e1,@PathParam("id") int id) {
		System.out.println(e1);
		if(repo.getEmployee(e1.getId()).getId()==null) {
			repo.create(e1);
		}
		repo.update(e1);
		
		return e1;
	}
	
	@DELETE
	@Path("/{id}")
	public Employee DeleteEmployee(@PathParam("id") int id) {
		Employee e=repo.getEmployee(id);
		if(e.getId()!=0)
			repo.delete(id);
		return e;
	}
	/*
	  @DELETE 
	  public Employee DeleteEmployee
	 */
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Employee> getEmployees(
	        @QueryParam("sort") String order,
	        @QueryParam("fields") String fields,
	        @QueryParam("gt")int gt,
	        @QueryParam("lt")int lt,
	        @QueryParam("id")int id,
	        @QueryParam("name") String n) {
	    
	    List<Employee> employees;                                                                                  

	    if ("desc".equalsIgnoreCase(order)) {
	        if ("id".equalsIgnoreCase(fields)) {
	            employees = repo.getEmployeesId(gt,lt,"DESC");
	        } else if ("name".equalsIgnoreCase(fields)) {
	            employees = repo.getEmployeesName(gt,lt ,"DESC");
	        } else {
	            employees = repo.getEmployees(gt,lt,n,"DESC");
	        }
	    } else {
	        if ("id".equalsIgnoreCase(fields)) {
	            employees = repo.getEmployeesId(gt, lt,"");
	        } else if ("name".equalsIgnoreCase(fields)) {
	                 employees = repo.getEmployeesName(gt,lt , "");
	        } else {
	            employees = repo.getEmployees(gt,lt,n, "");
	        }
	    }
	    
	    if(id>0)
	    	employees=repo.getEmployeed(id);

	    return employees;
	}
	



	
	//if using orderBy and orderType uncomment the below lines of code
	
	/*@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Employee> getEmployees(
	        @QueryParam("orderBy") String orderBy,
	        @QueryParam("orderType") String orderType) {

	    List<Employee> employees;

	    if ("id".equalsIgnoreCase(orderBy)) {
	        if ("desc".equalsIgnoreCase(orderType)) {
	            employees = repo.getEmployeesDesc(); }
	         else {
	            employees = repo.getEmployees();
	        }
	    } else if ("name".equalsIgnoreCase(orderBy)) {
	        if ("desc".equalsIgnoreCase(orderType)) {
	            employees = repo.getEmployeesDescName();
	        
	        } else {
	          
	            employees = repo.getEmployeesAsc();
	        }
	    } else {
	        
	        employees = repo.getEmployees();
	    }

	    return employees;
	}
*/

	
}
