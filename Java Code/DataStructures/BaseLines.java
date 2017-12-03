package DataStructures;

public class BaseLines {
	String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getValue() {
		return value;
	}

	public void setValue(float value) {
		this.value = value;
	}

	float value;

	public BaseLines(String name, float value) {
		this.value = value;
		this.name = name;
	}

}
