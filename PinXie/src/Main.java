
import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class Main {
private String content="";
	public static void main(String[] args) {

		String dd="pruvate";
		Main m = new Main();
		int min = 1000;
		String output="";
		try {
			String c=m.getListFile("C:\\Users\\Administrator\\Desktop\\Ӣ��С˵ԭ����ȫ");
			Map<String, Integer> result =findEnglishNum(c);
			Iterator<String> iter = result.keySet().iterator();
			while (iter.hasNext()) {
				String key = iter.next();
				int d = EditDistance.getEditDistance(dd, key);
				if(min>d){
					min=d;
					output=key;
				}
				//System.out.println(dd+"------"+key+"------:"+d);
			}
			System.out.println("input------"+dd+"------output------"+output);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * �ݹ��ȡ�ļ������е��ļ���Ϣ
	 * 
	 * @param path
	 *            �ļ�·��
	 * @return �ļ�ʵ�弯��
	 * @throws IOException
	 */
	public String getListFile(String path) throws IOException {
		// ����Ŀ¼, ���õݹ�ķ���������Ŀ¼
		File file = new File(path);
		if (file.isDirectory()) {// ������ļ��У���ȡ�����е��ļ�
			File[] files = file.listFiles();
			for (File file2 : files) {
				String pathDemo = file2.getPath();
				getListFile(pathDemo);
			}
		} else {// ������ļ����ȡ�ļ�
			String filePath = file.getParent() + "/";
			String fileName = file.getName();
			System.out.println("��ǰ�ļ����ļ�·��Ϊ��" + filePath + ",�ļ���Ϊ��" + fileName);
			// System.out.println("file.getTotalSpace():" +
			// file.getTotalSpace());

			FileInputStream fis = new FileInputStream(file);
			int length;
			String s="";
			byte[] bytes = new byte[1024];
			while ((length = fis.read(bytes)) != -1) {
				s+=new String(bytes, 0, length);// �����ݱ�Ϊ�ַ������
			}
			//System.out.println(content);
			this.content+=s;
			fis.close();// �ر���

		}
		return this.content;
	}

	/**
	 * ͳ�Ƹ������ʳ��ֵĴ���
	 * 
	 * @param text
	 */
	public static Map findEnglishNum(String text) {

		
		// �ҳ����еĵ���
		String[] array = {"\r\n","��","��","��",".", " ", "?", "!" ,"'",":","&","-",";","(",")","\"","`","��","��","��","]","["};
		for (int i = 0; i < array.length; i++) {
			text = text.replace(array[i], ",");
		}
		String[] textArray = text.split(",");

		// ���� ��¼
		Map<String, Integer> map = new HashMap<String, Integer>();
		for (int i = 0; i < textArray.length; i++) {
			String key = textArray[i];
			key = key.trim();
			// תΪСд
			String key_l = key.toLowerCase();
			if (!"".equals(key_l)) {
				Integer num = map.get(key_l);
				if (num == null || num == 0) {
					map.put(key_l, 1);
				} else if (num > 0) {
					map.put(key_l, num + 1);
				}
			}
		}
		
		
		List<Map.Entry<String, Integer>> list =  
	            new LinkedList<Map.Entry<String, Integer>>( map.entrySet() );  
	        Collections.sort( list, new Comparator<Map.Entry<String, Integer>>()  
	        {  
	            public int compare( Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2 )  
	            {  
	                return (o1.getValue()).compareTo( o2.getValue() );  
	            }  
	        } );  
	  
	        Map<String, Integer> result = new LinkedHashMap<String, Integer>();  
	        for (Map.Entry<String, Integer> entry : list)  
	        {  
	            result.put( entry.getKey(), entry.getValue() );  
	        }
	        
	        return result;

//		// ���������̨
//		System.out.println("�������ʳ��ֵ�Ƶ��Ϊ��");
//		Iterator<String> iter = result.keySet().iterator();
//		while (iter.hasNext()) {
//			String key = iter.next();
//			Integer num = result.get(key);
//			System.out.println(key + "\t\t" + num + "��\n-------------------");
//		}
		
		
	}
}
