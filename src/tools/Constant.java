package tools;

/**
 * @Title: Constant
 * @Description: define global constant
 * @Company: ZhongHe
 * @author dai, ben
 * @date 2013年11月5日
 */
public final class Constant {
	/* baidu LBS */
    public static final String gLbsAk = "AE1f0f9ccf32d1bba22d2bc319cc68e5";
    
	/*image*/
	public static final String IMAGE_WINDOWS_PATH = "\\userimages\\";
	public static final String IMAGE_NORMAL_PATH = "/userimages/";
	public static final String IMAGE_TYPE_GIF = "gif";
	public static final String IMAGE_TYPE_JPG = "jpg";
	public static final String IMAGE_TYPE_JPEG = "jpeg";
	public static final String IMAGE_TYPE_BMP = "bmp";  
	public static final String IMAGE_TYPE_PNG = "png";
    public static final String IMAGE_TYPE_PSD = "psd";   // photoshop
    
    /*image size*/
    //处理过的原始图片，定宽为360px，长宽比不变;
    public static final String ORIGINAL_IMAGE = "_original.jpg";
    public static final int ORIGINAL_IMAGE_WIDTH = 360;
    //要放入360px*200px区域的图片,主要是图文消息的图;
    public static final String STANDARD_IMAGE = "_standard.jpg";
    public static final int STANDARD_IMAGE_WIDTH = 360;
    public static final int STANDARD_IMAGE_HEIGHT = 200;
    //缩略图，形状为正方形，边长为50px;
    public static final String SMALL_IMAGE = "_small.jpg";
    public static final int SAMLL_IMAGE_WIDTH = 50;
    
    /*activity status*/
    public static final int ACTIVITY_DRAFT_STATUS = 1;
    public static final int ACTIVITY_SAVE_STATUS = 2;
    public static final int ACTIVITY_RELEASE_STATUS = 3;
    public static final int ACTIVITY_CLOSED_STATUS = 4;
    
    /*prize status*/
    public static final int PRIZE_ON = 1;
    public static final int PRIZE_OFF = 0;
    
    /*lottery status*/
    public static final int WITH_PRIZE = 1;
    public static final int WITHOUT_PRIZE = 0;
    
    /*random generator*/
    public static final String ALL_CHAR = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    
}
