package ludlows.github.io.dao;

import ludlows.github.io.entity.Shop;
import ludlows.github.io.mapper.ShopMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("shopDao")
public class ShopDao {

    @Autowired
    ShopMapper shopMapper;

    public Shop getShop(int id){
        return shopMapper.getShop(id);
    }

    public Shop getMasterShopByHandle(int id){
        return shopMapper.getShop(id);
    }

    public Shop getSlaverShopByHandle(int id){
        return shopMapper.getShop(id);
    }

    public Shop getMasterShopByAnnotation(int id){
        return shopMapper.getShop(id);
    }

    public Shop getSlaverShopByAnnotation(int id){
        return shopMapper.getShop(id);
    }

}