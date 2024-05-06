package tools.dao;

import java.util.List;

/**
 * データアクセス仕様
 * @param <T> 使用するDTO型
 */
public interface DataAccessable<T> {
    /**
     * データを登録する
     * @param data 登録データ格納DTO
     * @return 登録結果（件数）
     * @throws DataAccessException データアクセス例外
     */
    public int create(T data) throws DataAccessException;

    /**
     * 指定条件にマッチするデータを取得する
     * @param id データID
     * @param order データの並び順（ASC | DESC)
     * @param keyword 検索キーワード（商品名が対象）
     * @return 取得データを格納したリスト型オブジェクト
     * @throws DataAccessException データアクセス例外
     */
    public List<T> read(int id, String order, String keyword) throws DataAccessException;

    /**
     * データを取得する
     * @return 取得データを格納したリスト型オブジェクト
     * @throws DataAccessException データアクセス例外
     */
    public List<T> read() throws DataAccessException;

    /**
     * データの更新を行う
     * @param data 更新対象データ格納DTO
     * @return 更新結果（件数）
     * @throws DataAccessException データアクセス例外
     */
    public int update(T data) throws DataAccessException;

    /**
     * データの削除を行う
     * @param id 削除対象のデータID
     * @return 削除結果（件数）
     * @throws DataAccessException データアクセス例外
     */
    public int delete(int id) throws DataAccessException;
}
