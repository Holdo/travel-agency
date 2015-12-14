package cz.fi.muni.pa165.travelagency;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import cz.fi.muni.pa165.travelagency.dto.TripDTO;
import cz.fi.muni.pa165.travelagency.rest.mixin.TripDTOMixin;
import cz.fi.muni.pa165.travelagency.sampledata.TravelAgencySampleDataConfiguration;
import org.springframework.context.annotation.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

/**
 *
 * @author Michal Holic
 */
@EnableWebMvc
@Configuration
@Import({TravelAgencySampleDataConfiguration.class})
@ComponentScan(basePackages = {"cz.fi.muni.pa165.travelagency.rest.controllers"})
public class SpringMvcConfigREST extends WebMvcConfigurerAdapter {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new AllowOriginInterceptor());
	}

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	@Bean
	@Primary
	public MappingJackson2HttpMessageConverter customJackson2HttpMessageConverter() {
		MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		objectMapper.setDateFormat(new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH));

		objectMapper.addMixIn(TripDTO.class, TripDTOMixin.class);

		jsonConverter.setObjectMapper(objectMapper);
		return jsonConverter;
	}

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(customJackson2HttpMessageConverter());
	}
}
