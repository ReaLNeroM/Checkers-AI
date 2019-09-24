public class CoordinatePair {
    Integer rowNumber;
    Integer columnNumber;

    public CoordinatePair(CoordinatePair coordinatePair){
        rowNumber = coordinatePair.getRowNumber();
        columnNumber = coordinatePair.getColumnNumber();
    }

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

    public Integer getRowNumber(){
        return rowNumber;
    }

    public Integer getColumnNumber(){
        return columnNumber;
    }

    public boolean equals(CoordinatePair comparisonCoordinatePair) {
        return rowNumber == comparisonCoordinatePair.getRowNumber() &&
               columnNumber == comparisonCoordinatePair.getColumnNumber();
    }

    @Override
    public String toString(){
        Character rowCharacter = Character.valueOf((char) ((int) 'A' + rowNumber));
        Character columnCharacter = Character.valueOf((char) ((int) '1' + columnNumber));
        return rowCharacter.toString() + columnCharacter.toString();
    }
}