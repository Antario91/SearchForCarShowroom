package SearchForCarShowroom.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

/**
 * Created by AlexandrGoloborodko on 20.08.16.
 */
@Entity
@Table(name = "Kit_Room")
public class CarKitCarShowroomAdditionalTable implements Serializable {
    @Id
    @ManyToOne
    private CarKit kit;

    @Id
    @ManyToOne
    private CarShowroom showroom;

    public CarKitCarShowroomAdditionalTable() {
    }

    public CarKitCarShowroomAdditionalTable(CarKit kit, CarShowroom showroom) {
        this.kit = kit;
        this.showroom = showroom;
    }

    public CarKit getKit() {
        return kit;
    }

    public void setKit(CarKit kit) {
        this.kit = kit;
    }

    public CarShowroom getShowroom() {
        return showroom;
    }

    public void setShowroom(CarShowroom showroom) {
        this.showroom = showroom;
    }

    @Override
    public boolean equals(Object o) {
        if ( this == o ) {
            return true;
        }
        if ( o == null || getClass() != o.getClass() ) {
            return false;
        }
        CarKitCarShowroomAdditionalTable that = (CarKitCarShowroomAdditionalTable) o;
        return Objects.equals( kit, that.kit ) &&
                Objects.equals( showroom, that.showroom );
    }

    @Override
    public int hashCode() {
        return Objects.hash( kit, showroom );
    }
}
