package com.ice.homecontrol;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.View;
;

public class HomeGraph extends View implements Graph {
    public static final String PREFS_NAME = "MyPrefsFile";

	public int[] dataPoints = {180, 170, 160, 150, 140, 130, 120, 110, 100, 90, 80, 70};
	public String xLabels[] = {"10", "20", "30", "40", "50"};
	public String yLabels[] = {"10", "20", "30", "40", "50"};

	private int strikePrice;
	private int xorigin = 100;
	private int yorigin = 300;
    public Path dataPath = new Path();
    public Path optionPath = new Path();

    //  Constructor for class
    public HomeGraph(Context context, AttributeSet attrs) {
        super(context, attrs);
        Bitmap _scratch = BitmapFactory.decodeResource(getResources(), R.drawable.icon);
//      canvas.drawColor(Color.BLACK);
        TypedArray a=getContext().obtainStyledAttributes(attrs,R.styleable.Graph);
 
        }
 
    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint mLinePaint = new Paint();
        Paint mTextPaint = new Paint();
        mLinePaint.setColor(0xFF0000FF);
        mLinePaint.setStrokeWidth(1);
        canvas.drawLine(xorigin, 0, xorigin, yorigin, mLinePaint);                                           // draw vertical axis
        canvas.drawLine(xorigin, 150, 480, 150, mLinePaint);                                          // draw horizontal axis
        canvas.drawLine(xorigin, yorigin, 480, yorigin, mLinePaint);                                          // draw horizontal axis

        mLinePaint.setColor(0x66005500);
        mTextPaint.setStrokeWidth(1);
        mTextPaint.setTextSize(20);
        
        setXLabels(canvas, mTextPaint);
        canvas.drawLine(xorigin, 0, xorigin, 300, mLinePaint);                                           // draw vertical blue line
                                           // draw vertical blue line
                                
        // draw horizontal lines
        canvas.drawText("P/L", 0, 20, mTextPaint);
        canvas.drawText("0", 0, 150, mTextPaint);
        canvas.drawText("-$200", 0, 200, mTextPaint);
        canvas.drawText("$300", 0, 75, mTextPaint);
        mLinePaint.setColor(0x66005500);

        mLinePaint.setColor(0xFF0000FF);
        canvas.drawLine(250, 140, 250, 160, mLinePaint);                                           // draw check marks
        canvas.drawLine(300, 140, 300, 160, mLinePaint);                                           // draw check marks
        canvas.drawLine(350, 140, 350, 160, mLinePaint);                                           // draw check marks
        
        
        drawPayoffLine(dataPath, canvas, mTextPaint);       
        drawOptionLine(optionPath, canvas, mLinePaint);                  

        mLinePaint.setColor(Color.DKGRAY);


    }
	
	void setDP(final int newData)  {
			invalidate();              // this redraws page
	}
	
    
    public void drawPayoffLine(Path path, Canvas c, Paint paint)  {
        paint.setStrokeWidth(2);
        paint.setStyle(Style.STROKE);
		c.drawPath(path, paint);
    }
    
    public void drawOptionLine(Path path, Canvas c, Paint paint)  {
        paint.setColor(0xFFEEEEEE);
        paint.setStyle(Style.STROKE);
        paint.setPathEffect(new DashPathEffect(new float[] {10,20}, 0));
        paint.setStrokeWidth(1);
		c.drawPath(path, paint);

    }

    
    public void setDataPath(Path aPath, Path bPath)  {

    	dataPath = aPath;
    	optionPath = bPath;
        invalidate();
    }
    
    public void setSlider1(double d)  {
        invalidate();
    }
    
    public void setSlider2(double d)  {
        invalidate();
    }
    
    public void setXLabels(Canvas c, Paint paint)  {
        paint.setColor(0xEEEEEEEE);
        c.drawText(xLabels[0], 115, 320, paint);
        c.drawText(xLabels[1], 215, 320, paint);
        c.drawText(xLabels[2], 315, 320, paint);
        c.drawText(xLabels[3], 415, 320, paint);
        c.drawText(xLabels[4], 515, 320, paint);

    }
	@Override
    public void setYLabels()  {

    }
    

	public void setWidth() {
	}
	public void setLength(){
	}
	public void setXDivisions() {
	}
	public void setYDivisions() {
	}

    
	int getDataPoint(String name)  {
		return(10);
		}

	@Override
	public void setXLabels() {
		// TODO Auto-generated method stub
		
	}


	}


