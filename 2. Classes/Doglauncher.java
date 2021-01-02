public class Doglauncher {
	public static void main(String[] args) {
		Dog d = new Dog(20);
		Dog d2 = new Dog(10);
		Dog bigger = Dog.maxDog(d, d2);
		// Dog[] dogs = new Dog[2];
		// dogs[0] = new Dog(8);
		// dogs[1] = new Dog(20);
		// dogs[0].makeNoise();
		// bigger.makeNoise();
		d.maxDog(d2).makeNoise();

		// if you have a static variable, use the class name
		System.out.println(Dog.binomen);
		
		// bad styles
		System.out.println(d2.binomen);

	}
}
