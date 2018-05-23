public class Person{

	protected String name;
	protected String hairLength;
	protected String gender;
	protected String eyeColour;
	protected String headWear;
	protected String hairColour;
	protected String origin;
	protected String ageGroup;
	protected boolean isDown;
	
	public Person(String name, String hairLength, String gender, String eyeColour, String headWear, String hairColour, String origin, String ageGroup){

		// Attributes for the character;
		this.name = name;
		this.hairLength = hairLength;
		this.gender = gender;
		this.eyeColour = eyeColour;
		this.headWear = headWear;
		this.hairColour = hairColour;
		this.origin = origin;
		this.ageGroup = ageGroup;
	
		// Variables to check if down or up;
		boolean isDown = false;
	}

        public boolean setDown(){
		if(isDown == false){
			isDown = true;
		}

		return true;
	}

	public String get(String attribute){

		switch(attribute){

			case "name":
				return name;
			case "hairLength":
				return hairLength;
			case "gender":
				return gender;
			case "eyeColour":
				return eyeColour;
			case "headWear":
				return headWear;
			case "hairColour":
				return hairColour;
			case "origin":
				return origin;
			case "ageGroup":
				return ageGroup;
		}

		return "Unknown";
	}

	public boolean isDown(){
		return isDown;
	}

}
