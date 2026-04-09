<%@ page language="java" pageEncoding="UTF-8"%>

<!doctype html>
<html lang="ru"><head>
       <meta charset="UTF-8">
       <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
     <title>Регистрация пользователя</title>
</head><body>
  <form action="user/register" method="POST">
        ФИО: <input type="text" name="name" /> <br />
        Email: <input type="text" name="email" /> <br />
        Контакт: <input type="text" name="contact" /> <br />
        Пароль: <input type="password" name="pwd" /> <br />
        <input type="submit" value="Зарегистрировать" /> <br />
  </form>
</body>
</html>