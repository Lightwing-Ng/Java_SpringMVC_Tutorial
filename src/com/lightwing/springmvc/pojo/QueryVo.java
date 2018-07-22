package com.lightwing.springmvc.pojo;

import java.util.List;

/**
 * 包装的pojo传递
 *
 * @author Lightwing Ng
 */
public class QueryVo {
    private Item item;
    private Integer[] ids;
    private List<Item> items;

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Integer[] getIds() {
        return ids;
    }

    public void setIds(Integer[] ids) {
        this.ids = ids;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
