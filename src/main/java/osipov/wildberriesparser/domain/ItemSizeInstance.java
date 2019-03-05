package osipov.wildberriesparser.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class ItemSizeInstance {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    private Item item;

    private String size;

    private boolean presence;

    public ItemSizeInstance() {
    }

    public boolean isPresence() {
        return presence;
    }

    public void setPresence(boolean presence) {
        this.presence = presence;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ItemSizeInstance)) return false;
        ItemSizeInstance that = (ItemSizeInstance) o;
        return Objects.equals(item, that.item) &&
                Objects.equals(size, that.size);
    }

    @Override
    public int hashCode() {

        return Objects.hash(item, size);
    }
}