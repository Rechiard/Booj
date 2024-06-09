package com.yupi.booj.judge.codesnadbox;


import com.yupi.booj.judge.codesnadbox.impl.ExampleCodeSandbox;
import com.yupi.booj.judge.codesnadbox.impl.RemoteCodeSandbox;
import com.yupi.booj.judge.codesnadbox.impl.ThirdPartyCodeSandbox;
import com.yupi.booj.judge.codesnadbox.model.enums.CodeSandboxTypeEnum;

/**
 * 代码沙箱工厂（根据字符串参数创建指定的代码沙箱实例）
 */
public class CodeSandboxFactory {


    // TODO 如果确定代码沙箱示例中不会出现线程安全问题、可复用，那么可以使用单例工厂模式
    public static CodeSandbox newInstance(String type) {
        if (CodeSandboxTypeEnum.EXAMPLE.getValue().equals(type)) {
            return new ExampleCodeSandbox();
        }else if(CodeSandboxTypeEnum.REMOTE.getValue().equals(type)){
            return new RemoteCodeSandbox();
        }else if(CodeSandboxTypeEnum.THIRD_PARTY.getValue().equals(type)){
            return new ThirdPartyCodeSandbox();
        }
        return new ExampleCodeSandbox();
    }

}
