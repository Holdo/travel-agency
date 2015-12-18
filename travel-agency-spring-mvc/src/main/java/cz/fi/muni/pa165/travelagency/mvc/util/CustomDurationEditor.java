package cz.fi.muni.pa165.travelagency.mvc.util;

import java.beans.PropertyEditorSupport;
import java.time.Duration;

/**
 * PropertyEditor for Java 8 Duration objects.
 *
 * @author Michal Holic
 */
public class CustomDurationEditor extends PropertyEditorSupport implements Cloneable {

	public CustomDurationEditor() {
		super();
	}

	@Override
	public String getAsText() {
		Object val = getValue();
		if ( val == null ) {
			return null;
		}
		if ( val instanceof Duration) {
			Duration duration = (Duration) val;
			return String.valueOf(duration.toHours());
		}
		throw new IllegalArgumentException("Unsupported duration object ["
				+val.getClass() +"]: " +val);
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		try {
			Long hours = Long.valueOf(text);
			setValue(Duration.ofHours(hours));
		} catch ( NumberFormatException e ) {
			throw new IllegalArgumentException("Not a valid hour duration");
		}
	}

	@Override
	public Object clone() {
		try {
			return super.clone();
		} catch ( CloneNotSupportedException e ) {
			// should never get here
			throw new RuntimeException(e);
		}
	}

}