package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import tools.dao.AbstractDataAccessObject;
import tools.dao.DataAccessException;

/**
 * 商品データ操作クラス
 */
public final class ProductDao extends AbstractDataAccessObject<ProductDto> {

    @Override
    public int create(ProductDto data) throws DataAccessException {
        int rowCount = 0;

        // INSERT文
        String sql = String.format(
            "INSERT INTO products(%s, %s, %s, %s, %s) VALUES(?, ?, ?, ?, ?);",
            "product_code", "product_name", "price", "stock_quantity", "vendor_code"
        );

        // データベース接続とSQL文の送信準備
        try {
            connection();
            PreparedStatement stmt = getSqlStatement(sql);

            // SQL文のプレースホルダーの置換
            stmt.setInt(1,  data.getProductCode());
            stmt.setString(2, data.getProductName());
            stmt.setInt(3, data.getPrice());
            stmt.setInt(4, data.getStockQuantity());
            stmt.setInt(5, data.getVendorCode());

            // SQL文の実行
            rowCount = stmt.executeUpdate();
            disconnect();

        } catch (SQLException ex) {
            throw new DataAccessException(ex);
        }

        return rowCount;
    }

    @Override
    public List<ProductDto> read(int id, String order, String keyword) throws DataAccessException {
        String sql = "SELECT * FROM products";

        List<ProductDto> list = new ArrayList<ProductDto>();

        // 文字列のNULL対策
        order = Objects.toString(order, "");
        keyword = Objects.toString(keyword, "");

        // データベース接続
        try {
            connection();

            // 引数に合わせてSELECT文に条件を付加
            if (id > 0) {
                sql += " WHERE id = ?;";
            } else {
                // 検索キーワードが存在する場合
                if (!keyword.isEmpty()) {
                    sql += " WHERE product_name LIKE ?";
                }

                // 並べ替え方向に合わせてORDER BY句を付加
                if ("asc".equals(order)) {
                    sql += " ORDER BY updated_at ASC";
                } else if ("desc".equals(order)) {
                    sql += " ORDER BY updated_at DESC";
                }
                sql += ";";
            }

            PreparedStatement stmt = getSqlStatement(sql);

            // ?があれば置換
            if (id > 0) {
                stmt.setInt(1, id);
            } else if (!keyword.isEmpty()) {
                stmt.setString(1, "%" + keyword + "%");
            }

            // SQL文の実行
            ResultSet result = stmt.executeQuery();

            // 実行結果の対応
            while(result.next()) {
                // ProductDtoにデータを設定
                ProductDto product = new ProductDto();
                product.setId(result.getInt("id"));
                product.setProductCode(result.getInt("product_code"));
                product.setProductName(result.getString("product_name"));
                product.setPrice(result.getInt("price"));
                product.setStockQuantity(result.getInt("stock_quantity"));
                product.setVendorCode(result.getInt("vendor_code"));

                // リストに追加
                list.add(product);
            }

            disconnect();

        } catch (SQLException ex) {
            throw new DataAccessException(ex);
        }

        return list;
    }

    @Override
    public int update(ProductDto data) throws  DataAccessException {
        int rowCount = 0;

        // UPDATE文
        String sql = String.format(
            "UPDATE products SET %s = ?, %s = ?, %s = ?, %s = ?, %s = ? WHERE id = ?;",
            "product_code", "product_name", "price", "stock_quantity", "vendor_code"
        );

        // データベース接続とSQL文の送信準備
        try {
            connection();
            PreparedStatement stmt = getSqlStatement(sql);

            // SQL文のプレースホルダーの置換
            stmt.setInt(1, data.getProductCode());
            stmt.setString(2, data.getProductName());
            stmt.setInt(3,  data.getPrice());
            stmt.setInt(4,  data.getStockQuantity());
            stmt.setInt(5, data.getVendorCode());
            stmt.setInt(6, data.getId());

            // SQL文の実行
            rowCount = stmt.executeUpdate();

            disconnect();

        } catch (SQLException ex) {
            throw new DataAccessException(ex);
        }

        return rowCount;
    }

    @Override
    public int delete(int id) throws DataAccessException {
        int rowCount = 0;

        String sql = "DELETE FROM products WHERE id = ?;";

        // データベース接続とSQL文の送信準備
        try {
            connection();
            PreparedStatement stmt = getSqlStatement(sql);

            stmt.setInt(1, id);

            // SQL文の実行
            rowCount = stmt.executeUpdate();

            disconnect();

        } catch (SQLException ex) {
            throw new DataAccessException(ex);
        }

        return rowCount;
    }

}
