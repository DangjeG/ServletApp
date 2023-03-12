<%@page import="java.time.LocalDateTime"%>
<%@ page import="java.util.Date" %>
<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
    <title>Dir.com</title>
</head>
<body>
<h1>${LocalDateTime.now().toString()}</h1>
<h1>${name}</h1>
<hr/>
<div>
    <table>
        <thead>
        <th>Файл</th>
        <th>Размер</th>
        <th>Дата</th>
        </thead>
        <tbody>
        <form method="post" action="/files">
            <button name="btn" type="submit" value=" ">...</button>
        </form>
        <colgroup span="4"></colgroup>
        <c:forEach items="${files}" var="item">
            <tr>
                <colgroup span="4"></colgroup>
                <td>
                    <form method="post" action="/files">
                        <button name="btn" type="submit" value="${item.getAbsolutePath()}">${item.getName()}</button>
                    </form>
                </td>
                <td>
                        ${item.length()}
                </td>
                <td>
                        ${Date(item.lastModified())}
                </td>
                <td>
                    <form method="get" action="/download">
                        <button name="btn" type="submit" value="${item.getAbsolutePath()}">Скачать</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
