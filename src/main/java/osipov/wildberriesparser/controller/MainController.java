package osipov.wildberriesparser.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import osipov.wildberriesparser.SizeChecker;
import osipov.wildberriesparser.WildberriesPageChecker;
import osipov.wildberriesparser.domain.Item;
import osipov.wildberriesparser.domain.User;
import osipov.wildberriesparser.repos.ItemRepo;
import osipov.wildberriesparser.repos.ItemSizeInstanceRepo;
import osipov.wildberriesparser.repos.UserRepo;

import java.util.LinkedHashSet;

@Controller
public class MainController {

    private ItemRepo itemRepo;
    private UserRepo userRepo;
    private WildberriesPageChecker pageChecker;
    private ItemSizeInstanceRepo itemSizeRepo;
    private SizeChecker sizeChecker;

    @Autowired
    public MainController(ItemRepo itemRepo, UserRepo userRepo, WildberriesPageChecker pageChecker, ItemSizeInstanceRepo itemSizeRepo, SizeChecker sizeChecker) {
        this.itemRepo = itemRepo;
        this.userRepo = userRepo;
        this.pageChecker = pageChecker;
        this.itemSizeRepo = itemSizeRepo;
        this.sizeChecker = sizeChecker;
        new Thread(sizeChecker).start();
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("items", new LinkedHashSet<>(itemRepo.findAll()));
        model.addAttribute("itemSizeInstances", new LinkedHashSet<>(itemSizeRepo.findAll()));
        return "index";
    }

    @PostMapping("newItem")
    public String newItem(@RequestParam String url, Model model) {
        Item item = pageChecker.checkPage(url);
        User user = new User();
        userRepo.save(user);
        item.setUser(user);
        itemRepo.save(item);

        return "redirect:/";
    }


}