package bean;

import java.io.Serializable;

public class TestListStudent implements Serializable{

	private String subjectName;
	private String subjectCd;
	private Integer num;
	private Integer point;


	public String getSubjectName() {
		return subjectName;
	}

	public String getSubjectCd() {
		return subjectCd;
	}

	public Integer getNum() {
		return num;
	}

	public Integer getPoint() {
		return point;
	}

	public void setSubjectName(String name) {
		this.subjectName = name;
	}

	public void setSubjectCd(String subjectCd) {
		this.subjectCd = subjectCd;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public void setPoint(Integer point) {
		this.point = point;
	}

}
