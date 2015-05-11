/**
 * 
 */
package co.edu.unal.common;

import java.util.Random;


/**
 * @author Andres
 *
 */
public class RandomDataGenerator {

	private Random random = new Random();
	
	public String getString(Integer minLength,Integer maxLength){
		int stringLength = getInteger(minLength, maxLength);
		StringBuffer stringBuffer = new StringBuffer(); 				
		while(stringLength-->0){
			char randomChar = (char)getInteger(65, 90);
			stringBuffer.append(randomChar);
		}
		return stringBuffer.toString();
	}
	
	public char getRandomChar(char [] posibleValues){
		int randomIndex = getInteger(0, posibleValues.length-1);
		return posibleValues[randomIndex];
	}
	
	public int getInteger(Integer minValue,Integer maxValue){
	    long range = (long)maxValue - (long)minValue + 1;
	    long fraction = (long)(range * random.nextDouble());
	    int randomNumber =  (int)(fraction + minValue);  
		return randomNumber;
	}
	
	public static void main(String[] args) {
		System.out.println(new RandomDataGenerator().getRandomChar(RandomDataGeneratorConstants.PERSON_GENDER));
	}
	
}