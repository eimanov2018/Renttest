package enterprise.rest;

import java.util.Date;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.springframework.roo.addon.javabean.RooJavaBean;

import enterprise.util.DateAdapter;
import enterprise.util.ResourceSupport;
@XmlJavaTypeAdapter(DateAdapter.class)

@RooJavaBean
public class PurchaseOrderResource extends ResourceSupport {

	private Date startDate;
    private Date endDate;
    private Double total;
    private PlantResource plant;
    @XmlJavaTypeAdapter(DateAdapter.class)
	public Date getStartDate() {
        return this.startDate;
    }

	public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
	@XmlJavaTypeAdapter(DateAdapter.class)
	public Date getEndDate() {
        return this.endDate;
    }

	public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

	public Double getTotal() {
        return this.total;
    }

	public void setTotal(Double total) {
        this.total = total;
    }

	public PlantResource getPlant() {
        return this.plant;
    }

	public void setPlant(PlantResource plant) {
        this.plant = plant;
    }
}