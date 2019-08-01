package stark.industries.springboot.item.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import stark.industries.springboot.item.models.Item;
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
	
	@GetMapping("/ver/{id}/cantidad/{cantidad}")
	public Item detalle(@PathVariable Long id, 
			@PathVariable Integer cantidad) {
		System.out.println("\n\n *********** item.ver/cantidad/  *********  \n\n");
		return itemService.findById(id, cantidad);
	}

}
