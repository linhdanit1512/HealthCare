<%@page import="entity.Doctor"%>
<%@page import="DAO.DoctorDAO"%>

<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ADMIN_HEALTHCARE</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/datepicker3.css" rel="stylesheet">
<link href="css/bootstrap-table.css" rel="stylesheet">
<link href="css/styles.css" rel="stylesheet">
<link href="css/bs.css" rel="stylesheet">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.4.0/css/font-awesome.min.css" rel="stylesheet" type="text/css"> <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container"> 
 <div class="row"> 
  <h1 class="text-center">DANH SÁCH BÁC SĨ</h1> 
  <div class="col-md-10 col-md-offset-1"> 
   <div class="panel panel-default panel-table"> 
    <div class="panel-heading"> 
     <div class="row"> 
      <div class="col col-xs-6"> 
       <h3 class="panel-title">DANH SÁCH BÁC SĨ</h3> 
      </div> 
      <div class="col col-xs-6 text-right"> 
    
       <button type="button" class="btn btn-sm btn-primary btn-create">Thêm mới</button> 
      </div> 
     </div> 
    </div> 
    <div class="panel-body"> 
     
     <table class="table table-striped table-bordered table-list"> 
      <thead> 
       <tr> 
        <th><em class="fa fa-cog"></em>
        </th> 
        <th class="hidden-xs">Mã số</th> 
         <th>Username</th> 
        <th>Họ tên</th> 
        <th>Email</th> 
         <th>Password</th> 
       </tr> 
      </thead> 
      <tbody><tr> 
      	<%
      	
		List<Doctor> list = DoctorDAO.getAllDoctor();
		%>
       <% { for(int i=0;i<list.size();i++){ %>
       <td align="center"><a class="btn btn-default"><em class="fa fa-pencil"></em></a> <a class="btn btn-danger"><em class="fa fa-trash"></em></a>
       </td> 
       <td class="hidden-xs"><%=i+1 %></td> 
       <td> <%=list.get(i).getUserName() %></td> 
       <td><%=list.get(i).getNameDoctor()%></td>
       <td><%=list.get(i).getEmail() %></td> 
       <td><%=list.get(i).getPasswords()%></td>
      </tr> 
      
     <%}; %>
     </tbody></table> 
     <%} %>
    </div> 

   </div> 
  </div> 
 </div>
</div>
<script src="js/jquery-1.11.1.min.js"></script>
</body>
</html>