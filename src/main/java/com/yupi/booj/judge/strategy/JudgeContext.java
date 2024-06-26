package com.yupi.booj.judge.strategy;

import com.yupi.booj.model.dto.question.JudgeCase;
import com.yupi.booj.judge.codesnadbox.model.JudgeInfo;
import com.yupi.booj.model.entity.Question;
import com.yupi.booj.model.entity.QuestionSubmit;
import lombok.Data;

import java.util.List;

/**
 * 上下文（用于定义在策略中传递的参数）
 */
@Data
public class JudgeContext {

    private JudgeInfo judgeInfo;

    private List<String> inputList;

    private List<String> outputList;

    private Question question;

    private List<JudgeCase> judgeCaseList;

    private QuestionSubmit questionSubmit;

}
