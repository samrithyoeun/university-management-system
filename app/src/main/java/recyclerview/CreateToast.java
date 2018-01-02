package recyclerview;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by samrith on 2/1/18.
 */

public class CreateToast {
    public static void show(Context c, String text){
        Toast.makeText(c, text, Toast.LENGTH_SHORT).show();
    }
}
