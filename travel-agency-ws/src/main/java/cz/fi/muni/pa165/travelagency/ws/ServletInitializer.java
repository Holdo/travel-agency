package cz.fi.muni.pa165.travelagency.ws;

import org.springframework.ws.transport.http.support.AbstractAnnotationConfigMessageDispatcherServletInitializer;

/**
 *
 * @author Michal Holic
 */
public class ServletInitializer extends AbstractAnnotationConfigMessageDispatcherServletInitializer {
	@Override
	protected String[] getServletMappings() {
		return new String[]{"/"};
	}

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[]{WsConfig.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return null;
	}
}
