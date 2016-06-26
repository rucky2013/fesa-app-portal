package cn.feisa.action;

import java.io.File;
import java.util.*;

public class ReaderAction {
	
	List<String> listspe = new ArrayList<String>();
	private Map<String, Object> jsonRoot = new HashMap<String, Object>();
	private int fedid;// 要读取的feedid
	private String feedaddress;
	private int pageindex;// 记录翻页的id
	private File opmlfile;// 上传的图片
	private String message;
	private String fileName = "opml.xml";

	public String getFileDown() {
		return "success";
	}
	
	public List<String> getListspe() {
		return listspe;
	}

	public void setListspe(List<String> listspe) {
		this.listspe = listspe;
	}

	public String getMessage() {
		return message;
	}

	public int getFedid() {
		return fedid;
	}

	public void setFedid(int fedid) {
		this.fedid = fedid;
	}

	public String getFeedaddress() {
		return feedaddress;
	}

	public void setFeedaddress(String feedaddress) {
		this.feedaddress = feedaddress;
	}

	public int getPageindex() {
		return pageindex;
	}

	public void setPageindex(int pageindex) {
		this.pageindex = pageindex;
	}

	public File getOpmlfile() {
		return opmlfile;
	}

	public void setOpmlfile(File opmlfile) {
		this.opmlfile = opmlfile;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
}
