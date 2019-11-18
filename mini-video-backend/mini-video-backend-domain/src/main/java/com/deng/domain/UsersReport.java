package com.deng.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

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
@ApiModel(value="UsersReport对象", description="")
public class UsersReport implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    @ApiModelProperty(value = "被举报人id")
    private String dealUserId;

    @ApiModelProperty(value = "被举报视频id")
    private String dealVideoId;

    @ApiModelProperty(value = "举报标题")
    private String title;

    private String content;

    @ApiModelProperty(value = "举报人id")
    private String userId;

    private LocalDateTime createTime;


}
