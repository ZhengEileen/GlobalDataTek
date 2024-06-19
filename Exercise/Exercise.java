package Exercise;

public class Exercise {

	public int PrefixSuffix(String str) {
		int size = str.length();

		if (size < 2) {
			return 0;
		}

		int length = 0;

		for (int i = 0; i < size / 2; i++) {
			boolean Matching = true;
			int firstHalf = 0;
			int secondHalf = (size - 1) - i;

			while (firstHalf <= i) {
				if (str.charAt(firstHalf) != str.charAt(secondHalf)) {
					Matching = false;
					break;
				}
				firstHalf++;
				secondHalf++;
			}

			if (Matching == true) {
				length = i + 1;
			}
		}

		return length;
	}
}

