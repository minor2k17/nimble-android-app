package com.minor2k17.nimble;
/**
 * Created by shaur on 08-10-2017.
 */

import android.content.Intent;
import android.view.WindowManager;

import com.daimajia.androidanimations.library.Techniques;
import com.viksaa.sssplash.lib.activity.AwesomeSplash;
import com.viksaa.sssplash.lib.cnst.Flags;
import com.viksaa.sssplash.lib.model.ConfigSplash;


public class splashscreen_nimble extends AwesomeSplash {




    @Override
    public void initSplash(ConfigSplash configSplash) {

			/* you don't have to override every property */
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //Customize Circular Reveal
        configSplash.setBackgroundColor(R.color.black); //any color you want from colors.xml
        configSplash.setAnimCircularRevealDuration(1800); //int ms
        configSplash.setRevealFlagX(Flags.REVEAL_RIGHT);  //or Flags.REVEAL_LEFT
        //configSplash.setRevealFlagY(Flags.REVEAL_BOTTOM);


        configSplash.setBackgroundColor(R.color.black); //any color you want from colors.xml
        configSplash.setAnimCircularRevealDuration(950); //int ms
        configSplash.setRevealFlagX(Flags.REVEAL_LEFT);  //or Flags.REVEAL_LEFT
        configSplash.setRevealFlagY(Flags.REVEAL_TOP);//or Flags.REVEAL_TOP

        //Choose LOGO OR PATH; if you don't provide String value for path it's logo by default

        //Customize Logo
        configSplash.setLogoSplash(R.drawable.nimble_leaf); //or any other drawable
        configSplash.setAnimLogoSplashDuration(950); //int ms
        configSplash.setAnimLogoSplashTechnique(Techniques.FadeInDown); //choose one form Techniques (ref: https://github.com/daimajia/AndroidViewAnimations)





//        //Customize Title
        configSplash.setTitleSplash("NIMBLE");
//        configSplash.setTitleTextColor(R.color.black);
//        configSplash.setTitleTextSize(29f); //float value
//        configSplash.setAnimTitleDuration(3000);
        configSplash.setAnimTitleTechnique(Techniques.FlipInX);
 //       configSplash.setTitleFont("fonts/volatire.ttf"); //provide string to your font located in assets/fonts/
    }

    @Override
    public void animationsFinished() {
        startActivity(new Intent(this, signin.class));
        finish();
    }
}
