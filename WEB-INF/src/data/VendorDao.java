package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import tools.dao.AbstractDataAccessObject;
import tools.dao.DataAccessException;

/**
 * 仕入れ先データ操作クラス
 */
public final class VendorDao extends AbstractDataAccessObject<VendorDto> {

    @Override
    public List<VendorDto> read() throws DataAccessException {
        String sql = "SELECT vendor_code FROM vendors;";

        List<VendorDto> list = new ArrayList<VendorDto>();

        // データベース接続とSQL文の準備
        try {
            connection();
            PreparedStatement stmt = getSqlStatement(sql);
            ResultSet result = stmt.executeQuery();

            // 実行結果の対応
            while(result.next()) {
                VendorDto vendor = new VendorDto();
                vendor.setVendorCode(result.getInt("vendor_code"));

                list.add(vendor);
            }

            disconnect();

        } catch (SQLException ex) {
            throw new DataAccessException(ex);
        }

        return list;
    }

}
