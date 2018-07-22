package com.lightwing.springmvc.service.impl;

import com.lightwing.springmvc.mapper.ItemMapper;
import com.lightwing.springmvc.pojo.Item;
import com.lightwing.springmvc.service.ItemService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {
    // @Autowired
    private ItemMapper itemMapper;

    @Override
    public List<Item> getItemList() {
        return itemMapper.selectByExample(null);
    }

    @Override
    public Item getItemById(Integer id) {
        return itemMapper.selectByPrimaryKey(id);
    }

    @Override
    public void updateItem(Item item) {
        itemMapper.updateByPrimaryKeySelective(item);
    }

    public void setItemMapper(ItemMapper itemMapper) {
        this.itemMapper = itemMapper;
    }
}
