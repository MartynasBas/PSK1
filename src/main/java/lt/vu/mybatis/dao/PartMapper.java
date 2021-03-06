package lt.vu.mybatis.dao;

import lt.vu.mybatis.model.Part;
import org.mybatis.cdi.Mapper;

import java.util.List;

@Mapper
public interface PartMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.PART
     *
     * @mbg.generated Mon Apr 20 19:04:08 EEST 2020
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.PART
     *
     * @mbg.generated Mon Apr 20 19:04:08 EEST 2020
     */
    int insert(Part record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.PART
     *
     * @mbg.generated Mon Apr 20 19:04:08 EEST 2020
     */
    Part selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.PART
     *
     * @mbg.generated Mon Apr 20 19:04:08 EEST 2020
     */
    List<Part> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.PART
     *
     * @mbg.generated Mon Apr 20 19:04:08 EEST 2020
     */
    int updateByPrimaryKey(Part record);
}