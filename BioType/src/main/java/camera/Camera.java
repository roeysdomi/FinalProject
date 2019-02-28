package camera;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;

import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.stage.Stage;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.videoio.VideoCapture;

public class Camera extends Application {
   Mat matrix = null;

   @Override
   public void start(Stage stage) throws FileNotFoundException, IOException {
      // Capturing the snapshot from the camera
	   Camera obj = new Camera();
	  
      WritableImage writableImage = obj.capureSnapShot();

      // Saving the image
      obj.saveImage();
     
   
   }
   public WritableImage capureSnapShot() {
      WritableImage WritableImage = null;

      // Loading the OpenCV core library
      System.loadLibrary( Core.NATIVE_LIBRARY_NAME );

      // Instantiating the VideoCapture class (camera:: 0)
      VideoCapture capture = new VideoCapture(0);

      // Reading the next video frame from the camera
      Mat matrix = new Mat();
      capture.read(matrix);

      // If camera is opened
      if( capture.isOpened()) {
         // If there is next video frame
         if (capture.read(matrix)) {
            // Creating BuffredImage from the matrix
            BufferedImage image = new BufferedImage(matrix.width(), 
               matrix.height(), BufferedImage.TYPE_3BYTE_BGR);
            
            WritableRaster raster = image.getRaster();
            DataBufferByte dataBuffer = (DataBufferByte) raster.getDataBuffer();
            byte[] data = dataBuffer.getData();
            matrix.get(0, 0, data);
            this.matrix = matrix;
            
            // Creating the Writable Image
            WritableImage = SwingFXUtils.toFXImage(image, null);
         }
      }
      capture.release();
      return WritableImage;
   }
   public void saveImage() throws IOException {
      // Saving the Image
      String file = "sanpshot.jpg";

      // Instantiating the imgcodecs class
      Imgcodecs imageCodecs = new Imgcodecs();

      // Saving it again 
      imageCodecs.imwrite(file, matrix);
     
   }
   public static String takepic() throws IOException
   {
	   Camera obj = new Camera();
	      WritableImage writableImage = obj.capureSnapShot();

	      // Saving the image
	      obj.saveImage();
	      return MultipartUtility.uplouadpic();
   }
   public static void main(String args[]) throws IOException {
	  
	   System.out.println( Camera.takepic());
	      // Saving the image
	      
	     
      return;
   }
}