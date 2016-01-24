
package cz.fi.muni.pa165.travelagency.ws.generated;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="trip" type="{http://muni.fi.cz/pa165/ws/entities/trips}trip" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "trip"
})
@XmlRootElement(name = "getTripResponse", namespace = "http://muni.fi.cz/pa165/ws/entities/trips")
public class GetTripResponse {

    @XmlElement(namespace = "http://muni.fi.cz/pa165/ws/entities/trips")
    protected List<Trip> trip;

    /**
     * Gets the value of the trip property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the trip property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTrip().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Trip }
     * 
     * 
     */
    public List<Trip> getTrip() {
        if (trip == null) {
            trip = new ArrayList<Trip>();
        }
        return this.trip;
    }

}
