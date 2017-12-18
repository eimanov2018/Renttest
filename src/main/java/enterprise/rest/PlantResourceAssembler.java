package enterprise.rest;

import java.util.LinkedList;
import java.util.List;

import enterprise.domain.Plant;

public class PlantResourceAssembler {
	public PlantResource toResource(Plant plant) {
		PlantResource res = new PlantResource();
		res.setName(plant.getName());
		res.setDescription(plant.getDescription());
		res.setPrice(plant.getPrice());
		return res;
   }
	
   public List<PlantResource> toResource(List<Plant> plants) {
		List<PlantResource> resList = new LinkedList<>();
		for (Plant plant: plants)
			resList.add(toResource(plant));
		return resList;
   }
}
