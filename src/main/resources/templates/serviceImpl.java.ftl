package ${package.ServiceImpl};

import com.xiaohai.common.daomain.PageData;
import ${package.Entity}.${entity};
import ${package.Mapper}.${table.mapperName};
import ${package.Service}.${table.serviceName};
import ${superServiceImplClassPackage};
import com.xiaohai.common.daomain.ReturnPageData;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.xiaohai.common.utils.PageUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.stereotype.Service;
import org.springframework.beans.BeanUtils;
import ${other.query}.${entity}Query;
import ${other.vo}.${entity}Vo;
import ${other.dto}.${entity}Dto;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * ${table.comment!} 服务实现类
 *
 * @author ${author}
 * @since ${date}
 */
@Service
<#if kotlin>
open class ${table.serviceImplName} : ${superServiceImplClass}<${table.mapperName}, ${entity}>(), ${table.serviceName} {

}
<#else>
public class ${table.serviceImplName} extends ${superServiceImplClass}<${table.mapperName}, ${entity}> implements ${table.serviceName} {

    @Override
    public Integer add(${entity}Vo vo){
        ${entity} ${entity?uncap_first}=new ${entity}();
        BeanUtils.copyProperties(vo,${entity?uncap_first});
        return baseMapper.insert(${entity?uncap_first});
    }

    @Override
    public Integer delete(Long[] ids){
        for (Long id : ids) {
            baseMapper.deleteById(id);
        }
        return ids.length;
    }

    @Override
    public Integer updateData(${entity}Vo vo){
        ${entity} ${entity?uncap_first}=new ${entity}();
        BeanUtils.copyProperties(vo,${entity?uncap_first});
        return baseMapper.updateById(${entity?uncap_first});
    }

    @Override
    public ${entity} findById(Long id){
        return baseMapper.selectById(id);
    }

    @Override
    public ReturnPageData<${entity}Dto> findListByPage(${entity}Query query){
        ${entity} ${entity?uncap_first}=new ${entity}();
        BeanUtils.copyProperties(query,${entity?uncap_first});
        IPage<${entity}> wherePage = new Page<>(PageUtils.getPageNo(), PageUtils.getPageSize());
        IPage<${entity}> iPage = baseMapper.selectPage(wherePage,Wrappers.query(${entity?uncap_first}));
        List<${entity}Dto> list=new ArrayList<>();
        for(${entity} ${entity?uncap_first}s:iPage.getRecords()){
            ${entity}Dto ${entity?uncap_first}Dto=new ${entity}Dto();
            BeanUtils.copyProperties(${entity?uncap_first}s,${entity?uncap_first}Dto);
            list.add(${entity?uncap_first}Dto);
        }
        PageData pageData=new PageData();
        BeanUtils.copyProperties(iPage,pageData);
        return ReturnPageData.fillingData(pageData,list);
    }
}
</#if>
