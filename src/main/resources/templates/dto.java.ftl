package ${other.dto};

<#if entitySerialVersionUID>
import java.io.Serializable;
import java.io.Serial;
</#if>
import java.time.LocalDateTime;
<#if springdoc>
import io.swagger.v3.oas.annotations.media.Schema;
<#elseif swagger>
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
</#if>
<#if entityLombokModel>
import lombok.Getter;
import lombok.Setter;
<#if chainModel>
import lombok.experimental.Accessors;
</#if>
</#if>

/**
* <p>
* ${table.comment!} DTO 数据传输对象
* </p>
*
* @author ${author}
* @since ${date}
*/
<#if entityLombokModel>
@Getter
@Setter
<#if chainModel>
@Accessors(chain = true)
</#if>
</#if>
<#if springdoc>
@Schema(name = "${entity}Dto", description = "${table.comment!} DTO 数据传输对象")
<#elseif swagger>
@ApiModel(value = "${entity}Dto对象", description = "${table.comment!} DTO 数据传输对象")
</#if>
<#if entitySerialVersionUID>
public class ${entity}Dto implements Serializable {
<#else>
public class ${entity}Dto {
</#if>
<#if entitySerialVersionUID>
    @Serial
    private static final long serialVersionUID = 1L;
</#if>
<#-- ----------  BEGIN 字段循环遍历  ---------->
<#list table.fields as field>
    <#if field.keyFlag>
        <#assign keyPropertyName="${field.propertyName}"/>
    </#if>

    <#if field.comment!?length gt 0>
    <#if springdoc>
    @Schema(description = "${field.comment}")
    <#elseif swagger>
    @ApiModelProperty("${field.comment}")
    <#else>
    /**
    * ${field.comment}
    */
    </#if>
    </#if>
    <#-- 普通字段 -->
    <#if field.fill??>
    <#-- -----   存在字段填充设置   ----->
    <#if field.convert>
    @TableField(value = "${field.annotationColumnName}", fill = FieldFill.${field.fill})
   <#else>
    @TableField(fill = FieldFill.${field.fill})
   </#if>
    <#elseif field.convert>
    @TableField("${field.annotationColumnName}")
    </#if>
    private ${field.propertyType} ${field.propertyName};
</#list>
<#------------  END 字段循环遍历  ---------->
<#if !entityLombokModel>
    <#list table.fields as field>
        <#if field.propertyType == "boolean">
            <#assign getprefix="is"/>
        <#else>
            <#assign getprefix="get"/>
        </#if>

        public ${field.propertyType} ${getprefix}${field.capitalName}() {
        return ${field.propertyName};
        }

        <#if chainModel>
            public ${entity} set${field.capitalName}(${field.propertyType} ${field.propertyName}) {
        <#else>
            public void set${field.capitalName}(${field.propertyType} ${field.propertyName}) {
        </#if>
        this.${field.propertyName} = ${field.propertyName};
        <#if chainModel>
            return this;
        </#if>
        }
    </#list>
</#if>
<#if entityColumnConstant>
    <#list table.fields as field>

        public static final String ${field.name?upper_case} = "${field.name}";
    </#list>
</#if>
<#if activeRecord>

    @Override
    public Serializable pkVal() {
    <#if keyPropertyName??>
        return this.${keyPropertyName};
    <#else>
        return null;
    </#if>
    }
</#if>
<#if !entityLombokModel>

    @Override
    public String toString() {
    return "${entity}{" +
    <#list table.fields as field>
        <#if field_index==0>
            "${field.propertyName} = " + ${field.propertyName} +
        <#else>
            ", ${field.propertyName} = " + ${field.propertyName} +
        </#if>
    </#list>
    "}";
    }
</#if>
}
