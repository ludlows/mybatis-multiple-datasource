package ludlows.github.io.controller;

import ludlows.github.io.common.DataSourceEnum;
import ludlows.github.io.entity.Shop;
import ludlows.github.io.service.ShopService;
import ludlows.github.io.annotation.DataSourceTypeAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShopController {
    @Autowired
    private ShopService service;

    @RequestMapping(value = "/getShop")
    public Shop getShop(@RequestParam("id") int id) {
        return service.getShop(id);
    }


    @RequestMapping(value = "/getMasterShopByAnnotation")
    @DataSourceTypeAnnotation(DataSourceEnum.master)
    public Shop getMasterShopByAnnotation(int id) {
        return service.getMasterShopByAnnotation(id);
    }

    @RequestMapping(value = "/getSlaverShopByAnnotation")
    @DataSourceTypeAnnotation(DataSourceEnum.slaver)
    public Shop getSlaverShopByAnnotation(int id) {
        return service.getSlaverShopByAnnotation(id);
    }
}