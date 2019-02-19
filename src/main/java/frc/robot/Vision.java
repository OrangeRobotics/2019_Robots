package frc.robot;

//Imports
	//Java imports
import java.util.ArrayList;

	//OpenCV imports
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.opencv.imgproc.Moments;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;

//WPI imports
import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoCamera.WhiteBalance;
import edu.wpi.first.wpilibj.CameraServer;

//Default contructor
public class Vision
{
	//Decalre cameras
	private UsbCamera cargoCamera;
	private UsbCamera hatchCamera;

	//Construct CameraServer object
	private CameraServer cameraServer = CameraServer.getInstance();
	
	//Decalre CvSinks
	private CvSink cargoFrameGrabber;
	private CvSink hatchFrameGrabber;

	//Decalre CvSource
	private CvSource outputSteam;

	//Declare frames
	private Mat originalFrame;
	private Mat processedFrame;

	//Final values for image resolution 
	private static final int STANDARD_IMG_WIDTH = 160;
	private static final int STANDARD_IMG_HEIGHT = 120;

	//Final HSV thresholds for cargo(orange ball)
	private final Scalar HSV_THRESHOLD_LOWER = new Scalar(0.0, 162.8, 240.7);
	private final Scalar HSV_THRESHOLD_UPPER = new Scalar(29.5, 224.5, 255.0);

	//Singelton instance
	private static final Vision instance = new Vision();


	//Default constructor for the vision class
	public Vision()
	{
		//Initialize each camera with a channel and name, pushes non-processed images
		cargoCamera = cameraServer.startAutomaticCapture("Cargo Camera",0);
		hatchCamera = cameraServer.startAutomaticCapture("Hatch Camera",1);

		//Configure resoltuion, FPS, exposure, brightness and white-balance
		configureCamera(cargoCamera, false);
		configureCamera(hatchCamera, false);

		//Initialize frame grabber used to grab individual frames from video stream to be processed later
		cargoFrameGrabber = cameraServer.getVideo(cargoCamera);
		hatchFrameGrabber = cameraServer.getVideo(hatchCamera);

		//Push processed or unprocessed frames
		outputSteam = cameraServer.putVideo("Processed Video", STANDARD_IMG_WIDTH, STANDARD_IMG_HEIGHT);
	}

	/*Apply an HSV filter, filters the image based on hue, saturation and value(brightness sort of)
	*@param: lowerHSVBounds, the minimum values for the filtration
	*@param: upperHSVBounds, the maximum values for the filtration
	*
	*@return: processedFrame, binary image
	*/
	public Mat  getHSVFitlteredImage(Scalar lowerHSVBounds, Scalar upperHSVBounds)
	{
		//Grab frames from cargo camera to be processed
		cargoFrameGrabber.grabFrame(originalFrame);

		//Covert the BGR image to a HSV image
		Imgproc.cvtColor(originalFrame, processedFrame, Imgproc.COLOR_BGR2HSV);

		//Apply hsv filter
		Core.inRange(originalFrame, HSV_THRESHOLD_LOWER, HSV_THRESHOLD_UPPER, processedFrame);

		//Return processed frame
		return processedFrame;
	}

	/**Gets a list of contorus from a binary image, stores them in an array list
	 * 
	 * @param image, binary frame
	 * @return contoursList
	 */
	public ArrayList<MatOfPoint> findExternalContours(Mat image)
	{
		//Empty arraylist of mat points to store contours in 
		ArrayList <MatOfPoint> contoursList = new ArrayList<MatOfPoint>();

		//Mode and method variables, only find external contours
		int mode = Imgproc.RETR_EXTERNAL;
		//Again I don't knwo what this means but Simon did so it should work
		int method = Imgproc.CHAIN_APPROX_SIMPLE;

		//Simon did it for overlapping contours i don't know what is does!!
		Mat hierarchy = new Mat();

		return contoursList;
	}

	public Point findContourCenter(MatOfPoint foundContours)
	{
		//Decalre the center point
		Point centerPoint = new Point();
		//Simon did it, i don't know what is does but it's here
		Moments moments = Imgproc.moments(foundContours);

		//Find the x position of the center
		double xCenter = moments.get_m10()/ moments.get_m00();

		//Find the y position of the center
		double yCenter = moments.get_m01()/ moments.get_m00();

		//Set the X and Y values of the center
		centerPoint.x = xCenter;
		centerPoint.y = yCenter;

		return centerPoint;
	}

	/**
	 * 
	 * @param camera, camera object that will be configured
	 * @param targetingCamera, boolean indicating wether the camera is going to be used for image processing
	 */
	public void configureCamera(UsbCamera camera, boolean targetingCamera)
	{
		camera.setResolution(STANDARD_IMG_WIDTH, STANDARD_IMG_HEIGHT);
		camera.setFPS(15);
		if(targetingCamera)
		{
			camera.setExposureManual(5);
		}
		else
		{
			camera.setExposureAuto();
		}

		camera.setBrightness(50);
		camera.setWhiteBalanceManual(WhiteBalance.kFixedIndoor);

	}
}
