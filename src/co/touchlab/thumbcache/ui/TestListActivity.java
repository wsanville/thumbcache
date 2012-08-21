package co.touchlab.thumbcache.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import co.touchlab.thumbcache.R;
import co.touchlab.thumbcache.provider.Images;
import co.touchlab.thumbcache.util.ImageFetcher;
import co.touchlab.thumbcache.util.ImageWorker;

/**
 * User: William Sanville
 * Date: 7/30/12
 * Time: 10:09 AM
 * Testing this implementation with a list.
 */
public class TestListActivity extends Activity
{
    private ImageWorker imageWorker;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);
        ListView list = (ListView)findViewById(R.id.list);

        int size = (int)getResources().getDimension(R.dimen.list_image_size);
        imageWorker = new ImageFetcher(this, size);
        imageWorker.setLoadingImage(R.drawable.ic_launcher);
        imageWorker.setAdapter(Images.otherUrlAdapter);
    }

    class TestAdapter extends BaseAdapter
    {
        TestAdapter()
        {

        }

        @Override
        public int getCount()
        {
            return 0;  //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public Object getItem(int i)
        {
            return null;  //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public long getItemId(int i)
        {
            return 0;  //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup)
        {
            return null;  //To change body of implemented methods use File | Settings | File Templates.
        }
    }
}
