package enterprise.rest;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import enterprise.domain.PurchaseOrder;
import enterprise.rest.PurchaseOrderResource;
import enterprise.rest.web.PurchaseOrderRESTController;

public class PurchaseOrderResourceAssembler extends ResourceAssemblerSupport<PurchaseOrder, PurchaseOrderResource> {

	public PurchaseOrderResourceAssembler() {
		super(PurchaseOrderRESTController.class, PurchaseOrderResource.class);
	}

	@Override
	public PurchaseOrderResource toResource(PurchaseOrder po) {
		PurchaseOrderResource resource = createResourceWithId(po.getId(), po);
		resource.setStartDate(po.getStartDate());
		resource.setEndDate(po.getEndDate());
		resource.setTotal(po.getTotal());
		
		PlantResource plantResource = null;
		if (po.getPlant() != null) {
			PlantResourceAssembler assembler = new PlantResourceAssembler();
			plantResource = assembler.toResource(po.getPlant());
		}
		
		resource.setPlant(plantResource);
		
		return resource;
	}
}
