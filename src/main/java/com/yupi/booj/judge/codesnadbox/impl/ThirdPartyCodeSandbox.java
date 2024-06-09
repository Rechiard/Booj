package com.yupi.booj.judge.codesnadbox.impl;

import com.yupi.booj.judge.codesnadbox.CodeSandbox;
import com.yupi.booj.judge.codesnadbox.model.ExecuteCodeRequest;
import com.yupi.booj.judge.codesnadbox.model.ExecuteCodeResponse;

/**
 * 第三方代码沙箱（调用网上现成的代码沙箱）
 * 例如：https://github.com/criyle/go-judge
 * 注意：这里用到了适配器模式
 */
public class ThirdPartyCodeSandbox implements CodeSandbox {


    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        System.out.println("第三方代码沙箱");

        return null;
    }
}
