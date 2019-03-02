package osipov.wildberriesparser.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import osipov.wildberriesparser.domain.Item;
import osipov.wildberriesparser.domain.ItemSizeInstance;
import osipov.wildberriesparser.repos.ItemRepo;
import osipov.wildberriesparser.repos.ItemSizeInstanceRepo;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class FollowingController {

    @Autowired
    private ItemRepo itemRepo;

    @Autowired
    private ItemSizeInstanceRepo itemSizeRepo;

    private Set<Item> items = new HashSet<>();
    private Set<ItemSizeInstance> itemSizeInstances = new HashSet<>();


    @GetMapping("details")
    public String newItem(@RequestParam String id, @RequestParam String size, Model model) {
        Item item = itemRepo.getOne(Long.parseLong(id));
        ItemSizeInstance itemSizeInstance = new ItemSizeInstance();
        itemSizeInstance.setItem(item);
        itemSizeInstance.setSize(size);
        itemSizeInstances.add(itemSizeInstance);
        itemSizeRepo.save(itemSizeInstance);

        List<Item> itemsFromBD = itemRepo.findAll();
        items.addAll(itemsFromBD);

        model.addAttribute("items", items);
        model.addAttribute("itemSizeInstances", itemSizeInstances);

        return "index";
    }

}