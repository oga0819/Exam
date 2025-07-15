<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../header.html" %>
<!DOCTYPE html>
<html lang="ja">
<head>
  <meta charset="UTF-8">
  <title>ログイン</title>
  <link rel="stylesheet" type="text/css" href="../css/login.css">
  <script>
    // パスワード表示切替
    function togglePassword() {
      const pw = document.getElementById('password');
      pw.type = pw.type === 'password' ? 'text' : 'password';
    }
  </script>
</head>
<body>
  <h2 class="system-title">得点管理システム</h2>

  <div class="login-container">
    <div class="login-header">ログイン</div>
    <!-- エラーメッセージがある場合に表示 -->
    <c:if test="${not empty error}">
    <p style="color:red; font-size: 10px; text-align: center;">${error}</p>
</c:if>
    <form action="<%=request.getContextPath()%>/scoremanager/LoginExecute.action" method="post" autocomplete="off">
      <div class="login-form-area">
        <div class="login-input-area">
          <label class="login-label" for="id">ID</label>
          <input type="text" name="id" id="id" maxlength="10" inputmode="latin" required
                 placeholder="半角でご入力ください">

          <label class="login-label" for="password">パスワード</label>
          <input type="password" name="password" id="password" maxlength="30" inputmode="latin" required
                 placeholder="30文字以内の半角英数字でご入力ください">

          <div class="login-password-row">
            <label class="login-showpw" for="chk_d_ps">
              <input type="checkbox" id="chk_d_ps" onclick="togglePassword()">
              パスワードを表示
            </label>
          </div>
        </div>

        <div class="login-btn-row">
          <input type="submit" class="login-btn" value="ログイン">
        </div>


      </div>
    </form>
  </div>
</body>
</html>
<%@include file="../footer.html" %>
