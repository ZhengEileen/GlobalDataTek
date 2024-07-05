package july5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class RestaurantFinder {

	public static List<List<Integer>> findRestaurants(List<List<Integer>> allLocations, int numRestaurants) {
		List<Location> locations = new ArrayList<>();

		for (List<Integer> location : allLocations) {
			int x = location.get(0);
			int y = location.get(1);
			locations.add(new Location(x, y));
		}

		Collections.sort(locations, (a, b) -> Integer.compare(a.distanceSquared, b.distanceSquared));

		List<List<Integer>> result = new ArrayList<>();
		for (int i = 0; i < numRestaurants; i++) {
			result.add(locations.get(i).toList());
		}

		return result;
	}

	public static void main(String[] args) {
		List<List<Integer>> allLocations = Arrays.asList(Arrays.asList(1, 2), Arrays.asList(3, 4),
				Arrays.asList(1, -1));
		int numRestaurants = 2;

		List<List<Integer>> nearestRestaurants = findRestaurants(allLocations, numRestaurants);
		System.out.println(nearestRestaurants);
	}
}
