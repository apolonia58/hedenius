package com.example.hedenius01;
/**
 * Created by Abdullah Yildirim on 27.11.2017.
 */
//In dieser Klasse werden alle Images in einem Array gespeichert und von hier aus aufgerufen in den einzelnen ImageViews.
public class Bilder {

    //Runde 1
    //Set1 Bilder
    public static int[] images = new int []{
      R.drawable.ob1real1 ,R.drawable.ob1notreal1, R.drawable.ob1notreal2, R.drawable.ob1real2,
      R.drawable.ob1real3, R.drawable.ob1notreal3, R.drawable.ob1notreal4, R.drawable.ob1real4
    };

    //Runde 2
    //Set2 Bilder
    public static int[] images2 = new int []{
            R.drawable.ob2notreal1 , R.drawable.ob2notreal2, R.drawable.ob2real1, R.drawable.ob2real2,
            R.drawable.ob2notreal3 , R.drawable.ob2notreal4, R.drawable.ob2real3, R.drawable.ob2real4
    };

    //Runde 3
    //Set3 Bilder
    public static int[] images3 = new int []{
            R.drawable.ob3notreal1 , R.drawable.ob3real1 , R.drawable.ob3notreal2, R.drawable.ob3real2,
            R.drawable.ob3notreal3 , R.drawable.ob3notreal4, R.drawable.ob3real3, R.drawable.ob3real4
    };

    //Runde 4
    //Set4 Bilder
    public static int[] images4 = new int []{
            R.drawable.ob4real1, R.drawable.ob4real2, R.drawable.ob4notreal1, R.drawable.ob4real3,
            R.drawable.ob4notreal2, R.drawable.ob4real4, R.drawable.ob4notreal3, R.drawable.ob4notreal4
    };

    //Runde 5
    //Set5 Bilder
    public static int[] images5 = new int []{
            R.drawable.ob5notfiktiv1, R.drawable.ob5fiktiv1, R.drawable.ob5fiktiv2, R.drawable.ob5notfiktiv2,
            R.drawable.ob5notfiktiv3, R.drawable.ob5fiktiv3, R.drawable.ob5notfiktiv4, R.drawable.ob5fiktiv4

    };

    //Runde 6
    //Set5 Bilder
    public static int[] images6 = new int []{
            R.drawable.ob6fiktiv1, R.drawable.ob6notfiktiv1, R.drawable.ob6fiktiv2, R.drawable.ob6notfiktiv2,
            R.drawable.ob6fiktiv3, R.drawable.ob6notfiktiv3, R.drawable.ob6fiktiv4, R.drawable.ob6notfiktiv4

    };

    //Runde 7
    //Set5 Bilder
    public static int[] images7 = new int []{
            R.drawable.ob7notfiktiv1, R.drawable.ob7notfiktiv2, R.drawable.ob7fiktiv1, R.drawable.ob7notfiktiv3,
            R.drawable.ob7fiktiv2, R.drawable.ob7fiktiv3, R.drawable.ob7notfiktiv4, R.drawable.ob7fiktiv4

    };

    //Runde 8
    //Set5 Bilder
    public static int[] images8 = new int []{
            R.drawable.ob8fiktiv1, R.drawable.ob8notfiktiv1, R.drawable.ob8notfiktiv2, R.drawable.ob8fiktiv2,
            R.drawable.ob8notfiktiv3, R.drawable.ob8notfiktiv4, R.drawable.ob8fiktiv3, R.drawable.ob8fiktiv4

    };

    //Hier sind die Bilder für die beiden Trainingsrunden
    //Training Runde 1
    //SetTraining Bilder
    public static int[] training1images = new int []{
            R.drawable.not_training1img1, R.drawable.set_training1img1, R.drawable.not_training1img2, R.drawable.set_training1img2

    };

    //Training Runde 2
    //SetTraining Bilder
    public static int[] training2images = new int []{
           R.drawable.set_training2img1 ,R.drawable.not_training2img1, R.drawable.not_training2img2, R.drawable.set_training2img2

    };

    //In diesen arrays werden die richtigen Antworten gespeichert und in den jeweiligen Runden aufgerufen und in die CSV geschrieben
    //Antworten zu Runde1 real mit Hintergrund
    // 1 0 0 1 1 0 0 1
    public static boolean[] answers = new boolean[] {
            true, false, false, true , true , false, false, true
    };

    //Antworten zu Runde2 real mit Hintergrund
    // 0 0 1 1 0 0  1 1
    public static boolean[] answers2 = new boolean[] {
            false, false, true, true, false, false, true, true
    };

    //Antworten zu Runde3 real mit Hintergrund
    // 0 1 0 1 0 0 1 1
    public static boolean[] answers3 = new boolean[] {
            false, true, false, true, false, false, true, true
    };

    //Antworten zu Runde4 real mit Hintergrund
    // 1 1 0 1 0 1 0 0
    public static boolean[] answers4 = new boolean[] {
            true, true, false, true, false, true, false, false
    };

    //Antworten zu Runde5 fiktiv ohne Hintergrund
    // 0 1 1 0 0 1 0 1
    public static boolean[] answers5 = new boolean[] {
            false, true, true, false, false, true, false, true
    };

    //Antworten zu Runde6 fiktiv ohne Hintergrund
    // 1 0 1 0 1 0 1 0
    public static boolean[] answers6 = new boolean[] {
            true, false, true, false, true, false, true, false
    };

    //Antworten zu Runde7 fiktiv ohne Hintergrund
    // 0 0 1 0 1 1 0 1
    public static boolean[] answers7 = new boolean[] {
            false, false, true, false, true, true,  false, true
    };

    //Antworten zu Runde8 fiktiv ohne Hintergrund
    // 1 0 0 1 0 0 1 1
    public static boolean[] answers8 = new boolean[] {
            true, false, false, true, false, false, true, true
    };


    //Antworten zu SetTraining Runde1 real mit Hintergrund
    // 0 1 0 1
    public static boolean[] training1answers = new boolean[] {
            false, true, false, true
    };

    //Antworten zu SetTraining Runde2 fiktiv ohne Hintergrund
    // 1 0 0 1
    public static boolean[] training2answers = new boolean[] {
            true, false, false, true
    };
}
