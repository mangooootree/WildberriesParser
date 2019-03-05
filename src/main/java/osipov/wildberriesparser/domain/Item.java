package osipov.wildberriesparser.domain;

import javax.persistence.*;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String url;
    private String price;

    @ElementCollection(fetch = FetchType.EAGER)
    private Map<String, Boolean> sizeTable = new LinkedHashMap<>();
    private String img;
    private String goodCode;
    private String discountPrice;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

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

    public Item() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;
        Item item = (Item) o;
        return Objects.equals(goodCode, item.goodCode);
    }

    @Override
    public int hashCode() {

        return Objects.hash(goodCode);
    }
}
