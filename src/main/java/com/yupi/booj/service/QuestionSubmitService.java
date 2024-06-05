package com.yupi.booj.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yupi.booj.model.entity.QuestionSubmit;
import com.yupi.booj.model.entity.User;

/**
* @author 龚剑波
* @description 针对表【question_submit(题目提交表)】的数据库操作Service
* @createDate 2024-06-04 20:29:33
*/
public interface QuestionSubmitService extends IService<QuestionSubmit> {

    /**
     * 题目提交
     *
     * @param questionId
     * @param loginUser
     * @return
     */
    int doQuestionSubmit(long questionId, User loginUser);

    /**
     * 帖子题目提交（内部服务）
     *
     * @param userId
     * @param questionId
     * @return
     */
    int doQuestionSubmitInner(long userId, long questionId);

}
