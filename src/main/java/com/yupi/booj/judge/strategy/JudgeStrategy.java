package com.yupi.booj.judge.strategy;

import com.yupi.booj.judge.codesnadbox.model.JudgeInfo;

public interface JudgeStrategy {

    /**
     * 执行判题
     * @param judgeContext
     * @return
     */
    JudgeInfo doJudge(JudgeContext judgeContext);
}
