package com.luosoy.main.security;

import com.luosoy.main.dto.UrlFilterDTO;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.NamedFilterList;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.springframework.beans.factory.annotation.Autowired;

public class ShiroFilerChainManager {

    @Autowired
    private AbstractShiroFilter shiroFilter;

    private Map<String, NamedFilterList> defaultFilterChains;

    @PostConstruct
    public void init() {
        defaultFilterChains = new LinkedHashMap<String, NamedFilterList>(getFilterChainManager().getFilterChains());
    }

    public void initFilterChains(List<UrlFilterDTO> urlFilters) {
        //1、首先删除以前老的
        getFilterChainManager().getFilterChains().clear();
        
        //2、循环URL Filter 注册filter chain
        for (UrlFilterDTO urlFilter : urlFilters) {
            String url = urlFilter.getUrl();
            //注册roles filter
            if (!StringUtils.isEmpty(urlFilter.getRoles())) {
                getFilterChainManager().addToChain(url, "roles", urlFilter.getRoles());
            }
            //注册perms filter
            if (!StringUtils.isEmpty(urlFilter.getPermissions())) {
                getFilterChainManager().addToChain(url, "perms", urlFilter.getPermissions());
            }
        }
        //3.注册默认的filter chain
        if (defaultFilterChains != null) {
            getFilterChainManager().getFilterChains().putAll(defaultFilterChains);
        }

    }

    private DefaultFilterChainManager getFilterChainManager() {
        return ((DefaultFilterChainManager) ((PathMatchingFilterChainResolver)shiroFilter.getFilterChainResolver()).getFilterChainManager());
    }

}
