<%-- 
    Document   : imagenPuntoM
    Created on : 23/09/2011, 05:59:04 PM
    Author     : USUARIO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
        String puntoM=request.getParameter("puntom");
        System.out.println("imagen punto m"+puntoM);
        %>

        <input type="text" name="puntom" value="<%=puntoM %>" style="position: absolute; left:8px; top:102px" />
        <img src="Imagenes/IPuntoMuestreo.png" width="128" height="89" alt="IPuntoMuestreo" style="position: absolute; left: 8px; top: 12px"/>

        


    </body>
</html>
