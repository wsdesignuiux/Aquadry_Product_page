package adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import fragment.ProductListFragment;


/**
 * Created by wolfsoft on 10/11/2015.
 */
public class ProductsPagerAdapter extends FragmentStatePagerAdapter {

    int mNumOfTabs;


    public ProductsPagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }


    @Override
    public Fragment getItem(int position) {

        ProductListFragment fragment = new ProductListFragment();
        return fragment;
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }


}