package swing.univ_mng;

public class StudentDao {
/*
 * CREATE TABLE `univ_mng`.`student` (
  `std_idx` INT NOT NULL AUTO_INCREMENT COMMENT '학생 일렬번호',
  `std_code` VARCHAR(45) NOT NULL COMMENT '학번',
  `std_name` VARCHAR(45) NOT NULL COMMENT '학생이름',
  `std_jumin` VARCHAR(45) NOT NULL COMMENT '주민번호',
  `std_addr` VARCHAR(99) NOT NULL COMMENT '',
  `std_entrance_date` VARCHAR(45) NOT NULL COMMENT '입학년도\n',
  `std_major_code` VARCHAR(45) NOT NULL COMMENT '전공코드',
  `std_prof_code` VARCHAR(45) NOT NULL COMMENT '지도교수 코드',
  PRIMARY KEY (`std_idx`)  COMMENT '')
COMMENT = '학생등록 테이블';

 * */
	
	private int std_idx;
	private String std_code;
	private String std_name;
	private String std_jumin;
	private String std_addr;
	private String std_entrance_date;
	private String std_major_code;
	private String std_prof_code;
	
	
	public int getStd_idx() {
		return std_idx;
	}
	public void setStd_idx(int std_idx) {
		this.std_idx = std_idx;
	}
	public String getStd_code() {
		return std_code;
	}
	public void setStd_code(String std_code) {
		this.std_code = std_code;
	}
	public String getStd_name() {
		return std_name;
	}
	public void setStd_name(String std_name) {
		this.std_name = std_name;
	}
	public String getStd_jumin() {
		return std_jumin;
	}
	public void setStd_jumin(String std_jumin) {
		this.std_jumin = std_jumin;
	}
	public String getStd_addr() {
		return std_addr;
	}
	public void setStd_addr(String std_addr) {
		this.std_addr = std_addr;
	}
	public String getStd_entrance_date() {
		return std_entrance_date;
	}
	public void setStd_entrance_date(String std_entrance_date) {
		this.std_entrance_date = std_entrance_date;
	}
	public String getStd_major_code() {
		return std_major_code;
	}
	public void setStd_major_code(String std_major_code) {
		this.std_major_code = std_major_code;
	}
	public String getStd_prof_code() {
		return std_prof_code;
	}
	public void setStd_prof_code(String std_prof_code) {
		this.std_prof_code = std_prof_code;
	}
	
	
}
