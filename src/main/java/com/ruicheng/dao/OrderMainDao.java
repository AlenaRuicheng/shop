package com.ruicheng.dao;

import com.ruicheng.entity.OrderMain;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Ruicheng
 * on 2019/2/12.
 */
public interface OrderMainDao extends JpaRepository<OrderMain, String> {

    Page<OrderMain> findByBuyerOpenid(String buyerOpenid, Pageable pageable);
}
