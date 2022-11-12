public class IssuesValues {
    private String IssueName;
    private String IssueFormula;
    private int AcceptableLevels;
    private int ConcerningLevels;
    private int DangerousLevels;

    // IssuesValues constructor
    public IssuesValues(String issueName, String issueFormula, int acceptableLevels, int concerningLevels, int dangerousLevels) {
        IssueName = issueName;
        IssueFormula = issueFormula;
        AcceptableLevels = acceptableLevels;
        ConcerningLevels = concerningLevels;
        DangerousLevels = dangerousLevels;
    }

    //region Getters and Setters
    public String getIssueName() {
        return IssueName;
    }

    public void setIssueName(String issueName) {
        IssueName = issueName;
    }

    public String getIssueFormula()
    {
        return IssueFormula;
    }

    public int getAcceptableLevels() {
        return AcceptableLevels;
    }

    public void setAcceptableLevels(int acceptableLevels) {
        AcceptableLevels = acceptableLevels;
    }

    public int getConcerningLevels() {
        return ConcerningLevels;
    }

    public void setConcerningLevels(int concerningLevels) {
        ConcerningLevels = concerningLevels;
    }

    public int getDangerousLevels() {
        return DangerousLevels;
    }

    public void setDangerousLevels(int dangerousLevels) {
        DangerousLevels = dangerousLevels;
    }
    //endregion
}
