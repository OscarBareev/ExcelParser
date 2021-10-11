package dto.complaints;

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

public class ComplaintWriteService {

    public void run(List<ComplaintInfo> infoList, String path) throws IOException {
        String pathFolder = path + "\\Претензии\\";
        String pathFolderALl = path + "\\Претензии (Все документы)\\";

        Files.createDirectory(Path.of(pathFolder));
        Files.createDirectory(Path.of(pathFolderALl));

        StringsData data = new StringsData();

        int count = 0;

        for (ComplaintInfo info : infoList) {

            XWPFDocument document;

            if (info.getType().equalsIgnoreCase("регресс")) {
                document = createRegDoc(data, info);
            } else {
                document = createSubDoc(data, info);
            }

            String dirName = info.getGuiltyName()
                    .replace("/", "_").trim()
                    .replace("\"", "")
                    .replace("»", "")
                    .replace("«", "");

            String pathDir = pathFolder + count++ + ". " + dirName + "\\";
            Files.createDirectory(Path.of(pathDir));

            String finalDir = pathDir + "Претензия (" + dirName + ").docx";

            FileOutputStream withFolders = new FileOutputStream(finalDir);
            document.write(withFolders);
            withFolders.close();

            FileOutputStream noFolders = new FileOutputStream(pathFolderALl + count + ". Претензия (" + dirName + ").docx");
            document.write(noFolders);
            noFolders.close();


        }

        infoList.clear();
    }

    private XWPFDocument createRegDoc(StringsData str, ComplaintInfo info) {

        CreateTextData ctd = new CreateTextData();

        String par3 = info.getContractDate() + " года между " + info.getGuiltyName() + " и ООО «НСГ – «РОСЭНЕРГО» был заключен договор обязательного страхования " +
                "гражданской ответственности владельцев транспортных средств № " + info.getContractNum() + ", по которому был застрахован " +
                "риск наступления гражданской ответственности за причинение вреда при использовании автомобиля марки "
                + info.getCarType() + ".";

        String par4 = "В результате Ваших действий было причинен ущерб транспортному средству, " +
                "гражданская ответственность водителей которого бала застрахована в " + info.getCompany() + ".";

        String par6 = "В соответствии с тем, что данное событие было признано страховым случаем, " + info.getCompany() +
                " выплатило потерпевшему страховое возмещение.";

        String par8 = "Исполняя свои обязательства по договору страхования № " + info.getContractNum() +
                " ООО «НСГ – «РОСЭНЕРГО» возместило ущерб " + info.getCompany() + " в размере "
                + info.getDept().replace(".0 ", "") + "  руб.";

        String asking = "На основании изложенного   ООО «НСГ- «РОСЭНЕРГО» просит в десятидневный срок " +
                "(с момента получении претензии) добровольно возместить сумму ущерба в размере " +
                info.getDept().replace(".0 ", "") + " рублей по следующим реквизитам:";


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

        ctd.createSimpleTopForm(
                R0C1,
                info.getGuiltyName(),
                info.getGuiltyAddress()
        );

        ctd.crtSpanPrf(document);
        ctd.crtSpanPrf(document);

        ctd.crtMidBoldPrf(document, "ПРЕТЕНЗИЯ");
        ctd.crtMidPrf(document, "о возмещении ущерба в порядке регресса");
        ctd.crtSpanPrf(document);

        ctd.crtPrf(document, str.getDecisionInfo());
        ctd.crtPrf(document, str.par2());
        ctd.crtPrf(document, par3);
        ctd.crtPrf(document, par4);
        ctd.crtPrf(document, str.par5());
        ctd.crtPrf(document, par6);
        ctd.crtPrf(document, str.par7());
        ctd.crtPrf(document, par8);
        ctd.crtPrf(document, str.par9());
        ctd.crtSpanPrf(document);
        ctd.crtBoldPrf(document, str.reasons1());
        ctd.crtSpanPrf(document);
        ctd.crtBoldPrf(document, str.reasons2());
        ctd.crtSpanPrf(document);
        ctd.crtBoldPrf(document, str.reasons3());
        ctd.crtSpanPrf(document);
        ctd.crtBoldPrf(document, str.reasons4());
        ctd.crtSpanPrf(document);
        ctd.crtBoldPrf(document, str.reasons5());

        ctd.crtSpanPrf(document);

        ctd.crtItalicPrf(document, str.consequence1());
        ctd.crtItalicPrf(document, str.consequence2());
        ctd.crtItalicPrf(document, str.consequence3());
        ctd.crtItalicPrf(document, str.consequence4());

        ctd.crtSpanPrf(document);
        ctd.crtPrf(document, asking);
        ctd.crtSpanPrf(document);


        ctd.crtPrf(document, str.payDataPar3());
        ctd.crtPrf(document, str.payDataPar4());
        ctd.crtPrf(document, str.payDataPar5());
        ctd.crtPrf(document, str.payDataPar6());
        ctd.crtPrf(document, str.payDataPar7());
        ctd.crtPrf(document, str.payDataPar8());
        ctd.crtPrf(document, str.payDataPar9());
        ctd.crtPrf(document, str.payDataPar10());
        ctd.crtPrf(document, str.payDataPar11());

        ctd.crtSpanPrf(document);

        ctd.crtBoldPrf(document, str.getAttachment());

        ctd.crtPrf(document, "1.\t" + str.getDecisionAttach());
        ctd.crtPrf(document, "2.\t" + str.getWarrantAttach());


        return document;
    }

    private XWPFDocument createSubDoc(StringsData str, ComplaintInfo info) {

        CreateTextData ctd = new CreateTextData();


        String changes1 = "Как следует из административных материалов дела, " +
                "ДТП произошло в результате нарушения Вами правил дорожного движения при управлении " +
                "транспортным средством марки " + info.getCarType() + ", государственный регистрационный номер "
                + info.getCarNum() + ".";


        String changes2 = "Автомобиль потерпевшего застрахован по полису добровольного страхования в " +
                "ООО «НСГ- «РОСЭНЕРГО», полис страхования средств наземного транспорта № " + info.getContractNum().trim() + ".";


        String changes3 = "В связи с наступлением страхового события, " +
                "ООО «НСГ- «РОСЭНЕРГО» выплатило потерпевшему ущерб в размере " + info.getDept().replace(".0 ", "") + " руб.";

        String changes4 = "На основании изложенного   ООО «НСГ- «РОСЭНЕРГО» просит в десятидневный срок " +
                "(с момента получении претензии) добровольно возместить сумму ущерба в размере " +
                info.getDept().replace(".0 ", "") + " руб. по следующим реквизитам:";


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

        ctd.createSimpleTopForm(
                R0C1,
                info.getGuiltyName(),
                info.getGuiltyAddress()
        );

        ctd.crtSpanPrf(document);
        ctd.crtSpanPrf(document);

        ctd.crtMidBoldPrf(document, "ПРЕТЕНЗИЯ");
        ctd.crtMidPrf(document, "о возмещении убытков в порядке суброгации");
        ctd.crtSpanPrf(document);

        ctd.crtPrf(document, str.getDecisionInfo());
        ctd.crtPrf(document, str.subPar2());
        ctd.crtPrf(document, changes1);
        ctd.crtPrf(document, changes2);
        ctd.crtPrf(document, changes3);
        ctd.crtPrf(document, str.subPar6());
        ctd.crtPrf(document, changes4);
        ctd.crtSpanPrf(document);
        ctd.crtPrf(document, str.payDataPar3());
        ctd.crtPrf(document, str.payDataPar4());
        ctd.crtPrf(document, str.payDataPar5());
        ctd.crtPrf(document, str.payDataPar6());
        ctd.crtPrf(document, str.payDataPar7());
        ctd.crtPrf(document, str.payDataPar8());
        ctd.crtPrf(document, str.payDataPar9());
        ctd.crtPrf(document, str.payDataPar10());
        ctd.crtPrf(document, str.payDataPar11());

        ctd.crtSpanPrf(document);

        ctd.crtPrf(document, str.sunParLast());

        ctd.crtSpanPrf(document);

        ctd.crtBoldPrf(document, str.getAttachment());

        ctd.crtPrf(document, "1.\t" + str.getDecisionAttach());
        ctd.crtPrf(document, "2.\t" + str.getWarrantAttach());


        return document;
    }
}
