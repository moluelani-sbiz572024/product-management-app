package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.ProductDao;
import tools.servlet.ProductAppDefaultServlet;

/**
 * 削除機能用サーブレット
 */
public final class ExecDeleteServlet extends ProductAppDefaultServlet {
    private static final String FORWARD_PAGE = "/list";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException
    {
        // 前準備
        doSetup(req, resp);

        // フォームから送られてきたデータの取得と型変換（文字列から数値型）
        int id = 0;
        try {
            id = extractParamToInteger(req, "id");

        } catch (NumberFormatException ex) {
            req.setAttribute(FAILURE_MSG_KEY, "内部エラーが発生しました。システム管理者に確認してください");
            doForward(FORWARD_PAGE, req, resp);
        }

        // DAO操作（データの削除）
        ProductDao dao = new ProductDao();
        try {
            int rowCount = dao.delete(id);

            if (rowCount > 0) {
                req.setAttribute(SUCCESS_MSG_KEY, String.format("商品を %d 件削除しました", rowCount));
            } else {
                req.setAttribute(FAILURE_MSG_KEY, "データベー処理エラーが発生しました。システム管理者に確認してください");
            }

            doForward(FORWARD_PAGE, req, resp);

        } catch (SQLException ex) {
            doExceptedProcess(ex);
        }
    }

}
