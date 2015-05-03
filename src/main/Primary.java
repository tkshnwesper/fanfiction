package main;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Primary {
	
	class Forbidden {
		private Vector<String> vector;
		
		Forbidden(String... varargs) {
			vector = new Vector<String>();
			for(String s : varargs) {
				vector.add(s);
			}
		}
		
		boolean contains(String s) {
			return vector.contains(s);
		}
		
	}
	
	Forbidden forbiddenNewsURLs = new Forbidden("http://blog.fictionpress.com", "https://twitter.com/FICTIONPRESS");
	Document doc;
	
	Elements extractLinks(String elementId) {
		Element element = doc.getElementById(elementId);
		Elements a_elements = element.getElementsByTag("a");
		return a_elements;
	}
	
	Elements getFanfictionCategories() {
		return extractLinks("gui_table1i");
	}
	
	Elements getCrossoverCategories() {
		return extractLinks("gui_table2i");
	}
	
	class NewsExcerpt {
		String date;
		String text;
		Element element;
		
		void parse() {
			int dateEnds = text.indexOf(':');
			if(dateEnds != -1) {
				date = text.substring(0, dateEnds);
				text = text.substring(dateEnds);
			}
		}
		
		NewsExcerpt(Element e) {
			text = e.parent().text();
			element = e;
			parse();
		}
	}
	
	class NewsExcerpts implements Collection<NewsExcerpt> {
		private Vector<NewsExcerpt> vector;
		
		NewsExcerpts() {
			vector = new Vector<NewsExcerpt>();
		}

		@Override
		public boolean add(NewsExcerpt arg0) {
			return vector.add(arg0);
		}

		@Override
		public boolean addAll(Collection<? extends NewsExcerpt> arg0) {
			return vector.addAll(arg0);
		}

		@Override
		public void clear() {
			vector.clear();
		}

		@Override
		public boolean contains(Object arg0) {
			// TODO Auto-generated method stub
			return vector.contains(arg0);
		}

		@Override
		public boolean containsAll(Collection<?> arg0) {
			// TODO Auto-generated method stub
			return vector.containsAll(arg0);
		}

		@Override
		public boolean isEmpty() {
			return vector.isEmpty();
		}

		@Override
		public Iterator<NewsExcerpt> iterator() {
			return vector.iterator();
		}

		@Override
		public boolean remove(Object arg0) {
			return vector.remove(arg0);
		}

		@Override
		public boolean removeAll(Collection<?> arg0) {
			return vector.removeAll(arg0);
		}

		@Override
		public boolean retainAll(Collection<?> arg0) {
			// TODO Auto-generated method stub
			return vector.retainAll(arg0);
		}

		@Override
		public int size() {
			return vector.size();
		}

		@Override
		public Object[] toArray() {
			return vector.toArray();
		}

		@Override
		public <T> T[] toArray(T[] arg0) {
			return vector.toArray(arg0);
		}

	}
	
	Elements removeForbidden(Elements elements, Forbidden f) {
		Elements retval = new Elements();
		for(Element e : elements) {
			if(!f.contains(e.attr("href"))) {
				retval.add(e);
			}
		}
		return retval;
	}
	
	Elements getNewsLinks() {
		return removeForbidden(extractLinks("gui_table3i"), forbiddenNewsURLs);
	}
	
	NewsExcerpt getNewsExcerpt(Element element) {
		return new NewsExcerpt(element);
	}
	
	NewsExcerpts getNewsExcerpts() {
		Elements elements = getNewsLinks();
		NewsExcerpts nes = new NewsExcerpts();
		for(Element e : elements) {
			nes.add(getNewsExcerpt(e));
		}
		return nes;
	}
	
	Primary() throws ConnectionTimeout, IOException {
		
		doc = Connection.connect("https://www.fanfiction.net/").parse();

		NewsExcerpts nes = getNewsExcerpts();
		for(NewsExcerpt ne : nes) {
			System.out.println(ne.date);
		}
	}
	
	public static void main(String[] args) throws ConnectionTimeout, IOException {
		new Primary();
	}
}
