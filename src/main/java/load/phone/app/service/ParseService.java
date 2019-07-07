package load.phone.app.service;

import com.google.common.annotations.VisibleForTesting;
import load.phone.app.resource.model.CountryPhonecodeRecord;
import org.apache.commons.lang.math.NumberUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class ParseService {

	private static final int FIRST = 0;
	private static final int TD_CODE_INDEX = 1;

	public Stream<CountryPhonecodeRecord> parseHtml(Document document) {

		Element tableData = document.select(".sortable").first();
		Elements trsData = tableData.select("tr");
		trsData.remove(FIRST);

		return trsData.stream()
				.flatMap(t -> {
					Elements tdsData = t.select("td");
					String country = tdsData.first().text();
					Element tdWithCodes = tdsData.get(TD_CODE_INDEX);
					Elements codes = tdWithCodes.select("a");
					return codes.stream()
							.map(code -> new CountryPhonecodeRecord(formatPhoneCode(code.text()), country));
				}).filter(t -> NumberUtils.isDigits(t.getCode()));
	}

	@VisibleForTesting
	String formatPhoneCode(String code) {
		return code.replace("+", "").replace(" ", "");
	}

}
