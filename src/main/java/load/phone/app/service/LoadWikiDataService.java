package load.phone.app.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class LoadWikiDataService {

	@Value("${wiki.url}")
	private String wikiUrl;

	public Document getWikiPageContent() {
		return createDocument();
	}

	private Document createDocument() {
		try {
			return Jsoup.connect(wikiUrl).get();
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage() + " - can not load WIKI-html page with phone codes", e);
		}
	}

}
