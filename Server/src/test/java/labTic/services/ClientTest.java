package labTic.services;


import labTic.services.exceptions.ClientAlreadyExists;
import labTic.services.exceptions.InvalidClientInformation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.fail;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ClientTest {

    @Autowired
    ClientService cs;

    @Test
    public void testBasic() {

        try {
            cs.addClient(12345l, "daniel", "colonia 1234");
        } catch (InvalidClientInformation invalidClientInformation) {

            fail(invalidClientInformation.getMessage());

        } catch (ClientAlreadyExists clientAlreadyExists) {

            fail(clientAlreadyExists.getMessage());

        }


        try {

            // Se prueba agregar el mismo cliente con la cedula

            cs.addClient(12345l, "daniel","colonia 1234");

        } catch (InvalidClientInformation invalidClientInformation) {
            invalidClientInformation.printStackTrace();
        } catch (ClientAlreadyExists clientAlreadyExists) {

            // Ok flujo correcto
        }
    }


}
