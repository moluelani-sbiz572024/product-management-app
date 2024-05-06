package tools.dao;

import java.sql.SQLException;

/**
 * データアクセス例外（SQL例外をベース）
 */
public final class DataAccessException extends SQLException {
    public DataAccessException() {
        super();
    }

    public DataAccessException(String message) {
        super(message);
    }

    public DataAccessException(Throwable error) {
        super(error);
    }

    public DataAccessException(String message, Throwable error) {
        super(message, error);
    }
}
