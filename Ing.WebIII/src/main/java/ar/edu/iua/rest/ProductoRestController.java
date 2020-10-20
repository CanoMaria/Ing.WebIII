package ar.edu.iua.rest;
import ar.edu.iua.business.IProductoBusiness;
import ar.edu.iua.business.exception.BusinessException;
import ar.edu.iua.business.exception.NotFoundException;
import ar.edu.iua.model.Producto;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/*Se les llama controller por que mapean dos mundos,
 *  es decir por ejemplo los controladores rest mapean las url a codigo de servicio rest
 *  ej: URL--> http://localhost:8080/api/v1/productos/1* --> iProductoBussines.load(1)
 *  mapea la url con una funcion java*/

@RestController
@RequestMapping(value=Constantes.URL_PRODUCTOS)
public class ProductoRestController {
	
	private Logger log=LoggerFactory.getLogger(this.getClass());
	@Autowired
	private IProductoBusiness productoBusiness;

	//Aca le decimos que la ultima parte de la url es variable y lo que devuelve es un formato json
	@GetMapping(value="/{id}", produces= MediaType.APPLICATION_JSON_VALUE) 
	public ResponseEntity<Producto> load(@PathVariable("id")Long id){ //el @PathVariable asocia la parte de la url variable con una variable java
		try {
			return new ResponseEntity<Producto>(productoBusiness.load(id),HttpStatus.OK);//se devuelve un 200 si esta ok
		} catch (BusinessException e) {
			log.error(e.getMessage(),e);
			return new ResponseEntity<Producto>(HttpStatus.INTERNAL_SERVER_ERROR);// se devulve un 500 si hay un error
		} catch (NotFoundException e) {
			return new ResponseEntity<Producto>(HttpStatus.NOT_FOUND);// se devulve un 404 si hay un error
		} 
	}
	//listar todos los productos: curl "http://localhost:8080/api/v1/productos"
		@GetMapping(value="", produces= MediaType.APPLICATION_JSON_VALUE) 
		public ResponseEntity<List <Producto>> list(@RequestParam(name="parte",required=false,defaultValue ="*") String parte){ //el required=false esta diciendo que es un parametro opcional
			try {
				if(parte.equals("*")) {
					return new ResponseEntity<List <Producto>>(productoBusiness.list(),HttpStatus.OK);
				}else {
					return new ResponseEntity<List <Producto>>(productoBusiness.list(parte),HttpStatus.OK);
				}
				
			} catch (BusinessException e) {
				log.error(e.getMessage(),e);
				return new ResponseEntity<List <Producto>>(HttpStatus.INTERNAL_SERVER_ERROR);
			} 
		}
	//Guardar un producto:   curl -X POST "http://localhost:8080/api/v1/productos" -H "Content-Type: application/json" -d '{"nombre":"leche","descripcion":"rica","precioLista":9.0,"enStock":true}' -v
		@PostMapping(value="", produces= MediaType.APPLICATION_JSON_VALUE) 
		public ResponseEntity<String> add(@RequestBody Producto producto){ //se establece que se va a recibir el producto el cual viene en el body del request y no de la url como en el caso del id
			try {
				productoBusiness.add(producto); 
				HttpHeaders responseHeaders = new HttpHeaders(); // creo una cabecera que devolvere con los datos del producto
				responseHeaders.set("location", Constantes.URL_PRODUCTOS+"/"+ producto.getId()); // esta cabecera "location" contiene la url del producto y el id pero NO el producto
				return new ResponseEntity<String>(responseHeaders,HttpStatus.CREATED);
			} catch (BusinessException e) {
				log.error(e.getMessage(),e);
				return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
			} 
		}
	//Editar producto: curl -X PUT "http://localhost:8080/api/v1/productos" -H "Content-Type: application/json" -d '{"id":2,"nombre":"fideo","descripcion":"rico","precioLista":29.0,"enStock":true}' -v "
		@PutMapping(value="", produces= MediaType.APPLICATION_JSON_VALUE) 
		public ResponseEntity<String> update(@RequestBody Producto producto){ 
			try {
				productoBusiness.update(producto); 
				return new ResponseEntity<String>(HttpStatus.OK);
			} catch (BusinessException e) {
				log.error(e.getMessage(),e);
				return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
			}  catch (NotFoundException e) {
				return new ResponseEntity<String>(HttpStatus.NOT_FOUND);// 
			} 
		}
	//Borrar un producto: curl -X DELETE "http://localhost:8080/api/v1/productos/3" -v 
		@DeleteMapping(value="/{id}", produces= MediaType.APPLICATION_JSON_VALUE) 
		public ResponseEntity<String> update(@PathVariable("id")Long id){ 
			try {
				productoBusiness.delete(id);; 
				return new ResponseEntity<String>(HttpStatus.OK);
			} catch (BusinessException e) {
				log.error(e.getMessage(),e);
				return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
			}  catch (NotFoundException e) {
				return new ResponseEntity<String>(HttpStatus.NOT_FOUND);// 
			} 
		}
	//Devolver por descripcion:  curl "http://localhost:8080/api/v1/productos/description?desc=rica"
		@GetMapping(value="/description", produces= MediaType.APPLICATION_JSON_VALUE) 
		public ResponseEntity<Producto> loadByDescription(@RequestParam("desc")String desc){ 
			try {
				return new ResponseEntity<Producto>(productoBusiness.findByDescripcion(desc),HttpStatus.OK);//se devuelve un 200 si esta ok
			} catch (BusinessException e) {
				log.error(e.getMessage(),e);
				return new ResponseEntity<Producto>(HttpStatus.INTERNAL_SERVER_ERROR);// se devulve un 500 si hay un error
			} catch (NotFoundException e) {
				return new ResponseEntity<Producto>(HttpStatus.NOT_FOUND);// se devulve un 404 si hay un error
			} 
		}
	//Devolver por precio:  curl "http://localhost:8080/api/v1/productos/description?desc=rica"
		@GetMapping(value="/precio", produces= MediaType.APPLICATION_JSON_VALUE) 
		public ResponseEntity<Producto> loadByPrecio(@RequestParam("prec")Double prec){ 
			try {
				return new ResponseEntity<Producto>(productoBusiness.findByPrecioLista(prec),HttpStatus.OK);//se devuelve un 200 si esta ok
			} catch (BusinessException e) {
				log.error(e.getMessage(),e);
				return new ResponseEntity<Producto>(HttpStatus.INTERNAL_SERVER_ERROR);// se devulve un 500 si hay un error
			} catch (NotFoundException e) {
				return new ResponseEntity<Producto>(HttpStatus.NOT_FOUND);// se devulve un 404 si hay un error
			} 
		}
}
