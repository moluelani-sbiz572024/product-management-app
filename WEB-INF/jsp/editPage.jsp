<%@ page contentType="text/html;charset=utf-8" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Objects" %>
<%@ page import="data.ProductDto" %>
<%@ page import="data.VendorDto" %>

<!-- 編集ページ -->
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
        String id = Objects.toString(request.getParameter("id"), "");
        String productCode = Objects.toString(request.getParameter("product_code"), "");
        String productName = Objects.toString(request.getParameter("product_name"), "");
        String price = Objects.toString(request.getParameter("price"), "");
        String stockQuantity = Objects.toString(request.getParameter("stock_quantity"), "");
        String vendorCode = Objects.toString(request.getParameter("vendor_code"), "");

        ArrayList<ProductDto> products = (ArrayList<ProductDto>)request.getAttribute("products");

        if (products != null && !products.isEmpty()) {
          ProductDto product = products.get(0);

          id = Integer.toString(product.getId());
          productCode = Integer.toString(product.getProductCode());
          productName = product.getProductName();
          price = Integer.toString(product.getPrice());
          stockQuantity = Integer.toString(product.getStockQuantity());
          vendorCode = Integer.toString(product.getVendorCode());
        }
        %>
        <article class="registration">
            <h1>商品編集</h1>
            <%
            String failureMessage = (String)request.getAttribute("failureMessage");
            if (failureMessage != null && !failureMessage.isEmpty()) {
              out.println(String.format("<p class='failure'>%s</p>", failureMessage));
            }
            %>
            <div class="back">
                <a href="<%= request.getContextPath() %>/list" class="btn">&lt; 戻る</a>
            </div>
            <form action="<%= request.getContextPath() %>/update?id=<%= id %>" method="post" class="registration-form">
                <div>
                  <label for="product_code">商品コード</label>
                  <input type="number" id="product_code" name="product_code" min="0" max="100000000" value="<%= productCode %>" required>
                  <label for="product_name">商品名</label>
                  <input type="text" id="product_name" name="product_name" maxlength="50" value="<%= productName %>" required>
                  <label for="price">単価</label>
                  <input type="number" id="price" name="price" min="0" max="100000000" value="<%= price %>" required>
                  <label for="stock_quantity">在庫数</label>
                  <input type="number" id="stock_quantity" name="stock_quantity" min="0" max="100000000" value="<%= stockQuantity %>" required>
                  <label for="vendor_code">仕入れ先コード</label>
                  <select id="vendor_code" name="vendor_code" required>
                      <option disabled selected value>選択してください</option>
                      <%
                      ArrayList<VendorDto> vendors = (ArrayList<VendorDto>)request.getAttribute("vendors");
                      if (vendors != null) {
                          for (VendorDto vendor : vendors) {
                              String nowVendorCode = Integer.toString(vendor.getVendorCode());
                              String selected = Objects.equals(vendorCode, nowVendorCode) ? "selected" : "";
                              out.println(String.format(
                                "<option value='%d' %s>%d</option>",
                                vendor.getVendorCode(), selected, vendor.getVendorCode()
                              ));
                          }
                      }
                      %>
                  </select>
                </div>
                <button type="submit" class="submit-btn" name="submit" value="update">更新</button>
            </form>
        </article>
    </main>
    <footer>
        <p class="copyright">&copy; 商品管理アプリ All rights reserved.</p>
    </footer>
</body>
</html>