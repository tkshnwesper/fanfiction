package main;

import java.io.InputStream;

import org.jsoup.Connection.KeyVal;

public class Search {
	
	class KeyValPair implements KeyVal {
		private String key;
		private String value;

		@Override
		public boolean hasInputStream() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public InputStream inputStream() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public KeyVal inputStream(InputStream arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String key() {
			return key;
		}

		@Override
		public KeyVal key(String arg0) {
			key = arg0;
			return this;
		}

		@Override
		public String value() {
			return value;
		}

		@Override
		public KeyVal value(String arg0) {
			value = arg0;
			return this;
		}

		
	}
	

}
