package drzio.insomnia.treatment.bedtime.yoga.sleep;

import android.app.Activity;
import android.content.Context;
import android.view.Display;

public class DisplayConfiguration {

    public static Integer height(Context context, int height){
        Display display = ((Activity)context).getWindowManager().getDefaultDisplay();
        int get_height = display.getHeight();
        int height1;

        height1 = (get_height*height)/480;
        return height1;
    }

    public static Integer width(Context context,int width){
        Display display = ((Activity)context).getWindowManager().getDefaultDisplay();
        int get_width = display.getWidth();
        int width1;

        width1 = (get_width*width)/380;
        return width1;
    }

    public static Integer display(){

        return null;
    }

}
