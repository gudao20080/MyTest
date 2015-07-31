package wangkai.mytest;

import android.app.IntentService;
import android.content.Intent;

/**
 * User: WangKai(123940232@qq.com)
 * 2015-07-31 17:43
 */
public class MyService extends IntentService {

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public MyService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent intent) {

    }
}
