package com.yupi.booj.model.dto.question;

import lombok.Data;

/**
 * 题目用例，保存json数组格式的字段数据
 */
@Data
public class JudgeCase {

    /**
     * 输入用例
     */
    private String input;

    /**
     * 输出用例
     */
    private String output;
}
