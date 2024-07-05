package service;

import java.util.Scanner;
import pojo.Playlist;
import pojo.Song;
import pojo.Podcast;
import dao.PlaylistDAO;

public class PlaylistService {
	private PlaylistDAO playlistDAO;

	public PlaylistService() {
		this.playlistDAO = new PlaylistDAO();
	}

	public void createPlaylist(Scanner scanner) {
		System.out.println("Please enter the name of your playlist:");
		String name = scanner.nextLine().trim();
		Playlist playlist = new Playlist(name);
		playlistDAO.addPlaylist(playlist);
		System.out.println("Your playlist has been created successfully!");
	}

	public void addPlaylist(Playlist playlist) {
		playlistDAO.addPlaylist(playlist);
		System.out.println("Your playlist has been created successfully!");
	}

	public void addToPlaylist(SongService songService, PodcastService podcastService, Scanner scanner) {
		System.out.println("Please provide a name for your playlist:");
		String playlistName = scanner.nextLine().trim();
		Playlist playlist = playlistDAO.getPlaylistByName(playlistName);
		if (playlist == null) {
			System.out.println("The playlist you are looking for wasn't found. Please check the name and try again.");
			return;
		}

		System.out.println("Please type 'song' to add a song or 'podcast' to add a podcast:");
		String type = scanner.nextLine().trim().toLowerCase();
		if (type.equals("song")) {
			songService.addSongToPlaylist(playlist, scanner);
		} else if (type.equals("podcast")) {
			podcastService.addPodcastToPlaylist(playlist, scanner);
		} else {
			System.out.println("Invalid type.");
		}

		playlistDAO.updatePlaylist(playlist);
	}

	public void viewPlaylist(Scanner scanner) {
		System.out.println("Enter the name of the playlist:");
		String playlistName = scanner.nextLine().trim();
		Playlist playlist = playlistDAO.getPlaylistByName(playlistName);
		if (playlist == null) {
			System.out.println("The playlist you are looking for wasn't found. Please check the name and try again.");
			return;
		}

		System.out.println("Contents of playlist " + playlistName + " :");
		for (Song song : playlist.getSongs()) {
			System.out.println(song);
		}
		for (Podcast podcast : playlist.getPodcasts()) {
			System.out.println(podcast);
		}
	}
}
