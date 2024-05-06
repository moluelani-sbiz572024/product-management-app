package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.VendorDao;
import data.VendorDto;
import tools.servlet.ProductAppDefaultServlet;

/**
 * 登録ページ用サーブレット
 */
public final class DispRegisterServlet extends ProductAppDefaultServlet {
    private static final String FORWARD_PAGE = "/WEB-INF/jsp/registerPage.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException
    {
        // 前準備
        doSetup(req, resp);

        List<VendorDto> vendors = new ArrayList<VendorDto>();

        // 仕入れ先コードを取得してリクエスト元に返却する
        VendorDao dao = new VendorDao();
        try {
            vendors = dao.read();
            if (vendors.isEmpty()) {
                req.setAttribute(FAILURE_MSG_KEY, "仕入れ先コードが１件も登録されていません。");
            } else {
                req.setAttribute("vendors", vendors);
            }
        } catch (SQLException ex) {
            req.setAttribute(FAILURE_MSG_KEY, "データベース処理エラーが発生しました。システム管理者に確認してください。");
        }

        doForward(FORWARD_PAGE, req, resp);
    }

}
