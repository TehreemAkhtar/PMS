<%@page import="DTO.User"%>
<%
    String error="",err;
     HttpSession  httpSession = request.getSession();
     User user1= (User)httpSession.getAttribute("user");
     if(user1!=null)
     {
        httpSession.invalidate();
     }
 response.sendRedirect("login.jsp");
     
    
%>