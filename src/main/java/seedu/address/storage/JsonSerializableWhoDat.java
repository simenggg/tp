package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.ReadOnlyWhoDat;
import seedu.address.model.WhoDat;
import seedu.address.model.person.Person;


/**
 * An Immutable WhoDat that is serializable to JSON format.
 */
@JsonRootName(value = "whodat")
class JsonSerializableWhoDat {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";

    private final List<JsonAdaptedPerson> persons = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableWhoDat} with the given persons.
     */
    @JsonCreator
    public JsonSerializableWhoDat(@JsonProperty("persons") List<JsonAdaptedPerson> persons) {
        this.persons.addAll(persons);
    }

    /**
     * Converts a given {@code ReadOnlyWhoDat} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableWhoDat}.
     */
    public JsonSerializableWhoDat(ReadOnlyWhoDat source) {
        persons.addAll(source.getPersonList().stream().map(JsonAdaptedPerson::new).collect(Collectors.toList()));
    }

    /**
     * Converts this contact list into the model's {@code WhoDat} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public WhoDat toModelType() throws IllegalValueException {
        WhoDat whoDat = new WhoDat();
        for (JsonAdaptedPerson jsonAdaptedPerson : persons) {
            Person person = jsonAdaptedPerson.toModelType();
            if (whoDat.hasPerson(person)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            whoDat.addPerson(person);
        }
        return whoDat;
    }

}
