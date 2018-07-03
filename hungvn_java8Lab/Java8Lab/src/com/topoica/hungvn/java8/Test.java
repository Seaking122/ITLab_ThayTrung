package com.topoica.hungvn.java8;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Test {
	public static void main(String[] args) {
		List<Student> listStudents = getStudents();
		/* start sort in java 8 example */
		System.out.println("Before sort: ");
		for (Student student : listStudents) {
			System.out.println(student);
		}
		// sort list use collections
		// Collections.sort(listStudents, new Comparator<Student>() {
		//
		// @Override
		// public int compare(Student o1, Student o2) {
		// return o1.getAge() - o2.getAge();
		// }
		//
		// });
		// listStudents.sort((Student o1, Student o2)->o1.getAge()-o2.getAge());
		listStudents.sort((Student o1, Student o2) -> o1.getName().compareTo(o2.getName()));
		System.out.println("After sort: ");
		for (Student student : listStudents) {
			System.out.println(student);
		}
		/* end sort in java 8 example */

		/* start convert list to map in java 8 example */
		Map<String, Integer> mapStudent = new HashMap<>();
		listStudents.stream().forEach(e -> mapStudent.put(e.getName(), e.getAge()));
		System.out.println("List to map in java 8: ");
		for (Entry<String, Integer> studentMap : mapStudent.entrySet()) {
			System.out.println(studentMap.getKey() + " --->  " + studentMap.getValue());
		}
		/* end convert list to map in java 8 example */

		/* start convert map to list in java 8 example */
		System.out.println("Map to list");
		List<String> key = new ArrayList<>();
		List<Integer> value = new ArrayList<>();
		mapStudent.keySet().stream().forEach(e -> key.add(e));
		mapStudent.values().stream().forEach(e -> value.add(e));
		System.out.println("-----------------------");
		for (Integer v : value) {
			System.out.println(v);
		}
		System.out.println("-----------------------");
		for (String k : key) {
			System.out.println(k);
		}
		/* end convert map to list in java 8 example */

		/* start use parallelStream() in java 8 */
		List<Integer> listInt = new ArrayList<>();
		for (int i = 0; i < 10000; i++) {
			listInt.add(i);
		}
		long start = System.currentTimeMillis();
		listInt.stream().forEach(e -> System.out.println(e));
		System.out.println(System.currentTimeMillis() - start + " s");
		long end = System.currentTimeMillis() - start;
		long start1 = System.currentTimeMillis();
		listInt.parallelStream().forEach(e -> System.out.println(e));
		long end1 = System.currentTimeMillis() - start1;
		System.out.println("=========================");
		System.out.println("Stream() : " + end + " s");
		System.out.println("parallelStream() : " + end1 + " s");
	}

	private static List<Student> getStudents() {

		List<Student> result = new ArrayList<>();

		result.add(new Student("Hung", 23));
		result.add(new Student("Khoi", 24));
		result.add(new Student("Duy", 18));
		result.add(new Student("Luong", 21));

		return result;

	}
}
