package net.zeany.everytimeproject;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import net.zeany.everytimeproject.util.HTTP;
import net.zeany.everytimeproject.util.User;
import net.zeany.everytimeproject.R;
import net.zeany.everytimeproject.util.User;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fetchUserInformation();
        printView();

    }


    private void fetchUserInformation() {
        String cookie = getIntent().getStringExtra("Cookie");

         /*유저 정보 받아옴*/
        user = HTTP.printUser(cookie);
        /*유저 정보 받아옴*/

    }

    @Override
    public void onClick(View view) {

    }

    private void printView() {
        int width, height;
        /*이미지 출력을 위해 핸드폰 스크린 크기 계산*/
        width = ((WindowManager) getSystemService(WINDOW_SERVICE)).getDefaultDisplay().getWidth();
        height = ((WindowManager) getSystemService(WINDOW_SERVICE)).getDefaultDisplay().getHeight();
        /*이미지 출력을 위해 핸드폰 스크린 크기 계산*/

        printUserInformation(width, height);
        printLogo(width, height);
    }


    private void printUserInformation(int width, int height) {
         /*이미지 출력-조건 학번을 제대로 받아올 경우*/
        ImageView imagePicture = (ImageView) findViewById(R.id.imagePicture);
        if (user.getNumber() != 0)
            imagePicture.setImageBitmap(HTTP.printPicture(user.getNumber(), (int) (height * 0.3)));
        /*이미지 출력-조건 학번을 제대로 받아올 경우*/

        /*뷰에 정보 셋팅*/
        TextView textUser = (TextView) findViewById(R.id.textUser);
        textUser.setText(user.getName() + "\n" + user.getNumber() + "\n" + user.getGrade() + "학년" + "\n" + user.getMajor());
        /*뷰에 정보 셋팅*/
    }
    private void printLogo(int width, int height) {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.main_main_logo);
        ImageView imageLogo = (ImageView) findViewById(R.id.imageLogo);
        imageLogo.setImageBitmap(Bitmap.createScaledBitmap(bitmap, (int) (width * 0.45), (int) (height * 0.075), true));
        imageLogo.setY(height * 0.2f);
    }




}