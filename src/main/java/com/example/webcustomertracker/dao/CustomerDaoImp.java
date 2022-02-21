package com.example.webcustomertracker.dao;

import com.example.webcustomertracker.entity.Customer;
import com.example.webcustomertracker.sortUtilis;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Repository
public class CustomerDaoImp implements customerDao {

    //inject session factory
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public List<Customer> getCustomers(int sort) {
        Session session=sessionFactory.getCurrentSession();
        String sortField=null;
        if(sort== sortUtilis.FIRST_NAME)
            sortField="firstName";
        else if (sort==sortUtilis.LAST_NAEM)
            sortField="lastName";
        else if (sort==sortUtilis.EMAIL)
            sortField="email";
        else
            sortField="firstName";

        List<Customer> customers=session.createQuery("from Customer order by "+sortField,Customer.class).getResultList();
        return customers;
    }

    @Override
    @Transactional
    public void saveCustomer(Customer customer) {
        Session session=sessionFactory.getCurrentSession();
        session.saveOrUpdate(customer);
    }

    @Override
    @Transactional
    public Customer getCustomer(int id) {
        Session session=sessionFactory.getCurrentSession();
        Customer customer=session.get(Customer.class,id);
        return customer;
    }

    @Override
    @Transactional
    public void deleteCustomer(int id) {
        Session session=sessionFactory.getCurrentSession();
        Customer tempCustomer=session.get(Customer.class,id);
        session.delete(tempCustomer);
    }

    @Override
    @Transactional
    public List<Customer> search(String searchName) {
        Session session=sessionFactory.getCurrentSession();

        Query theQuery=null;
        if(searchName !=null){
             theQuery=session.createQuery("from Customer " +
                    "where lower(firstName) like :searchInput or lower(lastName) like :searchInput",Customer.class);
            theQuery.setParameter("searchInput","%"+searchName+"%");
        }else{
                theQuery=session.createQuery("from Customer",Customer.class);
        }
        List<Customer> customers= theQuery.getResultList();
        return customers;
    }
}
