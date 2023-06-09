package com.hiddenlayer.dalabel.manageLabeling;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import com.hiddenlayer.dalabel.dolabeling.LabelDoList;
import com.hiddenlayer.dalabel.manageBundle.ManageSelector;

public interface ManageLabelingMapper {
	public abstract int regLabelingProject(LabelingProject lp);

	public abstract ArrayList<LabelingProject> getMyLabelingProject(ManageSelector ms);

	public abstract LabelingProject getMyDeatilProject(int project_no);
	
	public abstract int getAllMyProjectCount(String user_id);
	
	public abstract int updateProjectAccessLevel(LabelingProject lp);
	
	public abstract int changeUserAccess(LabelDoList lp);
	
	public abstract ArrayList<LabelDoList> selectLabelingUser(int no);
	
	public abstract BigDecimal getFileCount(LabelingProject lp);
	
	public abstract ArrayList<LabelingResult> getResults(@Param("project_no")int project_no, @Param("userid")String userid);
}
