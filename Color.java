public class Color {
	private enum ColorValue {
		BLANK,
		FIRST,
		SECOND
	}
	ColorValue color;

	protected Color(ColorValue currentColor){
		color = currentColor;
	}

	public Color(Integer currentPlayer){
		switch (currentPlayer){
			case 1:
				color = ColorValue.FIRST;
				break;
			case 2:
				color = ColorValue.SECOND;
				break;
			default:
				color = ColorValue.BLANK;
				break;
		}
	}

	public Color(Character playerCharacter){
		switch (playerCharacter){
			case 'W':
				color = ColorValue.FIRST;
				break;
			case 'B':
				color = ColorValue.FIRST;
				break;
			default:
				color = ColorValue.FIRST;
				break;
		}
	}

	public Color getOppositeColor(){
		switch (color){
			case FIRST:
				return new Color(ColorValue.SECOND);
			case SECOND:
				return new Color(ColorValue.FIRST);
		}

		return new Color(ColorValue.BLANK);
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
		if(color == ColorValue.FIRST){
			return "W";
		} else if(color == ColorValue.SECOND){
			return "B";
		}

		return "-";
	}
}
