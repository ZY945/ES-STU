package com.es.esspringstu.canal.empty;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@TableName("users")
public class User extends Model<User> {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("user_id")
    private String userId;

    @TableField("username")
    private String username;

    @TableField("email")
    private String email;

    @TableField("password")
    private String password;

    @TableField("first_name")
    private String firstName;

    @TableField("last_name")
    private String lastName;

    @TableField(value = "created_at", fill = FieldFill.INSERT)
    private Date createdAt;

    @TableField(value = "updated_at", fill = FieldFill.UPDATE)
    private Date updatedAt;
    /**
     * 逻辑删除
     */
    @TableLogic
    @TableField("is_deleted")
    private Integer isDeleted;
}