/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebtechLec;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author james
 */
@WebServlet(name = "SAAdminServlet", urlPatterns = {"/SAAdminServlet"})
public class SAAdminServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
        response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
        response.setDateHeader("Expires", 0); // Proxies.
        response.setContentType("text/html;charset=UTF-8");
        String clients = request.getParameter("clients");
        String sp = request.getParameter("sp");
        String optionV = request.getParameter("usertype");
        response.setContentType("text/html");
        try (PrintWriter out = response.getWriter()) {
            HttpSession session = request.getSession(false);
            if(session == null){
                response.sendRedirect("index.html");  
            }else {
            ConnectDB db = new ConnectDB();
            Connection conn = db.getConn();
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pagefragments/SAheader.html");
            rd.include(request, response);
            String stmt;
            if (optionV.equals("All Accounts")) {
                out.println("<h1>All Accounts List</h1><br>");
                stmt = "select username, password, email, account_type, ar_status, ed_status from accounts;";
                PreparedStatement ps = conn.prepareStatement(stmt);
                ResultSet rs = ps.executeQuery();
                String table = "    <table>"
                        + "     <tr>"
                        + "         <th>Username</th>"
                        + "         <th>Password</th>"
                        + "         <th>Email</th>"
                        + "         <th>AccountType</th>"
                        + "         <th>Accept/Reject Status </th>"
                        + "         <th>Account Status</th>"
                        + "     </tr>";
                out.println(table);
                while(rs.next()){
                        out.println("           <tr>");
                        out.println("               <td>"+rs.getString("username")+"</td>");
                        out.println("               <td>"+rs.getString("password")+"</td>");
                        out.println("               <td>"+rs.getString("email")+"</td>");
                        out.println("               <td>"+rs.getString("account_type")+"</td>");
                        out.println("               <td>"+rs.getString("ar_status")+"</td>");
                        out.println("               <td>"+rs.getString("ed_status")+"<form method=\"post\" action=\"\"><button>Enable</button></form><form><button>Disable</button></form></td>");
                        out.println("           </tr>");
                }
                out.println("   </table>");
            }else if(optionV.equals("Customers")) {
                stmt = "select username, password, email, account_type from accounts where account_type='c';";
                out.println("<h1>Customer Users List</h1><br>");
                PreparedStatement ps = conn.prepareStatement(stmt);
                ResultSet rs = ps.executeQuery();
                String table = "    <table>"
                        + "     <tr>"
                        + "         <th>Username</th>"
                        + "         <th>Password</th>"
                        + "         <th>Email</th>"
                        + "         <th>AccountType</th>"
                        + "     </tr>";
                out.println(table);
                while(rs.next()){
                    out.println("           <tr>");
                    out.println("               <td>"+rs.getString("username")+"</td>");
                    out.println("               <td>"+rs.getString("password")+"</td>");
                    out.println("               <td>"+rs.getString("email")+"</td>");
                    out.println("               <td>"+rs.getString("account_type")+"</td>");
                    out.println("           </tr>");
                }
                out.println("   </table>");
            }else if(optionV.equals("Admins")) {
                stmt = "select username, password, email, account_type from accounts where account_type='sa' or account_type='a';";
                out.println("<h1>Admin Users List</h1><br>");
                PreparedStatement ps = conn.prepareStatement(stmt);
                ResultSet rs = ps.executeQuery();
                String table = "    <table>"
                        + "     <tr>"
                        + "         <th>Username</th>"
                        + "         <th>Password</th>"
                        + "         <th>Email</th>"
                        + "         <th>AccountType</th>"
                        + "     </tr>";
                out.println(table);
                while(rs.next()){
                    out.println("           <tr>");
                    out.println("               <td>"+rs.getString("username")+"</td>");
                    out.println("               <td>"+rs.getString("password")+"</td>");
                    out.println("               <td>"+rs.getString("email")+"</td>");
                    out.println("               <td>"+rs.getString("account_type")+"</td>");
                    out.println("           </tr>");
                }
                out.println("   </table>");
            }else if(optionV.equals("Service Provider")){
                stmt = "select username, password, email, account_type from accounts where account_type='sp';";
                out.println("<h1>Service Provider Users List</h1><br>");
                PreparedStatement ps = conn.prepareStatement(stmt);
                ResultSet rs = ps.executeQuery();
                String table = "    <table>"
                        + "     <tr>"
                        + "         <th>Username</th>"
                        + "         <th>Password</th>"
                        + "         <th>Email</th>"
                        + "         <th>AccountType</th>"
                        + "     </tr>";
                out.println(table);
                while(rs.next()){
                    out.println("           <tr>");
                    out.println("               <td>"+rs.getString("username")+"</td>");
                    out.println("               <td>"+rs.getString("password")+"</td>");
                    out.println("               <td>"+rs.getString("email")+"</td>");
                    out.println("               <td>"+rs.getString("account_type")+"</td>");
                    out.println("           </tr>");
                }
                out.println("   </table>");
            }else if(optionV.equals("UCustomers")) {
                stmt = "select first_name, last_name, address1, address2, contact_number, accepted from customer;";
                out.println("<h1>Customer User Information List</h1><br>");
                PreparedStatement ps = conn.prepareStatement(stmt);
                ResultSet rs = ps.executeQuery();
                String table = "    <table>"
                        + "     <tr>"
                        + "         <th>First Name</th>"
                        + "         <th>Last Name</th>"
                        + "         <th>Address 1</th>"
                        + "         <th>Address 2</th>"
                        + "         <th>Contact Number</th>"
                        + "         <th>Account Status</th>"
                        + "     </tr>";
                out.println(table);
                while(rs.next()){
                    out.println("           <tr>");
                    out.println("               <td>"+rs.getString("first_name")+"</td>");
                    out.println("               <td>"+rs.getString("last_name")+"</td>");
                    out.println("               <td>"+rs.getString("address1")+"</td>");
                    out.println("               <td>"+rs.getString("address2")+"</td>");
                    out.println("               <td>"+rs.getString("contact_number")+"</td>");
                    out.println("               <td>"+rs.getString("accepted")+"</td>");
                    out.println("           </tr>");
                }
                out.println("   </table>");
            }else if(optionV.equals("UAdmins")) {
                stmt = "select admin_name, admin_contact, account_id from admin;";
                out.println("<h1>Admin User Information List</h1><br>");
                PreparedStatement ps = conn.prepareStatement(stmt);
                ResultSet rs = ps.executeQuery();
                String table = "    <table>"
                        + "     <tr>"
                        + "         <th>Name</th>"
                        + "         <th>Contact</th>"
                        + "         <th>Account ID</th>"
                        + "     </tr>";
                out.println(table);
                while(rs.next()){
                    out.println("           <tr>");
                    out.println("               <td>"+rs.getString("admin_name")+"</td>");
                    out.println("               <td>"+rs.getString("admin_contact")+"</td>");
                    out.println("               <td>"+rs.getString("account_id")+"</td>");
                    out.println("           </tr>");
                }
                out.println("   </table>");
            }else if(optionV.equals("UService Provider")) {
                stmt = "select provider_name, provider_contact, provider_address, account_id from service_provider;";
                out.println("<h1>Service Provider User Information List</h1><br>");
                PreparedStatement ps = conn.prepareStatement(stmt);
                ResultSet rs = ps.executeQuery();
                String table = "    <table>"
                        + "     <tr>"
                        + "         <th>Name</th>"
                        + "         <th>Contact</th>"
                        + "         <th>Address</th>"
                        + "         <th>Account ID</th>"
                        + "     </tr>";
                out.println(table);
                while(rs.next()){
                    out.println("           <tr>");
                    out.println("               <td>"+rs.getString("provider_name")+"</td>");
                    out.println("               <td>"+rs.getString("provider_contact")+"</td>");
                    out.println("               <td>"+rs.getString("provider_address")+"</td>");
                    out.println("               <td>"+rs.getString("account_id")+"</td>");
                    out.println("           </tr>");
                }
                out.println("   </table>");
            }else if(optionV.equals("Customers2")){
                stmt = "select username, password, email, account_type, ar_status, ed_status from accounts where ar_status='Pending' and account_type='c';";
                out.println("<h1>Pending Customer Accounts</h1><br>");
                PreparedStatement ps = conn.prepareStatement(stmt);
                ResultSet rs = ps.executeQuery();
                String table = "    <table>"
                        + "     <tr>"
                        + "         <th>Username</th>"
                        + "         <th>Password</th>"
                        + "         <th>Email</th>"
                        + "         <th>AccountType</th>"
                        + "         <th>Accept/Reject Status </th>"
                        + "     </tr>";
                out.println(table);
                while(rs.next()){
                        out.println("           <tr>");
                        out.println("               <td>"+rs.getString("username")+"</td>");
                        out.println("               <td>"+rs.getString("password")+"</td>");
                        out.println("               <td>"+rs.getString("email")+"</td>");
                        out.println("               <td>"+rs.getString("account_type")+"</td>");
                        out.println("               <td>"+rs.getString("ar_status")+"<form method=\"post\" action=\"\"><button>Accept</button></form><form><button>Reject</button></form></td>");
                        out.println("           </tr>");
                }
                out.println("   </table>");
            }else if(optionV.equals("Service Provider2")){
                stmt = "select username, password, email, account_type, ar_status, ed_status from accounts where ar_status='Pending' and account_type='sp';";
                out.println("<h1>Pending Service Provider Accounts</h1><br>");
                PreparedStatement ps = conn.prepareStatement(stmt);
                ResultSet rs = ps.executeQuery();
                String table = "    <table>"
                        + "     <tr>"
                        + "         <th>Username</th>"
                        + "         <th>Password</th>"
                        + "         <th>Email</th>"
                        + "         <th>AccountType</th>"
                        + "         <th>Accept/Reject Status </th>"
                        + "     </tr>";
                out.println(table);
                while(rs.next()){
                        out.println("           <tr>");
                        out.println("               <td>"+rs.getString("username")+"</td>");
                        out.println("               <td>"+rs.getString("password")+"</td>");
                        out.println("               <td>"+rs.getString("email")+"</td>");
                        out.println("               <td>"+rs.getString("account_type")+"</td>");
                        out.println("               <td>"+rs.getString("ar_status")+"<form method=\"post\" action=\"\"><button>Accept</button></form><form><button>Reject</button></form></td>");
                        out.println("           </tr>");
                }
                out.println("   </table>");
            }else if(optionV.equals("Transaction")){
                stmt = "select first_name, last_name, item_name, price, date_rented, provider_name from customer join transaction on customer.customer_id = transaction.cust_id join items on items.item_id = transaction.item_id join service_provider on items.provider_id = service_provider.provider_id;";
                out.println("<h1>Transactions</h1><br>");
                PreparedStatement ps = conn.prepareStatement(stmt);
                ResultSet rs = ps.executeQuery();
                String table = "    <table>"
                        + "     <tr>"
                        + "         <th>First Name</th>"
                        + "         <th>Last Name</th>"
                        + "         <th>Item Name</th>"
                        + "         <th>Service Provider</th>"
                        + "         <th>Price</th>"
                        + "         <th>Date Rented</th>"
                        + "     </tr>";
                out.println(table);
                while(rs.next()){
                        out.println("           <tr>");
                        out.println("               <td>"+rs.getString("first_name")+"</td>");
                        out.println("               <td>"+rs.getString("last_name")+"</td>");
                        out.println("               <td>"+rs.getString("item_name")+"</td>");
                        out.println("               <td>"+rs.getString("provider_name")+"</td>");
                        out.println("               <td>"+rs.getString("price")+"</td>");
                        out.println("               <td>"+rs.getString("date_rented")+"</td>");

                        out.println("           </tr>");
                }
                out.println("   </table>");
            }
            rd = request.getRequestDispatcher("/WEB-INF/pagefragments/SAfooter.html");
            rd.include(request, response);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SAAdminServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
