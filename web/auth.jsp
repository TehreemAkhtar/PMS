
    <%@page import="DTO.User"%>
<%
     HttpSession  httpSession = request.getSession();
     User user1= (User)httpSession.getAttribute("user");
     if(user1!=null)
     {
//        if(user1.getAccountType()==1){
//            response.sendRedirect("#");
//        }
         response.sendRedirect("index.jsp");
     }
     
 response.sendRedirect("login.jsp");
     
    
%>