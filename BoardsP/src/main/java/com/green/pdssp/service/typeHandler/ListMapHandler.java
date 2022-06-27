package com.green.pdssp.service.typeHandler;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;
//import org.springframework.jdbc.support.nativejdbc.CommonsDbcpNativeJdbcExtractor;

import oracle.jdbc.OracleConnection;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;

/*
 *  ListMapTypeHandler
 *  마이바티스가 파라미터를 CallableStatement 에 세팅하거나
 *  resultSet 으로 부터의 값을 추적할때 마다, 타입핸들러는 자바타입을 추적할 때 사용한다
 *  지원하지 않는 타입이나 표준타입이 아닌 것을 다루기 위한 방법
 *  타입핸들러         <->   자바타입           <->  JDBC 타입
 *  StringHandler      <->   java.lang.String   <->  CHAR, VARCHAR
 */

public class ListMapHandler implements TypeHandler<Object> {

   @Override
   public void setParameter(PreparedStatement ps, int i, Object parameter, JdbcType jdbcType) throws SQLException {
  
	   List<String>  list = (List<String>) parameter;
	      
	      // db연결
	      /* spring 4 + ojdbc6
	      CommonsDbcpNativeJdbcExtractor extractor
	        = new CommonsDbcpNativeJdbcExtractor();
	      Connection  conn = extractor.getNativeConnection(
	         ps.getConnection()   );
	      */
	      // spring 5 + ojdbc11
	       OracleConnection conn = ps.getConnection().unwrap(OracleConnection.class);   
	      
	      ArrayDescriptor  desc
	        = ArrayDescriptor.createDescriptor("FILE_ARRAY", conn);
	      
	      // 남어온 파일명이 하나도 없을때
	      //  i 번째 파라미터 배열 방안에 null 값을 지정
	      if(list == null || list.size() == 0) {
	         
	         ps.setArray( i,
	            new ARRAY(desc, conn, new String[] { null } ) );
	         
	         return;
	      }
	         
	      // 넘어온 파일목록 처리
	      // in_filename(1) = list.get(j)
	      String []   strings  =  new String[list.size()];
	      for (int j = 0; j < list.size(); j++) {
	         strings[j]  = list.get(j);
	      }
	      
	      parameter  = new ARRAY(desc, conn, strings);
	      
	      ps.setArray(i, ( oracle.sql.ARRAY )parameter );
   }

   @Override
   public Object getResult(ResultSet rs, String columnName) throws SQLException {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public Object getResult(ResultSet rs, int columnIndex) throws SQLException {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public Object getResult(CallableStatement cs, int columnIndex) throws SQLException {
      // 수정
      if(  cs.wasNull() )
         return null;
      else
         return cs.getString( columnIndex );
   }

}