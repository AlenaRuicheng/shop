package com.ruicheng.service.impl;

import com.ruicheng.dao.ProductInfoDao;
import com.ruicheng.dto.CartDTO;
import com.ruicheng.entity.ProductInfo;
import com.ruicheng.enums.ProductStatusEnum;
import com.ruicheng.enums.ResultEnum;
import com.ruicheng.exceptions.SellException;
import com.ruicheng.service.interfaces.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Created by Ruicheng
 * on 2019/2/17.
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductInfoDao dao;

    @Override
    public ProductInfo findOne(String productId) {
        Optional<ProductInfo> optional  = dao.findById(productId);
        if (!optional.isPresent()){
            throw new SellException(ResultEnum.NO_SUCH_PRODUCT);
        }
        return optional.get();
    }

    @Override
    public List<ProductInfo> findAvailableProducts() {
        return dao.findByProductStatus(ProductStatusEnum.AVAILABLE.getCode());
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return dao.findAll(pageable);
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return dao.save(productInfo);
    }

    @Override
    @Transactional
    public void increaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO: cartDTOList) {
            ProductInfo productInfo = findOne(cartDTO.getProductId());
            if (productInfo == null) {
                throw new SellException(ResultEnum.NO_SUCH_PRODUCT);
            }
            Integer result = productInfo.getProductStock() + cartDTO.getProductQuantity();
            productInfo.setProductStock(result);

            dao.save(productInfo);
        }

    }

    @Override
    @Transactional
    public void decreaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO: cartDTOList) {
            ProductInfo productInfo = findOne(cartDTO.getProductId());
            if (productInfo == null) {
                throw new SellException(ResultEnum.NO_SUCH_PRODUCT);
            }

            Integer result = productInfo.getProductStock() - cartDTO.getProductQuantity();
            if (result < 0) {
                throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
            }

            productInfo.setProductStock(result);

            dao.save(productInfo);
        }
    }

    @Override
    public ProductInfo onSale(String productId) {
        ProductInfo productInfo = findOne(productId);
        if (productInfo == null) {
            throw new SellException(ResultEnum.NO_SUCH_PRODUCT);
        }
        if (productInfo.getProductStatusEnum() == ProductStatusEnum.AVAILABLE) {
            throw new SellException(ResultEnum.PRODUCT_STATUS_ERROR);
            //TODO
        }

        //更新
        productInfo.setProductStatus(ProductStatusEnum.AVAILABLE.getCode());
        return dao.save(productInfo);
    }

    @Override
    public ProductInfo offSale(String productId) {
        ProductInfo productInfo = findOne(productId);
        if (productInfo == null) {
            throw new SellException(ResultEnum.NO_SUCH_PRODUCT);
        }
        if (productInfo.getProductStatusEnum() == ProductStatusEnum.OUT_OF_STOCK) {
            throw new SellException(ResultEnum.PRODUCT_STATUS_ERROR);
        }

        //更新
        productInfo.setProductStatus(ProductStatusEnum.OUT_OF_STOCK.getCode());
        return dao.save(productInfo);
    }
}
