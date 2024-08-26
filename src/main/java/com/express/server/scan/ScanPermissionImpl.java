package com.express.server.scan;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.condition.RequestMethodsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.Map;
import java.util.Set;

/**
 * @author youping.tan
 * @date 2024/8/6 14:02
 */
@Component
public class ScanPermissionImpl implements CommandLineRunner, ApplicationContextAware {

    private static final Logger LOGGER = LoggerFactory.getLogger(ScanPermissionImpl.class);

    private ApplicationContext applicationContext;

    @Override
    public void run(String... args) {
        LOGGER.warn("Running scan request mapping");
//        Set<FuncPermission> funcPermissionSets = new HashSet<>();
        RequestMappingHandlerMapping mapping = applicationContext.getBean(RequestMappingHandlerMapping.class);
        Map<RequestMappingInfo, HandlerMethod> map = mapping.getHandlerMethods();
        Set<RequestMappingInfo> requestMappingInfos = map.keySet();
        for (RequestMappingInfo requestMappingInfo : requestMappingInfos) {
            PatternsRequestCondition patternsCondition = requestMappingInfo.getPatternsCondition();
            if (patternsCondition != null) {
                String element = null;
                Set<String> patterns = patternsCondition.getPatterns();
                for (String pattern : patterns) {
                    element = pattern;
                }
                RequestMethodsRequestCondition methodsCondition = requestMappingInfo.getMethodsCondition();
                String method = null;
                Set<RequestMethod> methods = methodsCondition.getMethods();
                for (RequestMethod requestMethod : methods) {
                    method = requestMethod.name();
                }
//                if (StringUtils.isNoneBlank(element, method)) {
//                    FuncPermission funcPermission = new FuncPermission();
//                    funcPermission.setMethod(method);
//                    funcPermission.setFuncPermName(element);
//                    funcPermission.setPerms(element);
//                    funcPermission.setRemark(element);
//                    funcPermissionSets.add(funcPermission);
//                }
            }
        }
//        PermissionService permissionService = applicationContext.getBean(PermissionService.class);
//        permissionService.enter(funcPermissionSets);
        LOGGER.warn("Scan request mapping finished");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
