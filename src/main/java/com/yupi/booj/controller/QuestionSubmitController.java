package com.yupi.booj.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yupi.booj.annotation.AuthCheck;
import com.yupi.booj.common.BaseResponse;
import com.yupi.booj.common.ErrorCode;
import com.yupi.booj.common.ResultUtils;
import com.yupi.booj.constant.UserConstant;
import com.yupi.booj.exception.BusinessException;
import com.yupi.booj.model.dto.question.QuestionQueryRequest;
import com.yupi.booj.model.dto.questionsubmit.QuestionSubmitAddRequest;
import com.yupi.booj.model.dto.questionsubmit.QuestionSubmitQueryRequest;
import com.yupi.booj.model.entity.Question;
import com.yupi.booj.model.entity.QuestionSubmit;
import com.yupi.booj.model.entity.User;
import com.yupi.booj.model.vo.QuestionSubmitVO;
import com.yupi.booj.service.QuestionSubmitService;
import com.yupi.booj.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 题目提交接口
 *
 * @author <a href="https://github.com/liyupi">程序员鱼皮</a>
 * @from <a href="https://yupi.icu">编程导航知识星球</a>
 */
@RestController
@RequestMapping("/question_submit")
@Slf4j
@Deprecated
public class QuestionSubmitController {



}
