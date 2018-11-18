package labTic.services;

import commons.exceptions.ClientAlreadyExistsException;
import commons.exceptions.InvalidClientInformationException;
import labTic.ClientMain;
import labTic.business.ClientService;
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
        } catch (InvalidClientInformationException | ClientAlreadyExistsException invalidClientInformation) {
            invalidClientInformation.printStackTrace();
        }
        assertEquals(cs.findOneByEmail("rodri.lopez98@gmail.com").getEmail(), "rodri.lopez98@gmail.com");
    }
}
