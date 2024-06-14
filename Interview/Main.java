package Exercise;

public class Main {

	public static void main(String[] args) {
		Exercise exercise = new Exercise();
		String s1 = " ";
		String s2 = "a";
		String s3 = "aabcdaabc";
		String s4 = "abcab";
		String s5 = "eileeneileen";

		System.out.println(exercise.PrefixSuffix(s1));
		System.out.println(exercise.PrefixSuffix(s2));
		System.out.println(exercise.PrefixSuffix(s3));
		System.out.println(exercise.PrefixSuffix(s4));
		System.out.println(exercise.PrefixSuffix(s5));
	}

}
