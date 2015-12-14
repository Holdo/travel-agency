package cz.fi.muni.pa165.travelagency.rest.mixin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 *
 * @author Michal Holic
 */
@JsonIgnoreProperties({ "excursions", "reservations" })
public abstract class TripDTOMixin {
}
