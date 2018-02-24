package com.ehangtian.core.web;

import com.ehangtian.core.Entity.User;
import com.ehangtian.core.util.JsonResult;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Created by wangshuai on 2018/2/11.
 */
@RestController
public class UserController {
    static Map<Long,User> users= Collections.synchronizedMap(new HashMap<Long, User>());
    static {
        users.put(111L,new User("张三",23));
    }


    /**
     * 根据Id查询用户
     * @param id
     * @return
     */
    @ApiOperation(value="获取用户详细信息",notes = "根据URL中的ID来获取用户详细信息")
    @ApiImplicitParam(name="id", value="用户id",required = true,dataType = "Long",paramType = "path")
    @RequestMapping(value = "user/{id}", method = RequestMethod.GET)
    public ResponseEntity<JsonResult> getUserById(@PathVariable(value = "id") Long id){
        JsonResult r=new JsonResult();
        try {
            User user=users.get(id);
            r.setResult(user);
            r.setStatus("ok");
        }catch (Exception e){
            r.setResult(e.getClass().getName()+":"+e.getMessage());
            r.setStatus("error");
            e.printStackTrace();
        }
        return ResponseEntity.ok(r);
    }

    /**
     * 获取全部用户列表
     * @return
     */
    @ApiOperation(value="获取用户列表",notes = "获取全部用户列表")
    @RequestMapping(value="users",method = RequestMethod.GET)
    public ResponseEntity<JsonResult> getUserList(){
        JsonResult r=new JsonResult();
        try {
             List<User> userList=new ArrayList<>(users.values());
             r.setResult(userList);
             r.setStatus("ok");
        }catch (Exception e){
            r.setResult(e.getClass().getName()+":"+e.getMessage());
            r.setStatus("error");
            e.printStackTrace();
        }
        return ResponseEntity.ok(r);
    }


    @ApiOperation(value="更新方法",notes = "根据URL的id来更新用户信息")
    @ApiImplicitParams({@ApiImplicitParam(name = "id",value = "用户ID",required = true,dataType = "integer",paramType = "path"),
            @ApiImplicitParam(name="user",value="用户详细实体user",required = true,dataType = "User")
    })
    @RequestMapping(value = "user/{id}",method = RequestMethod.PUT)
    public ResponseEntity<JsonResult> update(@PathVariable("id") Integer id,@RequestBody User user){
        JsonResult r=new JsonResult();

        try {
            User u=users.get(id);
            u.setName(user.getName());
            u.setAge(user.getAge());
            r.setResult(u);
            r.setStatus("ok");
        }catch (Exception e){
            r.setResult(e.getClass().getName()+":"+e.getMessage());
            r.setStatus("error");
            e.printStackTrace();
        }
        return ResponseEntity.ok(r);

    }

}
