package ludlows.github.io.service;

import ludlows.github.io.entity.Shop;
import ludlows.github.io.dao.ShopDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("shopService")
public class ShopService {
    @Autowired
    private ShopDao dao;

    public Shop getShop(int id) {
        return dao.getShop(id);
    }

    public Shop getMasterShopByHandle(int id) {
        return dao.getMasterShopByHandle(id);
    }

    public Shop getSlaverShopByHandle(int id) {
        return dao.getSlaverShopByHandle(id);
    }


    public Shop getMasterShopByAnnotation(int id) {
        return dao.getMasterShopByAnnotation(id);
    }


    public Shop getSlaverShopByAnnotation(int id) {
        return dao.getSlaverShopByAnnotation(id);
    }
}