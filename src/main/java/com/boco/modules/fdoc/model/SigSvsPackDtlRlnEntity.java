package com.boco.modules.fdoc.model;

import com.boco.common.persistence.DataEntity;

public class SigSvsPackDtlRlnEntity extends DataEntity<SigSvsPackDtlRlnEntity>{
    /**
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么).
	 */
	private static final long serialVersionUID = 5589453294057678629L;
	/**中间关系表编号 */
	private int id;
	/**签约服务包编号 */
	private int sigId;
	/**服务项目包编号 */
	private int detailsId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSigId() {
		return sigId;
	}
	public void setSigId(int sigId) {
		this.sigId = sigId;
	}
	public int getDetailsId() {
		return detailsId;
	}
	public void setDetailsId(int detailsId) {
		this.detailsId = detailsId;
	}


	
}
