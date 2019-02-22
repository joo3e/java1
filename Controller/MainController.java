package Controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.text.DecimalFormat;
import java.text.ParsePosition;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;

import Model.Product;

import Model.User;
import Model.UserInfo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class MainController implements Initializable {
@FXML TableView<User> table1;
@FXML TableView<UserInfo> stable1;
@FXML TableView<Product> table2;
@FXML Button chart;
@FXML Button edit;
@FXML Button add;
@FXML Button del;
@FXML Button edit1;
@FXML Button add1;
@FXML Button del1;
@FXML Button all;
@FXML DatePicker d1;
@FXML DatePicker d2;
@FXML Button search;

private ObservableList<User> obList1 = FXCollections.observableArrayList();
private ObservableList<Product> obList2 = FXCollections.observableArrayList();
private ObservableList<UserInfo> obList3 = FXCollections.observableArrayList();
private ArrayList<User> dbArrayList1 = new ArrayList<>();
private ArrayList<Product> dbArrayList2 = new ArrayList<>();
private ArrayList<UserInfo> dbArrayList4 = new ArrayList<>();
private ArrayList<String> dbArrayList3 = new ArrayList<>();
private ArrayList<String> ab = new ArrayList<>();
ObservableList<String> abc = FXCollections.observableArrayList();
public Stage primaryStage;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
		table1.setOnMouseClicked(e->setTab1sTableView1());
		setTab1TableView1();
		setTab1TableView2();
		edit.setOnAction(e->edit());
		del.setOnAction(e->del());
		search.setOnAction(e->search());
		all.setOnAction(e->setTab1TableView1());
		add.setOnAction(e->add());
		add1.setOnAction(e->add1());
		del1.setOnAction(e->del1());
		edit1.setOnAction(e->edit1());
		chart.setOnAction(e->BarChart());
	}
	
	private void edit1() {
	try {
	Product a=table2.getSelectionModel().getSelectedItem();
	if(a==null){callAlert("선택 : 수정할 인원을 선택하세요");return;}
	ab.removeAll(ab);
	abc.removeAll(abc);
	Stage editStage = new Stage(StageStyle.UTILITY);
	editStage.initModality(Modality.WINDOW_MODAL);
	editStage.setTitle("수정");
	FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/edit2.fxml"));
	Parent root = loader.load();
	Scene scene = new Scene(root);
	editStage.setScene(scene);
	editStage.show();
	ComboBox<String> combo1 = (ComboBox<String>) root.lookup("#combo1");
	TextField txt1 = (TextField) root.lookup("#text1");
	TextField txt2 = (TextField) root.lookup("#text2");
	TextField txt3 = (TextField) root.lookup("#text3");
	TextField txt4 = (TextField) root.lookup("#text4");
	Button reg = (Button) root.lookup("#reg1");

	ab = DATA.getuserid();
	for(String b: ab) {abc.add(b);	
	}
	combo1.setItems(abc);
	combo1.setValue(a.getUserID());
	txt1.setText(a.getProdName());
	txt2.setText(a.getGroupName());
	txt3.setText(a.getPrice());
	txt4.setText(a.getAmount());
	Button exit = (Button) root.lookup("#exit");
	exit.setOnAction(e->editStage.close());
	
	reg.setOnAction(e->{
		
	a.setUserID(combo1.getValue());
	a.setProdName(txt1.getText());
	a.setGroupName(txt2.getText());
	a.setPrice(txt3.getText());
	a.setAmount(txt4.getText());
	fifteennum(txt3);
	fifteennum(txt4);
	int b=table2.getSelectionModel().getSelectedIndex();
	int count = DATA.updatebuyData(a);
	if(count != 0) {
		obList2.set(b,	a);
		dbArrayList2.set(b, a);
		
		callAlert("수정완료 : 수정이 완료되었습니다.");
		editStage.close();
	}else {
		return;
	}
});} catch (IOException e) {}
}

	private void del1() {
	Product a=table2.getSelectionModel().getSelectedItem();
	if(a==null){callAlert("선택 : 삭제할 인원을 선택하세요");return;}
	int b=table2.getSelectionModel().getSelectedIndex();
	int count = DATA.deletebuyData(a.getNum());
	if(count != 0 ) {
		obList2.remove(b);
		dbArrayList2.remove(a);
		
	
		
		callAlert("삭제완료 : 삭제가 완료되었습니다.");
		
	}else {
		return;
		
	}
		
	}

	private void add1() {
		try {
			ab.removeAll(ab);
			abc.removeAll(abc);
			Stage editStage = new Stage(StageStyle.UTILITY);
			editStage.initModality(Modality.WINDOW_MODAL);
			editStage.setTitle("추가");
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/edit2.fxml"));
			Parent root = loader.load();
			Scene scene = new Scene(root);
			editStage.setScene(scene);
			editStage.show();
			Product a=table2.getSelectionModel().getSelectedItem();
			ComboBox<String> combo1 = (ComboBox<String>) root.lookup("#combo1");
			TextField txt1 = (TextField) root.lookup("#text1");
			TextField txt2 = (TextField) root.lookup("#text2");
			TextField txt3 = (TextField) root.lookup("#text3");
			TextField txt4 = (TextField) root.lookup("#text4");
			fifteennum(txt3);
			fifteennum(txt4);
		
			Button reg = (Button) root.lookup("#reg1");
			Button exit = (Button) root.lookup("#exit");
			exit.setOnAction(e->editStage.close());
			
			ab = DATA.getuserid();
			
			for(String b: ab) {abc.add(b);	
			}
			combo1.setItems(abc);
			
			reg.setOnAction(e->{
			Product product = new Product(null,combo1.getSelectionModel().getSelectedItem(), txt1.getText(), txt2.getText(), txt3.getText(), txt4.getText());
			dbArrayList2.add(product);
			int count = DATA.insertbuyTblData(product);
			if(count != 0) {
				callAlert("성공 : 입력 성공했어요");
				editStage.close();
				setTab1TableView2();
			}
			});} catch (IOException e) {}}
			
	private void search() {
		obList1.removeAll(obList1);
		dbArrayList1.removeAll(dbArrayList1);
		dbArrayList1=DATA.getselecteddate(d1.getValue().toString(),d2.getValue().toString());	
		for (User user : dbArrayList1) {
			obList1.add(user);
		}
	
	table1.setItems(obList1);
		
	}
	
	private void add() {
		try {
			Stage editStage = new Stage(StageStyle.UTILITY);
			editStage.initModality(Modality.WINDOW_MODAL);
			editStage.setTitle("추가");
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/edit.fxml"));
			Parent root = loader.load();
			Scene scene = new Scene(root);
			editStage.setScene(scene);
			editStage.show();
			User a=table1.getSelectionModel().getSelectedItem();
			TextField txt1 = (TextField) root.lookup("#txt1");
			TextField txt2 = (TextField) root.lookup("#txt2");
			TextField txt3 = (TextField) root.lookup("#txt3");
			TextField txt4 = (TextField) root.lookup("#txt4");
			TextField txt5 = (TextField) root.lookup("#txt5");
			TextField txt6 = (TextField) root.lookup("#txt6");
			TextField txt7 = (TextField) root.lookup("#txt7");
			DatePicker txt8 = (DatePicker) root.lookup("#txt8");
			Button check = (Button) root.lookup("#check");
			Button ereg = (Button) root.lookup("#ereg");
			fournum(txt3);
			threenum(txt7);
			threenum(txt5);
			eightnum(txt6);
			check.setOnAction(e->{
			dbArrayList3=DATA.getuserid();
			for(String c:dbArrayList3) {
			if(c.equals(txt1.getText())) {
			callAlert("중복 : 아이디 중복");return;
			}else if(txt1.getText().equals("")){callAlert("입력 : 아이디를 입력하세요");return;}
			}callAlert("가능 : 아이디 사용가능");
			});
			ereg.setOnAction(e->{
			User user= new User(txt1.getText(), txt2.getText(), txt3.getText(), txt4.getText(), txt5.getText(), txt6.getText(), txt7.getText(), txt8.getValue().toString());
			dbArrayList1.add(user);
			int count = DATA.insertUserData(user);
			if(count != 0) {
				callAlert("성공 : 입력 성공했어요");
				editStage.close();
				setTab1TableView1();
			}
			});} catch (IOException e) {}}
	
	private void del() {
		User a=table1.getSelectionModel().getSelectedItem();
		if(a==null){callAlert("선택 : 삭제할 인원을 선택하세요");return;}
		int b=table1.getSelectionModel().getSelectedIndex();
		int count = DATA.deleteuserData(a.getUserid());
		if(count != 0 ) {
			obList1.remove(b);
			dbArrayList1.remove(a);
			
		
			
			callAlert("삭제완료 : " + a.getName() + "님의 삭제가 완료되었습니다.");
			
		}else {
			return;
			
		}
		
		
	}

	private void edit() {
		try {
		
			User a=table1.getSelectionModel().getSelectedItem();
			if(a==null){callAlert("선택 : 수정할 인원을 선택하세요");return;}
			Stage editStage = new Stage(StageStyle.UTILITY);
			editStage.initModality(Modality.WINDOW_MODAL);
			editStage.setTitle("수정");
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/edit.fxml"));
			Parent root = loader.load();
			Scene scene = new Scene(root);
			editStage.setScene(scene);
			editStage.show();
			TextField txt1 = (TextField) root.lookup("#txt1");
			TextField txt2 = (TextField) root.lookup("#txt2");
			TextField txt3 = (TextField) root.lookup("#txt3");
			TextField txt4 = (TextField) root.lookup("#txt4");
			TextField txt5 = (TextField) root.lookup("#txt5");
			TextField txt6 = (TextField) root.lookup("#txt6");
			TextField txt7 = (TextField) root.lookup("#txt7");
			DatePicker txt8 = (DatePicker) root.lookup("#txt8");
			fournum(txt3);
			threenum(txt7);
			threenum(txt5);
			eightnum(txt6);
			txt1.setText(a.getUserid());
			txt2.setText(a.getName());
			txt3.setText(a.getBirthYear());
			txt4.setText(a.getAddr());
			txt5.setText(a.getMobile1());
			txt6.setText(a.getMobile2());
			txt7.setText(a.getHeight());
			txt8.setValue(LocalDate.parse(a.getMdate()));
			

			Button check = (Button) root.lookup("#check");
			Button ereg = (Button) root.lookup("#ereg");
			Button exit = (Button) root.lookup("#exit");
			
			txt1.setEditable(false);
			check.setDisable(true);
		
			
			ereg.setOnAction(e->{
			a.setUserid(txt1.getText());	
			a.setName(txt2.getText());
			a.setBirthYear(txt3.getText());
			a.setAddr(txt4.getText());
			a.setMobile1(txt5.getText());
			a.setMobile2(txt6.getText());
			a.setHeight(txt7.getText());
			a.setMdate(txt8.getValue().toString());
			int b=table1.getSelectionModel().getSelectedIndex();
			int count = DATA.updateUserData(a);
			if(count != 0) {
				obList1.set(b,	a);
				dbArrayList1.set(b, a);
				
				callAlert("수정완료 : " + a.getName() + "님의 수정이 완료되었습니다.");
				editStage.close();
				}else {
					return;
				}
			});} catch (IOException e) {}
	}
	
	private void setTab1sTableView1() {
		
		TableColumn name =stable1.getColumns().get(0);
		name.setCellValueFactory(new PropertyValueFactory<>("name"));
		name.setStyle("-fx-alignment: CENTER;");
		
		TableColumn mobile =stable1.getColumns().get(1);
		mobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));
		mobile.setStyle("-fx-alignment: CENTER;");
		
		TableColumn prodName=stable1.getColumns().get(2);
		prodName.setCellValueFactory(new PropertyValueFactory<>("prodName"));		
		prodName.setStyle("-fx-alignment: CENTER;");
		
		TableColumn amount=stable1.getColumns().get(3);
		amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
		amount.setStyle("-fx-alignment: CENTER;");
		
		TableColumn sum=stable1.getColumns().get(4);
		sum.setCellValueFactory(new PropertyValueFactory<>("sum"));
		sum.setStyle("-fx-alignment: CENTER;");

		User a=table1.getSelectionModel().getSelectedItem();
		
		
		obList3.removeAll(obList3);
		dbArrayList4.removeAll(dbArrayList4);
		dbArrayList4 = DATA.getstabledata(a.getUserid());
		for (UserInfo userinfo : dbArrayList4) {
			obList3.add(userinfo);
		}
		
		stable1.setItems(obList3);
		
	}
	
	private void setTab1TableView1() {
		TableColumn userid =table1.getColumns().get(0);
		userid.setCellValueFactory(new PropertyValueFactory<>("userid"));
		userid.setStyle("-fx-alignment: CENTER;");
		
		TableColumn name =table1.getColumns().get(1);
		name.setCellValueFactory(new PropertyValueFactory<>("name"));
		name.setStyle("-fx-alignment: CENTER;");
		
		TableColumn birthYear=table1.getColumns().get(2);
		birthYear.setCellValueFactory(new PropertyValueFactory<>("birthYear"));		
		birthYear.setStyle("-fx-alignment: CENTER;");
		
		TableColumn addr=table1.getColumns().get(3);
		addr.setCellValueFactory(new PropertyValueFactory<>("addr"));
		addr.setStyle("-fx-alignment: CENTER;");
		
		TableColumn mobile1=table1.getColumns().get(4);
		mobile1.setCellValueFactory(new PropertyValueFactory<>("mobile1"));
		mobile1.setStyle("-fx-alignment: CENTER;");
		
		TableColumn mobile2=table1.getColumns().get(5);
		mobile2.setCellValueFactory(new PropertyValueFactory<>("mobile2"));
		mobile2.setStyle("-fx-alignment: CENTER;");
		
		TableColumn height=table1.getColumns().get(6);
		height.setCellValueFactory(new PropertyValueFactory<>("height"));
		height.setStyle("-fx-alignment: CENTER;");
		
		TableColumn mdate=table1.getColumns().get(7);
		mdate.setCellValueFactory(new PropertyValueFactory<>("mdate"));
		mdate.setStyle("-fx-alignment: CENTER;");
		
		obList1.removeAll(obList1);
		dbArrayList1.removeAll(dbArrayList1);
		dbArrayList1 = DATA.getuserTotalData();
		for (User user : dbArrayList1) {
			obList1.add(user);
		}
		
		table1.setItems(obList1);
		

	}
	
	private void setTab1TableView2() {
		TableColumn num =table2.getColumns().get(0);
		num.setCellValueFactory(new PropertyValueFactory<>("num"));
		num.setStyle("-fx-alignment: CENTER;");
		
		TableColumn userID =table2.getColumns().get(1);
		userID.setCellValueFactory(new PropertyValueFactory<>("userID"));
		userID.setStyle("-fx-alignment: CENTER;");
		
		TableColumn prodName=table2.getColumns().get(2);
		prodName.setCellValueFactory(new PropertyValueFactory<>("prodName"));		
		prodName.setStyle("-fx-alignment: CENTER;");
		
		TableColumn groupName=table2.getColumns().get(3);
		groupName.setCellValueFactory(new PropertyValueFactory<>("groupName"));
		groupName.setStyle("-fx-alignment: CENTER;");
		
		TableColumn price=table2.getColumns().get(4);
		price.setCellValueFactory(new PropertyValueFactory<>("price"));
		price.setStyle("-fx-alignment: CENTER;");
		
		TableColumn amount=table2.getColumns().get(5);
		amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
		amount.setStyle("-fx-alignment: CENTER;");
		
		
		obList2.removeAll(obList2);
		dbArrayList2.removeAll(dbArrayList2);
		dbArrayList2 = DATA.getproductTotalData();
	

		for (Product product : dbArrayList2) {
			obList2.add(product);
		}
		table2.setItems(obList2);

	}
	
	private void BarChart() {
	try {
		Stage bcStage =new Stage(StageStyle.UTILITY);
		bcStage.initModality(Modality.WINDOW_MODAL);
		bcStage.initOwner(primaryStage);
		bcStage.setTitle("막대 그래프");
		
		FXMLLoader loader=new FXMLLoader(getClass().getResource("../View/barchart.fxml"));
		Parent root = loader.load();
		BarChart barchart=(BarChart)root.lookup("#barchart");
		
		ObservableList<Data<String, Integer>> List = FXCollections.observableArrayList();
		XYChart.Series<String, Integer> Series = new XYChart.Series<>();
		Series.setName("160");
		for(int i=0;i<4;i++) {
		List.add(new Data<>(String.valueOf(1950+i*10),  DATA.getselectedheight(i,0)));}
		
		Series.setData(List);
		barchart.getData().add(Series);
		
		ObservableList<Data<String, Integer>> List1 = FXCollections.observableArrayList();
		XYChart.Series<String, Integer> Series1 = new XYChart.Series<>();
		Series1.setName("170");
		for(int i=0;i<4;i++) {
			List1.add(new Data<>(String.valueOf(1950+i*10),  DATA.getselectedheight(i,1)));}
		
		Series1.setData(List1);
		barchart.getData().add(Series1);
		
		ObservableList<Data<String, Integer>> List2 = FXCollections.observableArrayList();
		XYChart.Series<String, Integer> Series2 = new XYChart.Series<>();
		Series2.setName("180");
		for(int i=0;i<4;i++) {
			List2.add(new Data<>(String.valueOf(1950+i*10),  DATA.getselectedheight(i,2)));}
		
		Series2.setData(List2);
		barchart.getData().add(Series2);
	
		
		
		

		Scene scene = new Scene(root);
		bcStage.setScene(scene);
		bcStage.show();
	} catch (Exception e) {
	callAlert("바차트창오류 : 바차트 창 오류가 발생되었습니다.");
	}

	}

	
	
	
	//
	public static void callAlert(String contentText) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("알림");
		alert.setHeaderText(contentText.substring(0, contentText.lastIndexOf(":")));
		alert.setContentText(contentText.substring(contentText.lastIndexOf(":") + 1));
		alert.showAndWait();
	}

	private void fournum(TextField textField) {
		// 숫자만 입력(정수만 입력받음), 실수입력받고싶으면new DecimalFormat("###.#");
		DecimalFormat format = new DecimalFormat("###");
		// 점수 입력시 길이 제한 이벤트 처리
		textField.setTextFormatter(new TextFormatter<>(event -> {
			// 입력받은 내용이 없으면 이벤트를 리턴함.
			if (event.getControlNewText().isEmpty()) {
				return event;
			}
			// 구문을 분석할 시작 위치를 지정함. 세자리까지 계속해서 점검하겠다.
			ParsePosition parsePosition = new ParsePosition(0);
			// 입력받은 내용과 분석위치를 지정한지점부터 format 내용과 일치한지 분석함.
			Object object = format.parse(event.getControlNewText(), parsePosition);
			// 리턴값이 null 이거나,입력한길이와 구문분석위치값이 적어버리면(다 분석하지못했음을 뜻함)거나,입력한길이가 4이면(3자리를 넘었음을
			// 뜻함.) 이면 null 리턴함.
			if (object == null || parsePosition.getIndex() < event.getControlNewText().length()
					|| event.getControlNewText().length() == 5) {
				return null;
			} else {
				return event; // 값을 돌려주겠다.
			}
		}));
	}
	
	private void threenum(TextField textField) {
		// 숫자만 입력(정수만 입력받음), 실수입력받고싶으면new DecimalFormat("###.#");
		DecimalFormat format = new DecimalFormat("###");
		// 점수 입력시 길이 제한 이벤트 처리
		textField.setTextFormatter(new TextFormatter<>(event -> {
			// 입력받은 내용이 없으면 이벤트를 리턴함.
			if (event.getControlNewText().isEmpty()) {
				return event;
			}
			// 구문을 분석할 시작 위치를 지정함. 세자리까지 계속해서 점검하겠다.
			ParsePosition parsePosition = new ParsePosition(0);
			// 입력받은 내용과 분석위치를 지정한지점부터 format 내용과 일치한지 분석함.
			Object object = format.parse(event.getControlNewText(), parsePosition);
			// 리턴값이 null 이거나,입력한길이와 구문분석위치값이 적어버리면(다 분석하지못했음을 뜻함)거나,입력한길이가 4이면(3자리를 넘었음을
			// 뜻함.) 이면 null 리턴함.
			if (object == null || parsePosition.getIndex() < event.getControlNewText().length()
					|| event.getControlNewText().length() == 4) {
				return null;
			} else {
				return event; // 값을 돌려주겠다.
			}
		}));
	}

	private void eightnum(TextField textField) {
		// 숫자만 입력(정수만 입력받음), 실수입력받고싶으면new DecimalFormat("###.#");
		DecimalFormat format = new DecimalFormat("###");
		// 점수 입력시 길이 제한 이벤트 처리
		textField.setTextFormatter(new TextFormatter<>(event -> {
			// 입력받은 내용이 없으면 이벤트를 리턴함.
			if (event.getControlNewText().isEmpty()) {
				return event;
			}
			// 구문을 분석할 시작 위치를 지정함. 세자리까지 계속해서 점검하겠다.
			ParsePosition parsePosition = new ParsePosition(0);
			// 입력받은 내용과 분석위치를 지정한지점부터 format 내용과 일치한지 분석함.
			Object object = format.parse(event.getControlNewText(), parsePosition);
			// 리턴값이 null 이거나,입력한길이와 구문분석위치값이 적어버리면(다 분석하지못했음을 뜻함)거나,입력한길이가 4이면(3자리를 넘었음을
			// 뜻함.) 이면 null 리턴함.
			if (object == null || parsePosition.getIndex() < event.getControlNewText().length()
					|| event.getControlNewText().length() == 9) {
				return null;
			} else {
				return event; // 값을 돌려주겠다.
			}
		}));
	}

	private void fifteennum(TextField textField) {
		// 숫자만 입력(정수만 입력받음), 실수입력받고싶으면new DecimalFormat("###.#");
		DecimalFormat format = new DecimalFormat("###");
		// 점수 입력시 길이 제한 이벤트 처리
		textField.setTextFormatter(new TextFormatter<>(event -> {
			// 입력받은 내용이 없으면 이벤트를 리턴함.
			if (event.getControlNewText().isEmpty()) {
				return event;
			}
			// 구문을 분석할 시작 위치를 지정함. 세자리까지 계속해서 점검하겠다.
			ParsePosition parsePosition = new ParsePosition(0);
			// 입력받은 내용과 분석위치를 지정한지점부터 format 내용과 일치한지 분석함.
			Object object = format.parse(event.getControlNewText(), parsePosition);
			// 리턴값이 null 이거나,입력한길이와 구문분석위치값이 적어버리면(다 분석하지못했음을 뜻함)거나,입력한길이가 4이면(3자리를 넘었음을
			// 뜻함.) 이면 null 리턴함.
			if (object == null || parsePosition.getIndex() < event.getControlNewText().length()
					|| event.getControlNewText().length() == 16) {
				return null;
			} else {
				return event; // 값을 돌려주겠다.
			}
		}));
	}
}

