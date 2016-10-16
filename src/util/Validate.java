package util;

public class Validate {
	
	public boolean notEmpty(String[] textos){
		boolean band = true;
		for(int i=0; i<textos.length; i++){
			if(textos[i].equals("") || textos[i] == null){
				band = false;
			}
		}
		return band;
	}
	
	public boolean numeroDecimal(String[] textos){
		boolean band = true;
		for(int i=0; i<textos.length; i++){
			if(!textos[i].matches("[0-9]+([,.][0-9]{1,2})?")){
				band = false;
			}
		}
		return band;
	}
	
	public boolean numeroEntero(String[] textos){
		boolean band = true;
		for(int i=0; i<textos.length; i++){
			if(!textos[i].matches("[0-9]*")){
				band = false;
			}
		}
		return band;
	}

}
