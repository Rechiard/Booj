package com.yupi.booj.judge.codesnadbox.impl;

import com.yupi.booj.judge.codesnadbox.CodeSandbox;
import com.yupi.booj.judge.codesnadbox.model.ExecuteCodeRequest;
import com.yupi.booj.judge.codesnadbox.model.ExecuteCodeResponse;

/**
 * 远程代码沙箱（实际调用接口的沙箱）
 */
public class RemoteCodeSandbox implements CodeSandbox {


    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        System.out.println("远程代码沙箱");

        return null;
    }
}
