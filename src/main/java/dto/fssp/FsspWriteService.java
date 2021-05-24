package dto.fssp;

import dto.StringsData;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;


public class FsspWriteService {

    public void run(List<FsspInfo> infoList) throws IOException {

        String path = "D:\\ideProjects\\parser\\";

        String pathFolder = path + "ИП ДОЛЖНИК\\";
        String pathFolderALl = path + "ИП ДОЛЖНИК (Все документы)\\";

        Files.createDirectory(Path.of(pathFolder));
        Files.createDirectory(Path.of(pathFolderALl));

        StringsData data = new StringsData();


        for (FsspInfo info : infoList) {

            XWPFDocument document = createDoc(data, info); //HERE

            String dirName = info.getIpNumber().replace("/", "_").trim();
            String pathDir = pathFolder + info.getIndex() + " " + dirName + "\\";
            Files.createDirectory(Path.of(pathDir));
            String finalDir = pathDir + "Ходатайство об окончании ИП № " + dirName + ".docx";

            FileOutputStream withFolders = new FileOutputStream(finalDir);
            document.write(withFolders);
            withFolders.close();

            //For all docs
            FileOutputStream noFolders = new FileOutputStream(pathFolderALl + dirName + ".docx");
            document.write(noFolders);
            noFolders.close();


        }
        infoList.clear();
    }

    private XWPFDocument createDoc(StringsData str, FsspInfo info) {


        String caseInfo = "В производстве " + info.getFsspDepartment() + " находится исполнительное производство № " + info.getIpNumber() +
                ", возбужденное на основании исполнительного документа " + info.getIpDocument() + " о взыскании с ООО «НСГ – «РОСЭНЕРГО» " +
                "суммы в размере " + info.getDebtSumm() + " руб.";

        String firstAsk = "1.   Окончить исполнительное производство № " + info.getIpNumber() + ";";
        String secondAsd = "2. Исполнительный документ " + info.getIpDocument() +
                " направить в адрес конкурсного управляющего ООО «НСГ – «РОСЭНЕРГО» по адресу: 127994, г. Москва, ГСП-4.";


        XWPFDocument document = new XWPFDocument();
        XWPFTable table = document.createTable();
        createFormedTable(table);

        XWPFTableRow row0 = table.getRow(0);
        XWPFTableRow row1 = table.createRow();
        XWPFTableRow row2 = table.createRow();
        XWPFTableRow row3 = table.createRow();
        XWPFTableRow row4 = table.createRow();
        XWPFTableRow row5 = table.createRow();
        XWPFTableRow row6 = table.createRow();
        XWPFTableRow row7 = table.createRow();
        XWPFTableRow row8 = table.createRow();
        XWPFTableRow row9 = table.createRow();
        XWPFTableRow row10 = table.createRow();
        XWPFTableRow row11 = table.createRow();
        XWPFTableRow row12 = table.createRow();
        XWPFTableRow row13 = table.createRow();
        XWPFTableRow row14 = table.createRow();
        XWPFTableRow row15 = table.createRow();

        XWPFTableCell R0C0 = row0.getCell(0);
        XWPFTableCell R1C0 = row1.getCell(0);
        XWPFTableCell R2C0 = row2.getCell(0);
        XWPFTableCell R3C0 = row3.getCell(0);
        XWPFTableCell R4C0 = row4.getCell(0);
        XWPFTableCell R5C0 = row5.getCell(0);
        XWPFTableCell R6C0 = row6.getCell(0);
        XWPFTableCell R7C0 = row7.getCell(0);
        XWPFTableCell R8C0 = row8.getCell(0);
        XWPFTableCell R9C0 = row9.getCell(0);
        XWPFTableCell R10C0 = row10.getCell(0);
        XWPFTableCell R11C0 = row11.getCell(0);
        XWPFTableCell R12C0 = row12.getCell(0);
        XWPFTableCell R13C0 = row13.getCell(0);
        XWPFTableCell R14C0 = row14.getCell(0);
        XWPFTableCell R15C0 = row15.getCell(0);

        XWPFTableCell R0C1 = row0.createCell();
        XWPFTableCell R1C1 = row1.createCell();
        XWPFTableCell R2C1 = row2.createCell();
        XWPFTableCell R3C1 = row3.createCell();
        XWPFTableCell R4C1 = row4.createCell();
        XWPFTableCell R5C1 = row5.createCell();
        XWPFTableCell R6C1 = row6.createCell();
        XWPFTableCell R7C1 = row7.createCell();
        XWPFTableCell R8C1 = row8.createCell();
        XWPFTableCell R9C1 = row9.createCell();
        XWPFTableCell R10C1 = row10.createCell();
        XWPFTableCell R11C1 = row11.createCell();
        XWPFTableCell R12C1 = row12.createCell();
        XWPFTableCell R13C1 = row13.createCell();
        XWPFTableCell R14C1 = row14.createCell();
        XWPFTableCell R15C1 = row15.createCell();

        crtCellMidPrf(R0C0, str.getGovCorp());
        crtCellMidBoldPrf(R1C0, str.getAgency());
        crtCellMidBoldPrf(R2C0, str.getInsur());
        crtCellMidPrf(R3C0, "");
        crtCellMidBoldPrf(R4C0, str.getManager());
        crtCellMidBoldPrf(R5C0, str.getLfo());
        crtCellMidPrf(R6C0, "");
        crtCellMidPrf(R7C0, str.getAsvAddress());
        crtCellMidPrf(R8C0, str.getPhnNmb());
        crtCellMidPrf(R9C0, "");
        crtCellMidPrf(R10C0, str.getDateAndNmb());
        crtCellMidPrf(R11C0, "");
        crtCellMidPrf(R12C0, "");
        crtCellMidPrf(R13C0, "");
        crtCellMidPrf(R14C0, "");
        crtCellMidPrf(R15C0, "");

        crtCellBoldPrf(R0C1, info.getFsspDepartment());
        crtCellPrf(R1C1, info.getFsspAdress());
        crtCellPrf(R2C1, "");
        crtBoldAndNormalCellPrf(R3C1, str.getDebtor(), str.getLfo());
        crtCellPrf(R4C1, str.getOgrn());
        crtCellPrf(R5C1, str.getInn());
        crtCellPrf(R6C1, str.getLfoAddress1());
        crtCellPrf(R7C1, str.getLfoAddress2());
        crtCellBoldPrf(R8C1, str.getInFace1());
        crtCellBoldPrf(R9C1, str.getInFace2());
        crtCellBoldPrf(R10C1, str.getInFace3());
        crtCellPrf(R11C1, "");
        crtCellBoldPrf(R12C1, str.getMailAddress());
        crtCellPrf(R13C1, str.getAsvAddress());
        crtCellPrf(R14C1, "");
        crtBoldAndNormalCellPrf(R15C1, str.getIpNumberTxt(), info.getIpNumber());

        crtSpanPrf(document);
        crtSpanPrf(document);
        crtMidBoldPrf(document, str.getPetition());
        crtMidPrf(document, str.getPettAbout());
        crtSpanPrf(document);

        crtPrf(document, caseInfo);
        crtPrf(document, str.getDecisionInfo());
        crtPrf(document, str.getFsspLawInfo());
        crtPrf(document, str.getResultInfo());

        crtSpanPrf(document);
        crtMidBoldPrf(document, str.getAsking());
        crtSpanPrf(document);

        crtPrf(document, firstAsk);
        crtPrf(document, secondAsd);
        crtSpanPrf(document);

        crtBoldPrf(document, str.getAttachment());
        crtPrf(document, "1.\t" + str.getDecisionAttach());
        crtPrf(document, "2.\t" + str.getWarrantAttach());
        crtSpanPrf(document);
        crtSpanPrf(document);

        noRedLinePrf(document, str.getSignData1());
        noRedLinePrf(document, str.getSignData2());
        noRedLinePrf(document, str.getSignData3());

        return document;
    }


    //Additional methods

    private void createFormedTable(XWPFTable table) {
        table.removeBorders();
        CTTbl sTable = table.getCTTbl();
        CTTblPr pr = sTable.getTblPr();
        CTTblWidth tblW = pr.getTblW();
        tblW.setW(BigInteger.valueOf(5200));
        tblW.setType(STTblWidth.PCT);
        pr.setTblW(tblW);
        sTable.setTblPr(pr);
        CTJc jc = pr.addNewJc();
        jc.setVal(STJc.RIGHT);
        pr.setJc(jc);
    }

    private void crtCellPrf(XWPFTableCell cell, String text) {
        XWPFParagraph paragraph = cell.addParagraph();
        cell.removeParagraph(0);
        paragraph.setSpacingAfter(0);
        paragraph.setSpacingBefore(0);
        XWPFRun run = paragraph.createRun();
        run.setFontSize(12);
        run.setFontFamily("Times New Roman");
        run.setText(text);
    }

    private void crtBoldAndNormalCellPrf(XWPFTableCell cell, String textOne, String textTwo) {
        XWPFParagraph paragraph = cell.addParagraph();
        cell.removeParagraph(0);
        paragraph.setSpacingAfter(0);
        paragraph.setSpacingBefore(0);
        XWPFRun run = paragraph.createRun();
        run.setFontSize(12);
        run.setFontFamily("Times New Roman");
        run.setBold(true);
        run.setText(textOne + " ");
        run = paragraph.createRun();
        run.setFontSize(12);
        run.setFontFamily("Times New Roman");
        run.setText(textTwo);
    }

    private void crtCellBoldPrf(XWPFTableCell cell, String text) {
        XWPFParagraph paragraph = cell.addParagraph();
        cell.removeParagraph(0);
        XWPFRun run = paragraph.createRun();
        paragraph.setSpacingAfter(0);
        paragraph.setSpacingBefore(0);
        run.setFontSize(12);
        run.setFontFamily("Times New Roman");
        run.setBold(true);
        run.setText(text);
    }

    private void crtCellMidPrf(XWPFTableCell cell, String text) {
        XWPFParagraph paragraph = cell.addParagraph();
        paragraph.setAlignment(ParagraphAlignment.CENTER);
        cell.removeParagraph(0);
        paragraph.setSpacingAfter(0);
        paragraph.setSpacingBefore(0);
        XWPFRun run = paragraph.createRun();
        run.setFontSize(12);
        run.setFontFamily("Times New Roman");
        run.setText(text);
    }

    private void crtCellMidBoldPrf(XWPFTableCell cell, String text) {
        XWPFParagraph paragraph = cell.addParagraph();
        paragraph.setAlignment(ParagraphAlignment.CENTER);
        cell.removeParagraph(0);
        XWPFRun run = paragraph.createRun();
        paragraph.setSpacingAfter(0);
        paragraph.setSpacingBefore(0);
        run.setFontSize(12);
        run.setFontFamily("Times New Roman");
        run.setBold(true);
        run.setText(text);
    }

    private void crtMidPrf(XWPFDocument document, String text) {
        XWPFParagraph paragraph = document.createParagraph();
        paragraph.setAlignment(ParagraphAlignment.CENTER);
        paragraph.setSpacingAfter(0);
        paragraph.setSpacingBefore(0);
        XWPFRun run = paragraph.createRun();
        run.setFontSize(12);
        run.setFontFamily("Times New Roman");
        run.setText(text);
    }

    private void crtMidBoldPrf(XWPFDocument document, String text) {

        XWPFParagraph paragraph = document.createParagraph();
        paragraph.setAlignment(ParagraphAlignment.CENTER);
        paragraph.setSpacingAfter(0);
        paragraph.setSpacingBefore(0);
        XWPFRun run = paragraph.createRun();
        run.setFontSize(12);
        run.setBold(true);
        run.setFontFamily("Times New Roman");
        run.setText(text);
    }

    private void crtPrf(XWPFDocument document, String text) {
        XWPFParagraph paragraph = document.createParagraph();
        paragraph.setAlignment(ParagraphAlignment.BOTH);
        paragraph.setFirstLineIndent(500);
        paragraph.setSpacingAfter(0);
        paragraph.setSpacingBefore(0);
        XWPFRun run = paragraph.createRun();
        run.setFontSize(12);
        run.setFontFamily("Times New Roman");
        run.setText(text);
    }

    private void noRedLinePrf(XWPFDocument document, String text) {
        XWPFParagraph paragraph = document.createParagraph();
        paragraph.setAlignment(ParagraphAlignment.BOTH);
        paragraph.setSpacingAfter(0);
        paragraph.setSpacingBefore(0);
        XWPFRun run = paragraph.createRun();
        run.setFontSize(12);
        run.setFontFamily("Times New Roman");
        run.setText(text);
    }

    private void crtBoldPrf(XWPFDocument document, String text) {
        XWPFParagraph paragraph = document.createParagraph();
        paragraph.setAlignment(ParagraphAlignment.BOTH);
        paragraph.setFirstLineIndent(500);
        paragraph.setSpacingAfter(0);
        paragraph.setSpacingBefore(0);
        XWPFRun run = paragraph.createRun();
        run.setBold(true);
        run.setFontSize(12);
        run.setFontFamily("Times New Roman");
        run.setText(text);
    }

    private void crtSpanPrf(XWPFDocument document) {
        XWPFParagraph paragraph = document.createParagraph();
        paragraph.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun run = paragraph.createRun();
        paragraph.setSpacingAfter(0);
        paragraph.setSpacingBefore(0);
        run.setFontSize(12);
        run.setFontFamily("Times New Roman");
    }
}
