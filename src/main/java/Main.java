
import dto.commonCout.CommonCourtReadService;
import dto.commonCout.CommonNewInfoWriteService;
import dto.commonCout.CourtTransferWriteService;
import dto.economyCourt.EconomyCourtReadService;
import dto.economyCourt.EconomyCourtWriteService;
import dto.economyCourt.EconomyNewInfoWriteService;
import dto.fssp.FsspReadService;
import dto.fssp.FsspWriteService;
import org.apache.poi.hsmf.MAPIMessage;
import org.apache.poi.hsmf.datatypes.AttachmentChunks;
import org.apache.poi.hsmf.datatypes.StringChunk;
import org.apache.poi.hsmf.exceptions.ChunkNotFoundException;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.IllegalFormatCodePointException;
import java.util.Objects;
import java.util.stream.Stream;


public class Main {
    public static void main(String[] args) throws IOException {


        EconomyCourtReadService crs = new EconomyCourtReadService();
        crs.parse();
        EconomyNewInfoWriteService eniws = new EconomyNewInfoWriteService();
        eniws.run(crs.getDataList());




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
        cniws.run(ctrs.getDataList());*/

    }


    public static int countFiles(String path) throws IOException {
        Stream<Path> files = Files.list(Paths.get(path));
        return (int) files.count();
    }

}
