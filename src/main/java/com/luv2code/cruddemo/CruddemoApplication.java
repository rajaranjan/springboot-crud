package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.AccountDao;
import com.luv2code.cruddemo.dao.InstructorDao;
import com.luv2code.cruddemo.dao.StudentDao;
import com.luv2code.cruddemo.entity.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

//	public CommandLineRunner commandLineRunner(StudentDao studentDao) {
//		 return runner -> {
//			 System.out.println("Hello World");
//			 createStudent(studentDao);
//             createMultiStudents(studentDao);
//			 readStudent(studentDao);
//			 queryForStudents(studentDao);
//			 queryForStudentsByLastName(studentDao);
//			 updateStudent(studentDao);
//			 deleteStudent(studentDao);
//			 deleteAllStudents(studentDao);
//		 };
//	}
//	@Bean
//	public CommandLineRunner commandLineRunner(InstructorDao instructorDao) {
//		return runner -> {
//			System.out.println("Hello");
//			// createInstructor(instructorDao);
//			// findInstructor(instructorDao);
//			// removeInstructor(instructorDao);
//			// findInstructorDetail(instructorDao);
//			// createInstructorWithCourses(instructorDao);
//			// findInstructorWithCourses(instructorDao);
//			// findCoursesForInstructor(instructorDao);
//			// updateInstructor(instructorDao);
//			// updateCourse(instructorDao);
//			// deleteCourse(instructorDao);
//			// createCourseAndReview(instructorDao);
//			// retrieveCourseAndReviews(instructorDao);
//			// deleteCourseAndReviews(instructorDao);
//
//			// many to many started
//			// createCourseAndStudents(instructorDao);
//			// findCourseAndStudents(instructorDao);
//			// findStudentAndCourses(instructorDao);
//			// addMoreCoursesForStudents(instructorDao);
//			// deleteCourse(instructorDao);
//			// deleteStudentById(instructorDao);
//		};
//	}
	@Bean
	public CommandLineRunner commandLineRunner(AccountDao accountDao) {
		return runner -> {
			demoTheBeforeAdvice(accountDao);
		};
	}

	private void demoTheBeforeAdvice(AccountDao theAccountDao) {

		// Call the business method
		theAccountDao.addAccount();
	}

	private void deleteStudentById(InstructorDao instructorDao) {

		int theId = 1;
		System.out.println("Deleting student id: " + theId);

		instructorDao.deleteStudentById(theId);
		System.out.println("Done !!");
	}

	private void addMoreCoursesForStudents(InstructorDao instructorDao) {

		int theId = 2;
		Student tempStudent = instructorDao.findStudentAndCoursesByStudentId(theId);

		// create more courses
		Course tempCourse1 = new Course("Mordern dance - Mordern flavour");
		Course tempCourse2 = new Course("Pop Dance - In the major places");

		// add courses to student
		tempStudent.addCourse(tempCourse1);
		tempStudent.addCourse(tempCourse2);

		System.out.println("Saving Student: " + tempStudent);
		System.out.println("Associated courses: " + tempStudent.getCourses());
		instructorDao.update(tempStudent);
	}

	private void findStudentAndCourses(InstructorDao instructorDao) {

		int theId = 2;
		Student tempStudent = instructorDao.findStudentAndCoursesByStudentId(theId);

		System.out.println("Loaded Student: " + tempStudent);
		System.out.println("Associated courses: " + tempStudent.getCourses());
		System.out.println("Done !!");

	}

	private void findCourseAndStudents(InstructorDao instructorDao) {

		int theId = 10;
		Course tempCourse = instructorDao.findCourseAndStudentsByCourseId(theId);

		System.out.println("Loaded Courses: " + tempCourse);
		System.out.println("Students: " + tempCourse.getStudents());
		System.out.println("Done !!");
	}

	private void createCourseAndStudents(InstructorDao instructorDao) {

		// create a course
		Course tempCourse = new Course("Classical dance - golden piece");

		// create the students
		Student tempStudent1 = new Student("John", "Doe", "john@gmail.com");
		Student tempStudent2 = new Student("Mary", "Public", "mary@gmail.com");

		// add students to the course
		tempCourse.addStudent(tempStudent1);
		tempCourse.addStudent(tempStudent2);

		// save the course and associated students
		System.out.println("Saving the course: " + tempCourse);
		System.out.println("Associated Students: " + tempCourse.getStudents());
		instructorDao.save(tempCourse);
		System.out.println("Done !!");
	}

	private void deleteCourseAndReviews(InstructorDao instructorDao) {

		int theId = 10;
		System.out.println("Deleting course id:" + theId);

		instructorDao.deletecourseById(theId);
		System.out.println("Done !!");
	}

	private void retrieveCourseAndReviews(InstructorDao instructorDao) {

		// get the course and reviews
		int theId = 10;
		Course tempCourse = instructorDao.findCourseAndReviewsByCourseId(theId);

		// print the course
		System.out.println(tempCourse);

		// print the reviews
		System.out.println(tempCourse.getReviews());

		System.out.println("Done !!");
	}

	private void createCourseAndReview(InstructorDao instructorDao) {

		// create a course
		Course tempCourse = new Course("Break Dance - one in a million");

		// add some reviews
		tempCourse.addReview(new Review("Great course. Loved it"));
		tempCourse.addReview(new Review("Good course. Would do one more"));
		tempCourse.addReview(new Review("Cool course. Job Well Done"));

		// save the course and leverage the cascade
		System.out.println("Saving the course");
		System.out.println(tempCourse);
		System.out.println(tempCourse.getReviews());
		instructorDao.save(tempCourse);
		System.out.println("Done !!");
	}

	private void deleteCourse(InstructorDao instructorDao) {

		int theId = 10;
		System.out.println("Deleting course by id: " + theId);

		instructorDao.deletecourseById(theId);
		System.out.println("Done !!");
	}

	private void updateCourse(InstructorDao instructorDao) {
		int theId = 10;

		// find the course
		System.out.println("Finding course by id: " + theId);
		Course tempCourse = instructorDao.findCourseById(theId);

		// update the course
		tempCourse.setTitle("Classical Dance - Ultimate Guide");
		instructorDao.update(tempCourse);
		System.out.println("Done !!");


	}

	private void updateInstructor(InstructorDao instructorDao) {

		int theId = 1;
		// find the instructor
		System.out.println("Find instructor with id: " + theId);
		Instructor tempInstructor = instructorDao.findInstructorById(theId);

		// update the Instructor
		System.out.println("Updating isntructor id: " + theId);
		tempInstructor.setLastName("Kohli");
		instructorDao.update(tempInstructor);
		System.out.println("Done !!");
	}

	private void findCoursesForInstructor(InstructorDao instructorDao) {

		int theId = 1;
		Instructor tempInstructor = instructorDao.findInstructorById(theId);
		System.out.println("tempInstructor: " + tempInstructor);

		// find courses for instructor
		System.out.println("Finding courses for instructor id: " + theId);
		List<Course> courses = instructorDao.findCoursesByInstructorId(theId);

		// associate the objects
		tempInstructor.setCourses(courses);
		System.out.println("associated courses: " + tempInstructor.getCourses());
		System.out.println("Done !!");
	}

	private void findInstructorWithCourses(InstructorDao instructorDao) {

		int theId = 1;
		Instructor tempInstructor = instructorDao.findInstructorById(theId);

		System.out.println("tempInstructor: " + tempInstructor);
		System.out.println("Associated courses: " + tempInstructor.getCourses());
		System.out.println("Done !!");
	}

	private void createInstructorWithCourses(InstructorDao instructorDao) {
		// create the instructor
		Instructor tempInstructor = new Instructor("Brad", "Pitt","brad@gmail.com");

		// create the instructor detail
		InstructorDetail tempInstructorDetail = new InstructorDetail(
				"http://youtube.com/luv2dance",
				"Luv 2 dance !!"
		);

		// associate the object
		tempInstructor.setInstructorDetail(tempInstructorDetail);

		// create courses
		Course tempCourse1 = new Course("Break Dance - Ultimate guide");
		Course tempCourse2 = new Course("Rock Dance - Ultimate review");

		// add courses to instructor
		tempInstructor.add(tempCourse1);
		tempInstructor.add(tempCourse2);

		// save the constructor
		// NOTE - This will also save the details object
		// because of CascadeTpe.ALL
		System.out.println("Saving the instructor... " + tempInstructor);
		System.out.println("Get Courses... " + tempInstructor.getCourses());
		instructorDao.save(tempInstructor);
		System.out.println("Done !!");
	}

	private void findInstructorDetail(InstructorDao instructorDao) {

		// get the instructor detail object
		int theId = 5;
		InstructorDetail tempInstructorDetail = instructorDao.findInstructorDetailById(theId);

		// print the instructor detail
		System.out.println("tempInstructorDetail: " + tempInstructorDetail);

		// print the associated instructor
		System.out.println("the associated instructor: " + tempInstructorDetail.getInstructor());
		System.out.println("Done !!");
	}

	private void removeInstructor(InstructorDao instructorDao) {
		int theId = 6;
		System.out.println("Delete instructor of id: " + theId);

		instructorDao.deleteInstructorById(theId);
		System.out.println("Done !!");
	}

	private void findInstructor(InstructorDao instructorDao) {
		int theId = 5;
		System.out.println("Finding instructor id: " + theId);

		Instructor tempInstructor = instructorDao.findInstructorById(theId);

		System.out.println("tempInstructor: " + tempInstructor);
		System.out.println("the associated instructorDetail: " + tempInstructor.getInstructorDetail());
	}

	private void createInstructor(InstructorDao instructorDao) {

		// create the instructor
		Instructor tempInstructor = new Instructor("Brad", "Pitt","brad@gmail.com");

		// create the instructor detail
		InstructorDetail tempInstructorDetail = new InstructorDetail(
				"http://youtube.com/luv2dance",
				"Luv 2 dance !!"
		);

		// associate the object
		tempInstructor.setInstructorDetail(tempInstructorDetail);

		// save the constructor
		// NOTE - This will also save the details object
		// because of CascadeTpe.ALL
		System.out.println("Saving the instructor... " + tempInstructor);
		instructorDao.save(tempInstructor);
	}

	private void deleteAllStudents(StudentDao studentDao) {
		// Delete all students
		System.out.println("Delete All Students ...");
		int numRowsDeleted = studentDao.deleteAll();
		System.out.println("Deleted Row Count ..." + numRowsDeleted);
	}

	private void deleteStudent(StudentDao studentDao) {
		int studentId = 2;
		// deleting student
		System.out.println("Deleting student of id " + studentId);
		studentDao.delete(studentId);
	}

	private void updateStudent(StudentDao studentDao) {

		// retrieve student based on primary id
		int studentId = 1;
		System.out.println(" Getting student Id: " + studentId);
		Student myStudent = studentDao.findById(studentId);

		// Change first name
		System.out.println("Update the student ...");
		myStudent.setFirstName("John");

		// update the student
		studentDao.update(myStudent);

		// display the updated student
		System.out.println("Updated Student ..." + myStudent);
	}

	private void queryForStudentsByLastName(StudentDao studentDao) {
		// get a list of students
		List<Student> theStudents = studentDao.findByLastName("Chan");

		// display list of students
		for(Student tempStudent: theStudents) {
			System.out.println(tempStudent);
		}
	}

	private void queryForStudents(StudentDao studentDao) {
		// get a list of students
		List<Student> theStudents = studentDao.findAll();

		// display list of students
		for(Student tempStudent : theStudents) {
			System.out.println(tempStudent);
		}
	}

	private void readStudent(StudentDao studentDao) {

		// create a student object
		System.out.println("Creating new student object ...");
		Student tempStudent = new Student("Mohd", "Shami", "shami@gmail.com");

		// save the student
		System.out.println("Saving the student ...");
		studentDao.save(tempStudent);

		// display id of the saved student
		int theId = tempStudent.getId();
		System.out.println("Saved student. Gnerate Id: " + theId);

		// retrieve student based on Id: primary key
		System.out.println("Retrieving student with id:  " + theId);
		Student myStudent = studentDao.findById(theId);

		// display student
		System.out.println("Found the student: " + myStudent);
	}

	private void createMultiStudents(StudentDao studentDao) {

		//create multiple students
		System.out.println("Creating 3 student objects ...");
		Student tempStudent1 = new Student("Rez", "Chen", "rex@gmail.com");
		Student tempStudent2 = new Student("Virat", "Kohli", "virat@gmail.com");
		Student tempStudent3 = new Student("Rohit", "Sharma", "rohit@gmail.com");

		// save student objects
		System.out.println("Saving the students ...");
		studentDao.save(tempStudent1);
		studentDao.save(tempStudent2);
		studentDao.save(tempStudent3);
	}

	private void createStudent(StudentDao studentDao) {
		// create the student object
		System.out.println("creating new student object ...");
		Student tempStudent = new Student("Alex", "Chan", "alex@gmail.com");

		// save the student object
		System.out.println("Saving the student object");
		studentDao.save(tempStudent);

		// display id of saved student
		System.out.println("Id of saved student: " + tempStudent.getId());
	}
}
