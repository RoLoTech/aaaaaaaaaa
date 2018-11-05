/*package labTic;

import labTic.services.entities.Booking;
import labTic.services.exceptions.FullRestaurantException;
import labTic.services.rmi.TableBookingService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;

import java.time.LocalTime;

@SpringBootApplication
public class RmiClient {
    @Bean
    RmiProxyFactoryBean service() {
        RmiProxyFactoryBean rmiProxyFactory = new RmiProxyFactoryBean();
        rmiProxyFactory.setServiceUrl("rmi://localhost:8080/TableBookingService");
        rmiProxyFactory.setServiceInterface(TableBookingService.class);
        return rmiProxyFactory;
    }

    public static void main(String[] args) throws FullRestaurantException {
        TableBookingService service = SpringApplication.run(RmiClient.class, args).getBean(TableBookingService.class);
        Booking bookingOutcome = service.book(1L, LocalTime.now(), "Rolo");
        System.out.println(bookingOutcome);
    }
}
*/