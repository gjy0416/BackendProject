package com.yeogi_jeogi.product;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class productController {

	@Autowired
	public productDAO dao = new productDAO();
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
    @GetMapping("/products")
    public String productPage(Model m, @RequestParam(value="page", defaultValue="1") int page, 
    		@RequestParam(value="lcId", defaultValue="50201") int lcId, 
    		@RequestParam(value="sdate", defaultValue = "") String sdate, 
    		@RequestParam(value="edate", defaultValue = "") String edate) throws Exception {
    	LinkedList<product> pd1 =  dao.getSearchProduct(lcId, sdate.isEmpty() ? sdf.format(new Date()) : sdate, edate.isEmpty() ? sdf.format(new Date()) : edate, 1);
    	LinkedList<product> pd2 =  dao.getSearchProduct(lcId, sdate.isEmpty() ? sdf.format(new Date()) : sdate, edate.isEmpty() ? sdf.format(new Date()) : edate, 2);
    	LinkedList<product> pd3 =  dao.getSearchProduct(lcId, sdate.isEmpty() ? sdf.format(new Date()) : sdate, edate.isEmpty() ? sdf.format(new Date()) : edate, 3);
    	m.addAttribute("product1", pd1);
    	m.addAttribute("product2", pd2);
    	m.addAttribute("product3", pd3);
    	m.addAttribute("page", page);
    	m.addAttribute("lastPage", (int) Math.ceil((double) pd1.size() / 4));

		return "product/product";
    }
    
}
