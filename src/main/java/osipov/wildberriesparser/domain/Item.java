package osipov.wildberriesparser.domain;

import java.util.Map;
import java.util.Objects;

public class Item {
    private String url;
    private String price;
    private Map<String, Boolean> sizeTable;
    private String img;
    private String goodCode;
    private String discountPrice;

    public String getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(String discountPrice) {
        this.discountPrice = discountPrice;
    }

    public String getGoodCode() {
        return goodCode;
    }

    public void setGoodCode(String goodCode) {
        this.goodCode = goodCode;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Map<String, Boolean> getSizeTable() {
        return sizeTable;
    }

    public void setSizeTable(Map<String, Boolean> sizeTable) {
        this.sizeTable = sizeTable;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;
        Item item = (Item) o;
        return Objects.equals(url, item.url) &&
                Objects.equals(price, item.price) &&
                Objects.equals(sizeTable, item.sizeTable) &&
                Objects.equals(img, item.img) &&
                Objects.equals(goodCode, item.goodCode) &&
                Objects.equals(discountPrice, item.discountPrice);
    }

    @Override
    public int hashCode() {

        return Objects.hash(url, price, sizeTable, img, goodCode, discountPrice);
    }
}
