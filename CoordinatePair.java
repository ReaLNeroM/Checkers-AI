public class CoordinatePair {
    Integer rowNumber;
    Integer columnNumber;

    public CoordinatePair(String pairString){
    	if('A' <= pairString.charAt(0) && pairString.charAt(0) <= 'Z'){
	        this.rowNumber = Integer.valueOf((int) pairString.charAt(0) - (int) 'A');
    	} else if ('a' <= pairString.charAt(0) && pairString.charAt(0)  <= 'z'){
	        this.rowNumber = Integer.valueOf((int) pairString.charAt(0) - (int) 'a');
    	} else {
    		this.rowNumber = null;
    	}

        this.columnNumber = Integer.valueOf((int) pairString.charAt(1) - (int) '1');
    }

    public CoordinatePair(Integer rowNumber, Integer columnNumber){
        this.rowNumber = rowNumber;
        this.columnNumber = columnNumber;
    }

    public Integer getFirst(){
        return rowNumber;
    }

    public Integer getSecond(){
        return columnNumber;
    }

    public boolean equals(CoordinatePair comparisonCoordinatePair) {
    	return rowNumber == comparisonCoordinatePair.getFirst() &&
    		   columnNumber == comparisonCoordinatePair.getSecond();
    }

    public String toString(String pairString){
        String rowCharacter = Character.toString((int) 'A' + rowNumber);
        String columnCharacter = Character.toString((int) '1' + columnNumber);
        return rowCharacter + columnCharacter;
    }
}