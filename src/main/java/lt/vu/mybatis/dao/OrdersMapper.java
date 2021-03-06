package lt.vu.mybatis.dao;

import lt.vu.mybatis.model.Orders;
import org.mybatis.cdi.Mapper;

import java.util.List;

@Mapper
public interface OrdersMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.ORDERS
     *
     * @mbg.generated Mon Apr 20 19:04:08 EEST 2020
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.ORDERS
     *
     * @mbg.generated Mon Apr 20 19:04:08 EEST 2020
     */
    int insert(Orders record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.ORDERS
     *
     * @mbg.generated Mon Apr 20 19:04:08 EEST 2020
     */
    Orders selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.ORDERS
     *
     * @mbg.generated Mon Apr 20 19:04:08 EEST 2020
     */
    List<Orders> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.ORDERS
     *
     * @mbg.generated Mon Apr 20 19:04:08 EEST 2020
     */
    int updateByPrimaryKey(Orders record);
}