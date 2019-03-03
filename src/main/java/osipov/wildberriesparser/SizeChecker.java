package osipov.wildberriesparser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import osipov.wildberriesparser.domain.Item;
import osipov.wildberriesparser.domain.ItemSizeInstance;
import osipov.wildberriesparser.repos.ItemSizeInstanceRepo;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class SizeChecker implements Runnable {

    private ItemSizeInstanceRepo itemSizeInstanceRepo;
    private WildberriesPageChecker pageChecker;

    @Autowired
    public SizeChecker(ItemSizeInstanceRepo itemSizeInstanceRepo, WildberriesPageChecker pageChecker) {
        this.itemSizeInstanceRepo = itemSizeInstanceRepo;
        this.pageChecker = pageChecker;
    }

    @Override
    public void run() {
        while (true) {
            System.out.println("Проверяем размер списка");
            List<ItemSizeInstance> itemSizeInstances = itemSizeInstanceRepo.findAll();
            if (itemSizeInstances.size() > 0) {
                System.out.println("Количество проверяемых объектов " + itemSizeInstances.size());

                Set<Item> items = new HashSet<>();
                for (ItemSizeInstance instance : itemSizeInstances){
                    items.add(instance.getItem());
                }

                for (Item item: items){
                    pageChecker.checkPage(item.getUrl());
                    System.out.println("Запускаем pageChecker для " + item.getGoodCode());
                }

                for (ItemSizeInstance instance : itemSizeInstances) {
                    System.out.println("Проверяем элемент " + instance.getItem().getGoodCode() + " размера " + instance.getSize());
                    System.out.println("Текущее состояние " + instance.getPresence());
                    boolean newState = instance.checkSize();
                    System.out.println("Состояние после проверки " + instance.getPresence());
                    if (instance.getPresence() != newState) {
                        itemSizeInstanceRepo.save(instance);
                    }
                }
            }
            try {
                Thread.sleep(3 * 60 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}