package com.shanjupay.generator;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * MyBatisPlus代码生成器
 */
public class MyBatisPlusGenerator {

    //创建代码生成器
    public static AutoGenerator autoGenerator = new AutoGenerator();

    //================ 路径信息 ================
    //项目所在路径
    public static String projectPath = System.getProperty("user.dir");
    //生成代码存放的文件夹
    public static String projectName = "/code";
    //父包名
    public static String parentPath = "com.shanjupay";
    //模块包名
    public static String moduleName = "merchant";

    //================ 数据源 ================
    //数据库驱动
    public static String driver = "com.mysql.cj.jdbc.Driver";
    public static String username = "root";
    public static String password = "root";
    public static String url =
            "jdbc:mysql://localhost:3306/temp_generator?serverTimezone=Asia/Shanghai";
//    public static String url =
//            "jdbc:mysql://127.0.0.1:3306/shanjupay_transaction?serverTimezone=Asia/Shanghai";


    //================ 其他配置================
    public static String author = "hqz"; //作者
    public static boolean isDto = false; //设置为false则生成entity, 否则生成dto


    //================ main方法 ================
    public static void main(String[] args) {

        //------------ 配置代码生成器 ------------
        //全局配置
        setGlobalConfig();
        //数据源配置
        setDataSource();
        //生成包配置
        setPackageInfo();
        //自定义配置
        setInjectionConfig();
        //模板配置
        setTemplate();
        //策略配置
        setStrategy();
        //模板引擎配置
        setTemplateEngine();

        //------------ 开始生成代码 ------------
        autoGenerator.execute();
    }

    //================ 配置模板引擎 ================
    private static void setTemplateEngine() {
        //设置模板引擎
        autoGenerator.setTemplateEngine(new FreemarkerTemplateEngine());
    }

    //================ 策略配置 ================
    private static void setStrategy() {

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
        strategyConfig.setTablePrefix(moduleName + "_");//表名映射到实体名称去掉前缀
        strategyConfig.setEntityBooleanColumnRemoveIsPrefix(true);// Boolean类型字段是否移除is前缀处理
        autoGenerator.setStrategy(strategyConfig);
    }

    //================ 配置模板 ================
    private static void setTemplate() {

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
    private static void setInjectionConfig() {
        InjectionConfig injectionConfig = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };

        // 如果模板引擎是 freemarker
        String templatePath = "/templates/mapper.xml.ftl";

        // 自定义输出配置, 自定义配置会被优先输出
        List<FileOutConfig> focList = new ArrayList<>();
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名
                return projectPath + projectName+"/src/main/resources/mapper/" + moduleName
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });

        injectionConfig.setFileOutConfigList(focList);
        autoGenerator.setCfg(injectionConfig);
    }

    //================ 生成包配置 ================
    private static void setPackageInfo() {
        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setParent(parentPath);
        packageConfig.setModuleName(moduleName);
        if (isDto) {
            packageConfig.setEntity("dto");
        }
        autoGenerator.setPackageInfo(packageConfig);
    }

    //================ 数据源配置 ================
    private static void setDataSource() {
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDriverName(driver);
        dataSourceConfig.setUsername(username);
        dataSourceConfig.setPassword(password);
        dataSourceConfig.setUrl(url);
        autoGenerator.setDataSource(dataSourceConfig);
    }

    //================ 全局配置 ================
    private static void setGlobalConfig(){
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setOutputDir(projectPath + projectName + "/src/main/java");
        globalConfig.setAuthor(author);
        globalConfig.setOpen(false);
        globalConfig.setIdType(IdType.ID_WORKER); //主键策略, 采用雪花片算法生成全局唯一ID
        if (isDto) {
            globalConfig.setSwagger2(true); //是否开启Swagger注解
            globalConfig.setEntityName("%sDto");
        }
        autoGenerator.setGlobalConfig(globalConfig);
    }

    //================ 读取控制台内容 ================
    private static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotEmpty(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }
}