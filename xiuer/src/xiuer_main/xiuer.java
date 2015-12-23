package xiuer_main;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.json.JSONException;
import org.json.JSONObject;

import com.facepp.error.FaceppParseException;
import com.facepp.http.HttpRequests;
import com.facepp.http.PostParameters;

public class xiuer {

	private static String mApiKey = "5c8438fc56cb846dde0396bb664405bb";
	private static String mApiSecret = "FSJJpAR2Z75kzZCJyYk8Z-9RH5HKu9mY";
	static HttpRequests httpRequest = new HttpRequests(mApiKey, mApiSecret);
	static PostParameters postParamenters = new PostParameters();

	public static void createPerson(String name) throws FaceppParseException {

		postParamenters = new PostParameters();
		postParamenters.setPersonName(name);
		JSONObject result = httpRequest.personCreate(postParamenters);
		System.out.println(result);

	}

	public static void addPicture() throws FaceppParseException, JSONException {
		File file = new File("//home//zstuacm//llj//face//xiuer//img//training");
		File[] fileLst = file.listFiles();

		System.out.println(fileLst.length);
		for (int i = 0; i < fileLst.length; i++) {
			File tmpFile = fileLst[i];
			if (tmpFile.isHidden()) {
				continue;
			}
			System.out.println(tmpFile.getAbsolutePath());
			postParamenters = new PostParameters();
			postParamenters.setImg(tmpFile);
			postParamenters.setTag("yyx");
			JSONObject result = httpRequest.detectionDetect(postParamenters);
			System.out.println(result);
			int arraysize = result.getJSONArray("face").length();
			System.out.println(arraysize);
			for (int j = 0; j < arraysize; j++) {
				String faceId = result.getJSONArray("face").getJSONObject(j)
						.getString("face_id");
				System.out.println(faceId);

				postParamenters = new PostParameters();
				postParamenters.setPersonName("yyx");
				postParamenters.setFaceId(faceId + "");
				JSONObject result1 = httpRequest.personAddFace(postParamenters);
				System.out.println(result1);
			}
		}
	}

	public static void createGroup(String name) throws FaceppParseException {
		httpRequest.groupCreate(new PostParameters().setGroupName(name));
	}

	public static void addPerson(String groupName) throws FaceppParseException,
			IOException {

		// group/add_person
		System.out.println("\ngroup/add_person");
		ArrayList<String> personList = new ArrayList<String>();
		personList.add("yyx");

		new PostParameters().setGroupName("group_0").setPersonName(personList)
				.getMultiPart().writeTo(System.out);
		System.out.println(httpRequest.groupAddPerson(new PostParameters()
				.setGroupName(groupName).setPersonName(personList)));
	}

	public static void training(String groupName) throws FaceppParseException,
			JSONException {
		// recognition/train
		JSONObject syncRet = null;

		System.out.println("\ntrain/Identify");
		syncRet = httpRequest.trainIdentify(new PostParameters()
				.setGroupName(groupName));
		System.out.println(syncRet);
		System.out.println(httpRequest.getSessionSync(syncRet
				.getString("session_id")));

		System.out.println("\ntrain/verify");
		// for (int i = 0; i < result.getJSONArray("face").length(); ++i) {
		syncRet = httpRequest.trainVerify(new PostParameters()
				.setPersonName("yyx"));
		// System.out.println(httpRequest.getSessionSync(syncRet.get("session_id").toString()));
		// }
	}

	public static boolean recognition_verify(String personName,
			JSONObject result) throws FaceppParseException, JSONException {
		// recognition/verify
		System.out.println("\nrecognition/verify");
		JSONObject ret = httpRequest.recognitionVerify(new PostParameters()
				.setPersonName(personName).setFaceId(
						result.getString("face_id")));
		System.out.println(ret);
		return ret.getBoolean("is_same_person");
	}

	public static void startRecognition() throws FaceppParseException,
			JSONException {
		File file = new File("//home//zstuacm//llj//face//xiuer//img//testing");
		File[] fileLst = file.listFiles();

		System.out.println(fileLst.length);
		System.out.println("this is recognition");
		for (int i = 0; i < fileLst.length; i++) {
			File tmpFile = fileLst[i];
			if (tmpFile.isHidden()) {
				continue;
			}
			System.out.println(tmpFile.getAbsolutePath());
			postParamenters = new PostParameters();
			postParamenters.setImg(tmpFile);
			JSONObject result = httpRequest.detectionDetect(postParamenters);
			System.out.println(result);
			int arraysize = result.getJSONArray("face").length();
			for (int j = 0; j < arraysize; j++) {
				JSONObject face = result.getJSONArray("face").getJSONObject(j);
				recognition_verify("yyx", face);
			}
		}
	}

	public static void solveImg(JSONObject picRes, List<JSONObject> facelist, File file, String picOutPos) throws JSONException{
		//Face now = new Face();
		//now.getFace_id(face.getJSONObject("face_id"));
		Coordination img = new Coordination(picRes.getDouble("img_width"),picRes.getDouble("img_height"));
        try {
            Image src = ImageIO.read(file);//open file
            BufferedImage image = new BufferedImage((int)img.x, (int)img.y,
                    BufferedImage.TYPE_INT_RGB);
            Graphics2D g = image.createGraphics();
            g.drawImage(src, 0, 0, (int)img.x, (int)img.y, null);
            g.setColor(Color.RED);
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,
                    0.5f));
            String textWord = "我是秀儿";
            g.setFont(new Font("宋体", Font.BOLD, 30));
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,
                    0.5f));
            for(int i = 0; i < facelist.size(); i ++){
            	JSONObject face =  facelist.get(i);
            	Coordination center = new Coordination(face.getJSONObject("position").getJSONObject("center").getDouble("x"), face.getJSONObject("position").getJSONObject("center").getDouble("y"));
            	Coordination yyx = new Coordination(face.getJSONObject("position").getDouble("width"), face.getJSONObject("position").getDouble("height"));
            	int age = face.getJSONObject("attribute").getJSONObject("age").getInt("value");
		
            	Coordination lefttop = new Coordination(img.x*0.01*(center.x-yyx.x*0.5),img.y*0.01*(center.y-yyx.y*0.5)), leftbottom = new Coordination(img.x*0.01*(center.x-yyx.x*0.5),img.y*0.01*(center.y+yyx.y*0.5));
            	Coordination righttop = new Coordination(img.x*0.01*(center.x+yyx.x*0.5),img.y*0.01*(center.y-yyx.y*0.5)), rightbottom = new Coordination(img.x*0.01*(center.x+yyx.x*0.5),img.y*0.01*(center.y+yyx.y*0.5));
            	System.out.println(lefttop.x + "~~~~"+ lefttop.y );
            
            // 在指定坐标绘制水印文字
            	g.drawRect((int)lefttop.x,(int) lefttop.y, (int)(yyx.x*0.01*img.x), (int)(yyx.y*0.01*img.y));
            	g.drawString(textWord+age+"岁", (float)leftbottom.x-30, (float)leftbottom.y+30f);
            }
            g.dispose();
            ImageIO.write((BufferedImage) image, "JPEG", new File(picOutPos));// 输出到文件流
        } catch (Exception e) {
            e.printStackTrace();
        }
		
		
		
		//ImageUtils.pressText("我是秀儿",picName,"/home/zstuacm/llj/face/xiuer/img/abc_pressText.jpg","宋体",Font.BOLD,Color.white, 40, -100, -100, 0.9f);//测试OK
	}

	public static void picProcess(String picName, String picOutPos)
			throws FaceppParseException, JSONException {
		File tmpFile = new File(picName);

		System.out.println(tmpFile.getAbsolutePath());
		postParamenters = new PostParameters();
		postParamenters.setImg(tmpFile);
		JSONObject result = httpRequest.detectionDetect(postParamenters);
		System.out.println(result);
		int arraysize = result.getJSONArray("face").length();
		int cnt = 0;
		List<JSONObject> ret = new ArrayList<JSONObject>(); 
		for (int j = 0; j < arraysize; j++) {
			JSONObject face = result.getJSONArray("face").getJSONObject(j);
			boolean flag = recognition_verify("yyx", face);
			if (flag){
				ret.add(face);
			}
		}
		if (ret.size() != 0) {
			solveImg(result, ret, tmpFile, picOutPos);
			System.out.println("there has xiuer\n");
		} else {
			System.out.println("there has no xiuer\n");
		}

	}

	public static String getPos(String text) {
		int len = text.length();
		int pos = 0;
		String ret = "";
		for (int i = 0; i < len; i++) {
			if (text.charAt(i) == '/') {
				pos = i;
			}
		}
		for (int i = 0; i <= pos; i++) {
			ret = ret + text.charAt(i);
		}
		for (int i = pos + 1; i < len; i++) {
			if (text.charAt(i) == '.')
				break;
			ret = ret + text.charAt(i);
		}
		ret = ret + "ok.jpg";
		return ret;
	}

	public static void main(String[] args) {
		try {

			 //postParamenters.setImg(new File("/img"));
			// createPerson("yyx");
			// addPicture();
			// createGroup("yyx");
		//	 addPerson("yyx");
			// training("yyx");
			// startRecognition();

			String picAddress = "/home/zstuacm/llj/face/xiuer/img/mmexport1450582273085.jpg";
			String picOutPos = getPos(picAddress);
			picProcess(picAddress, picOutPos);
			picAddress = "/home/zstuacm/llj/face/xiuer/img/mmexport1450582569785.jpg";
			picOutPos = getPos(picAddress);
			picProcess(picAddress, picOutPos);
			picAddress = "/home/zstuacm/llj/face/xiuer/img/yyx002.jpg";
			picOutPos = getPos(picAddress);
			picProcess(picAddress, picOutPos);
		/*
			//group/delete
			System.out.println("\ngroup/delete");
			System.out.println(httpRequest.groupDelete(new PostParameters().setGroupName("yyx")));
	
			//person/delete
			System.out.println("\nperson/delete");
			System.out.println(httpRequest.personDelete(new PostParameters().setPersonName("yyx")));	
		*/	
		} catch (FaceppParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
}

class Coordination {
	double x, y;

	public Coordination(double _x, double _y) {
		x = _x;
		y = _y;
		// TODO Auto-generated constructor stub
	}
}