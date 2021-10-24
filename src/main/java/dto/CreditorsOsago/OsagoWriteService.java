package dto.CreditorsOsago;


import dto.data.CreateTextData;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

public class OsagoWriteService {


    public void run(List<OsagoInfo> infoList, String docxPath) throws IOException {
        run(infoList, docxPath, "");
    }


    public void run(List<OsagoInfo> infoList, String docxPath, String pdfPath) throws IOException {

        String pathFolder = docxPath + "\\Возражения\\";
        String pathFolderALl = docxPath + "\\Возражения (Все документы)\\";

        Files.createDirectory(Path.of(pathFolder));
        Files.createDirectory(Path.of(pathFolderALl));


        for (OsagoInfo info : infoList) {


            XWPFDocument document = createDoc(info);

            String dirName = info.getCreditorName()
                    .replace("/", "_").trim()
                    .replace("\"", "")
                    .replace("»", "")
                    .replace("«", "");

            String pathDir = pathFolder + info.getNum().replace(".0","") + ". " + dirName + "\\";
            Files.createDirectory(Path.of(pathDir));

            int pages = 3;

            String finalDir = pathDir + "Возражение на требование кредитора (" + dirName + ", " + info.getIndex() + ") на " + pages + " л_.docx";

            FileOutputStream withFolders = new FileOutputStream(finalDir);
            document.write(withFolders);
            withFolders.close();


            if (!pdfPath.trim().equals("")) {


                String fromPdf = findPdfFile(pdfPath, info.getIndex());
                String pdfName = "1." + Paths.get(fromPdf).getFileName().toString();

                if (!pdfName.trim().equals("")) {

                    Files.copy(Paths.get(fromPdf), Paths.get(pathDir + pdfName));
                }
            }


            FileOutputStream noFolders = new FileOutputStream(pathFolderALl + info.getNum().replace(".0", "") +
                    ". Возражение на требование кредитора (" + dirName + ") на " + pages + " л_.docx");
            document.write(noFolders);
            noFolders.close();
        }
    }


    private XWPFDocument createDoc(OsagoInfo info) {

        CreateTextData ctd = new CreateTextData();

        String title1 = "ВОЗРАЖЕНИЯ";
        String title2 = "на требования кредитора (категория РО)";

        String par1 = "Решением Арбитражного суда Республики Алтай по делу № А02-211/2021 " +
                "(резолютивная часть которого объявлена 21.07.2021) ООО «НСГ – «РОСЭНЕРГО» " +
                "(ОГРН: 1020400754285, ИНН: 0411063374, 649000, Республика Алтай, г. Горно-Алтайск, " +
                "Коммунистический пр-т, д. 9, оф. 1 (далее – Страховая организация, Должник) признано несостоятельным " +
                "(банкротом), в отношении него открыто конкурсное производство. Полномочия конкурсного управляющего " +
                "в деле о банкротстве страховой организации осуществляет государственная корпорация " +
                "«Агентство по страхованию вкладов» (далее – Конкурсный управляющий, Агентство).";

        String par2Add = "";
        if (withKasko(info)) par2Add = ", договора добровольного страхования " + info.getPoliceKasko();
        String par2 = info.getCreditorName() + " (далее также – Кредитор) до открытия конкурсного производства " +
                "обратился к Должнику с заявлением о расторжении договора ОСАГО " +
                info.getPoliceOsago() + par2Add + " чем совершил действия направленные на возникновение денежного " +
                "обязательства со стороны Должника по возврату страховой премии.";

        String par3 = "Согласно п. 4 ст. 184.5. Федеральным законом от 26.10.2002 № 127-ФЗ " +
                "«О несостоятельности (банкротстве)» (далее – Закон о банкротстве), " +
                "требования страхователя, застрахованного лица или выгодоприобретателя " +
                "по договорам страхования включаются в реестр требований кредиторов в порядке " +
                "очередности, предусмотренном ст. 184.10 Закона о банкротстве, " +
                "независимо от даты возникновения обязательства.";

        String par4 = "Таким образом заявление о расторжении договора ОСАГО по существу является требованием кредитора.";

        String par5Add = "";
        if (withKasko(info)) par5Add = " в части требования по договору ОСАГО,";
        String par5 = "Конкурсный управляющий возражает относительно " +
                "включения заявленного требования в реестр требований кредиторов Должника" +
                par5Add + " руководствуясь следующим.";

        String par6 = "Конкурсное производство осуществляется в порядке и в соответствии с процедурами, " +
                "которые предусмотрены Федеральным законом от 26.10.2002 № 127-ФЗ " +
                "«О несостоятельности (банкротстве)» (далее – Закон о банкротстве).";

        String par7 = "В соответствии с п. 4 ст. 184.4-1 Закона о банкротстве Агентство как конкурсный управляющий " +
                "страховой организацией является лицом, участвующим в деле о банкротстве страховой организации, " +
                "и ведет реестр требований кредиторов страховой организации без привлечения реестродержателя.";


        String par8 = "Установление требований кредиторов страховой организации осуществляется в порядке и очередности, " +
                "предусмотренных ст. ст. 134, 183.26, 184.10 Закона о банкротстве.";


        String par9 = "Согласно п. 1 ст. 183.26 Закона о банкротстве в целях участия в деле о банкротстве страховой " +
                "организации кредиторы вправе заявить свои требования к страховой организации в ходе конкурсного " +
                "производства в течение двух месяцев с даты опубликования сведений о признании страховой организации " +
                "банкротом. Требования кредиторов направляются в арбитражный суд, страховую организацию и конкурсному " +
                "управляющему с приложением документов, подтверждающих обоснованность этих требований.";

        String par10 = "В силу п. 3 ст. 183.26 Закона о банкротстве конкурсный управляющий включает поступившие " +
                "требования в реестр заявленных требований кредиторов. Конкурсный управляющий не вправе отказать " +
                "во включении поступивших требований в реестр заявленных требований кредиторов. Реестр заявленных " +
                "требований кредиторов подлежит закрытию по истечении двух месяцев с даты опубликования сведений о " +
                "признании Страховщика банкротом.";

        String par11 = "Сведения о признании Страховщика банкротом и об открытии конкурсного производства " +
                "опубликованы 31.07.2021 в газете «Коммерсантъ» и включены в Единый федеральный реестр сведений " +
                "о банкротстве (далее – ЕФРСБ), реестр заявленных требований кредиторов Страховщика закрыт 30.09.2021.";

        String par12 = "Требование Кредитора заявлено в установленный законом срок.";

        String par13 = "Таким образом, поступившее требование Кредитора включено конкурсным управляющим в реестр " +
                "заявленных требований кредиторов Должника.";

        String par14 = "Согласно п. 7 ст. 183.26 Закона о банкротстве требования кредиторов, относительно " +
                "которых не поступили возражения, признаются установленными в составе, размере и очередности, " +
                "которые заявлены кредитором и подлежат включению конкурсным управляющим в реестр требований " +
                "кредиторов после закрытия реестра заявленных требований кредиторов.";

        String par15 = "Однако требование Кредитора в оспариваемой части не может быть включено в реестр требований " +
                "кредиторов Должника в связи со следующим.";

        String par16 = "Согласно п. 1.14. Правилах обязательного страхования гражданской ответственности владельцев " +
                "транспортных средств, утв. Положением Банка России от 19.09.2014 № 431-П (далее – Правила ОСАГО), " +
                "страхователь вправе досрочно прекратить действие договора обязательного страхования в случае замены " +
                "собственника транспортного средства.";

        String par17 = "Согласно п. 1.16 Правил ОСАГО, в случаях досрочного прекращения действия договора обязательного " +
                "страхования, предусмотренных пунктом 1.14 Правил ОСАГО, датой досрочного прекращения действия " +
                "договора обязательного страхования считается дата получения страховщиком письменного заявления " +
                "страхователя о досрочном прекращении действия договора обязательного страхования и документального " +
                "подтверждения факта, послужившего основанием для досрочного прекращения договора.";

        String par18 = "Исходя из вышеизложенного односторонняя сделка по расторжению договора ОСАГО на основании п. " +
                "1.14 Правил ОСАГО считается свершившийся в момент представления страховщику доказательств наличия " +
                "обстоятельств, предусмотренных указанным пунктом.";

        String par19 = "В противном случае сделка считается не совершенной, а договор ОСАГО – продолжает действовать " +
                "до момента выполнения страхователем условий указанного пункта либо прекращения " +
                "срока страхования (смотря, что наступит ранее).";
        String par20 = "Между тем, к заявлению кредитора не приложены документы, подтверждающие наличие обстоятельств, " +
                "предусмотренных п. 1.14. Правил ОСАГО, либо такие документы нечитаемые или представлены не в полном виде, " +
                "либо имеются правовые пороки влекущие недействительность сделки.";

        String par21 = "При данных обстоятельствах договор ОСАГО продолжал действовать, право на возврат " +
                "страховой премии не возникло.";

        String par22 = "В силу п. 5 ст. 183.26 Закона о банкротстве возражения относительно требований кредиторов, " +
                "включенных в реестр заявленных требований кредиторов, могут быть предъявлены в арбитражный суд в " +
                "том числе конкурсным управляющим в течение тридцати дней с даты закрытия реестра заявленных требований кредиторов.";

        String par23 = "На основании изложенного, руководствуясь ст. ст. 41, гл. 28 АПК РФ, ст. 2, 100, 142, " +
                "183.26, 184.4-1, 184.5, 184.10 Закона о банкротстве,";

        String asking = "ПРОШУ:";


        String ask1Add = "";
        if (withKasko(info)) ask1Add = " в оспариваемой части";
        String ask1 = "1.\tпроверить обоснованность требования Кредитора на наличие оснований для его включения в " +
                "реестр требований кредиторов ООО «НСГ-РОСЭНЕРГО»" + ask1Add + ";";

        String ask2 = "2.\tпо результатам рассмотрения требования Кредитора включить или отказать во включении в реестр " +
                "требований кредиторов ООО «НСГ-РОСЭНЕРГО» в оспариваемой части;";

        String ask3 = "3.\tнаправить судебный акт в адрес конкурсного управляющего " +
                "ООО «НСГ-РОСЭНЕРГО»: 127994, г. Москва, ГСП-4;";

        String ask4 = "4.\tв случае оставления настоящих возражений без движения по мотиву отсутствия сведений об адресе " +
                "Кредитора или доказательств направления ему настоящих возражений – " +
                "предоставить срок для устранения указанных обстоятельств до 01 февраля 2022 г. в связи с тем, что для " +
                "выявления адреса места жительства Кредитора требуется продолжительное время.";


        String attach = "Приложения.";
        String attach1 = "1.\tКопия требования Кредитора с приложениями.";
        String attach2 = "2.\tКопия доверенности представителя.";
        String attach3 = "3.\tКопия диплома.";
        String attach4 = "4.\tКопия документа о направлении возражения в адрес кредитора (при наличии).";








        /*
           --- ВЕРСТАЕМ ---
*/

        XWPFDocument document = new XWPFDocument();
        XWPFTable table = document.createTable();
        ctd.createFormedTable(table);

        XWPFTableRow row0 = table.getRow(0);
        XWPFTableCell R0C0 = row0.getCell(0);
        XWPFTableCell R0C1 = row0.createCell();

        ctd.createAsvForm(
                R0C0,
                "ГОСУДАРСТВЕННАЯ КОРПОРАЦИЯ ",
                "АГЕНСТВО",
                "ПО СТРАХОВАНИЮ ВКЛАДОВ",
                "КОНКУРСНЫЙ УПРАВЛЯЮЩИЙ ",
                "ООО «НСГ – «РОСЭНЕРГО»",
                "127994, г. Москва, ГСП-4",
                "тел. +7 (495) 725-31-29",
                "от____________№____________"
        );

        ctd.createCreditorTopForm(
                R0C1,
                info.getCreditorName(),
                info.getCreditorAddress()
        );

        ctd.crtSpanPrf(document);
        ctd.crtSpanPrf(document);

        ctd.crtMidBoldPrf(document, title1);
        ctd.crtMidPrf(document, title2);
        ctd.crtSpanPrf(document);

        ctd.crtPrf(document, par1);
        ctd.crtPrf(document, par2);
        ctd.crtPrf(document, par3);
        ctd.crtPrf(document, par4);
        ctd.crtPrf(document, par5);
        ctd.crtPrf(document, par6);
        ctd.crtPrf(document, par7);
        ctd.crtPrf(document, par8);
        ctd.crtPrf(document, par9);
        ctd.crtPrf(document, par10);
        ctd.crtPrf(document, par11);
        ctd.crtPrf(document, par12);
        ctd.crtPrf(document, par13);
        ctd.crtPrf(document, par14);
        ctd.crtPrf(document, par15);

        ctd.crtSpanPrf(document);

        ctd.crtPrf(document, par16);
        ctd.crtPrf(document, par17);
        ctd.crtPrf(document, par18);
        ctd.crtPrf(document, par19);
        ctd.crtPrf(document, par20);
        ctd.crtPrf(document, par21);

        ctd.crtSpanPrf(document);

        ctd.crtPrf(document, par22);
        ctd.crtPrf(document, par23);

        ctd.crtSpanPrf(document);
        ctd.crtMidBoldPrf(document, asking);
        ctd.crtSpanPrf(document);

        ctd.crtPrf(document, ask1);
        ctd.crtPrf(document, ask2);
        ctd.crtPrf(document, ask3);
        ctd.crtPrf(document, ask4);

        ctd.crtSpanPrf(document);
        ctd.crtSpanPrf(document);

        ctd.crtBoldPrf(document, attach);
        ctd.crtPrf(document, attach1);
        ctd.crtPrf(document, attach2);
        ctd.crtPrf(document, attach3);
        ctd.crtPrf(document, attach4);


        return document;
    }

    private boolean withKasko(OsagoInfo info) {
        return !info.getPoliceKasko().trim().equals("");
    }


    private String pdfPath = "";

    private String findPdfFile(String path, String index) throws IOException {

        Stream<Path> filePathStream = Files.walk(Paths.get(path));

        filePathStream
                .filter(Files::isRegularFile)
                .filter(filePath -> filePath.toString().toLowerCase().endsWith("pdf"))
                .filter(filePath -> filePath.getFileName().toString().toLowerCase().contains(index.toLowerCase()))
                .forEach(filePath -> pdfPath = filePath.toString());
        return pdfPath;
    }


}
