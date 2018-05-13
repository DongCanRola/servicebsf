package cn.dcan.Service;

import cn.dcan.dto.ProcessDTO;
import cn.dcan.dto.SampleDTO;

import java.util.List;

/**
 * Created by dongc_000 on 2018/5/13.
 */
public interface ProcessService {
    //增加样本
    int addSample(SampleDTO sampleDTO);
    //获取某一成品的所有样本信息
    List<SampleDTO> getSampleByProduct(int productid);
    //增加加工处理
    int addProcess(ProcessDTO processDTO);
    //获取所有加工处理信息
    List<ProcessDTO> getAllProcess();
    //更新加工处理信息（状态）
    int updateProcess(ProcessDTO processDTO);
}
