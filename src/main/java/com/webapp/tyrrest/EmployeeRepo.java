package com.webapp.tyrrest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



public class EmployeeRepo {
   Connection con = null;

   public EmployeeRepo() 
   {   
	  String url = "jdbc:mysql://localhost:3306/restdb";
	  String username = "root";
	  String password = "mysqlserver";
	  try {
		  Class.forName("com.mysql.jdbc.Driver");
		con  = DriverManager.getConnection(url,username,password);
	} catch (Exception e) {
		
		System.out.println(e);
	}		
   }
  
   
   public Employee getEmployee(int id) {
	   
	   String sql = "select * from employee where id="+id;
	   Employee e = new Employee();
	   try {
	   Statement st= con.createStatement();
	   ResultSet rs = st.executeQuery(sql);
	   if(rs.next()) {
		   e.setId(rs.getInt(1));
		   e.setName(rs.getString(2));	     
	   }
	   else {
           e.setId(404); 
           e.setName("Not Found");
       }
	   
	   }
	   catch(Exception a) {
		   System.out.println("Error");
	   }
	   return e;
	   
   }   
    public void create(Employee e1) 
    {
	   String sql = "insert into employee values (?,?)";
	   
	   try {
	   PreparedStatement st = con.prepareStatement(sql);
	  
		   st.setInt(1,e1.getId());
		   st.setString(2,e1.getName());
		   st.executeUpdate();
		   
	   }
	   catch(Exception a) {
		   System.out.println("Error");
	   }  
   }
    public void update(Employee e1) {
        String sql = "UPDATE employee SET name=? WHERE id=?";
        
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, e1.getName());
            st.setInt(2, e1.getId()); 

            st.executeUpdate();
        } catch (Exception ex) {
            System.out.println("Error updating employee: " + ex.getMessage());
        }
    }

   
public void delete(int id) {
	
	String sql = "delete from employee where id=?";
	   
	   try {
	   PreparedStatement st = con.prepareStatement(sql);
	  
		   
		   st.setInt(1,id);  
		   st.executeUpdate();
		   
		  

	   }
	   catch(Exception a) {
		   System.out.println("Error");
	   }
}

public List<Employee> getEmployeed(int id) {
	   List <Employee> employees = new ArrayList<>();
	   String sql = "select * from employee where id="+id;
	   Employee e = new Employee();
	   try {
	   Statement st= con.createStatement();
	   ResultSet rs = st.executeQuery(sql);
	   if(rs.next()) {   
		   e.setId(rs.getInt(1));
		   e.setName(rs.getString(2));	
		   employees.add(e);
		  
	   }
	   else {
           e.setId(404); 
           e.setName("Not Found");
           employees.add(e);
       }
	   
	   
	   }
	   catch(Exception a) {
		   System.out.println("Error");
	   }
	   return employees;
	   
} 


public List<Employee> getEmployees(int gt, int lt, String nameSearch, String order) {
    List<Employee> employees = new ArrayList<>();
    String sql = "SELECT * FROM employee WHERE 1=1 " +
                 (gt > 0 ? "AND id > ? " : "") +
                 (lt > 0 ? "AND id < ? " : "") +
                 (nameSearch != null && !nameSearch.isEmpty() ? "AND name LIKE ? " : "") +
                 "ORDER BY id " + order;

    try (PreparedStatement st = con.prepareStatement(sql)) {
        int i = 1;
        if (gt > 0) {
            st.setInt(i++, gt);
        }
        if (lt > 0) {
            st.setInt(i++, lt);
        }
        if (nameSearch != null && !nameSearch.isEmpty()) {
            st.setString(i++, "%" + nameSearch + "%");
        }
 
        try (ResultSet rs = st.executeQuery()) {
        	
            while (rs.next()) {
                Employee e = new Employee();
                e.setId(rs.getInt("id"));
                e.setName(rs.getString("name"));
                employees.add(e);
            }
            if(employees.isEmpty())
            {
            
       		    Employee e = new Employee();
       	           e.setId(404); 
       	           e.setName("Not Found");
       	           employees.add(e);
       	    
            }
        }
    } catch (Exception ex) {
        System.out.println("Error in query: " + ex.getMessage());
    }

    return employees;
}



 
public List<Employee> getEmployeesName(int gt, int lt ,String order) {
	   List<Employee> employees = new ArrayList<>();
	   String sql = "SELECT name FROM employee WHERE 1=1 " + 
            (gt > 0 ? "AND id > ? " : "") + 
            (lt > 0 ? "AND id < ? " : "") + 
            "ORDER BY name " + order;

	    try (PreparedStatement st = con.prepareStatement(sql)) {
	    	int i=1;
	    	if (gt > 0) {
	            st.setInt(i++, gt);
	      }
	        if (lt > 0) {
	            st.setInt(i, lt);
	         }
	       
	        try (ResultSet rs = st.executeQuery()) {
	        
	            while (rs.next()) {
	                Employee e = new Employee();
	                e.setId(null);
	                e.setName(rs.getString("name"));
	                employees.add(e); 
	            }
	            if(employees.isEmpty())
	            {
	            
	       		    Employee e = new Employee();
	       	           e.setId(404); 
	       	           e.setName("Not Found");
	       	           employees.add(e);
	       	    
	            }
	        }
	    } catch (Exception ex) {
	        System.out.println("Error in query: " + ex.getMessage());
	    }
	    
	    return employees;
}


public List<Employee> getEmployeesId(int gt, int lt , String order) {
    List<Employee> employees = new ArrayList<>();
    String sql = "SELECT id FROM employee WHERE 1=1 " + 
                 (gt > 0 ? "AND id > ? " : "") + 
                 (lt > 0 ? "AND id < ? " : "") + 
                 "ORDER BY id " + order;

    try (PreparedStatement st = con.prepareStatement(sql)) { 
    	int i=1;
       if (gt > 0) {
            st.setInt(i++, gt);
      }
        if (lt > 0) {
            st.setInt(i, lt);
         }
        try (ResultSet rs = st.executeQuery()) {
        
            while (rs.next()) {
                Employee e = new Employee();
                e.setId(rs.getInt("id"));
                employees.add(e);
            }
            if(employees.isEmpty())
            {
            
       		    Employee e = new Employee();
       	           e.setId(404); 
       	           e.setName("Not Found");
       	           employees.add(e);
       	    
            }
        }
    } catch (Exception ex) {
        System.out.println("Query Error: " + ex.getMessage());
    }
    return employees;
}


}





   















