
import dto.court.CourtReadService;
import dto.court.CourtWriteService;
import dto.fssp.FsspReadService;
import dto.fssp.FsspWriteService;
import dto.transferCout.CourtTransferReadService;
import dto.transferCout.CourtTransferWriteService;
import dto.transferCout.TransferInfo;

import java.io.IOException;
import java.util.List;


public class Main {
    public static void main(String[] args) throws IOException {

/*        FsspReadService frs = new FsspReadService();
        frs.parse();
        FsspWriteService fws = new FsspWriteService();
        fws.run(frs.getDataList());

        CourtReadService crs = new CourtReadService();
        crs.parse();
        CourtWriteService cws = new CourtWriteService();
        cws.run(crs.getDataList());*/

        CourtTransferReadService ctrs = new CourtTransferReadService();
        ctrs.parse();
        CourtTransferWriteService ctws = new CourtTransferWriteService();
        ctws.run(ctrs.getDataList());

    }
}
