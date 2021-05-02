package dto;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class FsspParser {

    private static final String EXCEL_FILE_PATH = "D:\\ideProjects\\parser\\File.xlsx";

    private List<FsspInfo> ipList = new ArrayList<>();

    private static final String IP_DATE = "Дата возбуждения";
    private static final String IP_NUMBER = "Номер исполнительного производства";
    private static final String IP_DOCUMENT = "Номер исполнительного документа";
    private static final String FSSP_DEPARTMENT = "Отдел судебных приставов";
    private static final String FSSP_ADRESS = "Адрес отдела судебных приставов";
    private static final String DEBT_SUMM = "Сумма долга";

    private int daneCol;
    private int numCol;
    private int docCol;
    private int departCol;
    private int adressCol;
    private int debtCol;


    public void parse() throws IOException {

        FileInputStream inputStream = new FileInputStream(EXCEL_FILE_PATH);
        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = workbook.getSheetAt(0);// Index of the first row

        int rows = sheet.getLastRowNum();
        int cols = sheet.getRow(0).getLastCellNum();


    }

    


}
