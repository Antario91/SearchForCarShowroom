package SearchForCarShowroom.domain;

/**
 * Created by AlexandrGoloborodko on 20.08.16.
 */
public interface AutomobileRepo extends GenericRepo<Automobile> {
    Automobile getByModel (String model);

}
