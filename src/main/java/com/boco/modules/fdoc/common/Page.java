package com.boco.modules.fdoc.common;

/**
 * 分页
 * @author Administrator
 *
 */
public class Page {

	//总页数
	private int pageCount = 0;
	//每一页可显示的条数
	private int pageSize = 10;
	//数据的总数
	private long rowCount = 0L;
	//上一页
	private int prev = 1;
	//下一页
	private int next;
	//当前页
	private int curr = 1;
	//第一页
	private int first = 1;
	//最后一页
	private int last;
	//游标的宽度
	private int navSize = 11;
	//游标的起始位置
	private int start = 1;
	
	private Object data;
	
	//游标的结束位置
	private int end;
	
	public Page() {
		
	}


	public Page(long rowCount,int curr) {
		this.init(rowCount, curr);
	
	}
	
	
	private void init(long rowCount,int curr){
		//初始化rowCount
		this.rowCount = rowCount<0?0:rowCount;
		//计算总页数
		this.pageCount = (int)Math.ceil(this.rowCount*1.0 / this.pageSize);
		//处理当前页
		this.curr = Math.min(curr, this.pageCount);
		this.curr = Math.max(1, this.curr);
		
		
		
		//第一页
		this.first = 1;
		//最后一页
		this.last = this.pageCount;
		//上一页
		this.prev = this.curr>1?(this.curr-1):1;
		//下一页
		this.next = this.curr<this.pageCount?(this.curr+1):this.pageCount;
		
		//判断navSize和PageCount的关系
		if(this.pageCount > this.navSize){
			//处理位置在  开始的时候
			if(this.curr <=(this.navSize/2+1)){
				this.start = 1;
				this.end = this.navSize;
//					}else if((this.pageCount - this.curr)<=(this.navSize/2-1)){	//处理位置在结束的时候
			}else if((this.pageCount - this.curr)<=(Math.ceil(this.navSize*1.0/2)-1)){	//处理位置在结束的时候
				//this.start = this.curr-5;
				this.start = this.pageCount - (this.navSize-1);
				this.end = this.pageCount;
			}else{	//处理位置在中间的时候
				this.start = this.curr-(this.navSize/2);
				this.end = this.start+this.navSize-1;
			}
		}else{	//处理pageCount很小的时候
			this.start = 1;
			this.end = this.pageCount;
		}
	}
	
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public long getRowCount() {
		return rowCount;
	}
	public void setRowCount(long rowCount) {
		this.rowCount = rowCount;
		this.init(rowCount, this.curr);
	}
	public int getPrev() {
		return prev;
	}
	public void setPrev(int prev) {
		this.prev = prev;
	}
	public int getNext() {
		return next;
	}
	public void setNext(int next) {
		this.next = next;
	}
	public int getCurr() {
		return curr;
	}
	public void setCurr(int curr) {
		this.curr = curr;
	}
	public int getFirst() {
		return first;
	}
	public void setFirst(int first) {
		this.first = first;
	}

	public int getLast() {
		return last;
	}
	public void setLast(int last) {
		this.last = last;
	}
	public int getNavSize() {
		return navSize;
	}
	public void setNavSize(int navSize) {
		this.navSize = navSize;
	}
	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}
	
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public void setEnd(int end) {
		this.end = end;
	}
	public static void main(String[] args) {
		//System.out.println(Math.ceil(11L*1.0/10));
		
		Page page = new Page(760L, 71);
		
		System.out.println("总条数："+page.getRowCount());
		System.out.println("总页数："+page.getPageCount());
		System.out.println("首页："+page.getFirst());
		System.out.println("上一页："+page.getPrev());
		System.out.println("Start："+page.getStart());
		System.out.println("当前页："+page.getCurr());
		System.out.println("End："+page.getEnd());
		System.out.println("下一页："+page.getNext());
		System.out.println("尾页："+page.getLast());
		
		System.out.println(11/2-1);
		System.out.println(76-70);
	}
	
	
	
}
