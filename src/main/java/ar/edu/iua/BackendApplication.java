package ar.edu.iua;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.json.*;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
		
		JSONObject myObject = new JSONObject();

        // Cadenas de texto básicas
        myObject.put("name","Carlos");
        System.out.println(myObject.get("name"));
	}

}
