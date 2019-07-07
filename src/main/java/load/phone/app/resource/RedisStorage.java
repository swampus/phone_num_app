package load.phone.app.resource;

import com.lambdaworks.redis.api.rx.RedisReactiveCommands;
import org.springframework.stereotype.Service;
import rx.Observable;

public class RedisStorage implements IStorage {

	private RedisReactiveCommands<String, String> commands;

	public RedisStorage(RedisReactiveCommands<String, String> commands) {
		this.commands = commands;
	}

	@Override
	public Observable<Void> putPhone(String phoneCode, String country) {
		return commands.set(phoneCode, country).map(t -> null);
	}

	@Override
	public Observable<String> getCountry(String phoneCode) {
		return commands.get(phoneCode);
	}
}
