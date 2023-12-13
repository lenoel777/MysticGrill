package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.Connect;

public class MenuItem {
	private int menuItemId, menuItemPrice;
	private String menuItemName, menuItemDescription;
	
	public MenuItem(int menuItemId, String menuItemName, String menuItemDescription, int menuItemPrice) {
		super();
		this.menuItemId = menuItemId;
		this.menuItemName = menuItemName;
		this.menuItemDescription = menuItemDescription;
		this.menuItemPrice = menuItemPrice;
	}
	
	public static ArrayList<MenuItem> loadMenuItems() {
		ArrayList<MenuItem> menuItems = new ArrayList<>();
		String query = "SELECT * FROM menuitem";
		ResultSet rs = Connect.getConnection().executeQuery(query);
		try {
			while (rs.next()) {
				int id = rs.getInt(1);
				String itemName = rs.getString(2);
				String itemDescription = rs.getString(3);
				int itemPrice = rs.getInt(4);

				menuItems.add(new MenuItem(id, itemName, itemDescription, itemPrice));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return menuItems;
	}

	public static void insertMenuItem(String menuItemName, String menuItemDescriptionint, int menuItemPrice) {
		String query = String.format(
				"INSERT INTO menuitem (menuItemName, menuItemDescriptionint, menuItemPrice) VALUES ('%s', '%s', %d)", 
				menuItemName, menuItemDescriptionint, menuItemPrice);
		Connect.getConnection().executeUpdate(query);
	}

//	public static void deleteMenuItem(int id) {
//		String query = "DELETE FROM menuitem WHERE menuItemId = ?";
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

//	public static void updateMenuItem(int id, String menuItemName, String menuItemDescription, int menuItemPrice) {
//		String query = "UPDATE menuitem SET menuItemName = ?, menuItemDescription = ?, menuItemPrice = ? WHERE menuItemId = ?";
//
//		PreparedStatement ps = Connect.getConnection().prepareStatement(query);
//
//		try {
//			ps.setString(1, menuItemName);
//			ps.setString(2, menuItemDescription);
//			ps.setInt(3, menuItemPrice);
//			ps.setInt(4, id);
//			ps.execute();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

	public int getMenuItemId() {
		return menuItemId;
	}

	public void setMenuItemId(int menuItemId) {
		this.menuItemId = menuItemId;
	}

	public int getMenuItemPrice() {
		return menuItemPrice;
	}

	public void setMenuItemPrice(int menuItemPrice) {
		this.menuItemPrice = menuItemPrice;
	}

	public String getMenuItemName() {
		return menuItemName;
	}

	public void setMenuItemName(String menuItemName) {
		this.menuItemName = menuItemName;
	}

	public String getMenuItemDescription() {
		return menuItemDescription;
	}

	public void setMenuItemDescription(String menuItemDescription) {
		this.menuItemDescription = menuItemDescription;
	}
	
}
