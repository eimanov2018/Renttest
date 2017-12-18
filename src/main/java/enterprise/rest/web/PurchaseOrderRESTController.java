package enterprise.rest.web;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import java.lang.reflect.Method;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import enterprise.domain.POStatus;
import enterprise.domain.PurchaseOrder;
import enterprise.rest.PurchaseOrderResource;
import enterprise.rest.PurchaseOrderResourceAssembler;
import enterprise.util.ExtendedLink;

@Controller
@RequestMapping("/rest/pos")
public class PurchaseOrderRESTController {
	
	@RequestMapping(method = RequestMethod.GET, value = "{id}")
	public ResponseEntity<PurchaseOrderResource> getPO(@PathVariable Long id) 
		throws NoSuchMethodException,  EntityNotFoundException {
		PurchaseOrder po = PurchaseOrder.findPurchaseOrder(id);
		
		if (po == null)
			throw new EntityNotFoundException("Purchase Order not found");
		
		PurchaseOrderResourceAssembler assembler = new PurchaseOrderResourceAssembler();
		PurchaseOrderResource resource = assembler.toResource(po);
		
		switch (po.getStatus()) {
		case PENDING_CONFIRMATION:
			Method _acceptPO = PurchaseOrderRESTController.class.getMethod("acceptPO", Long.class);
			String acceptLink = linkTo(_acceptPO, po.getId()).toUri().toString();
			resource.add(new ExtendedLink(acceptLink, "acceptPO", "POST"));
			
			Method _rejectPO = PurchaseOrderRESTController.class.getMethod("rejectPO", Long.class);
			String rejectLink = linkTo(_rejectPO, po.getId()).toUri().toString();
			resource.add(new ExtendedLink(rejectLink, "rejectPO", "DELETE"));
			break;
		case OPN:
			break;
		case PENDING_UPDATE:
			break;
		default: 
			break;
		}
	
		ResponseEntity<PurchaseOrderResource> response;
		response = new ResponseEntity<>(resource, HttpStatus.OK);
		return response;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/{id}/accept")
	public ResponseEntity<Void> acceptPO(@PathVariable Long id) {
		ResponseEntity<Void> response = null;
		PurchaseOrder po = PurchaseOrder.findPurchaseOrder(id);

		if (po.getStatus().equals(POStatus.PENDING_CONFIRMATION)) {
			po.setStatus(POStatus.OPN);
			po.merge();
			response = new ResponseEntity<>(HttpStatus.OK);
		} else
			response = new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
		return response;
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}/delete")
	public ResponseEntity<Void> rejectPO(@PathVariable Long id) {
		return null;
	}
		
}
