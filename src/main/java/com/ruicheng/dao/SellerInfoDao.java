package com.ruicheng.dao;

import com.ruicheng.entity.SellerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Ruicheng
 * on 2019/2/21.
 */
public interface SellerInfoDao extends JpaRepository<SellerInfo, String> {
    SellerInfo findBySellerOpenid(String sellerOpenid);

    SellerInfo findBySellerUsernameAndSellerPassword(String username, String password);

    SellerInfo findBySellerUsernameAndSellerOpenid(String username, String password);
}
