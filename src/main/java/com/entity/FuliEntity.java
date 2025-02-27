package com.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * 福利
 *
 * @author 
 * @email
 */
@TableName("fuli")
public class FuliEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public FuliEntity() {

	}

	public FuliEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    @TableField(value = "id")

    private Integer id;


    /**
     * 用户
     */
    @TableField(value = "yonghu_id")

    private Integer yonghuId;


    /**
     * 福利名称
     */
    @TableField(value = "fuli_name")

    private String fuliName;


    /**
     * 福利类型
     */
    @TableField(value = "fuli_types")

    private Integer fuliTypes;


    /**
     * 福利详情
     */
    @TableField(value = "fuli_content")

    private String fuliContent;


    /**
     * 发放时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @TableField(value = "fuli_time",fill = FieldFill.UPDATE)

    private Date fuliTime;


    /**
     * 添加时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @TableField(value = "insert_time",fill = FieldFill.INSERT)

    private Date insertTime;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @TableField(value = "create_time",fill = FieldFill.INSERT)

    private Date createTime;


    /**
	 * 设置：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 获取：主键
	 */

    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 设置：用户
	 */
    public Integer getYonghuId() {
        return yonghuId;
    }


    /**
	 * 获取：用户
	 */

    public void setYonghuId(Integer yonghuId) {
        this.yonghuId = yonghuId;
    }
    /**
	 * 设置：福利名称
	 */
    public String getFuliName() {
        return fuliName;
    }


    /**
	 * 获取：福利名称
	 */

    public void setFuliName(String fuliName) {
        this.fuliName = fuliName;
    }
    /**
	 * 设置：福利类型
	 */
    public Integer getFuliTypes() {
        return fuliTypes;
    }


    /**
	 * 获取：福利类型
	 */

    public void setFuliTypes(Integer fuliTypes) {
        this.fuliTypes = fuliTypes;
    }
    /**
	 * 设置：福利详情
	 */
    public String getFuliContent() {
        return fuliContent;
    }


    /**
	 * 获取：福利详情
	 */

    public void setFuliContent(String fuliContent) {
        this.fuliContent = fuliContent;
    }
    /**
	 * 设置：发放时间
	 */
    public Date getFuliTime() {
        return fuliTime;
    }


    /**
	 * 获取：发放时间
	 */

    public void setFuliTime(Date fuliTime) {
        this.fuliTime = fuliTime;
    }
    /**
	 * 设置：添加时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 获取：添加时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 设置：创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Fuli{" +
            "id=" + id +
            ", yonghuId=" + yonghuId +
            ", fuliName=" + fuliName +
            ", fuliTypes=" + fuliTypes +
            ", fuliContent=" + fuliContent +
            ", fuliTime=" + fuliTime +
            ", insertTime=" + insertTime +
            ", createTime=" + createTime +
        "}";
    }
}
