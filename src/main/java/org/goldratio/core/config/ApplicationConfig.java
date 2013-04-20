package org.goldratio.core.config;

import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Properties;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor.DiscardPolicy;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.goldratio.core.velocity.VelocityManager;
import org.hibernate.ejb.HibernatePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.concurrent.ConcurrentMapCacheFactoryBean;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaDialect;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ThemeResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.theme.SessionThemeResolver;

/** 
 * ClassName: ApplicationConfig <br/> 
 * Function: <br/> 
 * Reason: <br/> 
 * date: Apr 1, 2013 2:39:41 PM <br/> 
 * 
 * @author GoldRatio 
 * @version 1.0
 */

@Configuration
@ComponentScan(basePackages = "org.goldratio", excludeFilters = {
		@ComponentScan.Filter(type = FilterType.ANNOTATION, value = Controller.class),
		@ComponentScan.Filter(type = FilterType.ANNOTATION, value = Configuration.class)})
@EnableAspectJAutoProxy
@EnableTransactionManagement
@EnableCaching
@EnableScheduling
@EnableAsync
@EnableJpaRepositories(basePackages = "org.goldratio.repositories")
public class ApplicationConfig implements SchedulingConfigurer, AsyncConfigurer {
	
	@Autowired 
	private DataSource dataSource;
	
	@Autowired
	private JpaVendorAdapter vendorAdapter;
	
	@Autowired
	private Properties configProperties;
	
	/**
	 * 系统配置
	 *
	 * @return {@link SystemConfig}
	 *
	 * @author jojo 2013-1-10 上午8:27:14
	 */
	@Bean(initMethod = "init")
	public SystemConfig systemConfig() {
		return new SystemConfig(configProperties);
	}
	
	/**
	 * 设置定时任务调度器，提供@{@link Scheduled}的支持
	 * 
	 * @param taskRegistrar
	 *
	 * @author jojo 2013-1-10 上午8:27:22
	 */
	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
		taskRegistrar.setScheduler(taskScheduler());
	}
	
	//modify by Jiang
	/**
	 * 提供调度定时任务的调度器，使用这个bean可以用编码的方式来管理定时任务
	 *
	 * @return {@link TaskScheduler}
	 *
	 * @author jojo 2013-1-10 上午8:27:50
	 */
	@Bean
	public TaskScheduler taskScheduler() {
		return new ThreadPoolTaskScheduler();
	}
	
	/**
	 * 提供异步执行注解@{@link Async}的支持
	 * 
	 * @return {@link Executor}
	 *
	 * @author jojo 2013-1-10 上午8:27:57
	 */
	public Executor getAsyncExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(20);
        executor.setMaxPoolSize(50);
        executor.setQueueCapacity(100);
        executor.setThreadNamePrefix("BelinkExecutor-");
        executor.setRejectedExecutionHandler(new DiscardPolicy());
        executor.initialize();
        return executor;

	}
	
	/**
	 * 提供{@link Cacheable}的支持
	 * 
	 * @TODO 当前使用的是{@link SimpleCacheManager}，上线前会替换成{@link RedisCacheManager}
	 * 
	 * @return {@link SimpleCacheManager}
	 *
	 * @author jojo 2013-1-10 上午8:31:47
	 */
	@Bean
	public CacheManager cacheManager(){
		
		SimpleCacheManager cacheManager = new SimpleCacheManager();
		
		Collection<ConcurrentMapCache> caches = new HashSet<ConcurrentMapCache>();
		caches.add(getCacheFactory("domainFacades").getObject());
		
		cacheManager.setCaches(caches);
		return cacheManager;
	}

	/**
	 * 构建cache工厂，当前使用的是{@link ConcurrentMapCacheFactoryBean}
	 *
	 * @param cacheName
	 * @return
	 *
	 * @author jojo 2013-1-10 上午8:33:51
	 */
	private ConcurrentMapCacheFactoryBean getCacheFactory(String cacheName) {
		ConcurrentMapCacheFactoryBean cacheFactory = new ConcurrentMapCacheFactoryBean();
		cacheFactory.setName(cacheName);
		cacheFactory.afterPropertiesSet();
		return cacheFactory;
	}
	
	/**
	 * 提供jpa支持
	 *
	 * @return
	 * @throws IOException
	 *
	 * @author jojo 2013-1-10 上午8:36:11
	 */
	@Bean 
  	public EntityManagerFactory entityManagerFactory() throws IOException {
		
		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		
	    Properties jpaProperties = new Properties();
	    jpaProperties.setProperty("hibernate.format_sql", "true");
	    jpaProperties.setProperty("hibernate.max_fetch_depth", "2");
	    jpaProperties.setProperty("hibernate.c3p0.min_size", configProperties.getProperty("jdbc.pool.min_size"));
	    jpaProperties.setProperty("hibernate.c3p0.max_size", configProperties.getProperty("jdbc.pool.max_size"));
	    jpaProperties.setProperty("hibernate.c3p0.timeout", configProperties.getProperty("jdbc.pool.timeout"));
	    jpaProperties.setProperty("hibernate.c3p0.max_statements", configProperties.getProperty("jdbc.pool.max_statements"));
	    jpaProperties.setProperty("hibernate.search.default.indexBase", configProperties.getProperty("system.path.index"));
	    jpaProperties.setProperty("hibernate.ejb.naming_strategy", "org.goldratio.core.hibernate.ZenTaskNamingStrategy");
	    
		factory.setDataSource(dataSource);
		factory.setPersistenceXmlLocation("classpath:META-INF/persistence.xml");
		factory.setPersistenceUnitName("belink");
		factory.setPersistenceProvider(new HibernatePersistence());
		factory.setJpaVendorAdapter(vendorAdapter);
		factory.setJpaDialect(jpaDialect());
		factory.setJpaProperties(jpaProperties);
		
	    factory.afterPropertiesSet();
	    
	    return factory.getObject();
	}

	/**
	 * 指定jpa的实现为{@link HibernateJpaDialect}
	 *
	 * @return
	 *
	 * @author jojo 2013-1-10 上午8:36:27
	 */
	@Bean 
	public JpaDialect jpaDialect() {
		return new HibernateJpaDialect();
	}

	/**
	 * 增加事务支持，使用{@link JpaTransactionManager}
	 *
	 * @return
	 * @throws IOException
	 *
	 * @author jojo 2013-1-10 上午8:36:54
	 */
	@Bean 
  	public PlatformTransactionManager transactionManager() throws IOException {
	    JpaTransactionManager txManager = new JpaTransactionManager();
	    txManager.setEntityManagerFactory(entityManagerFactory());
	    return txManager;
	}
	
	/**
	 * 增加消息文件的支持,读取error_manage_zh_CN.properties和message_manage_zh_CN.properties
	 *
	 * @return {@link ResourceBundleMessageSource}
	 *
	 * @author jojo 2013-1-10 上午8:37:22
	 */
	@Bean
	public ResourceBundleMessageSource messageSource(){
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasenames("error_manage_zh_CN","message_manage_zh_CN");
		return messageSource;
	}
	
	/**
	 * locale解析器，提供多语言支持
	 *
	 * @return {@link SessionLocaleResolver}
	 *
	 * @author jojo 2013-1-10 上午8:38:13
	 */
	@Bean
	public LocaleResolver localeResolver(){
		return new SessionLocaleResolver();
	}
	
	/**
	 * theme解析器，提供换肤支持
	 *
	 * @return {@link SessionThemeResolver}
	 *
	 * @author jojo 2013-1-10 上午8:38:29
	 */
	@Bean
	public ThemeResolver themeResolver(){
		SessionThemeResolver themeResolver = new SessionThemeResolver();
		themeResolver.setDefaultThemeName("dhx_skyblue");
		return themeResolver;
	}
	
	/**
	 * multipart解析器，提供文件上传支持
	 *
	 * @return {@link CommonsMultipartResolver}
	 *
	 * @author jojo 2013-1-10 上午8:39:02
	 */
	@Bean
	public CommonsMultipartResolver multipartResolver(){
		return new CommonsMultipartResolver();
	}
	
	
	@Bean
	public PasswordEncoder passwordEncoder(){
		return new ShaPasswordEncoder();
	}
	
	
	/**
	 * velocity模版管理器
	 *
	 * @return {@link VelocityManager}
	 *
	 * @author jojo 2013-1-10 上午8:46:37
	 */
	@Bean(initMethod = "init")
	public VelocityManager velocityManager(){
		return new VelocityManager();
	}
}