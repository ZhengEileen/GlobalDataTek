package dao;

import java.util.ArrayList;
import java.util.List;

import pojo.Song;

public class SongDAO {
	private List<Song> songs;

	public SongDAO() {
		this.songs = new ArrayList<>();
	}

	public void addSong(Song song) {
		songs.add(song);
	}

	public List<Song> getSongs() {
		return new ArrayList<>(songs);
	}

	public List<Song> getSongsByArtist(String artist) {
		List<Song> result = new ArrayList<>();
		for (Song song : songs) {
			if (song.getArtist().equalsIgnoreCase(artist)) {
				result.add(song);
			}
		}
		return result;
	}

	public List<Song> getSongsByGenre(String genre) {
		List<Song> result = new ArrayList<>();
		for (Song song : songs) {
			if (song.getGenre().equalsIgnoreCase(genre)) {
				result.add(song);
			}
		}
		return result;
	}

	public List<Song> getSongsByAlbum(String album) {
		List<Song> result = new ArrayList<>();
		for (Song song : songs) {
			if (song.getAlbum().equalsIgnoreCase(album)) {
				result.add(song);
			}
		}
		return result;
	}
}
