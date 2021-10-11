package dto.complaints;

import dto.economyCourt.CourtInfo;
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

public class ComplaintReadService {


    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
    private final List<ComplaintInfo> complaintList = new ArrayList<>();

    private static final String EMPTY = "!!!ВСТАВИТЬ ДАННЫЕ!!!";

    private static final String GUILTY_NAME = "Наименование Должника";
    private static final String GUILTY_ADDRESS = "Адрес должника";
    private static final String CONTRACT_NUM = "Номер договора (страхового полиса)";
    private static final String CONTRACT_DATE = "Дата договора";
    private static final String CAR_TYPE = "Марка авто ВИНОВНИКА";
    private static final String CAR_NUM = "Госномер автомобиля ВИНОВНИКА";
    private static final String DEBT = "Сумма основного долга";
    private static final String COMPANY = "СК потерпевшего";
    private static final String TYPE = "Тип обязательства";

    private int guiltyNameCol;
    private int guiltyAddressCol;
    private int contractNumCol;
    private int contractDateCol;
    private int carTypeCol;
    private int carNumCol;
    private int deptCol;
    private int companyCol;
    private int typeCol;

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
                case GUILTY_NAME -> guiltyNameCol = c;
                case GUILTY_ADDRESS -> guiltyAddressCol = c;
                case CONTRACT_NUM -> contractNumCol = c;
                case CONTRACT_DATE -> contractDateCol = c;
                case CAR_TYPE -> carTypeCol = c;
                case CAR_NUM -> carNumCol = c;
                case DEBT -> deptCol = c;
                case COMPANY -> companyCol = c;
                case TYPE -> typeCol = c;
            }
        }


        for (int r = 1; r <= rows; r++) {
            XSSFRow row = sheet.getRow(r);

            if (getCellText(row.getCell(8)).toLowerCase().equals("регресс") ||
                    getCellText(row.getCell(8)).toLowerCase().equals("суброгация")
            ) {
                ComplaintInfo info = new ComplaintInfo(
                        clean(getCellText(row.getCell(guiltyNameCol))),
                        clean(getCellText(row.getCell(guiltyAddressCol))),
                        clean(getCellText(row.getCell(contractNumCol))),
                        clean(getCellText(row.getCell(contractDateCol))),
                        clean(getCellText(row.getCell(carTypeCol))),
                        clean(getCellText(row.getCell(carNumCol))),
                        clean(getCellText(row.getCell(deptCol))),
                        clean(getCellText(row.getCell(companyCol))),
                        clean(getCellText(row.getCell(typeCol)))
                );

                complaintList.add(checkInfo(info));
            }
        }

        inputStream.close();
    }

    private String clean(String text){

        return text.trim().replace("\n","");
    }

    private ComplaintInfo checkInfo(ComplaintInfo info) {

        if (info.getGuiltyName().trim().equals("")) info.setGuiltyName(EMPTY);
        if (info.getGuiltyAddress().trim().equals("")) info.setGuiltyAddress(EMPTY);
        if (info.getContractNum().trim().equals("")) info.setContractNum(EMPTY);
        if (info.getContractDate().trim().equals("")) info.setContractDate(EMPTY);
        if (info.getCarType().trim().equals("")) info.setCarType(EMPTY);
        if (info.getCarNum().trim().equals("")) info.setCarNum(EMPTY);
        if (info.getDept().trim().equals("")) info.setDept(EMPTY);
        if (info.getCompany().trim().equals("")) info.setCompany(EMPTY);

        return info;
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


    public List<ComplaintInfo> getComplaintList() {
        return complaintList;
    }
}
