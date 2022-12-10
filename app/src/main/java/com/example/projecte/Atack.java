package com.example.projecte;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

import static java.util.concurrent.TimeUnit.SECONDS;

import androidx.lifecycle.LiveData;

public class Atack {
    LiveData<String> ordenLiveData = new LiveData<String>() {
        @Override
        protected void onActive() {
            super.onActive();

            startAtackSlides(new AtackListener() {
                @Override
                public void cuandoDeLaOrden(String orden) {
                    postValue(orden);
                }
            });
        }

        @Override
        protected void onInactive() {
            super.onInactive();

            stopWeatherSlides();
        }
    };

    interface AtackListener {
        void cuandoDeLaOrden(String orden);
    }

    Random random = new Random();
    ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    ScheduledFuture<?> atackShowing;

    void startAtackSlides(AtackListener atackListener) {
        if (atackShowing == null || atackShowing.isCancelled()) {
            atackShowing = scheduler.scheduleAtFixedRate(new Runnable() {
                int atack;
                int repeatAtack = -1;

                @Override
                public void run() {
                    if (repeatAtack < 0) {
                        repeatAtack = random.nextInt(3) + 3;
                        atack = random.nextInt(10)+1;
                    }
                    /*weatherListener.cuandoDeLaOrden("W" + weather + ":" + (repeatWeather == 0 ? "CHANGE WEATHER" :
                            weather == 1 ? "Sunny" : weather == 2 ? "Cloudy" :
                            weather == 3 ? "Foggy" : weather == 4 ? "Rainy" :
                            weather == 5 ? "Thunderstorm" : weather == 6 ? "Snowy" : repeatWeather));
                    repeatWeather--;*/
                    String orden = "Character Change";

                    if(atack == 1) {
                        orden = "Ainz";

                    }
                    else if (atack == 2) {
                        orden = "Albeldo";

                    }
                    else if (atack == 3) {
                        orden = "Aura";
                    }
                    else if (atack == 4) {
                        orden = "Cocytus";
                    }
                    else if (atack == 5) {
                        orden = "Demiurge";
                    }
                    else if (atack == 6) {
                        orden = "Mare";
                    }
                    else if (atack == 7) {
                        orden = "Pandora Actor";
                    }
                    else if (atack == 8) {
                        orden = "Shalltear";
                    }
                    atackListener.cuandoDeLaOrden("A" + atack + ":" +  (repeatAtack == 0 ? "Character Change" : orden ));
                    repeatAtack--;
                }
            }, 0, 1, SECONDS);
        }
    }

    void stopWeatherSlides() {
        if (atackShowing != null) {
            atackShowing.cancel(true);
        }
    }
}
