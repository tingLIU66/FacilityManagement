package model.facilitymaitenance;

public class FacilityProblem {

	private int problemtypeNo;
	private String problemTypen;
	
	public void setProblemTypeNo(int problemtypeNo){
		this.problemtypeNo = problemtypeNo;
	}
    
	public int getProblemTypeNo(){
		return problemtypeNo;
	}
	
	public void setProblemType(String problemTypen){
		this.problemTypen = problemTypen;
	}
    
	public String getProblemType(){
		return problemTypen;
	}

}
