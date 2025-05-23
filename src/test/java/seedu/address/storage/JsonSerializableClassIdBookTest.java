package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.WhoDat;
import seedu.address.testutil.TypicalPersons;

public class JsonSerializableClassIdBookTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableWhoDatTest");
    private static final Path TYPICAL_PERSONS_FILE = TEST_DATA_FOLDER.resolve("typicalPersonsWhoDat.json");
    private static final Path INVALID_PERSON_FILE = TEST_DATA_FOLDER.resolve("invalidPersonWhoDat.json");
    private static final Path DUPLICATE_PERSON_FILE = TEST_DATA_FOLDER.resolve("duplicatePersonWhoDat.json");

    @Test
    public void toModelType_typicalPersonsFile_success() throws Exception {
        JsonSerializableWhoDat dataFromFile = JsonUtil.readJsonFile(TYPICAL_PERSONS_FILE,
                JsonSerializableWhoDat.class).get();
        WhoDat whoDatFromFile = dataFromFile.toModelType();
        WhoDat typicalPersonsWhoDat = TypicalPersons.getTypicalWhoDat();
        assertEquals(whoDatFromFile, typicalPersonsWhoDat);
    }

    @Test
    public void toModelType_invalidPersonFile_throwsIllegalValueException() throws Exception {
        JsonSerializableWhoDat dataFromFile = JsonUtil.readJsonFile(INVALID_PERSON_FILE,
                JsonSerializableWhoDat.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicatePersons_throwsIllegalValueException() throws Exception {
        JsonSerializableWhoDat dataFromFile = JsonUtil.readJsonFile(DUPLICATE_PERSON_FILE,
                JsonSerializableWhoDat.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableWhoDat.MESSAGE_DUPLICATE_PERSON,
                dataFromFile::toModelType);
    }

}
