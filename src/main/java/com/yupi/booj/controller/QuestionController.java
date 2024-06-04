package com.yupi.booj.controller;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yupi.booj.annotation.AuthCheck;
import com.yupi.booj.common.BaseResponse;
import com.yupi.booj.common.DeleteRequest;
import com.yupi.booj.common.ErrorCode;
import com.yupi.booj.common.ResultUtils;
import com.yupi.booj.constant.UserConstant;
import com.yupi.booj.exception.BusinessException;
import com.yupi.booj.exception.ThrowUtils;
import com.yupi.booj.model.dto.question.QuestionAddRequest;
import com.yupi.booj.model.dto.question.QuestionEditRequest;
import com.yupi.booj.model.dto.question.QuestionQueryRequest;
import com.yupi.booj.model.dto.question.QuestionUpdateRequest;
import com.yupi.booj.model.entity.Question;
import com.yupi.booj.model.entity.User;
import com.yupi.booj.model.vo.QuestionVO;
import com.yupi.booj.service.QuestionService;
import com.yupi.booj.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 题目接口
 *
 * @author <a href="https://github.com/liyupi">程序员鱼皮</a>
 * @from <a href="https://yupi.icu">编程导航知识星球</a>
 */
@RestController
@RequestMapping("/Question")
@Slf4j
public class QuestionController {

    @Resource
    private QuestionService QuestionService;

    @Resource
    private UserService userService;

    // region 增删改查

    /**
     * 创建
     *
     * @param QuestionAddRequest
     * @param request
     * @return
     */
    @PostMapping("/add")
    public BaseResponse<Long> addQuestion(@RequestBody QuestionAddRequest QuestionAddRequest, HttpServletRequest request) {
        if (QuestionAddRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Question Question = new Question();
        BeanUtils.copyProperties(QuestionAddRequest, Question);
        List<String> tags = QuestionAddRequest.getTags();
        if (tags != null) {
            Question.setTags(JSONUtil.toJsonStr(tags));
        }
        QuestionService.validQuestion(Question, true);
        User loginUser = userService.getLoginUser(request);
        Question.setUserId(loginUser.getId());
        Question.setFavourNum(0);
        Question.setThumbNum(0);
        boolean result = QuestionService.save(Question);
        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);
        long newQuestionId = Question.getId();
        return ResultUtils.success(newQuestionId);
    }

    /**
     * 删除
     *
     * @param deleteRequest
     * @param request
     * @return
     */
    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteQuestion(@RequestBody DeleteRequest deleteRequest, HttpServletRequest request) {
        if (deleteRequest == null || deleteRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        User user = userService.getLoginUser(request);
        long id = deleteRequest.getId();
        // 判断是否存在
        Question oldQuestion = QuestionService.getById(id);
        ThrowUtils.throwIf(oldQuestion == null, ErrorCode.NOT_FOUND_ERROR);
        // 仅本人或管理员可删除
        if (!oldQuestion.getUserId().equals(user.getId()) && !userService.isAdmin(request)) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        boolean b = QuestionService.removeById(id);
        return ResultUtils.success(b);
    }

    /**
     * 更新（仅管理员）
     *
     * @param QuestionUpdateRequest
     * @return
     */
    @PostMapping("/update")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Boolean> updateQuestion(@RequestBody QuestionUpdateRequest QuestionUpdateRequest) {
        if (QuestionUpdateRequest == null || QuestionUpdateRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Question Question = new Question();
        BeanUtils.copyProperties(QuestionUpdateRequest, Question);
        List<String> tags = QuestionUpdateRequest.getTags();
        if (tags != null) {
            Question.setTags(JSONUtil.toJsonStr(tags));
        }
        // 参数校验
        QuestionService.validQuestion(Question, false);
        long id = QuestionUpdateRequest.getId();
        // 判断是否存在
        Question oldQuestion = QuestionService.getById(id);
        ThrowUtils.throwIf(oldQuestion == null, ErrorCode.NOT_FOUND_ERROR);
        boolean result = QuestionService.updateById(Question);
        return ResultUtils.success(result);
    }

    /**
     * 根据 id 获取
     *
     * @param id
     * @return
     */
    @GetMapping("/get/vo")
    public BaseResponse<QuestionVO> getQuestionVOById(long id, HttpServletRequest request) {
        if (id <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Question Question = QuestionService.getById(id);
        if (Question == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        return ResultUtils.success(QuestionService.getQuestionVO(Question, request));
    }

    /**
     * 分页获取列表（仅管理员）
     *
     * @param QuestionQueryRequest
     * @return
     */
    @PostMapping("/list/page")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Page<Question>> listQuestionByPage(@RequestBody QuestionQueryRequest QuestionQueryRequest) {
        long current = QuestionQueryRequest.getCurrent();
        long size = QuestionQueryRequest.getPageSize();
        Page<Question> QuestionPage = QuestionService.page(new Page<>(current, size),
                QuestionService.getQueryWrapper(QuestionQueryRequest));
        return ResultUtils.success(QuestionPage);
    }

    /**
     * 分页获取列表（封装类）
     *
     * @param QuestionQueryRequest
     * @param request
     * @return
     */
    @PostMapping("/list/page/vo")
    public BaseResponse<Page<QuestionVO>> listQuestionVOByPage(@RequestBody QuestionQueryRequest QuestionQueryRequest,
            HttpServletRequest request) {
        long current = QuestionQueryRequest.getCurrent();
        long size = QuestionQueryRequest.getPageSize();
        // 限制爬虫
        ThrowUtils.throwIf(size > 20, ErrorCode.PARAMS_ERROR);
        Page<Question> QuestionPage = QuestionService.page(new Page<>(current, size),
                QuestionService.getQueryWrapper(QuestionQueryRequest));
        return ResultUtils.success(QuestionService.getQuestionVOPage(QuestionPage, request));
    }

    /**
     * 分页获取当前用户创建的资源列表
     *
     * @param QuestionQueryRequest
     * @param request
     * @return
     */
    @PostMapping("/my/list/page/vo")
    public BaseResponse<Page<QuestionVO>> listMyQuestionVOByPage(@RequestBody QuestionQueryRequest QuestionQueryRequest,
            HttpServletRequest request) {
        if (QuestionQueryRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        User loginUser = userService.getLoginUser(request);
        QuestionQueryRequest.setUserId(loginUser.getId());
        long current = QuestionQueryRequest.getCurrent();
        long size = QuestionQueryRequest.getPageSize();
        // 限制爬虫
        ThrowUtils.throwIf(size > 20, ErrorCode.PARAMS_ERROR);
        Page<Question> QuestionPage = QuestionService.page(new Page<>(current, size),
                QuestionService.getQueryWrapper(QuestionQueryRequest));
        return ResultUtils.success(QuestionService.getQuestionVOPage(QuestionPage, request));
    }

    // endregion


    /**
     * 编辑（用户）
     *
     * @param QuestionEditRequest
     * @param request
     * @return
     */
    @PostMapping("/edit")
    public BaseResponse<Boolean> editQuestion(@RequestBody QuestionEditRequest QuestionEditRequest, HttpServletRequest request) {
        if (QuestionEditRequest == null || QuestionEditRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Question Question = new Question();
        BeanUtils.copyProperties(QuestionEditRequest, Question);
        List<String> tags = QuestionEditRequest.getTags();
        if (tags != null) {
            Question.setTags(JSONUtil.toJsonStr(tags));
        }
        // 参数校验
        QuestionService.validQuestion(Question, false);
        User loginUser = userService.getLoginUser(request);
        long id = QuestionEditRequest.getId();
        // 判断是否存在
        Question oldQuestion = QuestionService.getById(id);
        ThrowUtils.throwIf(oldQuestion == null, ErrorCode.NOT_FOUND_ERROR);
        // 仅本人或管理员可编辑
        if (!oldQuestion.getUserId().equals(loginUser.getId()) && !userService.isAdmin(loginUser)) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        boolean result = QuestionService.updateById(Question);
        return ResultUtils.success(result);
    }

}
