package enterprise.rest.web;

import java.net.URI;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import enterprise.domain.Plant;
import enterprise.rest.PlantResource;
import enterprise.rest.PlantResourceAssembler;
import enterprise.rest.PlantResourceList;

@Controller
@RequestMapping("/rest")
public class PlantRESTController {
	@RequestMapping(method = RequestMethod.GET, value = "/pos")
	  public ResponseEntity<PlantResourceList> getAllPRL() {
	   List<Plant> pos = Plant.findAllPlants();
		PlantResourceAssembler assembler = new PlantResourceAssembler();
		PlantResourceList resList = (PlantResourceList) assembler.toResource(pos);
		ResponseEntity<PlantResourceList> response = new 	ResponseEntity<>(resList, HttpStatus.OK);
		return response;
	  }	

	@RequestMapping(method = RequestMethod.POST, value = "/pos")
	public ResponseEntity<Void> createPlantResource(@RequestBody PlantResource res) {
	Plant p = new Plant();
	p.setDescription(res.getDescription());
	p.setName(res.getName());
	p.setPrice(res.getPrice());
	p.persist();
	HttpHeaders headers = new HttpHeaders();
	URI location = ServletUriComponentsBuilder.fromCurrentRequestUri().
	pathSegment(p.getId().toString()).build().toUri();
	headers.setLocation(location);
	ResponseEntity<Void> response = new
	ResponseEntity<>(headers, HttpStatus.CREATED);
	return response;
	}
	
	
}
