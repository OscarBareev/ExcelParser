import dto.complaints.ComplaintReadService;
import dto.complaints.ComplaintWriteService;
import dto.creditors.CreditorReadService;
import dto.creditors.CreditorWriteService;

import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException {

        String fromPath = "D:\\TestDir\\0_Список кредиторов .xlsx";
        String toPath = "D:\\TestDir";

        CreditorReadService crs = new CreditorReadService();
        crs.parse(fromPath);
        CreditorWriteService cws = new CreditorWriteService();
        cws.run(crs.getCreditorList(), toPath);





/*        ComplaintReadService crs = new ComplaintReadService();
        crs.parse(fromPath);
        ComplaintWriteService cws = new ComplaintWriteService();
        cws.run(crs.getComplaintList(), toPath);*/


/*
        FsspReadService frs = new FsspReadService();
        frs.parse();
        FsspWriteService fws = new FsspWriteService();
        fws.run(frs.getDataList());
*/




/*        String path = "D:\\ideProjects\\parser\\Письма";
        String pathAtt = path + "\\Attachments";

        try (Stream<Path> filePathStream = Files.walk(Paths.get(path))) {
            filePathStream.forEach(filePath -> {


                if (Files.isRegularFile(filePath)) {

                    if (filePath.toString().endsWith("msg")) {


                        MAPIMessage msg = null;


                        try {
                            msg = new MAPIMessage(filePath.toString());


                            AttachmentChunks[] attachments = msg.getAttachmentFiles();

                            if (!Files.exists(Path.of(pathAtt))) {
                                Files.createDirectory(Path.of(pathAtt));
                            }

                            int number = countFiles(pathAtt);

                            for (AttachmentChunks attachment : attachments) {

                                number++;

                                FileOutputStream fileOutputStream =
                                        new FileOutputStream(new File(pathAtt + "\\" + number + ".html"));
                                ObjectOutputStream objectOutputStream =
                                        new ObjectOutputStream(fileOutputStream);
                                objectOutputStream.writeObject(attachment.getEmbeddedAttachmentObject());
                                objectOutputStream.close();
                                fileOutputStream.close();

                            }

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }*/




/*      FsspReadService frs = new FsspReadService();
        frs.parse();
        FsspWriteService fws = new FsspWriteService();
        fws.run(frs.getDataList());

        EconomyCourtReadService crs = new EconomyCourtReadService();
        crs.parse();
        EconomyCourtWriteService cws = new EconomyCourtWriteService();
        cws.run(crs.getDataList());

        CommonCourtReadService ctrs = new CommonCourtReadService();
        ctrs.parse();
        CourtTransferWriteService ctws = new CourtTransferWriteService();
        ctws.run(ctrs.getDataList());

        EconomyCourtReadService crs = new EconomyCourtReadService();
        crs.parse();
        EconomyNewInfoWriteService eniws = new EconomyNewInfoWriteService();
        eniws.run(crs.getDataList());


        CommonCourtReadService ctrs = new CommonCourtReadService();
        ctrs.parse();
        CommonNewInfoWriteService cniws = new CommonNewInfoWriteService();
        cniws.run(ctrs.getDataList());
        */

    }

/*
    public static int countFiles(String path) throws IOException {
        Stream<Path> files = Files.list(Paths.get(path));
        return (int) files.count();
    }*/

}
