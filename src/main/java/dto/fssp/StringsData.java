package dto.fssp;

public final class StringsData {


    private FsspInfo info;

    public StringsData(FsspInfo info) {
        this.info = info;
    }


    private final String toWho = info.getFsspDepartment();;
    private final String toWhoAddress = info.getFsspAdress();
    private final String ipNumber = info.getIpNumber();


    //MAIN TEXT

    private final String caseInfo = "В производстве " + info.getFsspDepartment() + " находится исполнительное производство № " + info.getIpNumber() +
            ", возбужденное на основании исполнительного документа " + info.getIpDocument() + " о взыскании с ООО «НСГ – «РОСЭНЕРГО» " +
            "суммы в размере " + info.getDebtSumm() + " руб.";

    private final String firstAsk = "1.   Окончить исполнительное производство № " + info.getIpNumber() + ";";
    private final String secondAsd = "2. Исполнительный документ " + info.getIpDocument() +
            " направить в адрес конкурсного управляющего ООО «НСГ – «РОСЭНЕРГО» по адресу: 127994, г. Москва, ГСП-4.";


    //GETTERS

    public FsspInfo getInfo() {
        return info;
    }

    public String getGovCorp() {
        String govCorp = "  ГОСУДАРСТВЕННАЯ КОРПОРАЦИЯ  ";
        return govCorp;
    }

    public String getAgency() {
        String agency = " АГЕНСТВО ";
        return agency;
    }

    public String getInsur() {
        String insur = " ПО СТРАХОВАНИЮ ВКЛАДОВ ";
        return insur;
    }

    public String getManager() {
        String manager = " КОНКУРСНЫЙ УПРАВЛЯЮЩИЙ ";
        return manager;
    }

    public String getLfo() {
        String lfo = "ООО «НСГ – «РОСЭНЕРГО»";
        return lfo;
    }

    public String getAsvAddress() {
        String asvAddress = " 127994, г. Москва, ГСП-4 ";
        return asvAddress;
    }

    public String getPhnNmb() {
        String phnNmb = " тел. +7 (495) 725-31-29 ";
        return phnNmb;
    }

    public String getDateAndNmb() {
        String dateAndNmb = " от____________№____________ ";
        return dateAndNmb;
    }

    public String getToWho() {
        return toWho;
    }

    public String getToWhoAddress() {
        return toWhoAddress;
    }

    public String getDebtor() {
        String debtor = "Должник:";
        return debtor;
    }

    public String getOgrn() {
        String ogrn = "(ОГРН: 1020400754285,";
        return ogrn;
    }

    public String getInn() {
        String inn = "ИНН: 0411063374, КПП: 041101001)";
        return inn;
    }

    public String getLfoAdress1() {
        String lfoAdress1 = "649000, Республика Алтай, г. Горно-Алтайск,";
        return lfoAdress1;
    }

    public String getLfoAdress2() {
        String lfoAdress2 = "Коммунистический пр-т, д. 9, оф. 1";
        return lfoAdress2;
    }

    public String getInFace1() {
        String inFace1 = "в лице конкурсного управляющего";
        return inFace1;
    }

    public String getInFace2() {
        String inFace2 = "государственной корпорации «Агентство по";
        return inFace2;
    }

    public String getInFace3() {
        String inFace3 = "страхованию вкладов»";
        return inFace3;
    }

    public String getMailAdress() {
        String mailAdress = "Адрес для направления корреспонденции:";
        return mailAdress;
    }

    public String getIpNumberTxt() {
        String ipNumberTxt = "Номер ИП:";
        return ipNumberTxt;
    }

    public String getIpNumber() {
        return ipNumber;
    }

    public String getPetition() {
        String petition = "ХОДАТАЙСТВО";
        return petition;
    }

    public String getPettAbout() {
        String pettAbout = "об окончании исполнительного производства";
        return pettAbout;
    }

    public String getCaseInfo() {
        return caseInfo;
    }

    public String getDecisionInfo() {
        String decisionInfo = "Решением Арбитражного суда Республики Алтай от ХХ.ХХ.2021 " +
                "по делу № А02-211/2021 (резолютивная часть которого объявлена ХХ.ХХ.2021) ООО «НСГ – «РОСЭНЕРГО» " +
                "признано несостоятельным (банкротом), в отношении него открыто конкурсное производство, " +
                "полномочия конкурсного управляющего возложены на " +
                "государственную корпорацию «Агентство по страхованию вкладов».";
        return decisionInfo;
    }

    public String getLawInfo() {
        String lawInfo = "Согласно п.п. 7 п. 1 ст.47 Федерального закона от 02.10.2007 № 229-ФЗ " +
                "«Об исполнительном производстве», исполнительное производство оканчивается судебным приставом-исполнителем " +
                "в случае признания должника банкротом и направления исполнительного документа арбитражному управляющему.";
        return lawInfo;
    }

    public String getResultInfo() {
        String resultInfo = "Исходя из вышеизложенного,";
        return resultInfo;
    }

    public String getAsking() {
        String asking = "ПРОШУ:";
        return asking;
    }

    public String getFirstAsk() {
        return firstAsk;
    }

    public String getSecondAsd() {
        return secondAsd;
    }

    public String getAttachment() {
        String attachment = "Приложения:";
        return attachment;
    }

    public String getFirstAttach() {
        String firstAttach = "1. Копия решения АС Республики Алтай от ХХ.ХХ.2021 по делу № А02-211/2021;";
        return firstAttach;
    }

    public String getSecondAttach() {
        String secondAttach = "2. Копия доверенности представителя.";
        return secondAttach;
    }

    public String getSignData1() {
        String signData1 = "Главный юрисконсульт";
        return signData1;
    }

    public String getSignData2() {
        String signData2 = "Первого отдела ПССР ДПС                     " +
                "                                             " +
                "               И.А. Богданов";
        return signData2;
    }
}