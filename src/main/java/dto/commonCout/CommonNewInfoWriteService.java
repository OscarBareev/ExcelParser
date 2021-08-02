package dto.commonCout;

import dto.data.CreateTextData;
import dto.data.StringsData;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

// Ходатайство о смене реквезитов для судов общей юрисдикции

public class CommonNewInfoWriteService {

    public void run(List<TransferInfo> infoList) throws IOException {

        String path = "D:\\ideProjects\\parser\\";

        String pathFolder = path + "Смена реквезитов общая юрисдикция\\";
        String pathFolderALl = path + "Смена реквезитов общая юрисдикция (Все документы)\\";

        Files.createDirectory(Path.of(pathFolder));
        Files.createDirectory(Path.of(pathFolderALl));

        StringsData data = new StringsData();

        for (TransferInfo info : infoList) {

            XWPFDocument document = createDoc(data, info); //HERE

            String dirName = info.getCaseNum().replace("/", "_").trim();
            String pathDir = pathFolder + info.getIndex() + " " + dirName + "\\";
            Files.createDirectory(Path.of(pathDir));

            String finalDir = pathDir + " Ходатйство о смене реквезитов в деле № " + dirName + ".docx";

            FileOutputStream withFolders = new FileOutputStream(finalDir);
            document.write(withFolders);
            withFolders.close();

            //For all docs
            FileOutputStream noFolders = new FileOutputStream(pathFolderALl + info.getIndex() + " " + dirName + ".docx");
            document.write(noFolders);
            noFolders.close();


        }
        infoList.clear();
    }

    private XWPFDocument createDoc(StringsData str, TransferInfo info) {

        CreateTextData ctd = new CreateTextData();

        String caseInfo = "В производстве " +
                ctd.rpStringForm(info.getCourtName()) + " находится дело № " +
                info.getCaseNum() + " с участием " +
                str.getLfo() + " в качестве Истца.";


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
        ctd.crtSpanPrf(document);

        ctd.crtMidBoldPrf(document, str.getPetition());
        ctd.crtMidPrf(document, str.changeInfoAbout());
        ctd.crtSpanPrf(document);

        ctd.crtPrf(document, caseInfo);
        ctd.crtPrf(document, str.getDecisionInfo());
        ctd.crtPrf(document, str.changeLawGPK());

        ctd.crtPrf(document, str.getResultInfo());

        ctd.crtSpanPrf(document);
        ctd.crtSpanPrf(document);
        ctd.crtMidBoldPrf(document, str.getAsking());
        ctd.crtSpanPrf(document);
        ctd.crtSpanPrf(document);

        ctd.crtPrf(document, str.changeFirstAsk());
        ctd.crtPrf(document, str.changeSecondAsk());
        ctd.crtSpanPrf(document);
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
