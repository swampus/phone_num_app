package load.phone.app.resource;

import com.google.common.annotations.VisibleForTesting;
import com.lambdaworks.redis.RedisClient;
import com.lambdaworks.redis.api.rx.RedisReactiveCommands;
import load.phone.app.exception.ApplicationInitilizationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutionException;

@Configuration
public class StorageConfig {

	@Value("${storage.type}")
	private String storageType;

	@Value("${storage.host}")
	private String host;

	@Bean
	public IStorage storage() {
		return createStorage(storageType);
	}


	@VisibleForTesting
	IStorage createStorage(String type) {
		switch (type) {
			case "redis":
				return createRedisStorage();
			case "simple":
				return new HashMapStorage();
			default:
				throw new ApplicationInitilizationException("Unknown Storage type configured: " + type);
		}
	}

	@VisibleForTesting
	RedisStorage createRedisStorage() {
		RedisClient client = RedisClient.create("redis://" + host);
		RedisReactiveCommands<String, String> reactiveCommands = client.connect().reactive();
		try {
			reactiveCommands.flushdb().toBlocking().toFuture().get();
		} catch (InterruptedException | ExecutionException e) {
			throw new RuntimeException("Problems with redis database: " + e.getMessage(), e);
		}
		return new RedisStorage(reactiveCommands);
	}

}
