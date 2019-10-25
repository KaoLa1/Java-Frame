package mybatis.mapper;

import mybatis.pojo.User;

/**
 * <p>Title: UserMapper</p>
 * <p>Description: mapper接口，相当 于dao接口，用户管理</p>
 * <p>Company: www.itcast.com</p>
 *
 * @version 1.0
 * @author 传智.燕青
 * @date 2015-4-22下午2:45:12
 */
public interface UserMapper {
    /**
     * 根据id查询用户信息
     * @param id
     * @return
     */
    User findUserById(int id);
}
