package com.callor.diet.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.callor.diet.config.DBContract;
import com.callor.diet.config.DBInfo;
import com.callor.diet.model.MyFoodCDTO;
import com.callor.diet.model.MyFoodVO;
import com.callor.diet.service.MyFoodService;

public class MyFoodServiceImplV1 implements MyFoodService{

	protected Connection dbConn;
	public MyFoodServiceImplV1() {
		dbConn = DBContract.getDBConnection();
	}
	
	public List<MyFoodCDTO> select(PreparedStatement pStr) throws SQLException {
		List<MyFoodCDTO> mfList = new ArrayList<MyFoodCDTO>();
		
		ResultSet rSet = pStr.executeQuery();
		while(rSet.next()) {
			
			MyFoodCDTO dto = new MyFoodCDTO();
			dto.setMf_seq(rSet.getLong(DBInfo.섭취정보계산.mf_seq));
			dto.setMf_date(rSet.getString(DBInfo.섭취정보계산.mf_date));
			dto.setMf_code(rSet.getString(DBInfo.섭취정보계산.mf_code));
			dto.setMf_name(rSet.getString(DBInfo.섭취정보계산.mf_name));
			dto.setMf_amt(rSet.getFloat(DBInfo.섭취정보계산.mf_amt));
			dto.setMf_ones(rSet.getFloat(DBInfo.섭취정보계산.mf_ones));
			dto.setMf_capa(rSet.getFloat(DBInfo.섭취정보계산.mf_capa));
			dto.setMf_cal(rSet.getFloat(DBInfo.섭취정보계산.mf_cal));
			dto.setMf_protein(rSet.getFloat(DBInfo.섭취정보계산.mf_protein));
			dto.setMf_fat(rSet.getFloat(DBInfo.섭취정보계산.mf_fat));
			dto.setMf_calbo(rSet.getFloat(DBInfo.섭취정보계산.mf_calbo));
			dto.setMf_sugar(rSet.getFloat(DBInfo.섭취정보계산.mf_sugar));
			
			mfList.add(dto);
		}
		
		return mfList;
	}
	
	@Override
	public List<MyFoodCDTO> selectAll() {
		// TODO 전체 식품섭취 리스트
		
		String sql = "SELECT * FROM view_섭취량계산 ";
		PreparedStatement pStr = null;
		try {
			pStr = dbConn.prepareStatement(sql);
			List<MyFoodCDTO> myList = this.select(pStr);
			pStr.close();
			return myList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	}

	@Override
	public MyFoodCDTO findById(Long seq) {
		// TODO Id로 찾기
		
		String sql = "SELECT * FROM view_섭취량계산 ";
		sql += " WHERE 일련번호 = ? ";
		
		PreparedStatement pStr = null;
		
		try {
			
			pStr = dbConn.prepareStatement(sql);
			pStr.setLong(1, seq);
			
			List<MyFoodCDTO> myList = this.select(pStr);
			
			pStr.close();
			if(myList == null && myList.size() > 0) {
				return myList.get(0);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public List<MyFoodCDTO> findByName(String mf_name) {
		// TODO 식품명으로 찾기
		return null;
	}

	@Override
	public List<MyFoodCDTO> findByDate(String mf_date) {
		// TODO 날짜로 찾기
		
		String sql = "SELECT * FROM view_섭취량계산 ";
		sql += "WHERE 섭취일자 = ? ";
		
		PreparedStatement pStr = null;
		try {
			pStr = dbConn.prepareStatement(sql);
			pStr.setString(1, mf_date);
			List<MyFoodCDTO> myList = this.select(pStr);
			pStr.close();
			return myList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public Integer insert(MyFoodVO myFoodVO) {
		// TODO DB에 입력
		String sql = " INSERT INTO tbl_myfoods( ";
		sql += "mf_seq,";
		sql += "mf_fcode,";
		sql += "mf_date,";
		sql += "mf_amt) ";
		sql += " VALUES( ";
		sql += "seq_myfoods.NEXTVAL,";
		sql += "?,";
		sql += "?,";
		sql += "?) ";
		
		PreparedStatement pStr = null;
		try {
			pStr = dbConn.prepareStatement(sql);
			pStr.setString(1, myFoodVO.getMf_fcode());
			pStr.setString(2, myFoodVO.getMf_date());
			pStr.setFloat(3, myFoodVO.getMf_amt());
			
			Integer result = pStr.executeUpdate();
			pStr.close();
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Integer update(MyFoodVO myFoodVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer delete(Long seq) {
		// TODO Auto-generated method stub
		return null;
	}

}
