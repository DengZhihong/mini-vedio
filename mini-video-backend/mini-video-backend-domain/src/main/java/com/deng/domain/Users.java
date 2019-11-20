package com.deng.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author deng.z.h
 * @since 2019-11-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="用户对象", description="用户的实体类")
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(hidden = true)
    private String id;

    @ApiModelProperty(value = "用户名",name = "username",example = "dengzhihong",required = true)
    @NotBlank(message = "用户名不能为空")
    private String username;

    @ApiModelProperty(value = "用户密码",name = "password",example = "123456",required = true)
    @NotBlank(message = "密码不能为空")
    private String password;

    @ApiModelProperty(hidden = true)
    private String faceImage;

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(hidden = true)
    private Integer fansCounts;

    @ApiModelProperty(hidden = true)
    private Integer followCounts;

    @ApiModelProperty(hidden = true)
    private Integer receiveLikeCounts;

}
