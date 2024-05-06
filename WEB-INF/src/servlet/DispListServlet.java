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
import tools.servlet.ProductAppDefaultServlet;

/**
 * 一覧ページ用サーブレット
 */
public final class DispListServlet extends ProductAppDefaultServlet {
    private static final String FORWARD_PAGE = "/WEB-INF/jsp/listPage.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException
    {
        handleProducts(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException
    {
        doSetup(req, resp);

        String message = extractAttribToString(req, SUCCESS_MSG_KEY);
        if (message != null && !message.isEmpty()) {
            req.setAttribute(SUCCESS_MSG_KEY, message);
        }

        handleProducts(req, resp);
    }

    /**
     * 商品データを取得してリクエスト元に返却する
     * @param request リクエストストリーム
     * @param response レスポンスストリーム
     * @throws ServletException サーブレット例外
     * @throws IOException 入出力（ストリーム）例外
     */
    private void handleProducts(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        // 前準備
        doSetup(request, response);

        // フォームからデータを取得（ヌル対策）
        String order = extractNullToString(request, "order");
        String keyword = extractNullToString(request, "keyword");

        List<ProductDto> products = new ArrayList<ProductDto>();

        // DAO操作
        ProductDao dao = new ProductDao();
        try {
            products = dao.read(0, order, keyword);
            if (products.isEmpty()) {
                request.setAttribute(FAILURE_MSG_KEY, "該当する商品が見つかりませんでした");
            } else {
                request.setAttribute("products", products);
            }
        } catch (SQLException ex) {
            request.setAttribute(FAILURE_MSG_KEY, "データベース処理エラーが発生しました。システム管理者に確認してください。");
        }

        doForward(FORWARD_PAGE, request, response);
    }
}
