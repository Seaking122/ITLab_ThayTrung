package com.topoica.hungvn.java8;

public class Student {
	private String name;
	private int age;
	
	public Student() {
		super();
	}
	public Student(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
//	Comparator<Student> byName = new Comparator<Student>() {
//
//		@Override
//		public int compare(Student o1, Student o2) {
//			return o1.getName().compareTo(o2.getName());
//		}
//	};
//	Comparator<Student> byName1 = 
//			(Student o1, Student o2)->o1.getName().compareTo(o2.getName());
			@Override
			public String toString() {
				// TODO Auto-generated method stub
				return this.name + " " + this.age;
			}
}
