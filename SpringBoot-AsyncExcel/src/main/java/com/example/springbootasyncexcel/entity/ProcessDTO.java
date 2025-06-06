package com.example.springbootasyncexcel.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * ClassName: ProcessDTO
 * Package: com.example.springbootasyncexcel.pojo
 * Description:
 *
 * @Author ms
 * @Create 2025/6/5 14:49
 * @Version 1.0
 */
@Data
public class ProcessDTO {

    private Long processId;

    private Object data;

    private ProcessDTO() {

    }

    public static ProcessDTO createProcessDTO(Long processId, Object data) {
        ProcessDTO processDTO = new ProcessDTO();
        processDTO.setProcessId(processId);
        processDTO.setData(data);
        return processDTO;
    }

    public static ProcessDTO createProcessDTO(Long processId) {
        ProcessDTO processDTO = new ProcessDTO();
        processDTO.setProcessId(processId);
        return processDTO;
    }
}
