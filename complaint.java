import java.io.Serializable;

@SuppressWarnings("serial")
public class complaint implements Serializable {
    private int cNo;
    private String dept;
    private String comp;
    private String soln;
    private int priority;
    private String type;
    private String email;
    private String address; // Added address field
    private String asset; // Added asset field
    private String assetRequestDetails; // Added field for asset request details

    public complaint(int cNo, String dept, String comp, String soln, int priority, String type, String email, String address, String asset) {
        this.cNo = cNo;
        this.dept = dept;
        this.comp = comp;
        this.soln = soln;
        this.priority = priority;
        this.type = type;
        this.email = email;
        this.address = address;
        this.asset = asset;
        this.assetRequestDetails = ""; // Initializing asset request details to an empty string
    }

    public int getcNo() {
        return cNo;
    }

    public String getDept() {
        return dept;
    }

    public String getComp() {
        return comp;
    }

    public String getSoln() {
        return soln;
    }

    public int getPriority() {
        return priority;
    }

    public String getType() {
        return type;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getAsset() {
        return asset;
    }

    public void setAsset(String asset) {
        this.asset = asset;
    }

    public String getAssetRequestDetails() {
        return assetRequestDetails;
    }

    public void setAssetRequestDetails(String assetRequestDetails) {
        this.assetRequestDetails = assetRequestDetails;
    }

    @Override
    public String toString() {
        return "Request No: " + cNo + "\nDepartment: " + dept + "\nRequest: " + comp +
                "\nSolution: " + soln + "\nPriority: " + priority + "\nType: " + type +
                "\nEmail: " + email + "\nAddress: " + address + "\nAsset: " + asset +
                "\nAsset Request Details: " + assetRequestDetails;
    }
}
