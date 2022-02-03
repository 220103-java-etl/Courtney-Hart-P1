package dev.hart.repositories;

import dev.hart.models.Reimbursement;
import dev.hart.models.Status;
import dev.hart.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static dev.hart.models.Status.*;
import static java.lang.String.valueOf;

public class ReimbursementDAO {

    /**
     * Should retrieve a Reimbursement from the DB with the corresponding id or an empty optional if there is no match.
     */
    static ConnectionFactory cu = ConnectionFactory.getInstance();
    public static Optional<Reimbursement> getById(int id) {
        String sql = "select * from reimbursements where id=?";
        try (Connection conn = cu.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Reimbursement r = new Reimbursement(
                        rs.getInt("id"),
                        Status.valueOf(rs.getString("role")),
                        rs.getString("author"),
                        rs.getString("resolver"),
                        rs.getInt("amount"),
                        rs.getInt("eventcost"),
                        rs.getString("name"),
                        rs.getString("grade"),
                        rs.getString("date"),
                        rs.getString("location"),
                        rs.getString("description"),
                        rs.getString("gradingformat"),
                        rs.getString("eventtype")
                );
                return Optional.of(r);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();

    }

    /**
     * Should retrieve a List of Reimbursements from the DB with the corresponding Status or an empty List if there are no matches.
     */
    public static List<Reimbursement> getByStatus(Status status) {
        List<Reimbursement> reimbursements = new ArrayList<>();
        String sql = "select * from reimbursements where role=?";
        try(Connection conn = cu.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, String.valueOf(status));
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Reimbursement r = new Reimbursement(
                        rs.getInt("id"),
                        //Status.valueOf(rs.getString("role")),
                        rs.getString("role"),
                        rs.getString("author"),
                        rs.getString("resolver"),
                        rs.getInt("amount"),
                        rs.getInt("eventcost"),
                        rs.getString("name"),
                        rs.getString("grade"),
                        rs.getString("date"),
                        rs.getString("location"),
                        rs.getString("description"),
                        rs.getString("gradingformat"),
                        rs.getString("eventtype")
                );
                reimbursements.add(r);
            }
            return reimbursements;
        }catch(Exception e) {
            System.out.println("Error: " + e.getLocalizedMessage());
            e.printStackTrace();
        }

        return Collections.emptyList();
    }

    /**
     * <ul>
     *     <li>Should Update an existing Reimbursement record in the DB with the provided information.</li>
     *     <li>Should throw an exception if the update is unsuccessful.</li>
     *     <li>Should return a Reimbursement object with updated information.</li>
     * </ul>
     */
    public Reimbursement update(Reimbursement unprocessedReimbursement) {
        switch (unprocessedReimbursement.getStatus()) {
            case APPROVED:
                String sql = "update reimbursements set role = ? where id = ?";
                try (Connection conn = cu.getConnection()) {
                    PreparedStatement ps = conn.prepareStatement(sql);
                    //ps.setString(1, String.valueOf(unprocessedReimbursement.getStatus()));
                    ps.setString(1, valueOf(APPROVED).toUpperCase());
                    ps.setInt(2, unprocessedReimbursement.getId());

                   // ps.setObject(2, unprocessedReimbursement.getResolver());
                    // ps.setDouble(3, unprocessedReimbursement.getIntAmount());


                    ps.executeUpdate();
                    return unprocessedReimbursement;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return null;

            case DENIED:
                String sql1 = "update reimbursements set role = ? where id = ?";
                try (Connection conn = cu.getConnection()) {
                    PreparedStatement ps = conn.prepareStatement(sql1);
                    //ps.setString(1, String.valueOf(unprocessedReimbursement.getStatus()));
                    ps.setString(1, valueOf(DENIED).toUpperCase());
                    ps.setInt(2, unprocessedReimbursement.getAuthor().getId());
                    //ps.setObject(2, unprocessedReimbursement.getResolver());
                    // ps.setDouble(3, unprocessedReimbursement.getIntAmount());


                    ps.executeUpdate();
                    return unprocessedReimbursement;
                } catch (SQLException e) {
                    e.printStackTrace();
                }

        }
        return null;
    }

    public static List<Reimbursement>getAllReimbursements() {
        List<Reimbursement> reimbursements = new ArrayList<>();
        String sql = "select * from reimbursements";

        try(Connection conn = cu.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Reimbursement r = new Reimbursement(
                        rs.getInt("id"),
                        Status.valueOf(rs.getString("role")),
                        //rs.getString("role"),
                        rs.getString("author"),
                        rs.getString("resolver"),
                        rs.getInt("amount"),
                        rs.getInt("eventcost"),
                        rs.getString("name"),
                        rs.getString("grade"),
                        rs.getString("date"),
                        rs.getString("location"),
                        rs.getString("description"),
                        rs.getString("gradingformat"),
                        rs.getString("eventtype")
                );
                //reimbursements.setId(rs.getInt("id"));

                reimbursements.add(r);
            }

            return reimbursements;
        }catch(Exception e) {
            System.out.println("Error: " + e.getLocalizedMessage());
            e.printStackTrace();
        }


        return null;
    }

   public static Optional<Reimbursement> getByAuthor(String author) {
        String sql = "select * from reimbursements where author=?";
        try (Connection conn = cu.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, author);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Reimbursement r = new Reimbursement(
                        rs.getInt("id"),
                        Status.valueOf(rs.getString("role")),
                        rs.getString("author"),
                        rs.getString("resolver"),
                        rs.getInt("amount"),
                        rs.getInt("eventcost"),
                        rs.getString("name"),
                        rs.getString("grade"),
                        rs.getString("date"),
                        rs.getString("location"),
                        rs.getString("description"),
                        rs.getString("gradingformat"),
                        rs.getString("eventtype")
                );
                return Optional.of(r);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();

    }
    public static Reimbursement create(Reimbursement reimbursement){

        String sql = "insert into reimbursements values (default, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) returning *";
        try (Connection conn = cu.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);

                            ps.setString(1, PENDING.toString());
                            ps.setString(2, reimbursement.getAuthorString());
                            ps.setString(3, reimbursement.getResolverString());
                            ps.setString(4, reimbursement.getName());
                            ps.setString(5, reimbursement.getGrade());
                            ps.setString(6, reimbursement.getDate());
                            ps.setString(7, reimbursement.getLocation());
                            ps.setString(8, reimbursement.getDescription());
                            ps.setString(9, reimbursement.getGradingFormat());
                            ps.setString(10, reimbursement.getEventType());
                            ps.setInt(11, reimbursement.getCost());
                            ps.setInt(12, reimbursement.getIntAmount());

                            //ps.setString(13, reimbursement.getUserID());


            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Reimbursement r = new Reimbursement(
                        rs.getInt("id"),
                        rs.getString("role"),
                        rs.getString("author"),
                        rs.getString("resolver"),
                        rs.getInt("amount"),
                        rs.getInt("eventcost"),
                        rs.getString("name"),
                        rs.getString("grade"),
                        rs.getString("date"),
                        rs.getString("location"),
                        rs.getString("description"),
                        rs.getString("gradingformat"),
                        rs.getString("eventtype")
                );
                //user.setId(rs.getInt("id"));
                return reimbursement;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }


}
