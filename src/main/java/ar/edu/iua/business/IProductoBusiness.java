package ar.edu.iua.business;

import java.util.List;

import ar.edu.iua.business.exception.BusinessException;
import ar.edu.iua.business.exception.NotFoundException;
import ar.edu.iua.model.Producto;

// Especifican los servicios que va a prestar
public interface IProductoBusiness {

	//-----Cargamos un producto-----
	public Producto load(Long id) throws NotFoundException, BusinessException;
	
	//-----Listamos los productos-----
	public List<Producto> list() throws BusinessException;
	
	//----Guardar un Producto------
	public Producto safe(Producto producto) throws BusinessException;//se lo envia a la bd y lo retorna para que me de un id
	//----Borrar por id-----------
	public void delete(Long id) throws NotFoundException,BusinessException;
	//----Devuelve un Producto-------
	public Producto findByDescripcion(String descripcionProducto) throws NotFoundException,BusinessException;
}
