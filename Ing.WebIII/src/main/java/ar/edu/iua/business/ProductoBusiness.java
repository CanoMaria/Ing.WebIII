package ar.edu.iua.business;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import ar.edu.iua.business.exception.BusinessException;
import ar.edu.iua.business.exception.NotFoundException;
import ar.edu.iua.model.Producto;
import ar.edu.iua.model.persistence.ProductoRepository;

@Service //hace candidato para ser instanciado
public class ProductoBusiness implements IProductoBusiness {
	
	@Autowired
	private ProductoRepository productoDAO; //llamo a la clase que conecta con la bd
	
	@Override
	public Producto load(Long id) throws NotFoundException, BusinessException {
		Optional<Producto> op;
		try {
			op=productoDAO.findById(id);
		}catch(Exception e){
			throw new BusinessException(e);
		}
		if(!op.isPresent()) {//en caso de que NO este presente
			throw new NotFoundException("El producto con id "+id+" no se encuentr en la bd"); 
		}
		return op.get();
	}

	@Override
	public List<Producto> list() throws BusinessException {
		try {
			return productoDAO.findAll();
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}

	@Override
	public Producto add(Producto producto) throws BusinessException {
		try {
			return productoDAO.save(producto);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}
	
	@Override
	public Producto update(Producto producto) throws NotFoundException, BusinessException {
		load(producto.getId()); // verifico que este el producto
		try {
			return productoDAO.save(producto);
			
		} catch (EmptyResultDataAccessException el ) {
			throw new NotFoundException(el);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}

	@Override
	public void delete(Long id) throws NotFoundException, BusinessException {
		try {
			productoDAO.deleteById(id);;
		} catch (EmptyResultDataAccessException el ) {
			throw new NotFoundException(el);
		} catch (Exception e) {
			throw new BusinessException(e);
		}

	}

	@Override
	public Producto findByDescripcion(String descripcionProducto) throws NotFoundException, BusinessException {
		Optional<Producto> op= null; //creo una veriable vacia que puede devolver un producto
		try {
		
			//log.info("Getting by Description"); DUDAAAA ACA EL LOG QUE ES ???
			op= productoDAO.findByDescripcion(descripcionProducto);
			
		} catch (Exception e) {
			throw new BusinessException(e);
		}
		if(!op.isPresent()) {
			throw new BusinessException("No se encuentra el producto con descripcion: "+ descripcionProducto);
		}
		return op.get();
	}
	

	@Override
	public List<Producto> list(String parte) throws BusinessException {
		try {
			return productoDAO.findByNombreContainingOrDescripcionContainingOrderByNombreDesc(parte,parte);// uno para nombre y otro para descripcion
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}

	@Override
	public Producto findByPrecioLista(Double precio) throws NotFoundException, BusinessException {
		Optional<Producto> op= null; //creo una veriable vacia que puede devolver un producto
		try {
		
			//log.info("Getting by Description"); DUDAAAA ACA EL LOG QUE ES ???
			op= productoDAO.findByPrecioLista(precio);
			
		} catch (Exception e) {
			throw new BusinessException(e);
		}
		if(!op.isPresent()) {
			throw new BusinessException("No se encuentra el producto con descripcion: "+ precio);
		}
		return op.get();
	}

	

}
