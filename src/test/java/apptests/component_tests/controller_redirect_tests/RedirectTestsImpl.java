package apptests.component_tests.controller_redirect_tests;

import com.web.app.controller.Controller;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(Controller.class)
public class RedirectTestsImpl implements RedirectTests {
    @Autowired
    private MockMvc mockMvc;

    @Override
    @Test
    public void testRedirect() {

    }

}
