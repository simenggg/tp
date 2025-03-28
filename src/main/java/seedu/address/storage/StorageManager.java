package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.ReadOnlyWhoDat;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * Manages storage of WhoDat data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private WhoDatStorage whoDatStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code WhoDatStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(WhoDatStorage whoDatStorage, UserPrefsStorage userPrefsStorage) {
        this.whoDatStorage = whoDatStorage;
        this.userPrefsStorage = userPrefsStorage;
    }

    // ================ UserPrefs methods ==============================

    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataLoadingException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }


    // ================ WhoDat methods ==============================

    @Override
    public Path getWhoDatFilePath() {
        return whoDatStorage.getWhoDatFilePath();
    }

    @Override
    public Optional<ReadOnlyWhoDat> readWhoDat() throws DataLoadingException {
        return readWhoDat(whoDatStorage.getWhoDatFilePath());
    }

    @Override
    public Optional<ReadOnlyWhoDat> readWhoDat(Path filePath) throws DataLoadingException {
        logger.fine("Attempting to read data from file: " + filePath);
        return whoDatStorage.readWhoDat(filePath);
    }

    @Override
    public void saveWhoDat(ReadOnlyWhoDat whoDat) throws IOException {
        saveWhoDat(whoDat, whoDatStorage.getWhoDatFilePath());
    }

    @Override
    public void saveWhoDat(ReadOnlyWhoDat whoDat, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        whoDatStorage.saveWhoDat(whoDat, filePath);
    }

}
