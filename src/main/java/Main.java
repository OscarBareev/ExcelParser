import dto.fssp.FsspInfo;
import dto.fssp.FsspReadService;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {


        FsspReadService frs = new FsspReadService();

        frs.parse();

        List<FsspInfo> list = frs.getDataList();

        System.out.println(list.get(76));


    }
}
