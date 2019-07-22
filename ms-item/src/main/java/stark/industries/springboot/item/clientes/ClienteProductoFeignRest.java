package stark.industries.springboot.item.clientes;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import stark.industries.springboot.item.models.Producto;

// Sin el balanceador Ribbon (está clavado el servicio)
// @ FeignClient(name = "servicio-productos", url="localhost:8001")

// Con Ribbon del lado del cliente => Mando Endpoints al properties

@FeignClient(name = "servicio-productos")
public interface ClienteProductoFeignRest {
	
	// Indicamos la Ruta para consumir el API Rest
	
	@GetMapping("/listar")
	public List<Producto> listar();
	
	@GetMapping("/ver/{id}")
	public Producto detalle(@PathVariable Long id);

}
