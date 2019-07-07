package load.phone.app.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ParseServiceUnitTest {

	private ParseService parseService = new ParseService();

	@Test
	public void testParseHtml() throws Exception {
		Document document = mock(Document.class);

		Elements first = mock(Elements.class);
		String testHtml = "<table>" +
				"<tr>\n" +
				"<td>Afghanistan</td>\n" +
				"<td align=\"right\"><span style=\"display:none\" class=\"sortkey\">7004930000000000000♠</span><a href=\"/wiki/%2B93\" class=\"mw-redirect\" title=\"+93\">+93</a></td>\n" +
				"<td>UTC+04:30</td>\n" +
				"<td></td>\n" +
				"</tr>\n" +
				"<tr>\n" +
				"<td>Abkhazia</td>\n" +
				"<td align=\"right\"><span style=\"display:none\" class=\"sortkey\">7004784000000000000♠</span>" +
				"<a href=\"/wiki/%2B7_840\" class=\"mw-redirect\" title=\"+7 840\">+7 840</a>, " +
				"<a href=\"/wiki/%2B7_940\" class=\"mw-redirect\" title=\"+7 940\">+7 940</a>," +
				"<sup id=\"cite_ref-zone7_6-0\" class=\"reference\">" +
				"<a href=\"#cite_note-zone7-6\">[notes 1]</a></sup>" +
				"<a href=\"/wiki/%2B995_44\" class=\"mw-redirect\" title=\"+995 44\">+995 44</a>" +
				"<sup id=\"cite_ref-abkhazia_4-1\" class=\"reference\">" +
				"<a href=\"#cite_note-abkhazia-4\">[3]</a></sup></td>\n" +
				"<td>UTC+03:00</td>\n" +
				"<td></td>\n" +
				"</tr>\n" +
				"<tr>\n" +
				"<td>Åland Islands</td>\n" +
				"<td align=\"right\"><span style=\"display:none\" class=\"sortkey\">7004358180000000000♠</span>" +
				"<a href=\"/wiki/%2B358_18\" class=\"mw-redirect\" title=\"+358 18\">+358 18</a></td>\n" +
				"<td>UTC+02:00</td>\n" +
				"<td>UTC+03:00</td>\n" +
				"</tr>\n" +
				"<tr>\n" +
				"<td>Albania</td>\n" +
				"<td align=\"right\"><span style=\"display:none\" class=\"sortkey\">7004355000000000000♠</span>" +
				"<a href=\"/wiki/%2B355\" class=\"mw-redirect\" title=\"+355\">+355</a></td>\n" +
				"<td>UTC+01:00</td>\n" +
				"<td>UTC+02:00</td>\n" +
				"</tr>" +
				"</table>";
		Element tableData = Jsoup.parse(testHtml);

		when(document.select(".sortable")).thenReturn(first);
		when(first.first()).thenReturn(tableData);

		assertEquals(3, parseService.parseHtml(document).filter(t -> t.getCountry().equals("Abkhazia")).count());
		assertEquals(1, parseService.parseHtml(document).filter(t -> t.getCountry().equals("Åland Islands")).count());
		assertEquals(1, parseService.parseHtml(document).filter(t -> t.getCountry().equals("Albania")).count());

		assertEquals(1, parseService.parseHtml(document).filter(t -> t.getCode().equals("7840")).count());
		assertEquals(1, parseService.parseHtml(document).filter(t -> t.getCode().equals("7940")).count());
		assertEquals(1, parseService.parseHtml(document).filter(t -> t.getCode().equals("99544")).count());
		assertEquals(1, parseService.parseHtml(document).filter(t -> t.getCode().equals("35818")).count());
		assertEquals(1, parseService.parseHtml(document).filter(t -> t.getCode().equals("355")).count());

		assertEquals(5, parseService.parseHtml(document).count());
	}

	@Test
	public void testFormatPhoneCode() throws Exception {
		assertEquals("37123492", parseService.formatPhoneCode("+371 234 92"));
		assertEquals("1137123492", parseService.formatPhoneCode("+113 7123 4 9 2"));
	}
}
