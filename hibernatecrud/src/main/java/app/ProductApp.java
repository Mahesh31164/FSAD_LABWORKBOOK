package app;

import entity.Product;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

public class ProductApp {

    public static void main(String[] args) {

        // 🔹 CREATE
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        Product p1 = new Product("Laptop", "Gaming Laptop", 75000, 10);
        Product p2 = new Product("Mouse", "Wireless Mouse", 1200, 50);

        session.save(p1);
        session.save(p2);

        tx.commit();
        session.close();

        // 🔹 READ
        session = HibernateUtil.getSessionFactory().openSession();
        Product product = session.get(Product.class, 1);
        System.out.println("Product Name: " + product.getName());
        session.close();

        // 🔹 UPDATE
        session = HibernateUtil.getSessionFactory().openSession();
        tx = session.beginTransaction();

        product = session.get(Product.class, 1);
        product.setPrice(70000);
        product.setQuantity(8);

        session.update(product);
        tx.commit();
        session.close();

        // 🔹 DELETE
        session = HibernateUtil.getSessionFactory().openSession();
        tx = session.beginTransaction();

        Product deleteProduct = session.get(Product.class, 2);
        session.delete(deleteProduct);

        tx.commit();
        session.close();

        System.out.println("CRUD operations completed successfully.");
    }
}
