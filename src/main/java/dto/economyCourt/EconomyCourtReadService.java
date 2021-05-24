package dto.economyCourt;


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

public class EconomyCourtReadService {

    private static final String EXCEL_FILE_PATH = "D:\\ideProjects\\parser\\File2.xlsx";
    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
    private final List<CourtInfo> caseList = new ArrayList<>();

    private static final String INDEX = "Индекс";
    private static final String COURT_NAME = "Суд";
    private static final String COURT_ADDRESS = "Адрес суда";
    private static final String CLAIMER_NAME = "Истец/Кредитор";
    private static final String CLAIMER_OGRN = "ОГРН Истца/Кредитора";
    private static final String CLAIMER_INN = "ИНН Истца/Кредитора";
    private static final String CASE_NUM = "Номер дела";
    private static final String JUDGE = "Судья";
    private static final String CASE_TYPE = "Категория спора";

    private int indexCol;
    private int cortNameCol;
    private int cortAddressCol;
    private int claimNameCol;
    private int claimOgrnCol;
    private int claimInnCol;
    private int caseNumCol;
    private int judgeCol;
    private int caseTypeCol;




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
                case COURT_NAME -> cortNameCol = c;
                case COURT_ADDRESS -> cortAddressCol = c;
                case CLAIMER_NAME -> claimNameCol = c;
                case CLAIMER_OGRN -> claimOgrnCol = c;
                case CLAIMER_INN -> claimInnCol = c;
                case CASE_NUM -> caseNumCol = c;
                case JUDGE -> judgeCol = c;
                case CASE_TYPE -> caseTypeCol = c;

            }
        }


        for (int r = 1; r <= rows; r++) {
            XSSFRow row = sheet.getRow(r);


            CourtInfo info = new CourtInfo(
                    getCellText(row.getCell(indexCol)).trim(),
                    getCellText(row.getCell(cortNameCol)).trim(),
                    getCellText(row.getCell(cortAddressCol)).trim(),
                    getCellText(row.getCell(claimNameCol)).trim(),
                    getCellText(row.getCell(claimOgrnCol)).trim(),
                    getCellText(row.getCell(claimInnCol)).trim(),
                    getCellText(row.getCell(caseNumCol)).trim(),
                    getCellText(row.getCell(judgeCol)).trim(),
                    getCellText(row.getCell(caseTypeCol)).trim());


            caseList.add(info);
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


    public List<CourtInfo> getDataList() {
        return caseList;
    }

}
