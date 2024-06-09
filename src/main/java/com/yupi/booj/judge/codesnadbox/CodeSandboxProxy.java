package com.yupi.booj.judge.codesnadbox;

import com.yupi.booj.judge.codesnadbox.model.ExecuteCodeRequest;
import com.yupi.booj.judge.codesnadbox.model.ExecuteCodeResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 代码沙箱代理类，通过这个类充当中介，可以对代码沙箱类进行代码增强（例如输出日志信息）
 */
@Slf4j
@AllArgsConstructor
public class CodeSandboxProxy implements CodeSandbox{

    // final表示只能改变一次
    private final CodeSandbox codeSandbox;

    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        log.info("代码沙箱请求信息：" + executeCodeRequest.toString());
        ExecuteCodeResponse executeCodeResponse = codeSandbox.executeCode(executeCodeRequest);
        log.info("代码沙箱响应信息：" + executeCodeResponse.toString());
        return executeCodeResponse;
    }
}
