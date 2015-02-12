/**
 * www.househood.net Inc.
 * Copyright (c) 2011 All Rights Reserved.
 */
package com.lencee.demo.beanvalidate;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

/**
 *                       
 * @Filename MemberCheckEstates.java
 *
 * @Description 
 *
 * @Version 1.0
 *
 * @Author lp
 *
 * @Email lp@cqnt.com.cn
 *       
 * @History
 *<li>Author: lp</li>
 *<li>Date: 2014年10月11日</li>
 *<li>Version: 1.0</li>
 *<li>Content: create</li>
 *
 */
public class MemberCheckEstatesFrom {
	
	@NotNull(message="物业主键不能为空")
	private Long memberCommunityEstatesId;
	@NotBlank(message="物业名称不能为空")
	private String ownerName;
	
	
	
	public Long getMemberCommunityEstatesId() {
		return memberCommunityEstatesId;
	}
	public void setMemberCommunityEstatesId(Long memberCommunityEstatesId) {
		this.memberCommunityEstatesId = memberCommunityEstatesId;
	}
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	
//	@Override
//	public String toString() {
//		return ToStringBuilder.reflectionToString(this,
//				ToStringStyle.SHORT_PREFIX_STYLE);
//	}

}
