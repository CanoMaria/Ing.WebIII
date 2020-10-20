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
	public Producto add(Producto producto) throws BusinessException;//se lo envia a la bd y lo retorna para que me de un id
	
	//----Editar un Producto------
	public Producto update(Producto producto) throws NotFoundException,BusinessException;
	
	//----Borrar por id-----------
	public void delete(Long id) throws NotFoundException,BusinessException;
	
	//----Devuelve un Producto de acuerdo a la Descripcion-------
	public Producto findByDescripcion(String descripcionProducto) throws NotFoundException,BusinessException;
	
	//----Devuelve un Producto de acuerdo a el precio -------
	public Producto findByPrecioLista(Double precio) throws NotFoundException,BusinessException;

	//----Devuelve un Producto deacuerd a el nombre o descripcion que le mandemos-------
	public List<Producto> list(String parte) throws BusinessException;
		

}
