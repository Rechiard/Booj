package com.yupi.booj.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yupi.booj.model.dto.question.QuestionQueryRequest;
import com.yupi.booj.model.dto.questionsubmit.QuestionSubmitAddRequest;
import com.yupi.booj.model.dto.questionsubmit.QuestionSubmitQueryRequest;
import com.yupi.booj.model.entity.Question;
import com.yupi.booj.model.entity.QuestionSubmit;
import com.yupi.booj.model.entity.User;
import com.yupi.booj.model.vo.QuestionSubmitVO;
import com.yupi.booj.model.vo.QuestionVO;

import javax.servlet.http.HttpServletRequest;

/**
* @author 龚剑波
* @description 针对表【question_submit(题目提交表)】的数据库操作Service
* @createDate 2024-06-04 20:29:33
*/
public interface QuestionSubmitService extends IService<QuestionSubmit> {

    /**
     * 题目提交
     *
     * @param questionSubmitAddRequest 题目提交信息
     * @param loginUser
     * @return
     */
    long doQuestionSubmit(QuestionSubmitAddRequest questionSubmitAddRequest, User loginUser);


    /**
     * 获取查询条件
     *
     * @param questionSubmitQueryRequest
     * @return
     */
    QueryWrapper<QuestionSubmit> getQueryWrapper(QuestionSubmitQueryRequest questionSubmitQueryRequest);

    /**
     * 获取题目封装
     *
     * @param questionSubmit
     * @param loginUser
     * @return
     */
    QuestionSubmitVO getQuestionSubmitVO(QuestionSubmit questionSubmit, User loginUser);

    /**
     * 分页获取题目封装
     *
     * @param questionSubmitPage
     * @param loginUser
     * @return
     */
    Page<QuestionSubmitVO> getQuestionSubmitVOPage(Page<QuestionSubmit> questionSubmitPage, User loginUser);

}
