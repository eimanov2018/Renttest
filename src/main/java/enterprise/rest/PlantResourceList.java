package enterprise.rest;

import java.util.Collections;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name="plants")

public class PlantResourceList {
	
	private List<PlantResource> plant;
	  public PlantResourceList() {
	 	this(Collections.<PlantResource>emptyList());
	  }
	  public PlantResourceList(List<PlantResource> plants) {
		this.plant = plants;
	  }
}
