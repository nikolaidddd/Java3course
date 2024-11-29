
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html
PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/html"
      xmlns:h="http://xmlns.jcp.org/jsf/html">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Лаб. 8</title>
</head>

<body>
<jsp:useBean id="mybean" scope="session" class="jspappl.NameHandler" />
<jsp:useBean id="individual" scope="session" class="jspappl.BeanComponents" />

<%@ page language="java" %>
<%
    String data = request.getParameter("data");
    if (data == null){

    }
    else{
        String[] args = data.split(" ");

        individual.CalculateAnswer(args);
    }

%>

<h1>Главная страница </h1>
<h3>Введите данные для функции через пробел</h3>
<form name="Input form" action="jsp_2.jsp">
    <input type="text" name="data"/>
    <input type="submit" value="OK" name="button1" />
    <%mybean.addCounter(1);%>
    <h1>${mybean.counter}</h1>
</form>
<a href="jsp_3.jsp?values=${123}">Получить данные</a>
</body>
</html>