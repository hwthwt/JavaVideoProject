import java.io.BufferedReader;  
import java.io.DataOutputStream;  
import java.io.InputStream;  
import java.io.InputStreamReader;  
import java.io.OutputStream;  
import java.net.HttpURLConnection;  
import java.net.URL;  
  
import org.json.JSONArray;  
import org.json.JSONObject;  
  
public class GetJsonData {  
  
	public static String getJsonData(JSONObject jsonParam,String urls) {  
		StringBuffer sb=new StringBuffer();  
		try {  
			;  
			// ����url��Դ  
			URL url = new URL(urls);  
			// ����http����  
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();  
			// �����������  
			conn.setDoOutput(true);  
						// ������������  
						conn.setDoInput(true);  
					   // ���ò��û���  
					   conn.setUseCaches(false);  
					   // ���ô��ݷ�ʽ  
					   conn.setRequestMethod("POST");  
					   // ����ά�ֳ�����  
						conn.setRequestProperty("Connection", "Keep-Alive");  
					   // �����ļ��ַ���:  
					   conn.setRequestProperty("Charset", "UTF-8");  
					   // ת��Ϊ�ֽ�����  
					   byte[] data = (jsonParam.toString()).getBytes();  
					  // �����ļ�����  
					   conn.setRequestProperty("Content-Length", String.valueOf(data.length));  
					  // �����ļ�����:  
					  conn.setRequestProperty("contentType", "application/json");  
						// ��ʼ��������  
					   conn.connect();        
					OutputStream out = new DataOutputStream(conn.getOutputStream()) ;  
			// д��������ַ���  
			out.write((jsonParam.toString()).getBytes());  
			out.flush();  
			out.close();  
  
			System.out.println(conn.getResponseCode());  
			  
			// ���󷵻ص�״̬  
			if (HttpURLConnection.HTTP_OK == conn.getResponseCode()){  
				System.out.println("���ӳɹ�");  
				// ���󷵻ص�����  
				InputStream in1 = conn.getInputStream();  
				try {  
					  String readLine=new String();  
					  BufferedReader responseReader=new BufferedReader(new InputStreamReader(in1,"UTF-8"));  
					  while((readLine=responseReader.readLine())!=null){  
						sb.append(readLine).append("\n");  
					  }  
					  responseReader.close();  
					  System.out.println(sb.toString());  
					  
				} catch (Exception e1) {  
					e1.printStackTrace();  
				}  
			} else {  
				System.out.println("error++");  
				  
			}  
  
		} catch (Exception e) {  
  
		}  
		  
		return sb.toString();  
  
	}  
	public static void main(String[] args) {  
		JSONObject jsonParam = new JSONObject();  
		jsonParam.put("id", "1401_1406");  
		jsonParam.put("device_size", "480x720");  
		String url="www.baidu.com";  
		String data=GetJsonData.getJsonData(jsonParam,url);  
				//���ص���һ��[{}]��ʽ���ַ���ʱ:                                 
				JSONArray jsonArray = new JSONArray(data);                         
			   //���ص���һ��{}��ʽ���ַ���ʱ:                         
			   /*JSONObject obj= new JSONObject(data);*/        
	}  
}  