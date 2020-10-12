package vu8;
/**
 * This class uses for managing Inventory items
 * @author Nam Vu
 */
public class Inventory {
    private String id, name;
    private int qoh, rop;
    private double sellPrice;
    /**
     * This is the default constructor
     */
    public Inventory(){
        setId("ABC-1234");
        setName("New Item");
        setQoh(10);
        setRop(25);
        setSellPrice(0);
    }
    /**
     * This is the constructor with a given id, name and sell price
     */
    public Inventory(String id, String name, double sellPrice){
        setId(id);
        setName(name);
        setSellPrice(sellPrice);
    }
    /**
     * This is the constructor with a given id, name, quantity on hand, 
     * re-order point and sell price
     */
    public Inventory(String id, String name, int qoh, int rop, double sellPrice){
        setId(id);
        setName(name);
        setQoh(qoh);
        setRop(rop);
        setSellPrice(sellPrice);
    }
    /**
     * This is an accessor method for the id
     * @return id
     */
    public String getId(){
        return id;
    }
    /**
     * This is an accessor method for the name
     * @return name
     */
    public String getName(){
        return name;
    }
    /**
     * This is an accessor method for the quantity on hand
     * @return qoh
     */
    public int getQoh(){
        return qoh;
    }
    /**
     * This is an accessor method for the re-order point
     * @return rop
     */
    public int getRop(){
        return rop;
    }
    /**
     * This is an accessor method for the sell price
     * @return sellPrice
     */
    public double getSellPrice(){
        return sellPrice;
    }
    
    /**
     * This is an mutator method for the id 
     * @param id
     */
    public void setId(String id){
        boolean isDigit = true;
        String error = "Error: Inventory ID must be in the form ABC-1234";
        if(id.length()!=8)
            throw new IllegalArgumentException(error);
        for(int dex=4; dex<8; dex++){
            if(!(Character.isDigit(id.charAt(dex)))){
                isDigit = false;
                throw new IllegalArgumentException(error);
            }
        }
        boolean isLetter = true;
        if(isDigit){
            for(int dex=0; dex<3; dex++){
                if(!(Character.isAlphabetic(id.charAt(dex)))){
                    isLetter = false;
                    throw new IllegalArgumentException(error);
                }
            }
            if(isLetter){
                this.id= id;
            }
        }
    }
    /**
     * This is an mutator method for the name
     * @param name
     */
    public void setName(String name){
        if(name==null || name.trim().length()==0) throw new IllegalArgumentException("Error: you must enter an item name.");
        else
            this.name = name;
    }
    /**
     * This is an mutator method for the quantity on hand
     * @param qoh
     */
    public void setQoh(int qoh){
        if(qoh>=0)
            this.qoh = qoh;
        else
            throw new IllegalArgumentException("Error: QOH must be 0 or more.");
    }
    /**
     * This is an mutator method for the re-order point 
     * @param rop
     */
    public void setRop(int rop){
        if(rop>=0)
            this.rop = rop;
        else
            throw new IllegalArgumentException("ROP must be greater than 0.");
    }
    /**
     * This is an mutator method for the sell price
     * @param sellPrice
     */
    public void setSellPrice(double sellPrice){
        if(sellPrice>=0)
            this.sellPrice = sellPrice;
        else
            throw new IllegalArgumentException("Error: Selling price must be greater than 0.");
    }
    
    @Override
    public String toString(){
        return String.format(id+" ("+ name +"), QOH: "+qoh+" Price: $"+sellPrice);
    }
}
