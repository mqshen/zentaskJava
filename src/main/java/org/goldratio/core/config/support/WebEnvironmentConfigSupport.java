package org.goldratio.core.config.support;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.goldratio.web.controllers.security.CSRFHandlerInterceptor;
import org.goldratio.web.controllers.security.CSRFRequestDataValueProcessor;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.support.RequestDataValueProcessor;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.sql.DataSource;

/** 
 * ClassName: WebEnvironmentConfigSupport <br/> 
 * Function: <br/> 
 * Reason: <br/> 
 * date: Mar 27, 2013 3:37:37 PM <br/> 
 * 
 * @author GoldRatio 
 * @version 1.0
 */

public abstract class WebEnvironmentConfigSupport extends WebMvcConfigurationSupport {
	/**
	 * 默认连接池大小 
	 */
	private static final String JDBC_POOL_MIN_SIZE = "5";
	/**
	 * 默认连接池大小
	 */
	private static final String JDBC_POOL_MAX_SIZE = "20";
	/**
	 * 默认连接池超时时间
	 */
	private static final String JDBC_POOL_TIMEOUT = "1800";
	/**
	 * 默认statement大小
	 */
	private static final String JDBC_POOL_MAX_STATEMENTS = "50";
	
	/**
	 * 默认的索引文件存放路径
	 */
	private static final String SYSTEM_PATH_INDEX = "goldratio/index";
	/**
	 * 默认的临时文件存放位置
	 */
	private static final String SYSTEM_PATH_TEMP = "goldratio/temp";

	/**
	 * 数据源
	 * @return
	 * @throws IOException
	 */
	@Bean(destroyMethod = "close")
	public DataSource dataSource() throws IOException {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(configProperties().getProperty("jdbc.driver"));
		dataSource.setUrl(configProperties().getProperty("jdbc.url"));
		dataSource.setUsername(configProperties().getProperty("jdbc.username"));
		dataSource.setPassword(configProperties().getProperty("jdbc.password"));
	    return dataSource;
	}
	
	/* (non-Javadoc)
	 * @see com.ycnet.belink.core.config.support.WebEnvironmentConfigSupport#getDatabase()
	 */
	protected Database getDatabase() {
		return Database.MYSQL;
	}

	/**
	 * 初始化系统配置,主要是为各个参数配置一个默认值。
	 * @param properties
	 * @throws IOException
	 */
	protected void initConfigProperties(Properties properties) throws IOException{
		setPropertyDefaultValue(properties, "jdbc.pool.min_size", JDBC_POOL_MIN_SIZE);
		setPropertyDefaultValue(properties, "jdbc.pool.max_size", JDBC_POOL_MAX_SIZE);
		setPropertyDefaultValue(properties, "jdbc.pool.timeout", JDBC_POOL_TIMEOUT);
		setPropertyDefaultValue(properties, "jdbc.pool.max_statements", JDBC_POOL_MAX_STATEMENTS);
		setPropertyDefaultValue(properties, "system.path.index", SYSTEM_PATH_INDEX);
		setPropertyDefaultValue(properties, "system.path.temp", SYSTEM_PATH_TEMP);
		FileUtils.forceMkdir(new File(properties.getProperty("system.path.index")));
		FileUtils.forceMkdir(new File(properties.getProperty("system.path.temp")));
	}

	/**
	 * 设置属性的默认值,如果在配置文件中未指定某个参数的值，则使用默认值
	 * @param properties
	 * @param key
	 * @param defaultValue
	 */
	private void setPropertyDefaultValue(Properties properties, String key, String defaultValue) {
		String value = properties.getProperty(key);
		if(StringUtils.isBlank(value)){
			properties.setProperty(key, defaultValue);
		}
	}
	
	
	/**
	 * 注册jspViewResolver
	 * @return
	 */
	@Bean
	public InternalResourceViewResolver jspViewResolver(){
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}
	
	/**
	 * 注册jsonView
	 * @return
	 */
	@Bean
	public MappingJackson2JsonView jsonView(){
		return new MappingJackson2JsonView();
	}
	
	/**
	 * 注册默认的viewResolver，控制层controller返回modelAndView时，先用tilesViewResolver解析view的名字，然后用jspViewResolver解析.
	 * 收到application/json请求时使用jsonView。
	 * @return
	 */
	@Bean
	public ViewResolver viewResolver(){
		ContentNegotiatingViewResolver viewResolver = new ContentNegotiatingViewResolver();
		
		List<ViewResolver> viewResolvers = new ArrayList<ViewResolver>();
		viewResolvers.add(jspViewResolver());
		viewResolver.setViewResolvers(viewResolvers);
		
		
		List<View> defaultViews = new ArrayList<View>();
		defaultViews.add(jsonView());
		viewResolver.setDefaultViews(defaultViews);
		
		return viewResolver;
	}
	
	/**
	 * 根据配置类的名字获取当前的的环境名称
	 * @return
	 */
	protected String getEnvironment(){
		return StringUtils.uncapitalize(StringUtils.substringBefore(getClass().getSimpleName(), "Config"));
	};
	
	/**
	 * 读取配置文件，根据环境的不同读取的文件不同
	 * @return
	 * @throws IOException
	 */
	@Bean
	public Properties configProperties() throws IOException{
		PropertiesFactoryBean factoryBean = new PropertiesFactoryBean();
		factoryBean.setLocations(new Resource[]{
				new ClassPathResource("config/environment/"+getEnvironment()+".properties"),
				new ClassPathResource("config/system.properties")
		});
		factoryBean.afterPropertiesSet();
		Properties properties = factoryBean.getObject();
		initConfigProperties(properties);
		return properties;
	}

	/**
	 * 指定JPA的实现（Hibernate）
	 * @return
	 */
	@Bean
	public JpaVendorAdapter vendorAdapter(){
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
	    vendorAdapter.setShowSql(true);
	    vendorAdapter.setGenerateDdl(true);
	    vendorAdapter.setDatabase(getDatabase());
	    return vendorAdapter;
	}
	
	
	@Bean
	public RequestDataValueProcessor requestDataValueProcessor(){
		CSRFRequestDataValueProcessor requestDataValueProcessor = new CSRFRequestDataValueProcessor();
	    return requestDataValueProcessor;
	}
	
	@Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new CSRFHandlerInterceptor());
    }
}