package dto.creditors;

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

public class CreditorReadService {


    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
    private final List<CreditorInfo> creditorList = new ArrayList<>();

    private static final String CREDITOR_NAME = "<Creditor_name>";
    private static final String CREDITOR_ADDRESS = "<Creditor_adress>";
    private static final String SUM = "<Summ>";
    private static final String CONTRACT_REQ = "<Contract_req>";
    private static final String ACR_REQ = "<Act_req>";
    private static final String SUM_ACT = "<Summ_Act>";
    private static final String CONTRACT_CLAIM_REQ = "<Contract_Claim_req>";
    private static final String ACT_CLAIM_REQ = "<Act_Claim _req>";
    private static final String SUM_CLAIM_REQ = "<Summ_Claim_Act>";

    private int creditorNameCol;
    private int creditorAddressCol;
    private int sumCol;
    private int contractReqCol;
    private int actReqCol;
    private int sumActCol;
    private int contractClaimReqCol;
    private int actClaimReqCol;
    private int sumClaimActCol;

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
                case CREDITOR_NAME -> creditorNameCol = c;
                case CREDITOR_ADDRESS -> creditorAddressCol = c;
                case SUM -> sumCol = c;
                case CONTRACT_REQ -> contractReqCol = c;
                case ACR_REQ -> actReqCol = c;
                case SUM_ACT -> sumActCol = c;
                case CONTRACT_CLAIM_REQ -> contractClaimReqCol = c;
                case ACT_CLAIM_REQ -> actClaimReqCol = c;
                case SUM_CLAIM_REQ -> sumClaimActCol = c;

            }
        }

        for (int r = 2; r <= rows; r++) { //Начинаем со строки  с индесом 2
            XSSFRow row = sheet.getRow(r);

            CreditorInfo info = new CreditorInfo(
                    clean(getCellText(row.getCell(creditorNameCol))),
                    clean(getCellText(row.getCell(creditorAddressCol))),
                    clean(getCellText(row.getCell(sumCol))),
                    clean(getCellText(row.getCell(contractReqCol))),
                    clean(getCellText(row.getCell(actReqCol))),
                    clean(getCellText(row.getCell(sumActCol))),
                    clean(getCellText(row.getCell(contractClaimReqCol))),
                    clean(getCellText(row.getCell(actClaimReqCol))),
                    clean(getCellText(row.getCell(sumClaimActCol)))
            );

            creditorList.add(info);
        }

        inputStream.close();
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

    public List<CreditorInfo> getCreditorList() {
        return creditorList;
    }
}
