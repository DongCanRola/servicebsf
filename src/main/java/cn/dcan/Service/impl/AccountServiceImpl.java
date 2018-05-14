package cn.dcan.Service.impl;

import cn.dcan.Service.AccountService;
import cn.dcan.dto.*;
import cn.dcan.entity.*;
import cn.dcan.mapper.*;
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
    @Autowired
    SaleGatherMapper saleGatherMapper;
    @Autowired
    SaleGatherDetailMapper saleGatherDetailMapper;
    @Autowired
    SaleOrderMapper saleOrderMapper;

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
        int payId = purchasePayDetail.getPayid();
        //判断储蓄账户是否有足够余额
        String savingdsId = purchasePayDetail.getSavingsid();
        Savings savings = savingsMapper.selectByPrimaryKey(savingdsId);
        double currentBalance = savings.getBalance();
        double currentPay = purchasePayDetail.getMoney();
        if(currentBalance < currentPay) {
            return -1;
        } else {
            //判断付款金额是否过多
            List<PurchasePayDetail> purchasePayDetails = purchasePayDetailMapper.selectByPayId(payId);
            PurchasePay purchasePay = purchasePayMapper.selectByPrimaryKey(payId);
            double total = purchasePay.getSurplus();
            double already = 0;
            for(PurchasePayDetail purchasePayDetail1 : purchasePayDetails) {
                already = already + purchasePayDetail1.getMoney();
            }
            if(currentPay + already > total) {
                return 0;
            } else {
                //新增付款详情，减少储蓄余额
                int count = purchasePayDetailMapper.insertSelective(purchasePayDetail);
                savings.setBalance(currentBalance - currentPay);
                savingsMapper.updateByPrimaryKey(savings);
            }
        }
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

    @Override
    public List<PurchasePayDetailDTO> getPurchasePayDetailBySavings(String savings) {
        List<PurchasePayDetail> purchasePayDetails = purchasePayDetailMapper.selectBySavingsId(savings);
        List<PurchasePayDetailDTO> purchasePayDetailDTOS = new ArrayList<>();
        for(PurchasePayDetail purchasePayDetail : purchasePayDetails) {
            purchasePayDetailDTOS.add(purchasePayDetailEntityToDto(purchasePayDetail));
        }
        return purchasePayDetailDTOS;
    }

    @Override
    public void addSaleGather(SaleDTO saleDTO) {
        SaleGather saleGather = new SaleGather();
        saleGather.setSaleid(saleDTO.getSale_orderId());
        double discount = saleDTO.getSale_discount();
        saleGather.setDiscount(discount);
        double planTotal = saleDTO.getSale_num() * saleDTO.getSale_price();
        saleGather.setPlantotal(planTotal);
        saleGather.setActualtotal(planTotal - discount);
        saleGatherMapper.insertSelective(saleGather);
    }

    @Override
    public List<SaleGatherDTO> getSaleGatherList() {
        return null;
    }

    @Override
    public int addSaleGatherDetail(SaleGatherDetailDTO saleGatherDetailDTO) {
        return 0;
    }

    @Override
    public List<SaleGatherDetailDTO> getSaleGatherDetailList() {
        return null;
    }

    @Override
    public List<SaleGatherDetailDTO> getSaleGatherDetailByGather(int gatherId) {
        return null;
    }

    @Override
    public List<SaleGatherDetailDTO> getSaleGatherDetailBySavings(String savings) {
        return null;
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

    private SaleGatherDTO sgEntityToDto(SaleGather saleGather, SaleOrder saleOrder, List<SaleGatherDetail> saleGatherDetails) {
        SaleGatherDTO saleGatherDTO = new SaleGatherDTO();
        return saleGatherDTO;
    }
}
