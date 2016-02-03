<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html>
	<head>
	
	<title>Index</title>
	<style>
		.container {
			margin: 0 auto;
			padding: 50px 40%;
			box-sizing: border-box;
		}

		.container input,
		.container span,
		.container button {
			display: block;
			width: 100%;
			margin-bottom: 20px;
			box-sizing: border-box;
		}
	</style>
	</head>  
 <body>
	<form id="loginForm" method="post" >
		 <div class="container">
		 	<span>User : </span>
		 	<input id="username" name="username" type="text" value="${loginControllerBean.loginCommand.username}" maxlength="128" />
		 
		 	<span>Pasword : </span>	
		 	<input id="password" name="password" type="password" value="${loginControllerBean.loginCommand.password}"/>
		 	
		 	<input class="btnEnter" value="Login" type="submit" />
		 </div>
	 </form>	
 </body>
</html> 
