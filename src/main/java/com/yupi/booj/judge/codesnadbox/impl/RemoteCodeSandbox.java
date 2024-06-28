package com.yupi.booj.judge.codesnadbox.impl;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.yupi.booj.common.ErrorCode;
import com.yupi.booj.exception.BusinessException;
import com.yupi.booj.judge.codesnadbox.CodeSandbox;
import com.yupi.booj.judge.codesnadbox.model.ExecuteCodeRequest;
import com.yupi.booj.judge.codesnadbox.model.ExecuteCodeResponse;
import org.apache.commons.lang3.StringUtils;

/**
 * 远程代码沙箱（实际调用接口的沙箱）
 */
public class RemoteCodeSandbox implements CodeSandbox {

    public static final String AUTH_REQUEST_HEADER = "auth";

    public static final String AUTH_REQUEST_SECRET = "secretKey";

    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        System.out.println("远程代码沙箱");
        String url = "http://localhost:8090/executeCode";
        String json = JSONUtil.toJsonStr(executeCodeRequest);
        String responseStr = HttpUtil.createPost(url)
                .header(AUTH_REQUEST_HEADER, AUTH_REQUEST_SECRET)
                .body(json)
                .execute()
                .body();
        if(StringUtils.isBlank(responseStr)){
            throw new BusinessException(ErrorCode.API_REQUEST_ERROR ,"executeCode remoteSandbox error, message = " + responseStr);
        }

        return JSONUtil.toBean(responseStr, ExecuteCodeResponse.class);
    }
}
