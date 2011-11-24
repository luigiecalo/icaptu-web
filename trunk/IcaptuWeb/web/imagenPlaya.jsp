<%-- 
    Document   : imagen
    Created on : 22/09/2011, 09:12:02 AM
    Author     : USUARIO
--%>

<%@page import="Controladores.controlPlayas"%>
<%@page import="java.io.OutputStream"%>
<%@page import="java.io.InputStream"%>
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
        int codigo=Integer.parseInt(request.getParameter("codigo"));
            
                    // Lees el blob
            InputStream is = controlPlayas.consultarImagen(codigo).getBinaryStream();
            OutputStream os = response.getOutputStream();

            // Lo mandas a la salida de la JSP
            byte [] bytes = new byte[1024];
            int n;
            while ((n = is.read(bytes)) != -1) {
            os.write(bytes, 0, n);
            }

        %>
    </body>
</html>
