package tools;

public class GetSpecificImage {
public String getOringnalImagePath(String imageID){
	return imageID + Constant.ORIGINAL_IMAGE;
}
public String getStandardImagePath(String imageID){
	return imageID + Constant.STANDARD_IMAGE;
}
public String getSmallImagePath(String imageID){
	return imageID + Constant.SMALL_IMAGE;
}
}
