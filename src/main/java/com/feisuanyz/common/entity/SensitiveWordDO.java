package com.feisuanyz.common.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.*;

/**
 * <p>
 *   敏感词实体对象
 * </p>
 * @author tianchunxu
 */
@Entity
@Table(name = "sensitive_word")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SensitiveWordDO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String wordContent;

    @NotNull
    private Integer wordType;

    @NotNull
    private Integer status;

    private Long createBy;

    @CreationTimestamp
    private LocalDateTime createTime;

    private Long updateBy;

    @UpdateTimestamp
    private LocalDateTime updateTime;
}