package modules;

public class Verify {

    // Variable

    private String mobile;
    private String verification_code;

    // constructor
    public Verify(String mobile, String verification_code) {
        this.mobile = mobile;
        this.verification_code = verification_code;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getVerification_code() {
        return verification_code;
    }

    public void setVerification_code(String verification_code) {
        this.verification_code = verification_code;
    }





}
