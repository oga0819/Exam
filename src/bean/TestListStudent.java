package bean;

import java.io.Serializable;

public class TestListStudent implements Serializable{

	private String name;
	private String subjectCd;
	private Integer num;
	private Integer point;


	public String getName() {
		return name;
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

	public void setName(String name) {
		this.name = name;
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
