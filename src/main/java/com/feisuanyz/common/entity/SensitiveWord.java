package com.feisuanyz.common.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 *   敏感词实体对象
 * </p>
 * @author tianchunxu
 */
@NoArgsConstructor
@Entity
@Table(name = "sensitive_word")
@AllArgsConstructor
@Data
public class SensitiveWord {

    /**
     * 主键ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 敏感词内容
     */
    @Column(name = "word_content", nullable = false, length = 50)
    private String wordContent;

    /**
     * 词类型: 1-弹幕 2-评论 3-标题
     */
    @Column(name = "word_type", columnDefinition = "TINYINT DEFAULT 1")
    private Integer wordType;

    /**
     * 状态: 1-启用 2-禁用
     */
    @Column(name = "status", columnDefinition = "TINYINT DEFAULT 1")
    private Integer status;

    /**
     * 创建人
     */
    @Column(name = "create_by")
    private Long createBy;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private java.time.LocalDateTime createTime;

    /**
     * 修改人
     */
    @Column(name = "update_by")
    private Long updateBy;

    /**
     * 修改时间
     */
    @Column(name = "update_time")
    private java.time.LocalDateTime updateTime;
}
