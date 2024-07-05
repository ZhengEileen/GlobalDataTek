package pojo;

public class Song {

	private String title;
	private String artist;
	private String genre;
	private String album;

	public Song(String title, String artist, String genre, String album) {
		this.title = title;
		this.artist = artist;
		this.genre = genre;
		this.album = album;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getAlbum() {
		return album;
	}

	public void setAlbum(String album) {
		this.album = album;
	}

	public String toString() {
		return "Song title:" + title + " artist:" + artist + " genre:" + genre + " album:" + album + " ";
	}

}
