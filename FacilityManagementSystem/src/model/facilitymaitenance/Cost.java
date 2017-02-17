package model.facilitymaitenance;


public class Cost {
	
	private int costNo;
	private float laborCost;	
	private float materialCost;
	private float total;
	
	public void setCostNo(int costNo){
		this.costNo = costNo;
	}
    
	public int getCostNo(){
		return costNo;
	}
	
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
	    
	public float getTotal(){
		return laborCost + materialCost;
	}
}
