package lisa.duterte.coursework_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class ViewPagerAdapter extends PagerAdapter {

     private Context context;
     private LayoutInflater layoutInflater;
     private Integer[] images = {R.drawable.friends_group, R.drawable.friends_group1};

     ViewPagerAdapter(Context context) {
         this.context = context;
     }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    public  Object instantiateItem(@NonNull ViewGroup container, final int position) {

         layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
         View view = layoutInflater.inflate(R.layout.activity_view_pager_adapter,null);

         //permet d'afficher l'image
         ImageView imageView = view.findViewById(R.id.imageView);
         imageView.setImageResource(images[position]);


         ViewPager vp = (ViewPager) container;
         vp.addView(view,0);
         return view;
    }
}
