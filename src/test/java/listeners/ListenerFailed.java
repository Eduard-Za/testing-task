package listeners;

import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import static pages.Header.deleteProductsIfPresentIfTestFailed;

public class ListenerFailed extends TestListenerAdapter {

    @Override
    public void onTestFailure(ITestResult result) {
        try {
            deleteProductsIfPresentIfTestFailed();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

