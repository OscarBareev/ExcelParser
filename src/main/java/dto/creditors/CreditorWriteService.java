package dto.creditors;


import dto.data.CreateTextData;
import dto.data.StringsData;
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

public class CreditorWriteService {

    private String pdfPath = "";

    public void run(List<CreditorInfo> infoList, String docxPath) throws IOException {
        run(infoList, docxPath, "");
    }

    public void run(List<CreditorInfo> infoList, String docxPath, String pdfPath) throws IOException {

        String pathFolder = docxPath + "\\Возражения\\";
        String pathFolderALl = docxPath + "\\Возражения (Все документы)\\";

        Files.createDirectory(Path.of(pathFolder));
        Files.createDirectory(Path.of(pathFolderALl));


        StringsData data = new StringsData();


        for (CreditorInfo info : infoList) {


            XWPFDocument document = createDoc(data, info);

            String dirName = info.getCreditorName()
                    .replace("/", "_").trim()
                    .replace("\"", "")
                    .replace("»", "")
                    .replace("«", "");

            String pathDir = pathFolder + info.getNum() + ". " + dirName + "\\";
            Files.createDirectory(Path.of(pathDir));


            int pages = 4;
            if (!containsOrange(info) && !containsYellow(info)) pages = 3;


            String finalDir = pathDir + "Возражение на требование кредитора (" + dirName + ") на " + pages + " л_.docx";

            FileOutputStream withFolders = new FileOutputStream(finalDir);
            document.write(withFolders);
            withFolders.close();

            if (!pdfPath.trim().equals("")) {


                String fromPdf = findPdfFile(pdfPath, info.getNum());
                String pdfName = Paths.get(fromPdf).getFileName().toString();

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


    private XWPFDocument createDoc(StringsData str, CreditorInfo info) {

        CreateTextData ctd = new CreateTextData();

/*
          --- СОЗДАЕМ СТРОКИ ---
*/

        String creditorPar2 = info.getCreditorName() + " (далее также – Кредитор) обратился к Конкурсному " +
                "управляющему с заявлением о включении в реестр требований кредиторов Должника " +
                "требования об оплате агентского вознаграждения " +
                "и услуг в общем размере " + info.getSum() + " руб.";

        if (containsYellow(info) || containsOrange(info)) creditorPar2 = creditorPar2 + ", в том числе по договору:";

        String creditorPar3 = "- возмездного оказания услуг в сфере страхования " +
                info.getContractReq() + " по актам " +
                info.getActReq() + " на общую сумму " +
                info.getSumAct() + " руб.";

        if (containsOrange(info)) creditorPar3 = creditorPar3 + ";";

        String creditorIfPar4 = "- возмездного оказания услуг по урегулированию убытков " +
                info.getContractClaimReq() + " по актам " +
                info.getActClaimReq() + " на общую сумму " +
                info.getSumClaimAct() + " руб.";

        String creditorPar5 = "Конкурсный управляющий возражает относительно " +
                "включения заявленного требования в реестр требований кредиторов " +
                "Должника";

        if (containsYellow(info) || containsOrange(info)) {
            creditorPar5 = creditorPar5 + ", в том числе, сумм по:";
        } else {
            creditorPar5 = creditorPar5 + ".";
        }


        String creditorPar6 = "- договору возмездного оказания услуг в сфере страхования " +
                info.getContractReq() + " по актам " +
                info.getActReq() + " на общую сумму " +
                info.getSumAct() + " руб.";

        if (!containsOrange(info)) creditorPar6 = creditorPar6 + ", руководствуясь следующим.";

        String creditorIfPar7 = "- договору возмездного оказания услуг по урегулированию убытков " +
                info.getContractClaimReq() + " по актам " +
                info.getActClaimReq() + " на общую сумму " +
                info.getSumClaimAct() + " руб.";

        if (containsOrange(info)) creditorIfPar7 = creditorIfPar7 + ", руководствуясь следующим.";

        String creditorPar8 = "Конкурсное производство осуществляется в порядке и в " +
                "соответствии с процедурами, которые предусмотрены Федеральным законом " +
                "от 26.10.2002 № 127-ФЗ «О несостоятельности (банкротстве)» (далее – Закон о банкротстве).";

        String creditorPar9 = "В соответствии с п. 4 ст. 184.4-1 Закона о банкротстве " +
                "Агентство как конкурсный управляющий страховой организацией является лицом, " +
                "участвующим в деле о банкротстве страховой организации, и ведет реестр требований кредиторов " +
                "страховой организации без привлечения реестродержателя.";

        String creditorPar10 = "Установление требований кредиторов страховой организации осуществляется в " +
                "порядке и очередности, предусмотренных ст. ст. 134, 183.26, 184.10 Закона о банкротстве.";

        String creditorPar11 = "Согласно п. 1 ст. 183.26 Закона о банкротстве в целях участия в деле " +
                "о банкротстве страховой организации кредиторы вправе заявить свои требования к страховой организации " +
                "в ходе конкурсного производства в течение двух месяцев с даты опубликования сведений о признании " +
                "страховой организации банкротом. Требования кредиторов направляются в арбитражный суд, " +
                "страховую организацию и конкурсному управляющему с приложением документов, подтверждающих " +
                "обоснованность этих требований.";

        String creditorPar12 = "В силу п. 3 ст. 183.26 Закона о банкротстве конкурсный управляющий включает " +
                "поступившие требования в реестр заявленных требований кредиторов. " +
                "Конкурсный управляющий не вправе отказать во включении поступивших требований в реестр заявленных " +
                "требований кредиторов. Реестр заявленных требований кредиторов подлежит закрытию по истечении " +
                "двух месяцев с даты опубликования сведений о признании Страховщика банкротом.";

        String creditorPar13 = "Сведения о признании Страховщика банкротом и об открытии конкурсного производства " +
                "опубликованы 31.07.2021 в газете «Коммерсантъ» и включены в Единый федеральный реестр сведений " +
                "о банкротстве (далее – ЕФРСБ), реестр заявленных требований кредиторов Страховщика закрыт 30.09.2021.";

        String creditorPar14 = "Требование Кредитора заявлено в установленный законом срок.";

        String creditorPar15 = "Таким образом, поступившее требование Кредитора включено конкурсным управляющим " +
                "в реестр заявленных требований кредиторов Должника.";

        String creditorPar16 = "Согласно п. 7 ст. 183.26 Закона о банкротстве требования кредиторов, " +
                "относительно которых не поступили возражения, признаются установленными в составе, " +
                "размере и очередности, которые заявлены кредитором и подлежат включению конкурсным управляющим в " +
                "реестр требований кредиторов после закрытия реестра заявленных требований кредиторов. ";

        String creditorPar17 = "Однако требование Кредитора в оспариваемой части не может быть включено в реестр " +
                "требований кредиторов Должника в связи со следующим.";


        String newPar1 = "Согласно п. 2.3.25 агентского договора, одновременно с представлением отчета " +
                "агент обязан перечислить принципалу страховые взносы, передать заключенные договоры, " +
                "квитанции по форме А7.";

        String newPar2 = "Согласно п. 3.3. агентского договора, агентское вознаграждение подлежит к " +
                "выплате при условии исполнения агентом обязанности по проведению инвентаризации и возврату " +
                "неиспользованного БСО, предоставлении информации по утраченным и испорченным страховым полисам.";

        String newPar3 = "Согласно п. 3.4. агентского договора, в случае возврата принципалом страхователю страховой " +
                "премии в полном объеме (в т.ч. в случае отказа страхователя от договора страхования) выплата " +
                "вознаграждения Агенту по такому договору страхования не производится, а выплаченное вознаграждение " +
                "подлежит пересчету и возврату принципалу. При этом Принципал вправе произвести пересчет агентского " +
                "вознаграждения возврате страхователю части премии, пропорционально оставшейся части страховой премии.";

        String newPar4 = "Согласно п. 4.4. агентского договора, принципал по своему усмотрению не оплачивает " +
                "агенту агентское вознаграждение, установленное п.3.2. агентского  договора, по договорам " +
                "страхования, отчет о заключении (внесении изменений, выдаче дубликатов) которых агентом " +
                "был сдан с нарушением срока сдачи отчета о работе, установленного п.2.3.25. и п.2.3.26. " +
                "договора, а также в случае нарушения срока и порядка передачи страховых взносов, " +
                "установленного в пункте 3.1. договора, либо передачи взносов в меньшем размере.";

        String newPar5 = "Между тем, к требованию кредитора не приложены доказательства исполнения обязанностей, " +
                "предусмотренных вышеуказанными условиями договора, обуславливающих исполнение Должником своего " +
                "обязательства по выплате агентского вознаграждения, в том числе:";

        String newPar6 = "-\tдоказательства передачи страховых взносов Должнику;";
        String newPar7 = "-\tпередачи Должнику неиспользованный, испорченных бланков строго отчетности, " +
                "сведений об утрате таковых.";


        String creditorPar18 = "Согласно п. 26 Постановления Пленума ВАС РФ от 22.06.2012 № 35 «О некоторых " +
                "процессуальных вопросах, связанных с рассмотрением дел о банкротстве», в силу пунктов " +
                "3 - 5 с. 71 и п. 3 - 5 ст. 100 Закона о банкротстве проверка обоснованности и размера требований " +
                "кредиторов осуществляется судом независимо от наличия разногласий относительно этих требований " +
                "между должником и лицами, имеющими право заявлять соответствующие возражения, с одной стороны, " +
                "и предъявившим требование кредитором - с другой стороны. При установлении требований кредиторов " +
                "в деле о банкротстве судам следует исходить из того, что установленными могут быть признаны только " +
                "требования, в отношении которых представлены достаточные доказательства наличия и размера задолженности. " +
                "В связи с изложенным при установлении требований в деле о банкротстве не подлежит применению " +
                "часть 3.1 статьи 70 АПК РФ, согласно которой обстоятельства, на которые ссылается сторона в " +
                "обоснование своих требований, считаются признанными другой стороной, если они ею прямо не " +
                "оспорены или несогласие с такими обстоятельствами не вытекает из иных доказательств, " +
                "обосновывающих представленные возражения относительно существа заявленных требований; " +
                "также при установлении требований в деле о банкротстве признание должником или арбитражным " +
                "управляющим обстоятельств, на которых кредитор основывает свои требования (часть 3 статьи 70 АПК РФ), " +
                "само по себе не освобождает другую сторону от необходимости доказывания таких обстоятельств.";

        String creditorPar19 = "Кредитор основывает свои требования на актах выполненных работ " +
                info.getActReq() + " к договору возмездного оказания услуг " +
                info.getContractReq() + ", согласно которым Кредитором в отчетный период были оказаны услуги по:";

        String creditorPar20 = "-\tанализу внутренних факторов деятельности Заказчика;";

        String creditorPar21 = "-\tанализу деятельности конкурентов Заказчика;";

        String creditorPar22 = "-\tанализу конкурентоспособности предлагаемых товаров (услуг);";

        String creditorPar23 = "-\tанализу текущей ситуации на рынке, отслеживание тенденций и перспектив развития рынка;";

        String creditorPar24 = "-\tвыявления предпочтения клиентов;";

        String creditorPar25 = "-\tразработки рекомендаций по организации системы клиентов;";

        String creditorPar26 = "-\tсегментации рынка;";

        String creditorPar27 = "-\tинформированию населения о порядке заключения договоров страхования, оформления " +
                "документов для получения страховых выплат, " +
                "о возможности заключения договоров страхования в электронном виде.";

        String creditorPar28 = "Между тем, каких-либо доказательств реального оказания вышеуказанных услуг " +
                "(результатов анализа, рекомендации, свидетельства о информировании третьих лиц) " +
                "Кредитором не представлено, требование основано исключительно на " +
                "формальных доказательствах оказания услуг. ";

        String creditorPar29 = "В силу вышеуказанного п. 26 Постановления Пленума ВАС РФ от 22.06.2012 № 35 " +
                "«О некоторых процессуальных вопросах, связанных с рассмотрением дел о банкротстве» в силу " +
                "повышенного стандарта доказывания признание факта оказания услуг само по себе правого значения не имеет.";

        String creditorIfPar30 = "По аналогичным основаниям Конкурсный управляющий возражает против задолженности, " +
                "основанной на актах " +
                info.getActClaimReq() + " к договору " +
                info.getContractClaimReq() + ".";

        String creditorPar31 = "В силу п. 5 ст. 183.26 Закона о банкротстве возражения относительно " +
                "требований кредиторов, включенных в реестр заявленных требований кредиторов, " +
                "могут быть предъявлены в арбитражный суд в том числе конкурсным управляющим в " +
                "течение тридцати дней с даты закрытия реестра заявленных требований кредиторов.";

        String creditorPar32 = "На основании изложенного, руководствуясь ст. ст. 41, гл. 28 АПК РФ, ст. 2, 100, 142, " +
                "183.26, 184.4-1, 184.5, 184.10 Закона о банкротстве,";

        String askPar1 = "1.\tпроверить обоснованность требования Кредитора на наличие оснований для его " +
                "включения в реестр требований кредиторов ООО «НСГ-РОСЭНЕРГО»;";

        String askPar2 = "2.\tпо результатам рассмотрения требования Кредитора включить или отказать во включении " +
                "в реестр требований кредиторов ООО «НСГ-РОСЭНЕРГО» в оспариваемой части;";

        String askPar3 = "3.\tнаправить судебный акт в адрес конкурсного управляющего " +
                "ООО «НСГ-РОСЭНЕРГО»: 127994, г. Москва, ГСП-4.";

        String att1 = "1.\tкопия доверенности представителя;";
        String att2 = "2.\tкопия диплома;";
        String att3 = "3.\tкопия требования Кредитора с приложениями;";
        String att4 = "4.\tкопия документа о направлении возражения в адрес кредитора.";



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
                str.getGovCorp(),
                str.getAgency(),
                str.getInsur(),
                str.getManager(),
                str.getLfo(),
                str.getAsvAddress(),
                str.getPhnNmb(),
                str.getDateAndNmb()
        );

        ctd.createCreditorTopForm(
                R0C1,
                info.getCreditorName(),
                info.getCreditorAddress()
        );

        ctd.crtSpanPrf(document);
        ctd.crtSpanPrf(document);

        ctd.crtMidBoldPrf(document, "ВОЗРАЖЕНИЯ");
        ctd.crtMidPrf(document, "на требования кредитора (категория А)");
        ctd.crtSpanPrf(document);

        ctd.crtPrf(document, str.getDecisionInfo());
        ctd.crtPrf(document, creditorPar2);
        if (containsYellow(info)) ctd.crtPrf(document, creditorPar3);
        if (containsOrange(info)) ctd.crtPrf(document, creditorIfPar4);
        ctd.crtSpanPrf(document);
        ctd.crtPrf(document, creditorPar5);
        if (containsYellow(info)) ctd.crtPrf(document, creditorPar6);
        if (containsOrange(info)) ctd.crtPrf(document, creditorIfPar7);
        ctd.crtPrf(document, creditorPar8);
        ctd.crtPrf(document, creditorPar9);
        ctd.crtPrf(document, creditorPar10);
        ctd.crtPrf(document, creditorPar11);
        ctd.crtPrf(document, creditorPar12);
        ctd.crtPrf(document, creditorPar13);
        ctd.crtPrf(document, creditorPar14);
        ctd.crtPrf(document, creditorPar15);
        ctd.crtPrf(document, creditorPar16);
        ctd.crtPrf(document, creditorPar17);
        ctd.crtSpanPrf(document);

        ctd.crtPrf(document, newPar1);
        ctd.crtPrf(document, newPar2);
        ctd.crtPrf(document, newPar3);
        ctd.crtPrf(document, newPar4);
        ctd.crtPrf(document, newPar5);
        ctd.crtPrf(document, newPar6);
        ctd.crtPrf(document, newPar7);

        ctd.crtSpanPrf(document);
        if (containsOrange(info) || containsYellow(info)) ctd.crtPrf(document, creditorPar18);
        if (containsYellow(info)) ctd.crtPrf(document, creditorPar19);
        if (containsYellow(info)) ctd.crtPrf(document, creditorPar20);
        if (containsYellow(info)) ctd.crtPrf(document, creditorPar21);
        if (containsYellow(info)) ctd.crtPrf(document, creditorPar22);
        if (containsYellow(info)) ctd.crtPrf(document, creditorPar23);
        if (containsYellow(info)) ctd.crtPrf(document, creditorPar24);
        if (containsYellow(info)) ctd.crtPrf(document, creditorPar25);
        if (containsYellow(info)) ctd.crtPrf(document, creditorPar26);
        if (containsYellow(info)) ctd.crtPrf(document, creditorPar27);
        if (containsOrange(info) || containsYellow(info)) ctd.crtSpanPrf(document);

        if (containsOrange(info) || containsYellow(info)) ctd.crtPrf(document, creditorPar28);
        if (containsYellow(info)) ctd.crtPrf(document, creditorPar29);
        if (containsOrange(info) || containsYellow(info)) ctd.crtSpanPrf(document);

        if (containsOrange(info)) ctd.crtPrf(document, creditorIfPar30);
        if (containsOrange(info)) ctd.crtSpanPrf(document);

        ctd.crtPrf(document, creditorPar31);
        ctd.crtPrf(document, creditorPar32);

        ctd.crtSpanPrf(document);
        ctd.crtMidBoldPrf(document, str.getAsking());
        ctd.crtSpanPrf(document);

        ctd.crtPrf(document, askPar1);
        ctd.crtPrf(document, askPar2);
        ctd.crtPrf(document, askPar3);

        ctd.crtSpanPrf(document);
        ctd.crtSpanPrf(document);

        ctd.crtBoldPrf(document, "Приложения:");
        ctd.crtPrf(document, att1);
        ctd.crtPrf(document, att2);
        ctd.crtPrf(document, att3);
        ctd.crtPrf(document, att4);

        return document;
    }


    private boolean containsOrange(CreditorInfo info) {
        return !info.getContractClaimReq().equals("")
                && !info.getActClaimReq().equals("")
                && !info.getSumClaimAct().equals("")
                && !info.getSumClaimAct().trim().equalsIgnoreCase("нет")
                && !info.getSumClaimAct().trim().equalsIgnoreCase("отсутсвуют");
    }

    private boolean containsYellow(CreditorInfo info) {
        return !info.getContractReq().equals("")
                && !info.getActReq().equals("")
                && !info.getSumAct().equals("")
                && !info.getSumAct().trim().equalsIgnoreCase("нет")
                && !info.getSumAct().trim().equalsIgnoreCase("отсутсвуют");
    }


    private String findPdfFile(String path, String startNum) throws IOException {

        Stream<Path> filePathStream = Files.walk(Paths.get(path));

        filePathStream
                .filter(Files::isRegularFile)
                .filter(filePath -> filePath.toString().endsWith("pdf"))
                .filter(filePath -> filePath.getFileName().toString().startsWith(startNum + "."))
                .forEach(filePath -> {
                    pdfPath = filePath.toString();
                });

        return pdfPath;
    }
}
