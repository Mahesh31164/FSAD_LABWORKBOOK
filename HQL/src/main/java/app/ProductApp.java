package app;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import entity.Product;
import util.Hibernateutil;

public class ProductApp {

    public static void main(String[] args) {

        Session session = Hibernateutil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        // INSERT SAMPLE DATA
        session.save(new Product("Laptop", "Electronics", 65000, 10));
        session.save(new Product("Mouse", "Electronics", 800, 50));
        session.save(new Product("Keyboard", "Electronics", 1500, 30));
        session.save(new Product("Chair", "Furniture", 3500, 15));
        session.save(new Product("Table", "Furniture", 7000, 5));
        session.save(new Product("Pen", "Stationery", 20, 0));
        session.save(new Product("Notebook", "Stationery", 100, 100));

        // SORTING
        session.createQuery("FROM Product p ORDER BY p.price ASC", Product.class)
                .list().forEach(System.out::println);

        session.createQuery("FROM Product p ORDER BY p.price DESC", Product.class)
                .list().forEach(System.out::println);

        session.createQuery("FROM Product p ORDER BY p.quantity DESC", Product.class)
                .list().forEach(System.out::println);

        // PAGINATION
        session.createQuery("FROM Product", Product.class)
                .setFirstResult(0)
                .setMaxResults(3)
                .list().forEach(System.out::println);

        session.createQuery("FROM Product", Product.class)
                .setFirstResult(3)
                .setMaxResults(3)
                .list().forEach(System.out::println);

        // AGGREGATES
        Long total =
                session.createQuery("SELECT COUNT(p) FROM Product p", Long.class)
                        .uniqueResult();
        System.out.println("Total Products: " + total);

        Long available =
                session.createQuery(
                        "SELECT COUNT(p) FROM Product p WHERE p.quantity > 0",
                        Long.class)
                        .uniqueResult();
        System.out.println("Available Products: " + available);

        Object[] minMax =
                session.createQuery(
                        "SELECT MIN(p.price), MAX(p.price) FROM Product p",
                        Object[].class)
                        .uniqueResult();
        System.out.println("Min Price: " + minMax[0]);
        System.out.println("Max Price: " + minMax[1]);

        // GROUP BY
        List<Object[]> grouped =
                session.createQuery(
                        "SELECT p.description, COUNT(p) FROM Product p GROUP BY p.description",
                        Object[].class)
                        .list();

        for (Object[] row : grouped) {
            System.out.println(row[0] + " -> " + row[1]);
        }

        // WHERE
        session.createQuery(
                "FROM Product p WHERE p.price BETWEEN 1000 AND 10000",
                Product.class)
                .list().forEach(System.out::println);

        // LIKE
        session.createQuery("FROM Product p WHERE p.name LIKE 'L%'", Product.class)
                .list().forEach(System.out::println);

        session.createQuery("FROM Product p WHERE p.name LIKE '%r'", Product.class)
                .list().forEach(System.out::println);

        session.createQuery("FROM Product p WHERE p.name LIKE '%tab%'", Product.class)
                .list().forEach(System.out::println);

        session.createQuery("FROM Product p WHERE LENGTH(p.name)=5", Product.class)
                .list().forEach(System.out::println);

        tx.commit();
        session.close();
        Hibernateutil.shutdown();
    }
}
