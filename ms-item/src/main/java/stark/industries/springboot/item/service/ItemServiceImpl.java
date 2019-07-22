package stark.industries.springboot.item.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import stark.industries.springboot.item.models.Item;
import stark.industries.springboot.item.models.Producto;

@Service("serviceRestTemplate")
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	private RestTemplate clienteRestTemplate;

	@Override
	public List<Item> findAll() {
		
		// Sin balanceador Ribbon => 
		//List<Producto> productos = Arrays.asList(clienteRestTemplate.getForObject("http://localhost:8001/listar", Producto[].class));
		
		// Con Ribbon
		List<Producto> productos = Arrays.asList(clienteRestTemplate.getForObject("http://servicio-productos/listar",
				Producto[].class));
		
		// Transformar ProductList en ItemList => Usar jdk 8, API Streams (prof funcional, exps lambda)
		// Usar MAP para cambiar cada elemento del objeto (a c/u aplicar una función Lambda
		return productos.stream().map(p -> new Item(p,1)).collect(Collectors.toList());
	}

	@Override
	public Item findById(Long id, Integer cantidad) {
		Map<String, String> pathVariables = new HashMap<String, String>();
		pathVariables.put("id", id.toString());
		Producto producto = clienteRestTemplate.getForObject("http://servicio-productos/ver/{id}",
				Producto.class, pathVariables);
		return new Item(producto, cantidad);
	}

}
