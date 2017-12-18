package enterprise.rest;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="plant")
public class PlantResource {

    /**
     */
    private String name;

    /**
     */
    private String description;

    /**
     */
    private Double price;
    
    
    public void setName(String name) {
          this.name = name;
      }
      
      public void setDescription(String description) {
          this.description = description;
      }
      
      public void setPrice(Double price) {
          this.price = price;
      }
      public String getName() {
          return name;
      }
      
      public String getDescription() {
          return description;
      }
      
      public Double getPrice() {
          return price;
      }
}
