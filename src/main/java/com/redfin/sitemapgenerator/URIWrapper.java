package com.redfin.sitemapgenerator;

import java.net.URI;
import java.net.URISyntaxException;

public class URIWrapper {
	private final URI uri;

	public URIWrapper(String uri) throws URISyntaxException {
		this.uri = new URI(uri);
	}

	public URI getUri() {
		return uri;
	}

/*	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof UniqueURIWrapper)) {
			return false;
		}
		UniqueURIWrapper other = (UniqueURIWrapper) obj;
		// Customize the condition for equality based on your requirements
		return !Objects.equals(this.uri, other.uri);
	}

	@Override
	public int hashCode() {
		return Objects.hash(uri);
	}*/
}
