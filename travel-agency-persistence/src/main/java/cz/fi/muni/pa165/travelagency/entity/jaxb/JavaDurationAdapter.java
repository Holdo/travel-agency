package cz.fi.muni.pa165.travelagency.entity.jaxb;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.Duration;

/**
 *
 * @author Michal Holic
 */
public final class JavaDurationAdapter extends XmlAdapter<JavaDurationType,Duration> {

	@Override
	public JavaDurationType marshal(Duration duration) throws Exception {
		JavaDurationType javaDurationType = new JavaDurationType();
		javaDurationType.nOfHours = duration.toHours();
		return javaDurationType;
	}

	@Override
	public Duration unmarshal(JavaDurationType javaDurationType) throws Exception {
		return Duration.ofHours(javaDurationType.nOfHours);
	}
}
