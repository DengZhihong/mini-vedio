package com.deng;


import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;

public class Test {
    public static void main(String[] args) {
        String s = IdUtil.objectId();
        Snowflake snowflake = IdUtil.createSnowflake(1, 64);
    }
}
