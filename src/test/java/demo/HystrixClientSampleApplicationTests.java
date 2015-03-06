package demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.cloud.netflix.hystrix.sample.HystrixSampleApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = HystrixSampleApplication.class)
@WebAppConfiguration
@IntegrationTest("server.port=0")
public class HystrixClientSampleApplicationTests {

	private Logger logger = Logger.getLogger(HystrixClientSampleApplicationTests.class.getName());

	@Value("${local.server.port}")
	protected int port = 0;

	@Value("${test.interval:100}")
	private int interval;

	@Value("${test.requests:10}")
	private int requests;

	@Test
	public void driveLoad() throws InterruptedException {
		TestRestTemplate restTemplate = new TestRestTemplate();
		String url = "http://localhost:" + port + "/";

		long start = System.currentTimeMillis();

		int count = requests;

		for (; count > 0; count--) {
			ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
			assertEquals(HttpStatus.OK, response.getStatusCode());

			logger.info("Got response [" + response.getBody() + "]");

			Thread.sleep(interval);
		}

		long elapsed = System.currentTimeMillis() - start;

		logger.info("Sent " + requests + " requests in " + elapsed + " milliseconds");
	}
}
