
package cz.fi.muni.pa165.travelagency.ws.generated;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for javaDurationType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="javaDurationType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="nOfHours" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "javaDurationType", propOrder = {
    "nOfHours"
})
public class JavaDurationType {

    protected long nOfHours;

    /**
     * Gets the value of the nOfHours property.
     * 
     */
    public long getNOfHours() {
        return nOfHours;
    }

    /**
     * Sets the value of the nOfHours property.
     * 
     */
    public void setNOfHours(long value) {
        this.nOfHours = value;
    }

}
