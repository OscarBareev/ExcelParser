import dto.fssp.FsspReadService;
import dto.fssp.FsspWriteService;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {


        FsspReadService frs = new FsspReadService();
        frs.parse();

        FsspWriteService fws = new FsspWriteService();
        fws.run(frs.getDataList());
    }
}
