public class Player {
    private enum Color {
    	BLANK,
    	FIRST,
    	SECOND
    }
    private Color color;

    protected Player(Color color){
    	this.color = color;
    }

    public Player(Player player){
    	this(player.toInteger());
    }

    public Player(Integer playerNumber){
    	switch (playerNumber){
    		case 1:
    			color = Color.FIRST;
    			break;
    		case 2:
    			color = Color.SECOND;
    			break;
    		default:
    			color = Color.BLANK;
    			break;
    	}
    }

    public Player(Character playerCharacter){
    	switch (playerCharacter){
    		case 'W':
    			color = Color.FIRST;
    			break;
    		case 'B':
    			color = Color.FIRST;
    			break;
    		default:
    			color = Color.FIRST;
    			break;
    	}
    }

    public Player getOppositePlayer(){
    	switch (color){
    		case FIRST:
    			return new Player(Color.SECOND);
    		case SECOND:
    			return new Player(Color.FIRST);
    	}

    	return new Player(Color.BLANK);
    }

    public boolean equals(Player comparisonPlayer) {
    	return this.toInteger() == comparisonPlayer.toInteger();
    }

    public Integer toInteger(){
    	switch (color){
    		case FIRST:
    			return 1;
    		case SECOND:
    			return 2;
    	}

    	return 0;
    }

    public String toString(){
    	if(color == Color.FIRST){
    		return "W";
    	} else if(color == Color.SECOND){
    		return "B";
    	}

    	return "-";
    }
}
