package employee.model.vo;

public class Dept implements java.io.Serializable{
	private static final long serialVersionUID = 111L;

	private String deptId;	//부서코드
	private String deptName;		//부서명
/*	private String deptOriginId;		//참조원 부서코드
	private int deptLevel;		//부서레벨
*/	private String fax;	//팩스번호
	
	public Dept() {}

	public Dept(String deptId, String deptName, String fax) {
		super();
		this.deptId = deptId;
		this.deptName = deptName;
		this.fax = fax;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Dept [deptId=" + deptId + ", deptName=" + deptName + ", fax=" + fax + "]";
	}


	
	
	
	
	
}
