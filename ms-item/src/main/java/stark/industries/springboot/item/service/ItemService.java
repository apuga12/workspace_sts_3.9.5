package stark.industries.springboot.item.service;

import java.util.List;

import stark.industries.springboot.item.models.Item;

public interface ItemService {
	
	public List<Item> findAll();
	public Item findById(Long id, Integer cantidad);
	

}
