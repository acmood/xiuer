package facePosition;

public class Face {
	public Position position; 
	public Attribute attribute;
	public String img_id ;
	public String face_id;
	public Position getPosition() {
		return position;
	}
	public void setPosition(Position position) {
		this.position = position;
	}
	public Attribute getAttribute() {
		return attribute;
	}
	public void setAttribute(Attribute attribute) {
		this.attribute = attribute;
	}
	public String getImg_id() {
		return img_id;
	}
	public void setImg_id(String img_id) {
		this.img_id = img_id;
	}
	public String getFace_id() {
		return face_id;
	}
	public void setFace_id(String face_id) {
		this.face_id = face_id;
	}
	
}

class Position{
	private cooperation center;
	public cooperation getCenter() {
		return center;
	}
	public void setCenter(cooperation center) {
		this.center = center;
	}
	public cooperation getEye_left() {
		return eye_left;
	}
	public void setEye_left(cooperation eye_left) {
		this.eye_left = eye_left;
	}
	public cooperation getEye_right() {
		return eye_right;
	}
	public void setEye_right(cooperation eye_right) {
		this.eye_right = eye_right;
	}
	public float getHeight() {
		return height;
	}
	public void setHeight(float height) {
		this.height = height;
	}
	public cooperation getMouth_left() {
		return mouth_left;
	}
	public void setMouth_left(cooperation mouth_left) {
		this.mouth_left = mouth_left;
	}
	public cooperation getMouth_right() {
		return mouth_right;
	}
	public void setMouth_right(cooperation mouth_right) {
		this.mouth_right = mouth_right;
	}
	public cooperation getNose() {
		return nose;
	}
	public void setNose(cooperation nose) {
		this.nose = nose;
	}
	public float getWidth() {
		return width;
	}
	public void setWidth(float width) {
		this.width = width;
	}
	private cooperation eye_left;
	private cooperation eye_right;
	private float height;
	private cooperation mouth_left;
	private cooperation mouth_right;
	private cooperation nose;
	private float width;	
}

class cooperation{
	public float x, y;
	cooperation(int _x, int _y){
		x = _x; y = _y;
	}
	public float getX() {
		return x;
	}
	public void setX(float x) {
		this.x = x;
	}
	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y = y;
	}
}

class Attribute{
	public Gender gender;
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public Age getAge() {
		return age;
	}
	public void setAge(Age age) {
		this.age = age;
	}
	public Race getRace() {
		return race;
	}
	public void setRace(Race race) {
		this.race = race;
	}
	public Smiling getSmiling() {
		return smiling;
	}
	public void setSmiling(Smiling smiling) {
		this.smiling = smiling;
	}
	public Glass getGlass() {
		return glass;
	}
	public void setGlass(Glass glass) {
		this.glass = glass;
	}
	public Age age;
	public Race race;
	public Smiling smiling;
	public Glass glass;
}

class Gender{
	public int value;
	public int confidence;
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public int getConfidence() {
		return confidence;
	}
	public void setConfidence(int confidence) {
		this.confidence = confidence;
	}
}

class Age{
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public int getRange() {
		return range;
	}
	public void setRange(int range) {
		this.range = range;
	}
	public int value;
	public int range;
}

class Race{
	public int value;
	public int confidence;
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public int getConfidence() {
		return confidence;
	}
	public void setConfidence(int confidence) {
		this.confidence = confidence;
	}
}

class Smiling{
	public int value;
	public int confidence;
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public int getConfidence() {
		return confidence;
	}
	public void setConfidence(int confidence) {
		this.confidence = confidence;
	}
}

class Glass{
	public int value;
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public int getConfidence() {
		return confidence;
	}
	public void setConfidence(int confidence) {
		this.confidence = confidence;
	}
	public int confidence;
}
