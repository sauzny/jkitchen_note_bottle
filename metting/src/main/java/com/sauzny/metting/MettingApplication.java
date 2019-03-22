package com.sauzny.metting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;

/***************************************************************************
 *
 * @时间: 2019/3/14 - 17:39
 *
 * @描述: TODO
 *
 ***************************************************************************/

@SpringBootApplication
public class MettingApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(MettingApplication.class);
        // 打印PID
        // 配置文件中可以设置文件打印路径，文件默认位置是项目当前目录application.pid
        // 执行测试用例的话，是不执行此处代码的
        app.addListeners(new ApplicationPidFileWriter());
        app.run(args);
    }
}
