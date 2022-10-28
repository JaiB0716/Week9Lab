<%-- 
    Document   : users
    Created on : Oct 27, 2022, 11:18:07 AM
    Author     : Owner
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Application User Database</title>
    </head>
    <body>
        <c:if test="${usersInDB == True}">
            
        <h1>Manage Users</h1>
        
        <table>
            <tr>
                <th>Email</th><th>First Name</th><th>Last Name</th><th>Role</th><th></th><th></th>
            </tr>
            <tr><input type="text" name="email"></tr><tr><input type="text" name="firsName"></tr><tr><input type="text" name="lastName"></tr><tr><input type="text" name="role"></tr><tr><a href="<c:url value='/UserServlet?action=edit'/>">Edit</a></tr><tr><a href="<c:url value='/UserServlet?action=delete'/>">Delete</a></tr>
        </table>
        </c:if>
        
        <c:if test="${userInDB == false}">
            <h1>Manage Users</h1>
            
            <p>No Users Found. Please Add a User.</p>
        </c:if>
            
            
            <c:if test="${addUser == true}">
                <h1>Add User</h1>
                <table>
                    <tr>Email:</tr><tr><input type="text" name="insertedEmail" required></tr>
                    <tr>First Name:</tr><tr><input type="text" name="insertedFirstName" required></tr>
                    <tr>Last Name:</tr><tr><input type="text" name="insertedLastName" required></tr>
                    <tr>Password</tr><tr><input type="password" name="password" required></tr>
                    <tr>Role:</tr><tr><select name="role"><option value="sysAdmin">System Admin</option><option value="regUser">Regular User</option></select></tr>
                    <tr><input type="submit" value="Add User"></tr>
                </table>
                <c:if test="${blankFields == true}">
                    All Fields Required
                </c:if>
            </c:if>
        
            <c:if test="${editUser == true}">
                <h1>Edit User</h1>
                <table>
                    <tr>Email:</tr><tr><input type="text" name="insertedEmail" required></tr>
                    <tr>First Name:</tr><tr><input type="text" name="insertedFirstName" required></tr>
                    <tr>Last Name:</tr><tr><input type="text" name="insertedLastName" required></tr>
                    <tr>Password</tr><tr><input type="password" name="password" required></tr>
                    <tr>Role:</tr><tr><select name="role"><option value="sysAdmin">System Admin</option><option value="regUser">Regular User</option></select></tr>
                    <tr><input type="submit" value="Update"><input type="submit" value="Cancel"</tr>
                </table>
            </c:if>
    </body>
</html>
