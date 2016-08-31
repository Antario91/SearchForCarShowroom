package SearchForCarShowroom.domain;

/**
 * Created by AlexandrGoloborodko on 21.08.16.
 */
public interface CarShowroomRepo extends GenericRepo<CarShowroom> {
    CarShowroom getByName (String name);
}
