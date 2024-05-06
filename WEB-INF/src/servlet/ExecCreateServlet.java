package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.ProductDao;
import data.ProductDto;
import tools.servlet.ProductAppDefaultServlet;

/**
 * 追加機能用サーブレット
 */
public final class ExecCreateServlet extends ProductAppDefaultServlet {
    private static final String SUCCESS_PAGE = "/list";
    private static final String FAILURE_PAGE = "/WEB-INF/jsp/registerPage.jsp";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException
    {
        // 前準備
        doSetup(req, resp);

        // フォームから送られたデータを取得（型変換併用）
        int productCode, price, stockQuantity, vendorCode;
        String productName;
        try {
            productCode = extractParamToInteger(req, "product_code");
            productName = req.getParameter("product_name");
            price = extractParamToInteger(req, "price");
            stockQuantity = extractParamToInteger(req, "stock_quantity");
            vendorCode = extractParamToInteger(req, "vendor_code");

        } catch (NumberFormatException ex) {
            req.setAttribute(FAILURE_MSG_KEY, ex);
            doForward(FAILURE_PAGE, req, resp);
            return;		// TODO これいる？
        }

        // データ格納
        ProductDto product = new ProductDto();
        product.setProductCode(productCode);
        product.setProductName(productName);
        product.setPrice(price);
        product.setStockQuantity(stockQuantity);
        product.setVendorCode(vendorCode);

        // DAO操作
        ProductDao dao = new ProductDao();
        try {
            int rowCount = dao.create(product);
            if (rowCount > 0) {
                req.setAttribute(SUCCESS_MSG_KEY, String.format("商品を %d 件登録しました", rowCount));
                doForward(SUCCESS_PAGE, req, resp);
            } else {
                req.setAttribute(FAILURE_MSG_KEY, "データベース処理エラーが発生しました。システム管理者に確認してください");
                doForward(FAILURE_PAGE, req, resp);
            }
        } catch (SQLException ex) {
            doExceptedProcess(ex);
        }
    }
}
