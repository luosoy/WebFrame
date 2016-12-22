package com.luosoy.main.service;

import com.luosoy.common.enums.ResTypeDmEnum;
import com.luosoy.common.utils.Const;
import com.luosoy.main.dto.UrlAllDTO;
import com.luosoy.main.dto.UrlFilterDTO;
import java.util.ArrayList;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class UrlFilterService implements InitializingBean {

    @Autowired
    @Qualifier(value = "NativeEntityManager")
    private EntityManager em;

    @Autowired
    private ShiroFilerChainManager shiroFilerChainManager;

    public void initFilterChain() {
        shiroFilerChainManager.initFilterChains(findAll());
    }

    private List<UrlFilterDTO> findAll() {
        List<UrlFilterDTO> filterDTOs = new ArrayList<UrlFilterDTO>();
        TypedQuery<UrlAllDTO> query = em.createQuery("select new com.luosoy.main.dto.UrlAllDTO(a.resurl,b.uuid,c.permissionDm) "
                + "from QxResourcesCMP a,QxRoleResourcesCMP c,QxRoleCMP b "
                + "WHERE a.uuid = c.qxRoleResourcesCMPPK.resourcesUuid "
                + "and c.qxRoleResourcesCMPPK.roleUuid = b.uuid "
                + "and a.resTypeDm='" + ResTypeDmEnum.MK.getCode() + "' "
                + "and a.yxbz='Y' "
                + "and b.yxbz='Y'", UrlAllDTO.class);
        filterDTOs.addAll(getFilterMap(query.getResultList()).values());
        return filterDTOs;
    }

    private Map<String, UrlFilterDTO> getFilterMap(List<UrlAllDTO> allDTOs) {
        Map<String, UrlFilterDTO> map = new HashMap<String, UrlFilterDTO>();
        for (UrlAllDTO allDTO : allDTOs) {
            if (map.containsKey(allDTO.getUrl())) {
                UrlFilterDTO filterDTO = map.get(allDTO.getUrl());
                setFilterDTO(allDTO, filterDTO);
            } else {
                UrlFilterDTO filterDTO = new UrlFilterDTO();
                setFilterDTO(allDTO, filterDTO);
                map.put(allDTO.getUrl(), filterDTO);
            }
        }
        return map;
    }

    private void setFilterDTO(UrlAllDTO allDTO, UrlFilterDTO filterDTO) {
        if (StringUtils.isNotEmpty(allDTO.getRole())) {
            if (StringUtils.isNotEmpty(filterDTO.getRoles())) {
                filterDTO.setRoles(filterDTO.getRoles() + Const.DEFAULT_DELIMITER_CHAR + allDTO.getRole());
            } else {
                filterDTO.setRoles(allDTO.getRole());
            }
        }
        if (StringUtils.isNotEmpty(String.valueOf(allDTO.getPermission()))) {
            if (StringUtils.isNotEmpty(filterDTO.getPermissions())) {
                filterDTO.setPermissions(filterDTO.getPermissions() + Const.DEFAULT_DELIMITER_CHAR + allDTO.getPermission());
            } else {
                filterDTO.setPermissions(String.valueOf(allDTO.getPermission()));
            }
        }
        filterDTO.setUrl(allDTO.getUrl());
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        initFilterChain();
    }

}
