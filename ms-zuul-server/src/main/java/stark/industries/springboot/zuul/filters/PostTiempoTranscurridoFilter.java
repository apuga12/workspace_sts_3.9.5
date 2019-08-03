package stark.industries.springboot.zuul.filters;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class PostTiempoTranscurridoFilter extends ZuulFilter {
	
	private static Logger log = LoggerFactory.getLogger(PostTiempoTranscurridoFilter.class);

	@Override
	public boolean shouldFilter() {
		// Indica si se ejecutara el metodo run()
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		// Aqui se ejecuta la logica del filtro
		
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		log.info(" ==> Entrando a POST filter:");
		
		Long tiempoInicio = (Long)request.getAttribute("tiempoInicio");
		Long tiempoFin = System.currentTimeMillis();
		Long tiempoTotal = tiempoFin - tiempoInicio; 
		
		log.info(String.format("Tiempo transcurrido en segundos %s", tiempoTotal.doubleValue()/1000.00));
		log.info(String.format("Tiempo transcurrido en mili segundos %s", tiempoTotal));
		return null;
	}

	@Override
	public String filterType() {
		// PRE Filtro
		return "post";
	}

	@Override
	public int filterOrder() {
		return 1;
	}

}
