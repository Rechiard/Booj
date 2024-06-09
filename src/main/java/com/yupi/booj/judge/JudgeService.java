package com.yupi.booj.judge;

import com.yupi.booj.model.entity.QuestionSubmit;
import com.yupi.booj.model.vo.QuestionSubmitVO;

/**
 * 判题服务接口
 */
public interface JudgeService {

    /**
     * 判题
     * @param questionSubmitId
     * @return
     */
    QuestionSubmit doJudge(long questionSubmitId);


}
