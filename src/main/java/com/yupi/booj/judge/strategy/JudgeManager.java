package com.yupi.booj.judge.strategy;

import com.yupi.booj.judge.codesnadbox.model.JudgeInfo;
import com.yupi.booj.model.entity.QuestionSubmit;
import org.springframework.stereotype.Service;

/**
 * 判题管理（简化调用）
 * 因为有不同的策略需要单独写doJudge方法，并且切换策略会加重service的判断逻辑（if-else过多）
 * 将切换策略的逻辑在这个类中进行管理
 */
@Service
public class JudgeManager {

    /**
     * 执行判题
     * @param judgeContext
     * @return
     */
    public JudgeInfo doJudge(JudgeContext judgeContext){
        QuestionSubmit questionSubmit = judgeContext.getQuestionSubmit();
        String language = questionSubmit.getLanguage();
        JudgeStrategy judgeStrategy = new DefaultJudgeStrategy();
        if("java".equals(language)){
            judgeStrategy = new JavaLanguageJudgeStrategy();
        }
        return judgeStrategy.doJudge(judgeContext);
    }
}
