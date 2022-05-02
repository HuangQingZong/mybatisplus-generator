package com.shanjupay.generator;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.List;

/**
 * MyBatisPlus代码生成器
 */
public class MyBatisPlusGenerator {

    //================ main方法 ================
    public static void main(String[] args) {

        //创建代码生成器
        AutoGenerator autoGenerator = new AutoGenerator();

        //------------ 配置代码生成器 ------------
        //全局配置
        MyBatisPlusGenerator.assembleGlobalConfig(autoGenerator);
        //数据源配置
        MyBatisPlusGenerator.assembleDataSourceConfig(autoGenerator);
        //生成包配置
        MyBatisPlusGenerator.assemblePackageConfig(autoGenerator);
        //自定义配置
        MyBatisPlusGenerator.assembleInjectionConfig(autoGenerator);
        //模板配置
        MyBatisPlusGenerator.assembleTemplateConfig(autoGenerator);
        //策略配置
        MyBatisPlusGenerator.assembleStrategyConfig(autoGenerator);

        //------------ 开始生成代码 ------------
        autoGenerator.execute();
    }


    //================ 代码路径 ================
    //项目所在路径
    private static String projectPath = System.getProperty("user.dir");
    //生成代码存放路径
    private static String projectName = "/code";
    //父包名
    private static String parentPath = "com.shanjupay";
    //模块包名
    private static String moduleName = "merchant";


    //================ 数据源 ================
    //数据库驱动
    private static String driver = "com.mysql.cj.jdbc.Driver";
    //用户名
    private static String username = "root";
    //密码
    private static String password = "root";
    //数据库url
    private static String url = "jdbc:mysql://localhost:3306/temp_generator?serverTimezone=Asia/Shanghai";


    //================ 其他配置 ================
    //作者
    private static String author = "hqz";
    //是否生成Dto，如果设置为false会生成entity
    public static boolean isDto = false;


    //================ 策略配置 ================
    private static void assembleStrategyConfig(AutoGenerator autoGenerator) {
        StrategyConfig strategyConfig = new StrategyConfig();

        //表名映射到实体策略，带下划线的转成驼峰
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);
        //列名映射到类型属性策略，带下划线的转成驼峰
        strategyConfig.setColumnNaming(NamingStrategy.underline_to_camel);

        // strategyConfig.setSuperEntityClass("com.baomidou.ant.common.BaseEntity");
        strategyConfig.setEntityLombokModel(true);//实体类使用lombok
//        strategyConfig.setRestControllerStyle(true);
        // strategyConfig.setSuperControllerClass("com.baomidou.ant.common.BaseController");

        // 如果 setInclude() //设置表名不加参数, 会自动查找所有表
        // 如需要制定单个表, 需填写参数如: strategyConfig.setInclude("user_info);
        strategyConfig.setInclude();
        // strategyConfig.setSuperEntityColumns("id");
//        strategyConfig.setControllerMappingHyphenStyle(true);

        //自动将数据库中表名为 user_info 格式的转为 UserInfo 命名
        strategyConfig.setTablePrefix(moduleName + "_");  //表名映射到实体名称去掉前缀
        strategyConfig.setEntityBooleanColumnRemoveIsPrefix(true); //Boolean类型字段是否移除is前缀处理
        autoGenerator.setStrategy(strategyConfig);
    }


    //================ 配置模板 ================
    private static void assembleTemplateConfig(AutoGenerator autoGenerator) {
        //设置模板引擎
        autoGenerator.setTemplateEngine(new FreemarkerTemplateEngine());

        //配置模板
        TemplateConfig templateConfig = new TemplateConfig();

        // 配置自定义输出模板
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        // templateConfig.setEntity("templates/entity-test.java");
        // templateConfig.setService("templates/service.java");
        // templateConfig.setController("templates/controller.java");

        templateConfig.setXml(null);
        autoGenerator.setTemplate(templateConfig);
    }


    //================ 自定义配置 ================
    private static void assembleInjectionConfig(AutoGenerator autoGenerator) {
        InjectionConfig injectionConfig = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };

        //如果模板引擎是 freemarker
        String templatePath = "/templates/mapper.xml.ftl";

        //自定义输出配置, 自定义配置会被优先输出
        List<FileOutConfig> focList = new ArrayList<>();
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名
                return projectPath + projectName + "/src/main/resources/mapper/" + moduleName
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });

        injectionConfig.setFileOutConfigList(focList);
        autoGenerator.setCfg(injectionConfig);
    }


    //================ 生成包配置 ================
    private static void assemblePackageConfig(AutoGenerator autoGenerator) {
        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setParent(parentPath);
        packageConfig.setModuleName(moduleName);
        if (isDto) {
            packageConfig.setEntity("dto");
        }
        autoGenerator.setPackageInfo(packageConfig);
    }


    //================ 数据源配置 ================
    private static void assembleDataSourceConfig(AutoGenerator autoGenerator) {
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDriverName(driver);
        dataSourceConfig.setUsername(username);
        dataSourceConfig.setPassword(password);
        dataSourceConfig.setUrl(url);
        autoGenerator.setDataSource(dataSourceConfig);
    }


    //================ 全局配置 ================
    private static void assembleGlobalConfig(AutoGenerator autoGenerator){
        GlobalConfig globalConfig = new GlobalConfig();

        //主键策略, 采用雪花片算法生成全局唯一ID
        globalConfig.setIdType(IdType.ID_WORKER);
        //日期类型使用Date
        globalConfig.setDateType(DateType.ONLY_DATE);

        //------------ 命名格式 ------------
        //dto命名格式
        if (isDto) {
            //是否开启Swagger注解
            globalConfig.setSwagger2(true);
            globalConfig.setEntityName("%sDto");
        }else{
            globalConfig.setEntityName("%s");
        }
        //service命名格式
        globalConfig.setServiceName("%sService");
        //mapper命名格式
        globalConfig.setMapperName("%sMapper");

        //代码输出路径
        globalConfig.setOutputDir(projectPath + projectName + "/src/main/java");
        //作者
        globalConfig.setAuthor(author);
        //生成后是否打开资源管理器
        globalConfig.setOpen(false);

        autoGenerator.setGlobalConfig(globalConfig);

    }


}