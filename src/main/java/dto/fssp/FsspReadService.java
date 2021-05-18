package dto.fssp;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


public class FsspReadService {

    private static final String EXCEL_FILE_PATH = "D:\\ideProjects\\parser\\File.xlsx";

    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

    private final List<FsspInfo> ipList = new ArrayList<>();

    private static final String INDEX = "Индекс";
    private static final String IP_DATE = "Дата возбуждения";
    private static final String IP_NUMBER = "Номер исполнительного производства";
    private static final String IP_DOCUMENT = "Номер исполнительного документа";
    private static final String FSSP_DEPARTMENT = "Отдел судебных приставов";
    private static final String FSSP_ADRESS = "Адрес отдела судебных приставов";
    private static final String DEBT_SUMM = "Сумма долга";

    private int indexCol;
    private int dateCol;
    private int numCol;
    private int docCol;
    private int departCol;
    private int addressCol;
    private int debtCol;


    public void parse() throws IOException {

        FileInputStream inputStream = new FileInputStream(EXCEL_FILE_PATH);
        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = workbook.getSheetAt(0);// Index of the first row

        int cols = sheet.getRow(0).getLastCellNum();
        int rows = sheet.getLastRowNum();

        for (int c = 0; c < cols; c++) {

            XSSFRow row = sheet.getRow(0);
            String result = getCellText(row.getCell(c));

            switch (result) {
                case INDEX -> indexCol = c;
                case IP_DATE -> dateCol = c;
                case IP_NUMBER -> numCol = c;
                case IP_DOCUMENT -> docCol = c;
                case FSSP_DEPARTMENT -> departCol = c;
                case FSSP_ADRESS -> addressCol = c;
                case DEBT_SUMM -> debtCol = c;
            }
        }


        for (int r = 1; r <= rows; r++) {
            XSSFRow row = sheet.getRow(r);


            FsspInfo info = new FsspInfo(
                    getCellText(row.getCell(indexCol)).trim(),
                    getCellText(row.getCell(dateCol)).trim(),
                    getCellText(row.getCell(numCol)).trim(),
                    getCellText(row.getCell(docCol)).trim(),
                    getCellText(row.getCell(departCol)).trim(),
                    getCellText(row.getCell(addressCol)).trim(),
                    getCellText(row.getCell(debtCol)).trim());

            ipList.add(info);
        }
        inputStream.close();
    }


    private String getCellText(Cell cell) {

        String result = "";

        switch (cell.getCellType()) {
            case STRING:
                result = cell.getRichStringCellValue().getString();
                break;
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    result = sdf.format(cell.getDateCellValue());
                } else {
                    result = Double.toString(cell.getNumericCellValue());
                }
                break;
            case BOOLEAN:
                result = Boolean.toString(cell.getBooleanCellValue());
                break;
            case FORMULA:
                result = cell.getCellFormula();
                break;
            case BLANK:
                result = "Пустая ячейка";
                break;
            default:
                System.out.println("Что-то пошло не так");
        }
        return result;
    }


    public List<FsspInfo> getDataList() {
        return ipList;
    }
}

