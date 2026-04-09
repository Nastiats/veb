<%@ page language="java" pageEncoding="UTF-8"%>

<html>
  <head lang="ru">
         <meta charset="UTF-8">
         <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
      <title>Форма входа</title>
  </head>
  <body>

    <form action="loginCheck" method="POST">
       Email : <input type="text" name="email" /> <br />
       Пароль : <input type="password" name="pwd" /> <br />
       <input type="submit" value="Вход" /> <br />
     </form>
  </body>
</html>
