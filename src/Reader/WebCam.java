package reader;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

public class WebCam {
	public static String init(){
		Webcam webcam = Webcam.getDefault(); // non-default (e.g. USB) webcam can be used too
		webcam.open();
		do{
			Result result = null;
			BufferedImage image = null;
	
			if (webcam.isOpen()) {
			    if ((image = webcam.getImage()) == null) {
			        continue;
			    }
	
			    LuminanceSource source = new BufferedImageLuminanceSource(image);
			    BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
			    try {
			        result = new MultiFormatReader().decode(bitmap);
			    } catch (NotFoundException e) {
			        // fall thru, it means there is no QR code in image
			    }
			}
	
			if (result != null) {
			    return( result.getText() );
			}
			return null;
		}while(true);
	}
}