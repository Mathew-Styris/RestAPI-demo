package com.webapp.tyrrest;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;


@Path("/")
public class MyResource {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */

    
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String getIt() {
      
    	String htmlResponse = "<html>"
    	        + "<head>"
    	        + "<style>"
    	        + "body {"
    	        + "    background-color: yellow;"
    	        + "    text-align: center;"
    	        + "    font-family: 'Amatic SC', cursive;"
    	        + "    font-size: 24px;"
    	        + "    margin-left: 150px;"
    	        + "    margin-right: 150px;"
    	        + "}"
    	        + "h1 {"
    	        + "    font-size: 60px;"
    	        + "}"
    	        + "p {"
    	        + "    font-size: 20px;"
    	        + "}"
    	        + "</style>"
    	        + "</head>"
    	        + "<body>"
    	        + "<h1>Welcome to Employee Database!</h1>"
    	        + "<p>View <a href=\"http://localhost:8080/tyrrest/employees\">Employee List</a>.</p>"
    	        + "<p style=\"font-size: 20px;\">Search Employee by ID</p>"
    	        + "<form>"
    	        + "<input id=\"id-input-text\" type=\"text\">"
    	        + "<input class=\"thmas-button\" type=\"button\" value=\"Search\" data-search-type=\"id\">"
    	        + "</form>"
    	        + "<p style=\"font-size: 20px;\">Search Employee by Name</p>"
    	        + "<form>"
    	        + "<input id=\"name-input-text\" type=\"text\">"
    	        + "<input class=\"thmas-button\" type=\"button\" value=\"Search\" data-search-type=\"name\">"
    	        + "</form>"
    	        + "<p style=\"font-size:12px;\"><br><br><br><br><br><br><a href=\"http://localhost:8080/tyrrest/employees?fields=name\">Employee Name List</a></p>"
    	        + "<p style=\"font-size:12px;\"><a href=\"http://localhost:8080/tyrrest/employees?fields=id\">Employee Id List</a></p>"
    	        + "<script type=\"text/javascript\">"
    	        + "    const thmasButtons = document.querySelectorAll('.thmas-button');"
    	        + "    thmasButtons.forEach(button => {"
    	        + "        button.addEventListener('click', () => {"
    	        + "            const searchType = button.getAttribute('data-search-type');"
    	        + "            const inputValue = document.querySelector(`#${searchType}-input-text`).value;"
    	        + "            const URL = `http://localhost:8080/tyrrest/employees?${searchType}=${inputValue}`;"
    	        + "            const win = window.open(URL, '_blank');"
    	        + "        });"
    	        + "    });"
    	        + "</script>"
    	        + "</body>"
    	        + "</html>";



        return htmlResponse;
    }
}
