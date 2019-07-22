package stark.industries.springboot.item.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import stark.industries.springboot.item.clientes.ClienteProductoFeignRest;
import stark.industries.springboot.item.models.Item;

@Service("serviceFeign")
public class ItemServiceFeign implements ItemService {

	@Autowired
	private ClienteProductoFeignRest clienteFeign;
	
	@Override
	public List<Item> findAll() {
		//return clienteFeign.listar();
		System.out.println("\n\n ****  ItemServiceFeign.findAll()  **** \n\n");
		return clienteFeign.listar().stream().map(p -> new Item(p,1)).collect(Collectors.toList());
	}

	@Override
	public Item findById(Long id, Integer cantidad) {
		System.out.println("\n\n ****  ItemServiceFeign.findById()  **** \n\n");
		return new Item( clienteFeign.detalle(id), cantidad);
	}

}
