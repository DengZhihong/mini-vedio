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
@ApiModel(value="Videos对象", description="")
public class Videos implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    @ApiModelProperty(value = "视频发布作者")
    private String userId;

    @ApiModelProperty(value = "音频的id，配乐")
    private String audioId;

    private String videoDesc;

    private String videoPath;

    private Float videoSeconds;

    private Integer videoWidth;

    private Integer videoHeight;

    @ApiModelProperty(value = "视频封面")
    private String coverPath;

    @ApiModelProperty(value = "视频被喜欢数")
    private Long likeCounts;

    @ApiModelProperty(value = "视频状态:1,发布成功;2,禁止播放; 管理员操作")
    private Integer status;

    private LocalDateTime createTime;


}
