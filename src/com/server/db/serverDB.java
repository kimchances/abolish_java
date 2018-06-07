package com.server.db;

import java.util.HashMap;
import java.util.LinkedList;

import com.db.SQLClient;

/**
 * 主要拿的数据地盘
 *
 * @author Kim
 */
public class serverDB {

    SQLClient sqlClient;

    public serverDB(SQLClient sqlClient) {
        this.sqlClient = sqlClient;
    }

    /**
     * 拿主页分类
     *
     * @return
     * @throws Exception
     */
    public LinkedList<HashMap<String, String>> findIndexCategory() throws Exception {
        StringBuffer sql = new StringBuffer();

        sql.append("SELECT ");
        sql.append("Idx_Category.Idx_Ctgr_Name AS cName, ");
        sql.append("Idx_Category.Idx_Ctgr_NameEn AS cNameEn, ");
        sql.append("Idx_Category.Idx_Ctgr_Content AS cContent, ");
        sql.append("Idx_Category.Idx_Ctgr_Url AS cUrl, ");
        sql.append("Idx_Category.Idx_Ctgr_Font AS cFont, ");
        sql.append("Idx_Category.Idx_Ctgr_JumpEnable AS jump ");
        sql.append("FROM ");
        sql.append("Idx_Category");

        return sqlClient.execQuery(sql.toString());
    }

    /**
     * 拿主页用户信息
     *
     * @return
     * @throws Exception
     */
    public LinkedList<HashMap<String, String>> findIndexUser() throws Exception {
        StringBuffer sql = new StringBuffer();

        sql.append("SELECT ");
        sql.append("Idx_Info.Idx_If_name AS uName, ");
        sql.append("Idx_Info.Idx_If_content AS uContent, ");
        sql.append("Idx_Info.Idx_Pj_Title AS pTitle, ");
        sql.append("Idx_Info.Idx_Pj_Content AS pContent, ");
        sql.append("Idx_Info.Idx_If_headImg AS uHeadImg, ");
        sql.append("Idx_Info.Idx_Wb_title AS wTitle, ");
        sql.append("Idx_Info.Idx_Wb_nav AS wNav ");
        sql.append("FROM ");
        sql.append("Idx_Info ");

        return sqlClient.execQuery(sql.toString());
    }

    /**
     * 拿主页项目
     *
     * @return
     * @throws Exception
     */
    public LinkedList<HashMap<String, String>> findIndexProjects() throws Exception {
        StringBuffer sql = new StringBuffer();

        sql.append("SELECT ");
        sql.append("Idx_Projects.id AS pId, ");
        sql.append("Idx_Projects.Idx_Pro_Name AS pName, ");
        sql.append("Idx_Projects.Idx_Pro_Pics AS pPics, ");
        sql.append("Idx_Projects.Idx_Pro_URL AS pUrl, ");
        sql.append("Idx_Projects.Idx_Pro_Content AS pContent, ");
        sql.append("Idx_Projects.Idx_Pro_JumpEnable AS jump ");
        sql.append("FROM ");
        sql.append("Idx_Projects ");

        return sqlClient.execQuery(sql.toString());
    }
    
	/**
	 * 拿项目详情
	 * @param projectId
	 * @return
	 * @throws Exception
	 */
	public LinkedList<HashMap<String, String>> findProjectDetail(String projectId) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("Idx_Pro_C_Html AS myAreaContent ");
		sql.append("FROM ");
		sql.append("Idx_Projects AS pj, ");
		sql.append("Idx_Projects_Detail AS pd ");
		sql.append("WHERE ");
		sql.append("pj.id = pd.Idx_Pro_C_id ");
		sql.append("AND  ");
		sql.append("pj.id =? ");
		
		sqlClient.addParameter(projectId);
		
		return sqlClient.execQuery(sql.toString());
	}
}
