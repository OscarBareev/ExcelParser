
import dto.court.CourtReadService;
import dto.court.CourtWriteService;
import dto.fssp.FsspReadService;
import dto.fssp.FsspWriteService;

import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException {

        FsspReadService frs = new FsspReadService();
        frs.parse();
        FsspWriteService fws = new FsspWriteService();
        fws.run(frs.getDataList());

        CourtReadService crs = new CourtReadService();
        crs.parse();
        CourtWriteService cws = new CourtWriteService();
        cws.run(crs.getDataList());
    }
}
