package chat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ChatDao {

	DataSource ds;
	
	public ChatDao() {
		try {
			InitialContext initContext = new InitialContext();
			Context context = (Context)initContext.lookup("java:/comp/env");
			ds = (DataSource)context.lookup("jdbc/oraDB");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<ChatDTO> getChatListById(String fromId, String toId, String chatId ) {
		ArrayList<ChatDTO> list = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = "SELECT * FROM CHAT WHERE ((FROMID = ? AND TOID = ?) OR (FROMID = ? AND TOID = ?) AND CHATID > ? ORDER BY CHATTIME)";
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, fromId);
			pstmt.setString(2, toId);
			pstmt.setString(3, toId);
			pstmt.setString(4, fromId);
			pstmt.setInt(5, Integer.parseInt(chatId));
			rset = pstmt.executeQuery();
			
			list = new ArrayList<ChatDTO>();
			while(rset.next()) {
				ChatDTO c = new ChatDTO();
				c.setChatId(rset.getInt("CHATID"));
				c.setFromId(rset.getString("FORMID").replaceAll(" ", "&nbsp;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"));
				c.setToId(rset.getString("TOID").replaceAll(" ", "&nbsp;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"));
				c.setChatContent(rset.getString("CHATCONTENT").replaceAll(" ", "&nbsp;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"));
				int chatTime = Integer.parseInt(rset.getString("CHATTIME").substring(11,13));
				String timeType = "오전";
				if(chatTime > 12) {
					timeType = "오후";
					chatTime -= 12;
				}
				c.setChatTime(rset.getString("CHATTIME").substring(0,11) + " " + timeType + " " + chatTime + ":" + rset.getString("CHATTIME").substring(14,16) + "");
				list.add(c);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rset != null) rset.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return list;
	}
	
	public ArrayList<ChatDTO> getChatListByRecnet(String fromId, String toId, int number) {
		ArrayList<ChatDTO> list = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = "SELECT * FROM CHAT WHERE ((FROMID = ? AND TOID = ?) OR (FROMID = ? AND TOID = ?) AND CHATID > (SELECT MAX(CHATID) - ? FROM CHAT) ORDER BY CHATTIME)";
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, fromId);
			pstmt.setString(2, toId);
			pstmt.setString(3, toId);
			pstmt.setString(4, fromId);
			pstmt.setInt(5, number);
			rset = pstmt.executeQuery();
			
			list = new ArrayList<ChatDTO>();
			while(rset.next()) {
				ChatDTO c = new ChatDTO();
				c.setChatId(rset.getInt("CHATID"));
				c.setFromId(rset.getString("FORMID").replaceAll(" ", "&nbsp;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"));
				c.setToId(rset.getString("TOID").replaceAll(" ", "&nbsp;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"));
				c.setChatContent(rset.getString("CHATCONTENT").replaceAll(" ", "&nbsp;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"));
				int chatTime = Integer.parseInt(rset.getString("CHATTIME").substring(11,13));
				String timeType = "오전";
				if(chatTime > 12) {
					timeType = "오후";
					chatTime -= 12;
				}
				c.setChatTime(rset.getString("CHATTIME").substring(0,11) + " " + timeType + " " + chatTime + ":" + rset.getString("CHATTIME").substring(14,16) + "");
				list.add(c);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rset != null) rset.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return list;
	}
	
	public int submit(String fromId, String toId, String chatContent) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = "INSERT INTO CHAT VALUES(SEQ_CHAT.NEXTVAL,?,?,?,TO_DATE(SYSDATE,'YYYY-MM-DD HH24:MI:SS')";
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, fromId);
			pstmt.setString(2, toId);
			pstmt.setString(3, chatContent);
			return pstmt.executeUpdate();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rset != null) rset.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return -1; // db오류
	}
}
