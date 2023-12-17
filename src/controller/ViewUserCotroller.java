package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.ViewUser;
import model.User;

public class ViewUserCotroller {
	private ViewUser viewUser;
	private ObservableList<User> users = FXCollections.observableArrayList();
	private boolean isEmpty;
	private int selectedId;
	public ViewUserCotroller(ViewUser view) {
		// TODO Auto-generated constructor stub
		this.viewUser = view;

		loadTableData();
		setupTableSelectionListener();
		setButtonListener();
	}
	private void setupTableSelectionListener() {
		isEmpty = true;
		viewUser.getTable().getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
			if (newSelection != null) {
				viewUser.getUserId().setText(String.valueOf(newSelection.getUserId()));
				viewUser.getUserRole().setText(String.valueOf(newSelection.getUserRole()));
				viewUser.getUserName().setText(String.valueOf(newSelection.getUserName()));
				viewUser.getUserEmail().setText(String.valueOf(newSelection.getUserEmail()));
				viewUser.getUserPassword().setText(String.valueOf(newSelection.getUserPassword()));
				
				selectedId = newSelection.getUserId();
				isEmpty = false;
			}
		});
	}
	private void setButtonListener(){
		viewUser.getUpdatebtn().setOnAction(e -> handleUpdate());
	}
	void handleUpdate() {
		int id = Integer.valueOf(viewUser.getUserId().getText());
		String role = viewUser.getUserRole().getText();
		
		loadTableData();
	}
	private void loadTableData(){
		users.clear();
		users.addAll(User.loadUsers());
		if (users != null && !users.isEmpty()) {
		    viewUser.getTable().setItems(users);
		} else {
		    System.out.println("User list is null or empty");
		}
	}
}
