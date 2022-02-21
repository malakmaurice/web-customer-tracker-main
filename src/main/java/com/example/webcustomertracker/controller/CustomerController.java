package com.example.webcustomertracker.controller;

import com.example.webcustomertracker.dao.customerDao;
import com.example.webcustomertracker.entity.Customer;
import com.example.webcustomertracker.sortUtilis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    //inject DAO into the controller
    @Autowired
    private customerDao customerDAO;

    @GetMapping("/list")
    public String customerList(Model model ,@RequestParam(required = false) String sort){
        List<Customer> customers=null;
        if(sort !=null){
            customers=customerDAO.getCustomers(Integer.parseInt(sort));
        }else
        {
            customers=customerDAO.getCustomers(sortUtilis.FIRST_NAME);
        }

        //add the customer to the model
        model.addAttribute("customers",customers);
        return "list-customer";
    }

    @GetMapping("/showFromForCustomer")
    public String addCustomerForm(Model model){
        model.addAttribute("customer",new Customer());
        return "customer-form";
    }


    @PostMapping("/saveCustomer")
     public String addCustomerProcess(@ModelAttribute("customer") Customer customer){
        customerDAO.saveCustomer(customer);
        return "redirect:/customer/list";
     }

     @GetMapping("/updateForm")
    public String updateForm(@RequestParam("customerID") int theID,Model model){
        Customer customer=customerDAO.getCustomer(theID);
        model.addAttribute("customer",customer);
        return "customer-form";
     }

     @GetMapping("/delete")
    public String deleteCustomer(@RequestParam("customerID") int theID,Model model){
        customerDAO.deleteCustomer(theID);
        return "redirect:/customer/list";
     }

     @GetMapping("/search")
    public String searchCustomer(@RequestParam("searchInput") String searchName ,Model model){
        List<Customer> customers=customerDAO.search(searchName);
        model.addAttribute("customers",customers);
        return "list-customer";
     }
}
