
package cz.fi.muni.pa165.travelagency.ws.generated;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for trip complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="trip">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="dateFrom" type="{}javaSqlDateType" minOccurs="0"/>
 *         &lt;element name="dateTo" type="{}javaSqlDateType" minOccurs="0"/>
 *         &lt;element name="destination" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numberOfAvailable" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="price" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="excursions" type="{}excursion" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="reservations" type="{}reservation" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "trip", namespace = "http://muni.fi.cz/pa165/ws/entities/trips", propOrder = {
    "id",
    "dateFrom",
    "dateTo",
    "destination",
    "numberOfAvailable",
    "price",
    "excursions",
    "reservations"
})
public class Trip {

    @XmlElement(namespace = "http://muni.fi.cz/pa165/ws/entities/trips")
    protected Long id;
    @XmlElement(namespace = "http://muni.fi.cz/pa165/ws/entities/trips")
    protected JavaSqlDateType dateFrom;
    @XmlElement(namespace = "http://muni.fi.cz/pa165/ws/entities/trips")
    protected JavaSqlDateType dateTo;
    @XmlElement(namespace = "http://muni.fi.cz/pa165/ws/entities/trips")
    protected String destination;
    @XmlElement(namespace = "http://muni.fi.cz/pa165/ws/entities/trips")
    protected Integer numberOfAvailable;
    @XmlElement(namespace = "http://muni.fi.cz/pa165/ws/entities/trips")
    protected BigDecimal price;
    @XmlElement(namespace = "http://muni.fi.cz/pa165/ws/entities/trips", nillable = true)
    protected List<Excursion> excursions;
    @XmlElement(namespace = "http://muni.fi.cz/pa165/ws/entities/trips", nillable = true)
    protected List<Reservation> reservations;

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setId(Long value) {
        this.id = value;
    }

    /**
     * Gets the value of the dateFrom property.
     * 
     * @return
     *     possible object is
     *     {@link JavaSqlDateType }
     *     
     */
    public JavaSqlDateType getDateFrom() {
        return dateFrom;
    }

    /**
     * Sets the value of the dateFrom property.
     * 
     * @param value
     *     allowed object is
     *     {@link JavaSqlDateType }
     *     
     */
    public void setDateFrom(JavaSqlDateType value) {
        this.dateFrom = value;
    }

    /**
     * Gets the value of the dateTo property.
     * 
     * @return
     *     possible object is
     *     {@link JavaSqlDateType }
     *     
     */
    public JavaSqlDateType getDateTo() {
        return dateTo;
    }

    /**
     * Sets the value of the dateTo property.
     * 
     * @param value
     *     allowed object is
     *     {@link JavaSqlDateType }
     *     
     */
    public void setDateTo(JavaSqlDateType value) {
        this.dateTo = value;
    }

    /**
     * Gets the value of the destination property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDestination() {
        return destination;
    }

    /**
     * Sets the value of the destination property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDestination(String value) {
        this.destination = value;
    }

    /**
     * Gets the value of the numberOfAvailable property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getNumberOfAvailable() {
        return numberOfAvailable;
    }

    /**
     * Sets the value of the numberOfAvailable property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setNumberOfAvailable(Integer value) {
        this.numberOfAvailable = value;
    }

    /**
     * Gets the value of the price property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * Sets the value of the price property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPrice(BigDecimal value) {
        this.price = value;
    }

    /**
     * Gets the value of the excursions property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the excursions property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getExcursions().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Excursion }
     * 
     * 
     */
    public List<Excursion> getExcursions() {
        if (excursions == null) {
            excursions = new ArrayList<Excursion>();
        }
        return this.excursions;
    }

    /**
     * Gets the value of the reservations property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the reservations property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getReservations().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Reservation }
     * 
     * 
     */
    public List<Reservation> getReservations() {
        if (reservations == null) {
            reservations = new ArrayList<Reservation>();
        }
        return this.reservations;
    }

}
