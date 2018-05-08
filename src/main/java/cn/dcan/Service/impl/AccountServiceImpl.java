package cn.dcan.Service.impl;

import cn.dcan.Service.AccountService;
import cn.dcan.dto.PurchaseDTO;
import cn.dcan.dto.SavingsDTO;
import cn.dcan.entity.PurchasePay;
import cn.dcan.entity.Savings;
import cn.dcan.mapper.PurchasePayMapper;
import cn.dcan.mapper.SavingsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
}
