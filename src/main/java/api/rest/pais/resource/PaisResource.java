package api.rest.pais.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import api.rest.pais.domain.Pais;
import api.rest.pais.dto.PaisDTO;
import api.rest.pais.exception.ResourceAccessException;
import api.rest.pais.hadler.ApiError;
import api.rest.pais.mapper.PaisMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Api-Rest Paises")
@RestController
@RequestMapping(path = "/paises", produces = MediaType.APPLICATION_JSON_VALUE)
public class PaisResource {

	final static String url = "https://topups.reloadly.com/countries";

	@Autowired
	private PaisMapper paisMapper;

	@Autowired
	private RestTemplate restTemplate;

	
	@ApiOperation(value = "Listado de los paises disponibles")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "OK", response = PaisDTO.class),
			@ApiResponse(code = 404, message = "Not Found", response = ApiError.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = ApiError.class) 
	})
	@GetMapping
	public ResponseEntity<List<PaisDTO>> findAll() throws ResourceAccessException {
		
		ResponseEntity<List<Pais>> responseList = restTemplate.exchange(url,
				HttpMethod.GET, null, new ParameterizedTypeReference<List<Pais>>() {
				});

		return new ResponseEntity<List<PaisDTO>>(paisMapper.toResponseList(responseList.getBody()), HttpStatus.OK);
	}
	
	
	@ApiOperation(value = "Busqueda de información de un pais en concreto")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "OK", response = PaisDTO.class),
			@ApiResponse(code = 404, message = "Not Found", response = ApiError.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = ApiError.class) 
	})
	@GetMapping(value = "/{pais}")
	public ResponseEntity<PaisDTO> findById(@PathVariable("pais") String pais) throws ResourceAccessException {

		Pais response = restTemplate.getForObject(url + "/{countrycode}", Pais.class, pais);
		return new ResponseEntity<PaisDTO>(paisMapper.entityToDTO(response), HttpStatus.OK);
	}

}
