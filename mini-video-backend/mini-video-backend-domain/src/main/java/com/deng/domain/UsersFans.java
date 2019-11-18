package com.deng.domain;

import io.swagger.annotations.ApiModel;
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
@ApiModel(value="UsersFans对象", description="")
public class UsersFans implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String userId;

    private String fanId;


}
