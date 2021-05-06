package dto.fssp;

public final class StringsData {




  /*  private String toWho = info.getFsspDepartment();
    private String toWhoAddress = info.getFsspAdress();
    private String ipNumber = info.getIpNumber();


    //MAIN TEXT

    private String caseInfo = "В производстве " + info.getFsspDepartment() + " находится исполнительное производство № " + info.getIpNumber() +
            ", возбужденное на основании исполнительного документа " + info.getIpDocument() + " о взыскании с ООО «НСГ – «РОСЭНЕРГО» " +
            "суммы в размере " + info.getDebtSumm() + " руб.";

    private String firstAsk = "1.   Окончить исполнительное производство № " + info.getIpNumber() + ";";
    private String secondAsd = "2. Исполнительный документ " + info.getIpDocument() +
            " направить в адрес конкурсного управляющего ООО «НСГ – «РОСЭНЕРГО» по адресу: 127994, г. Москва, ГСП-4.";
*/

    //GETTERS


    public String getGovCorp() {
        return "  ГОСУДАРСТВЕННАЯ КОРПОРАЦИЯ  ";
    }

    public String getAgency() {
        return " АГЕНСТВО ";
    }

    public String getInsur() {
        return " ПО СТРАХОВАНИЮ ВКЛАДОВ ";
    }

    public String getManager() {
        return " КОНКУРСНЫЙ УПРАВЛЯЮЩИЙ ";
    }

    public String getLfo() {
        return "ООО «НСГ – «РОСЭНЕРГО»";
    }

    public String getAsvAddress() {
        return "127994, г. Москва, ГСП-4";
    }

    public String getPhnNmb() {
        return "тел. +7 (495) 725-31-29";
    }

    public String getDateAndNmb() {
        return "от____________№____________";
    }

    public String getDebtor() {
        return "Должник:";
    }

    public String getOgrn() {
        return "(ОГРН: 1020400754285,";
    }

    public String getInn() {
        return "ИНН: 0411063374, КПП: 041101001)";
    }

    public String getLfoAddress1() {
        return "649000, Республика Алтай, г. Горно-Алтайск,";
    }

    public String getLfoAddress2() {
        return "Коммунистический пр-т, д. 9, оф. 1";
    }

    public String getInFace1() {
        return "в лице конкурсного управляющего";
    }

    public String getInFace2() {
        return "государственной корпорации «Агентство по";
    }

    public String getInFace3() {
        return "страхованию вкладов»";
    }

    public String getMailAddress() {
        return "Адрес для направления корреспонденции:";
    }

    public String getIpNumberTxt() {
        return "Номер ИП:";
    }

    public String getPetition() {
        return "ХОДАТАЙСТВО";
    }

    public String getPettAbout() {
        return "об окончании исполнительного производства";
    }

    public String getDecisionInfo() {
        return "Решением Арбитражного суда Республики Алтай от ХХ.ХХ.2021 " +
                "по делу № А02-211/2021 (резолютивная часть которого объявлена ХХ.ХХ.2021) ООО «НСГ – «РОСЭНЕРГО» " +
                "признано несостоятельным (банкротом), в отношении него открыто конкурсное производство, " +
                "полномочия конкурсного управляющего возложены на " +
                "государственную корпорацию «Агентство по страхованию вкладов».";
    }

    public String getLawInfo() {
        return "Согласно п.п. 7 п. 1 ст.47 Федерального закона от 02.10.2007 № 229-ФЗ " +
                "«Об исполнительном производстве», исполнительное производство оканчивается судебным приставом-исполнителем " +
                "в случае признания должника банкротом и направления исполнительного документа арбитражному управляющему.";
    }

    public String getResultInfo() {
        return "Исходя из вышеизложенного,";
    }

    public String getAsking() {
        return "ПРОШУ:";
    }

    public String getAttachment() {
        return "Приложения:";
    }

    public String getFirstAttach() {
        return "1. Копия решения АС Республики Алтай от ХХ.ХХ.2021 по делу № А02-211/2021;";
    }

    public String getSecondAttach() {
        return "2. Копия доверенности представителя.";
    }

    public String getSignData1() {
        return "Главный юрисконсульт";
    }

    public String getSignData2() {
        return "Первого отдела ПССР ДПС                                     " +
                "                                            И.А. Богданов";
    }
}