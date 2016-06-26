package cn.lyf.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.Action;
import cn.lyf.bean.baseFeed;
import cn.lyf.bean.baseFeedDetail;
import cn.lyf.bean.baseSpecies;
import cn.lyf.bean.userInfo;
import cn.lyf.service.baseFeedService;
import cn.lyf.service.baseSpeciesService;
import cn.lyf.service.feedDetailService;
import cn.lyf.service.userInfoService;

public class baseFeedAction {
	@Resource
	baseFeedService feedService;
	@Resource
	baseSpeciesService speciesService;
	@Resource
	feedDetailService feedDetail;
	@Resource
	userInfoService userService;
	private File image;// 上传的图片
	private String message;// 消息
	private List<baseFeed> basefeeds;// 所有feed
	private baseFeed basefeed;// 添加的feed
	private int fedid;// 传递的id
	private baseFeed editfeed;// 编辑的feed
	private Map<Integer, String> basespecies = new HashMap<Integer, String>();
	private Map<String, Object> jsonRoot = new HashMap<String, Object>();
	private Map<Integer, String> userids = new HashMap<Integer, String>();
	private int userid;// 记录选择的人员ID
	private String titlename;// title名称
	private String m_user;
	private String m_pwd;
	
    public String manage(){
    	JSONObject jo ;
    	if(m_user.equals("admin")&&m_pwd.equals("PASSword")){
    		userInfo admin_info=new userInfo();
    		admin_info.setUserName("admin");
    		admin_info.setUserPassword("PASSword");
    		ServletActionContext.getRequest().getSession()
			     .setMaxInactiveInterval(900);
	        ServletActionContext.getRequest().getSession()
			     .setAttribute("manage", admin_info);
	        Boolean existcookies=false;
	        Cookie[] reqcookies= ServletActionContext.getRequest().getCookies();
	        for (Cookie cookie : reqcookies) {
		        if(cookie.getName().toString().equals("feisaadmin")){
			        existcookies=true;
		      }
	       }
			if (!existcookies) {
				Cookie userCookie = new Cookie("feisaadmin", this.m_user+"^"+this.m_pwd);
				userCookie.setMaxAge(60 * 60 * 24);
				ServletActionContext.getResponse().addCookie(userCookie);
			}
    		jsonRoot.put("AminResult", "success");
			jo= JSONObject.fromObject(jsonRoot);
			this.message = jo.toString();
    	}else{
    		jsonRoot.put("AminResult", "fail");
		    jo = JSONObject.fromObject(jsonRoot);
    		this.message = jo.toString();
    	}
		return Action.SUCCESS;
    }
	/**
	 * feed列表显示
	 */
	public String list() {
		basefeed = null;
		image = null;
		// int fedid = -1;
		editfeed = null;
		basespecies = new HashMap<Integer, String>();
		List<baseSpecies> lis_species = this.speciesService.getbaseSpeciess();
		for (baseSpecies species : lis_species) {
			basespecies.put(species.getId(), species.getSpeciesName());
		}
		this.basefeeds = feedService.getbaseFeeds();
		try {

		} catch (Exception ex) {
			System.out.println("错误:" + ex.toString());
		}
		return "list";
	}

	/**
	 * feed添加界面
	 */
	public String addUI() {
		List<baseSpecies> lis_species = this.speciesService.getbaseSpeciess();
		for (baseSpecies species : lis_species) {
			basespecies.put(species.getId(), species.getSpeciesName());
		}
		this.basefeed = null;
		this.image = null;
		return "add";
	}

	/**
	 * feed添加
	 */
	public String add() {
		if(this.basefeed!=null&&this.basefeed.getFeedAddress()!=""){
			baseFeed tm_fed= this.feedService.getBaseFeedbyURL(this.basefeed.getFeedAddress());
			if(tm_fed==null){	
				if (this.getImage() != null) {
					try {
						SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmm");// 设置日期格式
						@SuppressWarnings("deprecation")
						String savepath = ServletActionContext.getRequest()
								.getRealPath("siteimages");
						System.out.println("保存路径：" + savepath);
						String temp = this.basefeed.getFeedAddress().replace('/', '.')
								.replace('?', '.');
						String flpath = "/" + this.basefeed.getFeedSpecies() + "."
								+ temp + "."
								+ (df.format(new java.util.Date()).toString()) + ".jpg";
						FileInputStream in = new FileInputStream(getImage());
						FileOutputStream out = new FileOutputStream(savepath + flpath);
						byte[] b = new byte[1024];
						while (in.read(b) != -1) {
							out.write(b);
							out.flush();
						}
						in.close();
						out.close();
						this.basefeed.setFeedPhoto("siteimages" + flpath);
					} catch (Exception ex) {
						System.out.println("错误:" + ex.toString());
					}
				}
				this.getBasefeed().setFeedStatus(false);
				this.feedService.save(this.basefeed);
				updatespecies(Integer.valueOf(this.basefeed.getFeedSpecies()));
				this.message = "添加成功";
			}else{
				this.message = "已存在该站点,id为:"+tm_fed.getId().toString();
			}
		}else
			this.message="信息不完整,请重新输入";
		return "message";
	}

	/**
	 * feed编辑界面
	 */
	public String editUI() {
		this.editfeed = null;
		this.image = null;
		for (baseFeed fed : this.getBasefeeds()) {
			if (fed.getId() == this.getFedid()) {
				this.editfeed = fed;
				break;
			}
		}
		this.setFedid(-1);
		return "edit";
	}

	/**
	 * 订阅feed编辑界面
	 * 
	 * @return
	 */
	public String cuseditUI() {
		return "";
	}

	/**
	 * feed编辑
	 */
	public String edit() {
		if (this.getEditfeed() != null) {
			System.out.println("类别:" + this.getEditfeed().getFeedSpecies());
			try {
				SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmm");// 设置日期格式
				String savepath = ServletActionContext.getServletContext()
						.getRealPath("siteimages");
				String temp = this.editfeed.getFeedAddress().replace('/', '.')
						.replace('?', '.');
				String flpath = "/" + this.editfeed.getFeedSpecies() + "."
						+ temp + "."
						+ (df.format(new java.util.Date()).toString()) + ".jpg";
				System.out.println(savepath + flpath);
				FileInputStream in = new FileInputStream(getImage());
				FileOutputStream out = new FileOutputStream(savepath + flpath);
				byte[] b = new byte[1024];
				while (in.read(b) != -1) {
					out.write(b);
					out.flush();
				}
				in.close();
				out.close();
				this.editfeed.setFeedPhoto("siteimages" + flpath);
			} catch (Exception ex) {
				System.out.println("出错:" + ex.toString());
			}
		}
		this.feedService.update(this.editfeed);
		updatespecies(Integer.valueOf(this.editfeed.getFeedSpecies()));
		this.message = "修改成功";
		return "message";
	}

	/**
	 * 发布feed
	 */
	public String releaseUI() {
		this.editfeed = null;
		for (baseFeed fed : this.getBasefeeds()) {
			if (fed.getId() == this.getFedid()) {
				this.editfeed = fed;
				break;
			}
		}
		if (this.getEditfeed() != null) {
			this.editfeed.setFeedStatus(true);
			this.feedService.update(this.editfeed);
		}
		this.setFedid(-1);
		this.message = "发布成功";
		return "message";
	}

	/**
	 * 发布订阅的feed
	 * 
	 * @return
	 */
	public String cusreleaseUI() {
		this.message = "发布成功";
		return "message";
	}

	/**
	 * 删除feed
	 */
	public String deleteUI() {
		if (this.getFedid() != -1) {
			this.feedService.delete(this.getFedid());
		}
		this.setFedid(-1);
		this.message = "删除成功";
		return "message";
	}
	
	public String trimHtml2Txt(String html, String[] filterTags) {
		html = html.replaceAll("\\<head>[\\s\\S]*?</head>(?i)", "");// 去掉head
		html = html.replaceAll("\\<!--[\\s\\S]*?-->", "");// 去掉注释
		html = html.replaceAll("\\<![\\s\\S]*?>", "");
		html = html.replaceAll("\\<style[^>]*>[\\s\\S]*?</style>(?i)", "");// 去掉样式
		html = html.replaceAll("\\<script[^>]*>[\\s\\S]*?</script>(?i)", "");// 去掉js
		html = html.replaceAll("\\<w:[^>]+>[\\s\\S]*?</w:[^>]+>(?i)", "");// 去掉word标签
		html = html.replaceAll("\\<xml>[\\s\\S]*?</xml>(?i)", "");
		html = html.replaceAll("\\<html[^>]*>|<body[^>]*>|</html>|</body>(?i)",
				"");
		html = html.replaceAll("\\\r\n|\n|\r", " ");// 去掉换行
		html = html.replaceAll("\\<br[^>]*>(?i)", "\n");
		List<String> tags = new ArrayList<String>();
		List<String> s_tags = new ArrayList<String>();
		List<String> halfTag = Arrays.asList(new String[] { "img", "table",
				"thead", "th", "tr", "td" });//
		if (filterTags != null && filterTags.length > 0) {
			for (String tag : filterTags) {
				tags.add("<" + tag + (halfTag.contains(tag) ? "" : ">"));// 开始标签
				if (!"img".equals(tag))
					tags.add("</" + tag + ">");// 结束标签
				s_tags.add("#REPLACETAG" + tag
						+ (halfTag.contains(tag) ? "" : "REPLACETAG#"));// 尽量替换为复杂一点的标记,以免与显示文本混合,如：文本中包含#td、#table等
				if (!"img".equals(tag))
					s_tags.add("#REPLACETAG/" + tag + "REPLACETAG#");
			}
		}
		html = StringUtils.replaceEach(html,
				tags.toArray(new String[tags.size()]),
				s_tags.toArray(new String[s_tags.size()]));
		html = html.replaceAll("\\</p>(?i)", "\n");
		html = html.replaceAll("\\<[^>]+>", "");
		html = StringUtils.replaceEach(html,
				s_tags.toArray(new String[s_tags.size()]),
				tags.toArray(new String[tags.size()]));
		html = html.replaceAll("\\ ", " ");
		if (html.trim().length() > 200)
			return html.trim().substring(0, 200);
		else
			return html.trim();
	}
	
	public void updatespecies(int speid) {
		baseSpecies species = speciesService.getBaseSpecies(speid);
		int cou = feedService.getbaseFeeds(String.valueOf(speid)).size();
		species.setCount(cou);
		speciesService.update(species);
	}

	public baseFeed getBasefeed() {
		return basefeed;
	}

	public void setBasefeed(baseFeed basefeed) {
		this.basefeed = basefeed;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<baseFeed> getBasefeeds() {
		return basefeeds;
	}

	public void setBasefeeds(List<baseFeed> basefeeds) {
		this.basefeeds = basefeeds;
	}

	public File getImage() {
		return image;
	}

	public void setImage(File image) {
		this.image = image;
	}

	public int getFedid() {
		return fedid;
	}

	public void setFedid(int fedid) {
		this.fedid = fedid;
	}

	public baseFeed getEditfeed() {
		return editfeed;
	}

	public void setEditfeed(baseFeed editfeed) {
		this.editfeed = editfeed;
	}

	public Map<Integer, String> getBasespecies() {
		return basespecies;
	}

	public void setBasespecies(Map<Integer, String> basespecies) {
		this.basespecies = basespecies;
	}

	public Map<Integer, String> getUserids() {
		return userids;
	}

	public void setUserids(Map<Integer, String> userids) {
		this.userids = userids;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getTitlename() {
		return titlename;
	}

	public void setTitlename(String titlename) {
		this.titlename = titlename;
	}

	public String getM_user() {
		return m_user;
	}
	public void setM_user(String m_user) {
		this.m_user = m_user;
	}
	public String getM_pwd() {
		return m_pwd;
	}
	public void setM_pwd(String m_pwd) {
		this.m_pwd = m_pwd;
	}
}
