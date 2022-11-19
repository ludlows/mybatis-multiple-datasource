package ludlows.github.io.mapper;

import ludlows.github.io.entity.Shop;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

public interface ShopMapper {
    @Select("SELECT * FROM t_shop WHERE id = #{id}")
    @Results(value = { @Result(id = true, column = "id", property = "id"),
            @Result(column = "shop_name", property = "shopName") })
    Shop getShop(@Param("id") int id);
}