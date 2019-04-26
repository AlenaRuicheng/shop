package com.ruicheng.service.impl;

import com.ruicheng.dao.SellerInfoDao;
import com.ruicheng.entity.SellerInfo;
import com.ruicheng.service.interfaces.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Ruicheng
 * on 2019/4/21.
 */
@Service
public class SellerServiceImpl implements SellerService {
    @Autowired
    private SellerInfoDao sellerInfoDao;

    @Override
    public SellerInfo findSellerInfoBySellerOpenid(String sellerOpenid) {
        return sellerInfoDao.findBySellerOpenid(sellerOpenid);
    }

    @Override
    public SellerInfo findSellerInfoBySellerUsernameAndPassword(String username, String password) {
        return sellerInfoDao.findBySellerUsernameAndSellerPassword(username, password);
    }

    @Override
    public SellerInfo findSellerInfoBySellerUsernameAndOpenid(String username, String openid) {
        return sellerInfoDao.findBySellerUsernameAndSellerOpenid(username, openid);
    }

    public SellerInfo resetPassword(String username, String openid, String password){
        SellerInfo sellerInfo = sellerInfoDao.findBySellerUsernameAndSellerOpenid(username, openid);
        if (sellerInfo != null){
            sellerInfo.setSellerPassword(password);
            return sellerInfoDao.save(sellerInfo);
        }
        return null;
    }
}
