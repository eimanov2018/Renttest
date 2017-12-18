package enterprise.webservice;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;
import enterprise.domain.Plant;

@WebService(serviceName="PlantService")
public class Service {
	
	@WebMethod(operationName="getPlants")
	public List<Plant> listAllPlants(){
//		return (Plant[]) Plant.findAllPlants().toArray(new Plant[Plant.findAllPlants().size()]) ;
		return Plant.findAllPlants();
	}

}
