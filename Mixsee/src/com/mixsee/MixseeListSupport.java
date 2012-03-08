package com.mixsee;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MixseeListSupport extends FragmentActivity {
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_stack);
    }

	public static class MixseeListViewFragment extends Fragment {
		
		private static final long FADE_IN_TIME = 200;
		private static final long OFFSET = 100;
		
		@Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
        }
		
		@SuppressWarnings("deprecation")
		@Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
			View v = inflater.inflate(R.layout.mixsee_list, container, false);
			
			ImageView img = (ImageView) v.findViewById(R.id.image1);
			ImageView img2 = (ImageView) v.findViewById(R.id.image2);
			ImageView img3 = (ImageView) v.findViewById(R.id.image3);
			ImageView img4 = (ImageView) v.findViewById(R.id.image4);
			ImageView img5 = (ImageView) v.findViewById(R.id.image5);
			ImageView img6 = (ImageView) v.findViewById(R.id.image6);
			ImageView img7 = (ImageView) v.findViewById(R.id.image7);
			ImageView img8 = (ImageView) v.findViewById(R.id.image8);
			ImageView img9 = (ImageView) v.findViewById(R.id.image9);
			ImageView img10 = (ImageView) v.findViewById(R.id.image10);
			ImageView img11 = (ImageView) v.findViewById(R.id.image11);
			
			
			img.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Log.i("MixseeListViewFragment", "click city!!!!");
					
				}
			});
			
    		Animation anim = AnimationUtils.loadAnimation(getActivity(), R.animator.webview_animator_out);
    		anim.reset();
    		img.clearAnimation();
    		img.startAnimation(anim);
    		
    		
    		Animation anim2 = AnimationUtils.loadAnimation(getActivity(), R.animator.webview_animator_out);
    		anim2.reset();
    		img2.clearAnimation();
    		anim2.setStartOffset(OFFSET);
    		img2.startAnimation(anim2);
    		
    		Animation anim3 = AnimationUtils.loadAnimation(getActivity(), R.animator.webview_animator_out);
    		anim3.reset();
    		img3.clearAnimation();
    		anim3.setStartOffset(OFFSET*2);
    		img3.startAnimation(anim3);
    		
    		Animation anim4 = AnimationUtils.loadAnimation(getActivity(), R.animator.webview_animator_out);
    		anim4.reset();
    		img4.clearAnimation();
    		anim4.setStartOffset(OFFSET*3);
    		img4.startAnimation(anim4);
    		
    		Animation anim5 = AnimationUtils.loadAnimation(getActivity(), R.animator.webview_animator_out);
    		anim5.reset();
    		img5.clearAnimation();
    		anim5.setStartOffset(OFFSET*4);
    		img5.startAnimation(anim5);
    		
    		
		
			return v;
		}

	}
}
