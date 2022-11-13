import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;

public class MainWindow extends JFrame implements ActionListener, MouseListener
{
    FileManagement file = new FileManagement();
    FileDialog fileLoad = new FileDialog(this, "Please select a report to load.", FileDialog.LOAD);
    FileDialog fileSave = new FileDialog(this, "Please select where to save the report.", FileDialog.SAVE);
    // Creates a new SpringLayout object called myLayout
    SpringLayout myLayout = new SpringLayout();

    String reportDate = "00/00/0000";
    String reportTime = "00:00am";
    String reportLocation = "00 Test Drive, Testburg";
    int recordedValue = 1000;

    String[] issueTypes = {"Sulphur Dioxide", "Nitrogen Dioxide", "Carbon Monoxide", "Obstruction"};

    ArrayList<JLabel> dataGridBoxes = new ArrayList<JLabel>();

    IssuesValues issueSulphurDioxide = new IssuesValues(
            "Sulphur Dioxide", "SO2", 2, 10, 20);
    IssuesValues issueNitrogenDioxide = new IssuesValues(
            "Nitrogen Dioxide", "NO2", 2, 9, 19);
    IssuesValues issueCarbonMonoxide = new IssuesValues(
            "Carbon Monoxide", "CO", 3, 12, 22);
    IssuesValues issueObstruction = new IssuesValues(
            "Obstruction", "Obs", 1, 2, 3);

    // Component initializer.
    JLabel lblIanIndustrialTitle;
    JLabel lblLocation;
    JLabel lblDate;
    JLabel lblTime;

    JLabel lblChemicalFormula;
    JLabel lblChemicalName;
    JLabel lblLegend;
    JLabel lblLegendDangerous;
    JLabel lblLegendConcerning;
    JLabel lblLegendAcceptable;

    JComboBox cboIssueSelector;
    JButton btnLoadFile;

    JLabel lblRecordedLevel;

    JRadioButton rbtExportFileRaf;
    JRadioButton rbtExportFileDat;
    JRadioButton rbtExportFileRpt;
    JButton btnExportRecord;

    BackgroundPanel pnlDataGridPanel;
    JPanel pnlDataSubPanel;

    JLabel dataGridBox;
    Color backgroundColour = new Color(54,57,63);

    // MainWindow constructor.
    public MainWindow()
    {
        reportLocation = file.GetReportLocation();
        reportDate = file.GetReportDate();
        reportTime = file.GetReportTime();

        ButtonGroup bgExportFile = new ButtonGroup();

        Font mainFont = new Font("helvetica", Font.PLAIN, 20);

        setTitle("Ian's Industrial Installation");
        setSize(1280, 760);
        setLocation(100, 100);
        setResizable(false);

        // JPanel creation.
        JPanel pnlMasterPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        JPanel pnlTitlePanel = new JPanel();
        JPanel pnlTopPanel = new JPanel(myLayout);
        pnlDataGridPanel = new BackgroundPanel(myLayout);
        pnlDataSubPanel = new JPanel(myLayout);
        JPanel pnlBottomPanel = new JPanel(myLayout);

        // JPanel size assignment.
        pnlTitlePanel.setPreferredSize(new Dimension(1280, 40));
        pnlTopPanel.setPreferredSize(new Dimension(1280, 60));
        pnlDataGridPanel.setPreferredSize(new Dimension(1280, 520));
        pnlBottomPanel.setPreferredSize(new Dimension(1280, 100));

        // JPanel background assignment.
        pnlMasterPanel.setBackground(backgroundColour);
        this.setBackground(backgroundColour);
        pnlTitlePanel.setBackground(new Color(189,160,40));
        pnlTopPanel.setBackground(backgroundColour);
        pnlDataGridPanel.setBackground(Color.GREEN);
        pnlDataSubPanel.setBackground(backgroundColour);
        pnlBottomPanel.setBackground(backgroundColour);

        pnlDataSubPanel.setOpaque(false);

        pnlDataGridPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));

        // JPanel assignment.
        pnlMasterPanel.add(pnlTitlePanel);
        pnlMasterPanel.add(pnlTopPanel);
        pnlMasterPanel.add(pnlDataGridPanel);
        pnlMasterPanel.add(pnlBottomPanel);

        lblIanIndustrialTitle = new JLabel("Ian’s Industrial Installation");
        lblIanIndustrialTitle.setFont(new Font("Times New Roman", Font.ITALIC|Font.BOLD, 30));
        pnlTitlePanel.add(lblIanIndustrialTitle);

        lblChemicalName = new JLabel();
        lblChemicalName.setFont(mainFont);
        lblChemicalName.setForeground(Color.WHITE);
        myLayout.putConstraint(SpringLayout.WEST,lblChemicalName,20, SpringLayout.WEST, this);
        myLayout.putConstraint(SpringLayout.VERTICAL_CENTER,lblChemicalName,15, SpringLayout.NORTH, this);
        pnlTopPanel.add(lblChemicalName);

        lblChemicalFormula = new JLabel();
        lblChemicalFormula.setFont(mainFont);
        lblChemicalFormula.setForeground(Color.WHITE);
        myLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER,lblChemicalFormula,0, SpringLayout.HORIZONTAL_CENTER, lblChemicalName);
        myLayout.putConstraint(SpringLayout.VERTICAL_CENTER,lblChemicalFormula,45, SpringLayout.NORTH, this);
        pnlTopPanel.add(lblChemicalFormula);

        lblLocation = new JLabel();
        lblLocation.setFont(mainFont);
        lblLocation.setForeground(Color.WHITE);
        myLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER,lblLocation,640, SpringLayout.WEST, this);
        myLayout.putConstraint(SpringLayout.NORTH,lblLocation,0, SpringLayout.NORTH, this);
        pnlTopPanel.add(lblLocation);

        lblTime = new JLabel();
        lblTime.setFont(mainFont);
        lblTime.setForeground(Color.WHITE);
        myLayout.putConstraint(SpringLayout.EAST,lblTime,-20, SpringLayout.HORIZONTAL_CENTER, lblLocation);
        myLayout.putConstraint(SpringLayout.NORTH,lblTime,5, SpringLayout.SOUTH, lblLocation);
        pnlTopPanel.add(lblTime);

        lblDate = new JLabel();
        lblDate.setFont(mainFont);
        lblDate.setForeground(Color.WHITE);
        myLayout.putConstraint(SpringLayout.WEST,lblDate,20, SpringLayout.HORIZONTAL_CENTER, lblLocation);
        myLayout.putConstraint(SpringLayout.NORTH,lblDate,5, SpringLayout.SOUTH, lblLocation);
        pnlTopPanel.add(lblDate);

        lblRecordedLevel = new JLabel("Recorded value: 0");
        lblRecordedLevel.setFont(mainFont);
        lblRecordedLevel.setForeground(Color.WHITE);
        myLayout.putConstraint(SpringLayout.EAST,lblRecordedLevel,1260, SpringLayout.WEST, this);
        myLayout.putConstraint(SpringLayout.VERTICAL_CENTER,lblRecordedLevel,30, SpringLayout.NORTH, this);
        pnlTopPanel.add(lblRecordedLevel);

        btnLoadFile = new JButton("Load File");
        btnLoadFile.setFont(mainFont);
        myLayout.putConstraint(SpringLayout.WEST,btnLoadFile,20, SpringLayout.WEST, this);
        myLayout.putConstraint(SpringLayout.VERTICAL_CENTER,btnLoadFile,50, SpringLayout.NORTH, this);
        btnLoadFile.addActionListener(this);
        pnlBottomPanel.add(btnLoadFile);

        cboIssueSelector = new JComboBox(issueTypes);
        cboIssueSelector.setFont(mainFont);
        myLayout.putConstraint(SpringLayout.WEST,cboIssueSelector,10, SpringLayout.EAST, btnLoadFile);
        myLayout.putConstraint(SpringLayout.VERTICAL_CENTER,cboIssueSelector,50, SpringLayout.NORTH, this);
        cboIssueSelector.addActionListener(this);
        pnlBottomPanel.add(cboIssueSelector);

        lblLegend = new JLabel("Legend:");
        lblLegend.setFont(mainFont);
        lblLegend.setForeground(Color.WHITE);
        myLayout.putConstraint(SpringLayout.EAST,lblLegend,635, SpringLayout.WEST, this);
        myLayout.putConstraint(SpringLayout.VERTICAL_CENTER,lblLegend,50, SpringLayout.NORTH, this);
        pnlBottomPanel.add(lblLegend);

        lblLegendDangerous = new JLabel("Dangerous", SwingConstants.CENTER);
        lblLegendDangerous.setFont(mainFont);
        lblLegendDangerous.setOpaque(true);
        lblLegendDangerous.setForeground(Color.BLACK);
        lblLegendDangerous.setBackground(Color.RED);
        lblLegendDangerous.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        lblLegendDangerous.setPreferredSize(new Dimension(110, 30));
        myLayout.putConstraint(SpringLayout.WEST,lblLegendDangerous,640, SpringLayout.WEST, this);
        myLayout.putConstraint(SpringLayout.NORTH,lblLegendDangerous,5, SpringLayout.NORTH, this);
        pnlBottomPanel.add(lblLegendDangerous);

        lblLegendConcerning = new JLabel("Concerning", SwingConstants.CENTER);
        lblLegendConcerning.setFont(mainFont);
        lblLegendConcerning.setOpaque(true);
        lblLegendConcerning.setForeground(Color.BLACK);
        lblLegendConcerning.setBackground(Color.YELLOW);
        lblLegendConcerning.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        lblLegendConcerning.setPreferredSize(new Dimension(110, 30));
        myLayout.putConstraint(SpringLayout.WEST,lblLegendConcerning,640, SpringLayout.WEST, this);
        myLayout.putConstraint(SpringLayout.NORTH,lblLegendConcerning,0, SpringLayout.SOUTH, lblLegendDangerous);
        pnlBottomPanel.add(lblLegendConcerning);

        lblLegendAcceptable = new JLabel("Acceptable", SwingConstants.CENTER);
        lblLegendAcceptable.setFont(mainFont);
        lblLegendAcceptable.setOpaque(true);
        lblLegendAcceptable.setForeground(Color.BLACK);
        lblLegendAcceptable.setBackground(Color.GREEN);
        lblLegendAcceptable.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        lblLegendAcceptable.setPreferredSize(new Dimension(110, 30));
        myLayout.putConstraint(SpringLayout.WEST,lblLegendAcceptable,640, SpringLayout.WEST, this);
        myLayout.putConstraint(SpringLayout.NORTH,lblLegendAcceptable,0, SpringLayout.SOUTH, lblLegendConcerning);
        pnlBottomPanel.add(lblLegendAcceptable);

        btnExportRecord = new JButton("Export Record");
        btnExportRecord.setFont(mainFont);
        btnExportRecord.setForeground(Color.BLACK);
        myLayout.putConstraint(SpringLayout.EAST,btnExportRecord,1260, SpringLayout.WEST, this);
        myLayout.putConstraint(SpringLayout.VERTICAL_CENTER,btnExportRecord,50, SpringLayout.NORTH, this);
        btnExportRecord.addActionListener(this);
        pnlBottomPanel.add(btnExportRecord);

        rbtExportFileRaf = new JRadioButton(".RAF");
        rbtExportFileRaf.setFont(mainFont);
        rbtExportFileRaf.setOpaque(false);
        rbtExportFileRaf.setForeground(Color.WHITE);
        myLayout.putConstraint(SpringLayout.EAST,rbtExportFileRaf,-10, SpringLayout.WEST, btnExportRecord);
        myLayout.putConstraint(SpringLayout.NORTH,rbtExportFileRaf,0, SpringLayout.NORTH, this);
        pnlBottomPanel.add(rbtExportFileRaf);

        rbtExportFileDat = new JRadioButton(".DAT");
        rbtExportFileDat.setFont(mainFont);
        rbtExportFileDat.setOpaque(false);
        rbtExportFileDat.setForeground(Color.WHITE);
        myLayout.putConstraint(SpringLayout.EAST,rbtExportFileDat,-10, SpringLayout.WEST, btnExportRecord);
        myLayout.putConstraint(SpringLayout.NORTH,rbtExportFileDat,0, SpringLayout.SOUTH, rbtExportFileRaf);
        pnlBottomPanel.add(rbtExportFileDat);

        rbtExportFileRpt = new JRadioButton(".RPT");
        rbtExportFileRpt.setFont(mainFont);
        rbtExportFileRpt.setOpaque(false);
        rbtExportFileRpt.setForeground(Color.WHITE);
        myLayout.putConstraint(SpringLayout.EAST,rbtExportFileRpt,-10, SpringLayout.WEST, btnExportRecord);
        myLayout.putConstraint(SpringLayout.NORTH,rbtExportFileRpt,0, SpringLayout.SOUTH, rbtExportFileDat);
        pnlBottomPanel.add(rbtExportFileRpt);

        // Radio Button Group assignment.
        bgExportFile.add(rbtExportFileRaf);
        bgExportFile.add(rbtExportFileDat);
        bgExportFile.add(rbtExportFileRpt);

        pnlDataGridPanel.add(pnlDataSubPanel);

        // DataSubPanel position assignment.
        myLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER,pnlDataSubPanel,640, SpringLayout.WEST, pnlDataGridPanel);
        myLayout.putConstraint(SpringLayout.VERTICAL_CENTER,pnlDataSubPanel,260, SpringLayout.NORTH, pnlDataGridPanel);


        // Adds the master panel to the frame.
        this.add(pnlMasterPanel);
        // Sets the frame to be visable.
        this.setVisible(true);
        // Sets the default behaviour for when the Windows close button is pressed.
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    // Handles the creation of the individual boxes that make up the data grid.
    private void CreateDataGridBox(int row, int column, int value, JPanel panel, int currentCount, MouseListener listener)
    {
        int acceptableLevel = 0;
        int concerningLevel = 0;
        int dangerousLevel = 0;

        dataGridBox = new JLabel();

        dataGridBox.setPreferredSize(new Dimension(20, 20));
        dataGridBox.setOpaque(true);
        dataGridBox.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        dataGridBox.setText(Integer.toString(value));


        switch (cboIssueSelector.getSelectedItem().toString())
        {
            case "Sulphur Dioxide":
                acceptableLevel = issueSulphurDioxide.getAcceptableLevels();
                concerningLevel = issueSulphurDioxide.getConcerningLevels();
                dangerousLevel = issueSulphurDioxide.getDangerousLevels();
                break;

            case "Nitrogen Dioxide":
                acceptableLevel = issueNitrogenDioxide.getAcceptableLevels();
                concerningLevel = issueNitrogenDioxide.getConcerningLevels();
                dangerousLevel = issueNitrogenDioxide.getDangerousLevels();
                break;

            case "Carbon Monoxide":
                acceptableLevel = issueCarbonMonoxide.getAcceptableLevels();
                concerningLevel = issueCarbonMonoxide.getConcerningLevels();
                dangerousLevel = issueCarbonMonoxide.getDangerousLevels();
                break;
            case "Obstruction":
                acceptableLevel = issueObstruction.getAcceptableLevels();
                concerningLevel = issueObstruction.getConcerningLevels();
                dangerousLevel = issueObstruction.getDangerousLevels();
                break;
        }

            if (value >= dangerousLevel)
            {
                dataGridBox.setBackground(Color.RED);
                dataGridBox.setForeground(Color.RED);
            }
            else if (value >= concerningLevel && value < dangerousLevel)
            {
                dataGridBox.setBackground(Color.YELLOW);
                dataGridBox.setForeground(Color.YELLOW);
            }
            else if (value >= acceptableLevel && value < concerningLevel)
            {
                dataGridBox.setBackground(Color.GREEN);
                dataGridBox.setForeground(Color.GREEN);
            }
            else
            {
                dataGridBox.setBackground(Color.WHITE);
                dataGridBox.setForeground(Color.WHITE);
            }

        myLayout.putConstraint(SpringLayout.WEST,dataGridBox,10 + (column * 25), SpringLayout.WEST, this);
        myLayout.putConstraint(SpringLayout.NORTH,dataGridBox,10 + (row * 25), SpringLayout.NORTH, this);

        dataGridBox.addMouseListener(listener);
        dataGridBoxes.add(dataGridBox);
        panel.add(dataGridBoxes.get(currentCount));
    }

    // Inputs the data from the Readings list and passes it into the CreateDataGridBox method.
    public void GenerateDataGrid()
    {
        int boxCount = 0;
        for (int i1 = 0; i1 < file.Get2dParentSize(); i1++)
        {
            for (int i2 = 0; i2 < file.Get2dChildSize(i1); i2++)
            {
                CreateDataGridBox(i1, i2, file.Get2dArrayValue(i1, i2),  pnlDataSubPanel, boxCount, this);
                boxCount++;
            }
        }

        switch (cboIssueSelector.getSelectedItem().toString())
        {
            case "Sulphur Dioxide":
                lblChemicalName.setText(issueSulphurDioxide.getIssueName());
                lblChemicalFormula.setText(issueSulphurDioxide.getIssueFormula());
                break;

            case "Nitrogen Dioxide":
                lblChemicalName.setText(issueNitrogenDioxide.getIssueName());
                lblChemicalFormula.setText(issueNitrogenDioxide.getIssueFormula());
                break;

            case "Carbon Monoxide":
                lblChemicalName.setText(issueCarbonMonoxide.getIssueName());
                lblChemicalFormula.setText(issueCarbonMonoxide.getIssueFormula());
                break;
            case "Obstruction":
                lblChemicalName.setText(issueObstruction.getIssueName());
                lblChemicalFormula.setText(issueObstruction.getIssueFormula());
                break;
        }
    }

    // Handles clearing away an existing data grid.
    public void RemoveDataGrid()
    {
            for (int i = 0; i < dataGridBoxes.size(); i++)
            {
                pnlDataSubPanel.removeAll();
                pnlDataGridPanel.removeAll();
            }
            pnlDataSubPanel.setOpaque(true);
            pnlDataGridPanel.revalidate();
            pnlDataGridPanel.repaint();
            dataGridBoxes.clear();
            AddDataSubGrid();
    }

    // Adds the DataSubPanel to prepare it for accepting the data grid.
    public void AddDataSubGrid()
    {
        pnlDataGridPanel.add(pnlDataSubPanel);

        pnlDataSubPanel.setPreferredSize(new Dimension(20 + (file.Get2dChildSize(0) * 25),
                20 + (file.Get2dParentSize() * 25)));

        myLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER,pnlDataSubPanel,640, SpringLayout.WEST, pnlDataGridPanel);
        myLayout.putConstraint(SpringLayout.VERTICAL_CENTER,pnlDataSubPanel,260, SpringLayout.NORTH, pnlDataGridPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        try
        {
            if (e.getSource() == btnLoadFile)
            {

                fileLoad.setVisible(true);
                String getFile = fileLoad.getDirectory() + fileLoad.getFile();

                if (getFile == null)
                {

                }
                else
                {

                    file.ReadReadingsValue(getFile);
                    lblLocation.setText(file.GetReportLocation());
                    lblDate.setText(file.GetReportDate());
                    lblTime.setText(file.GetReportTime());
                    RemoveDataGrid();
                    GenerateDataGrid();
                }
            }
            else if (e.getSource() == cboIssueSelector)
            {
                RemoveDataGrid();
                GenerateDataGrid();
            }
            else if (e.getSource() == btnExportRecord)
            {
                boolean radioFound = false;

                int currentValue = 0;

                int acceptableLevel;
                int concerningLevel;
                int dangerousLevel;

                String[][] colourArray = new String[file.Get2dParentSize()][file.Get2dChildSize(0)];

                while (radioFound == false)
                {
                    if (rbtExportFileRaf.isSelected() == true)
                    {
                        fileSave.setVisible(true);
                        String getFile = fileSave.getDirectory() + fileSave.getFile() + ".raf";
                        file.ExportRaf(getFile, lblLocation.getText(), lblDate.getText(), lblTime.getText());
                        radioFound = true;
                    }
                    else if (rbtExportFileDat.isSelected() == true)
                    {
                        fileSave.setVisible(true);
                        String getFile = fileSave.getDirectory() + fileSave.getFile() + ".dat";
                        file.ExportDat(getFile);
                        radioFound = true;
                    }
                    else if (rbtExportFileRpt.isSelected() == true)
                    {
                        switch (cboIssueSelector.getSelectedItem().toString())
                        {
                            case "Sulphur Dioxide":
                                acceptableLevel = issueSulphurDioxide.getAcceptableLevels();
                                concerningLevel = issueSulphurDioxide.getConcerningLevels();
                                dangerousLevel = issueSulphurDioxide.getDangerousLevels();

                                for (int i = 0; i < file.Get2dParentSize(); i++)
                                {
                                    for (int i2 = 0; i2 < file.Get2dChildSize(i); i2++)
                                    {
                                        currentValue = file.Get2dArrayValue(i, i2);

                                        if (currentValue >= dangerousLevel)
                                        {
                                            colourArray[i][i2] = "Red";
                                        }
                                        else if (currentValue >= concerningLevel)
                                        {
                                            colourArray[i][i2] = "Yellow";
                                        }
                                        else if (currentValue >= acceptableLevel)
                                        {
                                            colourArray[i][i2] = "Green";
                                        }
                                        else
                                        {
                                            colourArray[i][i2] = "White";
                                        }
                                    }
                                }
                                break;

                            case "Nitrogen Dioxide":
                                acceptableLevel = issueNitrogenDioxide.getAcceptableLevels();
                                concerningLevel = issueNitrogenDioxide.getConcerningLevels();
                                dangerousLevel = issueNitrogenDioxide.getDangerousLevels();

                                for (int i = 0; i < file.Get2dParentSize(); i++)
                                {
                                    for (int i2 = 0; i2 < file.Get2dChildSize(i); i2++)
                                    {
                                        currentValue = file.Get2dArrayValue(i, i2);

                                        if (currentValue >= dangerousLevel)
                                        {
                                            colourArray[i][i2] = "Red";
                                        }
                                        else if (currentValue >= concerningLevel)
                                        {
                                            colourArray[i][i2] = "Yellow";
                                        }
                                        else if (currentValue >= acceptableLevel)
                                        {
                                            colourArray[i][i2] = "Green";
                                        }
                                        else
                                        {
                                            colourArray[i][i2] = "White";
                                        }
                                    }
                                }
                                break;

                            case "Carbon Monoxide":
                                acceptableLevel = issueCarbonMonoxide.getAcceptableLevels();
                                concerningLevel = issueCarbonMonoxide.getConcerningLevels();
                                dangerousLevel = issueCarbonMonoxide.getDangerousLevels();

                                for (int i = 0; i < file.Get2dParentSize(); i++)
                                {
                                    for (int i2 = 0; i2 < file.Get2dChildSize(i); i2++)
                                    {
                                        currentValue = file.Get2dArrayValue(i, i2);

                                        if (currentValue >= dangerousLevel)
                                        {
                                            colourArray[i][i2] = "Red";
                                        }
                                        else if (currentValue >= concerningLevel)
                                        {
                                            colourArray[i][i2] = "Yellow";
                                        }
                                        else if (currentValue >= acceptableLevel)
                                        {
                                            colourArray[i][i2] = "Green";
                                        }
                                        else
                                        {
                                            colourArray[i][i2] = "White";
                                        }
                                    }
                                }
                                break;
                            case "Obstruction":
                                acceptableLevel = issueObstruction.getAcceptableLevels();
                                concerningLevel = issueObstruction.getConcerningLevels();
                                dangerousLevel = issueObstruction.getDangerousLevels();

                                for (int i = 0; i < file.Get2dParentSize(); i++)
                                {
                                    for (int i2 = 0; i2 < file.Get2dChildSize(i); i2++)
                                    {
                                        currentValue = file.Get2dArrayValue(i, i2);

                                        if (currentValue >= dangerousLevel)
                                        {
                                            colourArray[i][i2] = "Red";
                                        }
                                        else if (currentValue >= concerningLevel)
                                        {
                                            colourArray[i][i2] = "Yellow";
                                        }
                                        else if (currentValue >= acceptableLevel)
                                        {
                                            colourArray[i][i2] = "Green";
                                        }
                                        else
                                        {
                                            colourArray[i][i2] = "White";
                                        }
                                    }
                                }
                                break;
                        }
                        fileSave.setVisible(true);
                        String getFile = fileSave.getDirectory() + fileSave.getFile() + ".rpt";
                        file.ExportRpt(getFile, colourArray);
                        radioFound = true;
                    }
                    else
                    {

                    }
                }
            }
        }
        catch (Exception ex)
        {

        }
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {

    }

    @Override
    public void mousePressed(MouseEvent e)
    {

    }

    @Override
    public void mouseReleased(MouseEvent e)
    {

    }

    @Override
    public void mouseEntered(MouseEvent e)
    {

            JLabel label = (JLabel) e.getSource() ;
            lblRecordedLevel.setText("Recorded value: " + label.getText());
    }

    @Override
    public void mouseExited(MouseEvent e)
    {

    }
}


