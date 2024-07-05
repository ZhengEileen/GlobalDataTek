package controller;

import java.util.Scanner;
import service.PlaylistService;
import service.PodcastService;
import service.SongService;
import service.JukeboxService;

public class Jukebox {

	public static void main(String[] args) {
		PlaylistService playlistService = new PlaylistService();
		PodcastService podcastService = new PodcastService();
		SongService songService = new SongService();
		JukeboxService jukeboxService = new JukeboxService();

		Scanner scanner = new Scanner(System.in);

		while (true) {
			displayMenu();
			String command = scanner.nextLine().trim();

			if (command.equals("1")) {
				songService.addSong();
			} else if (command.equals("2")) {
				songService.displaySongsByCategory(scanner);
			} else if (command.equals("3")) {
				songService.searchSongs(scanner);
			} else if (command.equals("4")) {
				playlistService.createPlaylist(scanner);
			} else if (command.equals("5")) {
				playlistService.addToPlaylist(songService, podcastService, scanner);
			} else if (command.equals("6")) {
				playlistService.viewPlaylist(scanner);
			} else if (command.equals("7")) {
				podcastService.addPodcast();
			} else if (command.equals("8")) {
				podcastService.displayAllPodcasts();
			} else {
				System.out.println("Oops! That command isn't recognized. Please try again.");
			}
		}
	}

	private static void displayMenu() {
		System.out.println("\nWelcome to the Main Menu! Please choose an option to proceed:");
		System.out.println("1. Add a Song");
		System.out.println("2. Display Songs");
		System.out.println("3. Find a Song");
		System.out.println("4. Make a New Playlist");
		System.out.println("5. Add Song/Podcast to your Playlist");
		System.out.println("6. Show Playlist");
		System.out.println("7. Add a Podcast");
		System.out.println("8. Display Podcasts");
		System.out.print("What would you like to do? Please enter your choice: ");
	}
}
