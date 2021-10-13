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
import java.util.List;

public class CreditorWriteService {

    public void run(List<CreditorInfo> infoList, String path) throws IOException {

        String pathFolder = path + "\\Возражения\\";
        String pathFolderALl = path + "\\Возражения (Все документы)\\";

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

            String pathDir = pathFolder + info.getNum().replace(".0", "") + ". " + dirName + "\\";
            Files.createDirectory(Path.of(pathDir));


            int pages = 3;
            if (containsClaim(info)) pages = 4;

            String finalDir = pathDir + "Возражение на требование кредитора (" + dirName + ") на " + pages + " л_.docx";

            FileOutputStream withFolders = new FileOutputStream(finalDir);
            document.write(withFolders);
            withFolders.close();

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
                "и услуг размере в общем размере " + info.getSum() + " руб. в том числе по договору:";

        String creditorPar3 = "- возмездного оказания услуг в сфере страхования " +
                info.getContractReq() + " по актам " +
                info.getActReq() + " на общую сумму " +
                info.getSumAct() + " руб.";

        if (containsClaim(info)) creditorPar3 = creditorPar3 + ";";

        String creditorIfPar4 = "- возмездного оказания услуг по урегулированию убытков " +
                info.getContractClaimReq() + " по актам " +
                info.getActClaimReq() + " на общую сумму " +
                info.getSumClaimAct() + " руб.";

        String creditorPar5 = "Конкурсный управляющий возражает относительно " +
                "включения заявленного требования в реестр требований кредиторов " +
                "Должника в части сумм по:";

        String creditorPar6 = "- договору возмездного оказания услуг в сфере страхования " +
                info.getContractReq() + " по актам " +
                info.getActReq() + " на общую сумму " +
                info.getSumAct() + " руб.";

        if (!containsClaim(info)) creditorPar6 = creditorPar6 + ", руководствуясь следующим.";

        String creditorIfPar7 = "- договору возмездного оказания услуг по урегулированию убытков " +
                info.getContractClaimReq() + " по актам " +
                info.getActClaimReq() + " на общую сумму " +
                info.getSumClaimAct() + " руб.";

        if (containsClaim(info)) creditorIfPar7 = creditorIfPar7 + ", руководствуясь следующим.";

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
        ctd.crtPrf(document, creditorPar3);
        if (containsClaim(info)) ctd.crtPrf(document, creditorIfPar4);
        ctd.crtSpanPrf(document);
        ctd.crtPrf(document, creditorPar5);
        ctd.crtPrf(document, creditorPar6);
        if (containsClaim(info)) ctd.crtPrf(document, creditorIfPar7);
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
        ctd.crtPrf(document, creditorPar18);
        ctd.crtPrf(document, creditorPar19);
        ctd.crtPrf(document, creditorPar20);
        ctd.crtPrf(document, creditorPar21);
        ctd.crtPrf(document, creditorPar22);
        ctd.crtPrf(document, creditorPar23);
        ctd.crtPrf(document, creditorPar24);
        ctd.crtPrf(document, creditorPar25);
        ctd.crtPrf(document, creditorPar26);
        ctd.crtPrf(document, creditorPar27);
        ctd.crtSpanPrf(document);
        ctd.crtPrf(document, creditorPar28);
        ctd.crtPrf(document, creditorPar29);
        ctd.crtSpanPrf(document);
        if (containsClaim(info)) ctd.crtPrf(document, creditorIfPar30);
        if (containsClaim(info)) ctd.crtSpanPrf(document);
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


    private boolean containsClaim(CreditorInfo info) {
        return !info.getContractClaimReq().equals("")
                && !info.getActClaimReq().equals("")
                && !info.getSumClaimAct().equals("")
                && !info.getSumClaimAct().trim().equalsIgnoreCase("нет")
                && !info.getSumClaimAct().trim().equalsIgnoreCase("отсутсвуют");
    }

}
