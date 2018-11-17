/*package labTic;

import labTic.services.BookingService;
import labTic.services.TableBookingServiceImpl;
import labTic.services.rmi.TableBookingService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.remoting.rmi.RmiServiceExporter;

@SpringBootApplication
public class RmiServer {

    @Bean
    RmiServiceExporter exporter(@Qualifier("tableBookingServiceImpl") TableBookingService implementation) {
        Class<TableBookingService> serviceInterface = TableBookingService.class;
        RmiServiceExporter exporter = new RmiServiceExporter();
        exporter.setServiceInterface(serviceInterface);
        exporter.setService(implementation);
        exporter.setServiceName(serviceInterface.getSimpleName());
        exporter.setRegistryPort(8080);

        return exporter;
    }

    public static void main(String[] args) {
        SpringApplication.run(RmiServer.class, args);
    }
}
*/