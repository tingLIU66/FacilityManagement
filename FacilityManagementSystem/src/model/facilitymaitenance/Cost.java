package model.facilitymaitenance;


public class Cost implements CostIntf{
	
	//private int costNo;
	private float laborCost;	
	private float materialCost;
	private float total;


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
