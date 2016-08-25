package customview.ly.com.dashlinepro.myView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

import customview.ly.com.dashlinepro.R;


/**
 * @author ly
 * @version V1.0
 * @Package com.blueteam.ganjiuhui.myview
 * @Description: 虚线自定义View
 * @date 2016/8/25 11:30
 */
public class DashLine extends View {
    private Paint paintLine;
    private int dashWidth = 10;
    private int offset = 10;
    private int orientation = 0;
    private static final int horizontal = 0;
    ;
    private static final int vertical = 1;
    private int dashColor = 0XFFFF0000;

    @Override
    public boolean removeCallbacks(Runnable action) {
        return super.removeCallbacks(action);
    }

    public DashLine(Context context, AttributeSet attrs) {
        this(context,attrs,0);
    }

    public DashLine(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray initArray = context.obtainStyledAttributes(attrs, R.styleable.DashLine, defStyleAttr, 0);
        dashWidth = initArray.getDimensionPixelSize(R.styleable.DashLine_dashWidth, 0);
        orientation = initArray.getInteger(R.styleable.DashLine_orientation, 0);
        offset = initArray.getDimensionPixelSize(R.styleable.DashLine_offset, 4);
        dashColor = initArray.getColor(R.styleable.DashLine_dashColor, 0XFFFF0000);
        paintLine = new Paint();
        paintLine.setColor(dashColor);
        paintLine.setStrokeWidth(4);
    }


    public void setColor(int color) {
        paintLine.setColor(color);
        invalidate();
    }

    private int dp2px(int dp) {
        DisplayMetrics dm = getContext().getResources().getDisplayMetrics();
        return (int) (dm.density * dp);
    }

    public void setHeight(int height) {
        paintLine.setStrokeWidth(dp2px(height));
        invalidate();
    }

    public void setOffset(int offset) {
        this.offset = dp2px(offset);
        invalidate();
    }

    public void setDashWidth(int dashWidth) {
        this.dashWidth = dp2px(dashWidth);
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = getWidth();
        int height = getHeight();
        int start = 0;
        if (orientation == horizontal) {

            while (start <width) {
                canvas.drawLine(start, height / 2, start + dashWidth, height / 2, paintLine);
                start += (offset + dashWidth);
            }
        } else if (orientation == vertical) {
            while (start < height) {
                canvas.drawLine(width / 2, start, width / 2, start + dashWidth, paintLine);
                start += (offset + dashWidth);
            }
        }

    }
}
