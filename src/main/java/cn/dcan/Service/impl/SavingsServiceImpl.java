package cn.dcan.Service.impl;

import cn.dcan.Service.SavingsService;
import cn.dcan.dto.SavingsDTO;
import cn.dcan.entity.Savings;
import cn.dcan.mapper.SavingsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dongc_000 on 2018/5/4.
 */
@Service
public class SavingsServiceImpl implements SavingsService{

    @Autowired
    SavingsMapper savingsMapper;

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
    public String addSavings(SavingsDTO savingsDTO) {
        Savings savings = dtoToEntity(savingsDTO);
        int count = savingsMapper.insert(savings);
        return savings.getId();
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
        savings.setBank(savingsDTO.getSavings_bank());
        savings.setBalance(savingsDTO.getSavings_balance());
        return savings;
    }
}
