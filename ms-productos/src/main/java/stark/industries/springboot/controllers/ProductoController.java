package stark.industries.springboot.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import stark.industries.springboot.models.entity.Producto;
import stark.industries.springboot.models.service.IProductoService;

@RestController
public class ProductoController {
	
	// Obtener valores del properties:
	//@ Autowired
	//private Environment env;
	
	@Value("${server.port}")
	private Integer port;
	
	@Autowired
	private IProductoService productoService;
	
	@GetMapping("/listar")
	public List<Producto> listar(){
		//return productoService.findAll();
		// Agregar al vuelo el puerto en el flujo del response
		return productoService.findAll().stream().map(producto -> {
			//producto.setPort(Integer.parseInt(env.getProperty("local.server.port")));
			producto.setPort(port);
			return producto;
		}).collect(Collectors.toList());
	}
	
	@GetMapping("/ver/{id}")
	public Producto detalle(@PathVariable Long id){
		Producto producto = productoService.findById(id);
		//producto.setPort(Integer.parseInt(env.getProperty("local.server.port")));
		producto.setPort(port);
		return producto;
	}

}
