package com.ruicheng.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Ruicheng
 * on 2019/2/21.
 */
@Data
@Entity
public class SellerInfo {

    @Id
    private String sellerId;

    private String sellerUsername;

    private String sellerPassword;

    private String sellerOpenid;
}
