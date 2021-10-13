package dto.data;

import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;

import java.math.BigInteger;

public class CreateTextData {


    //Additional methods
    public String rpStringForm(String courtName) {
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


    public void createAsvForm(
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


    public void createSimpleTopForm(
            XWPFTableCell cell,
            String toName,
            String toAddress
    ) {
        XWPFParagraph paragraph = cell.addParagraph();
        cell.removeParagraph(0);

        paragraph.setSpacingAfter(0);
        paragraph.setSpacingBefore(0);


        XWPFRun run = paragraph.createRun();
        run.setBold(true);
        run.setFontSize(12);
        run.setFontFamily("Times New Roman");

        if (toName.length() < 39) {
            run.setText(toName);
        } else {
            String[] strings = toName.split(" ");
            String firstNameStr = strings[0] + " " + strings[1];
            String secondCNameStr = "";

            for (int i = 2; i < strings.length; i++) {
                secondCNameStr = secondCNameStr.trim() + " " + strings[i];
            }

            run.setText(firstNameStr);
            run.addBreak();
            run.setText(secondCNameStr);
        }

        run.addBreak();

        run = paragraph.createRun();
        run.setFontSize(12);
        run.setFontFamily("Times New Roman");

        if (toAddress.length() > 35) {
            run.setText(toAddress);
        } else {
            String[] strings = toAddress.split(" ");
            String firstAddressStr = strings[0] + " " + strings[1];
            String secondAddressStr = "";

            for (int i = 2; i < strings.length; i++) {
                secondAddressStr = secondAddressStr.trim() + " " + strings[i];
            }

            run.setText(firstAddressStr);
            run.addBreak();
            run.setText(secondAddressStr);
        }
    }


    public void createTopForm(
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

        if (court.length() < 39) {
            run.setText(court);
        } else {
            String[] strings = court.split(" ");
            String firstCourtStr = strings[0] + " " + strings[1] + " " + strings[2];
            String secondCourtStr = "";

            for (int i = 3; i < strings.length; i++) {
                secondCourtStr = secondCourtStr.trim() + " " + strings[i];
            }


            run.setText(firstCourtStr);
            run.addBreak();
            run.setText(secondCourtStr);
        }

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
        run.setText("Заявитель");
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


    public void createCreditorTopForm(
            XWPFTableCell cell,
            String creditor,
            String creditorAddress){

        XWPFParagraph paragraph = cell.addParagraph();
        cell.removeParagraph(0);

        paragraph.setSpacingAfter(0);
        paragraph.setSpacingBefore(0);

        XWPFRun run = paragraph.createRun();
        run.setBold(true);
        run.setFontSize(12);
        run.setFontFamily("Times New Roman");
        run.setText("Арбитражный суд Республики Алтай");
        run.addBreak();

        run = paragraph.createRun();
        run.setFontSize(12);
        run.setFontFamily("Times New Roman");
        run.setText("649000, Горно-Алтайск, ул. Ленкина, 4");


        run.addBreak();
        run.addBreak();

        run = paragraph.createRun();
        run.setBold(true);
        run.setFontSize(12);
        run.setFontFamily("Times New Roman");
        run.setText("Кредитор:");
        run.addBreak();

        run = paragraph.createRun();
        run.setFontSize(12);
        run.setFontFamily("Times New Roman");
        run.setText(creditor);
        run.addBreak();





        run = paragraph.createRun();
        run.setFontSize(12);
        run.setFontFamily("Times New Roman");

        if (creditorAddress.length() < 38){
            run.setText(creditorAddress);
        } else {
            String[] strings = creditorAddress.split(" ");
            String firstStr = "";
            String secondStr = "";

            if (strings.length > 3){
                firstStr = strings[0] + " " + strings[1] + " " + strings[2];
                for (int i = 3; i < strings.length; i++) {
                    secondStr = secondStr.trim() + " " + strings[i];
                }
            } else {
                firstStr = strings[0] + " " + strings[1];
                for (int i = 2; i < strings.length; i++) {
                    secondStr = secondStr.trim() + " " + strings[i];
                }
            }



            run.setText(firstStr);
            run.addBreak();
            run.setText(secondStr);

        }






        run.addBreak();

        run.addBreak();

        run = paragraph.createRun();
        run.setBold(true);
        run.setFontSize(12);
        run.setFontFamily("Times New Roman");
        run.setText("Должник:");
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
        run.setText("Дело № ");

        run = paragraph.createRun();
        run.setFontSize(12);
        run.setFontFamily("Times New Roman");
        run.setText("А02-211/2021");

    }

    public void createEconomyTopForm(
            XWPFTableCell cell,
            String court,
            String courtAddress,
            String claimerName,
            String claimerOgrn,
            String claimerInn,
            String caseNum,
            String judge) {

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
        run.setText("Истец:");
        run.addBreak();

        run = paragraph.createRun();
        run.setFontSize(12);
        run.setFontFamily("Times New Roman");
        run.setText(claimerName);
        run.addBreak();
        run.setText("(ОГРН " + claimerOgrn + "; " + "ИНН " + claimerInn + ")");
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
        run.addBreak();
        run.setText("Судья: " + judge);
    }

    public void createFormedTable(XWPFTable table) {
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

    public void crtMidPrf(XWPFDocument document, String text) {
        XWPFParagraph paragraph = document.createParagraph();
        paragraph.setAlignment(ParagraphAlignment.CENTER);
        paragraph.setSpacingAfter(0);
        paragraph.setSpacingBefore(0);
        XWPFRun run = paragraph.createRun();
        run.setFontSize(12);
        run.setFontFamily("Times New Roman");
        run.setText(text);
    }

    public void crtMidBoldPrf(XWPFDocument document, String text) {

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

    public void crtPrf(XWPFDocument document, String text) {
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

    public void noRedLinePrf(XWPFDocument document, String text) {
        XWPFParagraph paragraph = document.createParagraph();
        paragraph.setAlignment(ParagraphAlignment.BOTH);
        paragraph.setSpacingAfter(0);
        paragraph.setSpacingBefore(0);
        XWPFRun run = paragraph.createRun();
        run.setFontSize(12);
        run.setFontFamily("Times New Roman");
        run.setText(text);
    }

    public void crtBoldPrf(XWPFDocument document, String text) {
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


    public void crtItalicPrf(XWPFDocument document, String text) {
        XWPFParagraph paragraph = document.createParagraph();
        paragraph.setAlignment(ParagraphAlignment.BOTH);
        paragraph.setFirstLineIndent(500);
        paragraph.setSpacingAfter(0);
        paragraph.setSpacingBefore(0);
        XWPFRun run = paragraph.createRun();
        run.setItalic(true);
        run.setFontSize(12);
        run.setFontFamily("Times New Roman");
        run.setText(text);
    }

    public void crtSpanPrf(XWPFDocument document) {
        XWPFParagraph paragraph = document.createParagraph();
        paragraph.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun run = paragraph.createRun();
        paragraph.setSpacingAfter(0);
        paragraph.setSpacingBefore(0);
        run.setFontSize(12);
        run.setFontFamily("Times New Roman");
    }
}
