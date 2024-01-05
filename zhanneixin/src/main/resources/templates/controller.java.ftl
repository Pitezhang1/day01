package ${package.Controller};

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import ${package.Entity}.${entity};
import ${package.Service}.${table.serviceName};
import java.util.List;

<#if restControllerStyle>
import org.springframework.web.bind.annotation.RestController;
<#else>
import org.springframework.stereotype.Controller;
</#if>
<#if superControllerClassPackage??>
import ${superControllerClassPackage};
</#if>

/**
 * <p>
 * ${table.comment!} 控制层
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
<#if restControllerStyle>
@RestController
<#else>
@Controller
</#if>
@RequestMapping("<#if package.ModuleName?? && package.ModuleName != "">/${package.ModuleName}</#if>/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>")
<#if kotlin>
class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
<#if superControllerClass??>
public class ${table.controllerName} extends ${superControllerClass} {
<#else>
public class ${table.controllerName} {
</#if>

    @Autowired
    ${table.serviceName} ${entity?uncap_first}Service;

    /**
     * 添加接口
     */
    @RequestMapping("/add")
    public void add${entity}(@RequestBody ${entity} ${entity?uncap_first}){
        ${entity?uncap_first}Service.save(${entity?uncap_first});
    }

    /**
     * 修改接口
     */
    @RequestMapping("/update")
    public void update${entity}(@RequestBody ${entity} ${entity?uncap_first}){
        ${entity?uncap_first}Service.updateById(${entity?uncap_first});
    }

    /**
     * 单个删除接口
     */
    @RequestMapping("/deleteOne")
    public void deleteOne(@RequestParam Integer id){
        ${entity?uncap_first}Service.removeById(id);
    }

    /**
     * 批量删除接口
     * 前台ajax参数ids传入示例：1,2,3  (英文逗号分隔的字符串)
     */
    @RequestMapping("/deleteBatch")
    public void deleteBatch(@RequestParam List<Integer> ids){
        ${entity?uncap_first}Service.removeByIds(ids);
    }

    /**
     * 根据id查询实体接口
     */
    @RequestMapping("/findById")
    public ${entity} findById(@RequestParam Integer id){
        return ${entity?uncap_first}Service.getById(id);
    }

    /**
     * 查询所有接口
     */
    @RequestMapping("/findAll")
    public List<${entity}> findAll(){
        return ${entity?uncap_first}Service.list();
    }

    /**
     * 分页查询接口
     * 重要提示：启用分页功能必须在配置类中添加mybatis-plus分页的拦截器
     * 重要提示：启用分页功能必须在配置类中添加mybatis-plus分页的拦截器
     * 重要提示：启用分页功能必须在配置类中添加mybatis-plus分页的拦截器
     */
    @RequestMapping("/findPage")
    public Page<${entity}> findPage(@RequestParam Integer page,@RequestParam Integer pageSize){
        Page<${entity}> ${entity?uncap_first}Page = new Page<>(page, pageSize);
        return ${entity?uncap_first}Service.page(${entity?uncap_first}Page);
    }

}
</#if>
