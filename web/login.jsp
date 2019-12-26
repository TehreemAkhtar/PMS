
<%@page import="java.sql.SQLException"%>
<%@page import="java.util.logging.Logger"%>
<%@page import="java.util.logging.Level"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="DTO.jDBCConnection"%>
<%@page import="DTO.User"%>
<jsp:include page="resources/head.jsp" />
<jsp:include page="resources/navbar.jsp" />
<%
    String error="",err;
     HttpSession  httpSession = request.getSession();
     User user1= (User)httpSession.getAttribute("user");
     if(user1!=null)
     {
         response.sendRedirect("index.jsp");
     }
     
     if(request.getParameter("email")!=null)  //it will never be null
     {
         String email=request.getParameter("email");
         String password=request.getParameter("password");
         
          User user=User.isUserExist(email, password);
        
         if(user==null){
             error = "User does not exist";
        }
         else{
             out.print("<h1>user does exist></h1>");
             httpSession.setAttribute("user", user);
             response.sendRedirect("index.jsp");
         }
     }

%>

 <%
                if(error.compareTo("")!=0)
                {
            %>
<div class="alert alert-warning alert-dismissible fade show" role="alert">
  <strong>Failure!</strong> <%=error%>
  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
   
  </button>
</div>
<% } %>


<section class="ftco-section contact-section">
    <div class="container mt-5">
        <div class="row block-9">
           
            <div class="col-md-1"></div>
            <div class="col-md-6 ftco-animate">
                <form action="" class="contact-form" method="POST">
                    <div class="row">
                        <div class="col-md-6">
                            <div class="form-group">
                                <input type="email" class="form-control" placeholder="Email" name="email" required="">
                            </div>
                        </div>

                    </div>
                    <div class="row">
                        <div class="col-md-6">
                            <div class="form-group">
                                <input type="Password" class="form-control" placeholder="Password" name="password" required="">
                            </div>
                        </div></div>
                    <div class="form-group">
                        <input type="submit" value="Login" class="btn btn-primary py-3 px-5">
                    </div>
                    <div>
                        <div class="form-group">
                            <a href="#">Forgot Password? click here.</a>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</section>


<jsp:include page="resources/footer.jsp" />
