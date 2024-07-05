package service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import dao.SongDAO;
import pojo.Playlist;
import pojo.Song;

public class SongService {
	private SongDAO songDAO;

	public SongService() {
		this.songDAO = new SongDAO();
	}

	public void addSong() {
		Scanner scanner = new Scanner(System.in);

		System.out.println("Please fill in the song details below");
		System.out.print("Title: ");
		String title = scanner.nextLine().trim();
		System.out.print("Artist: ");
		String artist = scanner.nextLine().trim();
		System.out.print("Genre: ");
		String genre = scanner.nextLine().trim();
		System.out.print("Album: ");
		String album = scanner.nextLine().trim();

		Song newSong = new Song(title, artist, genre, album);
		songDAO.addSong(newSong);

		System.out.println("Great! The song has been added.");
	}

	public void addSong(Song song) {
		songDAO.addSong(song);
		System.out.println("Great! The song has been added.");
	}

	public void addSongToPlaylist(Playlist playlist, Scanner scanner) {
		List<Song> allSongs = songDAO.getSongs();
		if (allSongs.isEmpty()) {
			System.out.println("Unfortunately, there are no songs available for adding right now.");
			return;
		}

		System.out.println("Here are the available songs:");
		for (int i = 0; i < allSongs.size(); i++) {
			System.out.println((i + 1) + ". " + allSongs.get(i));
		}

		System.out.print("To add a song, please enter its corresponding number:");
		int songIndex = scanner.nextInt();
		scanner.nextLine(); // consume the newline

		if (songIndex < 1 || songIndex > allSongs.size()) {
			System.out.println("Invalid choice.");
			return;
		}

		Song songToAdd = allSongs.get(songIndex - 1);

		if (playlist.getSongs().contains(songToAdd)) {
			System.out.println("This song has already been added to the playlist.");
		} else {
			playlist.addSong(songToAdd);
			System.out.println("Great! The song has been added to your playlist.");
		}
	}

	public void displaySongsByCategory(Scanner scanner) {
		System.out.println("Please choose a category to display songs by:");
		System.out.println("1. Artist");
		System.out.println("2. Genre");
		System.out.println("3. Album");
		System.out.print("Please type the number of your selected option:");
		String categoryChoice = scanner.nextLine().trim();

		System.out.print("Please type the keyword for the category you have chosen:");
		String keyword = scanner.nextLine().trim();

		List<Song> result = null;
		switch (categoryChoice) {
		case "1":
			result = songDAO.getSongsByArtist(keyword);
			break;
		case "2":
			result = songDAO.getSongsByGenre(keyword);
			break;
		case "3":
			result = songDAO.getSongsByAlbum(keyword);
			break;
		default:
			System.out.println("Oops! That category choice is invalid. Please select again.");
			return;
		}

		if (result != null && !result.isEmpty()) {
			Collections.sort(result, Comparator.comparing(Song::getTitle));
			for (Song song : result) {
				System.out.println(song);
			}
		} else {
			System.out.println("Sorry, no songs were found for the given keyword.");
		}
	}

	public void searchSongs(Scanner scanner) {
		System.out.println("Please enter the keyword to search:");
		String keyword = scanner.nextLine().trim();

		List<Song> result = songDAO.getSongs().stream()
				.filter(song -> song.getTitle().contains(keyword) || song.getArtist().contains(keyword)
						|| song.getGenre().contains(keyword) || song.getAlbum().contains(keyword))
				.collect(Collectors.toList());

		if (result != null && !result.isEmpty()) {
			Collections.sort(result, Comparator.comparing(Song::getTitle));
			for (Song song : result) {
				System.out.println(song);
			}
		} else {
			System.out.println("Sorry, no songs were found for the given keyword.");
		}
	}

	public List<Song> getAllSongs() {
		return songDAO.getSongs();
	}

	public List<Song> getSongsByArtist(String artist) {
		return songDAO.getSongsByArtist(artist);
	}

	public List<Song> getSongsByGenre(String genre) {
		return songDAO.getSongsByGenre(genre);
	}

	public List<Song> getSongsByAlbum(String album) {
		return songDAO.getSongsByAlbum(album);
	}
}
