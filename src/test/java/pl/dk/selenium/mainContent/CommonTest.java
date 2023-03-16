package pl.dk.selenium.mainContent;

import com.capgemini.mrchecker.test.core.utils.PageFactory;
import pl.dk.categories.App;
import pl.dk.categories.Type;
import pl.dk.selenium.basetest.BaseTestGUI;

@App.Msalamon
@Type.Regression
public abstract class CommonTest extends BaseTestGUI {
    protected final MainPage     MainPage          = PageFactory.getPageInstance(MainPage.class);

    @Override
    public void setUpTest() {
        startPage();
    }

    @Override
    public void tearDownTest() {
    }

    public void startPage(){
        MainPage.openHomePagePageNoAssert();
    }
}
