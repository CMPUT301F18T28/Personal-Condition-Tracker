package ca.ualberta.cs.personal_condition_tracker;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Base64;

import junit.framework.TestCase;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import ca.ualberta.cs.personal_condition_tracker.Model.Photograph;

public class PhotographTest extends TestCase {
//        public void testGetbase64EncodedString(){
//            String filename = "TestFilename";
//            File directory = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
//            String currentTime = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
//            File image = File.createTempFile("tracker_" + currentTime,".jpg", directory);
//            Bitmap bitmap = BitmapFactory.decodeFile(new File());
//            bitmap = Bitmap.createScaledBitmap(bitmap,60,140,true);
//            ByteArrayOutputStream baos = new ByteArrayOutputStream();
//            bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
//            byte[] b = baos.toByteArray();
//            this.base64EncodedString = Base64.encodeToString(b, Base64.DEFAULT);
//            Photograph photograph = new Photograph(filename);
//            assertTrue(photograph.getFilename().equals(filename));
//        }
//
//        public void testSetFilename(){
//            String filename = "TestFilename";
//            Photograph photograph = new Photograph(filename);
//            String filename2 = "New Filename";
//            photograph.setFilename(filename2);
//            assertTrue(photograph.getFilename().equals(filename2));
//        }
//        //TODO: Update for Bitmap
//        public void testGetThumbnail(){
//            String testString = "TestString";
//            byte[] thumbnail = testString.getBytes();
//            Photograph photograph = new Photograph("Filename", thumbnail);
//            String testString2 = "TestString2";
//            byte[] thumbnail2 = testString2.getBytes();
//            photograph.setThumbnail(thumbnail2);
//            assertTrue(Arrays.equals(photograph.getThumbnail(), thumbnail2));
//        }
//        public void testSetThumbnail(){
//            String testString = "TestString";
//            byte[] thumbnail = testString.getBytes();
//            Photograph photograph = new Photograph("Filename", thumbnail);
//            assertTrue(Arrays.equals(photograph.getThumbnail(), thumbnail));
//
//        }

}