package service;

import pojo.Playlist;
import pojo.Podcast;
import pojo.Song;

public class JukeboxService {
	private SongService songService;
	private PodcastService podcastService;
	private PlaylistService playlistService;

	public JukeboxService() {
		this.songService = new SongService();
		this.podcastService = new PodcastService();
		this.playlistService = new PlaylistService();
	}

	public void addSong(Song song) {
		songService.addSong(song);
	}

	public void addPodcast(Podcast podcast) {
		podcastService.addPodcast(podcast);
	}

	public void addPlaylist(Playlist playlist) {
		playlistService.addPlaylist(playlist);
	}

	public void displayHomePage() {
		System.out.println("Songs in the catalog:");
		for (Song song : songService.getAllSongs()) {
			System.out.println(song);
		}
		System.out.println("\nPodcasts in the catalog:");
		for (Podcast podcast : podcastService.getAllPodcasts()) {
			System.out.println(podcast);
		}
	}
}
