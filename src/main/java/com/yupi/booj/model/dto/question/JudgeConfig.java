package com.yupi.booj.model.dto.question;

import lombok.Data;

/**
 * 题目配置，保存json数组格式的字段数据
 */
@Data
public class JudgeConfig {

    /**
     * 时间限制(ms)
     */
    private Long timeLimit;

    /**
     * 空间限制(kb)
     */
    private Long memoryLimit;

    /**
     * 堆栈限制(kb)
     */
    private Long stackLimit;
}
