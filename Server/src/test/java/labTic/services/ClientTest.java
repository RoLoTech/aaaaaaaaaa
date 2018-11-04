package labTic.services;

import labTic.ClientMain;
import labTic.services.exceptions.ClientAlreadyExists;
import labTic.services.exceptions.InvalidClientInformation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@SpringBootTest(classes = ClientMain.class)
@RunWith(SpringRunner.class)
public class ClientTest {
    @Autowired
    ClientService cs;

    @Test
    public void testAdd() {
        try {
            cs.addClient("Rodrigo", "López", "rodri.lopez98@gmail.com", "RoLoTech", "rodrigo270", "098273340");
        } catch (InvalidClientInformation | ClientAlreadyExists invalidClientInformation) {
            invalidClientInformation.printStackTrace();
        }
        assertEquals(cs.findOneByEmail("rodri.lopez98@gmail.com").getEmail(), "rodri.lopez98@gmail.com");
    }
}
