package test.pivotal.pal.tracker;

import io.pivotal.pal.tracker.controllers.WelcomeController;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.actuate.metrics.CounterService;

import static org.mockito.Mockito.*;

import static org.assertj.core.api.Assertions.assertThat;

public class WelcomeControllerTest {

    private CounterService counter;
    @Before
    public void setUp() throws Exception {
        counter = mock(CounterService.class);
    }

    @Test
    public void itSaysHello() throws Exception {
        WelcomeController controller = new WelcomeController("A welcome message", counter);
        assertThat(controller.sayHello()).isEqualTo("A welcome message");
    }
}
