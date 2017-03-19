package model.facilitymaitenance;


public class Cost {
	
	//private int costNo;
	private float laborCost;	
	private float materialCost;
	private float total;
	
	
	
	
	public Cost(float laborCost, float materialCost) {
		super();
		//this.costNo = costNo;
		this.laborCost = laborCost;
		this.materialCost = materialCost;
		this.total = laborCost + materialCost;
	}

	//public void setCostNo(int costNo){
		//this.costNo = costNo;
	//}
    
	//public int getCostNo(){
		//return costNo;
	//}
	
	public void setLaborCost(float laborCost){
		this.laborCost = laborCost;
	}
    
	public float getLaborCost(){
		return laborCost;
	}

	
	public void setMaterialCost(float materialCost){
		this.materialCost = materialCost;
	}
    
	public float getMaterialCost(){
		return materialCost;
	}
	
	public void setTotal(){
		this.total = laborCost + materialCost;
	}
	    
	public float getTotal(){
		return total;
	}
}
