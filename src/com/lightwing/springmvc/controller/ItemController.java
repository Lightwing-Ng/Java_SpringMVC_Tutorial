package com.lightwing.springmvc.controller;

import com.lightwing.springmvc.exception.MyException;
import com.lightwing.springmvc.pojo.Item;
import com.lightwing.springmvc.pojo.QueryVo;
import com.lightwing.springmvc.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;

@Controller
public class ItemController {
    @Autowired
    private ItemService itemService;

    @RequestMapping(value = {"itemList", "itemList2"})
    public ModelAndView itemList() {
        ModelAndView mav = new ModelAndView();

        List<Item> itemList = itemService.getItemList();

        mav.addObject("itemList", itemList);
        // mav.setViewName("/WEB-INF/jsp/itemList.jsp");
        mav.setViewName("itemList");
        System.out.println("ItemController.itemList.....");
        return mav;
    }

    /**
     * 跟据ID查询商品信息，跳转修改商品页面 演示默认支持的参数传递 Model/ModelMap返回数据模型
     *
     * @return
     */
    @RequestMapping("itemEdit")
    public String itemEdit(Model model, @RequestParam(value = "id", defaultValue = "1") Integer ids) {
        // 查询商品信息
        Item item = itemService.getItemById(ids);
        // model返回数据模型
        model.addAttribute("item", item);
        return "itemEdit";
    }

    /**
     * 修改商品 演示pojo参数绑定
     *
     * @param item
     * @return
     * @throws IOException
     * @throws IllegalStateException
     */
    @RequestMapping(value = "updateItem", method = {RequestMethod.POST, RequestMethod.GET})
    public String updateItem(Item item, MultipartFile pictureFile, Model model) throws Exception {
        // 图片新名字
        String newName = UUID.randomUUID().toString();
        // 图片原来的名字
        String oldName = pictureFile.getOriginalFilename();
        // 后缀
        String sux = oldName.substring(oldName.lastIndexOf("."));
        // 新建本地文件流
        File file = new File("build/" + newName + sux);
        // 写入本地磁盘
        pictureFile.transferTo(file);
        // 保存图片到数据库
        item.setPic(newName + sux);

        itemService.updateItem(item);
        model.addAttribute("item", item);
        model.addAttribute("msg", "修改商品信息成功");
        return "itemEdit";
    }

    @RequestMapping("queryItem")
    public String queryItem(QueryVo vo, Integer[] ids, Model model) {
        if (vo.getItem() != null) {
            System.out.println(vo.getItem());
        }
        if (ids != null && ids.length > 0) {
            for (Integer id : ids) {
                System.out.println(id);
            }
        }
        if (vo.getItems() != null && vo.getItems().size() > 0) {
            for (Item item : vo.getItems()) {
                System.out.println(item);
            }
        }
        // 模拟搜索商品
        List<Item> itemList = itemService.getItemList();
        model.addAttribute("itemList", itemList);
        return "itemList";
    }

    @RequestMapping("queryVoid")
    public void queryVoid(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 假设这里是跟据id查询商品信息，搜索不到商品
        if (true) {
            throw new MyException("你查找的商品不存在，请确认信息！");
        }
        int i = 1 / 0;

        response.setCharacterEncoding("utf-8");
        PrintWriter printWriter = response.getWriter();
        printWriter.println("这个是response打印的消息");
    }

    @RequestMapping("getItem")
    @ResponseBody
    public Item getItem(@RequestBody Item itemIn) {
        System.out.println(itemIn);

        itemIn.setName("手机");
        return itemIn;
    }

    /**
     * RESTful 风格演示
     *
     * @return
     */
    @RequestMapping("item/{id}")
    public String itemQuery(@PathVariable("id") Integer ids, Model model) {
        // 查询商品信息
        Item item = itemService.getItemById(ids);
        // model返回数据模型
        model.addAttribute("item", item);

        return "itemEdit";
    }
}
