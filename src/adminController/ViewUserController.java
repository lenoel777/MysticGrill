package adminController;

import adminView.ViewUser;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.User;

public class ViewUserController {
	private ViewUser viewUser;
	private ObservableList<User> users = FXCollections.observableArrayList();
	private boolean isEmpty;
	private int selectedId;
	public ViewUserController(ViewUser view) {
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
		
		User.updateUserRole(role, id);
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
