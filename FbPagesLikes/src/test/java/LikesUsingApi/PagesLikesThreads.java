package LikesUsingApi;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;


public class PagesLikesThreads extends JFrame {
	
static PagesLikesThreads StarMedia;
static DefaultTableModel modelap;
static String path;
static JButton ImportAccounts;
static File accountpath;
static JLabel lblOptions;
static JScrollPane scrollPane;
static JTable table;
static JLabel successLabel;
static JLabel failureLabel;
static JButton startBtn;
static JLabel TotalAccounts;
static  String[][] splitArrays ;
static JLabel dots;
static int badnum=0;
static int rownum=0;;
static int goodnum=0;
static int dataCounter=0;

private Thread myThread;
private static int THREAD_COUNT = 0; 
private static int accountsnum = 0; 
static boolean startBoolean=false;
 
 


public PagesLikesThreads() {
 
		try {path = new File(".").getCanonicalPath();} catch (IOException e1) {}
		
  setTitle("FB Likes Using APIS ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

  
		JPanel mainpanel = new JPanel();
		mainpanel.setBackground(new Color(0xd9dcd6));
		mainpanel.setLayout(null);
		
	 getContentPane().add(mainpanel);
	 
 

		setIconImage(Toolkit.getDefaultToolkit().getImage(path+"\\Config\\fb.png"));
		


		modelap=new DefaultTableModel();
 Object[] row11121 = new Object[0]; 
 Object[] column12111 = {"Num","Emails","Password","Status"};
 modelap.setColumnIdentifiers(column12111);
    
 

  scrollPane = new JScrollPane();
  scrollPane.setBounds(22, 49, 264, 151);
  mainpanel.add(scrollPane);
  
  table = new JTable();
  scrollPane.setColumnHeaderView(table);
  
 scrollPane.setViewportView(table);		
 
  table.getTableHeader().setReorderingAllowed(false);
  table.getTableHeader().setResizingAllowed(false);
  table.setModel(modelap);
  
   successLabel = new JLabel();
   successLabel.setBounds(164, 230, 132, 33);
   mainpanel.add(successLabel);
   successLabel.setText("Success: 0");
   successLabel.setForeground(new Color(0x16425b));
   successLabel.setFont(new Font("Dialog", Font.BOLD, 16));
   
    failureLabel = new JLabel();
    failureLabel.setBounds(22, 230, 137, 33);
    mainpanel.add(failureLabel);
    failureLabel.setText("Failure: 0");
    failureLabel.setForeground(new Color(0x16425b));
    failureLabel.setFont(new Font("Dialog", Font.BOLD, 16));
    
    TotalAccounts = new JLabel();
    TotalAccounts.setBounds(22, 200, 197, 22);
    mainpanel.add(TotalAccounts);
    TotalAccounts.setText("Total Accounts: 0");
    TotalAccounts.setForeground(new Color(0x16425b));
    TotalAccounts.setFont(new Font("Dialog", Font.BOLD, 14));
    
    
    JLabel Title = new JLabel();
    Title.setText("FB Likes Using APIS ");
    Title.setForeground(new Color(0x16425b));
    Title.setFont(new Font("Dialog", Font.BOLD, 16));
    Title.setBounds(80, 11, 174, 33);
    mainpanel.add(Title);
    
    JPanel panel = new JPanel();
    panel.setBounds(309, 28, 327, 220);
    mainpanel.add(panel);
    panel.setLayout(null);
    panel.setBackground(new Color(0x16425b));

 
  lblOptions = new JLabel();
  lblOptions.setBounds(150, 11, 69, 33);
  panel.add(lblOptions);
  lblOptions.setText("Options");
  lblOptions.setForeground(new Color(255, 255, 255));
  lblOptions.setFont(new Font("Dialog", Font.BOLD, 16));
  
  dots = new JLabel(".........................................");
  dots.setBounds(96, 11, 163, 53);
  panel.add(dots);
  dots.setBackground(new Color(0x344955));
  dots.setForeground(new Color(255, 255, 255));
  
      
      dots.setFont(new Font("Tahoma", Font.BOLD, 18));
      
      ImportAccounts = new JButton("Accounts");
      ImportAccounts.setBounds(105, 65, 154, 30);
      panel.add(ImportAccounts);
      ImportAccounts.setFont(new Font("Tahoma", Font.PLAIN, 14));
      
  
 	
      
 startBtn = new JButton("Start");
 startBtn.setBounds(121, 167, 114, 30);
 panel.add(startBtn);
 startBtn.setForeground(new Color(0, 128, 0));
 startBtn.setBackground(Color.LIGHT_GRAY);
 startBtn.addActionListener(new ActionListener() {
 	public void actionPerformed(ActionEvent e) {
 startThread();
 	}
 });
 
 startBtn.setFont(new Font("Tahoma", Font.BOLD, 18));
 
 postId = new JTextField();
 postId.setBounds(89, 106, 201, 33);
 panel.add(postId);
 postId.setColumns(10);
 
 lblPageId = new JLabel();
 lblPageId.setText("Post ID :");
 lblPageId.setForeground(Color.WHITE);
 lblPageId.setFont(new Font("Dialog", Font.BOLD, 16));
 lblPageId.setBounds(10, 106, 69, 33);
 panel.add(lblPageId);
      ImportAccounts.setVisible(true);
      ImportAccounts.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
			JFileChooser filechooser = new JFileChooser();
			filechooser.setFileFilter(new FileNameExtensionFilter("Text Files", "txt"));

			int response = filechooser.showOpenDialog(null);
			if(response == JFileChooser.APPROVE_OPTION) {
				 accountpath = new File(filechooser.getSelectedFile().getAbsolutePath());
				 Scanner myFile = null;
 	 			String accpath=""+accountpath;
 	 			try {
 	 				Scanner filexz= new Scanner(new File(accpath),"UTF-8");
 	 				myFile = filexz;
 	 			
 	 			} catch(FileNotFoundException e1) {
 	 				System.out.println("Your file does not exist.");
 	 			}
 	 		int ny=1;
 	 			while(myFile.hasNextLine()) {
 	 				String curline = myFile.nextLine();
 	 		
 	 			try {  String [] inputData=curline.split(":", 2);	 				
		    	  String[] zzz = {"       "+ny,inputData[0],inputData[1]};
		    	  modelap.addRow(zzz);
		    	  ny++;} catch(Exception e1) {}
		    	  
		    	  
 	 				int size= curline.length();
 	 				boolean foundDiv = true;
 	 				boolean FoundChar = false;
 	 				for(int i = 0; i < size; i++) {
 	 					if(curline.charAt(i) == ' ') {
 	 					} else {
 	 						FoundChar=true;
 	 					}
 	 					if(FoundChar && foundDiv) {
 	 						foundDiv = false;
 	 					}
 	 				}
 	 				accountsnum++;
 	 				
 	 			}
 	 			myFile.close();

 		 		 TotalAccounts.setText("Total Accounts: "+accountsnum);
			}
		
			if(response == JFileChooser.CANCEL_OPTION) {
				JOptionPane.showMessageDialog(null, "please add accounts");
			}
		} 
      	});
    
    
  


 

}
	
  private void startThread() {
  startBtn.setEnabled(false);
  THREAD_COUNT=10;
 
 if (startBoolean == false) {
 myThread = new Thread(new MyRunnable(10));
 myThread.start();
 }
 startBoolean=true;

 
  }
	
 
 	static File dir = null;
 
 public static  List<FacebookLoginThread> threads = new ArrayList<>();
 public static  String[][] fullnumbersThread ;
 public static int perthread;
  
  private class MyRunnable implements Runnable {
 private int numThreads;

 public MyRunnable(int numThreads) {
  this.numThreads = numThreads;
 }

 public void run() {
 	  
  

  

BufferedReader file = null;
try {
	try {
		file = new BufferedReader(new InputStreamReader( new FileInputStream(accountpath), "utf-8"));
	} catch (UnsupportedEncodingException e) {
		e.printStackTrace();
	}
} catch (FileNotFoundException e) {
	e.printStackTrace();
}


@SuppressWarnings("resource")
BufferedReader br=new BufferedReader(file);
String line; 
 
String fileData[]=new String[100000];

try {
	while ((line= br.readLine()) !=null) {
		
		fileData[dataCounter]=line;
		dataCounter++;
	}
} catch (IOException e) {}


String totalData[]=new String[dataCounter];

for(int z=0;z<dataCounter;z++) {
	totalData[z]=fileData[z];
}




 numThreads = 10;

 perthread=dataCounter/numThreads;

splitArrays = new String[numThreads][perthread];

int dataPerThread=0;
 
for(int z=0;z<numThreads;z++) {
	for(int y=0;y<perthread;y++) {
		splitArrays[z][y]=totalData[dataPerThread];
		dataPerThread++;
 
	}
	
}

  
 	DateTimeFormatter thisMonthPattern = DateTimeFormatter.ofPattern("MM");  
 	LocalDateTime thisMonth = LocalDateTime.now(); 
 	DateTimeFormatter thisDayPattern = DateTimeFormatter.ofPattern("dd");  
 	LocalDateTime thisDay = LocalDateTime.now();
 	DateTimeFormatter thisYearPattern = DateTimeFormatter.ofPattern("yy");  
 	LocalDateTime thisYear = LocalDateTime.now();
 	String month=""+thisMonthPattern.format(thisMonth);
 	String day=""+thisDayPattern.format(thisDay);
 	String year=""+thisYearPattern.format(thisYear);

 
 	//create folder for accounts
   dir = new File(path+"\\FB.Actions\\"+day+"-"+month+"-"+year);
  if( ! dir.exists( ) ) {  
 dir.mkdirs( ); 
 } 
  	
 
 
 
 // Create and start the threads
 for (int i = 0; i < numThreads; i++) {
 

try { FacebookLoginThread thread = new FacebookLoginThread();
  threads.add(thread);
  thread.start();
  } catch (Exception ev12) {}

try {
 	Thread.sleep(200);
 } catch (InterruptedException e) {
 }

  
 }
  
 
  
 // Wait for all the threads to finish
 for (FacebookLoginThread thread : threads) {
  try {
 thread.join();
  } catch (InterruptedException e) {
 e.printStackTrace();
  }
 }
 
 
  
 }  
 
  }
 
 
  
 public static  int threadcounter=0;
 public static JTextField postId;
 private JLabel lblPageId;
 private static class FacebookLoginThread extends Thread {
 

  
  
 @SuppressWarnings("deprecation")
		public void run() {
 
  String postID=postId.getText();
  
   //give each thread a unique number
  	int thisthreadnum=threadcounter;
 	threadcounter++;

    
 if(thisthreadnum==1)
  {
}
  else 
  {

  try {
 	Thread.sleep(2000);
 } catch (InterruptedException e) {}
  }
  

int usedDataPerThread=0;
   
 try {	
 //mainloop
 while(usedDataPerThread<=perthread){
	 
	 int currentRow=rownum;
	 
	rownum++;
	try {
		table.updateUI();
		table.changeSelection(rownum, 1, false, false);
	}catch(Exception ev){} 

	  

String [] inputData = null;
String cookies="";
	
try {inputData= splitArrays[thisthreadnum][usedDataPerThread].split(":",3); }catch(Exception ev){} 
try {cookies=inputData[2]; }catch(Exception ev){} 
usedDataPerThread++;

 

 

        Map<String, String> cookieMap = parseCookies(cookies);

        String cUser = cookieMap.getOrDefault("c_user", "Not Found");
        String xs = cookieMap.getOrDefault("xs", "Not Found");
        String presence = cookieMap.getOrDefault("presence", "Not Found");
        String sb = cookieMap.getOrDefault("sb", "Not Found");
        String fr = cookieMap.getOrDefault("fr", "Not Found");
        
        
        
        if(xs.equalsIgnoreCase("Not Found")) {xs="";}
        if(presence.equalsIgnoreCase("Not Found")) {presence="";}
        if(sb.equalsIgnoreCase("Not Found")) {sb="";}
        if(fr.equalsIgnoreCase("Not Found")) {fr="";}
		
        String fb_dtsg= "", __user = "", lsd= "", hs= "", accountId = "";

        
        try {
        CloseableHttpClient httpClient = HttpClients.custom().build();

        String initialGetUrl = "https://business.facebook.com/business_locations";
        HttpGet httpGet = new HttpGet(initialGetUrl);
        httpGet.addHeader("user-agent", "Mozilla/5.0 (Linux; Android 8.1.0; MI 8 Build/OPM1.171019.011) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.86 Mobile Safari/537.36");
        httpGet.addHeader("referer", "https://adsmanager.facebook.com/adsmanager");
        httpGet.addHeader("cookie", "c_user=" + cUser + "; xs=" + xs+ "; presence=" + presence+ "; fr=" + fr+ "; sb=" + sb);


        CloseableHttpResponse response = httpClient.execute(httpGet);
        String responseBody = EntityUtils.toString(response.getEntity());

        fb_dtsg = extractPattern(responseBody, "\"token\":\"([^\"]+)\"", "fb_dtsg Token");
        __user = extractPattern(responseBody, "\"actorID\":\"(\\d+)\"", "Account ID");
        lsd = extractPattern(responseBody, "\"token\":\"([^\"]+)\"", "lsd");
        hs = extractPattern(responseBody, "\"haste_session\":\"([^\"]+)\"", "hs");
        accountId = extractPattern(responseBody, "\"adAccountID\":\"(\\d+)\"", "Ad Account ID"); // Extracting the accountId
        response.close();


        
        
        fb_dtsg = URLEncoder.encode(fb_dtsg, "UTF-8");
        lsd = URLEncoder.encode(lsd, "UTF-8");
        hs = URLEncoder.encode(hs, "UTF-8");
       
  
        }catch(Exception ev){}
		





try {

    if(accountId.equalsIgnoreCase("")||accountId.equalsIgnoreCase(null))
    {

    	//invalid Cookies
	   try{modelap.setValueAt("Invalid", currentRow, 3);

	     badnum++;
    	 failureLabel.setText("Failure: "+badnum);
	   }catch(Exception ev){}  
    	try { 
    		//used
    		OutputStream outputStream = new FileOutputStream(dir+"\\Failure.txt",true);
    		try ( OutputStreamWriter Bwritecookie = new OutputStreamWriter(outputStream, "UTF-8")) {
    		Bwritecookie.write(""+inputData[0]+":"+inputData[1]+":"+cookies);
			Bwritecookie.write("\n");
    		Bwritecookie.close();
    		}
    		}catch(Exception ev){}  
	 
    
    }else  {
    	
 
		
 
        
        String  newRequestUrl = "https://www.facebook.com/api/graphql/";
        URL  url = new URL(newRequestUrl);
        HttpURLConnection  connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("content-type", "application/x-www-form-urlencoded");
        connection.setRequestProperty("cookie", "c_user=" + cUser + ";xs=" + xs+ ";fr="+";presence=" + presence + fr+ ";sb=" + sb);
        connection.setRequestProperty("x-fb-lsd", lsd);
        connection.setRequestProperty("x-fb-friendly-name", "CometProfilePlusLikeMutation");
        connection.setDoOutput(true);

        
        
        String requestBody = "av=" + __user +
        	    "&__aaid=0" +
        	    "&__user=" + __user +
        	    "&__a=1" +
        	    "&__req=1r" +
        	    "&__hs=20074.HYP%3Acomet_pkg.2.1.0.2.1" +
        	    "&dpr=2" +
        	    "&__ccg=EXCELLENT" +
        	    "&__rev=1018926459" +
        	    "&__s=x8vk46%3Ahc8tt5%3Al06vna" +
        	    "&__hsi=7449337179022232746" +
        	    "&__dyn=7xeXxaU5a5Q1ryaxG4Vp41twWwIxu13wFwnUW3q2ibwNwnof8boG0x8bo6u3y4o2Gwfi0LVEtwMw6ywIK1Rwwwqo462mcwfG12wOx62G5Usw9m1YwBgK7o6C0Mo4G1hx-3m1mzXw8W58jwGzE8FU5e3ym2SU4i5oe8464-5pUfEe88o4Wm7-2K0SEuwLKq2-azqwaW223908O3216xi4UK2K364UrwFg2fwxyo566k1FwgUjwOwWzUfHDzUiwRK6E4-mEbUaUaE2Tw" +
        	    "&__csr=gmMigz1tb24l7Ndl4jc_NZNcYdERPlRORvO8HO9AOOeKLb-FRlKJ8BL9h4Go_pvRF5oxRsF4nhqWBGcBp8y_CAmBGF5yGucx6rDAnGVp9pFVVV8nze6FV-eAUC23DUjWghg8EKfAzrXx-3KU-UO4poqyUWUiwxCyUWdyocEGdUlx23-5U-qexi2a2uEdUK22E9bwzK8DxuawqUeUuwnU5i1-xS1IwbW58eorwyw9i1IwiE3Ow7Ow7Gw4exm0e7w5jwcW1LwMwk823w052Bw0yyw6KwVyo0x60kTw3MUuwl80SC09nyUK083Cw3Wo0ivw1hy00yHU" +
        	    "&__comet_req=15" +
        	    "&fb_dtsg=" + fb_dtsg +
        	    "&jazoest=25741" +
        	    "&lsd=" + lsd +
        	    "&__spin_r=1018926459" +
        	    "&__spin_b=trunk" +
        	    "&__spin_t=1734433970" +
        	    "&fb_api_caller_class=RelayModern" +
        	    "&fb_api_req_friendly_name=CometUFIFeedbackReactMutation" +
        	    "&variables=%7B%22input%22%3A%7B%22attribution_id_v2%22%3A%22ProfileCometTimelineListViewRoot.react%2Ccomet.profile.timeline.list%2Cunexpected%2C1734433969879%2C22627%2C190055527696468%2C%2C%3BPagesCometLaunchpointUnifiedQueryPagesListRedesigned.react%2Ccomet.page.home%2Cvia_cold_start%2C1734433967085%2C822463%2C250100865708545%2C%2C%22%2C%22feedback_id%22%3A%22ZmVlZGJhY2s6NTY1NjYzNzA5NzU5Mzc1%22%2C%22feedback_reaction_id%22%3A%221635855486666999%22%2C%22feedback_source%22%3A%22PROFILE%22%2C%22is_tracking_encrypted%22%3Atrue%2C%22tracking%22%3A%5B%22AZUEj9xK7ul3uq4DYNFMI9j42vk4BZ8vJd9ukEFd2LOC23oX-dGYmII0CpgzPUO46JKZEaZv7poeulL3yKegO5ejcGhSJCAiQg8Oi-lgoWArEO5paVW3EhGHX_WZ1AgtrwPWApXIN1n-GXjIguCgPgTzMIU3WrmSOIuqcCsJiaaWO7Ydv5Jm7ADdKdk-QMIIydSaBrMEWhxuiduleXmrUg60P714ENG_yDMSGAPk_KPls8SPDXaGjrQ2wtkDslNEB9iAokBuxBrx2vTZsOqn5i0GIZqvB-XE9ICbdGxgdgHXY9trfOQX0_nLmZBTPCdeVKzHuMAO_lPXBrj4ZPr0IlSD6mmJtI-FMrDhw44w8lma5W9-4IhKZDb7bTQo1JgpIedeoNV3Zg1QzDfgChziZuJTPNK2_1WJTKm5810yZ_taiG-FfmXvtPU6JOxWRyI1Y3Z6JQQRn41bks53oINAcB8n9Laz8ySzB1VDq1Q-pqid5w%22%5D%2C%22session_id%22%3A%229b95d8de-5bbe-4b68-a3fa-b342a10ceb61%22%2C%22downstream_share_session_id%22%3A%22a276aef3-bc2c-4379-be94-f3a7104cb1fc%22%2C%22downstream_share_session_origin_uri%22%3A%22"+postID+"%22%2C%22downstream_share_session_start_time%22%3A%221734433553296%22%2C%22actor_id%22%3A%22" + __user + "%22%2C%22client_mutation_id%22%3A%223%22%7D%2C%22useDefaultActor%22%3Afalse%2C%22__relay_internal__pv__CometUFIReactionsEnableShortNamerelayprovider%22%3Afalse%7D" +
        	    "&server_timestamps=true" +
        	    "&doc_id=8995964513767096";


         
         try (OutputStream os = connection.getOutputStream()) {
             byte[] input = requestBody.getBytes(StandardCharsets.UTF_8);
             os.write(input, 0, input.length);
         }

         int responseCode1 = connection.getResponseCode();
    	

    	//valid Cookies
   try { modelap.setValueAt("Valid", currentRow, 3);
	     
    	goodnum++;
   	successLabel.setText("Success : "+goodnum);	  
    }catch(Exception ev){}  
   	
  	try { 
		//used
		OutputStream outputStream = new FileOutputStream(dir+"\\Success.txt",true);
		try ( OutputStreamWriter Bwritecookie = new OutputStreamWriter(outputStream, "UTF-8")) {
		Bwritecookie.write(""+inputData[0]+":"+inputData[1]+":"+cookies); 
		Bwritecookie.write("\n");
		Bwritecookie.close();
		}
		}catch(Exception ev){}  
    	

    }	 
} catch (IOException e) {}

	 
//waiting time between each request to avoid detection
Thread.sleep(3000);
 
 }
  
 }catch(Exception ev){}
  
  
 	
 	
 	
 	
 }
  
  
 }
  
 private static Map<String, String> parseCookies(String cookies) {
     Map<String, String> cookieMap = new HashMap<>();
     String[] cookieArray = cookies.split(";");
     for (String cookie : cookieArray) {
         String[] keyValue = cookie.split("=", 2);
         if (keyValue.length == 2) {
             cookieMap.put(keyValue[0].trim(), keyValue[1].trim());
         }
     }
     return cookieMap;
 }
 
 

  private static String extractPattern(String responseBody, String regex, String name) {
     Pattern pattern = Pattern.compile(regex);
     Matcher matcher = pattern.matcher(responseBody);
     if (matcher.find()) {
         return matcher.group(1);
     } else {
         throw new IllegalStateException("Failed to extract " + name);
     }
 }

 
	public static void main(String[] args) {
		
  	try {
 UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
 | UnsupportedLookAndFeelException e1) {
 e1.printStackTrace();
		}
  	SwingUtilities.invokeLater(new Runnable() {
  public void run() {

  StarMedia = new PagesLikesThreads();
 StarMedia.setVisible(true);
 StarMedia.setBounds(100, 100, 662, 313);
 StarMedia.setResizable(false);



 
  }
 });
  }
}
