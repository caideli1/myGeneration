////
//// Source code recreated from a .class file by IntelliJ IDEA
//// (powered by Fernflower decompiler)
////
//
//package io.seata.sample.config;
//
//import io.seata.common.exception.ShouldNeverHappenException;
//import io.seata.config.ConfigurationChangeListener;
//import io.seata.config.ConfigurationFactory;
//import io.seata.core.rpc.netty.RmRpcClient;
//import io.seata.core.rpc.netty.ShutdownHook;
//import io.seata.core.rpc.netty.TmRpcClient;
//import io.seata.rm.datasource.DataSourceProxy;
//import io.seata.spring.annotation.GlobalLock;
//import io.seata.spring.annotation.GlobalTransactional;
//import io.seata.spring.annotation.GlobalTransactionalInterceptor;
//import io.seata.spring.annotation.MethodDesc;
//import io.seata.spring.annotation.datasource.DataSourceProxyHolder;
//import io.seata.spring.tcc.TccActionInterceptor;
//import io.seata.spring.util.SpringProxyUtils;
//import io.seata.spring.util.TCCBeanParserUtils;
//import io.seata.tm.api.DefaultFailureHandlerImpl;
//import io.seata.tm.api.FailureHandler;
//import org.aopalliance.intercept.MethodInterceptor;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.aop.Advisor;
//import org.springframework.aop.TargetSource;
//import org.springframework.aop.framework.AdvisedSupport;
//import org.springframework.aop.framework.autoproxy.AbstractAutoProxyCreator;
//import org.springframework.aop.support.AopUtils;
//import org.springframework.beans.BeanUtils;
//import org.springframework.beans.BeansException;
//import org.springframework.beans.factory.DisposableBean;
//import org.springframework.beans.factory.InitializingBean;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.ApplicationContextAware;
//import org.springframework.context.ConfigurableApplicationContext;
//
//import javax.sql.DataSource;
//import java.lang.reflect.InvocationHandler;
//import java.lang.reflect.Method;
//import java.lang.reflect.Proxy;
//import java.util.HashSet;
//import java.util.Set;
//
//public class GlobalTransactionScanner extends AbstractAutoProxyCreator implements InitializingBean, ApplicationContextAware, DisposableBean {
//    private static final long serialVersionUID = 1L;
//    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalTransactionScanner.class);
//    private static final int AT_MODE = 1;
//    private static final int MT_MODE = 2;
//    private static final int ORDER_NUM = 1024;
//    private static final int DEFAULT_MODE = 3;
//    private static final Set<String> PROXYED_SET = new HashSet();
//    private static final FailureHandler DEFAULT_FAIL_HANDLER = new DefaultFailureHandlerImpl();
//    private MethodInterceptor interceptor;
//    private final String applicationId;
//    private final String txServiceGroup;
//    private final int mode;
//    private final boolean disableGlobalTransaction;
//    private final FailureHandler failureHandlerHook;
//    private ApplicationContext applicationContext;
//
//    public GlobalTransactionScanner(String txServiceGroup) {
//        this(txServiceGroup, txServiceGroup, 3);
//    }
//
//    public GlobalTransactionScanner(String txServiceGroup, int mode) {
//        this(txServiceGroup, txServiceGroup, mode);
//    }
//
//    public GlobalTransactionScanner(String applicationId, String txServiceGroup) {
//        this(applicationId, txServiceGroup, 3);
//    }
//
//    public GlobalTransactionScanner(String applicationId, String txServiceGroup, int mode) {
//        this(applicationId, txServiceGroup, mode, DEFAULT_FAIL_HANDLER);
//    }
//
//    public GlobalTransactionScanner(String applicationId, String txServiceGroup, FailureHandler failureHandlerHook) {
//        this(applicationId, txServiceGroup, 3, failureHandlerHook);
//    }
//
//    public GlobalTransactionScanner(String applicationId, String txServiceGroup, int mode, FailureHandler failureHandlerHook) {
//        this.disableGlobalTransaction = ConfigurationFactory.getInstance().getBoolean("service.disableGlobalTransaction", false);
//        this.setOrder(1024);
//        this.setProxyTargetClass(true);
//        this.applicationId = applicationId;
//        this.txServiceGroup = txServiceGroup;
//        this.mode = mode;
//        this.failureHandlerHook = failureHandlerHook;
//    }
//
//    public void destroy() {
////        ShutdownHook.getInstance().destroyAll();
//    }
//
////    private void initClient() {
////        if (LOGGER.isInfoEnabled()) {
////            LOGGER.info("Initializing Global Transaction Clients ... ");
////        }
////
////        if (!StringUtils.isNullOrEmpty(this.applicationId) && !StringUtils.isNullOrEmpty(this.txServiceGroup)) {
////            TMClient.init(this.applicationId, this.txServiceGroup);
////            if (LOGGER.isInfoEnabled()) {
////                LOGGER.info("Transaction Manager Client is initialized. applicationId[" + this.applicationId + "] txServiceGroup[" + this.txServiceGroup + "]");
////            }
////
////            RMClient.init(this.applicationId, this.txServiceGroup);
////            if (LOGGER.isInfoEnabled()) {
////                LOGGER.info("Resource Manager is initialized. applicationId[" + this.applicationId + "] txServiceGroup[" + this.txServiceGroup + "]");
////            }
////
////            if (LOGGER.isInfoEnabled()) {
////                LOGGER.info("Global Transaction Clients are initialized. ");
////            }
////
////            this.registerSpringShutdownHook();
////        } else {
////            throw new IllegalArgumentException("applicationId: " + this.applicationId + ", txServiceGroup: " + this.txServiceGroup);
////        }
////    }
//
//    private void registerSpringShutdownHook() {
//        if (this.applicationContext instanceof ConfigurableApplicationContext) {
//            ((ConfigurableApplicationContext)this.applicationContext).registerShutdownHook();
//            ShutdownHook.removeRuntimeShutdownHook();
//        }
//
//        ShutdownHook.getInstance().addDisposable(TmRpcClient.getInstance(this.applicationId, this.txServiceGroup));
//        ShutdownHook.getInstance().addDisposable(RmRpcClient.getInstance(this.applicationId, this.txServiceGroup));
//    }
//
//    protected Object wrapIfNecessary(Object bean, String beanName, Object cacheKey) {
//        if (this.disableGlobalTransaction) {
//            return bean;
//        } else {
//            try {
//                synchronized(PROXYED_SET) {
//                    if (PROXYED_SET.contains(beanName)) {
//                        return bean;
//                    } else {
//                        this.interceptor = null;
//                        if (TCCBeanParserUtils.isTccAutoProxy(bean, beanName, this.applicationContext)) {
//                            this.interceptor = new TccActionInterceptor(TCCBeanParserUtils.getRemotingDesc(beanName));
//                        } else {
//                            Class<?> serviceInterface = SpringProxyUtils.findTargetClass(bean);
//                            Class<?>[] interfacesIfJdk = SpringProxyUtils.findInterfaces(bean);
//                            if (!this.existsAnnotation(new Class[]{serviceInterface}) && !this.existsAnnotation(interfacesIfJdk)) {
//                                return bean;
//                            }
//
//                            if (this.interceptor == null) {
//                                this.interceptor = new GlobalTransactionalInterceptor(this.failureHandlerHook);
//                                ConfigurationFactory.getInstance().addConfigListener("service.disableGlobalTransaction", (ConfigurationChangeListener)this.interceptor);
//                            }
//                        }
//
//                        LOGGER.info("Bean[" + bean.getClass().getName() + "] with name [" + beanName + "] would use interceptor [" + this.interceptor.getClass().getName() + "]");
//                        if (!AopUtils.isAopProxy(bean)) {
//                            bean = super.wrapIfNecessary(bean, beanName, cacheKey);
//                        } else {
//                            AdvisedSupport advised = SpringProxyUtils.getAdvisedSupport(bean);
//                            Advisor[] advisor = this.buildAdvisors(beanName, this.getAdvicesAndAdvisorsForBean((Class)null, (String)null, (TargetSource)null));
//                            Advisor[] var7 = advisor;
//                            int var8 = advisor.length;
//
//                            for(int var9 = 0; var9 < var8; ++var9) {
//                                Advisor avr = var7[var9];
//                                advised.addAdvisor(0, avr);
//                            }
//                        }
//
//                        PROXYED_SET.add(beanName);
//                        return bean;
//                    }
//                }
//            } catch (Exception var13) {
//                throw new RuntimeException(var13);
//            }
//        }
//    }
//
//    private boolean existsAnnotation(Class<?>[] classes) {
//        if (classes != null && classes.length > 0) {
//            Class[] var2 = classes;
//            int var3 = classes.length;
//
//            for(int var4 = 0; var4 < var3; ++var4) {
//                Class clazz = var2[var4];
//                if (clazz != null) {
//                    Method[] methods = clazz.getMethods();
//                    Method[] var7 = methods;
//                    int var8 = methods.length;
//
//                    for(int var9 = 0; var9 < var8; ++var9) {
//                        Method method = var7[var9];
//                        GlobalTransactional trxAnno = (GlobalTransactional)method.getAnnotation(GlobalTransactional.class);
//                        if (trxAnno != null) {
//                            return true;
//                        }
//
//                        GlobalLock lockAnno = (GlobalLock)method.getAnnotation(GlobalLock.class);
//                        if (lockAnno != null) {
//                            return true;
//                        }
//                    }
//                }
//            }
//        }
//
//        return false;
//    }
//
//    private MethodDesc makeMethodDesc(GlobalTransactional anno, Method method) {
//        return new MethodDesc(anno, method);
//    }
//
//    protected Object[] getAdvicesAndAdvisorsForBean(Class beanClass, String beanName, TargetSource customTargetSource) throws BeansException {
//        return new Object[]{this.interceptor};
//    }
//
//    public void afterPropertiesSet() {
//        if (this.disableGlobalTransaction) {
//            if (LOGGER.isInfoEnabled()) {
//                LOGGER.info("Global transaction is disabled.");
//            }
//            return;
//        }
////        else {
////            this.initClient();
////        }
//    }
//
//    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
//        this.applicationContext = applicationContext;
//        this.setBeanFactory(applicationContext);
//    }
//
//    public Object postProcessBeforeInitialization(Object bean, String beanName) {
//        if (bean instanceof DataSourceProxy && ConfigurationFactory.getInstance().getBoolean("client.support.spring.datasource.autoproxy", false)) {
//            throw new ShouldNeverHappenException("Auto proxy of DataSource can't be enabled as you've created a DataSourceProxy bean.Please consider removing DataSourceProxy bean or disabling auto proxy of DataSource.");
//        } else {
//            return super.postProcessBeforeInitialization(bean, beanName);
//        }
//    }
//
//    public Object postProcessAfterInitialization(final Object bean, String beanName) throws BeansException {
//        if (bean instanceof DataSource && !(bean instanceof DataSourceProxy) && ConfigurationFactory.getInstance().getBoolean("client.support.spring.datasource.autoproxy", false)) {
//            if (LOGGER.isInfoEnabled()) {
//                LOGGER.info("Auto proxy of [{}]", beanName);
//            }
//
//            final DataSourceProxy dataSourceProxy = DataSourceProxyHolder.get().putDataSource((DataSource)bean);
//            Class<?>[] interfaces = SpringProxyUtils.getAllInterfaces(bean);
//            return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), interfaces, new InvocationHandler() {
//                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//                    Method m = BeanUtils.findDeclaredMethod(DataSourceProxy.class, method.getName(), method.getParameterTypes());
//                    if (null != m) {
//                        return m.invoke(dataSourceProxy, args);
//                    } else {
//                        boolean oldAccessible = method.isAccessible();
//
//                        Object var6;
//                        try {
//                            method.setAccessible(true);
//                            var6 = method.invoke(bean, args);
//                        } finally {
//                            method.setAccessible(oldAccessible);
//                        }
//
//                        return var6;
//                    }
//                }
//            });
//        } else {
//            return super.postProcessAfterInitialization(bean, beanName);
//        }
//    }
//}
