package dao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import pojo.Podcast;

public class PodcastDAO {
	private List<Podcast> podcasts;

	public PodcastDAO() {
		this.podcasts = new ArrayList<>();
	}

	public void addPodcast(Podcast podcast) {
		podcasts.add(podcast);
	}

	public List<Podcast> getPodcasts() {
		return new ArrayList<>(podcasts);
	}

	public List<Podcast> getPodcastsByCelebrity(String celebrity) {
		List<Podcast> result = new ArrayList<>();
		for (Podcast podcast : podcasts) {
			if (podcast.getCelebrity().equalsIgnoreCase(celebrity)) {
				result.add(podcast);
			}
		}
		return result;
	}

	public List<Podcast> getPodcastsByDate(Date date) {
		List<Podcast> result = new ArrayList<>();
		for (Podcast podcast : podcasts) {
			if (podcast.getDatePublished().equals(date)) {
				result.add(podcast);
			}
		}
		return result;
	}
}
