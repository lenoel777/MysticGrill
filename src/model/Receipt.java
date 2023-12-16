package model;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.Connect;

public class Receipt {
	private int receiptId, receiptOrderId, receiptPaymentAmount;
	private Date receiptPaymentDate;
	private String receiptPaymentType;
	
	public Receipt(int receiptId, int receiptOrderId, int receiptPaymentAmount, Date receiptPaymentDate,
			String receiptPaymentType) {
		super();
		this.receiptId = receiptId;
		this.receiptOrderId = receiptOrderId;
		this.receiptPaymentAmount = receiptPaymentAmount;
		this.receiptPaymentDate = receiptPaymentDate;
		this.receiptPaymentType = receiptPaymentType;
	}
	
	public static ArrayList<Receipt> loadReceipts() {
		ArrayList<Receipt> Receipts = new ArrayList<>();
		String query = "SELECT * FROM Receipt";
		ResultSet rs = Connect.getConnection().executeQuery(query);
		try {
			while (rs.next()) {
				int id = rs.getInt(1);
				int orderId = rs.getInt(2);
				int paymentAmount = rs.getInt(3);
				Date paymentDate = rs.getDate(4);
				String paymentType = rs.getString(5);

				Receipts.add(new Receipt(id, orderId, paymentAmount, paymentDate, paymentType));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return Receipts;
	}

	public static void insertReceipt(int orderId, int paymentAmount, Date paymentDate, String paymentType) {
		String query = String.format(
				"INSERT INTO receipt (receiptOrderId, receiptPaymentAmount, receiptPaymentDate, receiptPaymentType) VALUES (%d, %d, '%s', '%s')", 
				orderId, paymentAmount, paymentDate, paymentType);
		Connect.getConnection().executeUpdate(query);
	}
	
	//kalo butuh delete
//	public static void deleteReceipt(int id) {
//		String query = "DELETE FROM receipt WHERE receiptId = ?";
//		PreparedStatement ps = Connect.getConnection().prepareStatement(query);
//
//		try {
//			ps.setInt(1, id);
//			ps.execute();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

	//kalo butuh update
//	public static void updateReceipt(int id, int paymentAmount, Date paymentDate, String paymentType) {
//		String query = "UPDATE receipt SET receiptPaymentAmount = ?, receiptPaymentDate = ?, receiptPaymentType = ? WHERE receiptId = ?";
//
//		PreparedStatement ps = Connect.getConnection().prepareStatement(query);
//
//		try {
//			ps.setInt(1, paymentAmount);
//			ps.setDate(2, paymentDate);
//			ps.setString(3, paymentType);
//			ps.setInt(4, id);
//			ps.execute();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

	public int getReceiptId() {
		return receiptId;
	}

	public void setReceiptId(int receiptId) {
		this.receiptId = receiptId;
	}

	public int getReceiptOrderId() {
		return receiptOrderId;
	}

	public void setReceiptOrderId(int receiptOrderId) {
		this.receiptOrderId = receiptOrderId;
	}

	public int getReceiptPaymentAmount() {
		return receiptPaymentAmount;
	}

	public void setReceiptPaymentAmount(int receiptPaymentAmount) {
		this.receiptPaymentAmount = receiptPaymentAmount;
	}

	public Date getReceiptPaymentDate() {
		return receiptPaymentDate;
	}

	public void setReceiptPaymentDate(Date receiptPaymentDate) {
		this.receiptPaymentDate = receiptPaymentDate;
	}

	public String getReceiptPaymentType() {
		return receiptPaymentType;
	}

	public void setReceiptPaymentType(String receiptPaymentType) {
		this.receiptPaymentType = receiptPaymentType;
	}
	
}
