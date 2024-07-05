package dao;

import java.util.ArrayList;
import java.util.List;
import pojo.Playlist;

public class PlaylistDAO {
	private List<Playlist> playlists = new ArrayList<>();

	public void addPlaylist(Playlist playlist) {
		playlists.add(playlist);
	}

	public Playlist getPlaylistByName(String name) {
		for (Playlist playlist : playlists) {
			if (playlist.getName().equalsIgnoreCase(name)) {
				return playlist;
			}
		}
		return null;
	}

	public void updatePlaylist(Playlist playlist) {
		for (int i = 0; i < playlists.size(); i++) {
			if (playlists.get(i).getName().equalsIgnoreCase(playlist.getName())) {
				playlists.set(i, playlist);
				return;
			}
		}
	}

	public List<Playlist> getAllPlaylists() {
		return new ArrayList<>(playlists);
	}
}
