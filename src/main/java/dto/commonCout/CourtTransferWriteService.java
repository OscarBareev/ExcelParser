package dto.commonCout;

import dto.data.CreateTextData;
import dto.data.StringsData;
import org.apache.poi.xwpf.usermodel.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;


//Ходатйство о передаче по подсудности дела СОЮ

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
            FileOutputStream noFolders = new FileOutputStream(pathFolderALl + info.getIndex() + " Ходатйство о передаче по подсудности дела № " + dirName + ".docx");
            document.write(noFolders);
            noFolders.close();
        }

        infoList.clear();
    }


    private XWPFDocument createDoc(StringsData str, TransferInfo info) {

        CreateTextData ctd = new CreateTextData();

        String caseInfo = "В производстве " +
                ctd.rpStringForm(info.getCourtName()) + " находится дело № " +
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
        ctd.createFormedTable(table);

        XWPFTableRow row0 = table.getRow(0);
        XWPFTableCell R0C0 = row0.getCell(0);
        XWPFTableCell R0C1 = row0.createCell();
        XWPFTableCell R0C2 = row0.createCell();

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

        ctd.createTopForm(
                R0C2,
                info.getCourtName(),
                info.getCourtAddress(),
                info.getClaimerName(),
                info.getCaseNum()
        );


        ctd.crtSpanPrf(document);
        ctd.crtSpanPrf(document);

        ctd.crtMidBoldPrf(document, str.getPetition());
        ctd.crtMidPrf(document, str.sendToAnotherCourt());
        ctd.crtSpanPrf(document);

        ctd.crtPrf(document, caseInfo);
        ctd.crtPrf(document, str.getDecisionInfo());
        ctd.crtPrf(document, str.transferPart3());
        ctd.crtPrf(document, str.transferPart4());
        ctd.crtPrf(document, str.transferPart5());
        ctd.crtPrf(document, str.transferPart6());
        ctd.crtPrf(document, str.transferPart7());
        ctd.crtPrf(document, str.getResultInfo());


        ctd.crtSpanPrf(document);
        ctd.crtMidBoldPrf(document, str.getAsking());
        ctd.crtSpanPrf(document);

        ctd.crtPrf(document, firstAsk);
        ctd.crtPrf(document, str.transferSecondAsk());
        ctd.crtPrf(document, str.transferThirdAsk());
        ctd.crtSpanPrf(document);

        ctd.crtBoldPrf(document, str.getAttachment());

        ctd.crtPrf(document, "1.\t" + str.getDecisionAttach());
        ctd.crtPrf(document, "2.\t" + str.getWarrantAttach());
        ctd.crtPrf(document, "3.\t" + str.getDiplomaAttach());

        ctd.crtSpanPrf(document);
        ctd.crtSpanPrf(document);

        ctd.noRedLinePrf(document, str.getSignData1());
        ctd.noRedLinePrf(document, str.getSignData2());
        ctd.noRedLinePrf(document, str.getSignData3());

        return document;
    }
}
