package entities;

public class ExamDetails {
    private int exam_Id;
    private int detail_Id;
    private String exam_name;
    private String detail_question;
    private String picture_url;

    public int getExam_Id() {
        return exam_Id;
    }

    public void setExam_Id(int exam_Id) {
        this.exam_Id = exam_Id;
    }

    public int getDetail_Id() {
        return detail_Id;
    }

    public void setDetail_Id(int detail_Id) {
        this.detail_Id = detail_Id;
    }

    public String getExam_name() {
        return exam_name;
    }

    public void setExam_name(String exam_name) {
        this.exam_name = exam_name;
    }

    public String getDetail_question() {
        return detail_question;
    }

    public void setDetail_question(String detail_question) {
        this.detail_question = detail_question;
    }

    public String getPicture_url() {
        return picture_url;
    }

    public void setPicture_url(String picture_url) {
        this.picture_url = picture_url;
    }
}
