package pro.cuber.wca;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pro.cuber.wca.service.KinchCalculationService;

@SpringBootApplication
public class WcaDataApplication implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(WcaDataApplication.class);

    private KinchCalculationService kinchCalculationService;

    public static void main(String[] args) {
        SpringApplication.run(WcaDataApplication.class, args);
    }

    @Override
    public void run(String... args) {
        long start = System.currentTimeMillis();
        kinchCalculationService.calculateKinch();
        logger.info("Total time: " + (System.currentTimeMillis() - start) / 1000.0 + " seconds.");
    }

    @Autowired
    public void setKinchCalculationService(KinchCalculationService kinchCalculationService) {
        this.kinchCalculationService = kinchCalculationService;
    }
}
