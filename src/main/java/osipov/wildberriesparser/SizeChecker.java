package osipov.wildberriesparser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import osipov.wildberriesparser.domain.Item;
import osipov.wildberriesparser.domain.ItemSizeInstance;
import osipov.wildberriesparser.repos.ItemRepo;
import osipov.wildberriesparser.repos.ItemSizeInstanceRepo;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

@Component
public class SizeChecker implements Runnable {

    private ItemSizeInstanceRepo itemSizeInstanceRepo;
    private ItemRepo itemRepo;
    private WildberriesPageChecker pageChecker;

    @Autowired
    public SizeChecker(ItemSizeInstanceRepo itemSizeInstanceRepo, ItemRepo itemRepo, WildberriesPageChecker pageChecker) {
        this.itemSizeInstanceRepo = itemSizeInstanceRepo;
        this.itemRepo = itemRepo;
        this.pageChecker = pageChecker;
    }

    @Override
    public void run() {
        while (true) {
            List<ItemSizeInstance> itemSizeInstances = itemSizeInstanceRepo.findAll();
            if (itemSizeInstances.size() > 0) {
                Set<Item> items = new HashSet<>();
                for (ItemSizeInstance instance : itemSizeInstances) {
                    items.add(instance.getItem());
                }

                for (Item item : items) {
                    pageChecker.updateSizeTable(item);
                    itemRepo.save(item);
                }

                for (ItemSizeInstance instance : itemSizeInstances) {
                    boolean presence = instance.getItem().getSizeTable().get(instance.getSize());

                    if (instance.isPresence() != presence) {
                        instance.setPresence(presence);
                        itemSizeInstanceRepo.save(instance);
                    }
                }
            }
            try {
                int min = new Random().nextInt(4)+2;
                Thread.sleep(min * 60 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}