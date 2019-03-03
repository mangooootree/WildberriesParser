package osipov.wildberriesparser.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import osipov.wildberriesparser.WildberriesPageChecker;
import osipov.wildberriesparser.domain.Item;
import osipov.wildberriesparser.domain.User;
import osipov.wildberriesparser.repos.ItemRepo;
import osipov.wildberriesparser.repos.ItemSizeInstanceRepo;
import osipov.wildberriesparser.repos.UserRepo;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class MainController {

    private Set<Item> items = new HashSet<>();

    private ItemRepo itemRepo;
    private UserRepo userRepo;
    private WildberriesPageChecker pageChecker;
    private ItemSizeInstanceRepo itemSizeRepo;

    @Autowired
    public MainController(ItemRepo itemRepo, UserRepo userRepo, WildberriesPageChecker pageChecker, ItemSizeInstanceRepo itemSizeRepo) {
        this.itemRepo = itemRepo;
        this.userRepo = userRepo;
        this.pageChecker = pageChecker;
        this.itemSizeRepo = itemSizeRepo;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("items", items);
        model.addAttribute("itemSizeInstances", itemSizeRepo.findAll());
        return "index";
    }

    @PostMapping("newItem")
    public String newItem(@RequestParam String url, Model model) {
        Item item = pageChecker.checkPage(url);
        User user = new User();
        userRepo.save(user);
        item.setUser(user);
        itemRepo.save(item);

        List<Item> itemsFromBD = itemRepo.findAll();
        items.addAll(itemsFromBD);

        model.addAttribute("items", items);
        return "redirect:/";
    }


}