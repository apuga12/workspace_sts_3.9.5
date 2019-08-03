package stark.industries.springboot.item.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import stark.industries.springboot.item.models.Item;
import stark.industries.springboot.item.models.Producto;
import stark.industries.springboot.item.service.ItemService;

@RestController
public class ItemController {
	
	@Autowired
	@Qualifier("serviceFeign")  // Alternar Feign
	//@ Qualifier("serviceRestTemplate")
	private ItemService itemService;
	
	@GetMapping("/listar")
	public List<Item> listar(){
		System.out.println("\n\n *********** item.listar/  *********  \n\n");
		return itemService.findAll();
	}
	
	// Ejemplo de Hystrix => CircuitBreaker para que no caiga en la exception
	@HystrixCommand(fallbackMethod = "detalleAlternativo")
	@GetMapping("/ver/{id}/cantidad/{cantidad}")
	public Item detalle(@PathVariable Long id, 
			@PathVariable Integer cantidad) {
		System.out.println("\n\n *********** item.ver/cantidad/  *********  \n\n");
		return itemService.findById(id, cantidad);
	}
	
	// Implementando detalleAlternativo() para que ingrese a CircuitBreaker
	public Item detalleAlternativo(Long id, Integer cantidad) {
		Item item = new Item();
		Producto prod = new Producto();
		
		item.setCantidad(cantidad);
		prod.setId(id);
		prod.setNombre("Producto Así es");
		prod.setPrecio(400.00);
		item.setProducto(prod);
		return item;
	}

}
