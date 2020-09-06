package ar.edu.iua.rest;
import ar.edu.iua.business.IProductoBusiness;
import ar.edu.iua.business.exception.BusinessException;
import ar.edu.iua.business.exception.NotFoundException;
import ar.edu.iua.model.Producto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/*Se les llama controller por que mapean dos mundos,
 *  es decir por ejemplo los controladores rest mapean las url a codigo de servicio rest
 *  ej: URL--> http://localhost:8080/api/v1/productos/1* --> iProductoBussines.load(1)
 *  mapea la url con una funcion java*/

@RestController
@RequestMapping(value=constantes.URL_PRODUCTOS)
public class ProductoRestController {
	
	@Autowired
	private IProductoBusiness productoBusiness;

	//Aca le decimos que la ultima parte de la url es variable y lo que devuelve es un formato json
	@GetMapping(value="/{id}", produces= MediaType.APPLICATION_JSON_VALUE) 
	public ResponseEntity<Producto> load(@PathVariable("id")Long id){ //el @PathVariable asocia la parte de la url variable con una variable java
		try {
			return new ResponseEntity<Producto>(productoBusiness.load(id),HttpStatus.OK);//se devuelve un 200 si esta ok
		} catch (BusinessException e) {
			return new ResponseEntity<Producto>(HttpStatus.INTERNAL_SERVER_ERROR);// se devulve un 500 si hay un error
		} catch (NotFoundException e) {
			return new ResponseEntity<Producto>(HttpStatus.NOT_FOUND);// se devulve un 404 si hay un error
		} 
	}
	
	
	//Devolver por descripcion
		@GetMapping(value="/description", produces= MediaType.APPLICATION_JSON_VALUE) 
		public ResponseEntity<Producto> loadByDescription(@RequestParam("desc")String desc){ 
			try {
				return new ResponseEntity<Producto>(productoBusiness.findByDescripcion(desc),HttpStatus.OK);//se devuelve un 200 si esta ok
			} catch (BusinessException e) {
				return new ResponseEntity<Producto>(HttpStatus.INTERNAL_SERVER_ERROR);// se devulve un 500 si hay un error
			} catch (NotFoundException e) {
				return new ResponseEntity<Producto>(HttpStatus.NOT_FOUND);// se devulve un 404 si hay un error
			} 
		}
}
