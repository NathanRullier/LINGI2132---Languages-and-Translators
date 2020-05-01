package grammar;



public class Parser {

	//modify this  
	public boolean parse(String[] input) {
		boolean isFinished = false;
		int i = input.length-1;
		while(!isFinished) {
			if(input[i] == "T" ) {
				if(i+1 < input.length && input[i+1] == "E’") {
					    input = removeTheElement(input,i+1);
				    }
				if(i-1 > 0 && input[i-1] == "or"){
				    i--;
				    input = removeTheElement(input,i+1);
				    input[i] = "E’";
				}else{
				    input[i] = "E";					
				}
				i=input.length-1;
			}
			else if(input[i] == "or" && input[i+1] == "T") {
				if(i+2 < input.length && input[i+2] == "E’") {
					input = removeTheElement(input,i+2);
				}
				input = removeTheElement(input,i+1);
				input[i] = "E’";
				i=input.length-1;
			}
			else if(input[i] == "F") {
			    
				if(i+1 < input.length && input[i+1] == "T’") {
					input = removeTheElement(input,i+1);
				}
				if(i-1 > 0 && input[i-1] == "and"){
				    i--;
				    input = removeTheElement(input,i+1);
				    input[i] = "T’";
				}else{
				    input[i] = "T";					
				}				
				i=input.length-1;
			}
			else if(input[i] == "and" && input[i+1] == "F") {
				if(i+2 < input.length && input[i+2] == "T’") {
					input = removeTheElement(input,i+2);
				}
				input = removeTheElement(input,i+1);
				input[i] = "T’";
				i=input.length-1;
			}
			else if(input[i] == "not" && input[i+1] == "F") {
				input = removeTheElement(input,i+1);
				input[i] = "F";
				i=input.length-1;
			}
			else if(input[i]== "(" && input[i+1]== "E" && input[i+2]== ")") {
				input = removeTheElement(input,i+2);
				input = removeTheElement(input,i+1);
				input[i] = "F";
				i=input.length-1;
			}
			else if(input[i] == "id") {
				input[i] = "F";
				i=input.length-1;
			}
			else {
				i--;
			}
			
			if(input.length == 1 && input[0] == "E") {
				return true;
			}else if(i > input.length){
			return false;
			}
			for(int j = 0; j<input.length;j++){
			System.out.print(" " + input[j]);
			}
			System.out.println("");
		}
		return true;
	}
	
	public static String[] removeTheElement(String[] arr, int index) {
		String[] anotherArray = new String[arr.length - 1]; 


			for (int i = 0, k = 0; i < arr.length; i++) { 


				if (i == index) { 
					continue; 
				} 

				anotherArray[k++] = arr[i]; 
			} 
 
			return anotherArray; 
	} 
}
		

		
		

