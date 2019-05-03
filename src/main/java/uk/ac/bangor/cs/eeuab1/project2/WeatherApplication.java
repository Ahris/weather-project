package uk.ac.bangor.cs.eeuab1.project2;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

public class WeatherApplication extends javax.swing.JFrame {

	private static final long serialVersionUID = 1L;

	public WeatherApplication() {
		setResizable(false);
        initComponents();
    }
    
    private void initComponents() {
    	
        textField = new javax.swing.JTextField();
        parsedDataPane = new javax.swing.JScrollPane();
        textArea = new javax.swing.JTextArea();
        ForecastButton = new javax.swing.JButton();
        urlLabel = new javax.swing.JLabel();
        imgLabel = new javax.swing.JLabel();
        weatherIcon = new javax.swing.JLabel();
        LocationLabel = new javax.swing.JLabel();
        locationText = new javax.swing.JTextField();
        WeatherButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        ListArea = new javax.swing.JList<>();
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Get BBC Weather");
        
        textArea.setColumns(20);
        textArea.setRows(5);
        parsedDataPane.setViewportView(textArea);
        ForecastButton.setText("Forecast");
        ForecastButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ForecastButtonActionPerformed(evt);
            }
        });

        urlLabel.setText("URL");

        weatherIcon.setText(" ");

        LocationLabel.setText("Location");

        WeatherButton.setText("Weather");
        WeatherButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                weatherButtonActionPerformed(evt);
            }
        });

        jScrollPane2.setViewportView(ListArea);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(parsedDataPane, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(LocationLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(locationText, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(WeatherButton))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(urlLabel)
                                .addGap(26, 26, 26)
                                .addComponent(textField, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(ForecastButton)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(imgLabel))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(weatherIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(urlLabel)
                    .addComponent(ForecastButton))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LocationLabel)
                    .addComponent(locationText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(WeatherButton))
                .addGap(54, 54, 54)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 101, Short.MAX_VALUE)
                        .addComponent(imgLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(parsedDataPane, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(weatherIcon, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        pack();
    }

    private void ForecastButtonActionPerformed(java.awt.event.ActionEvent evt) {
        try {       
            url = data.getElementData(textField.getText(), "item", "title");
            textArea.setText(url);

            // Image is changed based on xml data parsed
            ImageIcon icon = new ImageIcon(data.getWeatherIcon(textField.getText()));
            weatherIcon.setIcon(icon);
        } catch (ParserConfigurationException | SAXException | IOException ex) {
        }
    }

    private void weatherButtonActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            location = locationText.getText();
            String geo = "http://api.geonames.org/search?q=" + location + "&maxRows=1&lang=en&username=eeuab1";

            // Sets the text area to the weather of the input location
            textArea.setText(data.getElementData(data.getLocation(geo), "item", "title")); 
            String b = data.getElementData(data.getLocation(geo), "item", "description");
            List<String> updated = data.Split(b);

            //Loop through each item in list and add it as element to JList
            DefaultListModel<String> listModel = new DefaultListModel<String>();
            for (String item : updated) {
                listModel.addElement(item);
            }
            ListArea.setModel(listModel);
            
            DateTimeFormatter.ofPattern("yyyy/mm/dd");
            LocalDate localDate = LocalDate.now();

            // Creates .xml file with information based on input
            new StAX();
            StAX.StAXCreator(localDate.toString(), location, found.toString(), XMLParsing.geoNameID);
            
            // Displays image within program based on output
            String text = data.getLocation(geo);
            ImageIcon icon = new ImageIcon(data.getWeatherIcon(text));
            weatherIcon.setIcon(icon);

        } catch (ParserConfigurationException | SAXException | IOException ex) {
            DefaultListModel<String> resetModel = new DefaultListModel<String>();
            textArea.setText("Location not found, please enter a valid location");
            ListArea.setModel(resetModel);
        }

    }
    
    Boolean found = false;
    String location;
    String url;
    XMLParsing data = new XMLParsing();

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new WeatherApplication().setVisible(true);
            }
        });
    }
    
    private javax.swing.JButton ForecastButton;
    private javax.swing.JLabel imgLabel;
    private javax.swing.JLabel urlLabel;
    private javax.swing.JLabel LocationLabel;
    private javax.swing.JScrollPane parsedDataPane;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList<String> ListArea;
    private javax.swing.JTextField locationText;
    private javax.swing.JTextArea textArea;
    private javax.swing.JTextField textField;
    private javax.swing.JButton WeatherButton;
    private javax.swing.JLabel weatherIcon;
}
