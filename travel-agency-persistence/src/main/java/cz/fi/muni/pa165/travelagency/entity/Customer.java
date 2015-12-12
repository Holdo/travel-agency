package cz.fi.muni.pa165.travelagency.entity;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

/**
 *
 * 
 * @author Branislav Bohumel
 */
@Entity
public class Customer extends User implements Serializable {

    public Customer() {
        super();
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
    private Set<Reservation> reservations = new HashSet<>();
    
    public void addReservation(Reservation reservation) {
        this.reservations.add(reservation);
    }

    public Set<Reservation> getReservations() {
        return Collections.unmodifiableSet(this.reservations);
    }
    
    public void removeReservation(Reservation reservation) {
        this.reservations.remove(reservation);
    }
}
