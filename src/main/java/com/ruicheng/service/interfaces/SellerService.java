package com.ruicheng.service.interfaces;

import com.ruicheng.entity.SellerInfo;

/**
 * Created by Ruicheng
 * on 2019/4/21.
 */
public interface SellerService {

    /**
     * 根据卖家的openid查询卖家信息
     */
    SellerInfo findSellerInfoBySellerOpenid(String sellerOpenid);

    /**
     * 核对卖家登录信息
     * @param username 卖家用户名
     * @param password 卖家登录密码
     */
    SellerInfo findSellerInfoBySellerUsernameAndPassword(String username, String password);

    /**
     *  根据用户名和OPENID查找卖家信息
     * @param username 卖家用户名
     * @param openid 卖家登录密码
     */
    SellerInfo findSellerInfoBySellerUsernameAndOpenid(String username, String openid);

    /**
     * 根据用户名和OPENID重置登录密码
     * @param username 卖家用户名
     * @param openid 卖家OPENID
     * @param password 新密码
     * @return 如果重置密码成功，返回更新后的对象；否则返回null
     */
    public SellerInfo resetPassword(String username, String openid, String password);
}
