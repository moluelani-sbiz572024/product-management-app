<%@ page contentType="text/html;charset=utf-8" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Objects" %>
<%@ page import="data.ProductDto" %>

<!-- 一覧ページ -->
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>商品一覧</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Noto+Sans+JP&display=swap">
</head>

<body>
    <header>
        <nav>
            <a href="<%= request.getContextPath()%>/">商品管理アプリ</a>
        </nav>
    </header>
    <main>
        <%
        String keyword = request.getParameter("keyword");
        keyword = Objects.toString(keyword, "");

        String order = request.getParameter("order");
        order = Objects.toString(order, "");
        %>
        <article class="products">
            <h1>商品一覧</h1>
            <%
            String successMessage = (String)request.getAttribute("successMessage");
            if (successMessage != null && !successMessage.isEmpty()) {
                out.println(String.format("<p class='success'>%s</p>", successMessage));
            }

            String failureMessage = (String)request.getAttribute("failureMessage");
            if (failureMessage != null && !failureMessage.isEmpty()) {
              out.println(String.format("<p class='failure'>%s</p>", failureMessage));
            }
            %>
            <div class="products-ui">
                <div>
                    <a href="<%= request.getContextPath() %>/list?order=desc&keyword=<%= keyword %>">
                        <img src="images/desc.png" alt="降順に並べ替え" class="sort-img">
                    </a>
                    <a href="<%= request.getContextPath() %>/list?order=asc&keyword=<%= keyword %>">
                        <img src="images/asc.png" alt="昇順に並べ替え" class="sort-img">
                    </a>
                    <form action="<%= request.getContextPath() %>/list" method="get" class="search-form">
                        <input type="hidden" name="order" value="<%= order %>">
                        <input type="text" class="search-box" placeholder="商品名で検索" name="keyword" value="<%= keyword %>">
                    </form>
                </div>
                <a href="<%= request.getContextPath() %>/register" class="btn">商品登録</a>
            </div>
            <table class="products-table">
                <tr>
                    <th class="hidden-id">ID</th>
                    <th>商品コード</th>
                    <th>商品名</th>
                    <th>単価</th>
                    <th>在庫数</th>
                    <th>仕入れ先コード</th>
                    <th>編集</th>
                    <th>削除</th>
                </tr>
                <%
                ArrayList<ProductDto> products = (ArrayList<ProductDto>)request.getAttribute("products");
                if (products != null) {
                  for (ProductDto product : products) {
                    out.println("<tr>");
                    out.println(String.format("<td class='hidden-id'>%s</td>", product.getId()));
                    out.println(String.format("<td>%s</td>", product.getProductCode()));
                    out.println(String.format("<td>%s</td>", product.getProductName()));
                    out.println(String.format("<td>%s</td>", product.getPrice()));
                    out.println(String.format("<td>%s</td>", product.getStockQuantity()));
                    out.println(String.format("<td>%s</td>", product.getVendorCode()));
                    out.println(String.format(
                      "<td><a href='%s/edit?id=%s'><img src='%s' alt='編集' class='%s'></a></td>",
                      request.getContextPath(), product.getId(), "images/edit.png", "edit-icon"
                    ));
                    out.println(String.format(
                      "<td><a href='%s/delete?id=%s'><img src='%s' alt='削除' class='%s' onclick=\"%s\"></a></td>",
                      request.getContextPath(), product.getId(), "images/delete.png", "delete-icon",
                      String.format("return confirm('この操作は元に戻せません。商品名：%s を本当に削除しますか？')", product.getProductName())
                    ));
                    out.println("</tr>");
                  }
                }
                %>
            </table>
        </article>
    </main>
    <footer>
        <p class="copyright">&copy; 商品管理アプリ All rights reserved.</p>
    </footer>
</body>
</html>