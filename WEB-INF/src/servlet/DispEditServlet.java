package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.ProductDao;
import data.ProductDto;
import data.VendorDao;
import data.VendorDto;
import tools.servlet.ProductAppDefaultServlet;

/**
 * 編集ページ用サーブレット
 */
public final class DispEditServlet extends ProductAppDefaultServlet {
    private static final String SUCCESS_PAGE = "/WEB-INF/jsp/editPage.jsp";
    private static final String FAILURE_PAGE = "/WEB-INF/jsp/listPage.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException
    {
        doSetup(req, resp);

        int id;
        try {
            id = extractParamToInteger(req, "id");

        } catch (NumberFormatException ex) {
            req.setAttribute(FAILURE_MSG_KEY, "内部エラーが発生しました。システム管理者に確認してください");
            doForward(FAILURE_PAGE, req, resp);
            return;		// TODO これいる？
        }

        //
        List<ProductDto> products = new ArrayList<ProductDto>();
        List<VendorDto> vendors = new ArrayList<VendorDto>();

        try {
            //
            ProductDao daop = new ProductDao();
            VendorDao daov = new VendorDao();

            products = daop.read(id, "", "");
            vendors = daov.read();

        } catch (SQLException ex) {
            req.setAttribute(FAILURE_MSG_KEY, "データベース処理エラーが発生しました。システム管理者に確認してください");
            doForward(FAILURE_PAGE, req, resp);
            return;		// TODO これいる？
        }

        if (products.isEmpty()) {
            req.setAttribute(FAILURE_PAGE, "該当する商品データが見つかりません");
        } else {
            req.setAttribute("products", products);
        }

        if (vendors.isEmpty()) {
            req.setAttribute(FAILURE_MSG_KEY, "仕入れ先コードが１件も登録されていません");
        } else {
            req.setAttribute("vendors", vendors);
        }

        doForward(SUCCESS_PAGE, req, resp);
    }

}
