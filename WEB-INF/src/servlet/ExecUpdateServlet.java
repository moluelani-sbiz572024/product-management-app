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
 * 更新機能用サーブレット
 */
public final class ExecUpdateServlet extends ProductAppDefaultServlet {
    private static final String SUCCESS_PAGE = "/list";
    private static final String FAILURE_PAGE = "/WEB-INF/jsp/editPage.jsp";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException
    {
        // 前準備
        doSetup(req, resp);

        // フォームから送られてきたデータの取得と型変換（文字列から数値型）
        int id, productCode, price, stockQuantity, vendorCode;
        String productName = null;
        try {
            id = extractParamToInteger(req, "id");
            productCode = extractParamToInteger(req, "product_code");
            productName = req.getParameter("product_name");
            price = extractParamToInteger(req, "price");
            stockQuantity = extractParamToInteger(req, "stock_quantity");
            vendorCode = extractParamToInteger(req, "vendor_code");

        } catch (NumberFormatException ex) {
            req.setAttribute(FAILURE_MSG_KEY, "商品名以外の項目には、有効な数値を入力してください");
            doForward(FAILURE_PAGE, req, resp);
            return;		// TODO これいる？
        }

        if (productName == null || productName.isEmpty()) {
            req.setAttribute(FAILURE_MSG_KEY, "有効な商品名を入力してください");
            doForward(FAILURE_PAGE, req, resp);
            return;		// TODO これいる？
        }

        ProductDto product = new ProductDto();
        product.setId(id);
        product.setProductCode(productCode);
        product.setProductName(productName);
        product.setPrice(price);
        product.setStockQuantity(stockQuantity);
        product.setVendorCode(vendorCode);

        // DAO操作
        ProductDao dao = new ProductDao();
        try {
            int rowCount = dao.update(product);
            if (rowCount > 0) {
                req.setAttribute(SUCCESS_MSG_KEY, String.format("商品を %d 件編集しました", rowCount));
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
