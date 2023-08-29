import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.sql.*;
import java.util.List;

public class AppInitializer {

    public static void main(String[] args) {

//    --------------------------------------------save----------------------------------------------------
//        try {
//            Customer customer = new Customer(1003, "Nimal", "Matale", 25000, "2023-11-12");
//            if (saveCustomer(customer)) {
//                System.out.println("Success");
//            } else {
//                System.out.println("Try Again");
//            }
//        } catch (SQLException | ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//    ----------------------------------------------find by id--------------------------------------------------
//        try {
//            Customer customer = findById(1001);
//            if (customer != null) {
//                System.out.println("Success");
//                System.out.println(customer.toString());
//            } else {
//                System.out.println("Try Again");
//            }
//        } catch (SQLException | ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//      -------------------------------------------------find all-----------------------------------------------
        try {
            List<Customer> customer = findAll();
            if (!customer.isEmpty()) {
                System.out.println("Success");
                customer.forEach(e-> System.out.println(e.toString()));
            } else {
                System.out.println("Try Again");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
//      ------------------------------------------------update------------------------------------------------
//        try {
//            Customer customer = new Customer(1004, "Nimal Santha Aiya", "Matale", 55000, "2023-11-12");
//            if (updateCustomer(customer)) {
//                System.out.println("Success");
//            } else {
//                System.out.println("Try Again");
//            }
//        } catch (SQLException | ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//      ------------------------------------------------delete------------------------------------------------
//        try {
//            if (deleteCustomer(1001)) {
//                System.out.println("Success");
//            } else {
//                System.out.println("Try Again");
//            }
//        } catch (SQLException | ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//      ------------------------------------------------------------------------------------------------


    }

    private static boolean saveCustomer(Customer customer) throws ClassNotFoundException, SQLException {

     /*   String SQL = "INSERT INTO customer VALUES (?,?,?,?,?)";
        PreparedStatement preparedStatement = getConnection().prepareStatement(SQL);
        preparedStatement.setLong(1, customer.getId());
        preparedStatement.setString(2, customer.getName());
        preparedStatement.setString(3, customer.getAddress());
        preparedStatement.setDouble(4, customer.getSalary());
        preparedStatement.setObject(5, customer.getDob());
        return preparedStatement.executeUpdate() > 0; */

        try(Session session = new HibernateUtil().openSession()){
            Transaction transaction=session.beginTransaction();
            session.save(customer);
            transaction.commit();
        }
        return true;

    }
    private static boolean updateCustomer(Customer customer) throws ClassNotFoundException, SQLException, SQLException {
        /*String SQL = "UPDATE customer SET name=?, address=?, salary=?, dob=? WHERE id=?";
        PreparedStatement preparedStatement = getConnection().prepareStatement(SQL);

        preparedStatement.setString(1, customer.getName());
        preparedStatement.setString(2, customer.getAddress());
        preparedStatement.setDouble(3, customer.getSalary());
        preparedStatement.setObject(4, customer.getDob());
        preparedStatement.setLong(5, customer.getId());
        return preparedStatement.executeUpdate() > 0;*/

        try(Session session = new HibernateUtil().openSession()){
            Transaction transaction=session.beginTransaction();
            Customer selectedCustomer = session.get(Customer.class, customer.getId());
            if (selectedCustomer == null) return false;
            selectedCustomer.setName(customer.getName());
            selectedCustomer.setAddress(customer.getAddress());
            selectedCustomer.setSalary(customer.getSalary());
            selectedCustomer.setDob(customer.getDob());
            transaction.commit();
            return true;
        }

    }

    private static Customer findById(long id) throws SQLException, ClassNotFoundException {
     /*   String SQL = "SELECT * FROM customer WHERE id=?";
        PreparedStatement preparedStatement = getConnection().prepareStatement(SQL);
        preparedStatement.setLong(1, id);
        ResultSet set = preparedStatement.executeQuery();
        if (set.next()) {
            return new Customer(
                    set.getLong(1),
                    set.getString(2),
                    set.getString(3),
                    set.getDouble(4),
                    set.getString(5)
            );
        }
        return null;*/

        try(Session session = new HibernateUtil().openSession()){
            return session.get(Customer.class, id);
        }

    }

    private static List<Customer> findAll() throws SQLException, ClassNotFoundException {
        /*List<Customer> customerList = new ArrayList<>();
        String SQL = "SELECT * FROM customer";
        PreparedStatement preparedStatement = getConnection().prepareStatement(SQL);
        ResultSet set = preparedStatement.executeQuery();
        while (set.next()) {
            customerList.add(
                    new Customer(
                            set.getLong(1),
                            set.getString(2),
                            set.getString(3),
                            set.getDouble(4),
                            set.getString(5)
                    )
            );
        }
        return customerList;*/
        try(Session session = new HibernateUtil().openSession()){
            String hql = "FROM Customer";
            Query<Customer> query = session.createQuery(hql, Customer.class);
            return query.list();
        }
    }

    private static boolean deleteCustomer(long id) throws ClassNotFoundException, SQLException {
        /*String SQL = "DELETE FROM customer WHERE id=?";
        PreparedStatement preparedStatement = getConnection().prepareStatement(SQL);
        preparedStatement.setLong(1, id);
        return preparedStatement.executeUpdate() > 0;*/
        try(Session session = new HibernateUtil().openSession()){
            Transaction transaction=session.beginTransaction();
            Customer selectedCustomer = session.get(Customer.class,id);
            if (selectedCustomer == null) return false;
            session.delete(selectedCustomer);
            transaction.commit();
            return true;
        }
    }
}