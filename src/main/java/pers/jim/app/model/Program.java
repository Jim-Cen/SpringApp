package pers.jim.app.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description="程序列表")
public class Program {
	@ApiModelProperty("程序代号")
	private int programId;
	@ApiModelProperty("程序名")
	private String name;
	@ApiModelProperty("程序图标")
	private String icon;
	@ApiModelProperty("程序地址")
	private String href;
	@ApiModelProperty("上层程序代号")
	private int parentId;
	@ApiModelProperty("程序层")
	private int level;
	public int getProgramId() {
		return programId;
	}
	public void setProgramId(int programId) {
		this.programId = programId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	
}
