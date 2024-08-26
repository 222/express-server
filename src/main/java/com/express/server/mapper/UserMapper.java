package com.express.server.mapper;

import com.express.server.model.User;

import com.express.server.param.req.UserIdDTO;
import com.express.server.param.req.UserQuery;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

/**
 * 用户数据库访问层
 *
 * @author youping.tan
 * @since 2024-08-06 11:19:07
 */
 @Mapper
public interface UserMapper {

    /**
     * 通过主键查询单条用户
     *
     * @param queryId 主键
     * @return 实例对象
     */
    User selectById(UserIdDTO queryId);

    /**
     * 查询用户
     *
     * @param query 查询条件
     * @return 对象列表
     */
    List<User> selectList(UserQuery query);

    /**
     * 新增用户
     *
     * @param user 实例对象
     * @return 影响行数
     */
    int insert(User user);

    /**
     * 批量新增用户
     *
     * @param userList 实例对象列表
     * @return 影响行数
     */
    int insertList(List<User> userList);
    
    /**
     * 修改用户
     *
     * @param user 实例对象
     * @return 影响行数
     */
    int update(User user);

    /**
     * 批量新增用户或更新用户
     *
     * @param userList 实例对象列表
     * @return 影响行数
     */
    int updateList(List<User> userList);

    /**
     * 通过主键删除用户
     *
     * @param deleteId 主键
     * @return 影响行数
     */
    int deleteById(UserIdDTO deleteId);
    
    
    /**
      * 校验是否存在
      *
      * @param field 字段值
      * @return 校验结果
      */
    boolean exists(String field);

    User loadByPhone(String phone);

}

