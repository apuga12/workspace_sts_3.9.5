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
		System.out.println("\n\n *********** productos.listar/  *********  \n\n");
		// Agregar al vuelo el puerto en el flujo del response
		return productoService.findAll().stream().map(producto -> {
			//producto.setPort(Integer.parseInt(env.getProperty("local.server.port")));
			producto.setPort(port);
			return producto;
		}).collect(Collectors.toList());
	}
	
	@GetMapping("/ver/{id}")
	public Producto detalle(@PathVariable Long id) throws Exception{
		System.out.println("\n\n *********** productos.ver/id  *********  \n\n");
		Producto producto = productoService.findById(id);
		//producto.setPort(Integer.parseInt(env.getProperty("local.server.port")));
		producto.setPort(port);
		
//		// Para probar Hystrix: 1. CircuitBreakes
//		boolean ok = false;
//		if(ok == false) {
//			throw new Exception("No se pudo cargar el producto");
//		}
		
//		// Para probar Hystrix: 2. Venciendo el timeout
//		Thread.sleep(5000L);
		
		return producto;
	}

}
