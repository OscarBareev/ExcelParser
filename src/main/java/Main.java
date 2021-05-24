
import dto.commonCout.CommonCourtReadService;
import dto.commonCout.CommonNewInfoWriteService;
import dto.economyCourt.EconomyCourtReadService;
import dto.economyCourt.EconomyNewInfoWriteService;

import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException {

/*
        FsspReadService frs = new FsspReadService();
        frs.parse();
        FsspWriteService fws = new FsspWriteService();
        fws.run(frs.getDataList());

        EconomyCourtReadService crs = new EconomyCourtReadService();
        crs.parse();
        CourtWriteService cws = new CourtWriteService();
        cws.run(crs.getDataList());

        CommonCourtReadService ctrs = new CommonCourtReadService();
        ctrs.parse();
        CourtTransferWriteService ctws = new CourtTransferWriteService();
        ctws.run(ctrs.getDataList());
*/
 /*       EconomyCourtReadService crs = new EconomyCourtReadService();
        crs.parse();
        EconomyNewInfoWriteService eniws = new EconomyNewInfoWriteService();
        eniws.run(crs.getDataList());
*/

        CommonCourtReadService ctrs = new CommonCourtReadService();
        ctrs.parse();
        CommonNewInfoWriteService cniws = new CommonNewInfoWriteService();
        cniws.run(ctrs.getDataList());

    }
}
