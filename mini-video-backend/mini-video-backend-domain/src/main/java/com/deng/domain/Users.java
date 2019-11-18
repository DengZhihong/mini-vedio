package com.deng.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
@ApiModel(value="Users对象", description="")
@Builder
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String username;

    private String password;

    @ApiModelProperty(value = "用户头像")
    private String faceImage;

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "粉丝数量")
    private Integer fansCounts;

    @ApiModelProperty(value = "关注数")
    private Integer followCounts;

    @ApiModelProperty(value = "收到的喜欢数")
    private Integer receiveLikeCounts;


}
