package mybatis.dao;


import mybatis.pojo.User;

/**
 * <p>Title: UserDao</p>
 * <p>Description: dao接口，用户管理</p>
 * <p>Company: www.itcast.com</p>
 *
 * @version 1.0
 * @author 传智.燕青
 * @date 2015-4-22下午2:45:12
 */
public interface UserDao {

    /**
     * 根据id查询用户信息
     *
     * @param id
     * @return
     */
    User findUserById(int id);

}
