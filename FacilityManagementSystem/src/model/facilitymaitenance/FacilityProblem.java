package model.facilitymaitenance;

public class FacilityProblem implements FProblemIntf{

	private int problemtypeNo;
	private String problemType;
	

	public void setProblemTypeNo(int problemtypeNo){
		this.problemtypeNo = problemtypeNo;
	}
    
	public int getProblemTypeNo(){
		return problemtypeNo;
	}
	
	public void setProblemType(String problemType){
		this.problemType = problemType;
	}
    
	public String getProblemType(){
		return problemType;
	}

}
