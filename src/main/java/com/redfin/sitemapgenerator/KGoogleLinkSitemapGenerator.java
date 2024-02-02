package com.redfin.sitemapgenerator;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Builds a Google Link Sitemap (to indicate alternate language pages).
 *
 * @author Sergio Vico
 * @see <a href="https://support.google.com/webmasters/answer/2620865">Creating alternate language pages Sitemaps</a>
 * @see <a href="https://developers.google.com/search/mobile-sites/mobile-seo/separate-urls?hl=en">Mobile SEO configurations | Separate URLs </a>
 */
public class KGoogleLinkSitemapGenerator extends SitemapGenerator<KGoogleLinkSitemapUrl, KGoogleLinkSitemapGenerator> {

	private static class Renderer extends AbstractSitemapUrlRenderer<KGoogleLinkSitemapUrl>
			implements ISitemapUrlRenderer<KGoogleLinkSitemapUrl> {
	
		public Class<KGoogleLinkSitemapUrl> getUrlClass() {

			return KGoogleLinkSitemapUrl.class;
		}

		public String getXmlNamespaces() {

			return "xmlns:xhtml=\"http://www.w3.org/1999/xhtml\"";
		}

		public void render(final KGoogleLinkSitemapUrl url, final StringBuilder sb, final W3CDateFormat dateFormat) {

			final StringBuilder tagSb = new StringBuilder();
			for (final Entry<URIWrapper, Map<String, String>> entry : url.getAlternates().entrySet()) {
				tagSb.append("    <xhtml:link\n");
				tagSb.append("      rel=\"alternate\"\n");
				for(final Entry<String, String> innerEntry : entry.getValue().entrySet()){
					tagSb.append("      " + innerEntry.getKey() + "=\"" + innerEntry.getValue() + "\"\n");
				}
				tagSb.append("      href=\"" + UrlUtils.escapeXml(entry.getKey().getUri().toString()) + "\"\n");
				tagSb.append("    />\n");
			}
			super.render(url, sb, dateFormat, tagSb.toString());
		}

	}

	/**
	 * Configures a builder so you can specify sitemap generator options
	 *
	 * @param baseUrl
	 *			All URLs in the generated sitemap(s) should appear under this base URL
	 * @param baseDir
	 *			Sitemap files will be generated in this directory as either "sitemap.xml" or
	 *			"sitemap1.xml" "sitemap2.xml" and so on.
	 * @return a builder; call .build() on it to make a sitemap generator
	 */
	public static SitemapGeneratorBuilder<KGoogleLinkSitemapGenerator> builder(final String baseUrl, final File baseDir)
		throws MalformedURLException {

		return new SitemapGeneratorBuilder<KGoogleLinkSitemapGenerator>(baseUrl, baseDir,
				KGoogleLinkSitemapGenerator.class);
	}

	/**
	 * Configures a builder so you can specify sitemap generator options
	 *
	 * @param baseUrl
	 *			All URLs in the generated sitemap(s) should appear under this base URL
	 * @param baseDir
	 *			Sitemap files will be generated in this directory as either "sitemap.xml" or
	 *			"sitemap1.xml" "sitemap2.xml" and so on.
	 * @return a builder; call .build() on it to make a sitemap generator
	 */
	public static SitemapGeneratorBuilder<KGoogleLinkSitemapGenerator> builder(final URL baseUrl, final File baseDir) {

		return new SitemapGeneratorBuilder<KGoogleLinkSitemapGenerator>(baseUrl, baseDir,
				KGoogleLinkSitemapGenerator.class);
	}

	/**
	 * Configures the generator with a base URL and a null directory. The object constructed is not
	 * intended to be used to write to files. Rather, it is intended to be used to obtain
	 * XML-formatted strings that represent sitemaps.
	 *
	 * @param baseUrl
	 *			All URLs in the generated sitemap(s) should appear under this base URL
	 */
	public KGoogleLinkSitemapGenerator(final String baseUrl) throws MalformedURLException {
		this(new SitemapGeneratorOptions(new URL(baseUrl)));
	}

	/**
	 * Configures the generator with a base URL and directory to write the sitemap files.
	 *
	 * @param baseUrl
	 *			All URLs in the generated sitemap(s) should appear under this base URL
	 * @param baseDir
	 *			Sitemap files will be generated in this directory as either "sitemap.xml" or
	 *			"sitemap1.xml" "sitemap2.xml" and so on.
	 * @throws MalformedURLException
	 */
	public KGoogleLinkSitemapGenerator(final String baseUrl, final File baseDir) throws MalformedURLException {
		this(new SitemapGeneratorOptions(baseUrl, baseDir));
	}

	/**
	 * Configures the generator with a base URL and a null directory. The object constructed is not
	 * intended to be used to write to files. Rather, it is intended to be used to obtain
	 * XML-formatted strings that represent sitemaps.
	 *
	 * @param baseUrl
	 *			All URLs in the generated sitemap(s) should appear under this base URL
	 */
	public KGoogleLinkSitemapGenerator(final URL baseUrl) {
		this(new SitemapGeneratorOptions(baseUrl));
	}

	/**
	 * Configures the generator with a base URL and directory to write the sitemap files.
	 *
	 * @param baseUrl
	 *			All URLs in the generated sitemap(s) should appear under this base URL
	 * @param baseDir
	 *			Sitemap files will be generated in this directory as either "sitemap.xml" or
	 *			"sitemap1.xml" "sitemap2.xml" and so on.
	 */
	public KGoogleLinkSitemapGenerator(final URL baseUrl, final File baseDir) {
		this(new SitemapGeneratorOptions(baseUrl, baseDir));
	}

	KGoogleLinkSitemapGenerator(final AbstractSitemapGeneratorOptions<?> options) {
		super(options, new Renderer());
	}
}
