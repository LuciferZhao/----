package com.org.tybar.utils;

import java.util.Random;

public class CodeRandom {

    /**
     * 生成6位随机数
     * @return
     */
    public String codeRandom(){
        String code = "";
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            int r = random.nextInt(10); //每次随机出一个数字（0-9）
            code = code + r;  //把每次随机出的数字拼在一起
            //System.out.println("第"+(i+1)+"个数为"+r);
        }
        //System.out.println(code);
        return code;
    }

}
