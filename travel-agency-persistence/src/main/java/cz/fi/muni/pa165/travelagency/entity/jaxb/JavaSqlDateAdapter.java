package cz.fi.muni.pa165.travelagency.entity.jaxb;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.sql.Date;

/**
 *
 * @author Michal Holic
 */
public final class JavaSqlDateAdapter extends XmlAdapter<JavaSqlDateType,Date> {

	@Override
	public JavaSqlDateType marshal(Date date) throws Exception {
		JavaSqlDateType javaSqlDateType = new JavaSqlDateType();
		javaSqlDateType.dateString = date.toString();
		return javaSqlDateType;
	}

	@Override
	public Date unmarshal(JavaSqlDateType javaSqlDateType) throws Exception {
		return Date.valueOf(javaSqlDateType.dateString);
	}
}
