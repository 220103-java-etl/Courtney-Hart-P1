package dev.hart.repositories;

import dev.hart.models.Role;
import dev.hart.models.User;
import dev.hart.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static dev.hart.models.Role.EMPLOYEE;
import static dev.hart.models.Role.FINANCE_MANAGER;
import static java.lang.String.valueOf;

public class UserDAO {

    /**
     * Should retrieve a User from the DB with the corresponding username or an empty optional if there is no match.
     */
    /*public Optional<User> getByUsername(String username) {

       return Optional.empty();
    }*/

    /**
     * <ul>
     *     <li>Should Insert a new User record into the DB with the provided information.</li>
     *     <li>Should throw an exception if the creation is unsuccessful.</li>
     *     <li>Should return a User object with an updated ID.</li>
     * </ul>
     *
     * Note: The userToBeRegistered will have an id=0, and username and password will not be null.
     * Additional fields may be null.
     */
   /* public User create(User userToBeRegistered) {
        return userToBeRegistered;
    }*/

    static ConnectionFactory cu = ConnectionFactory.getInstance();

    //@Overide
    public User create(User user) {

        //String sql = "insert into users values (default, ?, ?, ?, ?, ?) returning *";
        switch (user.getRole()) {
            case EMPLOYEE:
                String sql = "insert into users values (default, ?, ?, ?, ?, ?) returning *";
                try (Connection conn = cu.getConnection()) {
                    PreparedStatement ps = conn.prepareStatement(sql);

                    ps.setString(1, user.getUsername());
                    ps.setString(2, user.getPassword());
                    ps.setString(3, valueOf(EMPLOYEE).toUpperCase());
                    ps.setDouble(4, user.getAmountPending());
                    ps.setDouble(5, user.getTotalAwarded());
                    // how do we know when to assign a user as Finance_manager or an Employee?

                    ResultSet rs = ps.executeQuery();

                    if (rs.next()) {
                        User u = new User(
                                rs.getInt("id"),
                                rs.getString("username"),
                                rs.getString("pass"),
                                Role.valueOf(rs.getString("role").toUpperCase()),
                                rs.getDouble("amount_pending"),
                                rs.getDouble("total_amount_awarded")
                        );
                        //user.setId(rs.getInt("id"));
                        return user;
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return null;

            case FINANCE_MANAGER:
                String sql1 = "insert into users values (default, ?, ?, ?, ?, ?) returning *";
                try (Connection conn = cu.getConnection()) {
                    PreparedStatement ps = conn.prepareStatement(sql1);

                    ps.setString(1, user.getUsername());
                    ps.setString(2, user.getPassword());
                    ps.setString(3, valueOf(FINANCE_MANAGER).toUpperCase());
                    ps.setDouble(4, user.getAmountPending());
                    ps.setDouble(5, user.getTotalAwarded());
                    // how do we know when to assign a user as Finance_manager or an Employee?

                    ResultSet rs = ps.executeQuery();

                    if (rs.next()) {
                        User u = new User(
                                rs.getInt("id"),
                                rs.getString("username"),
                                rs.getString("pass"),
                                Role.valueOf(rs.getString("role").toUpperCase()),
                                rs.getDouble("amount_pending"),
                                rs.getDouble("total_amount_awarded")
                        );
                        //user.setId(rs.getInt("id"));
                        return user;
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }

        }
        return null;
    }
    public Optional<User> getById(int id) {
        String sql = "select * from users where id=?";

        try (Connection conn = cu.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                User u = new User(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("pass"),
                        //rs.getObject("role"),
                        Role.valueOf(rs.getString("role")),
                        rs.getDouble("amount_pending"),
                        rs.getDouble("total_amount_awarded")
                        //String.valueOf(rs.getBoolean("role")) // we'll see what this does
                        //rs.getString("role")
                );
                return Optional.of(u);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public static Optional<User> getByUsername(String username) {

        String sql = "select * from users where username = ?";
        try (Connection conn = cu.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                User u = new User(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("pass"),
                        Role.valueOf(rs.getString("role")),
                        rs.getDouble("amount_pending"),
                        rs.getDouble("total_amount_awarded")
                );

                return Optional.of(u);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();

    }

    /*public static Optional<User> getByPassword(String password) {
        String sql = "select * from users where pass = ?";
        try (Connection conn = cu.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                User u = new User(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("pass"),
                        Role.valueOf(rs.getString("role"))
                );
                return Optional.of(u);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();

    }*/

    public static List<User>getAll() {
        List<User> users = new ArrayList<>();
        String sql = "select * from users";

        try(Connection conn = cu.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                User u = new User(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("pass"),
                        //Role.valueOf(rs.getString("role")),
                        //Role.valueOf(rs.getObject("role").toString()),  /*EMPLOYEE|| FINANCE_MANAGER.toString()*/
                        //Role.valueOf(rs.getString("role").toUpperCase()),
                        //EMPLOYEE,
                        Role.valueOf(rs.getString("role").replace(" ", "_").toUpperCase()),
                        rs.getDouble("amount_pending"),
                        rs.getDouble("total_amount_awarded")
                );
                        //user.setId(rs.getInt("id"));

                users.add(u);
            }

            return users;
        }catch(Exception e) {
            System.out.println("Error: " + e.getLocalizedMessage());
            e.printStackTrace();
        }


        return null;
    }

    public void update(User user) {
        String sql = "update users set username = ?, pass = ? where id = ?";
        try(Connection conn = cu.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());


            ps.executeUpdate();
        }catch(SQLException e) {
            e.printStackTrace();
        }
    }

    //@Override
    public void delete(Integer id) {
        String sql = "delete from users where id = ?";
        try(Connection conn = cu.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User updateUserAmount(User user) {
        String sql = "update users set total_amount_awarded = ? where id = ?";
        try(Connection conn = cu.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setDouble(1, user.getTotalAwarded());

            ps.executeUpdate();
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return user;
    }


}
