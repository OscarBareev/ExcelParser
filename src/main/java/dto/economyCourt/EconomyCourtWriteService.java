package dto.economyCourt;

import dto.data.CreateTextData;
import dto.data.StringsData;
import org.apache.poi.xwpf.usermodel.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;


public class EconomyCourtWriteService {

    public void run(List<CourtInfo> infoList) throws IOException {

        String path = "D:\\ideProjects\\parser\\";

        String pathFolder = path + "ЛФО-Ответчик\\";
        String pathFolderALl = path + "ЛФО-Ответчик (Все документы)\\";

        Files.createDirectory(Path.of(pathFolder));
        Files.createDirectory(Path.of(pathFolderALl));

        StringsData data = new StringsData();

        for (CourtInfo info : infoList) {

            XWPFDocument document = createDoc(data, info); //HERE

            String dirName = info.getCaseNum().replace("/", "_").trim();
            String pathDir = pathFolder + info.getIndex() + " " + dirName + "\\";
            Files.createDirectory(Path.of(pathDir));

            String finalDir = pathDir + " Ходатйство об оставлении без рассмотрения дела № " + dirName + ".docx";

            FileOutputStream withFolders = new FileOutputStream(finalDir);
            document.write(withFolders);
            withFolders.close();

            //For all docs
            FileOutputStream noFolders = new FileOutputStream(pathFolderALl + info.getIndex() +" " + dirName + ".docx");
            document.write(noFolders);
            noFolders.close();
        }

        infoList.clear();
    }

    private XWPFDocument createDoc(StringsData str, CourtInfo info) {

        CreateTextData ctd = new CreateTextData();

        String caseInfo = "В производстве " +
                info.getCourtName().replace("Арбитражный суд", "Арбитражного суда") + " находится дело № " +
                info.getCaseNum() + " (Истец: " +
                info.getClaimerName() + ", Ответчик: " +
                str.getLfo() + ", категория: " +
                info.getCaseType() + ").";

        String firstAsk = "1.\tРассмотреть вопрос о наличии оснований для оставления дела № " +
                info.getCaseNum() + " (Истец: " +
                info.getClaimerName() + ", Ответчик: " +
                str.getLfo() + ", категория: " +
                info.getCaseType() + ") без рассмотрения.";


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

        ctd.createEconomyTopForm(
                R0C1,
                info.getCourtName(),
                info.getCourtAddress(),
                info.getClaimerName(),
                info.getClaimerOGRN(),
                info.getClaimerINN(),
                info.getCaseNum(),
                info.getJudge()
        );


        ctd.crtSpanPrf(document);
        ctd.crtSpanPrf(document);

        ctd.crtMidBoldPrf(document, str.getPetition());
        ctd.crtMidPrf(document, str.courtNameTitle());
        ctd.crtSpanPrf(document);

        ctd.crtPrf(document, caseInfo);
        ctd.crtPrf(document, str.getDecisionInfo());
        ctd.crtPrf(document, str.courtPar3());
        ctd.crtPrf(document, str.courtPar4());
        ctd.crtPrf(document, str.courtPar5());
        ctd.crtPrf(document, str.courtPar6());
        ctd.crtPrf(document, str.courtPar7());
        ctd.crtPrf(document, str.courtPar8());
        ctd.crtPrf(document, str.getResultInfo());


        ctd.crtSpanPrf(document);
        ctd.crtMidBoldPrf(document, str.getAsking());
        ctd.crtSpanPrf(document);

        ctd.crtPrf(document, firstAsk);
        ctd.crtPrf(document, str.courtSecondAsk());
        ctd.crtSpanPrf(document);

        ctd.crtBoldPrf(document, str.getAttachment());
        ctd.crtPrf(document, "1.\t" + str.getFirstActAttach());
        ctd.crtPrf(document, "2.\t" + str.getDecisionAttach());
        ctd.crtPrf(document, "3.\t" + str.getWarrantAttach());
        ctd.crtPrf(document, "4.\t" + str.getDiplomaAttach());

        ctd.crtSpanPrf(document);
        ctd.crtSpanPrf(document);

        ctd.noRedLinePrf(document, str.getSignData1());
        ctd.noRedLinePrf(document, str.getSignData2());
        ctd.noRedLinePrf(document, str.getSignData3());

        return document;
    }
}
