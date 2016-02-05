package controllers.frontend;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import models.Person;
import models.PersonRepository;
import play.mvc.Controller;
import play.mvc.Result;

/**
 * The main set of web services.
 */
@Named
@Singleton
public class Application extends Controller {

    private final PersonRepository personRepository;

    // We are using constructor injection to receive a repository to support our desire for immutability.
    @Inject
    public Application(final PersonRepository personRepository) {
        this.personRepository = personRepository;
    }
    

    public Result index() {
        personRepository.save((new Person.Builder("foo@bar.de")).build());
        return ok(views.html.index.render("Hi: " + Long.toString(personRepository.count())));
    }
}
