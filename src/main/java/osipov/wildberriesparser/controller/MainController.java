package osipov.wildberriesparser.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import osipov.wildberriesparser.WildberriesPageChecker;
import osipov.wildberriesparser.domain.Item;

import java.util.LinkedHashSet;
import java.util.Set;

@Controller
public class MainController {

    private Set<Item> items = new LinkedHashSet<>();

    @Autowired
    private WildberriesPageChecker pageChecker;

    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("items",items);
        return "index";
    }

    @PostMapping("newItem")
    public String newItem(@RequestParam String url, Model model){
        items.add(pageChecker.checkPage(url));
        model.addAttribute("items",items);
        return "redirect:/";
    }



}