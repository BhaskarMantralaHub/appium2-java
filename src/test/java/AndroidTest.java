import config.AndroidDriverManager;
import config.IosDriverManager;
import org.testng.annotations.Test;

public class AndroidTest {

    @Test
    public void launchApp() {
        AndroidDriverManager androidDriverManager = new AndroidDriverManager();
        androidDriverManager.launch();
    }

    @Test
    public void launchIosApp() {
        IosDriverManager iosDriverManager = new IosDriverManager();
        iosDriverManager.launch();
    }
}
