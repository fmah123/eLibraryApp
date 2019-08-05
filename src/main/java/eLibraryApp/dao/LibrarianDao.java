package eLibraryApp.dao;

import eLibraryApp.beans.LibrarianBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LibrarianDao {

    //Line 12: Adds a new librarian object to the e_librarian DB.
    public static int Save(LibrarianBean bean){
        int status = 0;
        try{
            Connection con = DB.getCon();
            PreparedStatement preStm = con.prepareStatement("INSERT INTO e_librarian(name,email,password,mobile) VALUES (?,?,?,?)");
            preStm.setString(1,bean.getName());
            preStm.setString(2,bean.getEmail());
            preStm.setString(3,bean.getPassword());
            preStm.setLong(4,bean.getMobile());
            status = preStm.executeUpdate();
            con.close();

        } catch(SQLException e){
            e.printStackTrace();
        }

        return status;
    }

    //Line 32: updates a librarian object that already exists.
    public static int Update(LibrarianBean bean){
        int status = 0;
        try{
            Connection con = DB.getCon();
            PreparedStatement preStm = con.prepareStatement("UPDATE e_librarian SET name=?,email=?,password=?,mobile=? WHERE id=?");
            preStm.setString(1,bean.getName());
            preStm.setString(2,bean.getEmail());
            preStm.setString(3,bean.getPassword());
            preStm.setLong(4,bean.getMobile());
            preStm.setInt(5,bean.getId());
            status = preStm.executeUpdate();
            con.close();

        }catch(SQLException e) {
            e.printStackTrace();
        }

        return status;
    }

    //Line 56: Librarian data is uploaded and stored in a LibrarianBean object that is saved to a list and returned.
    public static List<LibrarianBean> View(){
        List<LibrarianBean> list = new ArrayList<LibrarianBean>();
        try{
            Connection con = DB.getCon();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM e_librarian");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                LibrarianBean bean = new LibrarianBean();
                bean.setId(rs.getInt("id"));
                bean.setName(rs.getString("name"));
                bean.setEmail(rs.getString("email"));
                bean.setPassword(rs.getString("password"));
                bean.setMobile(rs.getLong("mobile"));
                list.add(bean);
            }
            con.close();

        }catch(SQLException e){
            e.printStackTrace();
        }

        return list;
    }

    //Line 81: Returns a LibrarianBean object through an id of a certain Librarian that exists in DB.
    public static LibrarianBean ViewById(int id){
        LibrarianBean bean = new LibrarianBean();
        try{
            Connection con = DB.getCon();
            PreparedStatement preStm = con.prepareStatement("SELECT * FROM e_librarian WHERE id=?");
            preStm.setInt(1,id);
            ResultSet rs = preStm.executeQuery();
            if(rs.next()){
                bean.setId(rs.getInt(1));
                bean.setName(rs.getString(2));
                bean.setPassword(rs.getString(3));
                bean.setEmail(rs.getString(4));
                bean.setMobile(rs.getLong(5));
            }
            con.close();

        }catch(SQLException e){
            e.printStackTrace();
        }

        return bean;
    }

    //Line 105: Deletes a librarian data from e_librarian DB through id.
    public static int Delete(int id){
        int status = 0;
        try{
            Connection con = DB.getCon();
            PreparedStatement preStm = con.prepareStatement("delete from e_librarian where id=?");
            preStm.setInt(1,id);
            status = preStm.executeUpdate();
            con.close();

        }catch(Exception e){
            e.printStackTrace();
        }

        return status;
    }

    //Line 122: Authenticates by Librarian username and password, and checks if it exists.
    public static boolean Authenticate(String email, String password){
        boolean status = false;
        try{
            Connection con = DB.getCon();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM e_librarian WHERE email=? AND password=?");
            ps.setString(1,email);
            ps.setString(2,password);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                status = true;
            }
            con.close();

        }catch(Exception e){System.out.println(e);}

        return status;
    }

}
