package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.tag.Tag;

/**
 * Represents a Person in the contact list.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final StudentId studentId;
    private final EmailId emailId;

    // Data fields
    private final ClassId classId;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, StudentId studentId, EmailId emailId, ClassId classId, Set<Tag> tags) {
        requireAllNonNull(name, studentId, emailId, classId, tags);
        this.name = name;
        this.studentId = studentId;
        this.emailId = emailId;
        this.classId = classId;
        this.tags.addAll(tags);
    }

    public Name getName() {
        return name;
    }

    public StudentId getStudentId() {
        return studentId;
    }

    public EmailId getEmail() {
        return emailId;
    }

    public ClassId getClassId() {
        return classId;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both persons have the same student id or email id.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean hasSameStudentIdAndEmailId(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        if (otherPerson == null) {
            return false;
        }

        String otherStudentId = otherPerson.getStudentId().toString();
        String currentStudentId = getStudentId().toString();
        boolean isSameStudentId = otherStudentId.equals(currentStudentId);

        String otherEmail = otherPerson.getEmail().toString();
        String currentEmail = getEmail().toString();
        boolean isSameEmail = otherEmail.equals(currentEmail);

        return isSameEmail || isSameStudentId;
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return name.equals(otherPerson.name)
                && studentId.equals(otherPerson.studentId)
                && emailId.equals(otherPerson.emailId)
                && classId.equals(otherPerson.classId)
                && tags.equals(otherPerson.tags);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, studentId, emailId, classId, tags);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("studentId", studentId)
                .add("emailId", emailId)
                .add("classId", classId)
                .add("tags", tags)
                .toString();
    }

}
