package stark.industries.springboot.models.service;

import java.util.List;

import stark.industries.springboot.models.entity.Producto;

public interface IProductoService {
	
	public List<Producto> findAll();
	public Producto findById(Long id);

}
