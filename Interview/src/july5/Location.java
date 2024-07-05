package july5;

import java.util.Arrays;
import java.util.List;

public class Location {
	int distanceSquared;
	int x;
	int y;

	Location(int x, int y) {
		this.x = x;
		this.y = y;
		this.distanceSquared = x * x + y * y;
	}

	List<Integer> toList() {
		return Arrays.asList(x, y);
	}
}
