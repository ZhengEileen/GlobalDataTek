package pojo;

import java.util.ArrayList;
import java.util.List;

public class Playlist {
	private String name;
	private List<Song> songs;
	private List<Podcast> podcasts;

	public Playlist(String name) {
		this.name = name;
		this.songs = new ArrayList<>();
		this.podcasts = new ArrayList<>();
	}

	public String getName() {
		return name;
	}

	public List<Song> getSongs() {
		return songs;
	}

	public List<Podcast> getPodcasts() {
		return podcasts;
	}

	public void addSong(Song song) {
		songs.add(song);
	}

	public void addPodcast(Podcast podcast) {
		podcasts.add(podcast);
	}

}
