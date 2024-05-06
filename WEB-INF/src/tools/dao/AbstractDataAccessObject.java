package tools.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * データアクセス抽象クラス
 * @param <T> データアクセスデータオブジェクト(DTO)
 */
public abstract class AbstractDataAccessObject<T> implements DataAccessable<T> {
    // データベース接続情報
    private Connection connection = null;

    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://192.168.0.41/java_db_app";
    private static final String USER_NAME = "samurai";
    private static final String PASSWORD = "Passw0rd";

    /**
     * データベース接続を行い、接続情報を保持する
     * @throws DataAccessException データベースアクセス例外
     */
    protected void connection() throws DataAccessException {
        try {
            if (this.connection == null) {
                Class.forName(DRIVER);
                this.connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new DataAccessException(ex);

        } catch (ClassNotFoundException ex) {
            System.err.println(ex.getMessage());
        }
    }

    /**
     * データベースセッションを切断する
     */
    protected void disconnect() {
        try {
            this.connection.close();

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    /**
     * SQL実行用ステートメント情報を取得する
     * @param sql 実行対象SQL文（文字列）
     * @return SQL実行用ステートメント情報
     * @throws DataAccessException データベースアクセス例外
     */
    protected PreparedStatement getSqlStatement(String sql) throws  DataAccessException {
        PreparedStatement stmt = null;

        try {
            stmt = this.connection.prepareStatement(sql);

        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new DataAccessException(ex);
        }

        return stmt;
    }

    @Override
    public int create(T data) throws DataAccessException {
        return 0;
    }

    @Override
    public List<T> read(int id, String order, String keyword) throws DataAccessException {
        return null;
    }

    @Override
    public List<T> read() throws DataAccessException {
        return null;
    }

    @Override
    public int update(T data) throws DataAccessException {
        return 0;
    }

    @Override
    public int delete(int id) throws DataAccessException {
        return 0;
    }
}
