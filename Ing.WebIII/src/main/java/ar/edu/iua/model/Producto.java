package ar.edu.iua.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/*Serializable= le dice a java que las instancias de los obj de la clase las almacene en la ram 
 * hasta que sean almacenadas en forma fisica */

@Entity //Indico que es una entidad-sirve para la persistencia-
@Table(name="productos")
public class Producto implements Serializable{

	private static final long serialVersionUID = 1L; 
	
	@Id 
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long id;
	
	@Column(length =100,nullable=false) //tamaño va a ser de 100 para nombre
	private String nombre;
	
	@Column(length =200)
	private String descripcion;
	
	private Double precioLista;
	
	@Column(columnDefinition= "TINYINT DEFAULT 0")
	private Boolean enStock;
	
	@OneToOne(cascade =  CascadeType.ALL)
	private ProductoDetalle productoDetalle;
	
	@ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "proveedor_id")
    private Proveedor proveedor;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Double getPrecioLista() {
		return precioLista;
	}
	public void setPrecioLista(Double precioLista) {
		this.precioLista = precioLista;
	}
	public Boolean getEnStock() {
		return enStock;
	}
	public void setEnStock(Boolean enStock) {
		this.enStock = enStock;
	}
	public ProductoDetalle getProductoDetalle() {
        return productoDetalle;
    }

    public void setProductoDetalle(ProductoDetalle productoDetalle) {
        this.productoDetalle = productoDetalle;
    }
    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

}
