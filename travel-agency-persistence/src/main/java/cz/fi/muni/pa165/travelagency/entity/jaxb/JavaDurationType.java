package cz.fi.muni.pa165.travelagency.entity.jaxb;

import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author Michal Holic
 */
public class JavaDurationType {

	@XmlElement(nillable = false)
	public long nOfHours;
}
