package modules;

public class Register {

    // Variables

    private String password;
    private String password_confirmation;
    private String favorite_language;
    private String responsible_person;
    private String name;
    private boolean is_accept_terms_and_conditions;
    private String mobile;
    private String email;
    private String success;
    private String status_code;
    private String message;


    // constructors

    public Register(String password,String password_confirmation,String favorite_language, String responsible_person,
                    String name, boolean is_accept_terms_and_conditions,String mobile, String email)
    {

        this.password_confirmation = password_confirmation;
        this.favorite_language = favorite_language;
        this.responsible_person = responsible_person;
        this.name = name;
        this.is_accept_terms_and_conditions = is_accept_terms_and_conditions;
        this.mobile = mobile;
        this.email = email;
        this.password = password;
    }


   // Setters And Getters

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword_confirmation() {
        return password_confirmation;
    }

    public void setPassword_confirmation(String password_confirmation) {
        this.password_confirmation = password_confirmation;
    }

    public String getFavorite_language() {
        return favorite_language;
    }

    public void setFavorite_language(String favorite_language) {
        this.favorite_language = favorite_language;
    }

    public String getResponsible_person() {
        return responsible_person;
    }

    public void setResponsible_person(String responsible_person) {
        this.responsible_person = responsible_person;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isIs_accept_terms_and_conditions() {
        return is_accept_terms_and_conditions;
    }

    public void setIs_accept_terms_and_conditions(boolean is_accept_terms_and_conditions) {
        this.is_accept_terms_and_conditions = is_accept_terms_and_conditions;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getStatus_code() {
        return status_code;
    }

    public void setStatus_code(String status_code) {
        this.status_code = status_code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }



}
