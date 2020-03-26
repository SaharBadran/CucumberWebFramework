package VodafoneFramework.utilities;

import VodafoneFramework.common.Paths;
import com.github.romankh3.image.comparison.ImageComparison;
import com.github.romankh3.image.comparison.ImageComparisonUtil;
import com.github.romankh3.image.comparison.model.ImageComparisonResult;
import com.github.romankh3.image.comparison.model.ImageComparisonState;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageValidator {

    private static ImageComparisonState state;
    private static BufferedImage expectedImage;
    private static BufferedImage actualImage;
    private static File resultDestinationImage;

    public static ImageComparisonState getImageComparisonState(String actual , String expected, String result){

        try {
            expectedImage = ImageIO.read(new File(Paths.TEST_EXPECTED_SCREENSHOTS+expected));
            actualImage = ImageIO.read(new File(Paths.TEST_ACTUAL_SCREENSHOTS+actual));
            resultDestinationImage = new File(Paths.TEST_RESULTS_IMAGE_COMPARISON+result);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ImageComparison imageComparison = new ImageComparison(expectedImage, actualImage);
        imageComparison.setThreshold(10);
        imageComparison.getThreshold();
        imageComparison.setPixelToleranceLevel(0.2);
        imageComparison.getPixelToleranceLevel();
        ImageComparisonResult imageComparisonResult = imageComparison.compareImages();
        ImageComparisonState imageComparisonState = imageComparisonResult.getImageComparisonState();
        BufferedImage resultImage = imageComparisonResult.getResult();
        ImageComparisonUtil.saveImage(resultDestinationImage,resultImage);
        return imageComparisonState;
    }
    public static boolean validateImage(String actual , String expected, String result){
        state = getImageComparisonState(actual,expected, result);
        switch (state){
            case MATCH:
                return true;
            default:
                return false;
        }
    }

}
