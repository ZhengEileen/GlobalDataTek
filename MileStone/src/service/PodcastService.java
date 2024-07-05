package service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import dao.PodcastDAO;
import pojo.Podcast;
import pojo.Playlist;

public class PodcastService {
	private PodcastDAO podcastDAO;

	public PodcastService() {
		this.podcastDAO = new PodcastDAO();
	}

	public void addPodcast() {
		Scanner scanner = new Scanner(System.in);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		System.out.println("Please fill in the podcast details below");
		System.out.print("Title:");
		String title = scanner.nextLine().trim();
		System.out.print("Celebrity:");
		String celebrity = scanner.nextLine().trim();
		System.out.print("Date Published (yyyy-mm-dd): ");
		String dateStr = scanner.nextLine().trim();
		Date datePublished = null;
		try {
			datePublished = dateFormat.parse(dateStr);
		} catch (ParseException e) {
			System.out.println("The date format is incorrect. Please use yyyy-mm-dd.");
			return;
		}

		Podcast newPodcast = new Podcast(title, celebrity, datePublished);
		podcastDAO.addPodcast(newPodcast);

		System.out.println("Great! The podcast has been added.");
	}

	public void addPodcast(Podcast podcast) {
		podcastDAO.addPodcast(podcast);
		System.out.println("Great! The podcast has been added.");
	}

	public void addPodcastToPlaylist(Playlist playlist, Scanner scanner) {
		List<Podcast> allPodcasts = podcastDAO.getPodcasts();
		if (allPodcasts.isEmpty()) {
			System.out.println("Unfortunately, there are no podcasts available for adding right now.");
			return;
		}

		System.out.println("Here are the available podcasts:");
		for (int i = 0; i < allPodcasts.size(); i++) {
			System.out.println((i + 1) + ". " + allPodcasts.get(i));
		}

		System.out.print("To add a podcast, please enter its corresponding number:");
		int podcastIndex = scanner.nextInt();
		scanner.nextLine();

		if (podcastIndex < 1 || podcastIndex > allPodcasts.size()) {
			System.out.println("Invalid choice.");
			return;
		}

		Podcast podcastToAdd = allPodcasts.get(podcastIndex - 1);

		if (playlist.getPodcasts().contains(podcastToAdd)) {
			System.out.println("This podcast has already been added to the playlist.");
		} else {
			playlist.addPodcast(podcastToAdd);
			System.out.println("Great! The podcast has been added to your playlist.");
		}
	}

	public void displayAllPodcasts() {
		List<Podcast> podcasts = getAllPodcasts();
		for (Podcast podcast : podcasts) {
			System.out.println(podcast);
		}
	}

	public List<Podcast> getAllPodcasts() {
		return podcastDAO.getPodcasts();
	}

	public List<Podcast> getPodcastsByCelebrity(String celebrity) {
		return podcastDAO.getPodcastsByCelebrity(celebrity);
	}

	public List<Podcast> getPodcastsByDate(Date date) {
		return podcastDAO.getPodcastsByDate(new java.sql.Date(date.getTime()));
	}

	public void searchPodcast(String category, String keyword) {
		List<Podcast> result = null;
		switch (category.toLowerCase()) {
		case "celebrity":
			result = podcastDAO.getPodcastsByCelebrity(keyword);
			break;
		case "date":
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			try {
				Date date = dateFormat.parse(keyword);
				result = podcastDAO.getPodcastsByDate(new java.sql.Date(date.getTime()));
			} catch (ParseException e) {
				System.out.println("The date format is incorrect. Please use yyyy-mm-dd.");
				return;
			}
			break;
		default:
			System.out.println("Invalid category. Please use 'celebrity' or 'date'.");
			return;
		}
		if (result != null) {
			for (Podcast podcast : result) {
				System.out.println(podcast);
			}
		}
	}
}

