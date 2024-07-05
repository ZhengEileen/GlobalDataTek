package pojo;

import java.util.Date;

public class Podcast {
	private String title;
	private String celebrity;
	private Date datePublished;

	public Podcast(String title, String celebrity, Date datePublished) {
		this.title = title;
		this.celebrity = celebrity;
		this.datePublished = datePublished;

	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCelebrity() {
		return celebrity;
	}

	public void setCelebrity(String celebrity) {
		this.celebrity = celebrity;
	}

	public Date getDatePublished() {
		return datePublished;
	}

	public void setDatePublished(Date datePublished) {
		this.datePublished = datePublished;
	}

	public String toString() {
		return "Podcast title:" + title + " celebrity:" + celebrity + " datePublished:" + datePublished + " ";
	}

}
