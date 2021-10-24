package dto.CreditorsOsago;


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

public class OsagoReadService {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
    private final List<OsagoInfo> osagoList = new ArrayList<>();


    private static final String NUM = "<Num>";
    private static final String INDEX = "<Index>";
    private static final String CREDITOR_NAME = "<Creditor_name>";
    private static final String CREDITOR_ADDRESS = "<Creditor_address>";
    private static final String POLICE_OSAGO = "<Police_osago>";
    private static final String POLICE_KASKO = "<Police_kasko>";

    private int numCol;
    private int indexCol;
    private int creditorNameCol;
    private int creditorAddressCol;
    private int policeOsagoCol;
    private int policeKaskoCol;

    public void parse(String path) throws IOException {


        FileInputStream inputStream = new FileInputStream(path);
        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = workbook.getSheetAt(0);// Index of the first row

        int cols = sheet.getRow(0).getLastCellNum();
        int rows = sheet.getLastRowNum();


        for (int c = 0; c < cols; c++) {
            XSSFRow row = sheet.getRow(0);
            String result = getCellText(row.getCell(c));

            switch (result) {
                case NUM: numCol = c;
                case INDEX: indexCol = c;
                case CREDITOR_NAME: creditorNameCol = c;
                case CREDITOR_ADDRESS: creditorAddressCol = c;
                case POLICE_OSAGO: policeOsagoCol = c;
                case POLICE_KASKO: policeKaskoCol = c;
            }
        }

        for (int r = 2; r <= rows; r++) {
            XSSFRow row = sheet.getRow(r);


            OsagoInfo osagoInfo = new OsagoInfo(
                    clean(getCellText(row.getCell(numCol))),
                    clean(getCellText(row.getCell(indexCol))),
                    clean(getCellText(row.getCell(creditorNameCol))),
                    clean(getCellText(row.getCell(creditorAddressCol))),
                    clean(getCellText(row.getCell(policeOsagoCol))),
                    clean(getCellText(row.getCell(policeKaskoCol)))
            );

            osagoList.add(fix(osagoInfo));
        }

        inputStream.close();
    }


    private OsagoInfo fix(OsagoInfo info) {

        String resultName = info.getCreditorName();
        if (resultName.contains("/")) {
            String[] nameArr = resultName.split("/");
            resultName = nameArr[1].trim();
            info.setCreditorName(resultName);
        }

        return info;
    }


    private String clean(String text) {

        return text.trim().replace("\n", "");
    }


    private String getCellText(Cell cell) {

        String result = "";

        if (cell != null) {
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
                    result = "";
                    break;
                default:
                    System.out.println("Что-то пошло не так");
            }
        }
        return result;
    }


    public List<OsagoInfo> getOsagoList() {
        return osagoList;
    }
}
