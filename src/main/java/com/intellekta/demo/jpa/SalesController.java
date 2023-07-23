package com.intellekta.demo.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.Date;
import java.util.List;

@Controller
public class SalesController {

    private final JPASaleRepository saleRepository;

    @Autowired
    public SalesController(JPASaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    @GetMapping("/")
    public String showHomePage() {
        return "home";
    }

    @GetMapping("/sales")
    public String showSalesPage(Model model, @ModelAttribute("sellerName") String sellerName) {
        model.addAttribute("sellerName", sellerName);
        List<JPASale> sales = saleRepository.findAll();
        model.addAttribute("sales", sales);

        return "sales";
    }

    @PostMapping("/")
    public String processForm(@RequestParam("sellerName") String sellerName, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("sellerName", sellerName);
        return "redirect:/sales";
    }

    @PostMapping("/sales")
    public String addSale(@RequestParam("amount") double amount,
                          @RequestParam("purchaseDate") @DateTimeFormat(pattern = "dd.MM.yyyy") Date purchaseDate,
                          @RequestParam("saleDate") @DateTimeFormat(pattern = "dd.MM.yyyy") Date saleDate,
                          @RequestParam("productId") int productId) {
        JPASale sale = new JPASale();
        sale.setAmount(amount);
        sale.setPurchaseDate(purchaseDate);
        sale.setSaleDate(saleDate);
        sale.setProductId(productId);
        saleRepository.save(sale);
        return "redirect:/sales";
    }
}
