package com.ruicheng.dao;

import com.ruicheng.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/**
 * Created by Ruicheng
 * on 2019/2/9.
 */
public interface OrderDetailDao extends JpaRepository<OrderDetail, String> {

    List<OrderDetail> findByOrderId(String orderId);
}
