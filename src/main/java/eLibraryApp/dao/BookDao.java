package eLibraryApp.dao;

import eLibraryApp.beans.BookBean;
import eLibraryApp.beans.IssueBookBean;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDao {

    //This method will use the BookBean object to add book data to database.
    public static int Save(BookBean bean){
        int status = 0;
        try{
            Connection con = DB.getCon();
            PreparedStatement preStm = con.prepareStatement("INSERT INTO e_book VALUES (?,?,?,?,?,?)");
            preStm.setString(1, bean.getCallNo());
            preStm.setString(2, bean.getName());
            preStm.setString(3, bean.getAuthor());
            preStm.setString(4, bean.getPublisher());
            preStm.setInt(5, bean.getQuantity());
            preStm.setInt(6, 0);
            status = preStm.executeUpdate();
            con.close();
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return status;
    }

    //Line 36: Method gets Book object data from the DB and store them in a List<BookBean> which is then returned by the method.
    public static List<BookBean> View(){
        List<BookBean> list = new ArrayList<BookBean>();
        try{
            Connection con = DB.getCon();
            PreparedStatement preStm = con.prepareStatement("SELECT * FROM e_book");
            ResultSet rs = preStm.executeQuery();

            while(rs.next()){
                BookBean bean = new BookBean();
                bean.setCallNo(rs.getString("callno"));
                bean.setName(rs.getString("name"));
                bean.setAuthor(rs.getString("author"));
                bean.setPublisher(rs.getString("publisher"));
                bean.setQuantity(rs.getInt("quantity"));
                bean.setIssued(rs.getInt("issued"));

                list.add(bean);
            }
            con.close();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return list;
    }

    //Line 63: This method takes callno of BookBean object and deletes it from the DB and updates.
    public static int Delete(String callno) {
        int status = 0;
        try{
            Connection con = DB.getCon();
            PreparedStatement preStm = con.prepareStatement("DELETE FROM e_book WHERE callno=?");
            preStm.setString(1, callno);
            status = preStm.executeUpdate();
            con.close();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }



        return status;
    }


    //Line 80 : This method checks the availability of the book from the callno.
    public static boolean CheckIssue(String callno){
        boolean status = false;
        try{
            Connection con = DB.getCon();
            PreparedStatement preStm = con.prepareStatement("SELECT * FROM e_book WHERE callno=? AND quantity>issued");
            preStm.setString(1, callno);
            ResultSet rs = preStm.executeQuery();
            if(rs.next()){
                status = true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return status;
    }

    //Line 97: This returns the amount of books issued through the callno
    public static int GetIssued(String callno){
        int issue = 0;
        try{
            Connection con =DB.getCon();
            PreparedStatement preStm = con.prepareStatement("SELECT * FROM e_book WHERE callno=?");
            preStm.setString(1, callno);
            ResultSet rs = preStm.executeQuery();
            while(rs.next()){
                issue =rs.getInt("issued");
            }
            con.close();
        }catch (SQLException e){
            e.printStackTrace();
        }

        return issue;
    }

    //Line 116: This method updates the DB if the book is
    public static int IssueBook(IssueBookBean bean){
        String callno = bean.getCallNo();
        boolean checkStatus = CheckIssue(callno);
        System.out.println("Check status:" + checkStatus);
        if(checkStatus){
            int status = 0;
            try{
                Connection con = DB.getCon();
                PreparedStatement preStm = con.prepareStatement("INSERT INTO e_issuebook VALUES (?,?,?,?,?,?)");
                preStm.setString(1, bean.getCallNo());
                preStm.setString(2, bean.getStudentId());
                preStm.setString(3, bean.getStudentName());
                preStm.setLong(4, bean.getStudentMobile());
                Date currentDate =new Date(System.currentTimeMillis());
                preStm.setDate(5, currentDate);
                preStm.setString(6, "No");

                status =  preStm.executeUpdate();

                if(status > 0){
                    PreparedStatement preStm2 =con.prepareStatement("UPDATE e_book SET issued=? WHERE callno=?");
                    preStm2.setInt(1, GetIssued(callno) + 1);
                    preStm2.setString(2, callno);
                    status = preStm2.executeUpdate();
                }

                con.close();

            } catch (SQLException e){
                e.printStackTrace();
            }

            return status;
        }

        return 0;
    }

    //Line 155: This method updates the e_issuebook database (DB) and e_book DB to visually show that a book has been returned.
    public static int ReturnBook(String callNo, int studentId){
        int status = 0;
        try{
            Connection con = DB.getCon();
            PreparedStatement preStm = con.prepareStatement("UPDATE e_issuebook SET returnstatus='Yes' WHERE callno=? AND studentid=?");
            preStm.setString(1, callNo);
            preStm.setInt(2, studentId);
            status = preStm.executeUpdate();

            if(status>0){
                PreparedStatement preStm2 = con.prepareStatement("UPDATE e_book SET issued=? WHERE callno=?");
                preStm2.setInt(1, GetIssued(callNo) - 1);
                preStm2.setString(2, callNo);
                status = preStm2.executeUpdate();
            }
            con.close();

        }catch (SQLException e){
            e.printStackTrace();
        }

        return status;
    }

    //Line 180: This method allows all issued book objects to be saved to a list from e_issuebook DB.
    public static List<IssueBookBean> ViewIssuedBooks(){
        List<IssueBookBean> list = new ArrayList<IssueBookBean>();
        try{
            Connection con=DB.getCon();
            PreparedStatement ps=con.prepareStatement("SELECT * FROM e_issuebook ORDER BY issueddate DESC");
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                IssueBookBean bean = new IssueBookBean();
                bean.setCallNo(rs.getString("callno"));
                bean.setStudentId(rs.getString("studentid"));
                bean.setStudentName(rs.getString("studentname"));
                bean.setStudentMobile(rs.getLong("studentmobile"));
                bean.setIssueDate(rs.getDate("issueddate"));
                bean.setReturnStatus(rs.getString("returnstatus"));
                list.add(bean);
            }
            con.close();

        }catch(SQLException e){
            e.printStackTrace();
        }

        return list;
    }













}

