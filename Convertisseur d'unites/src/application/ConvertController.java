package application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/*l'objectif de cette application est de convertir les unités de trois formes de mesure 
différentes: volume, distance et poid. Le code acceptera les nombres et se convertira dans l'unité désirée choisie.
*/
public class ConvertController implements Initializable
	{

		@FXML
		private TextField txtpoid1;

		@FXML
		private TextField txtvol1;

		@FXML
		private TextField txtvol2;

		@FXML
		private ComboBox<String> cbopoid2;

		@FXML
		private ComboBox<String> cbopoid1;

		@FXML
		private TextField txtdis1;

		@FXML
		private ComboBox<String> cbodis2;

		@FXML
		private TextField txtpoid2;
		

		@FXML
		private ComboBox<String> cbodis1;

		@FXML
		private ComboBox<String> cbovol2;

		@FXML
		private ComboBox<String> cbovol1;

		@FXML
		private TextField txtdis2;

		@FXML
		private void fermer()
			{
				System.exit(0);
			}

		/*
		 * cette partie du code est l'endroit où les noms des unités sont rassemblés
		 * dans la valeur d'une liste. Cela rendra plus facile de s'y référer
		 * plus tard dans le code.
		 */
		private ObservableList<String> listeV =(ObservableList<String>)FXCollections.observableArrayList("Littre","Gallon","Tasse");
		private ObservableList<String> listeP =(ObservableList<String>)FXCollections.observableArrayList("Grammes","Onces");
		private ObservableList<String> listeD =(ObservableList<String>)FXCollections.observableArrayList("Metres","Pieds");

		//Voci les valeurs de chacune des unités par rapport à une unité de base:
		
		//unite de base pour les volumes est une litre
		double []volume= {1.0,0.22,3.52};
		//unite de base pour la poids est grammes
		double []poids= {1.0,0.035};
		//unite de base pour la distance est une metres
		double []distance= {1.0,3.28};
		
//c'est ici que le code met les noms des unités dans les Combo Boxes
		@Override
		public void initialize(URL location, ResourceBundle resources)
			{
				cbovol1.setItems(listeV);
				cbovol2.setItems(listeV);
				cbovol1.getSelectionModel().selectFirst();
				cbovol2.getSelectionModel().selectFirst();

				cbopoid1.setItems(listeP);
				cbopoid2.setItems(listeP);
				cbopoid1.getSelectionModel().selectFirst();
				cbopoid2.getSelectionModel().selectFirst();

				
				cbodis1.setItems(listeD);
				cbodis2.setItems(listeD);
				cbodis1.getSelectionModel().selectFirst();
				cbodis2.getSelectionModel().selectFirst();	
		}
		

		
		//cette partie du code convertit le code dans l'autre unité
		@FXML
		void calculer1()
		{
				Convertir(txtvol1, txtvol2, cbovol1, cbovol2);
		    		
		}
		
		@FXML
		void calculerV1()
		{
				Convertir(txtvol2, txtvol1, cbovol2, cbovol1);
		}
		
		@FXML
		void calculer2()
		{
				ConvertirP(txtpoid1, txtpoid2, cbopoid1, cbopoid2);
		}
		@FXML
		void calculerP2()
		{
			ConvertirP(txtpoid2, txtpoid1, cbopoid2, cbopoid1);
		    		
		}
		
		@FXML
		void calculer3()
		{
				ConvertirD(txtdis1, txtdis2, cbodis1, cbodis2);
		    	
		}
		@FXML
		void calculerD3()
		{
			
				ConvertirD(txtdis2, txtdis1, cbodis2, cbodis1);
		}
		
		
		//cette partie est une grande partie de la conversion en calculant réellement la conversion d'une unité à l'autre
		public void Convertir(TextField txtVA,TextField txtVB,ComboBox boxVA, ComboBox boxVB)
		{
				verifNum(txtVA);
				verifNum(txtVB);
				int itemvol1 =boxVA.getSelectionModel().getSelectedIndex();
				int itemvol2 =boxVB.getSelectionModel().getSelectedIndex();
			    double tauxV=volume[itemvol2]/volume[itemvol1];
			    double resV=tauxV*(Double.parseDouble(txtVA.getText()));
			    txtVB.setText(String.format("%.2f", resV));
		}
		
		public void ConvertirP(TextField txtPA,TextField txtPB,ComboBox boxPA, ComboBox boxPB)
		{
				verifNum(txtPA);
				verifNum(txtPB);
				int itempoid1 =boxPA.getSelectionModel().getSelectedIndex();
				int itempoid2 =boxPB.getSelectionModel().getSelectedIndex();
			    double tauxP=poids[itempoid2]/poids[itempoid1];
			    double resP=tauxP*(Double.parseDouble(txtPA.getText()));
			    txtPB.setText(String.format("%.2f", resP));
		}
		
		public void ConvertirD(TextField txtDA,TextField txtDB,ComboBox boxDA, ComboBox boxDB)
		{	
				verifNum(txtDA);
				verifNum(txtDB);
				int itemdis1 =boxDA.getSelectionModel().getSelectedIndex();
				int itemdis2 =boxDB.getSelectionModel().getSelectedIndex();
			    double tauxD=distance[itemdis2]/distance[itemdis1];
			    double resD=tauxD*(Double.parseDouble(txtDA.getText()));
			    txtDB.setText(String.format("%.2f", resD));
				
		} 
		//Gestion numerique --acpter des caracteres numeriques seulement 
				public void verifNum(TextField a)
				
				{
						if(a.getText().equals("")) a.setText("0");
						a.textProperty().addListener((observable,oldValue,newValue)->
							{
							if(!newValue.matches("^[0-9](\\.[0-9]+)?$)"))	
								
							{
								a.setText(newValue.replaceAll("[^\\d*\\.]", ""));
							}
							
						});
						
				}
				
		
		
		
		
	}
		
	


