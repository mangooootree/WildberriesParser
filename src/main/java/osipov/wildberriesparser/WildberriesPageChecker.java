package osipov.wildberriesparser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;
import osipov.wildberriesparser.domain.Item;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class WildberriesPageChecker {

    public Item checkPage(String url) {
        Map<String, Boolean> sizeTable = new LinkedHashMap<>();
        Document page = null;
        Item item = null;
        try {
            page = Jsoup.connect(url).get();
            Elements elements = page.select("[^data-characteristic]");
            for (Element label : elements) {
                Boolean isPresent = !label.className().equals("j-size  disabled j-sold-out");
                String spanText = label.select("span").first().text();

                sizeTable.put(spanText, isPresent);
            }

            String goodCode = page.select("#GoodCode").first().text();
            String img = page.select("img[src$=" + goodCode + "-1.jpg]").first().attr("src");
            String price = page.select("ins").text();
            Elements discount = page.select(".add-discount-text-price");

            item = new Item();
            item.setUrl(url);
            item.setSizeTable(sizeTable);
            item.setGoodCode(goodCode);
            item.setImg(img);
            item.setPrice(price);

            if (discount.size() > 0) {
                item.setDiscountPrice(discount.first().text());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return item;
    }

    public void updateSizeTable(Item item) {
        Map<String, Boolean> sizeTable = new LinkedHashMap<>();
        Document page = null;
        try {
            page = Jsoup.connect(item.getUrl()).get();
            Elements elements = page.select("[^data-characteristic]");
            for (Element label : elements) {
                boolean isPresent = !label.className().equals("j-size  disabled j-sold-out");
                String spanText = label.select("span").first().text();

                sizeTable.put(spanText, isPresent);
            }
            item.setSizeTable(sizeTable);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
