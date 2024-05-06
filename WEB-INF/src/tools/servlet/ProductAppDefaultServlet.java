package tools.servlet;

import java.io.IOException;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 商品管理アプリ専用デフォルトサーブレット（抽象型）
 */
public abstract class ProductAppDefaultServlet extends HttpServlet {
    private static final String ENC_UTF8 = "utf-8";

    // 成功・失敗時メッセージの格納キー
    protected static final String SUCCESS_MSG_KEY = "successMessage";
    protected static final String FAILURE_MSG_KEY = "failureMessage";

    /**
     * リクエスト受診直後の前準備をサポートする
     * １）リクエストストリームの文字コード：UTF-8
     * ２）レスポンスストリームのMIMEタイプ：text/html; charset=utf-8
     * @param request リクエストストリーム
     * @param response レスポンスストリーム
     * @throws ServletException サーブレット例外
     * @throws IOException 入出力（ストリーム）例外
     */
    protected void doSetup(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        request.setCharacterEncoding(ENC_UTF8);
        response.setContentType(String.format("text/html; charset=%s", ENC_UTF8));
    }

    /**
     * 文字数字を数値に型変換する
     * 主にリクエストパラメーター値（文字列）を数値に変換する時に使用する
     * @param request リクエストストリーム
     * @param key 抽出キー
     * @return 変換結果（数値）
     * @throws NumberFormatException 数値形式例外
     */
    protected int extractParamToInteger(HttpServletRequest request, String key)
        throws NumberFormatException
    {
        return Integer.parseInt(request.getParameter(key));
    }

    /**
     * オブジェクト型を文字列型に変換する
     * 主にリクエストストリームから取得したデータの抽出に使用する
     * @param request リクエストストリーム
     * @param key 抽出キー
     * @return 変換結果（文字列）
     * @throws ClassCastException キャスト例外
     */
    protected String extractAttribToString(HttpServletRequest request, String key)
        throws ClassCastException
    {
        return (String)request.getAttribute(key);
    }

    /**
     * 文字列のヌル値を空文字列化する
     * 主にリクエストパラメーター値（文字列）取得時に使用する
     * @param request リクエストストリーム
     * @param key 抽出キー
     * @return 変換結果（文字列）
     */
    protected String extractNullToString(HttpServletRequest request, String key) {
        return Objects.toString(request.getParameter(key), "");
    }

    /**
     * フォワード処理をサポートする
     * @param uri フォワード先URI
     * @param request リクエストストリーム
     * @param response レスポンスストリーム
     * @throws ServletException サーブレット例外
     * @throws IOException 入出力（ストリーム）例外
     */
    protected void doForward(String uri, HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        request.getRequestDispatcher(uri).forward(request, response);
    }

    /**
     * 例外の読み替えをサポートする
     * @param exception 読み替え対象の例外情報
     * @throws ServletException 読み替えた結果のサーブレット例外
     */
    protected void doExceptedProcess(Exception exception) throws ServletException {
        throw new ServletException(exception);
    }
}
