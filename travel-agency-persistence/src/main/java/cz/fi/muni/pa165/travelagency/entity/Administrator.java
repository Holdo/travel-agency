package cz.fi.muni.pa165.travelagency.entity;

import java.io.Serializable;
import javax.persistence.Entity;

/**
 *
 * 
 * @author 
 */
@Entity
public class Administrator extends User implements Serializable {

	public Administrator() {
		super();
	}
}
