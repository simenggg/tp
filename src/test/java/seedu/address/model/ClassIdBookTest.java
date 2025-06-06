package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CLASS_ID_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.getTypicalWhoDat;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.testutil.PersonBuilder;

public class ClassIdBookTest {

    private final WhoDat whoDat = new WhoDat();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), whoDat.getPersonList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> whoDat.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyWhoDat_replacesData() {
        WhoDat newData = getTypicalWhoDat();
        whoDat.resetData(newData);
        assertEquals(newData, whoDat);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        Person editedAlice = new PersonBuilder(ALICE).withClassId(VALID_CLASS_ID_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Person> newPersons = Arrays.asList(ALICE, editedAlice);
        WhoDatStub newData = new WhoDatStub(newPersons);

        assertThrows(DuplicatePersonException.class, () -> whoDat.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> whoDat.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInWhoDat_returnsFalse() {
        assertFalse(whoDat.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInWhoDat_returnsTrue() {
        whoDat.addPerson(ALICE);
        assertTrue(whoDat.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInWhoDat_returnsTrue() {
        whoDat.addPerson(ALICE);
        Person editedAlice = new PersonBuilder(ALICE).withClassId(VALID_CLASS_ID_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(whoDat.hasPerson(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> whoDat.getPersonList().remove(0));
    }

    @Test
    public void toStringMethod() {
        String expected = WhoDat.class.getCanonicalName() + "{persons=" + whoDat.getPersonList() + "}";
        assertEquals(expected, whoDat.toString());
    }

    /**
     * A stub ReadOnlyAddressBook whose persons list can violate interface constraints.
     */
    private static class WhoDatStub implements ReadOnlyWhoDat {
        private final ObservableList<Person> persons = FXCollections.observableArrayList();

        WhoDatStub(Collection<Person> persons) {
            this.persons.setAll(persons);
        }

        @Override
        public ObservableList<Person> getPersonList() {
            return persons;
        }
    }

}
