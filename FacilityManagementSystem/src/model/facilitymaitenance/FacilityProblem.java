package model.facilitymaitenance;

public class FacilityProblem {

	private int problemtypeNo;
	private String problemType;
	
	
	
	public FacilityProblem(int problemtypeNo, String problemType) {
		this.problemtypeNo = problemtypeNo;
		this.problemType = problemType;
	}

	public void setProblemTypeNo(int problemtypeNo){
		this.problemtypeNo = problemtypeNo;
	}
    
	public int getProblemTypeNo(){
		return problemtypeNo;
	}
	
	public void setProblemType(String problemTypen){
		this.problemType = problemType;
	}
    
	public String getProblemType(){
		return problemType;
	}

}
