package com.aviy.memory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Pattern;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.util.Linkify;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;

public class Manager extends Activity {
    private static int ROW_COUNT = -1;
	private static int COL_COUNT = -1;
	private Context context;
	private Drawable backImage;
	private int [] [] cards;
	private List<Drawable> images;
	private Card firstCard;
	private Card seconedCard;
	private ButtonListener buttonListener;
	
	private static Object lock = new Object();
	
	int turns;
	private TableLayout mainTable;
	private UpdateCardsHandler handler;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
      
        
        handler = new UpdateCardsHandler();
        loadImages();
        setContentView(R.layout.main);
       
        TextView url = ((TextView)findViewById(R.id.myWebSite));
        Linkify.addLinks(url, Linkify.WEB_URLS);
        
       backImage =  getResources().getDrawable(R.drawable.icon);
       
       /*
       ((Button)findViewById(R.id.ButtonNew)).setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			newGame();
			
		}

		
	});*/
      
       buttonListener = new ButtonListener();
        
        mainTable = (TableLayout)findViewById(R.id.TableLayout03);
        
        
        context  = mainTable.getContext();
        
       	 Spinner s = (Spinner) findViewById(R.id.Spinner01);
	        ArrayAdapter adapter = ArrayAdapter.createFromResource(
	                this, R.array.type, android.R.layout.simple_spinner_item);
	        
	        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	        s.setAdapter(adapter);
	        
	        
	        s.setOnItemSelectedListener(new OnItemSelectedListener(){
	        	
	    	  @Override
	    	  public void onItemSelected(
	    			  android.widget.AdapterView<?> arg0, 
	    			  View arg1, int pos, long arg3){
	    		  
	    		  ((Spinner) findViewById(R.id.Spinner01)).setSelection(0);
	    		  
	  			int x,y;
	  			
	  			switch (pos) {
				case 1:
					x=4;y=4;
					break;
				case 2:
					x=4;y=5;
					break;
				case 3:
					x=4;y=6;
					break;
				case 4:
					x=5;y=6;
					break;
				case 5:
					x=6;y=6;
					break;
				default:
					return;
				}
	  			newGame(x,y);
	  			
	  		}


			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}

	  	});
    }
    
    private void newGame(int c, int r) {
    	ROW_COUNT = r;
    	COL_COUNT = c;
    	
    	cards = new int [COL_COUNT] [ROW_COUNT];
    	
    	
    	mainTable.removeView(findViewById(R.id.TableRow01));
    	mainTable.removeView(findViewById(R.id.TableRow02));
    	
    	TableRow tr = ((TableRow)findViewById(R.id.TableRow03));
    	tr.removeAllViews();
    	
    	mainTable = new TableLayout(context);
    	tr.addView(mainTable);
    	
    	 for (int y = 0; y < ROW_COUNT; y++) {
    		 mainTable.addView(createRow(y));
          }
    	 
    	 firstCard=null;
    	 loadCards();
    	 
    	 turns=0;
    	 ((TextView)findViewById(R.id.tv1)).setText("ចននបកលង: "+turns);
    	 
			
	}
    
    private void loadImages() {
    	images = new ArrayList<Drawable>();
    	
//    	images.add(getResources().getDrawable(R.drawable.card1));
//    	images.add(getResources().getDrawable(R.drawable.card2));
//    	images.add(getResources().getDrawable(R.drawable.card3));
//    	images.add(getResources().getDrawable(R.drawable.card4));
//    	images.add(getResources().getDrawable(R.drawable.card5));
//    	images.add(getResources().getDrawable(R.drawable.card6));
//    	images.add(getResources().getDrawable(R.drawable.card7));
//    	images.add(getResources().getDrawable(R.drawable.card8));
//    	images.add(getResources().getDrawable(R.drawable.card9));
//    	images.add(getResources().getDrawable(R.drawable.card10));
//    	images.add(getResources().getDrawable(R.drawable.card11));
//    	images.add(getResources().getDrawable(R.drawable.card12));
//    	images.add(getResources().getDrawable(R.drawable.card13));
//    	images.add(getResources().getDrawable(R.drawable.card14));
//    	images.add(getResources().getDrawable(R.drawable.card15));
//    	images.add(getResources().getDrawable(R.drawable.card16));
//    	images.add(getResources().getDrawable(R.drawable.card17));
//    	images.add(getResources().getDrawable(R.drawable.card18));
//    	images.add(getResources().getDrawable(R.drawable.card19));
//    	images.add(getResources().getDrawable(R.drawable.card20));
//    	images.add(getResources().getDrawable(R.drawable.card21));
    	
//    	images.add(getResources().getDrawable(R.drawable.card22));
//    	images.add(getResources().getDrawable(R.drawable.card23));
//    	images.add(getResources().getDrawable(R.drawable.card24));
//    	images.add(getResources().getDrawable(R.drawable.card25));
//    	images.add(getResources().getDrawable(R.drawable.card26));
//    	images.add(getResources().getDrawable(R.drawable.card27));
//    	images.add(getResources().getDrawable(R.drawable.card28));
//    	images.add(getResources().getDrawable(R.drawable.card29));
//    	images.add(getResources().getDrawable(R.drawable.card30));
//    	images.add(getResources().getDrawable(R.drawable.card31));
//    	images.add(getResources().getDrawable(R.drawable.card32));
//    	images.add(getResources().getDrawable(R.drawable.card33));
//    	images.add(getResources().getDrawable(R.drawable.card34));
//    	images.add(getResources().getDrawable(R.drawable.card35));
//    	images.add(getResources().getDrawable(R.drawable.card36));
//    	images.add(getResources().getDrawable(R.drawable.card37));
//    	images.add(getResources().getDrawable(R.drawable.card38));
//    	images.add(getResources().getDrawable(R.drawable.card39));
//    	images.add(getResources().getDrawable(R.drawable.card40));
//    	images.add(getResources().getDrawable(R.drawable.card41));
//    	images.add(getResources().getDrawable(R.drawable.card21));
    	
    	images.add(getResources().getDrawable(R.drawable.da));
    	images.add(getResources().getDrawable(R.drawable.dany));
    	images.add(getResources().getDrawable(R.drawable.hamish));
    	images.add(getResources().getDrawable(R.drawable.joanne));
    	images.add(getResources().getDrawable(R.drawable.narong));
    	images.add(getResources().getDrawable(R.drawable.nary));
    	images.add(getResources().getDrawable(R.drawable.neath));
    	images.add(getResources().getDrawable(R.drawable.run));
    	images.add(getResources().getDrawable(R.drawable.sinet));
    	images.add(getResources().getDrawable(R.drawable.willem));
    	
    	images.add(getResources().getDrawable(R.drawable.joshua));
    	images.add(getResources().getDrawable(R.drawable.leng));
    	images.add(getResources().getDrawable(R.drawable.loy1));
    	images.add(getResources().getDrawable(R.drawable.loy9));
    	images.add(getResources().getDrawable(R.drawable.pio));
    	images.add(getResources().getDrawable(R.drawable.sinet));
    	images.add(getResources().getDrawable(R.drawable.sombo));
    	images.add(getResources().getDrawable(R.drawable.sreykong));
    	images.add(getResources().getDrawable(R.drawable.tola2));
    	images.add(getResources().getDrawable(R.drawable.card34));
    	images.add(getResources().getDrawable(R.drawable.card35));
    	images.add(getResources().getDrawable(R.drawable.card36));
    	images.add(getResources().getDrawable(R.drawable.card37));
    	images.add(getResources().getDrawable(R.drawable.card38));
    	images.add(getResources().getDrawable(R.drawable.card39));
    	images.add(getResources().getDrawable(R.drawable.card40));
    	images.add(getResources().getDrawable(R.drawable.card41));
		
	}

	private void loadCards(){
		try{
	    	int size = ROW_COUNT*COL_COUNT;
	    	
	    	Log.i("loadCards()","size=" + size);
	    	
	    	ArrayList<Integer> list = new ArrayList<Integer>();
	    	
	    	for(int i=0;i<size;i++){
	    		list.add(new Integer(i));
	    	}
	    	
	    	
	    	Random r = new Random();
	    
	    	for(int i=size-1;i>=0;i--){
	    		int t=0;
	    		
	    		if(i>0){
	    			t = r.nextInt(i);
	    		}
	    		
	    		t=list.remove(t).intValue();
	    		cards[i%COL_COUNT][i/COL_COUNT]=t%(size/2);
	    		
	    		Log.i("loadCards()", "card["+(i%COL_COUNT)+
	    				"]["+(i/COL_COUNT)+"]=" + cards[i%COL_COUNT][i/COL_COUNT]);
	    	}
	    }
		catch (Exception e) {
			Log.e("loadCards()", e+"");
		}
		
    }
    
    private TableRow createRow(int y){
    	 TableRow row = new TableRow(context);
    	 row.setHorizontalGravity(Gravity.CENTER);
         
         for (int x = 0; x < COL_COUNT; x++) {
		         row.addView(createImageButton(x,y));
         }
         return row;
    }
    
    private View createImageButton(int x, int y){
    	Button button = new Button(context);
    	button.setBackgroundDrawable(backImage);
    	button.setId(100*x+y);
    	button.setOnClickListener(buttonListener);
    	return button;
    }
    
    class ButtonListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			
			synchronized (lock) {
				if(firstCard!=null && seconedCard != null){
					return;
				}
				int id = v.getId();
				int x = id/100;
				int y = id%100;
				turnCard((Button)v,x,y);
			}
			
		}

		private void turnCard(Button button,int x, int y) {
			button.setBackgroundDrawable(images.get(cards[x][y]));
			
			if(firstCard==null){
				firstCard = new Card(button,x,y);
			}
			else{ 
				
				if(firstCard.x == x && firstCard.y == y){
					return; //the user pressed the same card
				}
					
				seconedCard = new Card(button,x,y);
				
				turns++;
				((TextView)findViewById(R.id.tv1)).setText("ចននបកលង: "+turns);
				
			
				TimerTask tt = new TimerTask() {
					
					@Override
					public void run() {
						try{
							synchronized (lock) {
							  handler.sendEmptyMessage(0);
							}
						}
						catch (Exception e) {
							Log.e("E1", e.getMessage());
						}
					}
				};
				
				  Timer t = new Timer(false);
			        t.schedule(tt, 1300);
			}
			
				
		   }
			
		}
    
    class UpdateCardsHandler extends Handler{
    	
    	@Override
    	public void handleMessage(Message msg) {
    		synchronized (lock) {
    			checkCards();
    		}
    	}
    	 public void checkCards(){
    	    	if(cards[seconedCard.x][seconedCard.y] == cards[firstCard.x][firstCard.y]){
    				firstCard.button.setVisibility(View.INVISIBLE);
    				seconedCard.button.setVisibility(View.INVISIBLE);
    			}
    			else {
    				seconedCard.button.setBackgroundDrawable(backImage);
    				firstCard.button.setBackgroundDrawable(backImage);
    			}
    	    	
    	    	firstCard=null;
    			seconedCard=null;
    	    }
    }
    
   
    
    
}