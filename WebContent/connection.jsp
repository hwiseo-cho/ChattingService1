<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ page import="java.sql.*, javax.sql.*, java.io.*, javax.naming.InitialContext, javax.naming.Context" %>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		InitialContext initCtx = new InitialContext();
		Context envContext = (Context)initCtx.lookup("java:/comp/env");
		DataSource ds = (DataSource)envContext.lookup("jdbc/oraDB");
		Connection con = ds.getConnection();
		Statement stmt = con.createStatement();
		ResultSet rset = stmt.executeQuery("SELECT * FROM PRODUCT_COMPONENT_VERSION");

		while(rset.next()) {
			out.println(rset.getString("PRODUCT"));
		}
		rset.close();
		stmt.close();
		con.close();
		initCtx.close();
	%>
</body>
</html>