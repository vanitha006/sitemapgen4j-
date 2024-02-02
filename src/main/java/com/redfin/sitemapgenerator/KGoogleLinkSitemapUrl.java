package com.redfin.sitemapgenerator;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * One configurable Google Link URL. To configure, use {@link Options}
 *
 * @author Sergio Vico
 * @see Options
 * @see <a href="https://support.google.com/webmasters/answer/2620865">Creating alternate language pages Sitemaps</a>
 * @see <a href="https://developers.google.com/search/mobile-sites/mobile-seo/separate-urls?hl=en">Mobile SEO configurations | Separate URLs </a>
 */
public class KGoogleLinkSitemapUrl extends WebSitemapUrl {

	/** Options to configure URLs with alternates */
	public static class Options extends AbstractSitemapUrlOptions<KGoogleLinkSitemapUrl, Options> {
		private final Map<URIWrapper, Map<String, String>> alternates;

		private static Map<URIWrapper, Map<String, String>> convertAlternates(final Map<String, Map<String, String>> alternates)
				throws URISyntaxException {

			final Map<URIWrapper, Map<String, String>> converted = new LinkedHashMap<URIWrapper, Map<String, String>>();
			for (final Map.Entry<String, Map<String, String>> entry : alternates.entrySet()) {
				converted.put(new URIWrapper(entry.getKey()), new LinkedHashMap<String, String>(entry.getValue()));
			}
			return converted;
		}

		/**
		 * Options constructor with the alternates configurations
		 *
		 * @param url Base URL into which we will be adding alternates
		 * @param alternates Map&lt;String, Map&lt;String, String&gt;&gt; where the key is the href and
		 *					the value is a generic Map&lt;String, String&gt; holding the attributes of
		 *					the link (e.g. hreflang, media, ...)
		 */
		public Options(final String url, final Map<String, Map<String, String>> alternates) throws URISyntaxException, MalformedURLException {

			this(new URL(url), convertAlternates(alternates));
		}

		/**
		 * Options constructor with the alternates configurations
		 *
		 * @param url Base URL into which we will be adding alternates
		 * @param alternates Map&lt;URL, Map&lt;String, String&gt;&gt; where the key is the href and
		 *					the value is a generic Map&lt;String, String&gt; holding the attributes of
		 *					the link (e.g. hreflang, media, ...)
		 */
		public Options(final URL url, final Map<URIWrapper, Map<String, String>> alternates) {
			super(url, KGoogleLinkSitemapUrl.class);
			this.alternates = new LinkedHashMap<URIWrapper, Map<String, String>>(alternates);
		}
	}

	private final Map<URIWrapper, Map<String, String>> alternates;

	/**
	 * Constructor specifying the URL and the alternates configurations with Options object
	 *
	 * @param options Configuration object to initialize the GoogleLinkSitemapUrl with.
	 * @see Options#Options(String, Map)
	 */
	public KGoogleLinkSitemapUrl(final Options options) {
		super(options);
		alternates = options.alternates;
	}

	/**
	 * Constructor specifying the URL as a String and the alternates configurations
	 *
	 * @param url Base URL into which we will be adding alternates
	 * @param alternates Map&lt;String, Map&lt;String, String&gt;&gt; where the key is the href and
	 *					the value is a generic Map&lt;String, String&gt; holding the attributes of
	 *					the link (e.g. hreflang, media, ...)
	 */
	public KGoogleLinkSitemapUrl(final String url, final Map<String, Map<String, String>> alternates) throws URISyntaxException, MalformedURLException {
		this(new Options(url, alternates));
	}

	/**
	 * Constructor specifying the URL as a URL and the alternates configurations
	 *
	 * @param url Base URL into which we will be adding alternates
	 * @param alternates Map&lt;String, Map&lt;String, String&gt;&gt; where the key is the href and
	 *					the value is a generic Map&lt;String, String&gt; holding the attributes of
	 *					the link (e.g. hreflang, media, ...)
	 */
	public KGoogleLinkSitemapUrl(final URL url, final Map<URIWrapper, Map<String, String>> alternates) {
		this(new Options(url, alternates));
	}

	public Map<URIWrapper, Map<String, String>> getAlternates() {

		return this.alternates;
	}
}
