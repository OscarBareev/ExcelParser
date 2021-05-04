import dto.fssp.FsspReadService;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        System.out.println("Hello 123");


        FsspReadService parserService = new FsspReadService();
        parserService.parse();
    }
}
