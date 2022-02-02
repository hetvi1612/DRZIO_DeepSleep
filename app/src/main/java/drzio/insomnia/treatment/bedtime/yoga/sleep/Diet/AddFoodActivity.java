package drzio.insomnia.treatment.bedtime.yoga.sleep.Diet;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.loader.content.CursorLoader;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import drzio.insomnia.treatment.bedtime.yoga.sleep.Constants;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Database.TinyDB;
import drzio.insomnia.treatment.bedtime.yoga.sleep.webservice.BackpainAPIClient;
import drzio.insomnia.treatment.bedtime.yoga.sleep.webservice.BackpainAPIInterface;
import drzio.insomnia.treatment.bedtime.yoga.sleep.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static retrofit2.converter.scalars.ScalarsConverterFactory.create;

public class AddFoodActivity extends AppCompatActivity {
    EditText food_name, food_calories, food_desc;
    ImageView food_image;
    Button submit_food;
    String name, calories, descripation, foodimg, userid/* = "60ace788086b12b3beee9d72"*/;
    Uri selectedImage;
    InputStream imageStream;
    String encodedImage;
    Bitmap bitmap;
    CardView cardviewgallry;
    TinyDB tinyDB;
String BASEURL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window =/* ((Activity_Home)getActivity()).*/getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(
                    getResources().getColor(R.color.white));
        }
        food_name = (EditText) findViewById(R.id.foodtitle);
        food_calories = (EditText) findViewById(R.id.food_calories);
        food_desc = (EditText) findViewById(R.id.food_descripation);
        food_image = (ImageView) findViewById(R.id.food_image);
        submit_food = (Button) findViewById(R.id.submit_food);
        tinyDB = new TinyDB(this);
        cardviewgallry = findViewById(R.id.cardviewgallry);
        BASEURL=tinyDB.getString(Constants.NewBaseUrl);
        cardviewgallry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, 100);
            }
        });

        submit_food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = food_name.getText().toString();
                calories = food_calories.getText().toString();
                descripation = food_desc.getText().toString();
                userid = tinyDB.getString(Constants.USERID);
                AddFoodApi(name, calories, descripation, userid, "data:image/jpeg;base64," + encodedImage);

                Log.e("foodname", String.valueOf(name));

                Log.e("colories", String.valueOf(calories));
                Log.e("descripation", String.valueOf(descripation));
                Intent i = new Intent(AddFoodActivity.this, CustomUpdateActivity.class);
                startActivity(i);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK && data != null) {
            //the image URI
            selectedImage = data.getData();
            foodimg = selectedImage.toString();
            food_image.setImageURI(selectedImage);

            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (bitmap != null) {
                //image.setBackground(getDrawable(R.color.white));
                // image.setImageBitmap(bitmap);
                byte[] imageBytes = imageToByteArray(bitmap);
                encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT); // actual conversion to Base64 String Image
                //base64Text.setText(encodedImage); // display the Base64 String Image encoded text
            }












        /*    File file = new File(getRealPathFromURI(selectedImage));


            try {
                imageStream = getContentResolver().openInputStream(selectedImage);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();

                Bitmap bitmap = BitmapFactory.decodeStream(imageStream);

                bitmap.compress(Bitmap.CompressFormat.JPEG, 90, baos);

                byte[] imageBytes = baos.toByteArray();

                encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
*/

            Log.e("encodedImage", String.valueOf(encodedImage));
            //calling the upload file method after choosing the file
            //   uploadFile(selectedImage, "My Image");
        }
    }
  /*  private String encodeImage(String path)
    {
        File imagefile = new File(path);
        FileInputStream fis = null;
        try{
            fis = new FileInputStream(imagefile);
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }
        Bitmap bm = BitmapFactory.decodeStream(fis);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG,90,baos);
        byte[] b = baos.toByteArray();
        String encImage = Base64.encodeToString(b, Base64.DEFAULT);
        //Base64.de
        return encImage;
    }
*/


    private byte[] imageToByteArray(Bitmap bitmapImage) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmapImage.compress(Bitmap.CompressFormat.JPEG, 20, baos);
        return baos.toByteArray();
    }

    private String encodeImage(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 90, baos);
        byte[] b = baos.toByteArray();
        String encImage = Base64.encodeToString(b, Base64.DEFAULT);

        return encImage;
    }

    private void AddFoodApi(String name, String calories, String descripation, String userid, String fileUri) {


        //      BackpainAPIInterface   apiInterface = (BackpainAPIInterface) CelluliteAPIClient.getCelluliteClient1().create(BackpainAPIInterface.class);
        try {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASEURL)
                    .addConverterFactory(create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            BackpainAPIInterface apiInterface = (BackpainAPIInterface) BackpainAPIClient.getCelluliteClient1().create(BackpainAPIInterface.class);

            JSONObject paramObject = new JSONObject();
            paramObject.put("food_name", name);
            paramObject.put("user_id", userid);
            paramObject.put("calories", calories);
            paramObject.put("description", descripation);
            //   paramObject.put("image", fileUri);


            Call<AddFood> userCall = apiInterface.getaddfood(paramObject.toString());
            userCall.enqueue(new Callback<AddFood>() {
                @Override
                public void onResponse(Call<AddFood> call, Response<AddFood> response) {
                    try {


                        // Log.e("response", String.valueOf(response.body()));
                        AddFood loginData = response.body();
                        String foodname = loginData.getFoodName();
                        int colories = loginData.getCalories();
                        String descripation = loginData.getDescription();

                      /*  Log.e("foodname1", String.valueOf(foodname));

                        Log.e("colories1", String.valueOf(colories));
                        Log.e("descripation1", String.valueOf(descripation));
                        Log.e("images1", String.valueOf(loginData.getImage()));*/

                    } catch (Exception e) {

                    }
                }

                @Override
                public void onFailure(Call<AddFood> call, Throwable t) {
                    Log.e("response1", String.valueOf(t));
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private String getRealPathFromURI(Uri contentUri) {
        String[] proj = {MediaStore.Images.Media.DATA};
        CursorLoader loader = new CursorLoader(this, contentUri, proj, null, null, null);
        Cursor cursor = loader.loadInBackground();
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(column_index);
        cursor.close();
        return result;
    }
}