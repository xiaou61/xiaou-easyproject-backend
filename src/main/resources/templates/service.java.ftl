package ${package.Service};

import ${package.Entity}.${entity};
import ${superServiceClassPackage};
import com.xiaohai.common.daomain.ReturnPageData;
import ${other.query}.${entity}Query;
import ${other.vo}.${entity}Vo;
import ${other.dto}.${entity}Dto;

/**
 *
 * ${table.comment!} 服务类
 *
 *
 * @author ${author}
 * @since ${date}
 */
<#if kotlin>
interface ${table.serviceName} : ${superServiceClass}<${entity}>
<#else>
public interface ${table.serviceName} extends ${superServiceClass}<${entity}> {


    /**
     * 添加${table.comment!}
     *
     * @param vo ${table.comment!} VO（View Object）：显示层对象
     * @return Integer
     */
    Integer add(${entity}Vo vo);

    /**
     * 删除${table.comment!}
     *
     * @param ids 主键
     * @return Integer
     */
    Integer delete(Long[] ids);

    /**
     * 修改${table.comment!}
     *
     * @param vo ${table.comment!} VO（View Object）：显示层对象
     * @return Integer
     */
    Integer updateData(${entity}Vo vo);

    /**
     * id查询数据
     *
     * @param id id
     * @return   ${entity}
*/
    ${entity} findById(Long id);

    /**
    * 查询${table.comment!}列表数据
    *
    * @param query ${table.comment!} Query 数据查询对象
    * @return Response
    */
    ReturnPageData<${entity}Dto> findListByPage(${entity}Query query);
}
</#if>
