package com.shanjupay.merchant.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;

@Data
@TableName("code_holiday")
public class CodeHoliday implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 主键 */
    @TableId(value = "id", type = IdType.ID_WORKER)
    private String id;

    /** 年 */
    private String year;

    /** 月份 */
    private String month;

    /** 节假日json */
    private String holidayJson;

    /** 状态 */
    private String status;

    /** 创建时间 */
    private LocalDateTime createTime;

    /** 创建人 */
    private String creator;

    /** 修改时间 */
    private LocalDateTime editTime;

    /** 修改人 */
    private String editor;


}
