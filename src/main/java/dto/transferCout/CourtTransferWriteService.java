package dto.transferCout;

import dto.StringsData;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;


public class CourtTransferWriteService {

    public void run(List<TransferInfo> infoList) throws IOException {

        String path = "D:\\ideProjects\\parser\\";

        String pathFolder = path + "ЛФО-Подсудность\\";
        String pathFolderALl = path + "ЛФО-Подсудность (Все документы)\\";

        Files.createDirectory(Path.of(pathFolder));
        Files.createDirectory(Path.of(pathFolderALl));

        StringsData data = new StringsData();

        for (TransferInfo info : infoList) {

            XWPFDocument document = createDoc(data, info); //HERE

            String dirName = info.getCaseNum().replace("/", "_").trim();
            String pathDir = pathFolder + info.getIndex() + " " + dirName + "\\";
            Files.createDirectory(Path.of(pathDir));

            String finalDir = pathDir + " Ходатйство о передаче по подсудности дела № " + dirName + ".docx";

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

    private XWPFDocument createDoc(StringsData str, TransferInfo info) {

        String caseInfo = "В производстве " +
                rpStringForm(info.getCourtName()) + " находится дело № " +
                info.getCaseNum() + " (Истец(ы): " +
                info.getClaimerName() + ", Ответчик: " +
                str.getLfo() + ").";

        String firstAsk = "1.\tПередать гражданское дело № " +
                info.getCaseNum() + " (Истец: " +
                info.getClaimerName() + ", Ответчик: ООО «НСГ – «РОСЭНЕРГО) " +
                "для рассмотрения в рамках дела о банкротстве № А02-211/2021 в Арбитражный суд Республики Алтай " +
                "(649000 Республика Алтай, г. Горно-Алтайск ул. Ленкина, 4);";


        XWPFDocument document = new XWPFDocument();
        XWPFTable table = document.createTable();
        createFormedTable(table);

        XWPFTableRow row0 = table.getRow(0);
        XWPFTableCell R0C0 = row0.getCell(0);
        XWPFTableCell R0C1 = row0.createCell();


        createAsvForm(
                R0C0,
                str.getGovCorp(),
                str.getAgency(),
                str.getInsur(),
                str.getManager(),
                str.getLfo(),
                str.getAsvAddress(),
                str.getPhnNmb(),
                str.getDateAndNmb()
        );

        createTopForm(
                R0C1,
                info.getCourtName(),
                info.getCourtAddress(),
                info.getClaimerName(),
                info.getCaseNum()
        );


        crtSpanPrf(document);
        crtSpanPrf(document);

        crtMidBoldPrf(document, str.getPetition());
        crtMidPrf(document, str.sendToAnotherCourt());
        crtSpanPrf(document);

        crtPrf(document, caseInfo);
        crtPrf(document, str.getDecisionInfo());
        crtPrf(document, str.transferPart3());
        crtPrf(document, str.transferPart4());
        crtPrf(document, str.transferPart5());
        crtPrf(document, str.transferPart6());
        crtPrf(document, str.transferPart7());
        crtPrf(document, str.getResultInfo());


        crtSpanPrf(document);
        crtMidBoldPrf(document, str.getAsking());
        crtSpanPrf(document);

        crtPrf(document, firstAsk);
        crtPrf(document, str.transferSecondAsk());
        crtPrf(document, str.transferThirdAsk());
        crtSpanPrf(document);

        crtBoldPrf(document, str.getAttachment());

        crtPrf(document, "1.\t" + str.getDecisionAttach());
        crtPrf(document, "2.\t" + str.getWarrantAttach());
        crtPrf(document, "3.\t" + str.getDiplomaAttach());

        crtSpanPrf(document);
        crtSpanPrf(document);

        noRedLinePrf(document, str.getSignData1());
        noRedLinePrf(document, str.getSignData2());

        return document;
    }


    //Additional methods
    private String rpStringForm(String courtName) {
        return courtName
                .replace("вой", "вого")
                .replace("судебный", "судебного")
                .replace("ный", "ного")
                .replace("участок", "участка")
                .replace("онный", "онного")
                .replace("ский", "ского")
                .replace(" суд", " суда")
                .replace("судья", "судьи")
                .replace("ской", "ского");
    }

    private void createAsvForm(
            XWPFTableCell cell,
            String govCorp,
            String agency,
            String insur,
            String manager,
            String lfo,
            String asvAddress,
            String phnNum,
            String dateAndNmb) {

        XWPFParagraph paragraph = cell.addParagraph();
        cell.removeParagraph(0);
        paragraph.setAlignment(ParagraphAlignment.CENTER);
        paragraph.setSpacingAfter(0);
        paragraph.setSpacingBefore(0);
        XWPFRun run = paragraph.createRun();
        run.setFontSize(12);
        run.setFontFamily("Times New Roman");
        run.setText(govCorp);
        run.addBreak();
        run = paragraph.createRun();
        run.setBold(true);
        run.setFontSize(12);
        run.setFontFamily("Times New Roman");
        run.setText(agency);
        run.addBreak();
        run.setText(insur);
        run.addBreak();
        run.addBreak();
        run.setText(manager);
        run.addBreak();
        run.setText(lfo);
        run.addBreak();
        run.addBreak();
        run = paragraph.createRun();
        run.setFontSize(12);
        run.setFontFamily("Times New Roman");
        run.setText(asvAddress);
        run.addBreak();
        run.setText(phnNum);
        run.addBreak();
        run.addBreak();
        run.setText(dateAndNmb);
    }


    private void createTopForm(
            XWPFTableCell cell,
            String court,
            String courtAddress,
            String claimerName,
            String caseNum
    ) {

        XWPFParagraph paragraph = cell.addParagraph();
        cell.removeParagraph(0);

        paragraph.setSpacingAfter(0);
        paragraph.setSpacingBefore(0);

        XWPFRun run = paragraph.createRun();
        run.setBold(true);
        run.setFontSize(12);
        run.setFontFamily("Times New Roman");
        run.setText(court);
        run.addBreak();

        run = paragraph.createRun();
        run.setFontSize(12);
        run.setFontFamily("Times New Roman");
        run.setText(courtAddress);
        run.addBreak();
        run.addBreak();

        run = paragraph.createRun();
        run.setBold(true);
        run.setFontSize(12);
        run.setFontFamily("Times New Roman");
        run.setText("Заявитель (Ответчик)");
        run.addBreak();

        run = paragraph.createRun();
        run.setFontSize(12);
        run.setFontFamily("Times New Roman");
        run.setText("ООО «НСГ – «РОСЭНЕРГО»");
        run.addBreak();
        run.setText("(ОГРН: 1020400754285");
        run.addBreak();
        run.setText("ИНН: 0411063374, КПП: 041101001)");
        run.addBreak();
        run.setText("649000, Республика Алтай, г. Горно-Алтайск, ");
        run.addBreak();
        run.setText("Коммунистический пр-т, д. 9, оф. 1");
        run.addBreak();

        run = paragraph.createRun();
        run.setBold(true);
        run.setFontSize(12);
        run.setFontFamily("Times New Roman");
        run.setText("в лице конкурсного управляющего");
        run.addBreak();
        run.setText("государственной корпорации «Агентство по");
        run.addBreak();
        run.setText("страхованию вкладов»");
        run.addBreak();
        run.addBreak();
        run.setText("Адрес для направления корреспонденции:");
        run.addBreak();

        run = paragraph.createRun();
        run.setFontSize(12);
        run.setFontFamily("Times New Roman");
        run.setText("127994, г. Москва, ГСП-4");
        run.addBreak();
        run.addBreak();

        run = paragraph.createRun();
        run.setBold(true);
        run.setFontSize(12);
        run.setFontFamily("Times New Roman");
        run.setText("Истец(ы):");
        run.addBreak();

        run = paragraph.createRun();
        run.setFontSize(12);
        run.setFontFamily("Times New Roman");
        run.setText(claimerName);
        run.addBreak();
        run.addBreak();

        run = paragraph.createRun();
        run.setBold(true);
        run.setFontSize(12);
        run.setFontFamily("Times New Roman");
        run.setText("Дело № ");

        run = paragraph.createRun();
        run.setFontSize(12);
        run.setFontFamily("Times New Roman");
        run.setText(caseNum);

    }

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
