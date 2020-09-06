package ar.edu.iua.model.persistence;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.edu.iua.model.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto,Long>{
	
	Optional <Producto> findByDescripcion(String descripcionProducto);
	//Optional<Producto> findByPrecioLista(Double precioLista);
	
}
