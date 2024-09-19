// 2014 Dec COMP2396 Question 1a)
/* The problem is, the array of students were initialized,
* But the individual student objects were not initialized.
* A fix would be add "cs0396[0] = new Student();" and "cs0396[1] = new Student();"
* before assigning values to the student objects.
* The output would be
* Kevin 12345
* Haoyuan 67890
 */
class Student {
	String name;
	int id;
}

public class question1a {
	public static void main(String[] args) {
		// Initialize the student array
		Student[] cs0396 = new Student[2];

		// Initialize individual student objects
		cs0396[0] = new Student();
		cs0396[1] = new Student();

		cs0396[0].name = "Kevin";
		cs0396[0].id = 12345;
		cs0396[1].name = "Haoyuan";
		cs0396[1].id = 67890;

		for (int i = 0; i < 2; ++i) {
			System.out.println(cs0396[i].name + " " + cs0396[i].id);
		}
	}
}