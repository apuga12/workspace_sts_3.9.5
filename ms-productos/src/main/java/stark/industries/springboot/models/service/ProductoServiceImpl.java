package stark.industries.springboot.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import stark.industries.springboot.models.dao.ProductoDao;
import stark.industries.springboot.models.entity.Producto;

@Service
public class ProductoServiceImpl implements IProductoService {
	
	@Autowired
	private ProductoDao productoDao;

	@Override
	@Transactional(readOnly=true)
	public List<Producto> findAll() {
		return (List<Producto> )productoDao.findAll();
	}

	@Override
	@Transactional(readOnly=true)
	public Producto findById(Long id) {
		return productoDao.findById(id).orElse(null);
	}

}
