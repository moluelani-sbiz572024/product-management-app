<%@ page contentType="text/html; charset=utf-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>商品管理アプリ</title>
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
        <article class="home">
            <h1>商品管理アプリ</h1>
            <p>【Javaとデータベースで商品管理アプリを作ろう】成果物</p>
            <a href="<%= request.getContextPath()%>/list" class="btn">商品一覧</a>
        </article>
    </main>
    <footer>
        <p class="copyright">&copy; 商品管理アプリ All rights reserved.</p>
    </footer>
</body>
</html>