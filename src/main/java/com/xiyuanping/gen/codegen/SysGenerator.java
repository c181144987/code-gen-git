package com.xiyuanping.gen.codegen;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import com.xiyuanping.gen.codegen.entity.GenConfig;
import com.xiyuanping.gen.codegen.service.SysGeneratorService;
import lombok.AllArgsConstructor;
import lombok.var;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.io.File;


@ShellComponent
@AllArgsConstructor
public class SysGenerator {
    private final SysGeneratorService sysGeneratorService;

    @ShellMethod("所有表")
    public String show(@ShellOption(help = "表名", value = "-t", defaultValue = "") String tableName) {
        return sysGeneratorService.getPage(tableName);
    }

    @ShellMethod("生成代码")
    public String gen(@ShellOption(help = "表名", value = "-t") String tableName,
                      @ShellOption(defaultValue = "com.govmade.gds"
                              , help = "包路径"
                              , value = "-p") final String packageName,
                      @ShellOption(defaultValue = "module"
                              , help = "模块名"
                              , value = "-m") final String moduleName,
                      @ShellOption(defaultValue = "Shamoto"
                              , help = "作者名字"
                              , value = "-a") final String auth,
                      @ShellOption(defaultValue = "D:\\单独codegen\\测试"
                              , help = "保存路径,路径分隔符请使用 UNIX 风格"
                              , value = "-f") final String filePath) {

        var genConfig = GenConfig.builder()
                .author(auth)
                .tableName(tableName)
                .packageName(packageName)
                .moduleName(moduleName)
                .build();
        try {
            var data = sysGeneratorService.generatorCode(genConfig);
            var pathName = "";
            if (filePath != null) {
                pathName = filePath + "\\";
            }
            var fos = new File(pathName + tableName + ".zip");
            IoUtil.write(FileUtil.getOutputStream(fos), Boolean.TRUE, data);
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
        return String.format("%s 生成完成", tableName);
    }
}