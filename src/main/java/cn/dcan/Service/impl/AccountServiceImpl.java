package cn.dcan.Service.impl;

import cn.dcan.Service.AccountService;
import cn.dcan.dto.PurchaseDTO;
import cn.dcan.dto.PurchasePayDTO;
import cn.dcan.dto.PurchasePayDetailDTO;
import cn.dcan.dto.SavingsDTO;
import cn.dcan.entity.PurchaseOrder;
import cn.dcan.entity.PurchasePay;
import cn.dcan.entity.PurchasePayDetail;
import cn.dcan.entity.Savings;
import cn.dcan.mapper.PurchaseOrderMapper;
import cn.dcan.mapper.PurchasePayDetailMapper;
import cn.dcan.mapper.PurchasePayMapper;
import cn.dcan.mapper.SavingsMapper;
import cn.dcan.constrain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by dongc_000 on 2018/5/4.
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    SavingsMapper savingsMapper;
    @Autowired
    PurchasePayMapper purchasePayMapper;
    @Autowired
    PurchaseOrderMapper purchaseOrderMapper;
    @Autowired
    PurchasePayDetailMapper purchasePayDetailMapper;

    private ConcreteDataFormat concreteDataFormat = new ConcreteDataFormat();

    @Override
    public List<SavingsDTO> getAllSavings() {
        List<Savings> savingsList = savingsMapper.getAll();
        List<SavingsDTO> savingsDTOS = new ArrayList<>();
        for(Savings savings : savingsList) {
            savingsDTOS.add(entityToDto(savings));
        }
        return savingsDTOS;
    }

    @Override
    public void addSavings(SavingsDTO savingsDTO) {
        Savings savings = dtoToEntity(savingsDTO);
        int count = savingsMapper.insert(savings);
        System.out.println("添加成功，个数：" + count);
    }

    @Override
    public void addPurchasePay(PurchaseDTO purchaseDTO) {
        PurchasePay purchasePay = new PurchasePay();
        purchasePay.setPurchaseid(purchaseDTO.getPurchaseOrder_id());
        double discount = purchaseDTO.getPurchase_discount();
        purchasePay.setDiscount(discount);
        double total = purchaseDTO.getPurchase_num() * purchaseDTO.getPurchase_price();
        purchasePay.setTotal(total);
        purchasePay.setSurplus(total - discount);
        purchasePayMapper.insertSelective(purchasePay);
    }

    @Override
    public List<PurchasePayDTO> getPurchasePayList() {
        List<PurchasePay> purchasePays = purchasePayMapper.selectAll();
        List<PurchasePayDTO> purchasePayDTOS = new ArrayList<>();
        for(PurchasePay purchasePay : purchasePays) {
            int payId = purchasePay.getId();
            int orderId = purchasePay.getPurchaseid();
            List<PurchasePayDetail> purchasePayDetails = purchasePayDetailMapper.selectByPayId(payId);
            PurchaseOrder purchaseOrder = purchaseOrderMapper.selectByPrimaryKey(orderId);
            purchasePayDTOS.add(purchasePayEntityToDto(purchasePay,purchaseOrder,purchasePayDetails));
        }
        return purchasePayDTOS;
    }

    @Override
    public int addPurchasePayDetail(PurchasePayDetailDTO purchasePayDetailDTO) {
        PurchasePayDetail purchasePayDetail = purchasePayDetailDtoToEntity(purchasePayDetailDTO);
        int count = purchasePayDetailMapper.insertSelective(purchasePayDetail);
        return purchasePayDetail.getId();
    }

    @Override
    public List<PurchasePayDetailDTO> getPurchasePayDetailList() {
        List<PurchasePayDetail> purchasePayDetails = purchasePayDetailMapper.selectAll();
        List<PurchasePayDetailDTO> purchasePayDetailDTOS = new ArrayList<>();
        for(PurchasePayDetail purchasePayDetail : purchasePayDetails) {
            purchasePayDetailDTOS.add(purchasePayDetailEntityToDto(purchasePayDetail));
        }
        return purchasePayDetailDTOS;
    }

    @Override
    public List<PurchasePayDetailDTO> getPurchasePayDetailByPay(int payId) {
        List<PurchasePayDetail> purchasePayDetails = purchasePayDetailMapper.selectByPayId(payId);
        List<PurchasePayDetailDTO> purchasePayDetailDTOS = new ArrayList<>();
        for(PurchasePayDetail purchasePayDetail : purchasePayDetails) {
            purchasePayDetailDTOS.add(purchasePayDetailEntityToDto(purchasePayDetail));
        }
        return purchasePayDetailDTOS;
    }

    private SavingsDTO entityToDto(Savings savings) {
        SavingsDTO savingsDTO = new SavingsDTO();
        savingsDTO.setSavings_id(savings.getId());
        savingsDTO.setSavings_bank(savings.getBank());
        savingsDTO.setSavings_balance(savings.getBalance());
        return savingsDTO;
    }

    private Savings dtoToEntity(SavingsDTO savingsDTO) {
        Savings savings = new Savings();
        savings.setId(savingsDTO.getSavings_id());
        savings.setBank(savingsDTO.getSavings_bank());
        savings.setBalance(savingsDTO.getSavings_balance());
        return savings;
    }

    private PurchasePayDTO purchasePayEntityToDto(PurchasePay purchasePay, PurchaseOrder purchaseOrder, List<PurchasePayDetail> purchasePayDetails) {
        PurchasePayDTO purchasePayDTO = new PurchasePayDTO();
        purchasePayDTO.setPurchasePay_id(purchasePay.getId());
        purchasePayDTO.setPurchase_id(purchasePay.getPurchaseid());
        purchasePayDTO.setPlan_total(purchasePay.getTotal());
        purchasePayDTO.setDiscount(purchasePay.getDiscount());
        purchasePayDTO.setActual_total();
        double alreadyPay = 0;
        for(PurchasePayDetail purchasePayDetail : purchasePayDetails) {
            alreadyPay = alreadyPay + purchasePayDetail.getMoney();
        }
        purchasePayDTO.setAlready_pay(alreadyPay);
        purchasePayDTO.setSurplus();
        purchasePayDTO.setOrder_time(concreteDataFormat.DateToString(purchaseOrder.getOrdertime()));
        return purchasePayDTO;
    }

    private PurchasePayDetail purchasePayDetailDtoToEntity(PurchasePayDetailDTO purchasePayDetailDTO) {
        PurchasePayDetail purchasePayDetail = new PurchasePayDetail();
        purchasePayDetail.setPayid(purchasePayDetailDTO.getPay_id());
        purchasePayDetail.setMoney(purchasePayDetailDTO.getPay_money());
        purchasePayDetail.setPaytime(concreteDataFormat.StringToDate(purchasePayDetailDTO.getPay_time()));
        purchasePayDetail.setUserid(purchasePayDetailDTO.getPay_user());
        purchasePayDetail.setSavingsid(purchasePayDetailDTO.getPay_savings());
        return purchasePayDetail;
    }

    private PurchasePayDetailDTO purchasePayDetailEntityToDto(PurchasePayDetail purchasePayDetail) {
        PurchasePayDetailDTO purchasePayDetailDTO = new PurchasePayDetailDTO();
        purchasePayDetailDTO.setDetail_id(purchasePayDetail.getId());
        purchasePayDetailDTO.setPay_id(purchasePayDetail.getPayid());
        purchasePayDetailDTO.setPay_money(purchasePayDetail.getMoney());
        purchasePayDetailDTO.setPay_time(concreteDataFormat.DateToString(purchasePayDetail.getPaytime()));
        purchasePayDetailDTO.setPay_user(purchasePayDetail.getUserid());
        purchasePayDetailDTO.setPay_savings(purchasePayDetail.getSavingsid());
        return purchasePayDetailDTO;
    }
}
