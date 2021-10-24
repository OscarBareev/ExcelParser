import dto.CreditorsOsago.OsagoReadService;
import dto.CreditorsOsago.OsagoWriteKbmService;
import dto.CreditorsOsago.OsagoWriteRDService;
import dto.CreditorsOsago.OsagoWriteService;


import java.io.IOException;





public class Main {
    public static void main(String[] args) throws IOException {



        String fromPath = "D:\\TestDir\\file2.xlsx";
        String toPath = "D:\\TestDir\\Result";
        String pdfPath = "D:\\TestDir\\Creditors";


        OsagoReadService orc = new OsagoReadService();
        orc.parse(fromPath);
        OsagoWriteKbmService ows = new OsagoWriteKbmService();
        ows.run(orc.getOsagoList(), toPath, pdfPath);
    }


}
