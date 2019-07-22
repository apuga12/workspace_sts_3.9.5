package stark.industries.springboot.models.dao;

import org.springframework.data.repository.CrudRepository;

import stark.industries.springboot.models.entity.Producto;

public interface ProductoDao extends CrudRepository<Producto, Long>{

}
