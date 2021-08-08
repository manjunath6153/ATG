package com.atg.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.atg.beans.Customer;
import com.atg.beans.Product;
import com.atg.dao.AdminDao;
import com.atg.dao.NotificationValidator;
import com.atg.dao.ProductDao;

@Controller
@SessionAttributes("userName")
public class LoginController {
	@Autowired
	private ProductDao productDao;

	@Autowired
	private AdminDao adminDao;
	
	@Autowired
	private NotificationValidator notificationValidator;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView loginPage() {
		notificationValidator.validateAllProducts();//validates all products and sends email notifications
		return new ModelAndView("login");
	}
	
	
	@RequestMapping(value = "/customers", method = RequestMethod.GET)
	public ModelAndView customerPage() {
		ModelAndView model =  new ModelAndView("customers");
		model.addObject("Customers");//all customers from DB
		
		return model;
	}

	@RequestMapping(value = "/addCustomer", method = RequestMethod.POST)
	public String addCustomer(@ModelAttribute("customer") Customer customer, HttpSession session,
			RedirectAttributes redirectAttributes) {

		int status = adminDao.addCustomer(customer);
		if (status < 0) {
			redirectAttributes.addFlashAttribute("msgR", "Customer with the given Name already exists");
			return "redirect:customers";
		} else {
			redirectAttributes.addFlashAttribute("msgR", "Customer Added successfully");
		}
		return "redirect:customers";
	}
	@RequestMapping(value = "/products", method = RequestMethod.GET)
	public ModelAndView productsPage() {
		ModelAndView model =  new ModelAndView("products");
		model.addObject("Products");// all products from DB
		
		return model;
	}

	@RequestMapping(value = "/addProducts", method = RequestMethod.POST)
	public String addProducts(@ModelAttribute("product") Product product, HttpSession session,
			RedirectAttributes redirectAttributes) {

		int status = productDao.addProduct(product);
		if (status < 0) {
			redirectAttributes.addFlashAttribute("msgR", "product with the given Name already exists");
			return "redirect:products";
		} else {
			redirectAttributes.addFlashAttribute("msgR", "product Added successfully");
		}
		return "redirect:products";
	}

	@RequestMapping(value = "/deleteProduct/{productId}", method = RequestMethod.GET)
	public String deleteproduct(@PathVariable int productId, RedirectAttributes redirectAttributes) {
		productDao.deleteProduct(productId);
		redirectAttributes.addFlashAttribute("msg", "Product Deleted Successfully");
		return "redirect:/products";
	}
}
