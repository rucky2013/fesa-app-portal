package cn.lyf.service;

import java.util.List;
import org.springframework.stereotype.Service;
import cn.lyf.bean.recommendDetail;

@Service
public interface recommendDetailService {

	public void Save(recommendDetail recommend);
	
	public void update(recommendDetail recommend);
	
	public void delete(Integer id);
	
	public List<recommendDetail> getreDetails(Integer speid);
}
