package dao.impl;

import bean.User;
import dao.AbstractDAO;
import dao.UserDaoInter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl extends AbstractDAO implements UserDaoInter {

    @Override
    public List<User> getAll() {
        List<User> result = new ArrayList<>();
        try (Connection c = connect()) {

            Statement stmt = c.createStatement();
            stmt.execute("select * from user");
            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                String phone = rs.getString("phone");
                String email = rs.getString("email");

                result.add(new User(id, name, surname, phone, email));
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public User getById(int userId) {
        User result = null;
        try (Connection c = connect()) {

            Statement stmt = c.createStatement();
            stmt.execute("select * from user where id = " + userId);
            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                String phone = rs.getString("phone");
                String email = rs.getString("email");

                result = new User(id, name, surname, phone, email);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;

    }

    @Override
    public boolean addUser(User u) {
        try (Connection c = connect()) {
            PreparedStatement ps = c.prepareStatement("insert into user(name, surname, phone, email) values(?,?,?,?)");
            ps.setString(1, u.getName());
            ps.setString(2, u.getSurname());
            ps.setString(3, u.getPhone());
            ps.setString(4, u.getEmail());
            return ps.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        }

        @Override
        public boolean updateUser (User u){
            try (Connection c = connect()) {
                PreparedStatement ps = c.prepareStatement("update user set name = ?, surname = ?, phone = ?, email = ? where id = ?");
                ps.setString(1, u.getName());
                ps.setString(2, u.getSurname());
                ps.setString(3, u.getPhone());
                ps.setString(4, u.getEmail());
                ps.setInt(5, u.getId());
                return ps.execute();
            } catch (Exception ex) {
                ex.printStackTrace();
                return false;
            }
        }

        @Override
        public boolean removeUser ( int id){
            try (Connection c = connect();) {
                Statement stmt = c.createStatement();
                return stmt.execute("delete from user where id =" + id);
            } catch (Exception ex) {
                ex.printStackTrace();
                return false;
            }
        }
    }

